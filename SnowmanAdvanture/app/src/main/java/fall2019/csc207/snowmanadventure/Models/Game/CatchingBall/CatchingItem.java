package fall2019.csc207.snowmanadventure.Models.Game.CatchingBall;

import android.graphics.Canvas;
import android.graphics.Paint;

import fall2019.csc207.snowmanadventure.Models.Game.DrawItem;
import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManager;

public abstract class CatchingItem implements DrawItem {

  /** The x coordinates of the class object */
  private int x;

  /** The y coordinates of the class object */
  private int y;

  /** The width of the screen */
  private int width;

  /** The height of the screen */
  private int height;

  /** The speed of the object */
  private int speed;

  /** The boolean indicates whether the object exists or not */
  private boolean exist = true;

  /** The image manager for this game */
  private ImageManager imageManagerThree;

  /** The score of the player */
  private Integer score = 0;

  /** The radius of the image */
  private int radius;

  /** The paint of the color of the object */
  private Paint paint;

  /** The paint of the text of the object */
  private Paint paintText;

  /**
   * The constructor to initialize the class
   *
   * @param imageManagerThree the ImageManager of the game
   */
  CatchingItem(ImageManager imageManagerThree) {
    this.imageManagerThree = imageManagerThree;
    radius = 2 * imageManagerThree.getUnit();
    initImage();
  }

  /**
   * Set the location of the object
   *
   * @param x an int representing the x coordinates
   * @param y an int representing the y coordinates
   */
  public void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Set the upper bound of the objects' location
   *
   * @param width an int representing width of the screen
   * @param height an int representing the height of tbe screen
   */
  public void setBound(int width, int height) {
    this.width = width - 60;
    this.height = height;
  }

  /** Indicates whether the object exits @Return a boolean indicates whether the object exists */
  public boolean isExist() {
    return exist;
  }

  /** Set the object do not exists */
  public void die() {
    exist = false;
  }

  /** Update the location of the object */
  public void update() {
    y += speed;
    checkIsOutOfBound();
  }

  /**
   * Check if the location is out of bound if the location out of bound, set the object do not
   * exists
   */
  private void checkIsOutOfBound() {
    if (y >= height) {
      die();
    }
  }

  /**
   * Change the location either to the left or right based on the given boolean
   *
   * @param right the boolean indicates move to the right or not
   */
  void moveRight(boolean right) {
    x = (right) ? Math.min(width, x + speed) : Math.max(0, x + speed);
  }

  /**
   * Increase the speed based on the given offset
   *
   * @param offset an int to be used to calculate the speed change
   */
  void increaseSpeed(int offset) {
    speed = Math.max(Math.min(speed + offset, 5), -5);
  }

  /**
   * Getter of the speed
   *
   * @return an int representing current speed
   */
  public int getSpeed() {
    return speed;
  }

  /**
   * Set the current speed by the given speed
   *
   * @param speed the int to be set as speed
   */
  public void setSpeed(int speed) {
    this.speed = speed;
  }

  /**
   * Getter of the x coordinates
   *
   * @return an int representing the x coordinates
   */
  public int getX() {
    return x;
  }

  /**
   * Getter of the y coordinates
   *
   * @return an int representing the y coordinates
   */
  public int getY() {
    return y;
  }

  /**
   * Getter of the current score
   *
   * @return an Integer representing current score
   */
  public Integer getScore() {
    return score;
  }

  /**
   * Setter of the score
   *
   * @param score the Integer indicates the score
   */
  public void setScore(Integer score) {
    this.score = score;
  }

  /**
   * Getter of the radius
   *
   * @return a int representing current radius
   */
  int getRadius() {
    return radius;
  }

  /**
   * Setter of the radius
   *
   * @param radius the int to be set as radius
   */
  void setRadius(int radius) {
    this.radius = radius;
  }

  /**
   * Getter of the paint
   *
   * @return a Paint representing the paint
   */
  public Paint getPaint() {
    return paint;
  }

  /**
   * Setter of the paint
   *
   * @param paint a Paint representing the paint to be set
   */
  public void setPaint(Paint paint) {
    this.paint = paint;
  }

  /**
   * Getter of paintText
   *
   * @return a Paint representing the paintText
   */
  Paint getPaintText() {
    return paintText;
  }

  /**
   * Setter of the paintText
   *
   * @param paintText the Paint to be set as painText
   */
  void setPaintText(Paint paintText) {
    this.paintText = paintText;
  }

  /**
   * Getter of imageManagerThree
   *
   * @return an ImageManager representing ImageManagerThree
   */
  ImageManager getImageManagerThree() {
    return imageManagerThree;
  }

  /**
   * Draw the image of instance of CatchingItem class on the screen based on given canvas
   *
   * @param canvas the Canvas representing current canvas
   */
  @Override
  public void draw(Canvas canvas) {
    canvas.drawCircle(
        x * imageManagerThree.getUnit(), y * imageManagerThree.getUnit(), getRadius(), paint);
    canvas.drawText(
        score.toString(),
        (x - radius / 10) * imageManagerThree.getUnit(),
        (y + radius / 10) * imageManagerThree.getUnit(),
        paintText);
  }

  /** The abstract method to initiate the image */
  public abstract void initImage();
}
