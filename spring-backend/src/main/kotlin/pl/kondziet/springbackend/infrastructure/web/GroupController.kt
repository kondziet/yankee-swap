package pl.kondziet.springbackend.infrastructure.web

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.kondziet.springbackend.application.service.GroupService
import pl.kondziet.springbackend.application.service.dto.GroupRequest

@RestController
@RequestMapping("/api/group")
class GroupController(val groupService: GroupService) {

    @PostMapping
    fun createGroup(@Valid @RequestBody groupRequest: GroupRequest): ResponseEntity<Any> {
        val createdGroupId = groupService.createGroup(groupRequest)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGroupId)
    }

    @GetMapping("/{groupId}")
    fun getMembersDraws(@PathVariable groupId: String): ResponseEntity<Any> {
        return ResponseEntity.ok(groupService.getMembersDraws(groupId))
    }

    @GetMapping("/{groupId}/{userName}")
    fun getMemberDraw(@PathVariable groupId: String, @PathVariable userName: String): ResponseEntity<Any> {
        return ResponseEntity.ok(groupService.getMemberDraw(groupId, userName))
    }

    @PostMapping("/{groupId}/{userName}")
    fun toggleYankeeSwapParticipation(@PathVariable groupId: String, @PathVariable userName: String): ResponseEntity<Any> {
        return ResponseEntity.ok(groupService.toggleYankeeSwapParticipation(groupId, userName))
    }
}