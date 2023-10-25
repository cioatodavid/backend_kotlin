package br.upf.schemaflow.service

import br.upf.schemaflow.dto.UserDTO
import br.upf.schemaflow.dto.UserResponseDTO
import br.upf.schemaflow.entity.UserEntity
import br.upf.schemaflow.repository.UserRepository
import br.upf.schemaflow.utils.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var jwtUtil: JwtUtil

    private val passwordEncoder = BCryptPasswordEncoder()


    fun register(user: UserDTO): UserResponseDTO {
        val userData = UserDTO(user.username, user.password)
        val newUser = createUser(userData)
        val newToken = jwtUtil.generateToken(newUser.username)
        return UserResponseDTO(newUser.username, newToken)
    }

    fun login(user: UserDTO): UserResponseDTO {
        val username = user.username
        val password = user.password
        if (checkCredentials(username, password)) {
            val newToken = jwtUtil.generateToken(username)
            return UserResponseDTO(username, newToken)
        }
        throw IllegalArgumentException("Invalid credentials")
    }


    /* OTHERS */


    fun createUser(user: UserDTO): UserDTO {
        if (userRepository.findByUsername(user.username).isPresent) {
            throw IllegalArgumentException("Username already taken")
        }
        val userData = UserEntity(username = user.username, password = encodePassword(user.password))
        val newUser = userRepository.save(userData)
        return UserDTO(newUser.username, newUser.password)

    }


    fun getUserByUsername(username: String): Optional<UserEntity> {
        return userRepository.findByUsername(username)
    }

    fun encodePassword(rawPassword: String): String {
        return passwordEncoder.encode(rawPassword)
    }

    fun checkCredentials(username: String, rawPassword: String): Boolean {
        val user = getUserByUsername(username)
        return user.isPresent && passwordEncoder.matches(rawPassword, user.get().password)
    }

}



