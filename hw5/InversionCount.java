import java.util.Scanner;
import java.util.Arrays;

public class InversionCount {
    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);
        int instances = scnr.nextInt(); //getting number of instances from user
        scnr.nextLine();

        for (int i = 0; i < instances; i++){
            long listCount = scnr.nextInt(); //getting number of nodes for current instance
            scnr.nextLine();

            String[] list = scnr.nextLine().split(" ");
            int[] arr = new int[list.length];

            for (int k = 0; k < list.length; k++) { 
                arr[k] = Integer.parseInt(list[k]);
            }

            // System.out.println(countSort(arr, 0, arr.length - 1));
            System.out.println(countInversions(arr));
 
        }
        scnr.close();
    }

    public static long countInversions(int[] arr){
        return countSort(arr, new int[arr.length], 0, arr.length - 1);
    }

    public static long countSort(int[] array, int[] temp, int leftStart, int rightEnd) {
        
        if (leftStart >= rightEnd) {
            return 0;
        }

        int midpoint = (leftStart + rightEnd) / 2;

        long inversions = countSort(array, temp, leftStart, midpoint);
        inversions += countSort(array, temp, midpoint + 1, rightEnd);
        inversions += merge(array, temp, leftStart, rightEnd);   

        return inversions;
    }

    public static long merge (int[] array, int[] temp, int leftStart, int rightEnd) {
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