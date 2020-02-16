package fall2019.csc207.snowmanadventure.Views.Game.GameTwo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.Presenters.GameTwoInsPresenter;
import fall2019.csc207.snowmanadventure.R;
import fall2019.csc207.snowmanadventure.Views.Game.GameMenu.NewGamesActivity;

/** This is a class that describes the activity for the game two instruction */
public class GameTwoInsActivity extends AppCompatActivity
    implements View.OnClickListener, GameTwoInsView {

  private Global global;

  private GameTwoInsPresenter gameTwoInsPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_instruction_two);

    global = (Global) getIntent().getSerializableExtra("Global");
    gameTwoInsPresenter = new GameTwoInsPresenter(this, global, getApplicationContext());

    findViewById(R.id.btn_got_it2).setOnClickListener(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    gameTwoInsPresenter.onResume();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    gameTwoInsPresenter.onDestroy();
  }

  @Override
  protected void onPause() {
    super.onPause();
    gameTwoInsPresenter.setContext(getApplicationContext());
    gameTwoInsPresenter.onPause();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    gameTwoInsPresenter.onBackPressed();
  }

  @Override
  public void onClick(View view) {
    gameTwoInsPresenter.checkClick(view);
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
  public void goToGameTwo() {
    Intent intent = new Intent(this, GameTwoActivity.class);
    intent.putExtra("Global", global);
    startActivity(intent);
    finish();
  }
}
