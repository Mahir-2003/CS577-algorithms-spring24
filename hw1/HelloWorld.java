import java.util.Scanner;

public class HelloWorld {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        Integer count = scnr.nextInt();
        scnr.nextLine();
        String[] names = new String[count];
        for(int i = 0; i < count; i++){
            names[i] = scnr.next();
        }
        for(int i =0; i < count; i++){
            System.out.println("Hello, " + names[i] + "!");
        }
        scnr.close();
    }
}