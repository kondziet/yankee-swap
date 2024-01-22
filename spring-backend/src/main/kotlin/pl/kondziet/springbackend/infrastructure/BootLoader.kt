package pl.kondziet.springbackend.infrastructure

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import pl.kondziet.springbackend.infrastructure.persistence.UserRepository
import pl.kondziet.springbackend.model.Graph
import pl.kondziet.springbackend.model.User

@Component
class BootLoader(private val userRepository: UserRepository) : CommandLineRunner {

    override fun run(vararg args: String?) {
        userRepository.save(User(name = "John", email = "asdasdasd"))

        val graph: Graph<String> = Graph.createGraph(
            listOf(
                "Alice", "Bob", "Eric", "David", "Carol"),
            mapOf(
                "Alice" to listOf("Bob", "Eric"),
                "Bob" to listOf("Alice"),
                "Eric" to listOf("Alice")
            )
        )

        val cycle = graph.findRandomCycle()
        println(cycle)

        println(graph)
    }
}