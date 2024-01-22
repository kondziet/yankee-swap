package pl.kondziet.springbackend.application.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.kondziet.springbackend.application.service.dto.GroupRequest
import pl.kondziet.springbackend.infrastructure.persistence.GroupRepository
import pl.kondziet.springbackend.model.Draw
import pl.kondziet.springbackend.model.ResultEntry
import pl.kondziet.springbackend.model.User

@Service
class GroupService(val groupRepository: GroupRepository) {

    fun createGroup(groupRequest: GroupRequest) {
        val group = groupRequest.toGroup()
        val savedGroup = groupRepository.save(group)

        val firstDraw = calculateDraw(savedGroup.id!!)
        val results = generateResultEntries(firstDraw)

        savedGroup
            .copy(draws = listOf(Draw(results)))
            .let { groupRepository.save(it) }
    }

    fun calculateDraw(groupId: String): List<User> {
        val group = groupRepository.findByIdOrNull(groupId) ?: throw IllegalArgumentException("Group not found")
        val graph = group.toGraph()
        return graph.findRandomCycle()
    }

    private fun generateResultEntries(firstDraw: List<User>): List<ResultEntry> {
        val firstDrawIterator = firstDraw.iterator()
        val secondDrawIterator = firstDraw.iterator()
        secondDrawIterator.next()

        return generateSequence {
            if (secondDrawIterator.hasNext()) {
                ResultEntry(firstDrawIterator.next(), secondDrawIterator.next())
            } else {
                null
            }
        }.toList()
    }
}