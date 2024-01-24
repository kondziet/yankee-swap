package pl.kondziet.springbackend.application.service.dto

import pl.kondziet.springbackend.model.Constraint

data class ConstraintRequest(val user: UserRequest, val excludedNeighbors: List<UserRequest>) {
    fun toConstraint() = Constraint(user = user.toUser(), excludedUsers = excludedNeighbors.map { it.toUser() })
}