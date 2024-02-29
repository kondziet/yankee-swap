package pl.kondziet.springbackend.domain.algorithm

import spock.lang.Specification

class SingleRegularDrawStrategyTest extends Specification {

    def "Should find a singular Hamiltonian cycle in a graph"() {
        given:
        def strategy = new SingleRegularDrawStrategy<Integer>()
        def adjacency = [
                1: [2, 3],
                2: [1, 3],
                3: [1, 2]
        ]

        when:
        def cycles = strategy.findCycles(adjacency)

        then:
        cycles.size() == 1
        cycles.first().size() == 4

        and:
        cycles.first().containsAll([1, 2, 3])
    }

    def "Should find a singular Hamiltonian cycle in a graph with a different structure"() {
        given:
        def strategy = new SingleRegularDrawStrategy<Integer>()
        def adjacency = [
                1: [2, 3],
                2: [1, 3, 4],
                3: [1, 2, 4],
                4: [2, 3]
        ]

        when:
        def cycles = strategy.findCycles(adjacency)

        then:
        cycles.size() == 1
        cycles.first().size() == 5

        and:
        cycles.first().containsAll([1, 2, 3, 4])
    }

    def "Should return empty list when no Hamiltonian cycle is found"() {
        given:
        def strategy = new SingleRegularDrawStrategy<Integer>()
        def adjacency = [
                1: [5, 2],
                2: [3],
                3: [4, 2],
                4: [1],
                5: [4]
        ]

        when:
        def cycles = strategy.findCycles(adjacency)

        then:
        cycles.first().isEmpty()
    }


}
