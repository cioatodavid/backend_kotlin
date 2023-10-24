package br.upf.schemaflow.service

import br.upf.schemaflow.dto.RegisterDTO
import br.upf.schemaflow.dto.UserDTO
import br.upf.schemaflow.entity.UserEntity
import br.upf.schemaflow.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService @Autowired constructor(
    private val userRepository: UserRepository
) {

    private val passwordEncoder = BCryptPasswordEncoder()
    fun getById(id: Long): UserEntity? {
        return userRepository.findById(id).orElse(null)
    }

    fun createUser(user: RegisterDTO): UserEntity {
        if (userRepository.findByUsername(user.username).isPresent) {
            throw IllegalArgumentException("Username already taken")
        }

        val encodedPassword = passwordEncoder.encode(user.password)
        val newUser = UserEntity(username = user.username, password = encodedPassword)
        return userRepository.save(newUser)
    }

    fun getUserByUsername(username: String): Optional<UserEntity> {
        return userRepository.findByUsername(username)
    }

    fun encodePassword(rawPassword: String): String {
        return passwordEncoder.encode(rawPassword)
    }

    fun checkCredentials(username: String, rawPassword: String): Boolean {
        val user = getUserByUsername(username).orElseThrow { IllegalArgumentException("User not found") }
        return passwordEncoder.matches(rawPassword, user.password)


    }


}
