package fall2019.csc207.snowmanadventure.Models.GameManagers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

import fall2019.csc207.snowmanadventure.DataBase.GameData;
import fall2019.csc207.snowmanadventure.Models.Game.DrawItem;
import fall2019.csc207.snowmanadventure.Models.Game.FlyingSnowman.FlyingItem;
import fall2019.csc207.snowmanadventure.Models.Game.FlyingSnowman.Ladder;
import fall2019.csc207.snowmanadventure.Models.Game.FlyingSnowman.Snowball;
import fall2019.csc207.snowmanadventure.Models.Game.FlyingSnowman.SnowmanTwo;
import fall2019.csc207.snowmanadventure.Models.GameMenu.GameBuilder;
import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManager;

public class FlyingSnowmanManager implements GameManager {

  /** The width of screen window. */
  private int screenWidth;

  /** The height of screen window. */
  private int screenHeight;

  /** All items in flyingSnowMan */
  private List<FlyingItem> allItems = new ArrayList<>();

  /** All items to draw */
  private List<DrawItem> drawItems = new ArrayList<>();

  /** The player snowman */
  private SnowmanTwo snowmanTwo;

  /** Manager for image in FlyingSnowMan */
  private ImageManager imageManager;

  /** The boolean indicating whether the game should be end */
  private boolean endGame = false;

  /** Random number to generate items */
  private double r;

  /** Speed of the items in game */
  private Integer speed;

  /** The Paint of the FlyingSnowman object */
  private Paint paint;

  /** Boolean to check whether the screen is touched by user */
  private boolean isTouched;

  /** The health point of the player */
  private Integer healthPoint;

  /** The score of game */
  private Integer score;

  /**
   * Constructor for the FlyingSnowmanManager
   *
   * @param imageManager the image manager
   */
  public FlyingSnowmanManager(ImageManager imageManager) {
    screenWidth = imageManager.getWidth() / imageManager.getUnit();
    screenHeight = imageManager.getHeight() / imageManager.getUnit();
    this.imageManager = imageManager;
    paint = new Paint();
    paint.setColor(Color.GREEN);
    paint.setTextSize(12 * this.imageManager.getUnit());
  }

  /**
   * Draw all the items in game
   *
   * @param canvas the canvas
   */
  @Override
  public void draw(Canvas canvas) {
    for (DrawItem item : drawItems) {
      item.draw(canvas);
    }
    drawNumbers(canvas);
  }

  /**
   * Draw the numbers
   *
   * @param canvas canvas
   */
  private void drawNumbers(Canvas canvas) {
    canvas.drawText(
        ("Score: " + score.toString()),
        10 * imageManager.getUnit(),
        20 * imageManager.getUnit(),
        paint);
    canvas.drawText(
        ("Speed: " + speed.toString()),
        10 * imageManager.getUnit(),
        40 * imageManager.getUnit(),
        paint);
    canvas.drawText(
        ("HP: " + healthPoint.toString()),
        10 * imageManager.getUnit(),
        60 * imageManager.getUnit(),
        paint);
  }

  /** Update the status of game */
  @Override
  public void update() {
    if (healthPoint == 0) {
      endGame();
      return;
    }
    if (drawItems.size() == 0) {
      initItems();
    }
    for (int i = 0; i < allItems.size(); i++) {
      FlyingItem item = allItems.get(i);
      if (item != null) {
        if (checkHitItem(item)) {
          if (item instanceof Ladder) {
            decreaseHealthPoint();
          } else {
            score += 10;
            checkScore(score);
          }
          item.die();
        }
        if (!item.isExist()) {
          allItems.remove(item);
          drawItems.remove(item);
          i--;
        } else {
          item.setSpeed(speed);
          item.update();
        }
      }
    }
    snowmanTwo.update(this.isTouched);
    randomAddItem();
  }

  /** Decrease the healthPoints of SnowMan when hits ladder bar */
  private void decreaseHealthPoint() {
    healthPoint--;
  }

  /** Update the score of SnowMan */
  private void checkScore(Integer score) {
    if (score != 0 && score % 100 == 0) {
      speed++;
    }
  }

  /** Randomly add the item */
  private void randomAddItem() {
    r = Math.random();

    if (r < 0.5) {
      r = Math.random();
      int initY = (int) Math.floor(Math.sqrt(r) * (screenHeight));
      r = Math.random();
      if (r > 0.95) {
        addItem(screenWidth + 4 * imageManager.getUnit(), initY, new Ladder(imageManager));
      } else if (r < 0.15) {
        addItem(screenWidth + 4 * imageManager.getUnit(), initY, new Snowball(imageManager));
      }
    }
  }

  /** End the game */
  private void endGame() {
    endGame = true;
  }

  /** @return whether the game is end */
  @Override
  public boolean isEnd() {
    return endGame;
  }

  /** Check the movement */
  @Override
  public void checkMovement(float currX) {}

  /**
   * Check whether the SnowMan hit an item
   *
   * @param item The flying items in game
   * @return whether the SnowMan hit an item
   */
  private boolean checkHitItem(FlyingItem item) {
    int boundX =
        (item instanceof Ladder)
            ? imageManager.getBitmapLadder().getWidth()
            : imageManager.getUnit();
    int boundY =
        (item instanceof Ladder)
            ? imageManager.getBitmapLadder().getHeight()
            : imageManager.getUnit();

    return (item.getX()
            < snowmanTwo.getX() + snowmanTwo.getBitmap().getWidth() / imageManager.getUnit()
        && item.getY()
            < snowmanTwo.getY() + snowmanTwo.getBitmap().getHeight() / imageManager.getUnit()
        && item.getX() + boundX / imageManager.getUnit() > snowmanTwo.getX()
        && item.getY() + boundY / imageManager.getUnit() > snowmanTwo.getY());
  }

  /**
   * Add item in game
   *
   * @param x an int representing the x coordinates
   * @param y an int representing the y coordinates
   * @param item a CatchingItem to be added
   */
  private void addItem(int x, int y, FlyingItem item) {
    item.setLocation(x, y);
    item.setBound(screenWidth, screenHeight);
    if (item instanceof SnowmanTwo) {
      snowmanTwo = (SnowmanTwo) item;
    } else {
      item.setSpeed(speed);
      allItems.add(item);
    }
    drawItems.add(item);
  }

  /** init items in FlyingSnowMan */
  private void initItems() {
    SnowmanTwo snowman = new SnowmanTwo(imageManager);
    addItem(0, imageManager.getBitmapSnowman()[0].getHeight(), snowman);
    addItem(25, 50, new Ladder(imageManager));
    addItem(10, 25, new Snowball(imageManager));
  }

  /**
   * Checked whether the screen is touched
   *
   * @param isTouched whether the user is touched the screen
   */
  @Override
  public void checkIsTouched(boolean isTouched) {
    this.isTouched = isTouched;
  }

  /** @return GameData which need to be stored */
  @Override
  public GameData getGameData() {
    return new GameBuilder(healthPoint, speed, score, endGame).build();
  }

  /**
   * Renew the data
   *
   * @param gameData a GameData used to renew
   */
  @Override
  public void renewData(GameData gameData) {
    if (gameData == null || gameData.isEndGame()) {
      this.healthPoint = 10;
      this.endGame = false;
      this.speed = 1;
      this.score = 0;
    } else {
      this.healthPoint = gameData.getHealthPoint();
      this.endGame = gameData.isEndGame();
      this.speed = gameData.getSpeed();
      this.score = gameData.getCurrScore();
    }
  }
}
