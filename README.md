# Grade Management System

## Project Overview

The Grade Management System is a menu-driven console application for managing student marks across five subjects: Mathematics, Science, English, History, and Computer. It calculates each student's average and letter grade, identifies top performers, and summarizes class performance.

The application uses arrays to store up to 100 students during a single run. Student records are held in memory and are not saved after the program exits. Input is validated: names cannot be blank, menu selections must be in range, and marks must be between 0 and 100.

### Features

- Add student names and marks for all five subjects.
- View student marks, averages, and grades.
- Find all students tied for the highest average.
- Generate a report with class and subject averages, highest and lowest averages, and grade distribution.

### Project files

- `GradeManagementSystem.java` — menu, input validation, and application flow.
- `StudentGrade.java` — student name and subject marks.
- `GradeCalculator.java` — average and grade calculations.
- `ReportGenerator.java` — class performance report.

## Setup

### Requirements

- Java Development Kit (JDK) 8 or later
- A terminal or command prompt

### Compile

Open a terminal in the project directory (the directory containing the `.java` files), then compile:

```sh
javac GradeManagementSystem.java StudentGrade.java GradeCalculator.java ReportGenerator.java
```

The compiler creates the `.class` files in the current directory.

### Run

Start the program from the same directory:

```sh
java GradeManagementSystem
```

## Using the menu

1. **Add Student Marks** — enter a student name and marks for Mathematics, Science, English, History, and Computer.
2. **View All Students** — display each student's marks, average, and grade.
3. **Calculate Averages** — display the average and grade for each student.
4. **Find Top Performers** — show all students tied for the highest average.
5. **Generate Report** — show class and subject averages, highest and lowest averages, and grade distribution.
6. **Exit** — close the application.

Names cannot be blank. Marks must be numeric values from 0 to 100, inclusive. The application accepts up to 100 students per run.

## Grade scale

| Average | Grade |
| --- | --- |
| 90–100 | A+ |
| 80–89.99 | A |
| 70–79.99 | B |
| 60–69.99 | C |
| 50–59.99 | D |
| Below 50 | F |
