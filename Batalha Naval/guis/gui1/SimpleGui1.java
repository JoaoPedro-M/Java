import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleGui1{
    public JButton botao;
    public JFrame frame;
    public JLabel label;
    public static void main(String[] args) {
        SimpleGui1 a = new SimpleGui1();
        a.go();
    }

    public void go() {
        frame = new JFrame();
        botao = new JButton("Clique para alterar!");
        botao.addActionListener(new LabelListener());
        JButton btcor = new JButton("Alterar Cor!");
        btcor.addActionListener(new ButtonListener());
        MyDrawPanel panel = new MyDrawPanel();
        label = new JLabel("Eu to aoiubvwobvwoybv");

        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        frame.getContentPane().add(BorderLayout.SOUTH, btcor);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.WEST, label);
        frame.getContentPane().add(BorderLayout.EAST, botao);
        frame.setSize(600, 600);

        frame.setVisible(true);
    }


    class LabelListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            label.setText("Clicado");
        }
    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            frame.repaint();
        }
    }

}



class MyDrawPanel extends JPanel {

    public void paintComponent(Graphics g) {
        int wid = this.getWidth();
        int hei = this.getHeight();
        g.fillRect(0, 0, wid, hei);
        Graphics2D g2d = (Graphics2D) g;

        Color ini = Num.gerarCor(), fim = Num.gerarCor();


        GradientPaint gradient = new GradientPaint(70, 70, ini, 150, 150, fim);


        
        g2d.setPaint(gradient);
        g2d.fillOval(2, 50,  100, 100);
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