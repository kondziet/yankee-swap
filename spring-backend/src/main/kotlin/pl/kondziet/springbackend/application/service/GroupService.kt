package pl.kondziet.springbackend.application.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.kondziet.springbackend.application.service.dto.GroupRequest
import pl.kondziet.springbackend.application.service.dto.UserResponse
import pl.kondziet.springbackend.domain.algorithm.*
import pl.kondziet.springbackend.infrastructure.persistence.GroupRepository
import pl.kondziet.springbackend.domain.model.Draw
import pl.kondziet.springbackend.domain.model.User
import pl.kondziet.springbackend.domain.model.toCycles
import java.time.LocalDateTime

@Service
class GroupService(val groupRepository: GroupRepository, val drawService: DrawService) {

    fun createGroup(groupRequest: GroupRequest) {
        val group = groupRequest.toGroup()

        val drawStrategy = if (group.allowMutualDrawing) {
            MultipleRegularDrawStrategy<User>()
        } else {
            SingleRegularDrawStrategy<User>()
        }

        val drawResults = drawService.calculateDraws(group.toGraph(), drawStrategy)

        groupRepository.save(
            group.copy(
                draws = listOf(Draw(results = drawResults, completedAt = LocalDateTime.now()))
            )
        )
    }

    fun getMembersDraws(groupId: String): List<UserResponse> {
        val group = groupRepository.findByIdOrNull(groupId)
            ?: throw IllegalArgumentException("Group with id $groupId not found")

        if (group.shouldPerformYankeeSwap()){
        }

        return listOf()
    }

}