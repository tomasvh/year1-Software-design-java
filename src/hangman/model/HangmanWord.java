package hangman.model;

/**
 * Class for handling a word for the game
 *
 * @author Tomas Marx-Raacz von Hidvég
 * @version 1.0
 * */
public class HangmanWord {
    private String word = "";

    /**
     * Default constructor
     * */
    public HangmanWord () {

    }

    /**
     * Constructor with parameter
     * @param word String
     * */
    public HangmanWord (String word) {
        this.word = word;
    }

    /**
     * Generic getter for word
     * @return String
     * */
    public String getWord() {
        return word;
    }

    /**
     * Generic setter for word
     * @param word String
     * */
    public void setWord(String word) {
        this.word = word;
    }
}
