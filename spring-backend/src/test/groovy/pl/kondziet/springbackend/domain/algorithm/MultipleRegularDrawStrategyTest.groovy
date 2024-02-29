package pl.kondziet.springbackend.domain.algorithm

import spock.lang.Specification

class MultipleRegularDrawStrategyTest extends Specification {

    def "Should find multiple subcycles in a graph"() {
        given:
        def strategy = new MultipleRegularDrawStrategy<Integer>()
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
        cycles.size() > 1

        and:
        cycles.every { it.size() >= 2 }
        cycles.flatten().toSet().size() == adjacency.keySet().size()
    }
}
