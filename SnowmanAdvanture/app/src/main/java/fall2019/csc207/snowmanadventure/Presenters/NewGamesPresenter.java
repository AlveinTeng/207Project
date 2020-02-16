package fall2019.csc207.snowmanadventure.Presenters;

import android.content.Context;
import android.view.View;

import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.R;
import fall2019.csc207.snowmanadventure.Views.Game.GameMenu.NewGamesView;

/** This class describes the new games presenter */
public class NewGamesPresenter extends BasePresenter {

  private NewGamesView newGamesView;

  public NewGamesPresenter(NewGamesView newGamesView, Global global, Context context) {
    super(global, context);
    this.newGamesView = newGamesView;
    newGamesView.setWelcome(getGlobal().getCurrUser());
    initMusic();
    changeBackGround(true);
  }

  @Override
  public void onResume() {
    getMusicManager().playGameMusic(2, false, getGlobal().getWantThug());
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    newGamesView = null;
  }

  @Override
  public void onPause() {
    if (newGamesView != null) {
      getGlobal().saveAll(getContext());
      getMusicManager().pauseGameMusic(2, false, getGlobal().getWantThug());
    }
  }

  @Override
  public void onBackPressed() {
    newGamesView.updateGlobal(getGlobal());
    newGamesView.goToStart();
  }

  public void checkClick(View view) {
    super.checkClick();
    if (view.getId() == R.id.btn_game_one) {
      newGamesView.updateGlobal(getGlobal());
      newGamesView.goToGameOne();
    } else if (view.getId() == R.id.btn_game_two) {
      newGamesView.updateGlobal(getGlobal());
      newGamesView.goToGameTwo();
    } else if (view.getId() == R.id.btn_game_three) {
      newGamesView.updateGlobal(getGlobal());
      newGamesView.goToGameThree();
    } else if (view.getId() == R.id.btn_game_four) {
      newGamesView.updateGlobal(getGlobal());
      newGamesView.goToGameFour();
    } else if (view.getId() == R.id.btn_game_five) {
      newGamesView.updateGlobal(getGlobal());
      newGamesView.goToGameFive();
    } else if (view.getId() == R.id.ibtn_score_board) {
      newGamesView.updateGlobal(getGlobal());
      newGamesView.goToScoreBoardMenu();
    } else if (view.getId() == R.id.ibtn_rewards) {
      newGamesView.updateGlobal(getGlobal());
      newGamesView.goToRewards();
    } else if (view.getId() == R.id.ibtn_reset) {
      newGamesView.updateGlobal(getGlobal());
      changeBackGround(false);
    }
  }

  private void changeBackGround(boolean init) {
    if (!init) {
      getGlobal().changeChoice(!getGlobal().getChoice());
    }
    newGamesView.changeBackGround((getGlobal().getChoice()) ? R.drawable.night : R.drawable.sun);
  }

  public void checkDisplayReward() {
    if (getGlobal().getDisplay(true)) {
      newGamesView.showRewardDialog();
    }
  }
}
