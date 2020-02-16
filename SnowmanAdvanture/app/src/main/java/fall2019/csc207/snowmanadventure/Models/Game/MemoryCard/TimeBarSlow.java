package fall2019.csc207.snowmanadventure.Models.Game.MemoryCard;

import fall2019.csc207.snowmanadventure.Models.Game.CatchingBall.TimeBar;
import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManager;

public class TimeBarSlow extends TimeBar {

  private int interval = 0;

  private int difficulty = 0;

  /**
   * The constructor to initialize the class object set the coordinates, appearance and the time
   * left.
   *
   * @param x the int representing the x coordinates
   * @param y the int representing the y coordinates
   */
  public TimeBarSlow(int x, int y, ImageManager imageManager) {
    super(x, y, imageManager);
  }

  @Override
  public void resetTime() {
    if (difficulty > 5) {
      setTimeCount(50);
    } else if (difficulty > 3) {
      setTimeCount(150);
    } else if (difficulty > 1) {
      setTimeCount(250);
    } else {
      setTimeCount(300);
    }
    difficulty += 1;
  }

  @Override
  public void update() {
    if (interval == 1) {
      super.update();
      interval = 0;
    } else {
      interval++;
    }
  }
}
