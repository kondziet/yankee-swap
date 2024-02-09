package pl.kondziet.springbackend.domain.algorithm

import spock.lang.Specification

class YankeeSplitTest extends Specification {

    YankeeSplit<String> yankeeSplit = new YankeeSplit<String>()

    def "should split cycles correctly"() {
        expect:
        yankeeSplit.split(cycle, users) == expected

        where:
        cycle                                               | users                                      | expected
        ["Alice", "Bob", "David", "Carol", "Mark", "Alice"] | ["Alice", "Bob"]                           | [["Bob"], ["David", "Carol", "Mark", "Alice"]]
        ["Alice", "Bob", "David", "Carol", "Mark", "Alice"] | ["Alice", "Mark"]                          | [["Alice"], ["Bob", "David", "Carol", "Mark"]]
        ["Alice", "Bob", "David", "Carol", "Mark", "Alice"] | ["Bob", "Mark"]                            | [["Alice", "Bob"], ["David", "Carol", "Mark"]]
        ["Alice", "Bob", "David", "Carol", "Mark", "Alice"] | ["Alice", "Bob", "Mark"]                   | [["Alice"], ["Bob"], ["David", "Carol", "Mark"]]
        ["Alice", "Bob", "David", "Carol", "Mark", "Alice"] | ["Alice", "Bob", "David", "Carol", "Mark"] | [["Alice"], ["Bob"], ["David"], ["Carol"], ["Mark"]]
    }

    def "should throw exception"() {
        when:
        yankeeSplit.split(cycle, users)

        then:
        def exception = thrown(IllegalArgumentException)
        exception.message == expectedExceptionMessage

        where:
        cycle                                               | users                              | expectedExceptionMessage
        ["Alice", "Bob", "David", "Carol", "Mark", "Alice"] | []                                 | "Users cannot be empty"
        []                                                  | ["Alice", "Bob", "David", "Carol"] | "Cycle cannot be empty"
        ["Alice", "Alice"]                                  | ["Alice"]                          | "Cycle cannot be shorter than 3"
        ["Alice", "Bob", "Alice"]                           | ["Alice", "Bob", "Mark"]           | "Cycle cannot be shorter than users"
        ["Alice", "Bob", "David", "Carol", "Mark"]          | ["Alice", "David"]                 | "Cycle must be a cycle"
        ["Alice", "Bob", "David", "Bob", "Alice"]           | ["Alice", "David"]                 | "Cycle cannot contain duplicates"
        ["Alice", "Bob", "Alice"]                           | ["Alice", "David"]                 | "Cycle have to contain all users"
        ["Alice", "Bob", "David", "Alice"]                  | ["Alice", "David", "Alice"]        | "Users cannot contain duplicates"

    }
}
