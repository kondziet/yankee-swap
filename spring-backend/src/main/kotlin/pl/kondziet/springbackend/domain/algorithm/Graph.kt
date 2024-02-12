package pl.kondziet.springbackend.domain.algorithm

class Graph<T> private constructor(private val adjacency: Map<T, MutableList<T>>) {

    companion object {
        fun <T> completeOf(nodes: List<T>): Builder<T> {
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

        fun build(): Graph<T> {
            return Graph(adjacency)
        }
    }

    fun findCycles(cycleFindingStrategy: CycleFindingStrategy<T>): List<List<T>> {
        return cycleFindingStrategy.findCycles(adjacency)
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
