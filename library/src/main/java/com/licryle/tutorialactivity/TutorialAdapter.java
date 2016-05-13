package com.licryle.tutorialactivity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import java.util.ArrayList;

class TutorialAdapter extends FragmentPagerAdapter {
  protected ArrayList<TutorialFragment> _mFragments = new ArrayList<>();

  public TutorialAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override
  public TutorialFragment getItem(int position) {
    return _mFragments.get(position);
  }

  @Override
  public int getCount() {
    return _mFragments.size();
  }

  public int addFragment(TutorialFragment mFragment) {
    _mFragments.add(mFragment);

    return getCount() - 1;
  }

  public int findPageByView(View mView) {
    for (int i = 0; i < _mFragments.size(); i ++) {
      if (_mFragments.get(i).getView() != null
          && _mFragments.get(i).getView().equals(mView))
        return i;
    }

    return -1;
  }
}