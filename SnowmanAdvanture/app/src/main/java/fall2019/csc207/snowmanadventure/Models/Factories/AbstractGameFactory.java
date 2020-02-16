package fall2019.csc207.snowmanadventure.Models.Factories;

import android.view.SurfaceView;

import fall2019.csc207.snowmanadventure.Models.GameManagers.GameManager;
import fall2019.csc207.snowmanadventure.Models.GameMenu.Games;
import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManager;

/** This is a abstract factory class */
public abstract class AbstractGameFactory {

  public abstract ImageManager getImageManager(
      Games imageManager, SurfaceView customizedGameView, int height, int width, int unit);

  public abstract GameManager getGameManager(Games gameManager, ImageManager imageManager);
}
