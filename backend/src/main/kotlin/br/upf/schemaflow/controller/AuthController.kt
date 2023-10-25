package br.upf.schemaflow.controller

import br.upf.schemaflow.dto.UserDTO
import br.upf.schemaflow.dto.UserResponseDTO
import br.upf.schemaflow.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("auth")
class AuthController {
    @Autowired
    private lateinit var userService: UserService

    @PostMapping("register")
    fun register(@RequestBody body: UserDTO): ResponseEntity<UserResponseDTO> {
        return ResponseEntity.ok(userService.register(body))
    }

    @PostMapping("login")
    fun login(@RequestBody body: UserDTO): ResponseEntity<UserResponseDTO> {
        return ResponseEntity.ok(userService.login(body))
    }
  
}
