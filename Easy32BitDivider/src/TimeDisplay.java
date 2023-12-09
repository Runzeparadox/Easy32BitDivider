import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeDisplay {

    public static void displayCurrentTime(GraphicsContext gc, double centerX, double centerY, double radius) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String currentTime = dateFormat.format(new Date());

        gc.setFill(Color.BLACK);
        gc.setFont(javafx.scene.text.Font.font(18));
        gc.fillText(currentTime, centerX - radius / 2, centerY + radius / 2);
    }
}
