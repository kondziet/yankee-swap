package pl.kondziet.springbackend.domain.model

import java.time.LocalDateTime

data class Draw(val results: List<ResultEntry>, val completedAt: LocalDateTime)

fun List<ResultEntry>.toCycles(): List<List<User>> {
    val cycles = mutableListOf<List<User>>()
    var currentCycle = mutableListOf<User>()

    forEach { entry ->
        currentCycle.add(entry.drawer)
        if (entry.drawee == currentCycle.first()) {
            cycles.add(currentCycle)
            currentCycle = mutableListOf()
        }
    }

    return cycles
}

fun List<List<User>>.toResultEntries(): List<ResultEntry> {
    return if (size == 1) {
        val cycle = first()
        cycle.zipWithNext { drawer, drawee ->
            ResultEntry(drawer, drawee)
        }
    } else {
        flatMap { cycle ->
            cycle.zipWithNext { drawer, drawee ->
                ResultEntry(drawer, drawee)
            }
        }
    }
}