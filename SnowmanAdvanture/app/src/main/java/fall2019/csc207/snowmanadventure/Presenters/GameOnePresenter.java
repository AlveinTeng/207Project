package fall2019.csc207.snowmanadventure.Presenters;

import android.content.Context;

import fall2019.csc207.snowmanadventure.DataBase.GameData;
import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.DataBase.LocalGame;
import fall2019.csc207.snowmanadventure.Models.GameMenu.Games;
import fall2019.csc207.snowmanadventure.Views.CustomizedGameViews.CustomizedGameView;
import fall2019.csc207.snowmanadventure.Views.CustomizedGameViews.SkyLadderView;
import fall2019.csc207.snowmanadventure.Views.Game.GameView;

/** This class describes the game one presenter */
public class GameOnePresenter extends BasePresenter {

  private GameView gameOneView;

  private LocalGame localGame;

  private CustomizedGameView skyLadderView;

  public GameOnePresenter(GameView gameOneView, Global global, Context context) {
    super(global, context);
    this.gameOneView = gameOneView;
    localGame = global.getLocal(global.getCurrUser());
    initMusic();
  }

  public void setGameView(SkyLadderView skyLadderView) {
    skyLadderView.setGameOneView(gameOneView);
    skyLadderView.setUseThug(getGlobal().getWantThug());
    skyLadderView.setChangeableBackground(getGlobal().getChoice());
    this.skyLadderView = skyLadderView;
  }

  public void saveGame(GameData gameData) {
    localGame.saveGame(Games.GAME_ONE, gameData);
  }

  @Override
  public void onResume() {
    getMusicManager().playGameMusic(1, true, getGlobal().getWantThug());
    skyLadderView.loadData(localGame.loadGame(Games.GAME_ONE));
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    gameOneView = null;
  }

  @Override
  public void onPause() {
    if (gameOneView != null) {
      getMusicManager().pauseGameMusic(1, true, getGlobal().getWantThug());
      getGlobal().saveAll(getContext());
      skyLadderView.endThread();
    }
  }

  @Override
  public void onBackPressed() {
    gameOneView.updateGlobal(getGlobal());
    gameOneView.goToNewGames();
  }
}
