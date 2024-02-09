package pl.kondziet.springbackend.domain.model

data class ResultEntry(val drawer: User, val receiver: User, val seen: Boolean = false)