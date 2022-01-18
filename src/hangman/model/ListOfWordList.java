package hangman.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Class for handling a list of wordlists
 *
 * @author Tomas Marx-Raacz von Hidvég
 * @version 1.0
 * */
public class ListOfWordList extends HangmanWordList {
    private final ArrayList<HangmanWordList> listOfLists = new ArrayList<>();

    /**
     * Default constructor
     * */
    public ListOfWordList () {

    }

    /**
     * Generic getter for list
     * @return ArrayList<HangmanWordList>
     * */
    public ArrayList<HangmanWordList> getListOfLists() {
        return listOfLists;
    }

    /**
     * Method to get a list by name
     * @param listName String
     * @return HangmanWordList
     * */
    public HangmanWordList getListByName (String listName) {
        HangmanWordList returnList = null;
        for (HangmanWordList list: getListOfLists()
             ) {
            if (Objects.equals(list.getListName(), listName)) {
                returnList = list;
            }

        }
        return returnList;
    }

    /**
     * Method to add a HangmanWordList to the list of lists
     * @param x HangmanWordList
     * */
    public void addWordList(HangmanWordList x){
        this.listOfLists.add(x);
    }

}
