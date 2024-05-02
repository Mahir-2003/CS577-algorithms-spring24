import java.util.Scanner;
import java.lang.Math;

public class Knapsack {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int instances = scnr.nextInt(); //getting number of instances from user
        scnr.nextLine();

        for (int i = 0; i < instances; i++) {
            String[] inputLine= scnr.nextLine().split(" ");

            int numOfItems = Integer.parseInt(inputLine[0]);
            int totalWeight = Integer.parseInt(inputLine[1]);

            int[] values = new int[numOfItems];
            int[] weights = new int[numOfItems];

            for (int j = 0; j < numOfItems; j++){
                String[] inputLine2 = scnr.nextLine().split(" ");
                weights[j] = Integer.parseInt(inputLine2[0]);
                values[j] = Integer.parseInt(inputLine2[1]);
            }
            System.out.println(knapsack(weights, values, totalWeight));
        }
        scnr.close();
    }

    public static int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][capacity];
    }

    // public static int knapsackMaximizer(int numOfItems, int totalWeight, int[] values, int[] weights) {        
       
    //     int[][] table = new int[numOfItems + 1][totalWeight + 1];

    //     //fill table with -1's
    //     //done to show if a cell has been computed yet or not
    //     //i.e: if -1, then compute, otherwise value already computed.
    //     for (int i = 0; i < numOfItems + 1; i++)
    //         for (int j = 0; j < totalWeight + 1; j++)
    //             table[i][j] = -1;

    //     return knapsack(totalWeight, table);

    // }

    // public static int knapsackRecursive(int n, int totalWeight, int[] values, int[] weights, int[][] table) {
        
    //     // base case
    //     if(n == 0 || totalWeight == 0) {
    //         return 0;
    //     }
    //     // the current table lookup has already been calculated so return it 
    //     if(table[n][totalWeight] != -1) {
    //         return table[n][totalWeight]; 
    //     }

    //     // when current weight > totalWeight, skip to below
    //     if (weights[n - 1] > totalWeight) {
    //         //recurse before return
    //         return table[n][totalWeight] = knapsackRecursive(n - 1, totalWeight, values, weights, table); 
    //     }
    //     //actual dp algo
    //     else {
    //         return table[n - 1][totalWeight] = 
    //         Math.max(values[n - 1] + knapsackRecursive(n - 1, totalWeight - weights[n - 1], values, weights, table), 
    //             knapsackRecursive(n - 1, totalWeight, values, weights, table));

    //     }
    // }

}