package hangman.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class for handling a list of words
 *
 * @author Tomas Marx-Raacz von Hidvég
 * @version 1.0
 * */
public class HangmanWordList extends HangmanWord {
    private final ArrayList<HangmanWord> wordList = new ArrayList<>();
    private String listName;

    /**
     * Default constructor
     * */
    public HangmanWordList () {
    }

    /**
     * Constructor with name parameters
     * @param listName String*/
    public HangmanWordList (String listName) {
        this.listName = listName;
    }

    /**
     * Generic getter for wordlist
     * @return ArrayList<HangmanWord>
     * */
    public ArrayList<HangmanWord> getWordList() {
        return wordList;
    }

    /**
     * Generic getter for listName
     * @return String
     * */
    public String getListName() {
        return listName;
    }

    /**
     * Generic setter for listName
     * @param listName String
     * */
    public void setListName(String listName) {
        this.listName = listName;
    }

    /**
     * Randomized getter for HangmanWord within list
     * @return HangmanWord
     * */
    public HangmanWord getRandomWord(){
        if (this.wordList.size() < 1) {
            return null;
        } else {
            Random randomNr = new Random();
            int randInt = randomNr.nextInt(this.wordList.size());
            return this.wordList.get(randInt);
        }
    }

    /**
     * Method to add a HangmanWord to the list
     * @param word HangmanWord
     * */
    public void addWord (HangmanWord word) {
        this.wordList.add(word);
    }

}

