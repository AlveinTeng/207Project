package fall2019.csc207.snowmanadventure.Presenters;

import android.content.Context;
import android.view.View;

import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.R;
import fall2019.csc207.snowmanadventure.Views.Game.GameFive.GameFiveInsView;

/** This class describes the game five instruction presenter */
public class GameFiveInsPresenter extends BasePresenter {
  private GameFiveInsView gameFiveInsView;

  public GameFiveInsPresenter(GameFiveInsView gameFiveInsView, Global global, Context context) {
    super(global, context);
    this.gameFiveInsView = gameFiveInsView;
  }

  @Override
  public void onResume() {}

  @Override
  public void onDestroy() {
    gameFiveInsView = null;
  }

  @Override
  public void onPause() {
    if (gameFiveInsView != null) {
      getGlobal().saveAll(getContext());
    }
  }

  @Override
  public void onBackPressed() {
    gameFiveInsView.updateGlobal(getGlobal());
    gameFiveInsView.goToNewGames();
  }

  public void checkClick(View view) {
    if (view.getId() == R.id.btn_got_it5) {
      gameFiveInsView.updateGlobal(getGlobal());
      gameFiveInsView.goToGameFive();
    }
  }
}
