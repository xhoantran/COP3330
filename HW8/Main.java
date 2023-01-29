import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

class Employee {
  private int id;
  private String name;
  private double salary;
  private int numberOfDependents;

  public Employee(int id, String name, double salary, int numberOfDependents) {
    setId(id);
    setName(name);
    setSalary(salary);
    setNumberOfDependents(numberOfDependents);
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  public double getSalary() {
    return salary;
  }

  public void setNumberOfDependents(int numberOfDependents) {
    this.numberOfDependents = numberOfDependents;
  }

  public int getNumberOfDependents() {
    return numberOfDependents;
  }

  public String toString() {
    return "[" + id + " " + name + " " + String.format("%.2f", getNetSalary()) + "]";
  }

  public double getNetSalary() {
    return salary * (0.91 + numberOfDependents * 0.01);
  }

  public boolean equals(Object o) {
    if (o instanceof Employee) {
      Employee e = (Employee) o;
      // Compare double values with a tolerance of 0.01
      return Math.abs(getNetSalary() - e.getNetSalary()) < 0.01;
    }
    return false;
  }
}

public class Main {
  public static void main(String[] args) {
    Employee emp1 = new Employee(111, "Jimmy Dean", 5256.32, 0),
        emp2 = new Employee(598, "Jen Johnson", 47370, 5),
        emp3 = new Employee(920, "Jan Jones", 47834.25, 1);

    System.out.println(emp1.equals(emp3));

    ArrayList<Employee> list = new ArrayList<>();

    list.add(emp1);
    list.add(emp2);
    list.add(emp3);

    // Sort the list
    Collections.sort(list, new Comparator<Employee>() {
      @Override
      public int compare(Employee o1, Employee o2) {
        return (int) (o1.getNetSalary() - o2.getNetSalary());
      }
    });

    for (Employee e : list)
      System.out.println(e);
  }
}