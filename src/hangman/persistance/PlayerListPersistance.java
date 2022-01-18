package hangman.persistance;

import hangman.model.HangmanPlayer;
import hangman.model.HangmanPlayerList;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Class for handling persistent storage of players
 *
 * @author Tomas Marx-Raacz von Hidvég
 * @version 1.0
 * */
public class PlayerListPersistance {

    /**
     * Default constructor
     * */
    public PlayerListPersistance () {

    }

    /**
     * Method for encoding a password with base64 encoding
     * @param password String
     * @return String
     * */
    public String encodePassword (String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    /**
     * Method for decoding a password with base64 decoding
     * @param encodedPassword String
     * @return String
     * */
    public String decodePassword (String encodedPassword) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedPassword);
        return new String(decodedBytes);
    }

    /**
     * Method to save a player list to .txt file
     * @param playerList HangmanPlayerList
     * */
    public void savePlayerListToFile (HangmanPlayerList playerList) {
        try {
            FileWriter myWriter = new FileWriter("playerList.txt");
            for (HangmanPlayer player: playerList.getList()
                 ) { myWriter.write(player.getName() + "," + encodePassword(player.getPassword()) + "," + player.getTotalScore() + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Method to read and populate the internal list from player list file
     * @param playerList HangmanPlayerList
     * @return HangmanPlayerList
     * */
    public HangmanPlayerList readPlayerListFile (HangmanPlayerList playerList) {
        try {
            File myObj = new File("playerList.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                ArrayList<String> tempArray = new ArrayList<>(List.of(data.split(",")));
                HangmanPlayer player = new HangmanPlayer(tempArray.get(0), decodePassword(tempArray.get(1)));
                player.addToTotalScore(Integer.parseInt(tempArray.get(2)));
                playerList.addPlayer(player);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return playerList;
    }
}
