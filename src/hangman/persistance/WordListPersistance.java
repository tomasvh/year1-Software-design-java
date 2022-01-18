package hangman.persistance;

import hangman.model.HangmanWord;
import hangman.model.HangmanWordList;
import hangman.model.ListOfWordList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class for handling persistent storage of wordlists
 *
 * @author Tomas Marx-Raacz von Hidvég
 * @version 1.0
 * */
public class WordListPersistance {

    /**
     * Default constructor
     * */
    public WordListPersistance () {

    }

    /**
     * Getting and populating the ListOfWordList from file
     * @return ListOfWordList
     * */
    public ListOfWordList getWordLists () {
        ListOfWordList listOfLists = new ListOfWordList();
        try {
            File importFile = new File("wordLists.txt");
            Scanner listsScanner = new Scanner(importFile);
            while (listsScanner.hasNextLine()) {
                String data = listsScanner.nextLine();
                HangmanWordList list = new HangmanWordList(data);
                listOfLists.addWordList(list);
            }
            listsScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error has occurred.");
        }
        return listOfLists;
    }

    /**
     * Method for saving current ListOfWordList to a file
     * @param listOfLists ListOfWordList
     * */
    public void writeListOfListFile (ListOfWordList listOfLists) {
        try {
            FileWriter myWriter = new FileWriter("wordLists.txt");
            for (HangmanWordList list: listOfLists.getListOfLists()
            ) { myWriter.write(list.getListName() + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error has occurred");
        }
    }

    /**
     * Method to populate HangmanWordList from file
     * @param wordList HangmanWordList
     * */
    public void populateWordLists (HangmanWordList wordList) {
        try {
            File importFile = new File(wordList.getListName());
            Scanner listsScanner = new Scanner(importFile);
            while (listsScanner.hasNextLine()) {
                String data = listsScanner.nextLine();
                HangmanWord word = new HangmanWord(data);
                wordList.addWord(word);
            }
            listsScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("list " + wordList.getListName() + " does not exist, add it to the folder and try again.");
        }
    }
}
