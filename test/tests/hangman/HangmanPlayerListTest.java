package hangman;

import hangman.model.HangmanPlayer;
import hangman.model.HangmanPlayerList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HangmanPlayerListTest {

    private int count = 0;
    private final HangmanPlayer player1 = new HangmanPlayer("tomas", "humbug");
    private final HangmanPlayer player2 = new HangmanPlayer("nisse", "tuta");
    private final HangmanPlayerList playerList = new HangmanPlayerList();

    @BeforeEach
    void setUp() {
        System.out.println("");
    }

    @AfterEach
    void tearDown() {
        System.out.println("");
    }

    @Test
    void addPlayer() {
        System.out.println("Running addPlayer test");
        playerList.getList().size();
        assertEquals(0, playerList.getList().size());
        playerList.addPlayer(player1);
        assertNotEquals(0, playerList.getList().size());
        assertEquals(1, playerList.getList().size());
        playerList.addPlayer(player2);
        assertNotEquals(1, playerList.getList().size());
        assertEquals(2, playerList.getList().size());
        System.out.println("--Done with addPlayer test");

    }

    @Test
    void findPlayer() {
        System.out.println("Running findPlayer test");
        playerList.addPlayer(player1);
        assertTrue(playerList.findPlayer("tomas"));
        assertFalse(playerList.findPlayer("Bert"));
        System.out.println("--Done with findPlayer test");
    }

    @Test
    void checkPlayerPassword() {
        System.out.println("Running checkPlayerPassword test");
        playerList.addPlayer(player1);
        playerList.addPlayer(player2);
        assertFalse(playerList.checkPlayerPassword("Bert", "larsson"));
        assertTrue(playerList.checkPlayerPassword("tomas", "humbug"));
        assertFalse(playerList.checkPlayerPassword("nisse", "tutaaaa"));
        System.out.println("--Done with checkPlayerPassword test");
    }

    @Test
    void getPlayer() {
        System.out.println("Running getPlayer test");
        playerList.addPlayer(player1);
        assertEquals(player1, playerList.getPlayer("tomas"));
        assertNull(playerList.getPlayer("Bert"));
        System.out.println("--Done with getPlayer test");
    }

    @Test
    void alterPlayer() {
        System.out.println("Running alterPlayer test");
        playerList.addPlayer(player1);
        assertEquals(0, playerList.getPlayer("tomas").getTotalScore());
        HangmanPlayer modPlayer = playerList.getPlayer("tomas");
        modPlayer.addToTotalScore(5);
        playerList.alterPlayer(modPlayer);
        assertEquals(5, playerList.getPlayer("tomas").getTotalScore());
        HangmanPlayer unknownPlayer = new HangmanPlayer("Bert", "sventon");
        playerList.alterPlayer(unknownPlayer);
        assertEquals("tomas", playerList.getList().get(0).getName());
        assertEquals(1, playerList.getList().size());
        System.out.println("--Done with alterPlayer test");
    }

    @Test
    void getPrintableNames() {
        System.out.println("Running getPrintableNames test");
        assertEquals(0, playerList.getPrintableNames().size());
        playerList.addPlayer(player1);
        assertEquals("tomas", playerList.getPrintableNames().get(0));
        System.out.println("--Done with getPrintableNames test");
    }

    @Test
    void getPlayerNameAndScore() {
        System.out.println("Running getPlayerNameAndScore test");
        playerList.addPlayer(player1);
        assertEquals("tomas 0", playerList.getPlayerNameAndScore("tomas"));
        System.out.println("--Done with getPlayerNameAndScore test");
    }
}