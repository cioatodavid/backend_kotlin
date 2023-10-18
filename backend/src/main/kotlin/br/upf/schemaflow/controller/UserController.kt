package br.upf.schemaflow.controller

import org.springframework.web.bind.annotation.*
import br.upf.schemaflow.entity.UserEntity
import br.upf.schemaflow.service.UserService

@RestController
@RequestMapping("/api/user")
class UserController {

    private val userService: UserService = UserService()

    @GetMapping("/{userId}")
    fun getUserById(@PathVariable userId: Long): UserEntity? {
        return userService.findUserById(userId)
    }

    @PostMapping
    fun createUser(@RequestBody user: UserEntity): UserEntity {
        return userService.createUser(user)
    }

    @PutMapping("/{userId}")
    fun updateUser(@PathVariable userId: Long, @RequestBody user: UserEntity): UserEntity {
        return userService.updateUser(user)
    }

    @DeleteMapping("/{userId}")
    fun deleteUser(@PathVariable userId: Long) {
        userService.deleteUser(userService.findUserById(userId))
    }


}