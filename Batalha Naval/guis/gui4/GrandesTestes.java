import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;

public class GrandesTestes {
    public static void main(String[] args) {
        GrandesTestes a = new GrandesTestes();
        a.screen();
    }


    public void screen() {
        JFrame frame = new JFrame();

        JButton bt1 = new JButton("BOTAO");
        JButton bt2 = new JButton("BOT");

        JPanel panel = new JPanel();

        panel.add(bt1);
        panel.add(bt2);

        panel.setBackground(Color.green);

        frame.getContentPane().add(BorderLayout.NORTH, panel);


        JTextField text = new JTextField("Escreva aqui!");

        JPanel panel2 = new JPanel();


        panel2.add(text);

        frame.getContentPane().add(BorderLayout.CENTER, panel2);








        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
} 