package br.upf.schemaflow.dto

import br.upf.schemaflow.entity.UserEntity

class UserDTO(user: UserEntity) {
    private var username: String

    init {
        username = user.username
    }
}
