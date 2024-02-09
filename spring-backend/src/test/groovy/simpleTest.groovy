import spock.lang.Specification

class simpleTest extends Specification{
    def "test"() {
        given:
        def age = 18

        expect:
        age >= 18
    }
}
