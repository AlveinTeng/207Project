package fall2019.csc207.snowmanadventure.Presenters;

import android.content.Context;
import android.view.View;

import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.DataBase.LocalGame;
import fall2019.csc207.snowmanadventure.R;
import fall2019.csc207.snowmanadventure.Views.ScoreBoard.ScoreBoardMenuView;

/** This class describes the score board menu presenter */
public class ScoreBoardMenuPresenter extends BasePresenter {

  private ScoreBoardMenuView scoreBoardMenuView;

  private LocalGame localGame;

  public ScoreBoardMenuPresenter(
      ScoreBoardMenuView scoreBoardMenuView, Global global, Context context) {
    super(global, context);
    this.scoreBoardMenuView = scoreBoardMenuView;
    localGame = getGlobal().getGames().get(getGlobal().getCurrUser());
    assert localGame != null;
    this.scoreBoardMenuView.setNameSwitch(localGame.isSaveName());
    this.scoreBoardMenuView.setScoreSwitch(localGame.isSaveScore());
  }

  @Override
  public void onResume() {}

  @Override
  public void onDestroy() {
    scoreBoardMenuView = null;
  }

  @Override
  public void onPause() {
    if (scoreBoardMenuView != null) {
      getGlobal().saveAll(getContext());
    }
  }

  @Override
  public void onBackPressed() {
    scoreBoardMenuView.updateGlobal(getGlobal());
    scoreBoardMenuView.goToNewGames();
  }

  public void checkClick(View view) {
    if (view.getId() == R.id.game_one_btn) {
      scoreBoardMenuView.updateGlobal(getGlobal());
      scoreBoardMenuView.goToGameOne();
    } else if (view.getId() == R.id.game_two_btn) {
      scoreBoardMenuView.updateGlobal(getGlobal());
      scoreBoardMenuView.goToGameTwo();
    } else if (view.getId() == R.id.game_three_btn) {
      scoreBoardMenuView.updateGlobal(getGlobal());
      scoreBoardMenuView.goToGameThree();
    } else if (view.getId() == R.id.game_total_btn) {
      scoreBoardMenuView.updateGlobal(getGlobal());
      scoreBoardMenuView.goToTotalBoard();
    } else if (view.getId() == R.id.game_four_btn) {
      scoreBoardMenuView.updateGlobal(getGlobal());
      scoreBoardMenuView.goToGameFour();
    } else if (view.getId() == R.id.game_five_btn) {
      scoreBoardMenuView.updateGlobal(getGlobal());
      scoreBoardMenuView.goToGameFive();
    } else if (view.getId() == R.id.display_name_switch) {
      localGame.setSaveName(!localGame.isSaveName());
      scoreBoardMenuView.setNameSwitch(localGame.isSaveName());
    } else if (view.getId() == R.id.display_scores_switch) {
      localGame.setSaveScore(!localGame.isSaveScore());
      scoreBoardMenuView.setScoreSwitch(localGame.isSaveScore());
    }
  }
}
