package pl.kondziet.springbackend.domain.algorithm

class YankeeSplit<T> {

    fun split(cycle: List<T>, users: List<T>): List<List<T>> {
        require(cycle.isNotEmpty()) { "Cycle cannot be empty" }
        require(users.isNotEmpty()) { "Users cannot be empty" }
        require(cycle.size >= 3) { "Cycle cannot be shorter than 3" }
        require(cycle.size > users.size) { "Cycle cannot be shorter than users" }
        require(cycle.first() == cycle.last()) { "Cycle must be a cycle" }
        require(cycle.subList(0, cycle.size - 1).size == cycle.distinct().size) { "Cycle cannot contain duplicates" }
        require(users.distinct().size == users.size) { "Users cannot contain duplicates" }
        require(cycle.distinct().containsAll(users)) { "Cycle have to contain all users" }

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