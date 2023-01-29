public class Main {
  public static void main(String args[]) {
    int n = 3, x = 10;
    do {
      n = n + 1;
      if (n > 7)
        System.out.print(n);
    } while (n > -1 || x < 5);
    System.out.print("DONE");
  }
}