package hangman;

import hangman.model.HangmanWordList;
import hangman.model.ListOfWordList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListOfWordListTest {

    ListOfWordList listOfWordList = new ListOfWordList();
    HangmanWordList wordList = new HangmanWordList();

    @BeforeEach
    void setUp() {
        System.out.println("");
    }

    @AfterEach
    void tearDown() {
        System.out.println("");
    }

    @Test
    void getListOfLists() {
        System.out.println("Running getListOfLists test");
        assertNotNull(listOfWordList.getListOfLists());
        assertEquals(0, listOfWordList.getListOfLists().size());
        System.out.println("--Done with getListOfLists test");
    }

    @Test
    void addWordList() {
        System.out.println("Running addWordList test");
        assertEquals(0, listOfWordList.getListOfLists().size());
        listOfWordList.addWordList(wordList);
        assertNotEquals(0, listOfWordList.getListOfLists().size());
        assertEquals(1, listOfWordList.getListOfLists().size());

        System.out.println("--Done with addWordList test");
    }
}