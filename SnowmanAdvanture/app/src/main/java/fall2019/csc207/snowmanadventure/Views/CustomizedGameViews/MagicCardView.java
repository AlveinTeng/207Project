package fall2019.csc207.snowmanadventure.Views.CustomizedGameViews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import fall2019.csc207.snowmanadventure.DataBase.GameData;
import fall2019.csc207.snowmanadventure.Models.Factories.AbstractGameFactory;
import fall2019.csc207.snowmanadventure.Models.Factories.Factories;
import fall2019.csc207.snowmanadventure.Models.Factories.GameFactoryProducer;
import fall2019.csc207.snowmanadventure.Models.GameManagers.MagicCardManager;
import fall2019.csc207.snowmanadventure.Models.GameMenu.GameThread;
import fall2019.csc207.snowmanadventure.Models.GameMenu.Games;
import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManager;
import fall2019.csc207.snowmanadventure.Views.Game.GameView;

public class MagicCardView extends SurfaceView
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
  private int unit = Resources.getSystem().getDisplayMetrics().widthPixels / 108;

  /** the game four activity for this surface view */
  private GameView gameFourView;

  /** the image manager for game three, which contains all bitmaps for the game */
  private ImageManager imageManagerFour;

  /** the game manager for game three, which controls the behavior of each game item for the game */
  private MagicCardManager magicCardManager;

  /** whether the user touches the button hit */
  private boolean hit;

  /** whether the user touches the button stand; */
  private boolean stand;

  /** the choice for changing background */
  private boolean choice;

  /**
   * constructs game four view for this game
   *
   * @param context the context for the game four view
   */
  public MagicCardView(Context context) {
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
   * constructs game four view for this game
   *
   * @param context the context for the game four view
   * @param attrs the attribute set for this game four view
   */
  public MagicCardView(Context context, AttributeSet attrs) {
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
   * constructs game four view for this game
   *
   * @param context the context for the game four view
   * @param attrs the attribute set for this game four view
   * @param defStyleAttr the style attribute for game four view
   */
  public MagicCardView(Context context, AttributeSet attrs, int defStyleAttr) {
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
   * destroys the the surface for game four view
   *
   * @param surfaceHolder the surface holder containing the surface for game four
   */
  @Override
  public void surfaceDestroyed(SurfaceHolder surfaceHolder) {}

  /**
   * draw each bitmap needed for game four
   *
   * @param canvas the canvas where each bitmap and view is drawn on
   */
  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    if (canvas != null) {
      canvas.drawBitmap(imageManagerFour.getBackground(), 0, 0, null);
      magicCardManager.draw(canvas);
    }
  }

  /**
   * update each item in our current game four and save data to global each time update is called
   */
  @Override
  public void update() {
    saveData();
    if (magicCardManager.isEnd()) {
      gameFourView.goToGameOver();
      endThread();
    } else {
      if (hit) {
        magicCardManager.hitACard();
      } else if (stand) {
        magicCardManager.checkCards();
      }
      magicCardManager.update();
      hit = false;
      stand = false;
    }
  }

  /**
   * load all the game four data for game two stored previously in global
   *
   * @param gameData the game four data we need to load into our game
   */
  @Override
  public void loadData(GameData gameData) {
    if (magicCardManager == null) {
      AbstractGameFactory imageManagerFactory = GameFactoryProducer.getGameFactory(Factories.IMAGE);
      AbstractGameFactory gameManagerFactory = GameFactoryProducer.getGameFactory(Factories.GAME);
      imageManagerFour =
          imageManagerFactory.getImageManager(
              Games.GAME_FOUR, this, screenHeight, screenWidth, unit);
      imageManagerFour.changeBackground(choice);
      magicCardManager =
          (MagicCardManager) gameManagerFactory.getGameManager(Games.GAME_FOUR, imageManagerFour);
    }
    magicCardManager.renewData(gameData);
  }

  /** save the current game four data to global */
  @Override
  public void saveData() {
    gameFourView.autoSave(magicCardManager.getGameData());
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
   * @param gameFourView current game view
   */
  public void setGameFourView(GameView gameFourView) {
    this.gameFourView = gameFourView;
  }

  /** the user touches the button hit */
  public void hit() {
    hit = true;
  }

  /** the user touches the button stand; */
  public void stand() {
    stand = true;
  }
}
