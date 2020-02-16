package fall2019.csc207.snowmanadventure.Models.GameManagers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import fall2019.csc207.snowmanadventure.DataBase.GameData;
import fall2019.csc207.snowmanadventure.Models.Game.SkyLadder.GameStatus;
import fall2019.csc207.snowmanadventure.Models.Game.SkyLadder.Rope;
import fall2019.csc207.snowmanadventure.Models.Game.SkyLadder.SkyLadder;
import fall2019.csc207.snowmanadventure.Models.Game.SkyLadder.Snowman;
import fall2019.csc207.snowmanadventure.Models.GameMenu.GameBuilder;
import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManager;

/**
 * This is the GameManager of the first game SkyLadder.
 *
 * <p>In this game there are four items: Snowman/Rope/Current Ladder/Next Ladder.
 *
 * <p>And there are seven different *status* in the process of this game, which shown in
 * GameStatus.enum in the package SkyLadder, and stored in the attribute
 * SkyLadderManager.GameStatus.
 *
 * <p>The main task of this Manager is control the flow of changing *GameStatus* and control all
 * items in this game following the current status.
 */
public class SkyLadderManager implements GameManager {

  /** The allowable error */
  private final int error = 10;
  /** The height of screen window. */
  private int screenHeight;
  /** The snowman */
  private Snowman snowman;
  /** The rope of the snowman */
  private Rope rope;
  /** The ladder which snowman stand currently */
  private SkyLadder current;
  /** The ladder which snowman target at */
  private SkyLadder next;
  /** The number of chances of failing */
  private Integer healthPoint;
  /**
   * The status of playing.
   *
   * <p>This is the most important attribute in this GameManager. It implies how the items update
   * themselves. And items can tell Manager if GameStatus need to be changed by return a boolean
   * value in the method update.
   */
  private GameStatus status;

  /** The levels that the snowman has stepped */
  private Integer level;

  /** The score the player get, which is numerically equal to the height the player climbs. */
  private Integer score;

  /** ImageManger for Game one */
  private ImageManager imageManager;

  /**
   * Whether the phone screen is touched
   *
   * <p>This is used for switch GameStatus.
   */
  private boolean isTouched;

  private Paint paint;

  /** Game data of Game one */
  private GameData gameData;

  private int nextY;

  /**
   * Constructor for SkyLadderManager
   *
   * @param imageManager
   */
  public SkyLadderManager(ImageManager imageManager) {
    screenHeight = imageManager.getHeight() / imageManager.getUnit();
    this.imageManager = imageManager;
    paint = new Paint();
    paint.setColor(Color.RED);
    paint.setTextSize(6 * this.imageManager.getUnit());
  }

  /**
   * Draw items.
   *
   * <p>Just call items' draw simply.
   */
  @Override
  public void draw(Canvas canvas) {
    snowman.draw(canvas);
    rope.draw(canvas);
    current.draw(canvas);
    next.draw(canvas);
    drawNumbers(canvas);
  }

  /** Draw the number information of this game. */
  private void drawNumbers(Canvas canvas) {
    canvas.drawText("Current Score: " + score.toString(), 0, 25 * imageManager.getUnit(), paint);
    canvas.drawText("Level: " + level.toString(), 0, 15 * imageManager.getUnit(), paint);
    canvas.drawText("HP: " + healthPoint.toString(), 0, 5 * imageManager.getUnit(), paint);
  }

  /**
   * Update the game. Call this method every frame.
   *
   * <p>In the update process, SkyLadderManager pass the *status* to all items and receive a boolean
   * value showing whether if the *status* need to be changed.
   *
   * <p>If the status need change, call the method changeStatus.
   *
   * <p>Items update following the status.
   */
  @Override
  public void update() {
    boolean needChange = isTouched;
    needChange |= snowman.update(status);
    needChange |= rope.update(status);
    needChange |= current.update(status);
    needChange |= next.update(status);
    if (status == GameStatus.DOWN) checkExist();
    if (needChange) changeStatus();
  }

  /** Remove the ladder that out of screen and add ladder at the same time. */
  private void checkExist() {
    if (current.getY() >= screenHeight) {
      current = next;
      int newY = (int) ((Math.random() * 0.8 * snowman.getY()) + 0.1 * snowman.getY());
      next = new SkyLadder(26, newY, imageManager);
      rope.setTarget(next);
    }
  }

  /**
   * Change to next status.
   *
   * <p>This method shows how the game update between different status.
   */
  private void changeStatus() {
    GameStatus newStatus = status;
    switch (status) {
      case STAND:
        newStatus = GameStatus.HOLD;
        break;
      case HOLD:
        newStatus = GameStatus.THROW;
        break;
      case THROW:
        if (inTarget()) {
          newStatus = GameStatus.UP;
          addPoint(next.getY() - current.getY());
        } else {
          newStatus = GameStatus.FAILED;
          loseHealthPoint();
        }
        break;
      case UP:
        newStatus = GameStatus.DOWN;
        break;
      case DOWN:
        newStatus = GameStatus.STAND;
        break;
      case FAILED:
        if (healthPoint <= 0) {
          newStatus = GameStatus.END;
        } else {
          newStatus = GameStatus.STAND;
        }
      default:
        break;
    }
    status = newStatus;
    if (newStatus == GameStatus.STAND || newStatus == GameStatus.END) checkPoint();
  }

  /** Check the game state to store the data */
  private void checkPoint() {
    nextY = next.getY();
    gameData = new GameBuilder(healthPoint, level, score, isEnd()).setNextY(nextY).build();
  }

  /** Initialize the items */
  private void initItems() {
    snowman = new Snowman(40, screenHeight - 53, imageManager);
    rope = new Rope(53, screenHeight - 55, imageManager);
    current = new SkyLadder(26, screenHeight - 20, imageManager);
    next = new SkyLadder(26, nextY, imageManager);
    rope.setTarget(next);
  }

  /**
   * This is used to determine if it is succeeded
   *
   * @return Whether the target is hit
   */
  private boolean inTarget() {
    int fin_y = rope.getY() - rope.getLength();
    int target = next.getY();
    return target - error < fin_y && fin_y < target + error;
  }

  /** @return Whether the game is End */
  @Override
  public boolean isEnd() {
    return status == GameStatus.END;
  }

  /**
   * Lose an HealthPoint
   *
   * <p>Used when failing to reach the next ladder.
   */
  private void loseHealthPoint() {
    healthPoint--;
  }

  /**
   * Add the score point.
   *
   * @param height the height between current ladder and next ladder.
   */
  private void addPoint(int height) {
    level++;
    score -= height;
  }

  /**
   * Check whether the screen is touched.
   *
   * @param isTouched whether the screen is touched.
   */
  @Override
  public void checkIsTouched(boolean isTouched) {
    if (status == GameStatus.STAND) {
      this.isTouched = isTouched;
    } else if (status == GameStatus.HOLD) {
      this.isTouched = !isTouched;
    } else {
      this.isTouched = false;
    }
  }

  /** Check the movement of item */
  @Override
  public void checkMovement(float currX) {}

  /** A getter for gameData. */
  @Override
  public GameData getGameData() {
    return gameData;
  }

  /**
   * Renew Data Call this function when load game.
   *
   * @param gameData the gameDate needed to be read.
   */
  @Override
  public void renewData(GameData gameData) {
    if (gameData == null || gameData.isEndGame()) {
      healthPoint = 10;
      level = 0;
      score = 0;
      nextY = screenHeight - 100;
    } else {
      this.healthPoint = gameData.getHealthPoint();
      this.level = gameData.getSpeed();
      this.score = gameData.getCurrScore();
      nextY = gameData.getNextY();
    }
    status = GameStatus.STAND;
    initItems();
    checkPoint();
  }
}
