package hangman;

import hangman.model.HangmanWord;
import hangman.model.HangmanWordList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class HangmanWordListTest {

    HangmanWordList wordList = new HangmanWordList("firstList");
    HangmanWordList wordList2 = new HangmanWordList();
    HangmanWord word = new HangmanWord("liar");

    @BeforeEach
    void setUp() {
        System.out.println("");
    }

    @AfterEach
    void tearDown() {
        System.out.println("");
    }

    @Test
    void getWordList() {
        System.out.println("Running getWordList test");
        assertNotNull(wordList.getWordList());
        System.out.println("--Done with getWordList test");
    }

    @Test
    void getListName() {
        System.out.println("Running getListName test");
        assertNotNull(wordList.getListName());
        assertNull(wordList2.getListName());
        System.out.println("--Done with getListName test");
    }

    @Test
    void setListName() {
        System.out.println("Running setListName test");
        assertNull(wordList2.getListName());
        wordList2.setListName("ultimateWordList");
        assertNotNull(wordList2.getListName());
        assertEquals("ultimateWordList", wordList2.getListName());

        System.out.println("--Done with setListName test");
    }

    @Test
    void getRandomWord() {
        System.out.println("Running getRandomWord test");
        assertNull(wordList.getRandomWord());
        wordList.addWord(word);
        assertNotNull(wordList.getRandomWord());
        assertEquals("liar", wordList.getRandomWord().getWord());

        System.out.println("--Done with getRandomWord test");
    }

    @Test
    void addWord() {
        System.out.println("Running addWord test");
        assertEquals(0, wordList2.getWordList().size());
        wordList2.addWord(word);
        assertEquals(1, wordList2.getWordList().size());

        System.out.println("--Done with addWord test");
    }
}