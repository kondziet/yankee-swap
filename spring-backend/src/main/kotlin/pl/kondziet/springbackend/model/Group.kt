package pl.kondziet.springbackend.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

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
        return Graph.createGraph(
            nodes = members,
            constraints = constraints?.associate { it.user to it.excludedNeighbors } ?: emptyMap()
        )
    }
}

