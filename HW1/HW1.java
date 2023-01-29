import java.util.Scanner;
import java.text.DecimalFormat;

public class HW1 {
    public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      System.out.print("Enter the Employee’s full name: ");
      String name = input.nextLine();
      System.out.print("Enter the Employee’s number: ");
      String number = input.nextLine();
      System.out.print("Enter the pay rate per hour: ");
      double rate = input.nextDouble();
      System.out.print("Enter the regular hours worked: ");
      double hours = input.nextDouble();

      DecimalFormat df = new DecimalFormat("0.00");

      System.out.println("\nThank you!\n\nHere is your paycheck!\n\n\n------------------------------------------\n");
      System.out.println("Employee's name:          " + name);
      System.out.println("Employee's number:        " + number);
      System.out.println("Hourly rate of pay:       " + df.format(rate));
      System.out.println("Hours worked:             " + df.format(hours) + "\n");
      System.out.println("Gross pay:                " + df.format(rate * hours) + "\n");
      System.out.println("Deductions");
      System.out.println("Tax (6 %):                " + df.format((rate * hours) * 0.06) + "\n");
      System.out.println("Net pay:                  " + df.format((rate * hours) - ((rate * hours) * 0.06)));
      System.out.println("------------------------------------------\n\n\n\nBye!");
    }
}