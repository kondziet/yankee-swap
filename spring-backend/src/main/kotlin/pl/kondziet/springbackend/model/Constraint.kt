package pl.kondziet.springbackend.model

data class Constraint(val user: User, val excludedNeighbors: List<User>)
