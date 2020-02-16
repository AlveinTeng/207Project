package fall2019.csc207.snowmanadventure.Views.CustomizedGameViews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import fall2019.csc207.snowmanadventure.DataBase.GameData;
import fall2019.csc207.snowmanadventure.Models.Factories.AbstractGameFactory;
import fall2019.csc207.snowmanadventure.Models.Factories.Factories;
import fall2019.csc207.snowmanadventure.Models.Factories.GameFactoryProducer;
import fall2019.csc207.snowmanadventure.Models.GameManagers.GameManager;
import fall2019.csc207.snowmanadventure.Models.GameMenu.GameThread;
import fall2019.csc207.snowmanadventure.Models.GameMenu.Games;
import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManager;
import fall2019.csc207.snowmanadventure.Views.Game.GameView;

public class SkyLadderView extends SurfaceView
    implements SurfaceHolder.Callback, CustomizedGameView {
  /** game thread for game one */
  private GameThread thread;

  /** the game one context */
  private Context context;

  /** the screen width for the current device Pixel 3 */
  private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

  /** the screen height for the current device Pixel 3 */
  private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

  /** the unit for this surface view */
  private int unit = Resources.getSystem().getDisplayMetrics().widthPixels / 108;

  /** the game manager for game one */
  private GameManager skyLadderManager;

  /** the image manager containing all bitmaps for game one */
  private ImageManager imageManagerOne;

  /** the game one activity */
  private GameView gameOneView;

  /** the boolean value representing if the screen is touched */
  private boolean isTouched = false;

  /** the choice for changing background */
  private boolean choice;

  /** the choice for using thug */
  private boolean useThug;

  /**
   * constructs a new game one view
   *
   * @param context the game one context for the view
   */
  public SkyLadderView(Context context) {
    super(context);
    try {

      getHolder().addCallback(this);
      this.context = context;
      setFocusable(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * constructs a new game one view
   *
   * @param context the game one context for the view
   * @param attrs the game one attributes for the view
   */
  public SkyLadderView(Context context, AttributeSet attrs) {
    super(context, attrs);
    try {

      getHolder().addCallback(this);
      this.context = context;
      setFocusable(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * constructs a new game one view
   *
   * @param context the game one context for the view
   * @param attrs the game one attributes for the view
   * @param defStyleAttr the game one style attributes for the view
   */
  public SkyLadderView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    try {

      getHolder().addCallback(this);
      this.context = context;
      setFocusable(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * creates a new surface
   *
   * @param surfaceHolder the surface holder containing the surface
   */
  @Override
  public void surfaceCreated(SurfaceHolder surfaceHolder) {
    thread = new GameThread(this);
    thread.start();
  }

  /**
   * change the surface
   *
   * @param surfaceHolder the surface holder containing the surface
   * @param i the new x value for game one view
   * @param i1 the new y value for game one view
   * @param i2 the new focus value for game one view
   */
  @Override
  public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {}

  /**
   * destroys the surface in surface hold
   *
   * @param surfaceHolder the surface holder for the surface
   */
  @Override
  public void surfaceDestroyed(SurfaceHolder surfaceHolder) {}

  /**
   * draw bitmaps on the canvas
   *
   * @param canvas the canvas where bitmaps are drawn
   */
  @Override
  public void draw(Canvas canvas) {

    super.draw(canvas);
    if (canvas != null) {

      canvas.drawBitmap(imageManagerOne.getBackground(), 0, 0, null);
      skyLadderManager.draw(canvas);
    }
  }

  /**
   * checks if the screen is touched
   *
   * @param event the event happening on the screen
   * @return returns if the screen is touched
   */
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    int action = event.getAction();
    isTouched = (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE);
    return true;
  }

  /** updates and saves all data in game one activity */
  @Override
  public void update() {
    saveData();
    if (skyLadderManager.isEnd()) {
      gameOneView.goToGameOver();
      thread.end();
    } else {
      skyLadderManager.checkIsTouched(isTouched);
      skyLadderManager.update();
    }
  }

  /** end the current game thread */
  @Override
  public void endThread() {
    thread.end();
  }

  /**
   * load the data into game view
   *
   * @param gameData the game data we have for game one in this user
   */
  @Override
  public void loadData(GameData gameData) {
    if (skyLadderManager == null) {
      AbstractGameFactory imageManagerFactory = GameFactoryProducer.getGameFactory(Factories.IMAGE);
      AbstractGameFactory gameManagerFactory = GameFactoryProducer.getGameFactory(Factories.GAME);
      imageManagerOne =
          imageManagerFactory.getImageManager(
              Games.GAME_ONE, this, screenHeight, screenWidth, unit);
      imageManagerOne.changeBackground(choice);
      imageManagerOne.changeSnowman(useThug);
      skyLadderManager = gameManagerFactory.getGameManager(Games.GAME_ONE, imageManagerOne);
    }
    skyLadderManager.renewData(gameData);
  }

  /** save all current game data to global */
  @Override
  public void saveData() {
    gameOneView.autoSave(skyLadderManager.getGameData());
  }

  /**
   * change the background
   *
   * @param choice if the user changes the background
   */
  @Override
  public void setChangeableBackground(boolean choice) {
    this.choice = choice;
  }

  /**
   * Set the choice for changing the background to the ImageManager
   *
   * @param gameOneView current game view
   */
  public void setGameOneView(GameView gameOneView) {
    this.gameOneView = gameOneView;
  }

  /**
   * Set the choice for using thug to the ImageManager
   *
   * @param useThug the choice for using thug
   */
  @Override
  public void setUseThug(boolean useThug) {
    this.useThug = useThug;
  }
}
