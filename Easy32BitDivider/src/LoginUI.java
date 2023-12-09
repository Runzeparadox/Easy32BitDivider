import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginUI {

    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private Label timeLabel;

    public void showLoginDialog() {
        Stage loginStage = new Stage();
        loginStage.setTitle("登录");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        Label usernameLabel = new Label("用户名:");
        GridPane.setConstraints(usernameLabel, 0, 0);

        usernameField = new TextField();
        GridPane.setConstraints(usernameField, 1, 0);

        Label passwordLabel = new Label("密码:");
        GridPane.setConstraints(passwordLabel, 0, 1);

        passwordField = new PasswordField();
        GridPane.setConstraints(passwordField, 1, 1);

        loginButton = new Button("登录");
        loginButton.setOnAction(e -> attemptLogin());
        GridPane.setConstraints(loginButton, 1, 2);

        timeLabel = new Label();
        GridPane.setConstraints(timeLabel, 0, 3, 2, 1);

        gridPane.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton, timeLabel);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateTime();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Scene scene = new Scene(gridPane, 500, 700);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        loginStage.setScene(scene);
        loginStage.setResizable(false);
        loginStage.showAndWait();
    }

    private void attemptLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (isValidLogin(username, password)) {
            ((Stage) usernameField.getScene().getWindow()).close();
            // 启动主应用
            startMainApp();
        } else {
            showAlert("登录失败，请检查用户名和密码。");
        }
    }

    private boolean isValidLogin(String username, String password) {
        // 实际的登录验证逻辑
        // 暂时简单示例，用户名和密码都为 "admin"
        return "admin".equals(username) && "admin".equals(password);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("错误");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void updateTime() {
        timeLabel.setText("当前时间: " + java.time.LocalTime.now());
    }

    private void startMainApp() {
        DivisionSimulatorApp mainApp = new DivisionSimulatorApp();
        mainApp.main(new String[]{});
    }
}
