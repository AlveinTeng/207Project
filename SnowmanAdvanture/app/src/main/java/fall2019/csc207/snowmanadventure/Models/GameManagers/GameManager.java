package fall2019.csc207.snowmanadventure.Models.GameManagers;

import android.graphics.Canvas;

import fall2019.csc207.snowmanadventure.DataBase.GameData;

/** This is an interface that describes the game manager */
public interface GameManager {

  GameData getGameData();

  void renewData(GameData gameData);

  void draw(Canvas canvas);

  void update();

  boolean isEnd();

  void checkMovement(float currX);

  void checkIsTouched(boolean isTouched);
}
