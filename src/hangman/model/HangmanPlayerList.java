package hangman.model;

import java.util.ArrayList;

/**
 * Class for handling a list of players
 *
 * @author Tomas Marx-Raacz von Hidvég
 * @version 1.0
 * */
public class HangmanPlayerList extends HangmanPlayer {

    private final ArrayList<HangmanPlayer> playerList = new ArrayList<>();

    /**
     * Default constructor.
     * */
    public HangmanPlayerList(){

    }

    /**
     * Method for adding a player to the list
     * @param player HangmanPlayer
     * */
    public void addPlayer (HangmanPlayer player) {
        this.playerList.add(player);
    }

    /**
     * Method finding out if a player exist in the list
     * @param player String
     * @return boolean
     * */
    public boolean findPlayer(String player){
        boolean playerExists = false;
        for (HangmanPlayer e:this.playerList) {
            if (player.equals(e.getName())) {
                playerExists = true;
                break;
            }
        }
        return playerExists;
    }

    /**
     * Method for checking if password is correct
     * @param player String
     * @param password String
     * @return boolean
     * */
    public boolean checkPlayerPassword(String player, String password){
        boolean passwordCorrect = false;
        for (HangmanPlayer e:this.playerList) {
            if (player.equals(e.getName())) {
                if(password.equals((e.getPassword()))){
                    passwordCorrect = true;
                }
            }
        }
        return passwordCorrect;
    }

    /**
     * Method for getting a player object from the list
     * @param player String
     * @return HangmanPlayer
     * */
    public HangmanPlayer getPlayer(String player) {
        HangmanPlayer current = null;
        for (HangmanPlayer e : this.playerList) {
            if (player.equals(e.getName())) {
               current = e;
               break;
            }
        }
        return current;
    }

    /**
     * Method for altering a player in the list
     * @param player HangmanPlayer
     * */
    public void alterPlayer(HangmanPlayer player) {
        for (int i = 0; i < this.playerList.size(); i++) {
            if (player.getName().equals(this.playerList.get(i).getName())){
                this.playerList.set(i, player);

            }
        }
    }


    /**
     * Method for returning the playerlist
     * @return ArrayList<HangmanPlayer>
     * */
    public ArrayList<HangmanPlayer> getList(){
        return new ArrayList<>(this.playerList);
    }

    /**
     * Method for getting a arraylist of printable names
     * @return ArrayList<String>
     * */
    public ArrayList<String> getPrintableNames(){
        ArrayList<String> namesList = new ArrayList<>();
        for (HangmanPlayer e:this.playerList) {
            namesList.add(e.getName());
        }
        return namesList;
    }

    /**
     * Test method for testing assignment purposes
     * @param name String
     * @return String
     * */
    public String getPlayerNameAndScore (String name) {
        String returnString = "";
        /* // Commented out code for completion of this method:
        HangmanPlayer chosenPlayer = this.getPlayer(name);

        if (chosenPlayer != null) {
            returnString = chosenPlayer.getName() + " " + chosenPlayer.getTotalScore();
        }

         */


        return  returnString;
    }
}
