package pl.kondziet.springbackend.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import pl.kondziet.springbackend.domain.algorithm.Graph

@Document
data class Group(
    @Id
    val id: String? = null,
    val name: String,
    val description: String,
    val members: List<User>,
    val constraints: List<Constraint>? = null,
    val draws: List<Draw>? = null
) {
    fun toGraph(): Graph<User> {
        return Graph.completeOf(members)
            .excludeNeighbors(
                constraints?.associate { it.user to it.excludedUsers } ?: emptyMap()
            )
            .shuffleNeighbors()
            .build()
    }
}

