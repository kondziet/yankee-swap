package pl.kondziet.springbackend.domain.algorithm

import kotlin.random.Random

class SingleRegularDrawStrategy<T> : CycleFindingStrategy<T> {
    override fun findCycles(adjacency: Map<T, List<T>>, randomStartNode: Boolean): List<List<T>> {
        val start = if (randomStartNode) {
            Random(System.currentTimeMillis()).let { random -> adjacency.keys.toList().random(random) }
        } else {
            adjacency.keys.first()
        }
        return listOf(findSingularHamiltonianCycle(start, start, mutableListOf(), adjacency) ?: emptyList())
    }

    private fun findSingularHamiltonianCycle(
        start: T,
        current: T,
        cycle: MutableList<T>,
        adjacency: Map<T, List<T>>
    ): List<T>? {
        cycle.add(current)

        for (neighbor in adjacency[current] ?: emptyList()) {
            if (neighbor == start && cycle.size == adjacency.size) {
                return cycle + neighbor
            }

            if (neighbor !in cycle) {
                val result = findSingularHamiltonianCycle(start, neighbor, cycle, adjacency)
                if (result != null) {
                    return result
                }
            }
        }

        cycle.removeAt(cycle.lastIndex)

        return null
    }
}