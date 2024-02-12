package pl.kondziet.springbackend.domain.algorithm

interface CycleFindingStrategy<T> {
    fun findCycles(adjacency: Map<T, List<T>>): List<List<T>>
}