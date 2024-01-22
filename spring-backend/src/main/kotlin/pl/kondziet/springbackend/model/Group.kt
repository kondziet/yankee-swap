package pl.kondziet.springbackend.model

data class GiftGroup(val id: String? = null, val name: String, val description: String, val users: List<User>)
