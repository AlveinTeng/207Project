package fall2019.csc207.snowmanadventure.DataBase;

import android.app.Activity;
import android.content.Context;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** A class to store all game data */
public class Global implements Serializable {

  /** User Id */
  private static final long serialVersionUID = 1001089493218779234L;

  /** Map to store Account */
  private Map<String, Account> accounts = new HashMap<>();
  /** Map to store game */
  private Map<String, LocalGame> games = new HashMap<>();
  /** Current User */
  private String currUser;

  public Map<String, LocalGame> getGames() {
    return games;
  }

  /**
   * Constructor of the Choice
   *
   * @param change the change for background
   */
  public void changeChoice(boolean change) {
    LocalGame game = games.get(currUser);
    if (game != null) {
      game.changeChoice(change);
    }
  }

  /**
   * get the choice for the background
   *
   * @return Choice
   */
  public boolean getChoice() {
    LocalGame game = games.get(currUser);
    if (game != null) {
      return game.getChoice();
    }
    return false;
  }

  /**
   * Sign up for a new user
   *
   * @param userName the user name
   * @param firstPassword the password
   */
  public void signUp(String userName, String firstPassword) {
    addAccount(userName, firstPassword);
    addGame(userName);
  }

  /**
   * Add a new game
   *
   * @param userName the user name
   */
  private void addGame(String userName) {
    LocalGame newGame = new LocalGame();
    games.put(userName, newGame);
  }

  /**
   * @param userName the user name
   * @return An UserName for a local game
   */
  public LocalGame getLocal(String userName) {
    return games.get(userName);
  }

  /** @return The current User */
  public String getCurrUser() {
    return currUser;
  }

  /**
   * Add a new Account
   *
   * @param userName the user name
   * @param firstPassword the password
   */
  private void addAccount(String userName, String firstPassword) {
    Account newAccount = new Account(firstPassword);
    accounts.put(userName, newAccount);
  }

  /**
   * Check whether a User is exist
   *
   * @param userName the user name
   * @return whether the user is exist
   */
  public boolean checkUserExist(String userName) {
    return accounts.containsKey(userName);
  }

  /**
   * @param userName the user name
   * @param password the password
   * @return Whether signIn is successful
   */
  public boolean signIn(String userName, String password) {
    if (accounts.get(userName).signIn(password)) {
      currUser = userName;
      return true;
    }
    return false;
  }

  /**
   * Save all data we need
   *
   * @param context the context
   */
  public void saveAll(Context context) {
    ObjectOutputStream out = null;
    try {
      FileOutputStream fileOut = context.openFileOutput("database_8390.ser", Activity.MODE_PRIVATE);
      out = new ObjectOutputStream(fileOut);
      out.writeObject(this);
      fileOut.getFD().sync();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (out != null) {
        try {
          out.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /** get a hash map that has the key username and the value array of each game's score */
  public Map<String, int[]> playerScores() {
    Map<String, int[]> playerScore = new HashMap<>();
    List<String> players = new ArrayList<>(games.keySet());
    for (int i = 0; i < players.size(); i++) {
      LocalGame localGame = games.get(players.get(i));
      if (localGame != null) {
        playerScore.put(
            (localGame.isSaveName()) ? players.get(i) : String.format("P%s", i + 1),
            localGame.getScores());
      }
    }
    return playerScore;
  }

  /**
   * get whether the reward dialogue has been displayed
   *
   * @param isCurr whether the dialogue display once or stay the displayed state
   * @return whether the dialogue display once or stay the displayed state
   */
  public boolean getDisplay(boolean isCurr) {
    LocalGame game = games.get(currUser);
    if (game != null) {
      return game.isDisplay(isCurr);
    }
    return false;
  }

  /**
   * get whether the user want to use thug image
   *
   * @return whether the user want to use thug image
   */
  public boolean getWantThug() {
    LocalGame game = games.get(currUser);
    if (game != null) {
      return game.getWantThug();
    }
    return false;
  }

  /**
   * change image of the snowman
   *
   * @param index the position of the view pager
   */
  public void changeThug(int index) {
    LocalGame game = games.get(currUser);
    if (game != null) {
      game.changeThug(index);
    }
  }
}
