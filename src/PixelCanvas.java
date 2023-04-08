import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PixelCanvas extends JPanel {
    private static final int PIXEL_SIZE = 10;
    private final ArrayList<Pixel> pixels;

    public PixelCanvas() {
        pixels = new ArrayList<>();
        setPreferredSize(new Dimension(320, 320));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }

    public void addPixel(int x, int y, Color color) {
        pixels.add(new Pixel(x, y, color));
        repaint();
    }

    public void movePixel(int dx, int dy) {
        for (Pixel p : pixels) {
            p.move(dx, dy);
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Pixel p : pixels) {
            g.setColor(p.getColor());
            g.fillRect(p.getX() * PIXEL_SIZE, p.getY() * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
        }
    }

    private static class Pixel {
        private int x;
        private int y;
        private Color color;

        public Pixel(int x, int y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Color getColor() {
            return color;
        }

        public void move(int dx, int dy) {
            x += dx;
            y += dy;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pixel Canvas");
        PixelCanvas canvas = new PixelCanvas();
        frame.getContentPane().add(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        canvas.addPixel(2, 2, Color.RED);
        canvas.addPixel(5, 5, Color.BLUE);

        canvas.movePixel(2, 2); // Move all pixels by (2, 2)
    }
}
