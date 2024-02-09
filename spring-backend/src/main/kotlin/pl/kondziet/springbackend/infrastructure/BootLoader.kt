package pl.kondziet.springbackend.infrastructure

import pl.kondziet.springbackend.domain.algorithm.SingleRegularDrawStrategy
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import pl.kondziet.springbackend.infrastructure.persistence.GroupRepository
import pl.kondziet.springbackend.domain.model.Constraint
import pl.kondziet.springbackend.domain.model.Draw
import pl.kondziet.springbackend.domain.algorithm.Graph
import pl.kondziet.springbackend.domain.model.Group
import pl.kondziet.springbackend.domain.model.User

@Component
class BootLoader(private val groupRepository: GroupRepository) : CommandLineRunner {

    override fun run(vararg args: String?) {
        groupRepository.save(
            Group(
                name = "Test group",
                description = "Test group description",
                members = listOf(
                    User("Alice"),
                    User("Bob"),
                    User("Eric"),
                    User("David"),
                    User("Carol")
                ),
                constraints = listOf(
                    Constraint(User("Alice"), listOf(User("Bob"), User("Eric"))),
                    Constraint(User("Eric"), listOf(User("Alice")))
                ),
                draws = listOf(
                    Draw(results = listOf())
                )
            )
        )

        val graph: Graph<String> = Graph.completeOf(
            nodes = listOf("Alice", "Bob", "Eric", "David", "Carol")
        )
            .excludeNeighbors(
                mapOf(
                    "Alice" to listOf("Bob", "Eric"),
                    "Eric" to listOf("Alice")
                )
            )
            .shuffleNeighbors()
            .build()

        val cycle = graph.findCycles(SingleRegularDrawStrategy(), randomStartNode = true).first()
        println(cycle)

        println(graph)
    }
}