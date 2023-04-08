import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Main extends JPanel implements KeyListener {
    private static final int CANVAS_WIDTH = 1000;
    private static final int CANVAS_HEIGHT = 1000;
    private static final int PIXEL_SIZE = 32;
    private static final int PIXEL_COLOR = Color.RED.getRGB();
    private int pixelX = 0;
    private int pixelY = 0;
    private int[][] pixels;

    public Main() {
        pixels = new int[CANVAS_WIDTH/PIXEL_SIZE][CANVAS_HEIGHT/PIXEL_SIZE];
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        setBackground(Color.WHITE);
        addKeyListener(this);
        setFocusable(true);
    }

    public void setPixel(int x, int y, int color) {
        pixels[x][y] = color;
        repaint();
    }

    public void movePixel(int dx, int dy) {
        pixelX += dx;
        pixelY += dy;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int x = 0; x < CANVAS_WIDTH/PIXEL_SIZE; x++) {
            for (int y = 0; y < CANVAS_HEIGHT/PIXEL_SIZE; y++) {
                g.setColor(new Color(pixels[x][y]));
                g.fillRect(x * PIXEL_SIZE, y * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
            }
        }
        g.setColor(new Color(PIXEL_COLOR));
        g.fillRect(pixelX, pixelY, PIXEL_SIZE, PIXEL_SIZE);
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                System.out.println("Left key pressed");
                movePixel(-PIXEL_SIZE, 0);
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("Right key pressed");
                movePixel(PIXEL_SIZE, 0);
                break;
            case KeyEvent.VK_UP:
                System.out.println("Up key pressed");
                movePixel(0, -PIXEL_SIZE);
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("Down key pressed");
                movePixel(0, PIXEL_SIZE);
                break;
        }
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pixel Canvas");
        Main canvas = new Main();
        frame.getContentPane().add(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}