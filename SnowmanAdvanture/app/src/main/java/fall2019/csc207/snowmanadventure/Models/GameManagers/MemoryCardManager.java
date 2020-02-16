package fall2019.csc207.snowmanadventure.Models.GameManagers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import fall2019.csc207.snowmanadventure.DataBase.GameData;
import fall2019.csc207.snowmanadventure.Models.Game.DrawItem;
import fall2019.csc207.snowmanadventure.Models.Game.MemoryCard.CardMap;
import fall2019.csc207.snowmanadventure.Models.Game.MemoryCard.MemoryCard;
import fall2019.csc207.snowmanadventure.Models.Game.MemoryCard.TimeBarSlow;
import fall2019.csc207.snowmanadventure.Models.GameMenu.GameBuilder;
import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManager;

public class MemoryCardManager implements GameManager {

  /** The boolean indicate whether the game end or not. */
  private boolean endGame;

  /** The score. */
  private Integer score;

  /** The health point of the player */
  private Integer healthPoint;

  /** The ArrayList containing all DrawItems objects in game. */
  private List<DrawItem> drawItems = new ArrayList<>();

  /** The card list. */
  private List<MemoryCard> cards = new ArrayList<>();

  /** The image manager */
  private ImageManager imageManager;

  /** The Paint of the MemoryCardManager object */
  private Paint paint;

  /** The time bar of the game. */
  private TimeBarSlow timeBarSlow;

  /** Speed of the items in game */
  private Integer speed;

  /** the x position that the user clicked */
  private float posX;

  /** the y position that the user clicked */
  private float posY;

  /** the target card */
  private MemoryCard target;

  /**
   * The constructor for the magic card manager
   *
   * @param imageManager the image manager
   */
  public MemoryCardManager(ImageManager imageManager) {
    this.imageManager = imageManager;
    paint = new Paint();
    paint.setColor(Color.BLACK);
    paint.setTextSize(12 * this.imageManager.getUnit());
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
      healthPoint = 3;
      speed = 200;
      score = 0;
      endGame = false;
    } else {
      this.healthPoint = gameData.getHealthPoint();
      this.endGame = gameData.isEndGame();
      this.speed = gameData.getSpeed();
      this.score = gameData.getCurrScore();
    }
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
    canvas.drawText("Current Score: " + score.toString(), 0, 30 * imageManager.getUnit(), paint);
    canvas.drawText("HP: " + healthPoint.toString(), 0, 15 * imageManager.getUnit(), paint);
  }

  /** Update the status of game */
  public void update() {
    boolean allTurned = (drawItems.size() == 1);
    if (healthPoint == 0) {
      endGame();
      return;
    }
    if (drawItems.size() == 0) {
      initItems();
    } else if (target != null && !target.getIsTurned()) {
      target = null;
    }
    for (MemoryCard card : cards) {
      if (!card.getIsTurned()) {
        card.checkTouched(posX, posY);
      } else if (target == null) {
        target = card;
      } else if (target != card) {
        if (target.compareTo(card) != 0) {
          target.flipBack();
          card.flipBack();
        } else {
          drawItems.remove(target);
          drawItems.remove(card);
        }
      }
      card.update();
    }
    if (timeBarSlow.getTimeCount() == 0) {
      if (!allTurned) {
        decreaseHealthPoint();
      }
      timeBarSlow.resetTime();
    } else {
      timeBarSlow.update();
    }
    if (allTurned) {
      score += 100;
      checkScore();
      cards.clear();
      drawItems.clear();
    }
  }

  /** check scores */
  private void checkScore() {
    if (score.equals(speed)) {
      healthPoint += Math.max(1, (10 - speed / 100));
      speed += 100;
    }
  }

  private void decreaseHealthPoint() {
    healthPoint--;
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

  @Override
  public void checkMovement(float currX) {}

  @Override
  public void checkIsTouched(boolean isTouched) {}

  /** initialize the items */
  private void initItems() {
    HashMap<Integer, Integer> map = CardMap.getInstance().initMap();
    List<Integer> cardList = new ArrayList<>(map.keySet());
    Collections.shuffle(cardList);
    int x = -9 * imageManager.getUnit();
    int y = 18 * imageManager.getUnit();
    int size = 9 * imageManager.getUnit();
    for (Integer item : cardList) {
      if (x == 27 * imageManager.getUnit()) {
        x = 3 * imageManager.getUnit();
        y += 12 * imageManager.getUnit();
      } else {
        x += 12 * imageManager.getUnit();
      }
      addItem(x, y, size, map.get(item), new MemoryCard(imageManager));
    }
    timeBarSlow =
        new TimeBarSlow(
            x + 12 * imageManager.getUnit(), y + 12 * imageManager.getUnit(), imageManager);
    timeBarSlow.resetTime();
    drawItems.add(timeBarSlow);
    target = null;
  }

  /**
   * Add item in game
   *
   * @param x an int representing the x coordinates
   * @param y an int representing the y coordinates
   * @param number the number that the card have
   * @param size the size of each card
   * @param card the card class
   */
  private void addItem(int x, int y, int size, Integer number, MemoryCard card) {
    card.setLocation(x, y);
    card.setNumber(number);
    card.setSize(size);
    cards.add(card);
    drawItems.add(card);
  }

  /**
   * when the screen is touched
   *
   * @param x the x position
   * @param y the y position
   */
  public void checkTouched(float x, float y) {
    posX = x;
    posY = y;
  }
}
