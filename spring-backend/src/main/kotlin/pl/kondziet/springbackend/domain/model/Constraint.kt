package pl.kondziet.springbackend.domain.model

data class Constraint(val user: User, val excludedUsers: List<User>)
