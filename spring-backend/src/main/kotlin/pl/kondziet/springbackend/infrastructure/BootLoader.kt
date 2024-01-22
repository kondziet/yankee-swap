package pl.kondziet.springbackend.infrastructure

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import pl.kondziet.springbackend.infrastructure.persistence.GroupRepository
import pl.kondziet.springbackend.model.Constraint
import pl.kondziet.springbackend.model.Draw
import pl.kondziet.springbackend.model.Graph
import pl.kondziet.springbackend.model.User

@Component
class BootLoader(private val groupRepository: GroupRepository) : CommandLineRunner {

    override fun run(vararg args: String?) {
//        groupRepository.save(
//            pl.kondziet.springbackend.model.Group(
//                name = "Test group",
//                description = "Test group description",
//                members = listOf(
//                    User("Alice"),
//                    User("Bob"),
//                    User("Eric"),
//                    User("David"),
//                    User("Carol")
//                ),
//                constraints = listOf(
//                    Constraint(User("Alice"), listOf(User("Bob"), User("Eric"))),
//                    Constraint(User("Bob"), listOf(User("Alice"))),
//                    Constraint(User("Eric"), listOf(User("Alice")))
//                ),
//                draws = listOf(
//                    Draw(results = listOf())
//                )
//            )
//        )
//
//        val graph: Graph<String> = Graph.createGraph(
//            listOf(
//                "Alice", "Bob", "Eric", "David", "Carol"
//            ),
//            mapOf(
//                "Alice" to listOf("Bob", "Eric"),
//                "Bob" to listOf("Alice"),
//                "Eric" to listOf("Alice")
//            )
//        )
//
//        val cycle = graph.findRandomCycle()
//        println(cycle)
//
//        println(graph)
    }
}