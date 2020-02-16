package fall2019.csc207.snowmanadventure.Models.GameManagers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

import fall2019.csc207.snowmanadventure.DataBase.GameData;
import fall2019.csc207.snowmanadventure.Models.Game.MagicCard.MagicCard;
import fall2019.csc207.snowmanadventure.Models.GameMenu.GameBuilder;
import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManager;

public class MagicCardManager implements GameManager {

  /** A list of magic cards */
  private List<MagicCard> magicCards = new ArrayList<>();

  /** The index of the cards */
  private int index = 0;

  /** The offset of the goal */
  private Integer offset = 5;

  /** The image manager */
  private ImageManager imageManager;

  /** The paint */
  private Paint paint;

  /** The boolean indicate whether the game end or not. */
  private boolean endGame;

  /** The current score. */
  private Integer currScore = 0;

  /** The current score. */
  private Integer goal;

  /** The score. */
  private Integer score;

  /** The health point of the player */
  private Integer healthPoint;

  /**
   * The constructor for the magic card manager
   *
   * @param imageManager the image manager
   */
  public MagicCardManager(ImageManager imageManager) {
    this.imageManager = imageManager;
    paint = new Paint();
    paint.setColor(Color.RED);
    paint.setTextSize(6 * this.imageManager.getUnit());
  }

  /** @return GameData which need to be stored */
  @Override
  public GameData getGameData() {
    return new GameBuilder(healthPoint, offset, score, endGame).setGoal(goal).build();
  }

  /**
   * Renew the data
   *
   * @param gameData a GameData used to renew
   */
  @Override
  public void renewData(GameData gameData) {
    if (gameData == null || gameData.isEndGame()) {
      healthPoint = 10;
      offset = 5;
      score = 0;
      endGame = false;
      goal = 80;
    } else {
      healthPoint = gameData.getHealthPoint();
      offset = gameData.getSpeed();
      score = gameData.getCurrScore();
      endGame = gameData.isEndGame();
      goal = gameData.getGoal();
    }
  }

  /**
   * Draw all the items in game
   *
   * @param canvas the canvas
   */
  @Override
  public void draw(Canvas canvas) {
    for (MagicCard item : magicCards) {
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
        "HP: " + healthPoint.toString(),
        10 * imageManager.getUnit(),
        5 * imageManager.getUnit(),
        paint);
    canvas.drawText(
        "Score: " + score.toString(),
        40 * imageManager.getUnit(),
        5 * imageManager.getUnit(),
        paint);
    canvas.drawText(
        String.format("Goal: %s ~ %s", (goal - offset), (goal + offset)),
        10 * imageManager.getUnit(),
        20 * imageManager.getUnit(),
        paint);
    canvas.drawText(
        "Current Score: " + currScore.toString(),
        2 * imageManager.getUnit(),
        150 * imageManager.getUnit(),
        paint);
  }

  /** Update the status of game */
  @Override
  public void update() {
    if (healthPoint == 0) {
      endGame();
      return;
    }
    if (magicCards.size() == 0) {
      initItems();
    }
    for (MagicCard magicCard : magicCards) {
      magicCard.update();
    }
    checkExplode();
  }

  /** check if the score is over the goal */
  private void checkExplode() {
    if (score > goal + offset) {
      healthPoint = Math.max(0, healthPoint - 4);
      score -= 50;
      clearCards();
    }
  }

  /** init items in FlyingSnowMan */
  private void initItems() {
    addItem(8, 45, new MagicCard(imageManager));
    addItem(41, 45, new MagicCard(imageManager));
    addItem(74, 45, new MagicCard(imageManager));
    addItem(24, 90, new MagicCard(imageManager));
    addItem(57, 90, new MagicCard(imageManager));
  }

  /**
   * Add item in game
   *
   * @param x an int representing the x coordinates
   * @param y an int representing the y coordinates
   * @param item a CatchingItem to be added
   */
  public void addItem(int x, int y, MagicCard item) {
    item.setLocation(x, y);
    magicCards.add(item);
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

  /** Hit one card */
  public void hitACard() {
    magicCards.get(index).create();
    currScore += magicCards.get(index).getNumber();
    index = Math.min(4, index + 1);
    if (!checkScore(false)) clearCards();
  }

  /** check scores */
  public void checkCards() {
    score += currScore;
    if (checkScore(true)) {
      healthPoint += 11;
      goal += 80;
      changeDifficulty();
    }
    clearCards();
  }

  /** change difficulty to the game */
  private void changeDifficulty() {
    if (score > 500) {
      offset = 1;
    } else if (score > 350) {
      offset = 2;
    } else if (score > 250) {
      offset = 3;
    } else if (score > 150) {
      offset = 4;
    }
  }

  /** clear all the card */
  private void clearCards() {
    healthPoint = Math.max(0, healthPoint - 1);
    currScore = 0;
    index = 0;
    for (MagicCard magicCard : magicCards) {
      magicCard.die();
    }
  }

  /**
   * check cards
   *
   * @param stand the stand is clicked
   * @return the if the score is in the goal if stand is clicked, otherwise return if the current
   *     score is less than or equal to 26
   */
  private boolean checkScore(boolean stand) {
    if (stand) {
      return (score >= goal - offset && score <= goal + offset);
    } else {
      return (currScore <= 26);
    }
  }
}
