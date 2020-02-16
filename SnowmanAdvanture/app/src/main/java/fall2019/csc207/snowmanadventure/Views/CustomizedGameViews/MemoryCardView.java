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
import fall2019.csc207.snowmanadventure.Models.GameManagers.MemoryCardManager;
import fall2019.csc207.snowmanadventure.Models.GameMenu.GameThread;
import fall2019.csc207.snowmanadventure.Models.GameMenu.Games;
import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManager;
import fall2019.csc207.snowmanadventure.Views.Game.GameView;

public class MemoryCardView extends SurfaceView
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

  /** the game five activity for this surface view */
  private GameView gameFiveView;

  /** the image manager for game, which contains all bitmaps for the game */
  private ImageManager imageManagerFive;

  /** the game manager for game, which controls the behavior of each game item for the game */
  private MemoryCardManager memoryCardManager;

  /** the choice for changing background */
  private boolean choice;

  /**
   * constructs game view for this game
   *
   * @param context the context for the game view
   */
  public MemoryCardView(Context context) {
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
   * constructs game view for this game
   *
   * @param context the context for the game view
   * @param attrs the attribute set for this game view
   */
  public MemoryCardView(Context context, AttributeSet attrs) {
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
   * constructs game view for this game
   *
   * @param context the context for the game view
   * @param attrs the attribute set for this game view
   * @param defStyleAttr the style attribute for game view
   */
  public MemoryCardView(Context context, AttributeSet attrs, int defStyleAttr) {
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
   * change the game four surface
   *
   * @param surfaceHolder the holder for the current game surface
   * @param i the new view point for this game view
   * @param i1 the new screen height for this game view
   * @param i2 the new screen width for this game view
   */
  @Override
  public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {}

  /**
   * destroys the the surface for game view
   *
   * @param surfaceHolder the surface holder containing the surface for game
   */
  @Override
  public void surfaceDestroyed(SurfaceHolder surfaceHolder) {}

  /**
   * returns whether the screen is touched
   *
   * @param event the event physically happening on the surface of the screen
   * @return returns the boolean value representing if the screen is currently touched
   */
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    float x = 0;
    float y = 0;
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      x = event.getX();
      y = event.getY();
    }

    memoryCardManager.checkTouched(x / unit, y / unit);
    return true;
  }

  /**
   * draw each bitmap needed for game
   *
   * @param canvas the canvas where each bitmap and view is drawn on
   */
  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    if (canvas != null) {
      canvas.drawBitmap(imageManagerFive.getBackground(), 0, 0, null);
      memoryCardManager.draw(canvas);
    }
  }

  /**
   * update each item in our current game four and save data to global each time update is called
   */
  @Override
  public void update() {
    saveData();
    if (memoryCardManager.isEnd()) {
      gameFiveView.goToGameOver();
      endThread();
    } else {
      memoryCardManager.update();
    }
  }

  /**
   * load all the game data for game two stored previously in global
   *
   * @param gameData the game data we need to load into our game
   */
  @Override
  public void loadData(GameData gameData) {
    if (memoryCardManager == null) {
      AbstractGameFactory imageManagerFactory = GameFactoryProducer.getGameFactory(Factories.IMAGE);
      AbstractGameFactory gameManagerFactory = GameFactoryProducer.getGameFactory(Factories.GAME);
      imageManagerFive =
          imageManagerFactory.getImageManager(
              Games.GAME_FIVE, this, screenHeight, screenWidth, unit);
      imageManagerFive.changeBackground(choice);
      memoryCardManager =
          (MemoryCardManager) gameManagerFactory.getGameManager(Games.GAME_FIVE, imageManagerFive);
    }
    memoryCardManager.renewData(gameData);
  }

  /** save the current game data to global */
  @Override
  public void saveData() {
    gameFiveView.autoSave(memoryCardManager.getGameData());
  }

  /** ends the current game thread */
  @Override
  public void endThread() {
    thread.end();
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
   * Set the choice for using thug to the ImageManager
   *
   * @param useThug the choice for using thug
   */
  @Override
  public void setUseThug(boolean useThug) {}

  /**
   * Set the choice for changing the background to the ImageManager
   *
   * @param gameFiveView current game view
   */
  public void setGameFiveView(GameView gameFiveView) {
    this.gameFiveView = gameFiveView;
  }
}
