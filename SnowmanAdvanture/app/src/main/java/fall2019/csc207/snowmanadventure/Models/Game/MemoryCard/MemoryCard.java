package fall2019.csc207.snowmanadventure.Models.Game.MemoryCard;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import fall2019.csc207.snowmanadventure.Models.Game.DrawItem;
import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManager;

/** This class describes the MemoryCard */
public class MemoryCard implements DrawItem, Comparable<MemoryCard> {

  /** the number of the card */
  private Integer number;

  /** the bitmap of the card */
  private Bitmap bitmap;

  /** whether the card is turned up */
  private boolean turn;

  /** The image manager */
  private ImageManager imageManagerFive;

  /** The x position */
  private int x;

  /** The y position */
  private int y;

  /** The size of the card */
  private int size;

  /** The time delay for showing the card */
  private int delay = 0;

  public MemoryCard(ImageManager imageManagerFive) {
    this.imageManagerFive = imageManagerFive;
    initBitmap();
  }

  private void initBitmap() {
    this.bitmap = imageManagerFive.getCards()[0];
  }

  public void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void setSize(int size) {
    this.size = size;
  }

  /** Turn over the card */
  private void turnOver() {
    if (!turn) {
      turn = true;
    }
  }

  /** update the bitmap of the card */
  public void update() {
    bitmap = (turn) ? imageManagerFive.getCards()[number] : imageManagerFive.getCards()[0];
  }

  /**
   * check if the user touches the card
   *
   * @param x the x position
   * @param y the y position
   */
  public void checkTouched(float x, float y) {
    if ((this.x <= x) && (x <= this.x + size) && (this.y <= y) && (y <= this.y + size)) {
      turnOver();
    }
  }

  public boolean getIsTurned() {
    return turn;
  }

  private Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  /** flip back the card */
  public void flipBack() {
    if (delay == 40) {
      turn = false;
      delay = 0;
    } else {
      delay += 1;
    }
  }

  @Override
  public void draw(Canvas canvas) {
    drawImage(canvas, bitmap, x, y);
  }

  private void drawImage(Canvas canvas, Bitmap bitmap, int x, int y) {
    canvas.drawBitmap(bitmap, x * imageManagerFive.getUnit(), y * imageManagerFive.getUnit(), null);
  }

  @Override
  public int compareTo(MemoryCard other) {
    return number.compareTo(other.getNumber());
  }
}
