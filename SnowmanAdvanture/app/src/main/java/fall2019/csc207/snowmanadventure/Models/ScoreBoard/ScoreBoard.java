package fall2019.csc207.snowmanadventure.Models.ScoreBoard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import fall2019.csc207.snowmanadventure.DataBase.Global;

/** The ScoreBoard used to store, sort and show score. */
public class ScoreBoard {

  /** All players' scores information */
  private ArrayList<PlayerScore> playerScores = new ArrayList<>();

  /**
   * The number of game this board want to show. 0: SkyLadder 1: FlyingSnowman 2: CatchingBall 3:
   * MagicCard 4: MemoryCard
   */
  private int gameNum;

  /**
   * Constructor of ScoreBoard.
   *
   * <p>The constructor will sort the PlayerScore by the gameNum.
   */
  public ScoreBoard(Global global, int gameNum) {
    Map<String, int[]> nameScoresMap = global.playerScores();
    this.gameNum = gameNum - 1;
    for (String key : nameScoresMap.keySet()) {
      playerScores.add(new PlayerScore(key, nameScoresMap.get(key)));
    }
    if (gameNum == 0) {
      Collections.sort(playerScores);
    } else {
      Collections.sort(playerScores, new ScoreComparator(this.gameNum));
    }
  }

  /** Getter of PlayerScores */
  ArrayList<PlayerScore> getPlayerScores() {
    return playerScores;
  }

  /** Get the list of names of players */
  public ArrayList<String> getPlayers() {
    ArrayList<String> players = new ArrayList<>();
    for (PlayerScore player : playerScores) players.add(player.getName());
    return players;
  }

  /** Get the list of scores of players */
  public ArrayList<Integer> getScores() {
    ArrayList<Integer> scores = new ArrayList<>();
    for (PlayerScore player : playerScores) scores.add(player.getScore(gameNum));
    return scores;
  }
}
