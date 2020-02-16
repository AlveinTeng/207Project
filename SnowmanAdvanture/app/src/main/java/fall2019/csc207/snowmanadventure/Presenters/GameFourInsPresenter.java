package fall2019.csc207.snowmanadventure.Presenters;

import android.content.Context;
import android.view.View;

import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.R;
import fall2019.csc207.snowmanadventure.Views.Game.GameFour.GameFourInsView;

/** This class describes the game four instruction presenter */
public class GameFourInsPresenter extends BasePresenter {
  private GameFourInsView gameFourInsView;

  public GameFourInsPresenter(GameFourInsView gameFourInsView, Global global, Context context) {
    super(global, context);
    this.gameFourInsView = gameFourInsView;
  }

  @Override
  public void onResume() {}

  @Override
  public void onDestroy() {
    gameFourInsView = null;
  }

  @Override
  public void onPause() {
    if (gameFourInsView != null) {
      getGlobal().saveAll(getContext());
    }
  }

  @Override
  public void onBackPressed() {
    gameFourInsView.updateGlobal(getGlobal());
    gameFourInsView.goToNewGames();
  }

  public void checkClick(View view) {
    if (view.getId() == R.id.btn_got_it4) {
      gameFourInsView.updateGlobal(getGlobal());
      gameFourInsView.goToGameFour();
    }
  }
}
