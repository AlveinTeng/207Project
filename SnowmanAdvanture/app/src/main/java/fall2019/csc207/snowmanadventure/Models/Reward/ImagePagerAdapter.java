package fall2019.csc207.snowmanadventure.Models.Reward;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

/** This class describes the image pager adapter */
public class ImagePagerAdapter extends PagerAdapter {

  /** the context for this image pager adapter */
  private Context mContext;

  /** The view list */
  private List<ImageView> mViewList = new ArrayList<>();

  public ImagePagerAdapter(Context context, List<Integer> snowmans) {
    mContext = context;
    for (int i = 0; i < snowmans.size(); i++) {
      ImageView view = new ImageView(mContext);
      view.setLayoutParams(
          new ViewGroup.LayoutParams(
              ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
      view.setImageResource(snowmans.get(i));
      view.setScaleType(ImageView.ScaleType.FIT_CENTER);
      mViewList.add(view);
    }
  }

  @Override
  public int getCount() {
    return mViewList.size();
  }

  /**
   * check the view is equal to the object
   *
   * @param view view
   * @param object view
   * @return the view is equal to the object
   */
  @Override
  public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  /**
   * destory the item
   *
   * @param container view group
   * @param position the position
   * @param object the object
   */
  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView(mViewList.get(position));
  }

  /**
   * Instantiate the item
   *
   * @param container view group
   * @param position position
   * @return the specific item
   */
  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    container.addView(mViewList.get(position));
    return mViewList.get(position);
  }
}
