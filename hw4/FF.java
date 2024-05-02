import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FF {

    public static int FFfaults(int cacheSize, ArrayList<Integer> requestList,
            Hashtable<Integer, LinkedList<Integer>> hashTable,
            ArrayList<Integer> output) {

        int pageFaults = 0;

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(Collections.reverseOrder()); // contains indexes of next request in descending order
        ArrayList<Integer> cache = new ArrayList<Integer>(); // contains cached requests

        for (int i = 0; i < requestList.size(); i++) {
            int currentRequest = requestList.get(i);
            if (cache.contains(currentRequest)) {

                hashTable.get(currentRequest).removeFirst();

                try {
                    priorityQueue.add(hashTable.get(currentRequest).getFirst());
                    priorityQueue.remove(i);
                } catch (NoSuchElementException e) {
                    priorityQueue.remove(i);
                    cache.remove(requestList.get(i));
                }

                continue;
            } else {
                if (cache.size() < cacheSize) {
                    hashTable.get(currentRequest).removeFirst();
                    try {
                        priorityQueue.add(hashTable.get(currentRequest).getFirst());
                        cache.add(currentRequest);
                    } catch (NoSuchElementException e) {

                    }

                } else {
                    int removalIndex = priorityQueue.poll();
                    cache.remove(requestList.get(removalIndex));

                    hashTable.get(currentRequest).removeFirst();
                    try {
                        priorityQueue.add(hashTable.get(currentRequest).getFirst());
                        cache.add(currentRequest);
                    } catch (NoSuchElementException e) {

                    }

                }
                pageFaults++;
            }
        }
        return pageFaults;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int instanceCount = scan.nextInt();
        Hashtable<Integer, LinkedList<Integer>> table = new Hashtable<Integer, LinkedList<Integer>>();
        ArrayList<Integer> requestList = new ArrayList<Integer>();
        ArrayList<Integer> outputList = new ArrayList<Integer>();

        scan.nextLine();
        for (int i = 0; i < instanceCount; i++) {
            int cacheSize = scan.nextInt();
            scan.nextLine();
            int numRequests = scan.nextInt();
            scan.nextLine();

            String[] line = scan.nextLine().trim().split(" ");
            for (int j = 0; j < numRequests; j++) {
                int req = Integer.parseInt(line[j]);
                requestList.add(req);
                if (!table.keySet().contains(req)) {
                    LinkedList<Integer> linkedList = new LinkedList<Integer>();
                    linkedList.add(j);
                    table.put(req, linkedList);
                } else {
                    table.get(req).add(j);
                }

            }
            outputList.add(FFfaults(cacheSize, requestList, table, outputList));
            // System.out.println(table);
            requestList.clear();
            table.clear();
        }
        scan.close();
        for (Integer output : outputList) {
            System.out.println(output);
        }
    }
}