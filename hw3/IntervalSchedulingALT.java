import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class IntervalSchedulingALT {
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

            //sorting jobs by end time
            Collections.sort(intervals, Comparator.comparingInt(interval -> interval.end));
            
            int count = 1;
            int lastEndTime = intervals.get(0).end;

            for (int j = 1; j < jobCount; j++) {
                if (intervals.get(j).start >= lastEndTime) {
                    count++;
                    lastEndTime = intervals.get(j).end;
                } 
            }
            System.out.println(count);
        }
        scnr.close();
    }
    

    static class Interval {
        Integer start;
        Integer end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString(){
            return "start time: " + this.start + ", end time: " + this.end;
        }
    }
}
