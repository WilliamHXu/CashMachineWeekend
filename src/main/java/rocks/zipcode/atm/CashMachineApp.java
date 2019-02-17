package rocks.zipcode.atm;

import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {

    private TextField field = new TextField();
    private TextField accountIDField = new TextField();
    private TextField balanceField = new TextField();
    private TextField messageField = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());
    private boolean loggedIn = false;

    private Parent createContent() {
        VBox vbox = new VBox(10);
        vbox.setPrefSize(600, 600);

        TextArea areaInfo = new TextArea();



        Button btnDeposit = new Button("Deposit");
        btnDeposit.setDisable(!loggedIn);
        btnDeposit.setOnAction(e -> {
            Double amount = Double.parseDouble(field.getText());
            cashMachine.deposit(amount);

            balanceField.setText(cashMachine.toStringBalance());
        });

        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setDisable(!loggedIn);
        btnWithdraw.setOnAction(e -> {
            Double amount = Double.parseDouble(field.getText());
            cashMachine.withdraw(amount);

            balanceField.setText(cashMachine.toStringBalance());
            messageField.setText(cashMachine.toStringMessage());
        });

        Button btnExit = new Button("Exit");
        btnExit.setDisable(!loggedIn);
        btnExit.setOnAction(e -> {
            loggedIn = false;
            cashMachine.exit();

        });

        Button btnSubmit = new Button("Login");
        btnSubmit.setOnAction(e -> {
            int id = Integer.parseInt(field.getText());
            cashMachine.login(id);
            loggedIn = true;
            btnDeposit.setDisable(!loggedIn);
            btnWithdraw.setDisable(!loggedIn);
            btnExit.setDisable(!loggedIn);

            accountIDField.setText(cashMachine.toStringAccountInfo());
        });
        FlowPane flowpane = new FlowPane();

        flowpane.getChildren().add(btnSubmit);
        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnExit);
        vbox.getChildren().addAll(field, flowpane, accountIDField, balanceField, messageField, areaInfo);
        return vbox;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
