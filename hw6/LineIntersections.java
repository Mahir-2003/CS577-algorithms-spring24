import java.util.Scanner;

public class LineIntersections {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int instances = scnr.nextInt(); //getting number of instances from user
        scnr.nextLine();

        for (int i = 0; i < instances; i++) {
            int numOfPoints = scnr.nextInt();
            scnr.nextLine();

            int q[] = new int[numOfPoints];
            int p[] = new int[numOfPoints];


            for (int j = 0; j < numOfPoints; j++) {
                q[j] = scnr.nextInt();
                scnr.nextLine();
            }
            
            for (int j = 0; j < numOfPoints; j++) {
                p[j] = scnr.nextInt();
                scnr.nextLine();
            }
            
            // Sort array p using insertion sort
            for (int j = 1; j < p.length; j++) {
                int key = p[j];
                int keyQ = q[j];
                int k = j - 1;

                while (k >= 0 && p[k] > key) {
                    p[k + 1] = p[k];
                    q[k + 1] = q[k];
                    k--;
                }
                p[k + 1] = key;
                q[k + 1] = keyQ;
            }

            System.out.println(countIntersections(q, new int[q.length], 0, q.length - 1));        
        }
        scnr.close();
    }

    // intersection stuff
    public static long countIntersections(int[] i, int[] temp, int leftStart, int rightEnd) {
        
        if (leftStart >= rightEnd) {
            return 0;
        }

        int midpoint = (leftStart + rightEnd) / 2;

        long intersections = countIntersections(i, temp, leftStart, midpoint);
        intersections += countIntersections(i, temp, midpoint + 1, rightEnd);
        intersections += mergeIntersections(i, temp, leftStart, rightEnd);
        
        return intersections; 
    }

    public static long mergeIntersections(int[] array, int[] temp, int leftStart, int rightEnd) {
        int leftEnd = (leftStart + rightEnd) / 2; // the ending index for the left pointer
        int rightStart = leftEnd + 1; //the start index for the right pointer
        int size = rightEnd - leftStart + 1; //size of the current array

        int left = leftStart;
        int right = rightStart;
        int index = leftStart;
        long inversions = 0;

        while (left <= leftEnd && right <= rightEnd) {
            if (array[left] <= array[right]) {
                temp[index] = array[left];
                left++;
            } else {
                temp[index] = array[right];
                right++;
                inversions += leftEnd - left + 1;
            }
            index++;
        }

        System.arraycopy(array, left, temp, index, leftEnd - left + 1);
        System.arraycopy(array, right, temp, index, rightEnd - right + 1);
        System.arraycopy(temp, leftStart, array, leftStart, size);

        return inversions;
    }

}