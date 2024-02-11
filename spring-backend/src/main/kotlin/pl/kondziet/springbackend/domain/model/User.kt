package pl.kondziet.springbackend.domain.model

import pl.kondziet.springbackend.application.service.dto.UserResponse

data class User(val name: String) {
    fun toUserResponse() = UserResponse(name)
}
