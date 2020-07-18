import java.util.Random;
import java.io.*;

class SimpleDotCom{
    private  int[] locationsCells;
    private int numOfHits = 0;

    public String checkYourself(String stringGuess){
        int guess = Integer.parseInt(stringGuess);
        String result = "miss";
        for (int cell : locationsCells){
            if (guess == cell) {
                result = "hit";
                numOfHits++;
                break;
            }
        }
        if (numOfHits == locationsCells.length){
            result = "kill";
        }
        System.out.println(result);
        return result;
    }
    public void selfLocantionCells(int[] loc){
        locationsCells = loc;
    }
}

class SimpleDotComGame{
    public static void main(String[] args){
        int numOfGuesses = 0;
        GameHelper helper = new GameHelper();
        Random rand = new Random();
        int numRamdom = rand.nextInt(5);
        int[] pos = {numRamdom, numRamdom+1, numRamdom+2};
        SimpleDotCom dot = new SimpleDotCom();
        dot.selfLocantionCells(pos);
        String result = "miss";
        while (!result.equals("kill")){
            String guess = helper.getUserInput("insira um número: ");
            result = dot.checkYourself(guess);
            numOfGuesses++;
        }
        System.out.println(numOfGuesses);
    }
}


class GameHelper{
    public String getUserInput(String prompt){
        String inputLine = null;
        System.out.print(prompt + "");
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            inputLine = is.readLine();
            if (inputLine.length() == 0) return null;
        } catch (IOException e){
            System.out.print("IOException: " + e);
        }
        return inputLine;
    }
}