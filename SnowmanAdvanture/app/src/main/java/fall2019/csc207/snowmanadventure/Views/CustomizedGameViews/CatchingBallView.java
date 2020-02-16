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
import fall2019.csc207.snowmanadventure.Models.GameManagers.CatchingBallManager;
import fall2019.csc207.snowmanadventure.Models.GameMenu.GameThread;
import fall2019.csc207.snowmanadventure.Models.GameMenu.Games;
import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManager;
import fall2019.csc207.snowmanadventure.Views.Game.GameView;

public class CatchingBallView extends SurfaceView
    implements SurfaceHolder.Callback, CustomizedGameView {

  /** the context for this surface view */
  private Context context;

  /** the main game thread for this surface view */
  private GameThread thread;

  /** the screen width for this surface view */
  private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

  /** the screen height for this surface view */
  private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

  /** the unit for this surface view */
  private int unit = Resources.getSystem().getDisplayMetrics().widthPixels / 216;

  /** the game three activity for this surface view */
  private GameView gameThreeView;

  /** the image manager for game three, which contains all bitmaps for the game */
  private ImageManager imageManagerThree;

  /** the game manager for game three, which controls the behavior of each game item for the game */
  private CatchingBallManager catchingBallManager;

  /** the current x value for the game three view */
  private float currX = 0;

  /** the choice for changing background */
  private boolean choice;

  /** the choice for using thug */
  private boolean useThug;

  /**
   * constructs a new game three view
   *
   * @param context the context for the game three view
   */
  public CatchingBallView(Context context) {
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
   * constructs a new game three view
   *
   * @param context the context for the game three view
   * @param attrs the attributes for this game three view
   */
  public CatchingBallView(Context context, AttributeSet attrs) {
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
   * constructs a new game three view
   *
   * @param context the context for the game three view
   * @param attrs the attributes for this game three view
   * @param defStyleAttr the style attributes for this game three view
   */
  public CatchingBallView(Context context, AttributeSet attrs, int defStyleAttr) {
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
   * create the game three surface
   *
   * @param surfaceHolder the holder for the game three surface
   */
  @Override
  public void surfaceCreated(SurfaceHolder surfaceHolder) {
    thread = new GameThread(this);
    thread.start();
  }

  /**
   * change the current surface
   *
   * @param surfaceHolder the holder for the game three surface
   * @param i the new x value
   * @param i1 the new y value
   * @param i2 the new height
   */
  @Override
  public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {}

  /**
   * destroy the surface in the surface holder
   *
   * @param surfaceHolder the holder for the game three surface
   */
  @Override
  public void surfaceDestroyed(SurfaceHolder surfaceHolder) {}

  /**
   * draw the game three view on canvas
   *
   * @param canvas the convas where our game three view is drawn on
   */
  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    if (canvas != null) {
      canvas.drawBitmap(imageManagerThree.getBackground(), 0, 0, null);
      catchingBallManager.draw(canvas);
    }
  }

  /**
   * checks if the screen is touched
   *
   * @param event the event physically happening on the screen
   * @return returns the boolean value representing if the screen is touched
   */
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    int action = event.getAction();
    if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
      currX = event.getX();
    } else {
      currX = 0;
    }
    return true;
  }

  /** update the game three view and each item in game manager */
  @Override
  public void update() {
    saveData();
    if (catchingBallManager.isEnd()) {
      gameThreeView.goToGameOver();
      endThread();
    } else {
      catchingBallManager.checkMovement(currX);
      catchingBallManager.update();
    }
  }

  /** ends this current game thread */
  @Override
  public void endThread() {
    thread.end();
  }

  /**
   * load previous game data from global
   *
   * @param gameData the previous game data stored in global
   */
  @Override
  public void loadData(GameData gameData) {
    if (catchingBallManager == null) {
      AbstractGameFactory imageManagerFactory = GameFactoryProducer.getGameFactory(Factories.IMAGE);
      AbstractGameFactory gameManagerFactory = GameFactoryProducer.getGameFactory(Factories.GAME);
      imageManagerThree =
          imageManagerFactory.getImageManager(
              Games.GAME_THREE, this, screenHeight, screenWidth, unit);
      imageManagerThree.changeBackground(choice);
      imageManagerThree.changeSnowman(useThug);
      catchingBallManager =
          (CatchingBallManager)
              gameManagerFactory.getGameManager(Games.GAME_THREE, imageManagerThree);
    }
    catchingBallManager.renewData(gameData);
  }

  /** save all game three data for the current user into global */
  @Override
  public void saveData() {
    gameThreeView.autoSave(catchingBallManager.getGameData());
  }

  @Override
  public void setChangeableBackground(boolean choice) {
    this.choice = choice;
  }

  public void setGameThreeView(GameView gameThreeView) {
    this.gameThreeView = gameThreeView;
  }

  @Override
  public void setUseThug(boolean useThug) {
    this.useThug = useThug;
  }
}
