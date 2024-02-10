package pl.kondziet.springbackend.application.service

import pl.kondziet.springbackend.domain.algorithm.SingleRegularDrawStrategy
import org.springframework.stereotype.Service
import pl.kondziet.springbackend.application.service.dto.GroupRequest
import pl.kondziet.springbackend.infrastructure.persistence.GroupRepository
import pl.kondziet.springbackend.domain.model.Draw

@Service
class GroupService(val groupRepository: GroupRepository, val drawService: DrawService) {

    fun createGroup(groupRequest: GroupRequest) {
        val savedGroup = groupRepository.save(groupRequest.toGroup())

        val drawResults = drawService.calculateDraws(savedGroup.toGraph(), SingleRegularDrawStrategy())

        savedGroup
            .copy(draws = listOf(Draw(drawResults)))
            .let { groupRepository.save(it) }
    }

}