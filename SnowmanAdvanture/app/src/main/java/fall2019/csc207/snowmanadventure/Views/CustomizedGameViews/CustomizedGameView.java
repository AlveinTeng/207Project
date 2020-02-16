package fall2019.csc207.snowmanadventure.Views.CustomizedGameViews;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import fall2019.csc207.snowmanadventure.DataBase.GameData;

/** This Interface is used whenever you need to customize a surface view for a game. */
public interface CustomizedGameView {

  /** Access to the underlying surface */
  SurfaceHolder getHolder();

  /** Update the action in the surface */
  void update();

  /** Use canvas to draw the image */
  void draw(Canvas canvas);

  /** Load the game data from the database */
  void loadData(GameData gameData);

  /** Save data to the data base */
  void saveData();

  /** End current thread */
  void endThread();

  /** Set the choice for changing the background to the ImageManager */
  void setChangeableBackground(boolean choice);

  /** Set the choice for using thug to the ImageManager */
  void setUseThug(boolean useThug);
}
