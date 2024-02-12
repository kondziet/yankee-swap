package pl.kondziet.springbackend.domain.algorithm

import kotlin.random.Random

class MultipleRegularDrawStrategy<T> : CycleFindingStrategy<T> {
    override fun findCycles(adjacency: Map<T, List<T>>, randomStartNode: Boolean): List<List<T>> {
        val start = if (randomStartNode) {
            Random(System.currentTimeMillis()).let { random -> adjacency.keys.toList().random(random) }
        } else {
            adjacency.keys.first()
        }
        return findSubCycles(start, start, mutableListOf(), adjacency) ?: emptyList()
    }

    private fun findSubCycles(
        start: T,
        current: T,
        subCycles: MutableList<MutableList<T>>,
        adjacency: Map<T, List<T>>
    ): List<List<T>>? {
        if (current == start) {
            subCycles.add(mutableListOf(start))
        } else {
            subCycles.last().add(current)
        }

        for (neighbor in adjacency[current] ?: emptyList()) {
            if (subCycles.flatten().toSet().size == adjacency.keys.size && subCycles.last().size >= 2 && subCycles.last()
                    .first() == neighbor
            ) {
                subCycles.last().add(neighbor)
                return subCycles
            }

            if (neighbor == start && subCycles.last().size >= 2) {
                subCycles.last().add(neighbor)
                val remainingNodes = adjacency.keys.minus(subCycles.flatten().toSet())

                if (remainingNodes.isNotEmpty()) {
                    val randomNode = remainingNodes.random()
                    val result = findSubCycles(randomNode, randomNode, subCycles, adjacency)
                    if (result != null) {
                        return result
                    }
                }

                if (subCycles.isNotEmpty() && subCycles.last().size > 1) {
                    subCycles.last().removeAt(subCycles.last().lastIndex)
                } else {
                    if (subCycles.isNotEmpty()) {
                        subCycles.removeAt(subCycles.lastIndex)
                    }
                }
            }

            if (neighbor !in subCycles.flatten().toSet()) {
                val result = findSubCycles(start, neighbor, subCycles, adjacency)
                if (result != null) {
                    return result
                }
            }
        }

        if (subCycles.isNotEmpty() && subCycles.last().size > 1) {
            subCycles.last().removeAt(subCycles.last().lastIndex)
        }

        return null
    }
}