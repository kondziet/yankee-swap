package pl.kondziet.springbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBackendApplication

fun main(args: Array<String>) {
    runApplication<SpringBackendApplication>(*args)
}
