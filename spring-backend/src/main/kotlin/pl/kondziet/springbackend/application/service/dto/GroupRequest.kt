package pl.kondziet.springbackend.application.service.dto

data class CreateGroupRequest(
    val name: String,
    val description: String,
    val users: List<UserRequest>,
    val constraints: List<ConstraintRequest>
)
