package pl.kondziet.springbackend.infrastructure.persistence

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import pl.kondziet.springbackend.model.User

@Repository
interface UserRepository: MongoRepository<User, String>