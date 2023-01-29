import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

class CourseLocation {
  private int crn;
  private String location;

  public CourseLocation(int crn, String location) {
    this.crn = crn;
    this.location = location;
  }

  public int getCrn() {
    return crn;
  }

  public void setCrn(int crn) {
    this.crn = crn;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }
}

class Lecture extends CourseLocation {
  private String prefix;
  private String title;
  private boolean isGraduate;
  private String modality;
  private boolean hasLab;

  public Lecture(int crn, String prefix, String title, boolean isGraduate, String modality, String location,
      boolean hasLab) {
    super(crn, location);
    this.prefix = prefix;
    this.title = title;
    this.isGraduate = isGraduate;
    this.modality = modality;
    this.hasLab = hasLab;
  }

  public Lecture(int crn, String prefix, String title, boolean isGraduate) {
    super(crn, "");
    this.prefix = prefix;
    this.title = title;
    this.isGraduate = isGraduate;
    this.modality = "Online";
    this.hasLab = false;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean isGraduate() {
    return isGraduate;
  }

  public void setGraduate(boolean graduate) {
    isGraduate = graduate;
  }

  public String getModality() {
    return modality;
  }

  public void setModality(String modality) {
    this.modality = modality;
  }

  public boolean getHasLab() {
    return hasLab;
  }

  public void setHasLab(boolean hasLab) {
    this.hasLab = hasLab;
  }

  @Override
  public String toString() {
    if (modality.equals("Online"))
      return String.join(",", String.valueOf(getCrn()), getPrefix(), getTitle(),
          isGraduate() ? "Graduate" : "Undergraduate",
          "Online");
    return String.join(",", String.valueOf(getCrn()), getPrefix(), getTitle(),
        isGraduate() ? "Graduate" : "Undergraduate",
        getModality(), getLocation(), getHasLab() ? "Yes" : "No");
  }
}

public class Main {
  public static void main(String[] args) {
    ArrayList<CourseLocation> list = new ArrayList<>();
    ArrayList<Integer> onlineLecture = new ArrayList<>();
    ArrayList<Integer> noLabLecture = new ArrayList<>();

    // Read from file
    try (Scanner input = new Scanner(new File("lec.txt"))) {
      while (input.hasNext()) {
        String line = input.nextLine();
        String[] tokens = line.split(",");

        // Trim all arguments
        for (int i = 0; i < tokens.length; i++) {
          tokens[i] = tokens[i].trim();
        }

        if (tokens.length == 7) {
          // If has lab
          if (tokens[6].equals("Yes")) {
            list.add(new Lecture(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3].equals("Graduate"),
                tokens[4], tokens[5], true));
          } else {
            list.add(new Lecture(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3].equals("Graduate"),
                tokens[4], tokens[5], false));
            // Add index to noLabLecture
            noLabLecture.add(list.size() - 1);
          }
        } else if (tokens.length == 5) {
          list.add(new Lecture(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3].equals("Graduate")));
          // Add index to onlineLecture
          onlineLecture.add(list.size() - 1);
        } else {
          list.add(new CourseLocation(Integer.parseInt(tokens[0]), tokens[1]));
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    System.out.println("- There are " + onlineLecture.size() + " online lectures offered");
    System.out.print("- Enter the classroom: ");
    Scanner input = new Scanner(System.in);
    String location = input.nextLine();
    input.close();
    System.out.print("\tThe crns held in " + location + " are: ");
    ArrayList<String> crns = new ArrayList<>();
    for (CourseLocation courseLocation : list) {
      if (courseLocation.getLocation().equals(location)) {
        crns.add(String.valueOf(courseLocation.getCrn()));
      }
    }
    System.out.println(String.join(", ", crns));

    // Print into file
    try (PrintWriter output = new PrintWriter(new File("lecturesOnly.txt"))) {
      output.println("Online Lectures");
      for (Integer index : onlineLecture) {
        Lecture lecture = (Lecture) list.get(index);
        output.println(lecture.toString());
      }

      output.println("\nLectures with No Labs");
      for (Integer index : noLabLecture) {
        Lecture lecture = (Lecture) list.get(index);
        output.println(lecture.toString());
      }

      System.out.println("- lecturesOnly.txt is created");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
