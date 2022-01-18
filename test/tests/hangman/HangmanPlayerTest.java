package hangman;

import hangman.model.HangmanPlayer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HangmanPlayerTest {

    private final HangmanPlayer player1 = new HangmanPlayer("tomas", "humbug");
    private final HangmanPlayer player2 = new HangmanPlayer();

    @BeforeEach
    void setUp() {
        System.out.println("");
    }

    @AfterEach
    void tearDown() {
        System.out.println("");
    }



    @Test
    void getName() {
        System.out.println("Running getName test");
        assertEquals("tomas", player1.getName());
        assertEquals("randomPlayer", player2.getName());
        assertNotEquals("Bert", player1.getName());
        System.out.println("--Done with getName test");
    }

    @Test
    void getPassword() {
        System.out.println("Running getPassword test");
        assertEquals("humbug", player1.getPassword());
        assertNotEquals("order", player1.getPassword());
        assertEquals("1234", player2.getPassword());
        System.out.println("--Done with getPassword test");
    }

    @Test
    void getTotalScore() {
        System.out.println("Running getName test");
        assertEquals(0, player1.getTotalScore());
        assertNotEquals(5, player1.getTotalScore());
        System.out.println("--Done with addPlayer test");
    }

    @Test
    void addToTotalScore() {
        System.out.println("Running getName test");
        player1.addToTotalScore(5);
        assertNotEquals(4, player1.getTotalScore());
        assertEquals(5, player1.getTotalScore());
        player1.addToTotalScore(5);
        assertEquals(10, player1.getTotalScore());
        System.out.println("--Done with addPlayer test");
    }

    @Test
    void compareTo() {
        System.out.println("Running getName test");
        player1.addToTotalScore(5);
        player2.addToTotalScore(3);
        assertEquals(-2, player1.compareTo(player2));
        assertEquals(2, player2.compareTo(player1));
        System.out.println("--Done with addPlayer test");
    }

    @Test
    void setName() {
        System.out.println("Running getName test");
        assertEquals("tomas", player1.getName());
        player1.setName("lars");
        assertNotEquals("tomas", player1.getName());
        assertEquals("lars", player1.getName());
        System.out.println("--Done with addPlayer test");
    }

    @Test
    void setPassword() {
        System.out.println("Running getName test");
        assertEquals("humbug", player1.getPassword());
        player1.setPassword("chaos1");
        assertNotEquals("humbug", player1.getPassword());
        assertEquals("chaos1", player1.getPassword());
        System.out.println("--Done with addPlayer test");
    }
}