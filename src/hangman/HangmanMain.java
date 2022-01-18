package hangman;

import hangman.model.*;
import hangman.persistance.PlayerListPersistance;
import hangman.persistance.WordListPersistance;
import hangman.view.HangManGame;

/**
 * Class for starting the application
 *
 * @author Tomas Marx-Raacz von Hidvég
 * @version 1.0
 * */
public class HangmanMain extends WordListPersistance {


    /**
     * Main executing method for software
     * @param args String[]
     * */
    public static void main(String[] args) {

        PlayerListPersistance playerListImporter = new PlayerListPersistance();
        WordListPersistance wordListPersistance = new WordListPersistance();
        ListOfWordList listOfLists = wordListPersistance.getWordLists();
        for (HangmanWordList list: listOfLists.getListOfLists()
             ) {
            wordListPersistance.populateWordLists(list);
        }

        HangmanPlayerList playerList = new HangmanPlayerList();
        playerList = playerListImporter.readPlayerListFile(playerList);


        String activeWordList = listOfLists.getListOfLists().get(0).getListName();
        HangManGame game = new HangManGame();
        game.startUp(playerList, listOfLists.getListOfLists().get(0), listOfLists, activeWordList);
    }
}
