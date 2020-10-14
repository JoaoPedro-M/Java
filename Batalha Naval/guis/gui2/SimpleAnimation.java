import javax.swing.*;
import java.awt.*;

public class SimpleAnimation {
    int x;
    int y;

    public static void main(String[] args) {
        SimpleAnimation a = new SimpleAnimation();
        a.go();
    }



    public void go() {
        JFrame frame = new JFrame();


        MyDraw draw = new MyDraw();

        frame.getContentPane().add(draw);



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);


        for (int i = 0; i< 130; i++) {
            x++;
            y++;
            draw.repaint();
            try {
                Thread.sleep(30);
            } catch (Exception ex) { }
        }
        


    }


    class MyDraw extends JPanel {
        public void paintComponent(Graphics g) {
            g.setColor(Color.black);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());

            g.setColor(Color.green);
            g.fillOval(x, y, 40, 40);
        }
    }
}