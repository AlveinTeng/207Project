package fall2019.csc207.snowmanadventure.Models.ScoreBoard;

/** this is a class that stores the player's name and its scores */
public class PlayerScore implements Comparable<PlayerScore> {
  /** The username of the player. */
  private String name;

  /**
   * The scores of the players. Index 0: the score of GameOne: SkyLadder Index 1: the score of
   * GameTwo: FlyingSnowman Index 2: the score of GameThree: CatchingBall Index 3: the score of
   * GameFour: MagicCard Index 4: the score of GameFive: MemoryCard
   */
  private int[] scores;

  /**
   * The constructor for the player score
   *
   * @param name the game for the player
   * @param scores the array of scores
   */
  public PlayerScore(String name, int[] scores) {
    this.name = name;
    this.scores = scores;
  }

  /**
   * Get the name for the player
   *
   * @return the name for the player
   */
  public String getName() {
    return name;
  }

  /**
   * Get the score in specific game
   *
   * @param i the index of the array
   * @return the score in specific game
   */
  int getScore(int i) {
    return scores[i];
  }

  /**
   * Get the sum of the scores
   *
   * @return the sum of the scores
   */
  int sumOfScores() {
    int score = 0;
    for (int value : scores) {
      score += value;
    }
    return score;
  }

  /**
   * Compare two player's total score
   *
   * @return compare two player's score
   */
  @Override
  public int compareTo(PlayerScore o) {
    return -sumOfScores() + o.sumOfScores();
  }
}
