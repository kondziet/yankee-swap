package pl.kondziet.springbackend.application.service.dto

import pl.kondziet.springbackend.domain.model.Constraint

data class ConstraintRequest(val user: UserRequest, val excludedDrawees: List<UserRequest>) {
    fun toConstraint() = Constraint(user = user.toUser(), excludedDrawees = excludedDrawees.map { it.toUser() })
}