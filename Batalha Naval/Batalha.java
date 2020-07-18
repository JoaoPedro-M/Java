
class Batalha {
    public static void main(String[] args){
        Dog cachorro = new Dog();
        System.out.println(cachorro.getLatido());
        cachorro.setLatido(true);
        System.out.println(cachorro.getLatido());
    }
}

class Dog {
    private boolean latir;
         
    public boolean getLatido(){
        return latir;
    }

    public void setLatido(boolean late){
        latir = late;
    }
}
