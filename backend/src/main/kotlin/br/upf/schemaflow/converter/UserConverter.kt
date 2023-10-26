package br.upf.schemaflow.converter

import br.upf.schemaflow.dto.UserDTO
import br.upf.schemaflow.entity.UserEntity
import br.upf.schemaflow.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserConverter(
    private val userRepository: UserRepository
) {
    fun convertToEntity(userDTO: UserDTO): UserEntity {
        return userRepository.findByUsername(userDTO.username).orElse(null)
    }

    fun convertToDTO(userEntity: UserEntity): UserDTO {
        return UserDTO(
            id = userEntity.id,
            username = userEntity.username,
        )
    }
}
