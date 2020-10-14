import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.sound.midi.*;

public class MiniMusicPlayer{
    JFrame frame;
    MyDraw des;
    public static void main(String[] args) {
        MiniMusicPlayer a = new MiniMusicPlayer();
        a.go();
    }

    public void go() {
        frame = new JFrame();
        des = new MyDraw();
        frame.getContentPane().add(BorderLayout.CENTER, des);
        //JButton bt = new JButton("Clique pra tocar!");
        //bt.addActionListener(this);
        
        //frame.getContentPane().add(BorderLayout.SOUTH, bt);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);


        for (int i = 0; i < 10; i++) {
            tocar();
        }





    }

    public void tocar() {
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            
            sequencer.open();

            Sequence seq = new Sequence(Sequence.PPQ, 4);

            Track tr = seq.createTrack();

            ShortMessage change_instrument = new ShortMessage();
            change_instrument.setMessage(192, 1, Num.gerarAleatorio(100), 0);
            MidiEvent evento = new MidiEvent(change_instrument, 0);
            tr.add(evento);

            ShortMessage a = new ShortMessage();
            a.setMessage(144, 1, 44, 100);
            MidiEvent event = new MidiEvent(a, 0);
            tr.add(event);


            ShortMessage b = new ShortMessage();
            b.setMessage(128, 1, 44, 100);
            MidiEvent even = new MidiEvent(b, 16);
            tr.add(even);

            sequencer.setSequence(seq);

            sequencer.start();
            Thread.sleep(2000);
            des.repaint();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    class MyDraw extends JPanel {
        public void paintComponent(Graphics g) {
            //g.setColor(Color.white);
            //g.fillRect(0, 0, this.getWidth(), this.getHeight());

            g.setColor(Num.gerarCor());
            g.fillRect(Num.gerarAleatorio(200), Num.gerarAleatorio(200), Num.gerarAleatorio(60), Num.gerarAleatorio(60));
        }
    }
}









class Num {
    private Num() {}


    public static int gerarAleatorio(int multiplicador) {
        double num = Math.random() * multiplicador;
        return (int) num;
    }

    public static Color gerarCor() {
        int r = gerarAleatorio(255);
        int g = gerarAleatorio(255);
        int b = gerarAleatorio(255);
        return new Color(r, g, b);
    }
}