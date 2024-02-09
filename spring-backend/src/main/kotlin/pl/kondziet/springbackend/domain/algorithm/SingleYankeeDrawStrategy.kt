package pl.kondziet.springbackend.domain.algorithm

import java.util.Collections

class SingleYankeeDrawStrategy<T>(private val cycles: List<List<T>>) : CycleFindingStrategy<T> {
    override fun findCycles(adjacency: Map<T, List<T>>, randomStartNode: Boolean): List<List<T>> {
        val copiedAdjacency = HashMap<T, List<T>>(adjacency)

        cycles.forEach { cycle ->
            for (i in 0 until cycle.size - 1) {
                val current = cycle[i]
                val next = cycle[i + 1]
                copiedAdjacency[current] = listOf(next)
            }
        }

        println(cycles)

        val previousDraws = findPreviousDraws(cycles)
        println(previousDraws)

        for ((index, cycle) in cycles.withIndex()) {
            val remainingCycles = cycles.filterIndexed { i, _ -> i != index }

            val drawer = cycle.last()
            val neighbors = remainingCycles.map { it.first() }

            val neighborsOfDrawer = copiedAdjacency[drawer]?.intersect(neighbors.toSet())?.toList() ?: emptyList()

            if (randomStartNode) {
                val shuffled = neighborsOfDrawer.shuffled()
                Collections.swap(shuffled, shuffled.indexOf(previousDraws[drawer]), shuffled.lastIndex)
                copiedAdjacency[drawer] = shuffled
            } else {
                copiedAdjacency[drawer] = neighborsOfDrawer
            }
        }

        return SingleRegularDrawStrategy<T>().findCycles(copiedAdjacency, randomStartNode)
    }

    private fun findPreviousDraws(cycles: List<List<T>>): Map<T, T> {
        val adjacentCycles = cycles.zipWithNext() + listOf(cycles.last() to cycles.first())
        return adjacentCycles.associate { (current, next) -> current.last() to next.first() }
    }
}