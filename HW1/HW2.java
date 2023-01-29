import java.util.Scanner;

public class HW2 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.print("Enter your dollar amount: ");
    double dollar = input.nextDouble();

    int hundreds = (int) dollar / 100;
    int fifties = (int) dollar % 100 / 50;
    int twenties = (int) dollar % 50 / 20;
    int tens = (int) dollar % 20 / 10;
    int fives = (int) dollar % 10 / 5;
    int ones = (int) dollar % 5 / 1;
    int quarters = (int) (dollar * 100) % 100 / 25;
    int dimes = (int) (dollar * 100) % 25 / 10;
    int nickels = (int) (dollar * 100) % 25 % 10 / 5;
    int cents = (int) (dollar * 100) % 5;

    System.out.println("You have:");
    System.out.println("- " + hundreds + " hundred(s) ");
    System.out.println("- " + fifties + " fifty(s) ");
    System.out.println("- " + twenties + " twenty(s) ");
    System.out.println("- " + tens + " ten(s) ");
    System.out.println("- " + fives + " five(s) ");
    System.out.println("- " + ones + " one(s) ");
    System.out.println("- " + quarters + " quarter(s) ");
    System.out.println("- " + dimes + " dime(s) ");
    System.out.println("- " + nickels + " nickel(s) ");
    System.out.println("- " + cents + " cent(s) ");
    System.out.println("Goodbye!");
  }
}