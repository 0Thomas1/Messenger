import java.util.Scanner;

public class GenTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String output = input;

        sc.nextLine();
        while(!input.equals("")) {
            output += "\n" + input;
            input = sc.nextLine();
        }
        System.out.println(output);
    }
}