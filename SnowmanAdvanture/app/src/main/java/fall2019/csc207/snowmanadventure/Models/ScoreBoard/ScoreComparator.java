package fall2019.csc207.snowmanadventure.Models.ScoreBoard;

import java.util.Comparator;

/**
 * This is a comparator applied in PlayerScore.
 *
 * <p>This comparator can sort the users' scores of different games
 */
public class ScoreComparator implements Comparator<PlayerScore> {

  /**
   * The game number this comparator compares. 0: SkyLadder 1: FlyingSnowman 2: CatchingBall 3:
   * MagicCard 4: MemoryCard
   */
  private int gameNum;

  /**
   * Constructor of the comparator.
   *
   * @param gameNum the number of game which want to be compared by this comparator.
   */
  ScoreComparator(int gameNum) {
    this.gameNum = gameNum;
  }

  @Override
  public int compare(PlayerScore player1, PlayerScore player2) {
    return player2.getScore(gameNum) - player1.getScore(gameNum);
  }
}
