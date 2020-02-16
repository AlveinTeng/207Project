package fall2019.csc207.snowmanadventure.Views.Reward;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.Models.Reward.ImagePagerAdapter;
import fall2019.csc207.snowmanadventure.Presenters.RewardPresenter;
import fall2019.csc207.snowmanadventure.R;
import fall2019.csc207.snowmanadventure.Views.Game.GameMenu.NewGamesActivity;

/** This class describes the reward activity */
public class RewardActivity extends AppCompatActivity
    implements ViewPager.OnPageChangeListener, RewardView {

  private Global global;

  private RewardPresenter rewardPresenter;

  private ViewPager viewPager;

  private TextView tv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_reward);
    global = (Global) getIntent().getSerializableExtra("Global");
    viewPager = findViewById(R.id.vp_viewPager);
    tv = findViewById(R.id.REWARD_DESCRIPTION);
    rewardPresenter = new RewardPresenter(this, global, getApplicationContext());
    viewPager.addOnPageChangeListener(this);
  }

  @Override
  public void changeDescription(String newDescription) {
    tv.setText(newDescription);
  }

  @Override
  protected void onResume() {
    super.onResume();
    rewardPresenter.onResume();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    rewardPresenter.onDestroy();
  }

  @Override
  protected void onPause() {
    super.onPause();
    rewardPresenter.setContext(getApplicationContext());
    rewardPresenter.onPause();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    rewardPresenter.onBackPressed();
  }

  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

  @Override
  public void onPageSelected(int position) {
    rewardPresenter.checkOnPage(position);
  }

  @Override
  public void onPageScrollStateChanged(int state) {}

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
  public void setCurrentItem(int index) {
    viewPager.setCurrentItem(index);
  }

  @Override
  public void setAdapter(ImagePagerAdapter imagePagerAdapter) {
    viewPager.setAdapter(imagePagerAdapter);
  }
}
