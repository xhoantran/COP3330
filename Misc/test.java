class statictest {
  private int num;
  private static int value = 0;

  public statictest(int x) {
    num = x;
    value++;
  }

  public int numObjectsCreated() {
    return value;
  }

  public void increment() {
    num++;
  }

  public String toString() {
    return "num = "+num+" value = "+value;
  }

}

public class test {
  public static void main(String[] args) {
    statictest first = new statictest(3);
    statictest second = new statictest(10);
    first.increment();
    System.out.println(first);
    System.out.println(second);
  }
}