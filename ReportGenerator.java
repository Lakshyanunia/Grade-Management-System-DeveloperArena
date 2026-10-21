public final class ReportGenerator {
    private static final String[] GRADE_CATEGORIES = {"A+", "A", "B", "C", "D", "F"};

    private ReportGenerator() {
    }

    public static void generate(StudentGrade[] students, int studentCount, String[] subjects) {
        if (students == null || subjects == null) {
            throw new IllegalArgumentException("Students and subjects are required.");
        }
        if (studentCount < 1 || studentCount > students.length) {
            throw new IllegalArgumentException("Student count is outside the available array bounds.");
        }
        if (subjects.length == 0) {
            throw new IllegalArgumentException("At least one subject is required.");
        }

        double totalAverage = 0;
        double highestAverage = -1;
        double lowestAverage = 101;
        String highestStudent = "";
        String lowestStudent = "";
        double[] subjectTotals = new double[subjects.length];
        int[] gradeCounts = new int[GRADE_CATEGORIES.length];

        for (int i = 0; i < studentCount; i++) {
            StudentGrade student = students[i];
            if (student == null || student.getSubjectCount() != subjects.length) {
                throw new IllegalArgumentException("Student data does not match the subject list.");
            }

            double average = GradeCalculator.calculateAverage(student);
            totalAverage += average;
            if (average > highestAverage) {
                highestAverage = average;
                highestStudent = student.getName();
            }
            if (average < lowestAverage) {
                lowestAverage = average;
                lowestStudent = student.getName();
            }

            String grade = GradeCalculator.getGrade(average);
            for (int j = 0; j < GRADE_CATEGORIES.length; j++) {
                if (GRADE_CATEGORIES[j].equals(grade)) {
                    gradeCounts[j]++;
                    break;
                }
            }
            for (int j = 0; j < subjects.length; j++) {
                subjectTotals[j] += student.getMark(j);
            }
        }

        System.out.println("Students: " + studentCount);
        System.out.printf("Class average: %.2f%n", totalAverage / studentCount);
        System.out.printf("Highest average: %.2f (%s)%n", highestAverage, highestStudent);
        System.out.printf("Lowest average: %.2f (%s)%n", lowestAverage, lowestStudent);

        System.out.println("\nSubject averages:");
        for (int i = 0; i < subjects.length; i++) {
            System.out.printf("%-15s %.2f%n", subjects[i], subjectTotals[i] / studentCount);
        }

        System.out.println("\nGrade distribution:");
        for (int i = 0; i < GRADE_CATEGORIES.length; i++) {
            System.out.printf("%-3s %d%n", GRADE_CATEGORIES[i], gradeCounts[i]);
        }
    }
}
