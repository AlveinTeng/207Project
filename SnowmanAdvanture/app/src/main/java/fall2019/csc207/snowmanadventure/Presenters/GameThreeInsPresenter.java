package fall2019.csc207.snowmanadventure.Presenters;

import android.content.Context;
import android.view.View;

import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.R;
import fall2019.csc207.snowmanadventure.Views.Game.GameThree.GameThreeInsView;

/** This class describes the game three instruction presenter */
public class GameThreeInsPresenter extends BasePresenter {
  private GameThreeInsView gameThreeInsView;

  public GameThreeInsPresenter(GameThreeInsView gameThreeInsView, Global global, Context context) {
    super(global, context);
    this.gameThreeInsView = gameThreeInsView;
  }

  @Override
  public void onResume() {}

  @Override
  public void onDestroy() {
    gameThreeInsView = null;
  }

  @Override
  public void onPause() {
    if (gameThreeInsView != null) {
      getGlobal().saveAll(getContext());
    }
  }

  @Override
  public void onBackPressed() {
    gameThreeInsView.updateGlobal(getGlobal());
    gameThreeInsView.goToNewGames();
  }

  public void checkClick(View view) {
    if (view.getId() == R.id.btn_got_it3) {
      gameThreeInsView.updateGlobal(getGlobal());
      gameThreeInsView.goToGameThree();
    }
  }
}
