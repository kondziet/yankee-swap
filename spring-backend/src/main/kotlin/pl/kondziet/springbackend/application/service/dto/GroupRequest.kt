package pl.kondziet.springbackend.application.service.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import pl.kondziet.springbackend.application.service.validation.DistinctElements
import pl.kondziet.springbackend.domain.model.Group
import java.time.LocalDateTime

data class GroupRequest(
    @field:NotBlank(message = "Name cannot be blank")
    @field:Size(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
    val name: String,
    @field:NotBlank(message = "Description cannot be blank")
    @field:Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    val description: String,
    val owner: UserRequest,
    @field:DistinctElements
    @field:Size(min = 1, message = "At least one user must be present")
    val members: List<UserRequest>,
    val constraints: List<ConstraintRequest>? = null,
    val allowMutualDrawing: Boolean,
    val yankeeSwapCountdownHours: Long? = null
) {
    fun toGroup() = Group(
        name = name,
        description = description,
        owner = owner.toUser(),
        members = members.map { it.toUser() },
        constraints = constraints?.map { it.toConstraint() } ?: emptyList(),
        allowMutualDrawing = allowMutualDrawing,
        yankeeSwapDate = yankeeSwapCountdownHours?.let {
            LocalDateTime.now().plusHours(it)
        },
        draws = emptyList()
    )
}
