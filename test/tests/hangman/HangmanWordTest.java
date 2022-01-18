package hangman;

import hangman.model.HangmanWord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HangmanWordTest {

    HangmanWord word1 = new HangmanWord("liar");
    HangmanWord word2 = new HangmanWord();


    @BeforeEach
    void setUp() {
        System.out.println("");
    }

    @AfterEach
    void tearDown() {
        System.out.println("");
    }

    @Test
    void getWord() {
        System.out.println("Running getWord test");
        assertEquals("liar", word1.getWord());
        assertNotEquals("liar", word2.getWord());
        assertEquals("", word2.getWord());
        System.out.println("--Done with getWord test");
    }

    @Test
    void setWord() {
        System.out.println("Running setWord test");
        assertEquals("liar", word1.getWord());
        word1.setWord("truthful");
        assertNotEquals("liar", word1.getWord());
        assertEquals("truthful", word1.getWord());
        System.out.println("--Done with setWord test");
    }
}