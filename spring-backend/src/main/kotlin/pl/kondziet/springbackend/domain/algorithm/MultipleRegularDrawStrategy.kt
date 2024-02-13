package pl.kondziet.springbackend.domain.algorithm

import kotlin.random.Random

class MultipleRegularDrawStrategy<T> : CycleFindingStrategy<T> {
    override fun findCycles(adjacency: Map<T, List<T>>): List<List<T>> {
        val keys = adjacency.keys.toMutableList()
        var result: List<List<T>> = mutableListOf()

        while (keys.isNotEmpty() && result.isEmpty()) {
            val startNode = Random(System.currentTimeMillis()).let { random -> keys.random(random) }
            keys.remove(startNode)
            result = findSubCycles(startNode, startNode, mutableListOf(), adjacency) ?: emptyList()
        }

        return result
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
                    val randomNode = Random(System.currentTimeMillis()).let { random -> remainingNodes.random(random) }
                    val result = findSubCycles(randomNode, randomNode, subCycles, adjacency)
                    if (result != null) {
                        return result
                    }
                } else {
                    if (subCycles.isNotEmpty()) {
                        subCycles.removeAt(subCycles.lastIndex)
                        subCycles.add(mutableListOf(start))
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