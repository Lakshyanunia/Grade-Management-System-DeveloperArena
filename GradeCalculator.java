public final class GradeCalculator {
    private GradeCalculator() {
    }

    public static double calculateAverage(StudentGrade student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }

        double total = 0;
        for (int i = 0; i < student.getSubjectCount(); i++) {
            total += student.getMark(i);
        }
        return total / student.getSubjectCount();
    }

    public static String getGrade(double average) {
        if (!Double.isFinite(average) || average < 0 || average > 100) {
            throw new IllegalArgumentException("Average must be between 0 and 100.");
        }
        if (average >= 90) {
            return "A+";
        } else if (average >= 80) {
            return "A";
        } else if (average >= 70) {
            return "B";
        } else if (average >= 60) {
            return "C";
        } else if (average >= 50) {
            return "D";
        }
        return "F";
    }
}
