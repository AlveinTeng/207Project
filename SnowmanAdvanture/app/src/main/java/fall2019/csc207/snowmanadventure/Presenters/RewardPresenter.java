package fall2019.csc207.snowmanadventure.Presenters;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.Models.Reward.ImagePagerAdapter;
import fall2019.csc207.snowmanadventure.R;
import fall2019.csc207.snowmanadventure.Views.Reward.RewardView;

/** This class describes the reward presenter */
public class RewardPresenter extends BasePresenter {

  private RewardView rewardView;

  private int index = 0;

  public RewardPresenter(RewardView rewardView, Global global, Context context) {
    super(global, context);
    this.rewardView = rewardView;
    initViewPager();
    initMusic();
  }

  @Override
  public void onResume() {
    getMusicManager().playGameMusic(2, false, getGlobal().getWantThug());
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    rewardView = null;
  }

  @Override
  public void onPause() {
    if (rewardView != null) {
      getGlobal().saveAll(getContext());
      getMusicManager().pauseGameMusic(2, false, getGlobal().getWantThug());
    }
  }

  @Override
  public void onBackPressed() {
    rewardView.updateGlobal(getGlobal());
    rewardView.goToNewGames();
  }

  public void checkOnPage(int position) {
    changeText(position);
    getGlobal().changeThug(position);
    if (getGlobal().getDisplay(false)) {
      getMusicManager().pauseGameMusic(2, false, !getGlobal().getWantThug());
      getMusicManager().playGameMusic(2, false, getGlobal().getWantThug());
    }
  }

  private void initViewPager() {
    List<Integer> snowmans = new ArrayList<>();
    snowmans.add(R.drawable.snowman_stand);
    snowmans.add(
        (getGlobal().getDisplay(false)) ? R.drawable.snowthug1 : R.drawable.lockedsnowthug);
    index = (getGlobal().getWantThug()) ? 1 : 0;
    rewardView.setAdapter(new ImagePagerAdapter(getContext(), snowmans));
    rewardView.setCurrentItem(index);
    changeText(index);
  }

  private void changeText(int position) {
    if (position == 0) {
      rewardView.changeDescription(
          "This is a base snowman character, who is clever and ready to take adventures with you.");
    } else {
      if (getGlobal().getDisplay(false)) {
        rewardView.changeDescription(
            "This is a snow thug which is your reward! After so many adventures, he is a now a tough grown adult, not a child snow man!");
      } else {
        rewardView.changeDescription("??? (you need 500 total scores to unlock!)");
      }
    }
  }
}
