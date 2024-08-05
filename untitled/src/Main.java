import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Student {
    private String name;
    private int age;
    private String email;
    private String phone;
    private String code;
    private int gender; // 0 for male, 1 for female
    private float grade;

    public Student(String name, int age, String email, String phone, String code, int gender, float grade) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.code = code;
        this.gender = gender;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public float getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", code='" + code + '\'' +
                ", gender=" + (gender == 0 ? "Male" : "Female") +
                ", grade=" + grade +
                '}';
    }
}

public class Main {
    static List<Student> studentList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add new student");
            System.out.println("2. Display all students");
            System.out.println("3. Remove student by code");
            System.out.println("4. Sort students by descending grade");
            System.out.println("5. Find student by code or name");
            System.out.println("6. Find students with grade >= x");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    input();
                    break;
                case 2:
                    output();
                    break;
                case 3:
                    System.out.println("Enter student code to remove:");
                    String code = scanner.nextLine();
                    removeByCode(code);
                    break;
                case 4:
                    sortByGradeDesc();
                    break;
                case 5:
                    System.out.println("Enter student code or name to find:");
                    String keyword = scanner.nextLine();
                    Student foundStudent = findByCodeOrName(keyword);
                    if (foundStudent != null) {
                        System.out.println("Found: " + foundStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 6:
                    System.out.println("Enter grade threshold:");
                    float x = scanner.nextFloat();
                    scanner.nextLine(); // consume newline
                    List<Student> filteredStudents = filterByGrade(x);
                    System.out.println("Students with grade >= " + x + ":");
                    for (Student student : filteredStudents) {
                        System.out.println(student);
                    }
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Add new student
    public static void input() {
        System.out.println("Enter student information:");

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Code: ");
        String code = scanner.nextLine();

        System.out.print("Gender (0 for Male, 1 for Female): ");
        int gender = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Grade: ");
        float grade = scanner.nextFloat();
        scanner.nextLine(); // consume newline

        Student student = new Student(name, age, email, phone, code, gender, grade);
        studentList.add(student);

        System.out.println("Student added successfully.");
    }

    // Display all students
    public static void output() {
        System.out.println("Student List:");
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    // Remove student by code
    public static void removeByCode(String code) {
        studentList.removeIf(student -> student.getCode().equals(code));
        System.out.println("Student with code " + code + " removed.");
    }

    // Sort students by descending grade
    public static void sortByGradeDesc() {
        studentList.sort(Comparator.comparing(Student::getGrade).reversed());
        System.out.println("Students sorted by descending grade.");
    }

    // Find student by code or name
    public static Student findByCodeOrName(String keyword) {
        return studentList.stream()
                .filter(student -> student.getCode().equalsIgnoreCase(keyword) || student.getName().equalsIgnoreCase(keyword))
                .findFirst()
                .orElse(null);
    }

    // Find students with grade >= x
    public static List<Student> filterByGrade(float x) {
        List<Student> filteredStudents = new ArrayList<>();
        for (Student student : studentList) {
            if (student.getGrade() >= x) {
                filteredStudents.add(student);
            }
        }
        return filteredStudents;
    }
}

