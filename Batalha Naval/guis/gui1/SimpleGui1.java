import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleGui1 implements ActionListener{
    public JButton botao;
    public JFrame frame;
    public static void main(String[] args) {
        SimpleGui1 a = new SimpleGui1();
        a.go();
    }

    public void go() {
        frame = new JFrame();
        //botao = new JButton("Clique!");
        //botao.addActionListener(this);
        MyDrawPanel panel = new MyDrawPanel();

        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        frame.getContentPane().add(panel);
        frame.setSize(300, 300);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {

        frame.repaint();
    }

}



class MyDrawPanel extends JPanel {

    public void paintComponent(Graphics g) {
        //g.fillRect(0, 0, this.getWidth(), this.getHeight());
        Graphics2D g2d = (Graphics2D) g;

        Color ini = Num.gerarCor(), fim = Num.gerarCor();


        GradientPaint gradient = new GradientPaint(70, 70, ini, 150, 150, fim);


        
        g2d.setPaint(gradient);
        g2d.fillOval(70, 70,  100, 100);
    }
}













class Num {

    private Num() {}

    public static int gerarAleatorio() {
        double num = Math.random()*255;     
        int n = (int) num;
        return n;
    }

    public static Color gerarCor() {
        int red = gerarAleatorio();
        int green = gerarAleatorio();
        int blue = gerarAleatorio();

        Color cor = new Color(red, green, blue);
        return cor;
    }
}