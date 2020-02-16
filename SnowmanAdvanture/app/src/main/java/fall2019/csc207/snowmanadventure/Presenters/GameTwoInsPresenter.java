package fall2019.csc207.snowmanadventure.Presenters;

import android.content.Context;
import android.view.View;

import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.R;
import fall2019.csc207.snowmanadventure.Views.Game.GameTwo.GameTwoInsView;

/** This class describes the game two instruction presenter */
public class GameTwoInsPresenter extends BasePresenter {
  private GameTwoInsView gameTwoInsView;

  public GameTwoInsPresenter(GameTwoInsView gameTwoInsView, Global global, Context context) {
    super(global, context);
    this.gameTwoInsView = gameTwoInsView;
  }

  @Override
  public void onResume() {}

  @Override
  public void onDestroy() {
    gameTwoInsView = null;
  }

  @Override
  public void onPause() {
    if (gameTwoInsView != null) {
      getGlobal().saveAll(getContext());
    }
  }

  @Override
  public void onBackPressed() {
    gameTwoInsView.updateGlobal(getGlobal());
    gameTwoInsView.goToNewGames();
  }

  public void checkClick(View view) {
    if (view.getId() == R.id.btn_got_it2) {
      gameTwoInsView.updateGlobal(getGlobal());
      gameTwoInsView.goToGameTwo();
    }
  }
}
