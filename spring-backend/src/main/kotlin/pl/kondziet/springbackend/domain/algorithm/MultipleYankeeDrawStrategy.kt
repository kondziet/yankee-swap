package pl.kondziet.springbackend.domain.algorithm

import java.util.*
import kotlin.collections.HashMap

class MultipleYankeeDrawStrategy<T>(private val splitSubCycles: List<List<List<T>>>) : CycleFindingStrategy<T> {
    override fun findCycles(adjacency: Map<T, List<T>>): List<List<T>> {
        val copiedAdjacency = HashMap<T, List<T>>(adjacency)

        splitSubCycles.forEach { splitSubCycle ->
            splitSubCycle.forEach { split ->
                for (i in 0 until split.size - 1) {
                    val current = split[i]
                    val next = split[i + 1]
                    copiedAdjacency[current] = listOf(next)
                }
            }
        }

        val previousDraws = findPreviousDraws(splitSubCycles)

        splitSubCycles.forEach { splitSubCycle ->
            splitSubCycle.forEachIndexed { index, split ->
                val remainingCycles = if (splitSubCycle.size == 1) {
                    splitSubCycle
                } else {
                    splitSubCycle.filterIndexed { i, _ -> i != index }
                }

                val drawer = split.last()
                val neighbors = remainingCycles.map { it.first() }

                val neighborsOfDrawer = copiedAdjacency[drawer]?.intersect(neighbors.toSet())?.toList() ?: emptyList()

                val shuffled = neighborsOfDrawer.shuffled()
                Collections.swap(shuffled, shuffled.indexOf(previousDraws[drawer]), shuffled.lastIndex)
                copiedAdjacency[drawer] = shuffled
                copiedAdjacency[drawer] = neighborsOfDrawer
            }
        }

        println(copiedAdjacency)

        return MultipleRegularDrawStrategy<T>().findCycles(copiedAdjacency)
    }

    private fun findPreviousDraws(splitSubCycles: List<List<List<T>>>): Map<T, T> {
        val result = mutableListOf<Pair<List<T>, List<T>>>()
        splitSubCycles.forEach { subCycle ->
            val adjacentCycles = subCycle.zipWithNext() + listOf(subCycle.last() to subCycle.first())
            result.addAll(adjacentCycles)
        }

        return result.associate { (current, next) -> current.last() to next.first() }
    }
}