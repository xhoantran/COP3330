// public class Main {
// private static Test t1, t2;

// public static void main(String args[]) {
// t1 = new Test(4, 5);
// t2 = new Test(1, 2);
// print();
// }

// private static void print() {
// System.out.print(t1.x + " " + t2.y);
// }
// }

// class Test {
// public int x, y;

// public Test(int x, int y) {
// this.x = x;
// this.y = y;
// }

// private void print() {
// System.out.print(y + " " + x);
// }
// }

// public class Main {
// public static void main(String args[]) {
// A a = new A();
// B b = new B();
// C c = new C();
// c = a;
// b = a;
// System.out.println(b);
// }
// }

// class A extends B {
// public String toString() {
// return "A";
// }
// }

// class B extends C {
// public String toString() {
// return "B";
// }
// }

// class C {
// public String toString() {
// return "C";
// }
// }

// public class Main {
// public static void main(String args[]) {
// double x = 0;
// try {
// x = 1 / 2 + (double) (1 / 2) + (double) 1 / 2;
// System.out.print(x);
// } catch (ArithmeticException e) {
// System.out.println("9");
// } finally {
// System.out.println(x + 0.5);
// }
// }
// }

// public class Main {
// public static void main(String args[]) {
// String[] array = new String[2];
// int i = 1;
// try {
// for (i = 0; i < 2; i++) {
// array[i] = new String("9");
// System.out.println(array[i-1]);
// }
// }
// catch {
// System.out.println("!");
// }
// }
// }

// class Test {
// public static int x;
// public int y;

// Test() {
// x = 1;
// y = 1;
// }
// }

// public class Main {
// public static void main(String args[]) {
// Test t2 = new Test(2, 1);
// Test t3 = new Test(1, 2);
// t3 = t2;
// t2 = t3;
// t2.print();
// }
// }

// class Test {
// private int x, y;

// public void setAll(int x, int y) {
// this.x = x;
// this.y = y;
// }

// public Test(int x, int y) {
// setAll(x, y);
// }

// public void print() {
// System.out.println(x + " " + y);
// }
// }

// public class Main {
// public static void main(String args[]) {
// Employee e = new Manager();
// e.print(2);
// }

// private static void method(Employee e) {
// e.print();
// }
// }

// abstract class Employee {
// public void print() {
// System.out.println("Employee");
// }
// }

// class Manager extends Employee {
// public void print(int k) {
// System.out.println("Manager");
// }

// public void print() {
// System.out.println("Manager2");
// }

// public void print(Employee e) {
// e.print();
// }
// }

// public class Main {
// public static void main(String args[]) {
// Soccer game1 = new Soccer();
// System.out.println(game1);
// }
// }

// class Sport {
// public int duration;

// public Sport(int duration) {
// this.duration = duration;
// }
// }

// class Soccer extends Sport {
// private int NumReferee;

// public Soccer(int NumReferee) {
// super(90);
// NumReferee = 3;
// }

// public Soccer() {
// super(0);
// NumReferee = 0;
// }

// public String toString() {
// if (duration > 0)
// return "ON";
// return "OFF";
// }
// }

// public class Main {
// private double myGPA;
// private int myCredits;

// public void increaseCredits(int credits) {
// myCredits += credits;
// }

// public void setCredits(int credits) {
// myCredits = credits;
// }

// public int getCredits() {
// return myCredits;
// }

// public static void main(String args[]) {
// Main s = new Main();
// s.setCredits(6);
// s.increaseCredits(12);
// System.out.println(s.getCredits());
// }
// }

// public class Main {
// private int odometer;

// public Main() {
// odometer = 0;
// }

// public void showOdometer() {
// System.out.print(odometer);
// }

// public Main(int miles) {
// odometer = miles;
// }

// public void drive(int miles) {
// odometer = odometer + miles;
// }

// public static void main(String[] args) {
// Main fordFusion = new Main();
// Main hondaAccord = new Main(30);
// fordFusion = hondaAccord;
// hondaAccord.drive(100);
// fordFusion.drive(20);
// fordFusion.showOdometer();
// }
// }

// public class Main {
//   private static int val;

//   private void method1() {
//     method2();
//     val = 1;
//   }

//   public static void main(String args[]) {
//     Main q = new Main();
//     q.method1();
//     method2();
//   }

//   private static void method2() {
//     System.out.print((new Main()).val + " " + new Main());
//   }

//   public Main() {
//     val = 5;
//   }

//   public String toString() {
//     return "6";
//   }
// }

public class Main {
  private static String name;

  public static void main(String args[]) {
    name = "Wardra";
    Main.changeName();
    System.out.print(name);
  }

  private static void changeName() {
    name = "Zahra";
    System.out.print(name);
  }
}