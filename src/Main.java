import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Main extends JPanel implements KeyListener {
    private static final int CANVAS_WIDTH = 905;
    private static final int CANVAS_HEIGHT = 905;
    private static final int PIXEL_SIZE = 30;
    private static final int PIXEL_COLOR = Color.RED.getRGB();
    private int pixelX = 5;
    private int pixelY = 5;
    private int pixelLength = 1;
    private int[] pixelXCoords;
    private int[] pixelYCoords;

    public Main() {
        pixelXCoords = new int[pixelLength];
        pixelYCoords = new int[pixelLength];
        pixelXCoords[0] = pixelX;
        pixelYCoords[0] = pixelY;
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        setBackground(Color.DARK_GRAY);
        setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
        addKeyListener(this);
        setFocusable(true);
    }

    public void movePixel(int dx, int dy) {
        if (pixelX + dx < CANVAS_WIDTH - 10 && pixelY + dy < CANVAS_HEIGHT - 10 && pixelY + dy >= 0 && pixelX + dx >= 0) {
            for (int i = pixelLength - 1; i > 0; i--) {
                pixelXCoords[i] = pixelXCoords[i-1];
                pixelYCoords[i] = pixelYCoords[i-1];
            }
            pixelX += dx;
            pixelY += dy;
            pixelXCoords[0] = pixelX;
            pixelYCoords[0] = pixelY;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < pixelLength; i++) {
            g.setColor(new Color(PIXEL_COLOR));
            g.fillRect(pixelXCoords[i], pixelYCoords[i], PIXEL_SIZE, PIXEL_SIZE);
        }
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
            case KeyEvent.VK_ENTER:
                System.out.println("Enter Pressed");
                pixelLength++;
                int[] newPixelXCoords = new int[pixelLength];
                int[] newPixelYCoords = new int[pixelLength];
                for (int i = 0; i < pixelLength - 1; i++) {
                    newPixelXCoords[i] = pixelXCoords[i];
                    newPixelYCoords[i] = pixelYCoords[i];
                }
                newPixelXCoords[pixelLength - 1] = pixelXCoords[pixelLength - 2];
                newPixelYCoords[pixelLength - 1] = pixelYCoords[pixelLength - 2];
                pixelXCoords = newPixelXCoords;
                pixelYCoords = newPixelYCoords;
                repaint();
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