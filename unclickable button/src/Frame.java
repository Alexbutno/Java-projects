import jdk.dynalink.Operation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Frame extends JFrame {

    final static int SIZE_X = 700;
    final static int SIZE_Y = 700;
    JLabel statusLabel;
    JButton buttonNo;
    JButton buttonYes;

    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setSize(SIZE_X, SIZE_Y);
        frame.setVisible(true);
    }
    Frame() {
        super("Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        statusLabel = new JLabel("Радует ли вас размер стипендии?");
        add(statusLabel, BorderLayout.NORTH);

        buttonNo = new JButton("НЕТ!");
        buttonNo.setBounds(100, 100,150,30);

        buttonYes = new JButton("ДА!");
        buttonYes.setBounds(400, 100,150,30);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(buttonNo);
        panel.add(buttonYes);
        add(panel, BorderLayout.CENTER);

        buttonNo.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(Frame.this, "Sorry,but is reality");
            }
        });

        buttonYes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setLocationYes();
            }
        });
    }

    void setLocationYes(){
        boolean ifFits=true;
        while(ifFits){
            int x = (int) (Math.random()*(SIZE_X-buttonYes.getWidth()+1));
            int y = (int) (Math.random()*(SIZE_Y-statusLabel.getHeight()-buttonYes.getHeight()-49));
            if (!buttonInButton(x,y,buttonYes) && !buttonInButton(x,y,buttonNo)&&buttonInWindow(x,y,buttonYes)){
                buttonYes.setLocation(x,y);
                ifFits=false;
            }
        }
    }
    boolean pointInButton(int x,int y,JButton button){
        return (x>=button.getX()&&x<=(button.getX()+button.getWidth())&&y>=button.getY()&&y<=(button.getY()+button.getHeight()));
    }
    boolean buttonInButton(int x,int y,JButton button){
        return pointInButton(x,y,button)||pointInButton(x+button.getWidth(),y,button)||pointInButton(x,y+button.getHeight(),button)||pointInButton(x+button.getWidth(),y+button.getHeight(),button) ;
    }

    boolean buttonInWindow(int x,int y,JButton button){
        return (x>=0 && x<=SIZE_X-button.getWidth()&&y>=0&&y<=SIZE_Y-button.getHeight());
    }
}