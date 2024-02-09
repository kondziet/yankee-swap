package pl.kondziet.springbackend.domain.algorithm

class YankeeSplit<T> {

    fun split(cycle: List<T>, users: List<T>): List<List<T>> {
        require(cycle.isNotEmpty()) { "Cycle cannot be empty" }
        require(users.isNotEmpty()) { "Users cannot be empty" }
        require(cycle.size >= 3) { "Cycle cannot be shorter than 3" }
        require(cycle.size >= users.size) { "Cycle cannot be shorter than users" }

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