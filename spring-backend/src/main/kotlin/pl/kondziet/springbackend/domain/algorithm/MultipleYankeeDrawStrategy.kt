package pl.kondziet.springbackend.domain.algorithm

class MultipleYankeeDrawStrategy<T>(private val cycles: List<List<T>>) : CycleFindingStrategy<T> {
    override fun findCycles(adjacency: Map<T, List<T>>, randomStartNode: Boolean): List<List<T>> {
        TODO("Not yet implemented")
    }
}