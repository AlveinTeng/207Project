package fall2019.csc207.snowmanadventure.Models.GameManagers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

import fall2019.csc207.snowmanadventure.DataBase.GameData;
import fall2019.csc207.snowmanadventure.Models.Game.CatchingBall.CatchingItem;
import fall2019.csc207.snowmanadventure.Models.Game.CatchingBall.GreenBall;
import fall2019.csc207.snowmanadventure.Models.Game.CatchingBall.Movement;
import fall2019.csc207.snowmanadventure.Models.Game.CatchingBall.RedBall;
import fall2019.csc207.snowmanadventure.Models.Game.CatchingBall.SnowmanThree;
import fall2019.csc207.snowmanadventure.Models.Game.CatchingBall.TimeBar;
import fall2019.csc207.snowmanadventure.Models.Game.DrawItem;
import fall2019.csc207.snowmanadventure.Models.GameMenu.GameBuilder;
import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManager;

public class CatchingBallManager implements GameManager {

  /** The width of screen window. */
  private int screenWidth;

  /** The height of screen window. */
  private int screenHeight;

  /** The ArrayList containing all CatchingItem objects in game. */
  private List<CatchingItem> allItems = new ArrayList<>();

  /** The ArrayList containing all DrawItems objects in game. */
  private List<DrawItem> drawItems = new ArrayList<>();

  /** The snowman of the game. */
  private SnowmanThree snowmanThree;

  /** The time bar of the game. */
  private TimeBar timeBar;

  /** the image manager of the game. */
  private ImageManager imageManager;

  /** The boolean indicate whether the game end or not. */
  private boolean endGame;

  /** a random double */
  private double r;

  /** The speed of the CatchingItem. */
  private Integer speed;

  /** The movement status. */
  private Movement movement;

  /** The previous score. */
  private Integer prevScore;

  /** The current score. */
  private Integer currScore;

  /** The Paint of the CatchingItem object */
  private Paint paint;

  /** The time remaining for the game */
  private int timeCount;

  /** The health point of the player */
  private Integer healthPoint;

  /**
   * The constructor of the class
   *
   * @param imageManager an ImageManager representing the image manager of the game
   */
  public CatchingBallManager(ImageManager imageManager) {
    screenWidth = imageManager.getWidth() / imageManager.getUnit();
    screenHeight = imageManager.getHeight() / imageManager.getUnit();
    this.imageManager = imageManager;
    paint = new Paint();
    paint.setColor(Color.RED);
    paint.setTextSize(12 * this.imageManager.getUnit());
  }

  /**
   * Draw the image of objects on the screen by the current Canvas
   *
   * @param canvas the current Canvas
   */
  public void draw(Canvas canvas) {
    for (DrawItem item : drawItems) {
      item.draw(canvas);
    }
    drawNumbers(canvas);
  }

  /**
   * Draw the score and health points on the screen.
   *
   * @param canvas the current Canvas
   */
  private void drawNumbers(Canvas canvas) {
    canvas.drawText(
        "Current Score: " + currScore.toString(), 0, 30 * imageManager.getUnit(), paint);
    canvas.drawText("HP: " + healthPoint.toString(), 0, 15 * imageManager.getUnit(), paint);
  }

  /** Update the game and the attributes */
  public void update() {
    if (healthPoint == 0) {
      endGame();
      return;
    }
    if (drawItems.size() == 0) {
      initItems();
    }
    for (int i = 0; i < allItems.size(); i++) {
      CatchingItem item = allItems.get(i);
      if (item != null) {
        if (checkHitItem(item)) {
          increaseScore(item.getScore());
          item.die();
        }
        if (!item.isExist()) {
          allItems.remove(item);
          drawItems.remove(item);
          i--;
        } else {
          item.update();
        }
      }
    }
    snowmanThree.update(movement);
    if (timeBar.getTimeCount() == 0) {
      if (currScore.equals(prevScore) || currScore < 0 || currScore % 2 != 0) {
        decreaseHealthPoint();
      }
      timeCount++;
      checkTimeCount();
      timeBar.resetTime();
      prevScore = currScore;
    } else {
      timeBar.update();
    }
    randomAddItem();
  }

  /**
   * Update the score by adding the given score
   *
   * @param score an Integer representing the score to add
   */
  private void increaseScore(Integer score) {
    currScore += score;
  }

  /** decrease the healthPoint by one */
  private void decreaseHealthPoint() {
    healthPoint--;
  }

  /** Speed increase by one by checking timeCount value */
  private void checkTimeCount() {
    if (timeCount != 0 && timeCount % 5 == 0) {
      speed++;
    }
  }

  /** Add random items to the game */
  private void randomAddItem() {
    r = Math.random();

    if (r < 0.5) {
      r = Math.random();
      int initX = (int) Math.floor(Math.sqrt(Math.random()) * (screenWidth)) - 30;
      r = Math.random();
      if (r > 0.95) {
        addItem(initX, -21, new GreenBall(imageManager), speed);
      } else if (r < 0.03) {
        addItem(initX, -21, new RedBall(imageManager), speed);
      }
    }
  }

  /** Set the game to end */
  private void endGame() {
    endGame = true;
  }

  /**
   * Indicate whether the game ends
   *
   * @return a boolean to indicate whether the game ends
   */
  public boolean isEnd() {
    return endGame;
  }

  /**
   * Add a new CatchingItem to the game
   *
   * @param x an int representing the x coordinates
   * @param y an int representing the y coordinates
   * @param item a CatchingItem to be added
   * @param speed an int representing the speed of the CatchingItem
   */
  private void addItem(int x, int y, CatchingItem item, int speed) {
    item.setLocation(x, y);
    item.setBound(screenWidth, screenHeight);
    if (item instanceof SnowmanThree) {
      snowmanThree = (SnowmanThree) item;
    } else {
      item.setSpeed(speed);
      allItems.add(item);
    }
    drawItems.add(item);
  }

  /** initialize the items */
  private void initItems() {
    addItem(40, -60, new GreenBall(imageManager), speed);
    addItem(80, -10, new RedBall(imageManager), speed);
    addItem(60, screenHeight - 65, new SnowmanThree(imageManager), speed);
    timeBar = new TimeBar(screenWidth - 10, screenHeight - 52, imageManager);
    drawItems.add(timeBar);
  }

  /**
   * Modify the movement status based the given currX
   *
   * @param currX a float representing currX
   */
  public void checkMovement(float currX) {
    int changeX = (int) (screenWidth * imageManager.getUnit() / 2 - currX);
    if (changeX == 0 || currX == 0) {
      movement = Movement.MOVE_STAY;
    } else if (changeX > 0) {
      movement = Movement.MOVE_LEFT;
    } else {
      movement = Movement.MOVE_RIGHT;
    }
  }

  /**
   * Check if the items hit snowmanTree
   *
   * @param item a CatchingItem to be checked
   * @return a boolean indicate whether an item hits snowmanTree
   */
  private boolean checkHitItem(CatchingItem item) {
    return (item.getX() > snowmanThree.getX()
        && item.getY() > snowmanThree.getY()
        && item.getX()
            < snowmanThree.getX() + snowmanThree.getBitmap().getWidth() / imageManager.getUnit()
        && item.getY()
            < snowmanThree.getY() + snowmanThree.getBitmap().getHeight() / imageManager.getUnit());
  }

  /**
   * Check whether the player touch the screen
   *
   * @param isTouched a boolean indicate if the player touch the screen
   */
  @Override
  public void checkIsTouched(boolean isTouched) {}

  /**
   * Getter of the game data
   *
   * @return a new GameData containing all game data
   */
  @Override
  public GameData getGameData() {
    return new GameBuilder(healthPoint, speed, currScore, endGame)
        .setPrevScore(prevScore)
        .setTimeCount(timeCount)
        .build();
  }
  /**
   * renew the game data
   *
   * @param gameData a GameData used to renew
   */

  @Override
  public void renewData(GameData gameData) {
    if (gameData == null || gameData.isEndGame()) {
      healthPoint = 3;
      speed = 2;
      currScore = 0;
      endGame = false;
      prevScore = 0;
      timeCount = 0;
    } else {
      this.healthPoint = gameData.getHealthPoint();
      this.endGame = gameData.isEndGame();
      this.speed = gameData.getSpeed();
      this.prevScore = gameData.getPrevScore();
      this.currScore = gameData.getCurrScore();
      this.timeCount = gameData.getTimeCount();
    }
  }
}
