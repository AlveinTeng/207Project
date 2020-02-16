package fall2019.csc207.snowmanadventure.Presenters;

import android.content.Context;
import android.view.View;

import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.Models.GameMenu.Games;
import fall2019.csc207.snowmanadventure.R;
import fall2019.csc207.snowmanadventure.Views.Game.GameOver.GameOverView;

/** This class describes the game over menu presenter */
public class GameOverPresenter extends BasePresenter {

  private Games game;
  private GameOverView gameOverView;

  public GameOverPresenter(GameOverView gameOverView, Global global, Context context) {
    super(global, context);
    this.gameOverView = gameOverView;
  }

  @Override
  public void onResume() {}

  @Override
  public void onDestroy() {
    gameOverView = null;
  }

  @Override
  public void onPause() {
    if (gameOverView != null) {
      getGlobal().saveAll(getContext());
    }
  }

  @Override
  public void onBackPressed() {
    gameOverView.updateGlobal(getGlobal());
    gameOverView.goToNewGames();
  }

  public void checkClick(View view) {
    if (view.getId() == R.id.btn_yes) {
      goBackToGame();
    } else if (view.getId() == R.id.btn_no) {
      gameOverView.goToNewGames();
    }
  }

  public void goBackToGame() {
    switch (game) {
      case GAME_ONE:
        gameOverView.updateGlobal(getGlobal());
        gameOverView.goToGameOne();
        break;
      case GAME_TWO:
        gameOverView.updateGlobal(getGlobal());
        gameOverView.goToGameTwo();
        break;
      case GAME_THREE:
        gameOverView.updateGlobal(getGlobal());
        gameOverView.goToGameThree();
        break;
      case GAME_FOUR:
        gameOverView.updateGlobal(getGlobal());
        gameOverView.goToGameFour();
        break;
      case GAME_FIVE:
        gameOverView.updateGlobal(getGlobal());
        gameOverView.goToGameFive();
        break;
    }
  }

  public void setGame(Games game) {
    this.game = game;
  }

  public void setBackground() {
    gameOverView.setBackground(
        (getGlobal().getChoice()) ? R.drawable.backgroundnight : R.drawable.backgroundsun);
  }

  public void setSnowman() {
    gameOverView.setSnowman(
        (getGlobal().getWantThug()) ? R.drawable.meltedsnowthug : R.drawable.meltedsnowman);
  }
}
