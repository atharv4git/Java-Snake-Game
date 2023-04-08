import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KeyListenerExample extends JPanel{
    public static void main(String[] args) {
        JFrame frame = new JFrame("KeyListener Example");
        KeyListenerExample canvas = new KeyListenerExample();
        frame.getContentPane().add(canvas);
        JPanel panel = new JPanel();

        panel.addKeyListener(new MyKeyListener()); // add a KeyListener to the JPanel
        panel.setFocusable(true); // make sure the JPanel is focusable

        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public KeyListenerExample(){
        setPreferredSize(new Dimension(100, 100));
    }

    private static class MyKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) { // check if the space key was pressed
                System.out.println("Space key pressed!"); // invoke your function here
            }
        }
    }
}
