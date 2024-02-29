![Yankee_Swap🎅🏻](https://github.com/kondziet/yankee-swap/assets/86203980/78c4306b-5f1b-4644-9cb6-3acb44d76149)

## About project

Yankee Swap is a web application designed for creating user groups and performing random draws among them. Each participant can draw another group member using the "Secret Santa" type algorithm. The group creator can set restrictions on drawing user pairs. Users also have the option to choose "Yankee Swap" and participate in a re-draw after a designated time period. The algorithm, considering the number of participants, will determine new pairs among those participating in the "Yankee Draw". This application is not only effective for organizing traditional holiday lotteries but also for various situations requiring random user pairing. Yankee Swap serves not only as a drawing tool but also as a means to encourage engaging interactions within groups.

## Technologies

* 🌐 Backend: Kotlin, Spring Boot
* ✨Frontend: React, Tailwind CSS
* 💾 Database: MongoDB
* 🧪 Testing: Spock

## Algorithm

I have chosen a graph as the data representation, where nodes correspond to individual users, and edges represent the possibility of drawing a specific pair. Assuming that each user can draw themselves, the graph takes the form of a complete graph.
![complete graph](https://github.com/kondziet/yankee-swap/assets/86203980/8be8b7e4-888d-43ba-87b7-48f69c2cca83)

To find user pairs, it is sufficient to identify a Hamiltonian cycle in this graph. For this purpose, a slightly modified DFS algorithm can be employed. A sample solution assumes that each user draws another, thus forming a Hamiltonian cycle.
![hamiltonian cycle](https://github.com/kondziet/yankee-swap/assets/86203980/1d311c0e-b9ef-4a18-b56e-df20b85e272c)

However, what happens when we introduce constraints on the drawn pairs? For example, if there is no edge between the user nodes Bob and Andy, as well as Alice and John?
![constraints](https://github.com/kondziet/yankee-swap/assets/86203980/e5aca1fc-74fd-4090-b49d-7f95db389069)

The algorithm's operation remains fundamentally the same, as each node in the graph must have at least one edge, and the graph is undirected, allowing for a solution to be found.

Problems arise, however, when considering a directed graph with specific drawing constraints, such as one-sided constraints. For example, Alice can draw Bob, but Bob cannot draw Alice. In such a situation, the fact that each node has at least one edge is not sufficient. An illustrative graph depicts a scenario where finding a Hamiltonian cycle becomes impossible.
![sub cycles](https://github.com/kondziet/yankee-swap/assets/86203980/09f10d34-25ef-4a80-93ba-c46975f49bcb)

In the case of a directed graph with one-sided constraints, it is necessary to create an algorithm that explores the space of potential solutions, considering not only single cycles but also the possibility of multiple subcycles. The presented solution is correct, but unlike the previous algorithm, it allows for situations where two individuals have drawn each other.


## How to install
