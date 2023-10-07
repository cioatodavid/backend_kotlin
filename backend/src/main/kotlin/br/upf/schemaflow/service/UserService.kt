package br.upf.schemaflow.service

import br.upf.schemaflow.entity.UserEntity
import br.upf.schemaflow.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired

class UserService(@Autowired val userRepository: UserRepository) {
    fun createUser(user: UserEntity): UserEntity {
        return userRepository.save(user)
    }

    fun updateUser(user: UserEntity): UserEntity {
        return userRepository.save(user)
    }

    fun deleteUser(user: UserEntity) {
        userRepository.delete(user)
    }

    fun findUserById(id: Long): UserEntity {
        return userRepository.findById(id).get()
    }
}