import java.util.Scanner;

public class GradeManagementSystem {
    private static final int MAX_STUDENTS = 100;
    private static final String[] SUBJECTS = {
        "Mathematics", "Science", "English", "History", "Computer"
    };
    
    private static final StudentGrade[] students = new StudentGrade[MAX_STUDENTS];
    private static final Scanner scanner = new Scanner(System.in);
    private static int studentCount;
    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== GRADE MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Student Marks");
            System.out.println("2. View All Students");
            System.out.println("3. Calculate Averages");
            System.out.println("4. Find Top Performers");
            System.out.println("5. Generate Report");
            System.out.println("6. Exit");
            int choice = readInt("Enter your choice: ", 1, 6);

            switch (choice) {
                case 1:
                    addStudentMarks();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    calculateAverages();
                    break;
                case 4:
                    findTopPerformers();
                    break;
                case 5:
                    generateReport();
                    break;
                case 6:
                    running = false;
                    System.out.println("Thank you for using Grade Management System!");
                    break;
                default:
                    throw new IllegalStateException("Unexpected menu choice: " + choice);
            }
        }

        scanner.close();
    }

    private static void addStudentMarks() {
        if (studentCount >= MAX_STUDENTS) {
            System.out.println("Maximum student limit reached!");
            return;
        }

        System.out.println("\n=== ADD STUDENT MARKS ===");
        String name;
        do {
            System.out.print("Enter Student Name: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Student name cannot be empty.");
            }
        } while (name.isEmpty());

        double[] marks = new double[SUBJECTS.length];
        System.out.println("\nEnter marks for " + SUBJECTS.length + " subjects (out of 100):");
        for (int i = 0; i < SUBJECTS.length; i++) {
            marks[i] = readMark(SUBJECTS[i] + ": ");
        }

        students[studentCount++] = new StudentGrade(name, marks);
        System.out.println("Student marks added successfully!");
    }

    private static void viewAllStudents() {
        System.out.println("\n=== ALL STUDENTS ===");
        if (!hasStudents()) {
            return;
        }

        System.out.printf("%-20s %-12s %-12s %-12s %-12s %-12s %-12s %-8s%n",
                "Student Name", "Math", "Science", "English", "History",
                "Computer", "Average", "Grade");
        System.out.println("------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < studentCount; i++) {
            StudentGrade student = students[i];
            double average = GradeCalculator.calculateAverage(student);
            System.out.printf("%-20s %-12.2f %-12.2f %-12.2f %-12.2f %-12.2f %-12.2f %-8s%n",
                    student.getName(),
                    student.getMark(0), student.getMark(1), student.getMark(2),
                    student.getMark(3), student.getMark(4), average,
                    GradeCalculator.getGrade(average));
        }
    }

    private static void calculateAverages() {
        System.out.println("\n=== STUDENT AVERAGES ===");
        if (!hasStudents()) {
            return;
        }

        for (int i = 0; i < studentCount; i++) {
            StudentGrade student = students[i];
            double average = GradeCalculator.calculateAverage(student);
            System.out.printf("%-20s: Average = %.2f, Grade = %s%n",
                    student.getName(), average, GradeCalculator.getGrade(average));
        }
    }

    private static void findTopPerformers() {
        System.out.println("\n=== TOP PERFORMERS ===");
        if (!hasStudents()) {
            return;
        }

        double highestAverage = GradeCalculator.calculateAverage(students[0]);
        for (int i = 1; i < studentCount; i++) {
            highestAverage = Math.max(highestAverage, GradeCalculator.calculateAverage(students[i]));
        }

        System.out.printf("Highest average: %.2f%n", highestAverage);
        for (int i = 0; i < studentCount; i++) {
            StudentGrade student = students[i];
            double average = GradeCalculator.calculateAverage(student);
            if (Double.compare(average, highestAverage) == 0) {
                System.out.printf("%s (Grade %s)%n", student.getName(), GradeCalculator.getGrade(average));
            }
        }
    }

    private static void generateReport() {
        System.out.println("\n=== PERFORMANCE REPORT ===");
        if (hasStudents()) {
            ReportGenerator.generate(students, studentCount, SUBJECTS);
        }
    }

    private static boolean hasStudents() {
        if (studentCount == 0) {
            System.out.println("No students found!");
            return false;
        }
        return true;
    }

    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("Please enter a number between %d and %d.%n", min, max);
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input! Please enter a whole number.");
            }
        }
    }

    private static double readMark(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double mark = Double.parseDouble(input);
                if (Double.isFinite(mark) && mark >= 0 && mark <= 100) {
                    return mark;
                }
                System.out.println("Marks must be between 0 and 100.");
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }
}
