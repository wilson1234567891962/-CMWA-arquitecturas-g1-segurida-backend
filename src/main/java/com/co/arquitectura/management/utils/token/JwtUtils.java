package com.co.arquitectura.management.utils.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;

public class JwtUtils implements Serializable {
	final static Logger logger = Logger.getLogger(JwtUtils.class);
	
    public JwtUtils() {
        // TODO Auto-generated constructor stub
    }

    /**
     *
     */
    private static final long serialVersionUID = 511082764571751556L;
    private static String SECRET_KEY = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa-222222222222222222222222222-2222222222222-2222222-2-2";


    public static String createJWT(String idAssociated, String idProduct, String idbeneficiary, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(idAssociated)
                .setIssuedAt(now)
                .setSubject(idProduct)
                .setIssuer(idbeneficiary)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + TimeUnit.MINUTES.toMillis(30);
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public static String createJWT(String origin, String subject , long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(origin)
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(signatureAlgorithm, signingKey);

        long expMillis = nowMillis + TimeUnit.MINUTES.toMillis(ttlMillis);
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public static Claims decodeJWT(String jwt) {
        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = null;
      
            claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                    .parseClaimsJws(jwt).getBody();
       
        return claims;
    }

    public static String decodeJWTAnExtend(String jwt) {
        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims;
        long nowMillis = System.currentTimeMillis();
        String token = "";
        Date now = new Date(nowMillis);
        try {
            claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                    .parseClaimsJws(jwt).getBody();
            if (System.currentTimeMillis() >= 0) {
                long expMillis = nowMillis + TimeUnit.MINUTES.toMillis(30);
                Date exp = new Date(expMillis);
                claims.setExpiration(exp);
            }
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
            JwtBuilder builder = Jwts.builder().setId(claims.getId())
                    .setIssuedAt(now)
                    .setSubject(claims.getSubject())
                    .setIssuer(claims.getIssuer())
                    .signWith(signatureAlgorithm, signingKey);
            token = builder.compact();
        } catch (Exception e) {
            claims = null;
            logger.error("JWT No se pudo extender el token: ", e);
        }
        return token;
    }

}
