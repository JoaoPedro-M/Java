import java.io.*;


public class Salvar {
    public static void main(String[] args)  {
        Jogador jog = new Jogador();
        jog.setName("kkk");

        try{ 
            FileInputStream f = new FileInputStream("Arquivo.ser");
            ObjectInputStream o = new ObjectInputStream(f);
            Jogador n = (Jogador) o.readObject();
            o.close();
            System.out.println(n.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}




class Jogador implements Serializable {
    private String name;

    public void setName(String n) {
        name = n;
    }
    public String getName() {
        return name;
    }
}