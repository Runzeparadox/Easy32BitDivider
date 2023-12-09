// DivisionSimulatorUI.java
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DivisionSimulatorUI {

    private TextField dividendField;
    private TextField divisorField;
    private Button calculateButton;
    private Button timeButton;
    private DivisionCalculator calculator;
    private CanvasDrawer canvasDrawer;

    public void initialize(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        Label dividendLabel = new Label("被除数:");
        GridPane.setConstraints(dividendLabel, 0, 0);

        dividendField = new TextField();
        GridPane.setConstraints(dividendField, 1, 0);

        Label divisorLabel = new Label("除数:");
        GridPane.setConstraints(divisorLabel, 0, 1);

        divisorField = new TextField();
        GridPane.setConstraints(divisorField, 1, 1);

        calculateButton = new Button("计算");
        calculateButton.setOnAction(e -> performDivision());
        GridPane.setConstraints(calculateButton, 1, 2);

        timeButton = new Button("显示时间");
        timeButton.setOnAction(e -> displayCurrentTime());
        GridPane.setConstraints(timeButton, 2, 2);

        gridPane.getChildren().addAll(dividendLabel, dividendField, divisorLabel, divisorField, calculateButton, timeButton);

        canvasDrawer = new CanvasDrawer();
        borderPane.setTop(gridPane);
        borderPane.setCenter(canvasDrawer.getCanvas());

        Scene scene = new Scene(borderPane, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        calculator = new DivisionCalculator();
    }

    private void performDivision() {
        try {
            long dividend = Long.parseLong(dividendField.getText());
            long divisor = Long.parseLong(divisorField.getText());

            calculator.calculate(dividend, divisor, canvasDrawer);
        } catch (NumberFormatException ex) {
            showAlert("请输入有效的数字！");
        }
    }

    private void displayCurrentTime() {
        canvasDrawer.displayCurrentTime();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("错误");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
