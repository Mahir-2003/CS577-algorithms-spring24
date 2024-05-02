import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;
import java.util.Stack;
/**
 * IntervalScheduling
 */
public class IntervalScheduling {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in); 
        int instances = scnr.nextInt(); //getting number of instances from user
        scnr.nextLine();

        for (int i = 0; i < instances; i++){
            int jobCount = scnr.nextInt(); //getting number of nodes for current instance
            scnr.nextLine();
            ArrayList<Interval> intervals = new ArrayList<>();
            
            for(int j = 0; j < jobCount; j++) {
                String[] jobs = scnr.nextLine().split(" ");

                if(Integer.parseInt(jobs[0]) > Integer.parseInt(jobs[1])){
                    throw new IllegalArgumentException("Start time cannot be greater than end time");
                }

                intervals.add(new Interval(Integer.parseInt(jobs[0]), Integer.parseInt(jobs[1])));
            } 
            intervals.sort(Comparator.comparingInt(interval -> interval.end));
            
            ArrayList<Stack<Interval>> output = finishFirstAlgorithm(intervals);
            System.out.println(output.size());
        }

        scnr.close();
    }

    public static ArrayList<Stack<Interval>> finishFirstAlgorithm(ArrayList<Interval> intervals) { 
        
        ArrayList<Stack<Interval>> numOfLines = new ArrayList<>();
        

        while(!intervals.isEmpty()){
            Interval current = intervals.remove(0);

            if (numOfLines.isEmpty()) {
                Stack<Interval> firstStack = new Stack<Interval>();
                firstStack.push(current); //adding current since no other stacks
                numOfLines.add(firstStack); //first stack being added
            } else {
                boolean addedToStack = false;

                for (Stack<Interval> stack : numOfLines) {
                    // if(addedToStack) break;
                    if (stack.peek().end <= current.start) { //checking if interval can be added to the current stack
                        // System.out.println("adding to existing stack: " + current);
                        stack.push(current);
                        addedToStack = true;
                    }
                    // else if (stack.peek().start == current.start && stack.peek().end == current.end) { //checking for duplicate in the stack
                    //     // System.out.println("exiting due to duplicate");
                    //     addedToStack = true;
                    //     continue;
                    // }
                    //idea: get rid of this, first iterate through all present stacks and checking for duplicates before making a new stack.
                    // else if (stack.peek().end > current.start){          //need to check all stacks first to see that there isn't a duplicate in them already. Fix this!        
                        
                    // }
                }
                //adding a new stack to list since current hasn't been added to any
                // if(!addedToStack){
                //     Stack<Interval> newStack = new Stack<Interval>();
                //     newStack.push(current);
                //     numOfLines.add(newStack); // Add new stacks to the main list
                // }
            } 
        }
        return numOfLines;
    }

    static class Interval implements Comparable<Interval>{
        Integer start;
        Integer end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(IntervalScheduling.Interval o) {
            return end.compareTo(o.end);
        }

        @Override
        public String toString(){
            return "start time: " + this.start + ", end time: " + this.end;
        }
        
        public boolean equals(IntervalScheduling.Interval o) {
            return this.start == o.start && this.end == o.end;
        }
    }
}

