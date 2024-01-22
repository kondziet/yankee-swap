package pl.kondziet.springbackend

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import pl.kondziet.springbackend.model.User

@Component
class BootLoader(private val userRepository: UserRepository) : CommandLineRunner {

    override fun run(vararg args: String?) {
        userRepository.save(User(name = "John", email = "asdasdasd"))
    }
}