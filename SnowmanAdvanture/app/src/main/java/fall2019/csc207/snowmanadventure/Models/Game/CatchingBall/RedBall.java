package fall2019.csc207.snowmanadventure.Models.Game.CatchingBall;

import android.graphics.Color;
import android.graphics.Paint;

import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManager;

public class RedBall extends CatchingItem {

  /**
   * The constructor to initialize the class
   *
   * @param imageManagerThree the ImageManager of the game
   */
  public RedBall(ImageManager imageManagerThree) {
    super(imageManagerThree);
  }

  /** Initialize the image of RedBall set the color radius and score RedBall object */
  @Override
  public void initImage() {
    setPaint(new Paint());
    getPaint().setColor(Color.RED);
    getPaint().setAntiAlias(false);
    setPaintText(new Paint());
    getPaintText().setColor(Color.BLACK);
    int randomInt = (int) (Math.random() * 5);
    setScore(-randomInt);
    setRadius(randomInt + 6 * getImageManagerThree().getUnit());
    getPaintText().setTextSize(getRadius() + 2 * getImageManagerThree().getUnit());
  }
}
