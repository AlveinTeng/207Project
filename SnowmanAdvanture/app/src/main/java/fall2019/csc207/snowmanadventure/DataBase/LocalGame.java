package fall2019.csc207.snowmanadventure.DataBase;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import fall2019.csc207.snowmanadventure.Models.GameMenu.Games;

/** A class to store local game */
public class LocalGame implements Serializable {

  /** Version Id */
  private static final long serialVersionUID = 1095943473894725L;

  /** Hash map to GameData */
  private Map<Games, GameData> gameArchives;

  /** whether the user want to display the score */
  private boolean saveScore;

  /** whether the user want to display the name */
  private boolean saveName;

  /** whether the user want to change the image of the snowman */
  private boolean wantThug;

  /** Boolean choice */
  private boolean choice;

  /** attribute indicating if the reward dialog has displayed ever! */
  private boolean displayReward;

  /** the array of each game's score */
  private int[] scores = new int[6];

  /** Constructor for LocalGame */
  public LocalGame() {
    gameArchives = new HashMap<>();
  }

  /**
   * Return the specific game data
   *
   * @param gameName the name for the game
   * @return the specific game data
   */
  public GameData loadGame(Games gameName) {
    if (!gameArchives.containsKey(gameName)) {
      gameArchives.put(gameName, null);
    }
    return gameArchives.get(gameName);
  }

  /**
   * Save the game to the map
   *
   * @param gameName the name for the game
   * @param gameData the game data for the game
   */
  public void saveGame(Games gameName, GameData gameData) {
    gameArchives.put(gameName, gameData);
  }

  /**
   * Get the array that contains each game's data
   *
   * @return the array that contains each game's data
   */
  int[] getScores() {
    if (saveScore) {
      updateScores();
      return scores;
    }
    return new int[6];
  }

  /** Update the array of scores (only save the highest score) */
  private void updateScores() {
    Games[] games = Games.values();

    for (int i = 0; i < scores.length; i++) {
      int score = getScore(games[i]);
      scores[i] = (scores[i] < score) ? score : scores[i];
    }
  }

  /**
   * get the score in specific game
   *
   * @param game the game name
   * @return the score in the game data
   */
  private int getScore(Games game) {
    GameData gameData = gameArchives.get(game);
    if (gameData == null) {
      return 0;
    }
    return gameData.getCurrScore();
  }

  /**
   * get whether the reward dialogue has been displayed
   *
   * @param isCurr whether the dialogue display once or stay the displayed state
   * @return whether the dialogue display once or stay the displayed state
   */
  boolean isDisplay(boolean isCurr) {
    if (!isCurr) {
      return displayReward;
    }
    if (displayReward) {
      return false;
    }
    if (getTotalScore() >= 500) {
      displayReward = true;
    }
    return displayReward;
  }

  /**
   * get the total score
   *
   * @return the total score
   */
  private int getTotalScore() {
    updateScores();
    int sum = 0;
    for (int i : scores) {
      sum += i;
    }
    return sum;
  }

  /**
   * get whether the user want to use thug image
   *
   * @return whether the user want to use thug image
   */
  public boolean getWantThug() {
    return wantThug;
  }

  /**
   * change image of the snowman
   *
   * @param index the position of the view pager
   */
  void changeThug(int index) {
    wantThug = displayReward && (index == 1);
  }

  /**
   * change the choice for using the background
   *
   * @param change the choice for using the background
   */
  void changeChoice(boolean change) {
    choice = change;
  }

  public boolean getChoice() {
    return choice;
  }

  /**
   * whether the score is displayed
   *
   * @return whether the score is displayed
   */
  public boolean isSaveScore() {
    return saveScore;
  }

  /**
   * the user want to displayed the score
   *
   * @param saveScore whether the user want to displayed the score
   */
  public void setSaveScore(boolean saveScore) {
    this.saveScore = saveScore;
  }

  /**
   * whether the score is displayed
   *
   * @return whether the score is displayed
   */
  public boolean isSaveName() {
    return saveName;
  }

  /**
   * the user want to displayed the name
   *
   * @param saveName whether the user want to displayed the name
   */
  public void setSaveName(boolean saveName) {
    this.saveName = saveName;
  }
}
