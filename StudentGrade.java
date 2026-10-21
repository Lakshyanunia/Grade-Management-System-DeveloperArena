public class StudentGrade {
    private final String name;
    private final double[] marks;

    public StudentGrade(String name, double[] marks) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty.");
        }
        if (marks == null || marks.length == 0) {
            throw new IllegalArgumentException("At least one mark is required.");
        }

        this.name = name.trim();
        this.marks = marks.clone();
        for (double mark : this.marks) {
            if (!Double.isFinite(mark) || mark < 0 || mark > 100) {
                throw new IllegalArgumentException("Marks must be between 0 and 100.");
            }
        }
    }

    public String getName() {
        return name;
    }

    public int getSubjectCount() {
        return marks.length;
    }

    public double getMark(int subjectIndex) {
        return marks[subjectIndex];
    }
}
