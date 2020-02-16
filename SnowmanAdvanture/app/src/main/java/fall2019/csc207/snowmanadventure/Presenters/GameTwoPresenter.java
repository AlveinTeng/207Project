package fall2019.csc207.snowmanadventure.Presenters;

import android.content.Context;

import fall2019.csc207.snowmanadventure.DataBase.GameData;
import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.DataBase.LocalGame;
import fall2019.csc207.snowmanadventure.Models.GameMenu.Games;
import fall2019.csc207.snowmanadventure.Views.CustomizedGameViews.FlyingSnowmanView;
import fall2019.csc207.snowmanadventure.Views.Game.GameView;

/** This class describes the game two presenter */
public class GameTwoPresenter extends BasePresenter {

  private GameView gameTwoView;

  private LocalGame localGame;

  private FlyingSnowmanView flyingSnowmanView;

  public GameTwoPresenter(GameView gameTwoView, Global global, Context context) {
    super(global, context);
    this.gameTwoView = gameTwoView;
    localGame = global.getLocal(global.getCurrUser());
    initMusic();
  }

  public void setGameView(FlyingSnowmanView flyingSnowmanView) {
    flyingSnowmanView.setGameTwoView(gameTwoView);
    flyingSnowmanView.setUseThug(getGlobal().getWantThug());
    flyingSnowmanView.setChangeableBackground(getGlobal().getChoice());
    this.flyingSnowmanView = flyingSnowmanView;
  }

  public void saveGame(GameData gameData) {
    localGame.saveGame(Games.GAME_TWO, gameData);
  }

  @Override
  public void onResume() {
    getMusicManager().playGameMusic(2, true, getGlobal().getWantThug());
    flyingSnowmanView.loadData(localGame.loadGame(Games.GAME_TWO));
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    gameTwoView = null;
  }

  @Override
  public void onPause() {
    if (gameTwoView != null) {
      getMusicManager().pauseGameMusic(2, true, getGlobal().getWantThug());
      getGlobal().saveAll(getContext());
      flyingSnowmanView.endThread();
    }
  }

  @Override
  public void onBackPressed() {
    gameTwoView.updateGlobal(getGlobal());
    gameTwoView.goToNewGames();
  }
}
