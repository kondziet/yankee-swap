package pl.kondziet.springbackend.application.service

import org.springframework.stereotype.Service
import pl.kondziet.springbackend.domain.algorithm.*
import pl.kondziet.springbackend.domain.model.*

@Service
class DrawService {

    fun calculateRegularDraw(group: Group): List<ResultEntry> {
        val drawStrategy = if (group.allowMutualDrawing) {
            MultipleRegularDrawStrategy<User>()
        } else {
            SingleRegularDrawStrategy<User>()
        }

        val cycles = group.toGraph().findCycles(drawStrategy)
        if (cycles.first().isEmpty() || cycles.isEmpty()) {
            throw IllegalStateException("No cycles found in the graph")
        }

        return cycles.toResultEntries()
    }

    fun calculateYankeeDraw(group: Group): List<ResultEntry> {
        val yankeeSwapParticipants =
            group.draws.last().results.filter { it.yankeeSwapParticipation }.map { it.drawer }

        val drawStrategy = if (group.allowMutualDrawing) {
            val splitSubCycles = YankeeSplit<User>().splitSubCycles(
                group.draws.last().results.toCycles(),
                yankeeSwapParticipants
            )
            MultipleYankeeDrawStrategy<User>(splitSubCycles ?: emptyList())
        } else {
            val splitCycle = YankeeSplit<User>().splitCycle(
                group.draws.last().results.toCycles().first(),
                yankeeSwapParticipants
            )
            SingleYankeeDrawStrategy(splitCycle ?: emptyList())
        }

        val cycles = group.toGraph().findCycles(drawStrategy)
        if (cycles.first().isEmpty() || cycles.isEmpty()) {
            throw IllegalStateException("No cycles found in the graph")
        }

        return cycles.toResultEntries()
    }
}