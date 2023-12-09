// CanvasDrawer.java
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CanvasDrawer implements DivisionCallback {

    private Canvas canvas;

    public CanvasDrawer() {
        canvas = new Canvas(300, 300);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public void onStep(long quotient, long remainder) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double centerX = canvas.getWidth() / 2;
        double centerY = canvas.getHeight() / 2;
        double radius = Math.min(centerX, centerY) - 10;

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        double total = quotient + remainder;
        double quotientAngle = (quotient / total) * 360;
        double remainderAngle = (remainder / total) * 360;

        // 绘制商的扇形
        gc.setFill(Color.BLUE);
        gc.fillArc(10, 10, 2 * radius, 2 * radius, 0, quotientAngle, javafx.scene.shape.ArcType.ROUND);
        // 绘制商的文本
        drawDataText(gc, "商", quotientAngle / 2, radius / 2, centerX, centerY);

        // 绘制余数的扇形
        gc.setFill(Color.RED);
        gc.fillArc(10, 10, 2 * radius, 2 * radius, quotientAngle, remainderAngle, javafx.scene.shape.ArcType.ROUND);
        // 绘制余数的文本
        drawDataText(gc, "余数", quotientAngle + remainderAngle / 2, radius / 2, centerX, centerY);
    }

    public void displayCurrentTime() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double centerX = canvas.getWidth() / 2;
        double centerY = canvas.getHeight() / 2;
        double radius = Math.min(centerX, centerY) - 10;

        TimeDisplay.displayCurrentTime(gc, centerX, centerY, radius);
    }

    private void drawDataText(GraphicsContext gc, String text, double angle, double distance, double centerX, double centerY) {
        double x = centerX + Math.cos(Math.toRadians(angle)) * distance;
        double y = centerY + Math.sin(Math.toRadians(angle)) * distance;

        gc.setFill(Color.BLACK);
        gc.fillText(text, x, y);
    }
}
