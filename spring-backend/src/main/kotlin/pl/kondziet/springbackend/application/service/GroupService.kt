package pl.kondziet.springbackend.application.service

import pl.kondziet.springbackend.domain.algorithm.SingleRegularDrawStrategy
import org.springframework.stereotype.Service
import pl.kondziet.springbackend.application.service.dto.GroupRequest
import pl.kondziet.springbackend.domain.algorithm.MultipleRegularDrawStrategy
import pl.kondziet.springbackend.infrastructure.persistence.GroupRepository
import pl.kondziet.springbackend.domain.model.Draw
import pl.kondziet.springbackend.domain.model.User

@Service
class GroupService(val groupRepository: GroupRepository, val drawService: DrawService) {

    fun createGroup(groupRequest: GroupRequest) {
        val savedGroup = groupRepository.save(groupRequest.toGroup())

        val drawStrategy = if (savedGroup.allowMutualDrawing) {
            MultipleRegularDrawStrategy<User>()
        } else {
            SingleRegularDrawStrategy<User>()
        }

        val drawResults = drawService.calculateDraws(savedGroup.toGraph(), drawStrategy)

        savedGroup
            .copy(draws = listOf(Draw(drawResults)))
            .let { groupRepository.save(it) }
    }

}