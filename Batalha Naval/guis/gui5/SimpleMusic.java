import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.midi.*;






public class SimpleMusic {
    public static void main(String[] args) {
        Tela a = new Tela();
        a.iniciar();
    }
}




class Music {
    private Sequencer sequencer;
    private Sequence seq;
    private Track tr;

    
    public Music() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            seq = new Sequence(Sequence.PPQ, 4);
            tr = seq.createTrack();
            sequencer.setTempoInBPM(220);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        setChannels();
    }

    public void criarMidi(int command, int channel, int data1, int data2, int tick) {
        try{
            ShortMessage a = new ShortMessage();
            a.setMessage(command, channel, data1, data2);
            MidiEvent nota = new MidiEvent(a, tick);
            tr.add(nota);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void tocar() {
        try {
            sequencer.setSequence(seq);
            sequencer.start();
            seq = new Sequence(Sequence.PPQ, 4);
            tr = seq.createTrack();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setChannels() {
        for (int i=0; i<16; i++) {
            criarMidi(192, i, i+5, 0, 0);
        }
    }

    public void stop() {
        sequencer.stop();
    }

    public void setTime(int tempo) {
        sequencer.setTempoInBPM(tempo);
    }
}















class Tela {
    public JFrame frame;
    private JCheckBox[] cheques;
    private Music musica;
    int tempo=220;

    public void iniciar() {
        musica = new Music();

        frame = new JFrame();


        JButton[] botoes = new JButton[6];
        
        String[] palavras = {
        "Start",
        "Stop",
        "Time Up",
        "Time Down",
        "serializelt",
        "restore"
        };

        JPanel panel1 = new JPanel();
        
        
        for (int i=0; i < 6; i++) {
            botoes[i] = new JButton(palavras[i]);
            panel1.add(botoes[i]);
        }

        botoes[0].addActionListener(new Start());
        botoes[1].addActionListener(new Stop());
        botoes[2].addActionListener(new TimeUp());
        botoes[3].addActionListener(new TimeDown());
        botoes[4].addActionListener(new Serializelt());
        botoes[5].addActionListener(new Restore());



        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

        criarPainel2();
        criarPainel3();

        frame.getContentPane().add(BorderLayout.EAST, panel1);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setBounds(500, 50, 630, 470);
        frame.setVisible(true);
        

    }

    private void criarPainel2() {
        String[] pal = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal",
        "Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap",
        "Low-mid Tom", "High Agogo", "Open Hi Conga"};

        JPanel painel2 = new JPanel();

        for (String pala : pal) {
            JTextArea p = new JTextArea(pala);
            painel2.add(p);
        }
        painel2.setLayout(new BoxLayout(painel2, BoxLayout.Y_AXIS));
        frame.getContentPane().add(BorderLayout.WEST, painel2);
    }

    private void criarPainel3() {
        JPanel painel = new JPanel();
        cheques = new JCheckBox[256];

        for (int i=0; i < 256; i++) {
            cheques[i] = new JCheckBox();
            painel.add(cheques[i]);
        }
        frame.getContentPane().add(painel);
    }

    class Start implements ActionListener{
        public void actionPerformed(ActionEvent ev) {
            for (int i=0; i<256; i++) {
                if (cheques[i].isSelected()) {
                    musica.criarMidi(144, Math.abs(i/16)+1, 44, 100, i%16);
                }
                else {
                    musica.criarMidi(128, Math.abs(i/16)+1, 4, 100, i%16);
                }
            }
            musica.tocar();
        }
    }

    class Stop implements ActionListener{
        public void actionPerformed(ActionEvent ev) {
            musica.stop();
        }
    }

    class TimeUp implements ActionListener{
        public void actionPerformed(ActionEvent ev) {
            tempo += 20;
            musica.setTime(tempo);
        }
    }

    class TimeDown implements ActionListener{
        public void actionPerformed(ActionEvent ev) {
            tempo -= 20;
            musica.setTime(tempo);
        }
    }

    class Serializelt implements ActionListener{
        public void actionPerformed(ActionEvent ev) {
            System.out.println("AAAA");
        }
    }

    class Restore implements ActionListener{
        public void actionPerformed(ActionEvent ev) {
            for (JCheckBox check : cheques) {
                check.setSelected(false);
                tempo = 220;
                musica.setTime(tempo);
            }
        }
    }
}