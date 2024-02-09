package pl.kondziet.springbackend.application.service.dto

import pl.kondziet.springbackend.domain.model.User

data class UserRequest(val name: String, val accessCode: String? = null) {
    fun toUser() = User(name = name, accessCode = accessCode)
}
