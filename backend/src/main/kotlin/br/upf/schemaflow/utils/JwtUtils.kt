package br.upf.schemaflow.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil {

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    @Value("\${jwt.expiration}")
    private val expiration: Long = 0

    fun generateToken(userId: String): String {
        return Jwts.builder()
            .setIssuer(userId)
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact()
    }

    fun extractUserId(jwt: String): String? {
        return getClaims(jwt)?.issuer
    }

    private fun getClaims(jwt: String): Claims? {
        return try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).body
        } catch (e: Exception) {
            null
        }
    }
}
