package fall2019.csc207.snowmanadventure.Models.GameMenu;

import fall2019.csc207.snowmanadventure.DataBase.GameData;

/** This is a builder that build a game data */
public class GameBuilder {

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
   * Constructor for GameBuilder
   *
   * @param healthPoint health point
   * @param speed the speed
   * @param currScore the score
   * @param endGame the game is end
   */
  public GameBuilder(Integer healthPoint, Integer speed, Integer currScore, boolean endGame) {
    this.healthPoint = healthPoint;
    this.speed = speed;
    this.currScore = currScore;
    this.endGame = endGame;
  }

  public GameData build() {
    return new GameData(this);
  }

  public Integer getHealthPoint() {
    return healthPoint;
  }

  public Integer getSpeed() {
    return speed;
  }

  public Integer getPrevScore() {
    return prevScore;
  }

  public GameBuilder setPrevScore(Integer prevScore) {
    this.prevScore = prevScore;
    return this;
  }

  public Integer getCurrScore() {
    return currScore;
  }

  public Integer getGoal() {
    return goal;
  }

  public GameBuilder setGoal(Integer goal) {
    this.goal = goal;
    return this;
  }

  public int getTimeCount() {
    return timeCount;
  }

  public GameBuilder setTimeCount(Integer timeCount) {
    this.timeCount = timeCount;
    return this;
  }

  public boolean isEndGame() {
    return endGame;
  }

  public int getNextY() {
    return nextY;
  }

  public GameBuilder setNextY(Integer nextY) {
    this.nextY = nextY;
    return this;
  }
}
