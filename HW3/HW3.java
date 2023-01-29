import java.text.DecimalFormat;

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
  }3

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

  @override
  public String toString() {
    return "[" + this.employeeNumber + "/" + this.fullName + ", " + this.hoursWorked + " Hours @ " + this.payRate + " per hour] "
  }

  private double netPay() {
    return this.payRate * this.hoursWorked * 0.94;
  }

  public void printCheck() {
    DecimalFormat df = new DecimalFormat("0.00");

    System.out.println("Employee's name:\t\t\t" + this.fullName);
    System.out.println("Employee's number:\t\t\t" + number);
    System.out.println("Hourly rate of pay:\t\t\t" + df.format(rate));
    System.out.println("Hours worked:\t\t\t" + df.format(hours) + "\n");
    System.out.println("Gross pay:\t\t\t" + df.format(rate * hours) + "\n");
    System.out.println("Deductions");
    System.out.println("Tax (6 %):\t\t\t" + df.format((rate * hours) * 0.06) + "\n");
    System.out.println("Net pay:\t\t\t" + df.format((rate * hours) - ((rate * hours) * 0.06)));
  }
}