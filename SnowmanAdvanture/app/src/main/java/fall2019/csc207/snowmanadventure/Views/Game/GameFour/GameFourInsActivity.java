package fall2019.csc207.snowmanadventure.Views.Game.GameFour;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.Presenters.GameFourInsPresenter;
import fall2019.csc207.snowmanadventure.R;
import fall2019.csc207.snowmanadventure.Views.Game.GameMenu.NewGamesActivity;

/** This is a class that describes the game five instruction activity */
public class GameFourInsActivity extends AppCompatActivity
    implements View.OnClickListener, GameFourInsView {
  private Global global;

  private GameFourInsPresenter gameFourInsPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_instruction_four);

    global = (Global) getIntent().getSerializableExtra("Global");
    gameFourInsPresenter = new GameFourInsPresenter(this, global, getApplicationContext());

    findViewById(R.id.btn_got_it4).setOnClickListener(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    gameFourInsPresenter.onResume();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    gameFourInsPresenter.onDestroy();
  }

  @Override
  protected void onPause() {
    super.onPause();
    gameFourInsPresenter.setContext(getApplicationContext());
    gameFourInsPresenter.onPause();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    gameFourInsPresenter.onBackPressed();
  }

  @Override
  public void onClick(View view) {
    gameFourInsPresenter.checkClick(view);
  }

  @Override
  public void updateGlobal(Global global) {
    this.global = global;
  }

  @Override
  public void goToGameFour() {
    Intent intent = new Intent(this, GameFourActivity.class);
    intent.putExtra("Global", global);
    startActivity(intent);
    finish();
  }

  @Override
  public void goToNewGames() {
    Intent intent = new Intent(this, NewGamesActivity.class);
    intent.putExtra("Global", global);
    startActivity(intent);
    finish();
  }
}
