package fall2019.csc207.snowmanadventure.DataBase;

import java.io.Serializable;

import fall2019.csc207.snowmanadventure.Models.GameMenu.GameBuilder;

/** A class to store data */
public class GameData implements Serializable {

  /** VersionId */
  private static final long serialVersionUID = 29475937459372L;

  /** Boolean to indicate whether the game is end */
  private boolean endGame;

  /** Speed for items */
  private Integer speed;

  /** Previous Score */
  private Integer prevScore;

  /** Current Score */
  private Integer currScore;

  /** Count the time */
  private int timeCount;

  /** Health point of player */
  private Integer healthPoint;

  /** Y coordinate of next item */
  private int nextY;

  /** The goal score */
  private Integer goal;

  /**
   * Constructor for GameData
   *
   * @param gameBuilder the corresponding game builder
   */
  public GameData(GameBuilder gameBuilder) {
    healthPoint = gameBuilder.getHealthPoint();
    endGame = gameBuilder.isEndGame();
    speed = gameBuilder.getSpeed();
    prevScore = gameBuilder.getPrevScore();
    currScore = gameBuilder.getCurrScore();
    timeCount = gameBuilder.getTimeCount();
    nextY = gameBuilder.getNextY();
    goal = gameBuilder.getGoal();
  }

  /** @return The HealthPoint for a player */
  public Integer getHealthPoint() {
    return healthPoint;
  }

  /** @return The speed of an item */
  public Integer getSpeed() {
    return speed;
  }

  /** @return The previous score */
  public Integer getPrevScore() {
    return prevScore;
  }

  /** @return The current Score */
  public Integer getCurrScore() {
    return currScore;
  }

  /** @return the goal */
  public Integer getGoal() {
    return goal;
  }

  /** @return The time a game has run */
  public int getTimeCount() {
    return timeCount;
  }

  /** @return Whether a game is end */
  public boolean isEndGame() {
    return endGame;
  }

  /** @return Get the Y coordinate of next item */
  public int getNextY() {
    return nextY;
  }
}
