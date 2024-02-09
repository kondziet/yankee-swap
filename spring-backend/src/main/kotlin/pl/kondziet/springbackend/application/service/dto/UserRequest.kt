package pl.kondziet.springbackend.application.service.dto

import pl.kondziet.springbackend.domain.model.User

data class UserRequest(val name: String) {
    fun toUser() = User(name = name)
}
