package pl.kondziet.springbackend.model

class CompleteGraph<T> private constructor(private val adjacency: Map<T, MutableList<T>>) {

    companion object {
        fun <T> of(nodes: List<T>): Builder<T> {
            return Builder(nodes)
        }
    }

    class Builder<T>(private val nodes: List<T>) {
        private val adjacency = mutableMapOf<T, MutableList<T>>()
        init {
            nodes.forEach {
                adjacency[it] = (nodes - it).toMutableList()
            }
        }

        fun excludeNeighbors(constraints: Map<T, List<T>>): Builder<T> {
            constraints.forEach { (node, neighborsToExclude) ->
                adjacency[node]?.removeAll(neighborsToExclude)
            }
            return this
        }

        fun shuffleNeighbors(): Builder<T> {
            adjacency.forEach { (_, neighbors) ->
                neighbors.shuffle()
            }
            return this
        }

        fun build(): CompleteGraph<T> {
            return CompleteGraph(adjacency)
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
