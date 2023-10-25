package br.upf.schemaflow.service

import br.upf.schemaflow.converter.UserConverter
import br.upf.schemaflow.dto.UserDTO
import br.upf.schemaflow.dto.UserRequestDTO
import br.upf.schemaflow.dto.UserResponseDTO
import br.upf.schemaflow.entity.UserEntity
import br.upf.schemaflow.repository.UserRepository
import br.upf.schemaflow.utils.JwtUtil
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userConverter: UserConverter,
    private var jwtUtil: JwtUtil
) {

    private val passwordEncoder = BCryptPasswordEncoder()

    @Transactional

    fun getUserById(id: Long): UserDTO {
        val userEntity = userRepository.findById(id)
            .orElseThrow { EntityNotFoundException("User not found with id: $id") }
        return userConverter.convertToDTO(userEntity)
    }

    @Transactional
    fun updateUser(id: Long, userDTO: UserDTO): UserDTO {
        val existingUser = userRepository.findById(id)
            .orElseThrow { EntityNotFoundException("User not found with id: $id") }

        // Update the fields of existingUser with the fields from userDTO
        existingUser.username = userDTO.username
        // Add other fields as necessary

        val updatedUser = userRepository.save(existingUser)
        return userConverter.convertToDTO(updatedUser)
    }

    @Transactional
    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }

    @Transactional
    fun register(user: UserRequestDTO): UserResponseDTO {
        val userData = UserRequestDTO(user.username, user.password)
        val newUser = createUser(userData)
        val newToken = jwtUtil.generateToken(newUser.username)
        return UserResponseDTO(newUser.username, newToken)
    }

    fun login(user: UserRequestDTO): UserResponseDTO {
        val username = user.username
        val password = user.password
        if (checkCredentials(username, password)) {
            val newToken = jwtUtil.generateToken(username)
            return UserResponseDTO(username, newToken)
        }
        throw IllegalArgumentException("Invalid credentials")
    }


    /* OTHERS */


    fun createUser(user: UserRequestDTO): UserRequestDTO {
        if (userRepository.findByUsername(user.username).isPresent) {
            throw IllegalArgumentException("Username already taken")
        }
        val userData = UserEntity(username = user.username, password = encodePassword(user.password))
        val newUser = userRepository.save(userData)
        return UserRequestDTO(newUser.username, newUser.password)

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



