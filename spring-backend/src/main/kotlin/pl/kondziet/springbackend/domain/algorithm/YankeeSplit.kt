package pl.kondziet.springbackend.domain.algorithm

class YankeeSplit<T> {

    fun splitCycle(cycle: List<T>, users: List<T>): List<List<T>>? {
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

    fun splitSubCycles(subCycles: List<List<T>>, users: List<T>): List<List<List<T>>>? {
        if (users.isEmpty() || subCycles.isEmpty()) {
            return null
        }

        val result = mutableListOf<List<List<T>>>()
        subCycles.forEach { subCycle ->
            val usersInSubCycle = users.intersect(subCycle.toSet()).toList()
            if (usersInSubCycle.isNotEmpty()) {
                result.add(splitCycle(subCycle, usersInSubCycle) ?: emptyList())
            } else {
                result.add(listOf(subCycle - subCycle.first()))
            }
        }

        return result
    }
}