package pl.kondziet.springbackend

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class SpringBackendApplicationTest extends Specification {

    def "context loads"() {
        expect:
        true
    }
}
