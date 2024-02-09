package pl.kondziet.springbackend.domain.algorithm

class YankeeSplit<T> {

    fun split(cycle: List<T>, users: List<T>): List<List<T>>? {
        if (users.isEmpty() || cycle.isEmpty()) {
            return null
        }

        val results = mutableListOf<MutableList<T>>()
        val indices = users.map { cycle.indexOf(it) }.sorted()

        var start = 0
        for (index in indices) {
            results.add(cycle.subList(start, index + 1).toMutableList())
            start = index + 1
        }
        results.add(cycle.subList(start, cycle.size).toMutableList())

        val last = results.last()
        if (last.size > 1) {
            last.removeAt(last.lastIndex)
            last.addAll(results.first())
            results.remove(results.first())
        } else {
            results.removeAt(results.lastIndex)
        }

        return results
    }
}