package fall2019.csc207.snowmanadventure.Models.Game.MagicCard;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import fall2019.csc207.snowmanadventure.Models.Game.DrawItem;
import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManager;

/** This class describes the MagicCard */
public class MagicCard implements DrawItem {
  private int number;

  /** X coordinate of MagicCard */
  private int x;

  /** Y coordinate of MagicCard */
  private int y;

  /** Boolean indicating whether a MagicCard is exist */
  private boolean exist = false;

  /** Image for game four */
  private ImageManager imageManagerFour;

  private Bitmap bitmap;

  public MagicCard(ImageManager imageManagerFour) {
    this.imageManagerFour = imageManagerFour;
    initBitmap();
  }

  private void initBitmap() {
    bitmap = imageManagerFour.getCards()[0];
  }

  public void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void update() {
    changeBitmap(exist);
  }

  private void changeBitmap(boolean exist) {
    if (exist) {
      bitmap = imageManagerFour.getCards()[number - 1];
    } else {
      bitmap = imageManagerFour.getCards()[0];
    }
  }

  @Override
  public void draw(Canvas canvas) {
    drawImage(canvas, bitmap, x, y);
  }

  private void drawImage(Canvas canvas, Bitmap bitmap, int x, int y) {
    canvas.drawBitmap(bitmap, x * imageManagerFour.getUnit(), y * imageManagerFour.getUnit(), null);
  }

  public int getNumber() {
    return number - 1;
  }

  public void create() {
    exist = true;
    number = (int) (Math.random() * (14 - 2)) + 2;
  }

  public void die() {
    exist = false;
  }
}
