import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String category;
        Character letter;
        int attempts = 10;
        int currentScore = 0;
        List<String> wordsCategories = new ArrayList<>();
        boolean isGuessCharachter = false;
        boolean isGuessWord = false;

        while (attempts > 0) {
            BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\AVLuser\\Downloads\\Hangman\\Hangman\\src\\dictionary.txt"));

            System.out.println("Please choose a category:");
            System.out.println("Football teams");
            System.out.println("Books");
            System.out.println("Programming principles");

            System.out.print(">");
            category = scanner.nextLine().toLowerCase();
            category = "_" + category;

            String line=null;
            while ((line = in.readLine()) != null) {
                if ((line.toLowerCase()).equals(category)) {
                    try {
                        while (!((line = in.readLine()).contains("_"))) {
                            wordsCategories.add(line);
                        }
                    }catch (NullPointerException npe){

                    }
                }
            }
            in.close();

            if(wordsCategories.isEmpty()){
                break;
            }


            Random random = new Random();
            int n = random.nextInt(wordsCategories.size());

            char[] word = wordsCategories.remove(n).toCharArray();

            int size = wordsCategories.size();

            for (int i = 0; i < size; i++) {
                wordsCategories.remove(wordsCategories.size()-1);
            }
            Character[] field = new Character[word.length];
            for (int i = 0; i < field.length; i++) {
                field[i] = '_';
            }

            System.out.printf("Attempts left: %d%n", attempts);
            System.out.print("Current wordsCategories/phrase:");
            for (int i = 0; i < word.length - 1; i++) {
                if (word[i] == ' ') {
                    System.out.print(" ");
                }
                System.out.print("_ ");
            }
            System.out.println();
            while (true) {
                //System.out.println(word);
                System.out.println("Please enter a letter:");
                System.out.print("> ");
                letter = scanner.nextLine().charAt(0);
                for (int i = 0; i < word.length; i++) {
                    if (letter.equals(word[i])) {
                        field[i] = word[i];
                        isGuessCharachter = true;
                    } else {
                        if (word[i] == ' ') {
                            field[i] = ' ';
                        } else {
                            if (field[i] == '_') {
                                field[i] = '_';
                            }
                        }
                    }
                }
                for (int i = 0; i < field.length; i++) {
                    if (field[i] == '_') {
                        isGuessWord = false;
                    }
                    System.out.print(field[i] + " ");
                }
                System.out.println();
                if (!isGuessCharachter) {
                    attempts--;
                }
                if (isGuessWord) {
                    System.out.println("Congratulations you have revealed the word/phrase:");
                    System.out.println(word);
                    currentScore++;
                    System.out.printf("Current score: %d%n", currentScore);
                    break;
                }
                isGuessCharachter = false;
                isGuessWord = true;
                if(attempts==0){
                    break;
                }else {
                    System.out.printf("Attempts left: %d%n", attempts);
                    System.out.print("Current wordsCategories/phrase:");
                    System.out.println();
                }
            }
        }
        System.out.println("GAME OVER");

    }
}
