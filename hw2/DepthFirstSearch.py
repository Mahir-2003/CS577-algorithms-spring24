# def __main__():
#     instances = int(input())

#     for i in range(instances):
#         nodeCount = int(input())
#         graph = {}
#         order = []
#         visited = []

#         for j in range(nodeCount):
#             node = input()
#             list = node.split()
#             current_node = list[0]
#             order.append(current_node)

#             #adding current node to graph it hasn't been added yet
#             if current_node not in graph:
#                 graph[current_node] = []

#             #for the case where there are multiple nodes
#             for k in range(1, len(list)):
#                 #if the node is not in the graph, add it
#                 if list[k] not in graph:
#                     graph[list[k]] = []
#                 #if the current node is in the graph, add the node to the list, otherwise
#                 if current_node in graph:
#                     graph[current_node].append(list[k])
        
#         dfs(graph, order[0], visited)

#         print(visited)

# # Depth First Search Algorithm                    
# def dfs (graph, start, visited):
#     stack = [] #stack to keep track of nodes

#     stack.append(start) #add the start node to the stack

#     while(stack != []):
#         current = stack.pop()

#         #if the current node has not been visited, add it to the visited list
#         if current not in visited:
#             visited.append(current)

#         #add the neighbors of the current node to the stack, in reverse
#         for neighbor in reversed(graph[current]):
#             if neighbor not in visited:
#                 print(neighbor)
#                 stack.append(neighbor)


#     #add any disconnected nodes that were not visited
#     for node in graph:
#         if node not in visited:
#             visited.append(node)
        

# # Description: running the main function
# if __name__ == "__main__":
#     __main__()

# dfs.py
from collections import defaultdict

def dfs(graph, start):
    visited = set()
    stack = [start]
    result = []

    while stack:
        node = stack.pop()
        if node not in visited:
            visited.add(node)
            result.append(node)
            stack.extend(reversed(graph[node]))  # Maintain the order specified in the adjacency list

    return result

if __name__ == "__main__":
    t = int(input().strip())
    for _ in range(t):
        n = int(input().strip())
        graph = defaultdict(list)
        nodes_order = []  # Maintain the order of nodes encountered

        for _ in range(n):
            line = input().strip().split()
            node = line[0]
            if node not in graph:
                nodes_order.append(node)
            neighbors = line[1:]
            for neighbor in neighbors:
                if neighbor not in graph:
                    nodes_order.append(neighbor)
            graph[node].extend(neighbors)

        # Ensure consistent tie-breaking by sorting neighbors based on input order
        for node, neighbors in graph.items():
            graph[node] = sorted(neighbors, key=lambda x: nodes_order.index(x))

        visited = set()
        result = []

        # Iterate over all nodes and perform DFS from each unvisited node
        for node in nodes_order:
            if node not in visited:
                traversal_result = dfs(graph, node)
                result.extend(traversal_result)
                visited.update(traversal_result)

        print(' '.join(result))


