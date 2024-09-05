import java.awt.*;


public class Feld{

    int width;
    int height;
    int x;
    int y;
    Color color;
    Graphics graphics;
    boolean belegt = false;

    public Feld(Graphics graphics, int width, int height, int x, int y, Color color, boolean belegt) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.color = color;
        this.graphics = graphics;
        this.belegt = false;

        drawField();
    }

    private void drawField() {
        graphics.setColor(color);
        graphics.fillRect(x, y, width, height);
    }

    public void setColor(Color color) {
        this.color = color;
        drawField();
    }





}
