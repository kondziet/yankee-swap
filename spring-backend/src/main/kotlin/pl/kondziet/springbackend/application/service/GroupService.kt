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

        val drawResults = drawService.calculateRegularDraw(group)

        groupRepository.save(
            group.copy(
                draws = listOf(Draw(results = drawResults, completedAt = LocalDateTime.now()))
            )
        )
    }

    fun getMembersDraws(groupId: String): List<UserResponse> {
        val group = groupRepository.findByIdOrNull(groupId)
            ?: throw NoSuchElementException("Group with id $groupId not found")

        if (group.shouldPerformYankeeSwap()) {
            val drawResults = drawService.calculateYankeeDraw(group)
            group.copy(
                draws = group.draws?.plus(Draw(results = drawResults, completedAt = LocalDateTime.now()))
            ).let { groupRepository.save(it) }
        }

        return group.draws?.last()?.results?.filter { !it.seen }?.map { it.drawer.toUserResponse() } ?: emptyList()
    }

    fun getMemberDraw(groupId: String, userName: String): UserResponse {
        val group = groupRepository.findByIdOrNull(groupId)
            ?: throw NoSuchElementException("Group with id $groupId not found")

//        group.copy(
//            draws = group.draws?.map { draw ->
//                draw.copy(
//                    results = draw.results.map { result ->
//                        if (result.drawer.name == userName) {
//                            result.copy(seen = true)
//                        } else {
//                            result
//                        }
//                    }
//                )
//            }
//        ).let { groupRepository.save(it) }

        return TODO()
    }
}