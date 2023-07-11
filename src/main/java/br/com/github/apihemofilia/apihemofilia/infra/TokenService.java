package br.com.github.apihemofilia.apihemofilia.infra;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.github.apihemofilia.apihemofilia.domain.entitys.Login;

@Service
public class TokenService {

	@Value("${api.hemofilia.secret}")
	private String secret;

	public String generatedToken(final Login login) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			final String token = JWT.create().withIssuer("api-hemofilia")
					.withSubject(login.getUsername())
					.withExpiresAt(getExpirationToken())
					.sign(algorithm);
			return token;
		} catch (JWTCreationException e) {
			throw new RuntimeException("Error while generated the token", e);
		}
	}
	
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
					.withIssuer("api-hemofilia")
					.build()
					.verify(token)
					.getSubject();
		} catch (JWTVerificationException e) {
			return Strings.EMPTY;
		}
	}

	private Instant getExpirationToken() {
		return LocalDateTime.now().plusHours(1)
				.toInstant(ZoneOffset.of("-03:00"));
	}
}
