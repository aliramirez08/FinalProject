import java.io.*;
import java.util.*;

class Student {
    private String name;
    private String address;
    private double gpa;

    public Student(String name, String address, double gpa) {
        this.name = name;
        this.address = address;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public double getGpa() {
        return gpa;
    }
    
    // Returns a formatted string for a table row
    public String getFormattedString() {
        return String.format("%-15s %-30s %-5.2f", name, address, gpa);
    }
}

public class StudentDataEntry {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList<Student> students = new LinkedList<>();

        while (true) {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter student address: ");
            String address = scanner.nextLine().trim();

            double gpa;
            while (true) {
                System.out.print("Enter student GPA: ");
                try {
                    gpa = Double.parseDouble(scanner.nextLine().trim());
                    break;
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid input. Please enter a numeric GPA.");
                }
            }

            students.add(new Student(name, address, gpa));

            System.out.print("Add another student? (yes/no): ");
            String answer = scanner.nextLine().trim().toLowerCase();
            if (!answer.equals("yes")) {
                break;
            }
        }

        // Sort the student list in ascending order by name
        students.sort(Comparator.comparing(Student::getName));

        // Write the sorted data to a text file with custom formatting
        try (PrintWriter writer = new PrintWriter(new FileWriter("students.txt"))) {
            writer.println("=========================================");
            writer.println("              Student Records");
            writer.println("=========================================");
            writer.printf("%-15s %-30s %-5s\n", "Name", "Address", "GPA");
            writer.println("-----------------------------------------");
            
            for (Student student : students) {
                writer.println(student.getFormattedString());
            }
            
            System.out.println("Student data has been written to 'students.txt'.");
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
        
        scanner.close();
    }
}
