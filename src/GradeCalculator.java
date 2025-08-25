import java.util.Scanner;

public class GradeCalculator {

    public static void main(String[] args) {
        // Create a Scanner object to read input from the user
        Scanner scanner = new Scanner(System.in);

        // Task 1: Take input for the number of subjects
        System.out.print("Enter the number of subjects: ");
        int numberOfSubjects = scanner.nextInt();

        // Initialize total marks
        int totalMarks = 0;

        // Task 1: Take marks for each subject
        for (int i = 1; i <= numberOfSubjects; i++) {
            System.out.print("Enter marks obtained in subject " + i + " (out of 100): ");
            int marks = scanner.nextInt();
            totalMarks += marks;
        }

        // Task 2: Calculate total marks
        System.out.println("---------------------------------");
        System.out.println("Total Marks: " + totalMarks);

        // Task 3: Calculate average percentage
        double averagePercentage = (double) totalMarks / numberOfSubjects;
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);

        // Task 4: Assign grades
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        // Task 5: Display results
        System.out.println("Grade: " + grade);

        // Close the scanner to prevent resource leaks
        scanner.close();
    }
}