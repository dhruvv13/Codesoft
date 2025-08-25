import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class Main extends Application {

    private StudentManagementSystem sms;
    private TextField nameField, rollNumberField, gradeField;
    private TextArea displayArea;

    @Override
    public void start(Stage primaryStage) {
        sms = new StudentManagementSystem();

        // Main layout
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.setStyle("-fx-font-size: 14;");

        // Input form
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.addRow(0, new Label("Name:"), nameField = new TextField());
        form.addRow(1, new Label("Roll Number:"), rollNumberField = new TextField());
        form.addRow(2, new Label("Grade:"), gradeField = new TextField());

        // Buttons
        HBox buttons = new HBox(10);
        Button addButton = new Button("Add Student");
        Button removeButton = new Button("Remove Student");
        Button searchButton = new Button("Search Student");
        Button displayAllButton = new Button("Display All Students");
        buttons.getChildren().addAll(addButton, removeButton, searchButton, displayAllButton);

        // Display area
        displayArea = new TextArea();
        displayArea.setEditable(false);
        displayArea.setPrefHeight(200);

        root.getChildren().addAll(new Label("Student Management System"), form, buttons, displayArea);

        // Event handlers
        addButton.setOnAction(e -> addStudent());
        removeButton.setOnAction(e -> removeStudent());
        searchButton.setOnAction(e -> searchStudent());
        displayAllButton.setOnAction(e -> displayAllStudents());

        Scene scene = new Scene(root, 400, 500);
        // Link the CSS file to the scene
        scene.getStylesheets().add("styles.css");

        primaryStage.setTitle("Student Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addStudent() {
        String name = nameField.getText();
        String rollNumber = rollNumberField.getText();
        String grade = gradeField.getText();

        if (!name.isEmpty() && !rollNumber.isEmpty() && !grade.isEmpty()) {
            Student newStudent = new Student(name, rollNumber, grade);
            sms.addStudent(newStudent);
            clearFields();
            displayMessage("Student added successfully!");
        } else {
            displayMessage("Error: All fields must be filled.");
        }
    }

    private void removeStudent() {
        String rollNumber = rollNumberField.getText();
        if (!rollNumber.isEmpty()) {
            sms.removeStudent(rollNumber);
            clearFields();
            displayMessage("Student removed (if they existed).");
        } else {
            displayMessage("Error: Please enter a roll number to remove.");
        }
    }

    private void searchStudent() {
        String rollNumber = rollNumberField.getText();
        if (!rollNumber.isEmpty()) {
            Student student = sms.searchStudent(rollNumber);
            if (student != null) {
                displayMessage("Student Found:\n" + student.toString());
            } else {
                displayMessage("Student with roll number " + rollNumber + " not found.");
            }
        } else {
            displayMessage("Error: Please enter a roll number to search.");
        }
    }

    private void displayAllStudents() {
        List<Student> allStudents = sms.getAllStudents();
        if (allStudents.isEmpty()) {
            displayMessage("No students in the system.");
        } else {
            StringBuilder sb = new StringBuilder("All Students:\n");
            for (Student student : allStudents) {
                sb.append(student.toString()).append("\n");
            }
            displayMessage(sb.toString());
        }
    }

    private void clearFields() {
        nameField.clear();
        rollNumberField.clear();
        gradeField.clear();
    }

    private void displayMessage(String message) {
        displayArea.setText(message);
    }

    public static void main(String[] args) {
        launch(args);
    }
}