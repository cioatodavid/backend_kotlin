package br.upf.schemaflow.service

import br.upf.schemaflow.entity.UserEntity
import br.upf.schemaflow.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService() {
    @Autowired
    lateinit var userRepository: UserRepository

    fun createUser(user: UserEntity): UserEntity {
        return userRepository.save(user)
    }

    fun updateUser(user: UserEntity): UserEntity {
        if (userRepository.existsById(user.id)) {
            return userRepository.save(user)
        }
        throw Exception("User not found")
    }


    fun deleteUser(user: UserEntity?) {
        if (user != null) {
            if (userRepository.existsById(user.id)) {
                userRepository.deleteById(user.id)
            } else {
                throw Exception("User not found")
            }
        }
    }


    fun findUserById(id: Long): UserEntity? {
        return userRepository.findById(id).orElse(null)
    }

}