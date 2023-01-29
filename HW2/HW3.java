import java.util.Scanner;
import java.text.DecimalFormat;

public class HW3 {
  public static void main(String[] args) {
    int a = 0, b = 0, c = 0, d = 0, f = 0;
    int count = 0, total = 0;

    System.out.println("Welcome to my program. You will be asked to enter the scores of a test one by one, and to enter -1 to stop.\n");

    while (true) {
      System.out.print("Enter Score (Enter -1 to Stop): ");
      int inp = (new Scanner(System.in)).nextInt();
      
      if (inp == -1) {
        break;
      }

      if (inp < 0 || inp > 100) {
        System.out.println("Score " + inp + " Rejected");
        continue;
      }

      if (inp >= 90) {
        a++;
      } else if (inp >= 80) {
        b++;
      } else if (inp >= 70) {
        c++;
      } else if (inp >= 60) {
        d++;
      } else {
        f++;
      }

      count++;
      total += inp;
    }

    System.out.println("\nHere is your report:");
    System.out.println("\t- A total of " + count + " scores entered. " + (a + b + c) + " of them are 70 or higher.\n");
    System.out.println("\t- Letter Grade distribution of the scores:");
    System.out.println("\t\t- " + a + " Students earned the grade of A (90-100)");
    System.out.println("\t\t- " + b + " Students earned the grade of B (80-89)");
    System.out.println("\t\t- " + c + " Students earned the grade of C (70-79)");
    System.out.println("\t\t- " + d + " Students earned the grade of D (60-69)");
    System.out.println("\t\t- " + f + " Students earned the grade of F (0-59)");
    System.out.println("\t- The average score is: " + (new DecimalFormat("0.00")).format(total / count));

    System.out.print("\nWould you like to process another class? (Y or N): ");
    String inp = (new Scanner(System.in)).next();
    if (inp.equals("Y") || inp.equals("y")) {
      main(args);
    } else {
      System.out.println("Goodbye!");
    }

  }
}