public class Passagem {
    public static void main(String[ ] args) {
        Dog c = new Dog();
        c.latir();
    }
}




class Animal {
    public int idade;
    public String nome;
    private boolean vivo;

    public boolean esta_vivo() {
        falar();
        return vivo;
    }

    private void falar() {
        System.out.print("Falando");
    }
}



class Dog extends Animal {

    public void latir() {
        Dog cachorro = new Dog();
        String nomito = cachorro.nome;
        System.out.println(cachorro.esta_vivo());
    }
}