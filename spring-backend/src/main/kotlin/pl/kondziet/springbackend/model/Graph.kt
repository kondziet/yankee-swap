package pl.kondziet.springbackend.model

class Graph<T> private constructor(private val nodes: Map<T, MutableList<T>>) {

    companion object {
        fun <T> createGraph(nodes: List<T>, constraints: Map<T, List<T>>): Graph<T> {
            val builder = Builder<T>()

            nodes.forEach { node ->
                builder.add(node)

                val neighbors = nodes.toMutableList()
                neighbors.remove(node)
                neighbors.removeAll(constraints[node] ?: emptyList())

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

        fun build(): Graph<T> {
            return Graph(nodes)
        }
    }

    fun findRandomCycle(): List<T> {
        val randomNode = nodes.keys.random()
        return findSingularHamiltonianCycle(randomNode, randomNode, mutableListOf()) ?: emptyList()
    }

    private fun findSingularHamiltonianCycle(
        start: T,
        current: T,
        cycle: MutableList<T>
    ): List<T>? {
        cycle.add(current)

        for (neighbor in nodes[current] ?: emptyList()) {
            if (neighbor == start && cycle.size == nodes.size) {
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

        for ((node, neighbors) in nodes) {
            stringBuilder.append("$node is connected to: $neighbors\n")
        }

        return stringBuilder.toString()
    }
}
