package fall2019.csc207.snowmanadventure.Models.Game.SkyLadder;

/** This is the GameStatus of SkyLadder. */
public enum GameStatus {
  /**
   * The initial status of the game.
   *
   * <p>In this status the Snowman is standing in the current ladder and do nothing.
   */
  STAND,

  /**
   * The second status of the game.
   *
   * <p>When status is in STAND and player touch the screen, it will enter this status.
   *
   * <p>The snowman start to store power to throw rope.
   */
  HOLD,

  /** The status where the snowman is throwing the rope */
  THROW,

  /**
   * The status where the snowman succeed to target at the next ladder.
   *
   * <p>In this status the snowman go up and the length of the rope decrease.
   */
  UP,

  /**
   * The status after *UP*
   *
   * <p>The snowman and the ladder go down.
   */
  DOWN,

  /** The status where the rope failed to catch the ladder. */
  FAILED,

  /** The game is ended. */
  END
}
