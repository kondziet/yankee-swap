package pl.kondziet.springbackend.domain.algorithm

class MultipleRegularDrawStrategy<T> : CycleFindingStrategy<T> {
    override fun findCycles(adjacency: Map<T, List<T>>, randomStartNode: Boolean): List<List<T>> {
        TODO("Not yet implemented")
    }
}