package pl.kondziet.springbackend.application.service.dto

import pl.kondziet.springbackend.domain.model.Group

data class GroupRequest(
    val name: String,
    val description: String,
    val users: List<UserRequest>,
    val constraints: List<ConstraintRequest>
) {
    fun toGroup() = Group(
        name = name,
        description = description,
        members = users.map { it.toUser() },
        constraints = constraints.map { it.toConstraint() }
    )
}
