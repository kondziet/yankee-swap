package pl.kondziet.springbackend.infrastructure.web

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.kondziet.springbackend.application.service.GroupService
import pl.kondziet.springbackend.application.service.dto.GroupRequest

@RestController
@RequestMapping("/api/group")
class GroupController(val groupService: GroupService) {

    @PostMapping
    fun createGroup(@RequestBody groupRequest: GroupRequest): ResponseEntity<Any> {
        groupService.createGroup(groupRequest)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}