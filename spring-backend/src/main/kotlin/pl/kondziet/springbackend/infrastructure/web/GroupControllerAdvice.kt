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

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(exception: NoSuchElementException): ResponseEntity<Any> {
        val response = mapOf(
            "status" to HttpStatus.NOT_FOUND.value(),
            "error" to HttpStatus.NOT_FOUND.reasonPhrase,
            "message" to "Resource not found",
            "details" to exception.message
        )

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response)
    }

    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalStateException(exception: IllegalStateException): ResponseEntity<Any> {
        val response = mapOf(
            "status" to HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "error" to HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
            "message" to "Internal server error",
            "details" to exception.message
        )

        return ResponseEntity.badRequest().body(response)
    }
}