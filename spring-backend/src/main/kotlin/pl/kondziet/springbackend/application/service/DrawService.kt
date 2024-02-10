package pl.kondziet.springbackend.application.service

import org.springframework.stereotype.Service
import pl.kondziet.springbackend.domain.algorithm.CycleFindingStrategy
import pl.kondziet.springbackend.domain.algorithm.Graph
import pl.kondziet.springbackend.domain.model.ResultEntry
import pl.kondziet.springbackend.domain.model.User

@Service
class DrawService {

    fun calculateDraws(graph: Graph<User>, drawStrategy: CycleFindingStrategy<User>): List<ResultEntry> {
        val cycles = graph.findCycles(drawStrategy, true)

        return generateResultEntries(cycles)
    }

    private fun generateResultEntries(drawResults: List<List<User>>): List<ResultEntry> {
        return if (drawResults.size == 1) {
            val cycle = drawResults.first()
            cycle.zipWithNext { drawer, drawee ->
                ResultEntry(drawer, drawee)
            }
        } else {
            drawResults.flatMap { cycle ->
                cycle.zipWithNext { drawer, drawee ->
                    ResultEntry(drawer, drawee)
                }
            }
        }
    }
}