package pl.kondziet.springbackend.application.service

import org.springframework.stereotype.Service
import pl.kondziet.springbackend.domain.algorithm.CycleFindingStrategy
import pl.kondziet.springbackend.domain.algorithm.Graph
import pl.kondziet.springbackend.domain.model.ResultEntry
import pl.kondziet.springbackend.domain.model.User
import pl.kondziet.springbackend.domain.model.toResultEntries

@Service
class DrawService {

    fun calculateDraws(graph: Graph<User>, drawStrategy: CycleFindingStrategy<User>): List<ResultEntry> {
        val cycles = graph.findCycles(drawStrategy, true)
        if (cycles.isEmpty()) {
            throw IllegalStateException("No cycles found in the graph")
        }

        return cycles.toResultEntries()
    }
}