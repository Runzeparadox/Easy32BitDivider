import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DivisionSimulatorApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        LoginUI loginUI = new LoginUI();
        loginUI.showLoginDialog();

        // 继续启动主应用
        initializeMainApp(primaryStage);
    }

    private void initializeMainApp(Stage primaryStage) {
        DivisionSimulatorUI ui = new DivisionSimulatorUI();
        ui.initialize(primaryStage);

        primaryStage.setTitle("32位除法器模拟器");
        primaryStage.show();
    }

}
