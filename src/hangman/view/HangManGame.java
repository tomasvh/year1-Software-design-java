package hangman.view;

import hangman.model.HangmanPlayer;
import hangman.model.HangmanPlayerList;
import hangman.model.HangmanWordList;
import hangman.model.ListOfWordList;
import hangman.persistance.PlayerListPersistance;
import hangman.persistance.WordListPersistance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Class for handling game user interface
 *
 * @author Tomas Marx-Raacz von Hidvég
 * @version 1.0
 * */
public class HangManGame extends ListOfWordList {

    /**
     * Default constructor
     * */
    public HangManGame () {

    }

    /**
     * Method for printing 15 empty lines in terminal window
     * */
    public void printEmptySpace(){
        int numOfLines = 15;
        for (int i = 0; i < numOfLines; i++) {
            System.out.println(" ");

        }
    }

    /**
     * Method for controlling High Score view
     * @param playerList HangmanPlayerList
     * @param wordList HangmanWordList
     * @param listOfWordList ListOfWordList
     * @param activeWordList String
     * */
    public void highScoreList (HangmanPlayerList playerList, HangmanWordList wordList, ListOfWordList listOfWordList, String activeWordList){
        ArrayList<HangmanPlayer> highScoreList = playerList.getList();
        Collections.sort(highScoreList);
        System.out.println("High Score list:\n" +
                "------------------------------\n");
        for (int i = 0; i < highScoreList.size(); i++) {
            int listNumber = i+1;
            System.out.println(""+ listNumber +". " + highScoreList.get(i).getName() + " with score of: " + highScoreList.get(i).getTotalScore());

        }
        Scanner scan = new Scanner(System.in);
        System.out.println("\n" +
                "to quit back to main menu, enter any character\n");
        String answer = scan.nextLine();
        if (answer != null){
            printEmptySpace();
            startUp(playerList, wordList, listOfWordList, activeWordList);
        }
    }




    /**
     * Method for controlling Play game view.
     * @param wordList HangmanWordlist
     * @param player HangmanPlayer
     * @param playerList HangmanPlayerList
     * @param listOfLists ListOfWordList
     * @param activeWordList String
     * */
    public void playGame(HangmanWordList wordList, HangmanPlayer player, HangmanPlayerList playerList, ListOfWordList listOfLists, String activeWordList){
        Scanner scan = new Scanner(System.in);
        String word = wordList.getRandomWord().getWord();
        int tries = 10;
        ArrayList<Character> theWord = new ArrayList<>();
        ArrayList<Character> gameField = new ArrayList<>();

        for (int i = 0; i < word.length(); i++){
            theWord.add(word.charAt(i));
            gameField.add('_');
        }

        System.out.println("Welcome to a game of Hangman. " + player.getName() + "\n" +
                "--------------------------------\n" +
                "Please enter a letter between a and z.\n" +
                "Enter 0 to quit to Start game menu.");



        while(gameField.contains('_') && tries != 0){
            System.out.println("You have " + tries + " left");
            System.out.println(gameField.toString());
            char playerLetter = scan.next().toLowerCase().charAt(0);
            if ( (playerLetter >= 'a' && playerLetter <= 'z') ) {
                boolean rightLetter = false;
                for (int i = 0; i < theWord.size(); i++) {
                    if(theWord.get(i).equals(playerLetter)){
                        gameField.set(i, playerLetter);
                        rightLetter = true;
                    }
                }
                if(!rightLetter){
                    tries--;
                }
            } else {
                if (playerLetter == '0') {
                    startGame(playerList, wordList, listOfLists, activeWordList);
                } else {
                    System.out.println("input was not within scope!");
                }
            }
        }
        if(tries == 0){
            System.out.println("Tries are at 0.\n" +
                    "Sorry, you got hanged\n" +
                    "\n" +
                    "If you want to try again, enter Y\n" +
                    "If you want to return to main menu, enter any other key");
            char playerAnswer = scan.next().charAt(0);
            printEmptySpace();
            if(playerAnswer == 'y'){
                playGame(wordList, player, playerList, listOfLists, activeWordList);
            } else {
                startUp(playerList, wordList, listOfLists, activeWordList);
            }
        } else if (!gameField.contains('_')){
            player.addToTotalScore(tries);
            playerList.alterPlayer(player);
            System.out.println("Congratulations, you finished the word: " + word + ".\n" +
                    "And that with " + tries + " lives left!\n" +
                    "Be proud!\n" +
                    "Sub menu(enter number of menu item):\n" +
                    "1. Play again\n" +
                    "2. Return to main menu\n" +
                    "3. View high score list\n");
            char playerAnswer = scan.next().toLowerCase().charAt(0);
            switch (playerAnswer) {
                case '1' -> {
                    printEmptySpace();
                    playGame(wordList, player, playerList, listOfLists, activeWordList);
                }
                case '2' -> {
                    printEmptySpace();
                    startUp(playerList, wordList, listOfLists, activeWordList);
                }
                case '3' -> {
                    printEmptySpace();
                    highScoreList(playerList, wordList, listOfLists, activeWordList);
                }
                default -> {
                    System.out.println("No such option, enter any key to return to main menu");
                    String playerAnswer2 = scan.nextLine();
                    if (!playerAnswer2.equals("")) {
                        printEmptySpace();
                        startUp(playerList, wordList, listOfLists, activeWordList);
                    }
                }
            }
        }
    }


    /**
     * Method for controlling handling of passwords view.
     * @param wordList HangmanWordlist
     * @param playerList HangmanPlayerList
     * @param listOfLists ListOfWordList
     * @param activeWordList String
     * @param username String
     * @param password String
     * @param scan Scanner
     * */
    public void handlePassword (HangmanPlayerList playerList, HangmanWordList wordList, ListOfWordList listOfLists, String activeWordList, String username, String password, Scanner scan) {
        System.out.println("\n" +
                "Username is: " + username + "\n" +
                "enter password:\n");
        password = scan.nextLine();

        if(playerList.checkPlayerPassword(username, password)){
            printEmptySpace();
            playGame(wordList, playerList.getPlayer(username), playerList, listOfLists, activeWordList);
        } else {
            System.out.println("It is something wrong with your credentials, enter y to try again.");
            char response = scan.nextLine().toLowerCase().charAt(0);
            if (response == 'y') {
                handlePassword(playerList, wordList, listOfLists, activeWordList, username, "", scan);
            } else {
                startGame(playerList, wordList, listOfLists, activeWordList);
            }
        }
    }

    /**
     * Method for controlling login view
     * @param playerList HangmanPlayerList
     * @param wordList HangmanWordList
     * @param listOfLists ListOfWordList
     * @param activeWordList String
     * */
    public void logIn(HangmanPlayerList playerList, HangmanWordList wordList, ListOfWordList listOfLists, String activeWordList){
        String username;
        Scanner scan = new Scanner(System.in);
        ArrayList<String> printableNames = playerList.getPrintableNames();
        for (String e: printableNames
        ) {
            System.out.println(e);
        }
        System.out.println("--------------------------\n" +
                "Log in with player credentials\n" +
                "---------------------------\n" +
                "Enter your username(enter 0 to cancel):\n");
        username = scan.nextLine();
        if(username.toString().equals("0")){
            System.out.println("Are you sure you want to cancel login? (Y to confirm)");
            String answer = scan.next();
            if (answer.equalsIgnoreCase("y")){
                printEmptySpace();
                startGame(playerList, wordList, listOfLists, activeWordList);
            }
        } else if (playerList.findPlayer(username)){
            handlePassword(playerList, wordList, listOfLists, activeWordList, username, "", scan);
        } else {
            System.out.println("Player does not exist, if you want to try again enter Y\n" +
                    "if you want to go back to Start Game to register enter anything else\n");
            String answer = scan.nextLine().toLowerCase();
            if (answer.equals("y")){
                printEmptySpace();
                logIn(playerList, wordList, listOfLists, activeWordList);
            } else {
                printEmptySpace();
                startGame(playerList, wordList, listOfLists, activeWordList);
            }
        }
    }

    /**
     * Method for checking input for the integer 0
     * @param playerList HangmanPlayerList
     * @param wordList HangmanWordList
     * @param listOfLists ListOfWordList
     * @param activeWordList String
     * @param input String
     * */
    public void checkFor0Cancel(HangmanPlayerList playerList, HangmanWordList wordList, ListOfWordList listOfLists, String activeWordList, String input) {
        if (input.equals("0")) {
            startGame(playerList, wordList, listOfLists, activeWordList);
        }
    }

    /**
     * Method for controlling register player view
     * @param playerList HangmanPlayerList
     * @param wordList HangmanWordList
     * @param listOfLists ListOfWordList
     * @param activeWordList String
     * */
    public void registerPlayer(HangmanPlayerList playerList, HangmanWordList wordList, ListOfWordList listOfLists, String activeWordList){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your desired username: \n" +
                "enter a single 0 if you want to cancel registration at any point");
        String username = scan.nextLine();
        checkFor0Cancel(playerList, wordList, listOfLists, activeWordList, username.toString());
        System.out.println("Your chosen username is: " + username + "\n" +
                "Please enter your desired password: \n");
        String password = scan.nextLine();
        checkFor0Cancel(playerList, wordList, listOfLists, activeWordList, password.toString());
        System.out.println("Your chosen username is: " + username + " \n" +
                "and your password is: " + password + "\n" +
                "enter any character to return to game menu to log on");
        HangmanPlayer newPlayer = new HangmanPlayer(username, password);
        playerList.addPlayer(newPlayer);
        String playerAnswer = scan.nextLine();
        if(playerAnswer != null){
            startGame(playerList, wordList, listOfLists, activeWordList);
        }
    }

    /**
     * Method for controlling Start game view
     * @param playerList HangmanPlayerList
     * @param wordList HangmanWordList
     * @param listOfLists ListOfWordList
     * @param activeWordList String
     * */
    public void startGame(HangmanPlayerList playerList, HangmanWordList wordList, ListOfWordList listOfLists, String activeWordList){
        Scanner scan = new Scanner(System.in);
        System.out.print("Start Game\n" +
                "--------------------------\n" +
                "Please select one of the following menu choices by entering the corresponding number.\n" +
                "1. Log in\n" +
                "2. Register user\n" +
                "3. Quit to main menu\n");
        int currentScan = scan.next().charAt(0);
        if(currentScan > '3' || currentScan < '1') {
            System.out.println("Wrong input, try again.");
            printEmptySpace();
            startGame(playerList, wordList, listOfLists, activeWordList);
        } else {
            printEmptySpace();
            switch (currentScan) {
                case '1' -> {
                    printEmptySpace();
                    logIn(playerList, wordList, listOfLists, activeWordList);
                }
                case '2' -> {
                    printEmptySpace();
                    registerPlayer(playerList, wordList, listOfLists, activeWordList);
                }
                case '3' -> {
                    System.out.println("Are you sure you want to quit? (Y for confirm)");
                    String answer = scan.next();
                    if (answer.equalsIgnoreCase("y")) {
                        startUp(playerList, wordList, listOfLists, activeWordList);
                    } else {
                        startGame(playerList, wordList, listOfLists, activeWordList);
                    }
                }
            }
        }
    }

    /**
     * Method for controlling import wordlist view
     * @param playerList HangmanPlayerList
     * @param wordList HangmanWordList
     * @param listOfLists ListOfWordList
     * @param activeWordList String
     * */
    public void importWordlist(HangmanPlayerList playerList, HangmanWordList wordList, ListOfWordList listOfLists, String activeWordList){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter name of txt format file that contains your list of words: ");
        String answer = scan.nextLine();
        WordListPersistance listSave = new WordListPersistance();
        HangmanWordList newList = new HangmanWordList(answer);
        listSave.populateWordLists(newList);
        if (newList.getWordList().size() != 0) {
            listOfLists.addWordList(newList);
            listSave.writeListOfListFile(listOfLists);
        }
        System.out.println("enter any character to return to main menu\n");
        String answer2 = scan.nextLine();
        if(answer2 != null){
            startUp(playerList, wordList, listOfLists, activeWordList);
        }
    }

    /**
     * Method for controlling Select wordlist view
     * @param playerList HangmanPlayerList
     * @param wordList HangmanWordList
     * @param listOfLists ListOfWordList
     * @param activeWordList String
     * */
    public void setWordList(HangmanPlayerList playerList, HangmanWordList wordList, ListOfWordList listOfLists, String activeWordList){
        Scanner scan = new Scanner(System.in);
        System.out.println("list of wordlists: ");
        for (HangmanWordList e:listOfLists.getListOfLists()) {
            System.out.println(e.getListName());
        }
        System.out.println("\n" +
                "Active wordlist is: " + activeWordList + "\n" +
                "Enter the selected list you want to play from: ");
        String answer = scan.nextLine();
        boolean isPresent = false;
        for (HangmanWordList e: listOfLists.getListOfLists()) {
            if (answer.equals(e.getListName())) {
                isPresent = true;
                break;
            }

        }
        if(isPresent){
            activeWordList = answer;
            wordList = listOfLists.getListByName(activeWordList);
            System.out.println(activeWordList + "is now the active list\n" +
                    "enter any character to return to main menu\n");
            String answer2 = scan.nextLine();
            if (answer2 != null){
                startUp(playerList, wordList, listOfLists, activeWordList);
            }
        } else {
            System.out.println("Input is wrong, do you you want to try again? enter Y\n" +
                    "else enter any other character");
            String answer3 = scan.nextLine();
            if(answer3.equalsIgnoreCase("y")){
                printEmptySpace();
                setWordList(playerList, wordList, listOfLists, activeWordList);
            } else {
                printEmptySpace();
                startUp(playerList, wordList, listOfLists, activeWordList);
            }
        }
    }

    /**
     * Method for controlling Start up view
     * @param playerList HangmanPlayerList
     * @param wordList HangmanWordList
     * @param listOfLists ListOfWordList
     * @param activeWordList String
     * */
    public void startUp(HangmanPlayerList playerList, HangmanWordList wordList, ListOfWordList listOfLists, String activeWordList){
        Scanner scan = new Scanner(System.in);
        System.out.print("Welcome to The HangMan\n" +
                "-----------------------\n" +
                "Please select one of the following menu choices by entering the corresponding number.\n" +
                "\n" +
                "1. Start Game\n" +
                "2. Import word list\n" +
                "3. Select word list\n" +
                "4. View high score list\n" +
                "5. Quit game\n");

        int currentScan = scan.next().charAt(0);

        printEmptySpace();
        if (currentScan <= '5' && currentScan >= '1' ) {
            switch (currentScan) {
                case '1' -> startGame(playerList, wordList, listOfLists, activeWordList);
                case '2' -> importWordlist(playerList, wordList, listOfLists, activeWordList);
                case '3' -> setWordList(playerList, wordList, listOfLists, activeWordList);
                case '4' -> highScoreList(playerList, wordList, listOfLists, activeWordList);
                case '5' -> {
                    System.out.println("Are you sure you want to quit? (Y for confirm)");
                    String answer = scan.next().toLowerCase();
                    if (answer.equalsIgnoreCase("y")) {
                        PlayerListPersistance listSaver = new PlayerListPersistance();
                        listSaver.savePlayerListToFile(playerList);
                        System.exit(0);
                    } else {
                        startUp(playerList, wordList, listOfLists, activeWordList);
                    }
                }
            }
        } else {
            System.out.println("Wrong input, try again");
            startUp(playerList, wordList, listOfLists, activeWordList);
        }

    }
}
