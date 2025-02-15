import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class PaintPanel extends JPanel
{
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 800;
    private static final Color BACK_COLOR = Color.RED;

    private int x1, y1, x2, y2;
    private Graphics g;

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Paint program simple");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new PaintPanel();
        frame.add(panel);

        frame.pack();
        frame.setVisible(true);
    }

    private PaintPanel()
    {
        setBackground(BACK_COLOR);
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));

        MyMouseHandler handler = new MyMouseHandler();
        this.addMouseListener(handler);
        this.addMouseMotionListener(handler);
    }

    private class MyMouseHandler extends MouseAdapter
    {
        public void mousePressed(MouseEvent e)
        {
            x1 = e.getX();  y1 = e.getY();

            g = getGraphics();

            x2 = x1;  y2 = y1;
        }

        public void mouseDragged(MouseEvent e)
        {
            x1 = e.getX();  y1 = e.getY();

            g.drawLine(x1, y1, x2, y2);

            x2 = x1;   y2 = y1;
        }
    }
}