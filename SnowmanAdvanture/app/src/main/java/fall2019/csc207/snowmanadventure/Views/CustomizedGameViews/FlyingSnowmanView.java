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

public class FlyingSnowmanView extends SurfaceView
    implements SurfaceHolder.Callback, CustomizedGameView {

  /** all the context for game two view */
  private Context context;

  /** the current game thread */
  private GameThread thread;

  /** the integer value for the screen width of our virtual device Pixel 3 */
  private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

  /** the integer value for the screen height of our virtual device Pixel 3 */
  private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

  /** the unit for this surface view */
  private int unit = Resources.getSystem().getDisplayMetrics().widthPixels / 216;

  /** the image manager of game two where all bitmaps are stored */
  private ImageManager imageManagerTwo;

  /** the game manager of game two where all data is stored and managed */
  private GameManager flyingSnowmanManager;

  /** the activity that controls game two */
  private GameView gameTwoView;

  /** the boolean value indicting if the screen is currently touched */
  private boolean isTouched = false;

  /** the choice for changing background */
  private boolean choice;

  /** the choice for using thug */
  private boolean useThug;

  /**
   * constructs game two view for this game
   *
   * @param context the context for the game two view
   */
  public FlyingSnowmanView(Context context) {
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
   * constructs game two view for this game
   *
   * @param context the context for the game two view
   * @param attrs the attribute set for this game two view
   */
  public FlyingSnowmanView(Context context, AttributeSet attrs) {
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
   * constructs game two view for this game
   *
   * @param context the context for the game two view
   * @param attrs the attribute set for this game two view
   * @param defStyleAttr the style attribute for game two view
   */
  public FlyingSnowmanView(Context context, AttributeSet attrs, int defStyleAttr) {
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
   * creates the surface and starts the game thread
   *
   * @param surfaceHolder the holder for the current game surface
   */
  @Override
  public void surfaceCreated(SurfaceHolder surfaceHolder) {
    thread = new GameThread(this);
    thread.start();
  }

  /**
   * change the game two surface
   *
   * @param surfaceHolder the holder for the current game surface
   * @param i the new view point for this game view
   * @param i1 the new screen height for this game view
   * @param i2 the new screen width for this game view
   */
  @Override
  public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {}

  /**
   * destroys the the surface for game two view
   *
   * @param surfaceHolder the surface holder containing the surface for game two
   */
  @Override
  public void surfaceDestroyed(SurfaceHolder surfaceHolder) {}

  /**
   * draw each bitmap needed for game two
   *
   * @param canvas the canvas where each bitmap and view is drawn on
   */
  @Override
  public void draw(Canvas canvas) {
    if (canvas != null) {
      super.draw(canvas);

      canvas.drawBitmap(imageManagerTwo.getBackground(), 0, 0, null);
      flyingSnowmanManager.draw(canvas);
    }
  }

  /**
   * returns whether the screen is touched
   *
   * @param event the event physically happening on the surface of the screen
   * @return returns the boolean value representing if the screen is currently touched
   */
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      isTouched = true;
    }
    return true;
  }

  /** update each item in our current game two and save data to global each time update is called */
  @Override
  public void update() {
    saveData();
    if (flyingSnowmanManager.isEnd()) {
      gameTwoView.goToGameOver();
      thread.end();
    } else {
      flyingSnowmanManager.checkIsTouched(isTouched);
      flyingSnowmanManager.update();
      isTouched = false;
    }
  }

  /** ends the current game thread */
  public void endThread() {
    thread.end();
  }

  /**
   * load all the game two data for game two stored previously in global
   *
   * @param gameData the game two data we need to load into our game
   */
  @Override
  public void loadData(GameData gameData) {
    if (flyingSnowmanManager == null) {
      AbstractGameFactory imageManagerFactory = GameFactoryProducer.getGameFactory(Factories.IMAGE);
      AbstractGameFactory gameManagerFactory = GameFactoryProducer.getGameFactory(Factories.GAME);
      imageManagerTwo =
          imageManagerFactory.getImageManager(
              Games.GAME_TWO, this, screenHeight, screenWidth, unit);
      imageManagerTwo.changeBackground(choice);
      imageManagerTwo.changeSnowman(useThug);
      flyingSnowmanManager = gameManagerFactory.getGameManager(Games.GAME_TWO, imageManagerTwo);
    }
    flyingSnowmanManager.renewData(gameData);
  }

  /** save the current game two data to global */
  @Override
  public void saveData() {
    gameTwoView.autoSave(flyingSnowmanManager.getGameData());
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
   * @param gameTwoView current game view
   */
  public void setGameTwoView(GameView gameTwoView) {
    this.gameTwoView = gameTwoView;
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
