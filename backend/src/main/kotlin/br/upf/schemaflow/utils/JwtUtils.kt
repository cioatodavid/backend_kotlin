package br.upf.schemaflow.utils


import com.auth0.jwt.JWT
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil {

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    @Value("\${jwt.expiration}")
    private val expiration: Long = 0

    fun generateToken(username: String): String {
        return JWT.create()
            .withSubject(username)
            .withIssuedAt(Date(System.currentTimeMillis()))
            .withExpiresAt(Date(System.currentTimeMillis() + expiration * 1000))
            .sign(com.auth0.jwt.algorithms.Algorithm.HMAC512(secret.toByteArray()))
    }


    fun getUsernameFromToken(token: String): String {
        return JWT.require(com.auth0.jwt.algorithms.Algorithm.HMAC512(secret.toByteArray()))
            .build()
            .verify(token)
            .subject
    }

    fun validateToken(token: String): Boolean {
        return !isTokenExpired(token)
    }

    fun isTokenExpired(token: String): Boolean {
        return JWT.require(com.auth0.jwt.algorithms.Algorithm.HMAC512(secret.toByteArray()))
            .build()
            .verify(token)
            .expiresAt
            .before(Date())
    }
}
