package pl.kondziet.springbackend.domain.algorithm

import spock.lang.Specification

class YankeeSplitTest extends Specification {

    YankeeSplit<String> yankeeSplit = new YankeeSplit<String>()

    def "should split cycles correctly"() {
        expect:
        yankeeSplit.splitCycle(cycle, users) == expected

        where:
        cycle                                               | users                                      | expected
        ["Alice", "Bob", "David", "Carol", "Mark", "Alice"] | ["Alice", "Bob"]                           | [["Bob"], ["David", "Carol", "Mark", "Alice"]]
        ["Alice", "Bob", "David", "Carol", "Mark", "Alice"] | ["Alice", "Mark"]                          | [["Alice"], ["Bob", "David", "Carol", "Mark"]]
        ["Alice", "Bob", "David", "Carol", "Mark", "Alice"] | ["Bob", "Mark"]                            | [["Alice", "Bob"], ["David", "Carol", "Mark"]]
        ["Alice", "Bob", "David", "Carol", "Mark", "Alice"] | ["Alice", "Bob", "Mark"]                   | [["Alice"], ["Bob"], ["David", "Carol", "Mark"]]
        ["Alice", "Bob", "David", "Carol", "Mark", "Alice"] | ["Alice", "Bob", "David", "Carol", "Mark"] | [["Alice"], ["Bob"], ["David"], ["Carol"], ["Mark"]]
    }

    
}
