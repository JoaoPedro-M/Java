import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class QuizCardBuilder implements ActionListener{
    public JFrame frame;
    public JTextArea anwser;
    public JTextArea question;
    public QuizCard card;
    public static void main(String[] args) {
        new QuizCardPlayer().run();
    }


    public void run() {
        frame = new JFrame("Quiz Card Builder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        JMenuBar barra = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem item = new JMenuItem("Salvar");
        item.addActionListener(this);
        barra.add(menu);
        menu.add(item);
        frame.setJMenuBar(barra);




        question = new JTextArea(10, 20);
        anwser = new JTextArea(10, 20);
        JPanel area = new JPanel();
        area.add(question);
        area.add(anwser);
        //area.setLayout(new BoxLayout(area, BoxLayout.Y_AXIS));

        frame.getContentPane().add(BorderLayout.CENTER, area);
        frame.setVisible(true);
        frame.setBounds(50, 50, 1000, 600);
    }



    public void actionPerformed(ActionEvent ev) {
        salvarCartao();
    }

    public void salvarCartao() {
        String q = question.getText();
        String a = anwser.getText();
        card = new QuizCard(q, a);
        try {
            FileOutputStream file = new FileOutputStream("Arquivo.ser");
            ObjectOutputStream obj = new ObjectOutputStream(file);
            obj.writeObject(card);
            obj.close();
        } catch (Exception ex) {ex.printStackTrace();}
    }



}



class QuizCardPlayer implements ActionListener{
    JTextArea area;
    JPanel panel;
    boolean carregado = false;
    QuizCard card;
    public void run() {
        JFrame frame = new JFrame();


        JMenuBar barra = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem load = new JMenuItem("Load");
        load.addActionListener(this);
        barra.add(menu);
        menu.add(load);

        area = new JTextArea(10, 20);
        panel = new JPanel();
        

        

        panel.add(area);
        
        frame.getContentPane().add(panel);
        frame.setJMenuBar(barra);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setBounds(50, 50, 1000, 600);


    }



    public void actionPerformed(ActionEvent ev) {
        if (!carregado) {
            try {
                FileInputStream file = new FileInputStream("Arquivo.ser");
                ObjectInputStream obj = new ObjectInputStream(file);
                Object ob = obj.readObject();
                
                card = (QuizCard) ob;
                area.setText(card.getQuestion());
                obj.close();
            } catch (Exception ex) {ex.printStackTrace();}
            JButton showAnwser = new JButton("Show Anwser");
            showAnwser.addActionListener(this);
            panel.add(showAnwser);
            carregado = true;
        }else {
            area.setText(card.getAnswer());
        }
    }
}













class QuizCard implements Serializable{
    private String question;
    private String answer;

    public QuizCard(String q, String a) {
        question = q;
        answer = a;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}