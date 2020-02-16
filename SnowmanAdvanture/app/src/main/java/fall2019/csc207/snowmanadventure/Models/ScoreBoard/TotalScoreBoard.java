package fall2019.csc207.snowmanadventure.Models.ScoreBoard;

import java.util.ArrayList;

import fall2019.csc207.snowmanadventure.DataBase.Global;

/** The ScoreBoard to compare and show the total score. */
public class TotalScoreBoard extends ScoreBoard {

  /** Constructor. The gameNum should be 0. */
  public TotalScoreBoard(Global global, int gameNum) {
    super(global, gameNum);
  }

  /**
   * The scores shown in this ScoreBoard should be the sum of all games, so the getter of Scores
   * should override.
   */
  @Override
  public ArrayList<Integer> getScores() {
    ArrayList<Integer> scores = new ArrayList<>();
    for (PlayerScore player : getPlayerScores()) scores.add(player.sumOfScores());
    return scores;
  }
}
