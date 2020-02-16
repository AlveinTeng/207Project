package fall2019.csc207.snowmanadventure.Presenters;

import android.content.Context;

import fall2019.csc207.snowmanadventure.DataBase.GameData;
import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.DataBase.LocalGame;
import fall2019.csc207.snowmanadventure.Models.GameMenu.Games;
import fall2019.csc207.snowmanadventure.Views.CustomizedGameViews.MemoryCardView;
import fall2019.csc207.snowmanadventure.Views.Game.GameView;

/** This class describes the game five presenter */
public class GameFivePresenter extends BasePresenter {

  private GameView gameFiveView;

  private LocalGame localGame;

  private MemoryCardView memoryCardsView;

  public GameFivePresenter(GameView gameFiveView, Global global, Context context) {
    super(global, context);
    this.gameFiveView = gameFiveView;
    localGame = global.getLocal(global.getCurrUser());
    initMusic();
  }

  public void setGameView(MemoryCardView memoryCardView) {
    memoryCardView.setGameFiveView(gameFiveView);
    memoryCardView.setChangeableBackground(getGlobal().getChoice());
    this.memoryCardsView = memoryCardView;
  }

  public void saveGame(GameData gameData) {
    localGame.saveGame(Games.GAME_FIVE, gameData);
  }

  @Override
  public void onResume() {
    getMusicManager().playGameMusic(5, true, getGlobal().getWantThug());
    memoryCardsView.loadData(localGame.loadGame(Games.GAME_FIVE));
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    gameFiveView = null;
  }

  @Override
  public void onPause() {
    if (gameFiveView != null) {
      getMusicManager().pauseGameMusic(5, true, getGlobal().getWantThug());
      getGlobal().saveAll(getContext());
      memoryCardsView.endThread();
    }
  }

  @Override
  public void onBackPressed() {
    gameFiveView.updateGlobal(getGlobal());
    gameFiveView.goToNewGames();
  }
}
