package pl.kondziet.springbackend.domain.model

data class ResultEntry(val drawer: User, val drawee: User, val seen: Boolean = false)