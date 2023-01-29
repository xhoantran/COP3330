// HW4
// Hoan Tran

import java.text.DecimalFormat;

public class Main {
public static void main(String[] args) {
  String fullName = "Erika T. Jones";
  String employeeNumber = "ej789";
  double payRate = 100.0 , hoursWorked = 1.0;
  //TA will change the payrate and the hours worked to test your code
  Employee e;
  e = new Employee (fullName,employeeNumber,payRate,hoursWorked);
  System.out.println(e); //To Test your toString method
  e.printCheck(); //This prints the check of Erika T. Jones
  System.out.println("Bye!");
}
}

class Employee {
  private String fullName;
  private String employeeNumber;
  private double payRate;
  private double hoursWorked;

  public Employee(String fullName, String employeeNumber, double payRate, double hoursWorked) {
    this.fullName = fullName;
    this.employeeNumber = employeeNumber;
    this.payRate = payRate;
    this.hoursWorked = hoursWorked;
  }

  public String getFullName() {
    return this.fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmployeeNumber() {
    return this.employeeNumber;
  }

  public void setEmployeeNumber(String employeeNumber) {
    this.employeeNumber = employeeNumber;
  }

  public double getPayRate() {
    return this.payRate;
  }

  public void setPayRate(double payRate) {
    this.payRate = payRate;
  }

  public double getHoursWorked() {
    return this.hoursWorked;
  }

  public void setHoursWorkede(double hoursWorked) {
    this.hoursWorked = hoursWorked;
  }

  @Override
  public String toString() {
    return "[" + this.employeeNumber + "/" + this.fullName + ", " + (int)this.hoursWorked + " Hours @ " + this.payRate + " per hour]";
  }

  private double netPay() {
    return this.payRate * this.hoursWorked * 0.94;
  }

  public void printCheck() {
    DecimalFormat df = new DecimalFormat("0.00");

    System.out.println("Employee's name:\t\t" + this.fullName);
    System.out.println("Employee's number:\t\t" + this.employeeNumber);
    System.out.println("Hourly rate of pay:\t\t" + df.format(this.payRate));
    System.out.println("Hours worked:\t\t\t" + df.format(this.hoursWorked) + "\n");
    System.out.println("Gross pay:\t\t\t" + df.format(this.payRate * this.hoursWorked) + "\n");
    System.out.println("Deductions");
    System.out.println("Tax (6 %):\t\t\t" + df.format((this.payRate * this.hoursWorked) * 0.06) + "\n");
    System.out.println("Net pay:\t\t\t" + df.format(this.netPay()));
  }
}