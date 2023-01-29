import java.util.Random;
import java.util.Scanner;

class Book {
  protected String author = "";
  protected String title = "";
  protected String isbn = "";

  public Book(String author, String title, String isbn) {
    this.author = author.toUpperCase();
    this.title = title.toUpperCase();
    this.isbn = isbn;
  }

  public Book(String author, String title) {
    this.author = author.toUpperCase();
    this.title = title.toUpperCase();
  }

  public Book() {
  };

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getAuthor() {
    return author;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getIsbn() {
    return isbn;
  }
}

class BookstoreBook extends Book {
  private double deductionPercentage;
  private double price;
  static int count = 0;

  public BookstoreBook(String author, String title, String isbn, double deductionPercentage) {
    super(author, title, isbn);
    this.deductionPercentage = deductionPercentage;
    count++;
  }

  public BookstoreBook(String author, String title, String isbn) {
    super(author, title, isbn);
    this.deductionPercentage = 0;
    count++;
  }

  public BookstoreBook() {
    super();
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public void setPrice(String price) {
    this.price = Double.parseDouble(price);
  }

  public double getPrice() {
    return price;
  }

  public void setDeductionPercentage(double deductionPercentage) {
    this.deductionPercentage = deductionPercentage;
  }

  public void setDeductionPercentage(String deductionPercentage) {
    deductionPercentage = deductionPercentage.replace("%", "");
    this.deductionPercentage = Double.parseDouble(deductionPercentage) / 100;
  }

  public double getDeductionPercentage() {
    return deductionPercentage;
  }

  public double getListedPrice() {
    return price * (1 - deductionPercentage);
  }

  @Override
  public String toString() {
    return "[" + getIsbn() + "-" + getTitle() + " by " + getAuthor() + ", $" + getPrice() + " listed for $"
        + String.format("%.2f", getListedPrice())
        + "]";
  }
}

class LibraryBook extends Book {
  private String callNumber = "";
  static int count = 0;

  public LibraryBook(String author, String title, String isbn) {
    super(author, title, isbn);
    setCallNumber();
    count++;
  }

  public LibraryBook(String author, String title) {
    super(author, title);
    count++;
  }

  public LibraryBook() {
    super();
    count++;
  }

  private void setCallNumber() {
    if (author.trim().isEmpty() || title.trim().isEmpty() || isbn.trim().isEmpty()) {
      callNumber = "N/A";
    } else {
      Random rand = new Random();
      callNumber = String.format("%2d", rand.nextInt(98) + 1) + "." + author.substring(0, 3) + "."
          + isbn.substring(isbn.length() - 1);
    }
  }

  public String getCallNumber() {
    return callNumber;
  }

  @Override
  public void setAuthor(String author) {
    super.setAuthor(author);
    setCallNumber();
  }

  @Override
  public void setIsbn(String isbn) {
    super.setIsbn(isbn);
    setCallNumber();
  }

  @Override
  public String toString() {
    return "[" + getIsbn() + "-" + getTitle() + " by " + getAuthor() + "-" + getCallNumber() + "]";
  }
}

public class Main {

  public static void main(String[] args) {
    BookstoreBook[] BBArray = new BookstoreBook[100];
    LibraryBook[] LBArray = new LibraryBook[200];
    Scanner sc = new Scanner(System.in);

    System.out.println("Welcome to the book program!");

    while (true) {
      String q = "";

      // Ask user whether they want to create book
      System.out.print("Would you like to create a book object? (yes/no): ");
      q = sc.nextLine().toLowerCase();
      while (!q.equals("yes") && !q.equals("no")) {
        System.out.print("I'm sorry but " + q + " isn't a valid answer. Please enter either yes or no: ");
        q = sc.nextLine().toLowerCase();
      }
      if (q.equals("no")) {
        break;
      }

      // Ask user title and author
      System.out.print("\nPlease enter the author, title ad the isbn of the book separated by /: ");
      String[] arrInp = sc.nextLine().split("/");
      System.out.println("Got it!");

      // Ask user whether they want to create a bookstore book or library book
      System.out.print(
          "Now, tell me if it is a bookstore book or a library book (enter BB for bookstore book or LB for library book): ");
      q = sc.nextLine().toLowerCase();
      while (!q.equals("bb") && !q.equals("lb")) {
        System.out.print("Oops! That's not a valid entry. Please try again: ");
        q = sc.nextLine().toLowerCase();
      }
      System.out.println("Got it!");

      // Create book store book
      if (q.equals("bb")) {
        BookstoreBook bb = new BookstoreBook(arrInp[0], arrInp[1], arrInp[2]);
        BBArray[BookstoreBook.count - 1] = bb;
        System.out.print("Please enter the list price of " + bb.getTitle() + " by " + bb.getAuthor() + ": ");
        String listPrice = sc.nextLine();
        bb.setPrice(listPrice);
        System.out.print("Is it on sale? (y/n): ");
        q = sc.nextLine();
        while (!q.toLowerCase().equals("y") && !q.toLowerCase().equals("n")) {
          System.out.print("Oops! That's not a valid entry. Please try again: ");
          q = sc.nextLine();
        }
        if (q.toLowerCase().equals("y")) {
          System.out.print("Deduction percentage: ");
          String salePercentage = sc.nextLine();
          bb.setDeductionPercentage(salePercentage);
        } else {
          bb.setDeductionPercentage(0);
        }
        System.out.println("Got it!");
        System.out.println("\nHere is your bookstore book information");
        System.out.println(bb.toString() + "\n");
      } else {
        // Create library book
        LibraryBook lb = new LibraryBook(arrInp[0], arrInp[1], arrInp[2]);
        LBArray[LibraryBook.count - 1] = lb;
        System.out.println("\nHere is your bookstore book information");
        System.out.println(lb.toString() + "\n");
      }
    }

    System.out.println("Sure!");
    System.out.println("Here are all your books...");
    System.out.println("Library Books (" + LibraryBook.count + ")");
    for (int i = 0; i < LibraryBook.count; i++) {
      System.out.println("\t" + LBArray[i].toString());
    }
    System.out.println("____");

    System.out.println("Bookstore Books (" + BookstoreBook.count + ")");
    for (int i = 0; i < BookstoreBook.count; i++) {
      System.out.println("\t" + BBArray[i].toString());
    }
    System.out.println("____");
    System.out.println("Take care now!");

    sc.close();
  }
}