package pl.kondziet.springbackend.model

class CompleteGraph<T> private constructor(private val adjacency: Map<T, MutableList<T>>) {

    companion object {
        fun <T> of(nodes: List<T>): CompleteGraph<T> {
            val builder = Builder<T>()

            nodes.forEach { node ->
                builder.add(node)

                val neighbors = nodes.toMutableList()
                neighbors.remove(node)

                builder.connect(node, neighbors)
            }

            return builder.build()
        }

        fun <T> withoutExcludedNeighbors(nodes: List<T>, excludedNeighbors: Map<T, List<T>>): CompleteGraph<T> {
            val builder = Builder<T>()

            nodes.forEach { node ->
                builder.add(node)

                val neighbors = nodes.toMutableList()
                neighbors.remove(node)
                neighbors.removeAll(excludedNeighbors[node] ?: emptyList())

                builder.connect(node, neighbors)
            }

            return builder.build()
        }
    }

    class Builder<T> {
        private val nodes: MutableMap<T, MutableList<T>> = mutableMapOf()

        fun add(node: T): Builder<T> {
            nodes[node] = mutableListOf()
            return this
        }

        fun connect(node: T, neighbors: List<T>): Builder<T> {
            nodes[node]?.addAll(neighbors)
            return this
        }

        fun build(): CompleteGraph<T> {
            return CompleteGraph(nodes)
        }
    }

    fun findRandomCycle(): List<T> {
        val randomNode = adjacency.keys.random()
        return findSingularHamiltonianCycle(randomNode, randomNode, mutableListOf()) ?: emptyList()
    }

    private fun findSingularHamiltonianCycle(
        start: T,
        current: T,
        cycle: MutableList<T>
    ): List<T>? {
        cycle.add(current)

        for (neighbor in adjacency[current] ?: emptyList()) {
            if (neighbor == start && cycle.size == adjacency.size) {
                return cycle + neighbor
            }

            if (neighbor !in cycle) {
                val result = findSingularHamiltonianCycle(start, neighbor, cycle)
                if (result != null) {
                    return result
                }
            }
        }

        cycle.removeAt(cycle.lastIndex)

        return null
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("GraphNode:\n")

        for ((node, neighbors) in adjacency) {
            stringBuilder.append("$node is connected to: $neighbors\n")
        }

        return stringBuilder.toString()
    }
}
