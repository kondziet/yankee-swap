package pl.kondziet.springbackend.application.service

import org.springframework.stereotype.Service
import pl.kondziet.springbackend.domain.algorithm.MultipleRegularDrawStrategy
import pl.kondziet.springbackend.domain.algorithm.SingleRegularDrawStrategy
import pl.kondziet.springbackend.domain.algorithm.SingleYankeeDrawStrategy
import pl.kondziet.springbackend.domain.algorithm.YankeeSplit
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

        return listOf()
    }
}