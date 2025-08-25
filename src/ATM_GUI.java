import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ATM_GUI extends Application {

    private BankAccount account;
    private TextField amountField;
    private Label messageLabel;

    @Override
    public void start(Stage primaryStage) {
        account = new BankAccount(1000.00); // Initialize with a starting balance of $1000

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.getStyleClass().add("root");

        // UI Components
        Label titleLabel = new Label("ATM Interface");
        titleLabel.setId("title-label");
        grid.add(titleLabel, 0, 0, 2, 1);

        Label balanceLabel = new Label("Current Balance: $" + account.getBalance());
        balanceLabel.setId("balance-label");
        grid.add(balanceLabel, 0, 1, 2, 1);

        Label amountLabel = new Label("Enter Amount:");
        amountLabel.getStyleClass().add("label");
        grid.add(amountLabel, 0, 2);

        amountField = new TextField();
        amountField.getStyleClass().add("text-field");
        grid.add(amountField, 1, 2);

        Button withdrawButton = new Button("Withdraw");
        Button depositButton = new Button("Deposit");
        Button checkBalanceButton = new Button("Check Balance");
        withdrawButton.getStyleClass().add("button");
        depositButton.getStyleClass().add("button");
        checkBalanceButton.getStyleClass().add("button");

        grid.add(withdrawButton, 0, 3);
        grid.add(depositButton, 1, 3);
        grid.add(checkBalanceButton, 0, 4, 2, 1);

        messageLabel = new Label("");
        messageLabel.setId("message-label");
        grid.add(messageLabel, 0, 5, 2, 1);

        // Event Handlers
        withdrawButton.setOnAction(e -> handleWithdraw(balanceLabel));
        depositButton.setOnAction(e -> handleDeposit(balanceLabel));
        checkBalanceButton.setOnAction(e -> handleCheckBalance(balanceLabel));

        Scene scene = new Scene(grid, 400, 400);
        scene.getStylesheets().add("atm_styles.css");
        primaryStage.setTitle("ATM");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleWithdraw(Label balanceLabel) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            account.withdraw(amount);
            balanceLabel.setText("Current Balance: $" + String.format("%.2f", account.getBalance()));
            messageLabel.setText("Withdrawal successful.");
        } catch (NumberFormatException e) {
            messageLabel.setText("Invalid amount.");
        }
        amountField.clear();
    }

    private void handleDeposit(Label balanceLabel) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            account.deposit(amount);
            balanceLabel.setText("Current Balance: $" + String.format("%.2f", account.getBalance()));
            messageLabel.setText("Deposit successful.");
        } catch (NumberFormatException e) {
            messageLabel.setText("Invalid amount.");
        }
        amountField.clear();
    }

    private void handleCheckBalance(Label balanceLabel) {
        balanceLabel.setText("Current Balance: $" + String.format("%.2f", account.getBalance()));
        messageLabel.setText("Balance checked.");
        amountField.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}