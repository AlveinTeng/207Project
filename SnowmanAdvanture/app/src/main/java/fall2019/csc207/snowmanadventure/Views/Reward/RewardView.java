package fall2019.csc207.snowmanadventure.Views.Reward;

import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.Models.Reward.ImagePagerAdapter;

public interface RewardView {
  void updateGlobal(Global global);

  void goToNewGames();

  void setCurrentItem(int index);

  void setAdapter(ImagePagerAdapter imagePagerAdapter);

  void changeDescription(String newDescription);
}
