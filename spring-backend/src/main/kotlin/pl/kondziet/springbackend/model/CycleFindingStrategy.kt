interface CycleFindingStrategy<T> {
    fun findCycles(adjacency: Map<T, List<T>>, randomStartNode: Boolean): List<List<T>>
}