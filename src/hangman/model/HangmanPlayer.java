package hangman.model;

/**
 * Class for handling a player
 *
 * @author Tomas Marx-Raacz von Hidvég
 * @version 1.0
 * */
public class HangmanPlayer implements Comparable {
    private String name = "randomPlayer";
    private String password = "1234";
    private int totalScore = 0;

    /**
     * Default constructor.
     * */
    public HangmanPlayer(){

    }

    /**
     * Constructor with parameters.
     * @param name String
     * @param password int
     * */
    public HangmanPlayer(String name, String password){
        this.name = name;
        this.password = password;
    }

    /**
     * Generic getter for name
     * */
    public String getName() {
        return name;
    }

    /**
     * Generic getter for password
     * @return String
     * */
    public String getPassword() {
        return password;
    }

    /**
     * Generic getter for totalScore
     * @return int
     * */
    public int getTotalScore() {
        return totalScore;
    }

    /**
     * Method for adding a number to the totalScore
     * @param totalScore int
     * */
    public void addToTotalScore(int totalScore) {
        this.totalScore += totalScore;
    }



    /**
     * Default constructor
     * @param o Object
     * @return int
     * */
    @Override
    public int compareTo(Object o) {
        int compareScore = ((HangmanPlayer) o).getTotalScore();
        return compareScore - this.totalScore;
    }

    /**
     * Generic setter for name
     * @param name String
     * */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Generic setter for password
     * @param password String
     * */
    public void setPassword(String password) {
        this.password = password;
    }
}
