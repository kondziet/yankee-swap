package pl.kondziet.springbackend.model

data class Constraint(val user: User, val excludedUsers: List<User>)
