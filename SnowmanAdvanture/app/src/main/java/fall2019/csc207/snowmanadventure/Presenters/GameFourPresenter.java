package fall2019.csc207.snowmanadventure.Presenters;

import android.content.Context;
import android.view.View;

import fall2019.csc207.snowmanadventure.DataBase.GameData;
import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.DataBase.LocalGame;
import fall2019.csc207.snowmanadventure.Models.GameMenu.Games;
import fall2019.csc207.snowmanadventure.R;
import fall2019.csc207.snowmanadventure.Views.CustomizedGameViews.MagicCardView;
import fall2019.csc207.snowmanadventure.Views.Game.GameView;

/** This class describes the game four presenter */
public class GameFourPresenter extends BasePresenter {

  private GameView gameFourView;

  private LocalGame localGame;

  private MagicCardView magicCardView;

  public GameFourPresenter(GameView gameFourView, Global global, Context context) {
    super(global, context);
    this.gameFourView = gameFourView;
    localGame = global.getLocal(global.getCurrUser());
    initMusic();
  }

  public void setGameView(MagicCardView magicCardView) {
    magicCardView.setGameFourView(gameFourView);
    magicCardView.setChangeableBackground(getGlobal().getChoice());
    this.magicCardView = magicCardView;
  }

  public void saveGame(GameData gameData) {
    localGame.saveGame(Games.GAME_FOUR, gameData);
  }

  @Override
  public void onResume() {
    getMusicManager().playGameMusic(4, true, getGlobal().getWantThug());
    magicCardView.loadData(localGame.loadGame(Games.GAME_FOUR));
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    gameFourView = null;
  }

  @Override
  public void onPause() {
    if (gameFourView != null) {
      getMusicManager().pauseGameMusic(4, true, getGlobal().getWantThug());
      getGlobal().saveAll(getContext());
      magicCardView.endThread();
    }
  }

  @Override
  public void onBackPressed() {
    gameFourView.updateGlobal(getGlobal());
    gameFourView.goToNewGames();
  }

  public void checkClick(View view) {
    if (view.getId() == R.id.btn_hit) {
      magicCardView.hit();
    } else if (view.getId() == R.id.btn_stand) {
      magicCardView.stand();
    }
  }
}
