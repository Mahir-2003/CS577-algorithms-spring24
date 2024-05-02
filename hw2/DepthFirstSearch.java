import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.HashMap;

public class DepthFirstSearch {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in); 
        int instances = scnr.nextInt(); //getting number of instances from user
        scnr.nextLine();

        for (int i = 0; i < instances; i++){
            int nodeCount = scnr.nextInt(); //getting number of nodes for current instance
            scnr.nextLine();

            //HashMap of lists acting as the adjacency list
            HashMap<String, List<String>> adjList = new HashMap<>();
            List<String> order = new ArrayList<>(); //maintaining input order of nodes

            for (int j = 0; j < nodeCount; j++){
                String nodes = scnr.nextLine().trim();
                String[] nodeArray = nodes.split(" ");
                
                //creating new TreeMap for this current node
                String currentNode = nodeArray[0];
                if(!order.contains(currentNode)) order.add(currentNode);
                List<String> adjNodes = new ArrayList<>();

                //adding adjacent nodes to the list
                for (int k = 1; k < nodeArray.length; k++){
                    adjNodes.add(nodeArray[k]);
                    order.add(nodeArray[k]);
                }

                adjList.put(currentNode, adjNodes);
            }
            System.out.println("Adjacency List: " + adjList);
            //initializing the graph
            Graph graph = new Graph(adjList);
            graph.DepthFirstSearch(order.get(0));

            //print da output
            String output = "";
            for (String node : graph.output) {
                output += node + " ";
            }
            System.out.println(output.trim());
        }
        scnr.close();
    }

    static class Graph {
        HashMap<String, List<String>> adjList;
        Stack<String> stack = new Stack<>();
        LinkedList<String> output = new LinkedList<>();
        

        Graph(HashMap<String, List<String>> adjList){
            this.adjList = adjList;
        }

        void DepthFirstSearch(String startNode){
            stack.push(startNode); //pushing the starting node to the stack

            while (!stack.isEmpty()){
                String u = stack.pop();
                
                //checking if the current node has been visited
                if(!output.contains(u)){
                    output.add(u);
                }

                //Exploring adjacent nodes
                // for (String v: adjList.get(u)){
                //     System.out.println(("String v is currently: " + v));
                //     if(!output.contains(v)){
                //         System.out.println(("String v is being pushed: " + v));
                //         stack.push(v);
                //     }
                // }

                // EXPLORING ADJ NODES IN REVERSE ORDER TO MAINTAIN TIEBREAKER ORDER
                List<String> adjacentNodes = adjList.get(u);
                
                for (int i = adjacentNodes.size() - 1; i >= 0; i--) {
                    String v = adjacentNodes.get(i);
                    if (!output.contains(v)) {
                        stack.push(v);
                    }
                }
                    
            }
            
            //Adding any disconnected nodes
            for (String key: adjList.keySet()) {
                if(!output.contains(key)){
                    output.add(key);
                }
            }
            
        }

    }

}
