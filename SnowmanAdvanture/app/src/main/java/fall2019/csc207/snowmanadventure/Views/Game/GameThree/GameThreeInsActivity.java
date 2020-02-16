package fall2019.csc207.snowmanadventure.Views.Game.GameThree;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.Presenters.GameThreeInsPresenter;
import fall2019.csc207.snowmanadventure.R;
import fall2019.csc207.snowmanadventure.Views.Game.GameMenu.NewGamesActivity;

/** This is a class that describes the activity for the game three instruction */
public class GameThreeInsActivity extends AppCompatActivity
    implements View.OnClickListener, GameThreeInsView {

  private Global global;

  private GameThreeInsPresenter gameThreeInsPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_instruction_three);

    global = (Global) getIntent().getSerializableExtra("Global");
    gameThreeInsPresenter = new GameThreeInsPresenter(this, global, getApplicationContext());

    findViewById(R.id.btn_got_it3).setOnClickListener(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    gameThreeInsPresenter.onResume();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    gameThreeInsPresenter.onDestroy();
  }

  @Override
  protected void onPause() {
    super.onPause();
    gameThreeInsPresenter.setContext(getApplicationContext());
    gameThreeInsPresenter.onPause();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    gameThreeInsPresenter.onBackPressed();
  }

  @Override
  public void onClick(View view) {
    gameThreeInsPresenter.checkClick(view);
  }

  @Override
  public void updateGlobal(Global global) {
    this.global = global;
  }

  @Override
  public void goToNewGames() {
    Intent intent = new Intent(this, NewGamesActivity.class);
    intent.putExtra("Global", global);
    startActivity(intent);
    finish();
  }

  @Override
  public void goToGameThree() {
    Intent intent = new Intent(this, GameThreeActivity.class);
    intent.putExtra("Global", global);
    startActivity(intent);
    finish();
  }
}
