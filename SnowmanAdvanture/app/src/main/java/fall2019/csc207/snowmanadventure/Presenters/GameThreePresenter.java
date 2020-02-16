package fall2019.csc207.snowmanadventure.Presenters;

import android.content.Context;

import fall2019.csc207.snowmanadventure.DataBase.GameData;
import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.DataBase.LocalGame;
import fall2019.csc207.snowmanadventure.Models.GameMenu.Games;
import fall2019.csc207.snowmanadventure.Views.CustomizedGameViews.CatchingBallView;
import fall2019.csc207.snowmanadventure.Views.Game.GameView;

/** This class describes the game three presenter */
public class GameThreePresenter extends BasePresenter {

  private GameView gameThreeView;

  private LocalGame localGame;

  private CatchingBallView catchingBallView;

  public GameThreePresenter(GameView gameThreeView, Global global, Context context) {
    super(global, context);
    this.gameThreeView = gameThreeView;
    localGame = global.getLocal(global.getCurrUser());
    initMusic();
  }

  public void setGameView(CatchingBallView catchingBallView) {
    catchingBallView.setGameThreeView(gameThreeView);
    catchingBallView.setUseThug(getGlobal().getWantThug());
    catchingBallView.setChangeableBackground(getGlobal().getChoice());
    this.catchingBallView = catchingBallView;
  }

  public void saveGame(GameData gameData) {
    localGame.saveGame(Games.GAME_THREE, gameData);
  }

  @Override
  public void onResume() {
    getMusicManager().playGameMusic(3, true, getGlobal().getWantThug());
    catchingBallView.loadData(localGame.loadGame(Games.GAME_THREE));
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    gameThreeView = null;
  }

  @Override
  public void onPause() {
    if (gameThreeView != null) {
      getMusicManager().pauseGameMusic(3, true, getGlobal().getWantThug());
      getGlobal().saveAll(getContext());
      catchingBallView.endThread();
    }
  }

  @Override
  public void onBackPressed() {
    gameThreeView.updateGlobal(getGlobal());
    gameThreeView.goToNewGames();
  }
}
