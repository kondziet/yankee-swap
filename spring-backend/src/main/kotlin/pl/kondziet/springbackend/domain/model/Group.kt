package pl.kondziet.springbackend.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.mapping.Document
import pl.kondziet.springbackend.domain.algorithm.Graph
import java.time.LocalDateTime

@Document
data class Group(
    @Id
    val id: String? = null,
    val name: String,
    val description: String,
    val owner: User,
    val members: List<User>,
    val constraints: List<Constraint>? = null,
    val allowMutualDrawing: Boolean,
    val yankeeSwapDate: LocalDateTime? = null,
    val draws: List<Draw>,
    @Version
    val version: Long = 0
) {
    fun toGraph(): Graph<User> {
        return Graph.completeOf(members)
            .excludeNeighbors(
                constraints?.associate { it.user to it.excludedUsers } ?: emptyMap()
            )
            .shuffleNeighbors()
            .build()
    }

    fun shouldPerformYankeeSwap(): Boolean {
        return (yankeeSwapDate != null) &&
                yankeeSwapDate.isBefore(LocalDateTime.now()) &&
                draws.last().completedAt.isBefore(yankeeSwapDate)
    }
}

