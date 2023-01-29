import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

public class Project2 {
  public static int printMenuAndGetChoice() {
    while (true) {
      System.out.println("1.  Enter the information of a faculty");
      System.out.println("2.  Enter the information of a students");
      System.out.println("3.  Print tuition invoice ");
      System.out.println("4.  Print faculty information");
      System.out.println("5.  Enter the information of a staff member");
      System.out.println("6.  Print the information of a staff member");
      System.out.println("7.  Exit Program\n");
      System.out.print("\tEnter your selection: ");

      try {
        int choice = (new Scanner(System.in)).nextInt();
        if (choice >= 1 && choice <= 7) {
          System.out.println();
          return choice;
        }
      } catch (Exception e) {
        System.out.println("\nInvalid entry - please try again\n");
      }
    }
  }

  public static void main(String[] args) {
    Personnel personnel = new Personnel();
    System.out.println("\n\t\tWelcome to my Personnel Management Program\n");
    System.out.println("Choose one of the options:\n");
    while (true) {
      int choice = printMenuAndGetChoice();
      switch (choice) {
        case 1:
          personnel.addFaculty();
          break;
        case 2:
          personnel.addStudent();
          break;
        case 3:
          personnel.printStudent();
          break;
        case 4:
          personnel.printFaculty();
          break;
        case 5:
          personnel.addStaff();
          break;
        case 6:
          personnel.printStaff();
          break;
        case 7:
          System.out.print("\n\nWould you like to create the report? (Y/N):");
          String answer = (new Scanner(System.in)).nextLine();
          while (!answer.equalsIgnoreCase("Y") && !answer.equalsIgnoreCase("N")) {
            System.out.print("\nInvalid entry - please try again: ");
            answer = (new Scanner(System.in)).nextLine();
          }
          if (answer.equalsIgnoreCase("Y")) {
            System.out.print("Would you like to sort your students by (1) gpa or (2) credit hours: ");
            String sort = (new Scanner(System.in)).nextLine();
            while (!sort.equalsIgnoreCase("1") && !sort.equalsIgnoreCase("2")) {
              System.out.print("\nInvalid entry - please try again: ");
              sort = (new Scanner(System.in)).nextLine();
            }
            if (sort.equalsIgnoreCase("1")) {
              personnel.printReportSortByGPA();
            } else {
              personnel.printReportSortByCreditHours();
            }
            System.out.println("Report created and saved on your hard drive! ");
          }

          System.out.println("Goodbye!");
          System.exit(0);
        default:
          System.out.println("Invalid choice - please try again\n");
      }
    }
  }
}

// ---------------------------
class IdException extends Exception {
  public IdException(String message) {
    super(message);
  }
}

// ---------------------------
class Personnel {
  private Person[] list;
  private int count;

  public Personnel() {
    list = new Person[100];
    count = 0;
  }

  public void printReportSortByGPA() {
    ArrayList<Student> students = new ArrayList<Student>();
    ArrayList<Faculty> faculty = new ArrayList<Faculty>();
    ArrayList<Staff> staff = new ArrayList<Staff>();
    for (int i = 0; i < count; i++) {
      if (list[i] instanceof Student) {
        students.add((Student) list[i]);
      } else if (list[i] instanceof Faculty) {
        faculty.add((Faculty) list[i]);
      } else if (list[i] instanceof Staff) {
        staff.add((Staff) list[i]);
      }
    }

    // Sort students by GPA
    Collections.sort(students, new StudentGpaComparator());

    // Print report into report.txt
    try {
      PrintWriter writer = new PrintWriter("report.txt", "UTF-8");
      DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
      Date date = new Date();
      writer.println("\t\tReport created on " + dateFormat.format(date));
      writer.println("\t\t****************************n\n");

      writer.println("Faculty Members:");
      writer.println("-------------------------");
      for (int i = 0; i < faculty.size(); i++) {
        writer.println(faculty.get(i).toReport(i + 1));
      }

      writer.println("\n\nStaff Members");
      writer.println("-------------------");
      for (int i = 0; i < staff.size(); i++) {
        writer.println(staff.get(i).toReport(i + 1));
      }

      writer.println("\n\nStudents (Sorted by gpa)");
      writer.println("-------------------------");
      for (int i = 0; i < students.size(); i++) {
        writer.println(students.get(i).toReport(i + 1));
      }

      writer.close();
    } catch (Exception e) {
      System.out.println("Error writing to file");
    }
  }

  public void printReportSortByCreditHours() {
    ArrayList<Student> students = new ArrayList<Student>();
    ArrayList<Faculty> faculty = new ArrayList<Faculty>();
    ArrayList<Staff> staff = new ArrayList<Staff>();
    for (int i = 0; i < count; i++) {
      if (list[i] instanceof Student) {
        students.add((Student) list[i]);
      } else if (list[i] instanceof Faculty) {
        faculty.add((Faculty) list[i]);
      } else if (list[i] instanceof Staff) {
        staff.add((Staff) list[i]);
      }
    }

    // Sort students by credit hours
    Collections.sort(students, new StudentCreditHoursComparator());

    // Print report into report.txt
    try {
      PrintWriter writer = new PrintWriter("report.txt", "UTF-8");
      DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
      Date date = new Date();
      writer.println("\t\tReport created on " + dateFormat.format(date));
      writer.println("\t\t****************************n\n");

      writer.println("Faculty Members:");
      writer.println("-------------------------");
      for (int i = 0; i < faculty.size(); i++) {
        writer.println(faculty.get(i).toReport(i + 1));
      }

      writer.println("\n\nStaff Members");
      writer.println("-------------------");
      for (int i = 0; i < staff.size(); i++) {
        writer.println(staff.get(i).toReport(i + 1));
      }

      writer.println("\n\nStudents (Sorted by credit hours)");
      writer.println("-------------------------");
      for (int i = 0; i < students.size(); i++) {
        writer.println(students.get(i).toReport(i + 1));
      }

      writer.close();
    } catch (Exception e) {
      System.out.println("Error writing to file");
    }
  }

  private int findIdIndex(String id) {
    if (id == null) {
      return -1;
    }

    id = id.toLowerCase();
    for (int i = 0; i < count; i++) {
      if (list[i].getId().equals(id)) {
        return i;
      }
    }

    return -1;
  }

  private String sanitizeId(String id) throws IdException {
    if (id == null) {
      throw new IdException("Id cannot be null");
    }

    if (!id.matches("[a-z]{2}[0-9]{4}")) {
      throw new IdException("Invalid ID format. Must be LetterLetterDigitDigitDigitDigit");
    }

    if (findIdIndex(id) != -1) {
      throw new IdException("Id already exists");
    }

    return id.trim().toLowerCase();
  }

  public void addStudent() {
    System.out.println("Enter the student info:");
    System.out.print("\tName of Student: ");
    Scanner input = new Scanner(System.in);
    String name = input.nextLine();

    String id = null;
    System.out.print("\tID: ");
    while (true) {
      id = input.nextLine();
      try {
        id = validateId(id);
        break;
      } catch (IdException e) {
        System.out.println("\t" + e.getMessage());
        System.out.print("\tID: ");
      }
    }
    double gpa = 0;
    while (true) {
      try {
        System.out.print("\tGPA: ");
        String temp = input.nextLine();
        gpa = Double.parseDouble(temp);
        break;
      } catch (Exception e) {
        System.out.println("\tInvalid GPA - please try again.");
      }
    }
    int numCredits = 0;
    while (true) {
      try {
        System.out.print("\tCredit hours: ");
        String temp = input.nextLine();
        numCredits = Integer.parseInt(temp);
        break;
      } catch (Exception e) {
        System.out.println("\tInvalid Credit Hours - please try again.");
      }
    }

    list[count] = new Student(name, id, gpa, numCredits);
    count++;
    System.out.println("Student added!\n\n");
  }

  public void addFaculty() {
    System.out.println("Enter the faculty info:");
    System.out.print("\tName of Faculty: ");
    Scanner input = new Scanner(System.in);
    String name = input.nextLine();

    String id = null;
    System.out.print("\tID: ");
    while (true) {
      id = input.nextLine();
      try {
        id = validateId(id);
        break;
      } catch (IdException e) {
        System.out.println("\t" + e.getMessage());
        System.out.print("\tID: ");
      }
    }

    Faculty faculty = new Faculty(name, id);
    list[count] = faculty;
    count++;

    String rank = null;
    while (true) {
      System.out.print("\tRank: ");
      rank = input.nextLine();
      try {
        faculty.setRank(rank);
        break;
      } catch (Exception e) {
        System.out.println("\t\t" + e.getMessage() + "");
      }
    }

    String department = null;
    while (true) {
      System.out.print("\tDepartment: ");
      department = input.nextLine();
      try {
        faculty.setDepartment(department);
        break;
      } catch (Exception e) {
        System.out.println("\t\t" + e.getMessage() + "");
      }
    }

    System.out.println("Faculty added!\n\n");
  }

  public void addStaff() {
    System.out.println("Enter the staff info:");
    System.out.print("\tName of Staff: ");
    Scanner input = new Scanner(System.in);
    String name = input.nextLine();

    String id = null;
    System.out.print("\tID: ");
    while (true) {
      id = input.nextLine();
      try {
        id = validateId(id);
        break;
      } catch (IdException e) {
        System.out.println("\t" + e.getMessage());
        System.out.print("\tID: ");
      }
    }

    Staff staff = new Staff(name, id);
    list[count] = staff;
    count++;

    String department = null;
    while (true) {
      System.out.print("\tDepartment: ");
      department = input.nextLine();
      try {
        staff.setDepartment(department);
        break;
      } catch (Exception e) {
        System.out.println("\t\t" + e.getMessage() + "");
      }
    }

    String status = null;
    while (true) {
      System.out.print("\tStatus, Enter P for Part Time, or Enter F for Full Time: ");
      status = input.nextLine();
      try {
        staff.setStatus(status);
        break;
      } catch (Exception e) {
        System.out.println("\t\t" + e.getMessage());
      }
    }

    System.out.println("Staff added!\n\n");
  }

  public void printStudent() {
    System.out.print("\tEnter the student's is: ");
    String id = (new Scanner(System.in)).nextLine();
    int index = findIdIndex(id);
    if (index != -1 && list[index] instanceof Student) {
      list[index].print();
      return;
    }
    System.out.println("\tNo student matched!\n");
  }

  public void printFaculty() {
    System.out.print("\tEnter the faculty's is: ");
    String id = (new Scanner(System.in)).nextLine();
    int index = findIdIndex(id);
    if (index != -1 && list[index] instanceof Faculty) {
      list[index].print();
      return;
    }
    System.out.println("\tNo faculty matched!\n");
  }

  public void printStaff() {
    System.out.print("\tEnter the staff's is: ");
    String id = (new Scanner(System.in)).nextLine();
    int index = findIdIndex(id);
    if (index != -1 && list[index] instanceof Staff) {
      list[index].print();
      return;
    }
    System.out.println("\tNo staff matched!\n");
  }
}

// ---------------------------
abstract class Person {
  private String fullName;
  private String id;

  public Person(String fullName, String id) {
    setFullName(fullName);
    setId(id);
  }

  public Person(String id) {
    setId(id);
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id.toLowerCase();
  }

  public void printHeader() {
    System.out.println("---------------------------------------------------------------------------");
    System.out.println(getFullName() + "\t\t" + getId());
  }

  public void printFooter() {
    System.out.println("---------------------------------------------------------------------------\n\n");
  }

  public abstract void print();

  public abstract String toReport(int index);
}

// ---------------------------
abstract class Employee extends Person {
  private String department = "None";

  public Employee(String fullName, String id, String department) {
    super(fullName, id);
    setDepartment(department);
  }

  public Employee(String fullName, String id) {
    super(fullName, id);
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    department = department.substring(0, 1).toUpperCase() + department.substring(1).toLowerCase();
    if (department.equals("Mathematics") || department.equals("Engineering")
        || department.equals("Sciences")) {
      this.department = department;
      return;
    }
    throw new IllegalArgumentException("“" + department + "” is invalid");
  }
}

// ---------------------------
class Faculty extends Employee {
  private String rank = "None";

  public Faculty(String fullName, String id, String rank, String department) {
    super(fullName, id, department);
    setRank(rank);
  }

  public Faculty(String fullName, String id) {
    super(fullName, id);
  }

  public String getRank() {
    return rank;
  }

  public void setRank(String rank) {
    rank = rank.substring(0, 1).toUpperCase() + rank.substring(1).toLowerCase();
    if (rank.equals("Professor") || rank.equals("Adjunct")) {
      this.rank = rank;
      return;
    }
    throw new IllegalArgumentException("“" + rank + "” is invalid");
  }

  public void print() {
    printHeader();
    System.out.println(getDepartment() + " Department, " + getRank());
    printFooter();
  }

  public String toReport(int index) {
    return "\t" + index + ". " + getFullName() + "\n\tID: " + getId() + "\n\t" + getDepartment() + ", "
        + getRank() + "\n";
  }
}

// ---------------------------
class Staff extends Employee {
  private String status = "None";

  public Staff(String fullName, String id, String department, String status) {
    super(fullName, id, department);
    setStatus(status);
  }

  public Staff(String fullName, String id) {
    super(fullName, id);
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    if (status.equalsIgnoreCase("f")) {
      this.status = "Full Time";
      return;
    }
    if (status.equalsIgnoreCase("p")) {
      this.status = "Part Time";
      return;
    }
    throw new IllegalArgumentException("Status must be 'F' or 'P'");
  }

  public void print() {
    printHeader();
    System.out.println(getDepartment() + " Department, " + getStatus());
    printFooter();
  }

  public String toReport(int index) {
    return "\t" + index + ". " + getFullName() + "\n\tID: " + getId() + "\n\t" + getDepartment() + ", "
        + getStatus() + "\n";
  }
}

// ---------------------------
class Student extends Person {
  private double gpa = 0.0;
  private int credits = 0;

  public Student(String fullName, String id, double gpa, int credits) {
    super(fullName, id);
    setGpa(gpa);
    setCredits(credits);
  }

  public Student(String fullName, String id) {
    super(fullName, id);
  }

  public double getGpa() {
    return gpa;
  }

  public void setGpa(double gpa) {
    this.gpa = gpa;
  }

  public int getCredits() {
    return credits;
  }

  public void setCredits(int credits) {
    this.credits = credits;
  }

  public void print() {
    System.out.println("\nHere is the tuition invoice for " + getFullName() + " :\n");
    printHeader();
    System.out.println("Credit Hours: " + getCredits() + " ($236.45/credit hour)");
    System.out.println("Fee: $52\n");
    double creditPayment = getCredits() * 236.45 + 52;
    String discount = getGpa() >= 3.85 ? String.format("%,.2f", creditPayment * 0.25) : "0";
    String totalPayment = String.format("%,.2f", getGpa() >= 3.85 ? creditPayment * 0.75 : creditPayment);
    System.out.println("Total payment: $" + totalPayment + " ($" + discount + " discount applied)");
    printFooter();
  }

  public String toReport(int index) {
    return "\t" + index + ". " + getFullName() + "\n\tID: " + getId() + "\n\tGPA: " + getGpa() + "\n\tCredit hours: "
        + getCredits() + "\n";
  }
}

class StudentGpaComparator implements Comparator<Student> {
  public int compare(Student s1, Student s2) {
    if (s1.getGpa() > s2.getGpa())
      return -1;
    if (s1.getGpa() < s2.getGpa())
      return 1;
    return 0;
  }
}

class StudentCreditsComparator implements Comparator<Student> {
  public int compare(Student s1, Student s2) {
    if (s1.getCredits() > s2.getCredits())
      return -1;
    if (s1.getCredits() < s2.getCredits())
      return 1;
    return 0;
  }
}