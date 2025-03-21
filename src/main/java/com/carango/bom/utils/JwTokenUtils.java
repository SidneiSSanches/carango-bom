
package com.carango.bom.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.Base64;

@Component
public class JwTokenUtils {

	private final byte[] SECRET_KEY = Base64.getDecoder().decode("TU9ieW6hQk2q2SxHS8L/hQtBsew2r4Q5+DqSBoklPOo=");

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		return createToken(new HashMap<>(), userDetails.getUsername(),
				new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)); // 10 hours
	}

	public String generateToken(UserDetails userDetails, Date expiration) {
		return createToken(new HashMap<>(), userDetails.getUsername(), expiration);
	}

	private String createToken(Map<String, Object> claims, String subject, Date expiration) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(expiration).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
}
