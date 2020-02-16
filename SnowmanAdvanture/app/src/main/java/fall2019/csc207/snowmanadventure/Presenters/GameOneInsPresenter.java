package fall2019.csc207.snowmanadventure.Presenters;

import android.content.Context;
import android.view.View;

import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.R;
import fall2019.csc207.snowmanadventure.Views.Game.GameOne.GameOneInsView;

/** This class describes the game one instruction presenter */
public class GameOneInsPresenter extends BasePresenter {

  private GameOneInsView gameOneInsView;

  public GameOneInsPresenter(GameOneInsView gameOneInsView, Global global, Context context) {
    super(global, context);
    this.gameOneInsView = gameOneInsView;
  }

  @Override
  public void onResume() {}

  @Override
  public void onDestroy() {
    gameOneInsView = null;
  }

  @Override
  public void onPause() {
    if (gameOneInsView != null) {
      getGlobal().saveAll(getContext());
    }
  }

  @Override
  public void onBackPressed() {
    gameOneInsView.updateGlobal(getGlobal());
    gameOneInsView.goToNewGames();
  }

  public void checkClick(View view) {
    if (view.getId() == R.id.btn_got_it) {
      gameOneInsView.updateGlobal(getGlobal());
      gameOneInsView.goToGameOne();
    }
  }
}
