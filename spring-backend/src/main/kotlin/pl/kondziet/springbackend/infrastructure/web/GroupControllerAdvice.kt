package pl.kondziet.springbackend.infrastructure.web

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GroupControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException): ResponseEntity<Any> {
        val validationErrors = exception.bindingResult.fieldErrors.map {
            "${it.field}: ${it.defaultMessage}"
        }

        val response = mapOf(
            "status" to HttpStatus.BAD_REQUEST.value(),
            "error" to HttpStatus.BAD_REQUEST.reasonPhrase,
            "message" to "Validation failed",
            "details" to validationErrors
        )

        return ResponseEntity.badRequest().body(response)
    }
}