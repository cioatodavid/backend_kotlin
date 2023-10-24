package br.upf.schemaflow.controller

import br.upf.schemaflow.dto.LoginDTO
import br.upf.schemaflow.dto.RegisterDTO
import br.upf.schemaflow.dto.UserDTO
import br.upf.schemaflow.service.UserService
import br.upf.schemaflow.utils.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("auth")
class AuthController {
    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var jwtUtil: JwtUtil


    @PostMapping("register")
    fun register(@RequestBody body: RegisterDTO): ResponseEntity<Any> {
        return try {
            val user = RegisterDTO(body.username, body.password)
            userService.createUser(user)
            ResponseEntity.status(201).body(RegisterDTO(user.username, user.password))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @PostMapping("login")
    fun login(@RequestBody body: LoginDTO, response: HttpServletResponse): ResponseEntity<Any> {
        return try {
            val user =
                userService.getUserByUsername(body.username).orElseThrow { IllegalArgumentException("User not found") }
            if (userService.checkCredentials(body.username, body.password)) {
                val token = jwtUtil.generateToken(user.id.toString())
                val cookie = Cookie("token", token)
                cookie.isHttpOnly = true
                cookie.maxAge = 60 * 60 * 24 * 7
                response.addCookie(cookie)
                ResponseEntity.ok(UserDTO(user))
            } else {
                ResponseEntity.badRequest().body("Invalid credentials")
            }
        } catch (e: Exception) {
            ResponseEntity.badRequest().body("Login failed: ${e.message}")
        }
    }

}
