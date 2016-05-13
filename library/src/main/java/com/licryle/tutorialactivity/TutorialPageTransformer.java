package com.licryle.tutorialactivity;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.View;


public class TutorialPageTransformer implements ViewPager.PageTransformer {
  TutorialActivity _mParentActivity;
  TutorialAdapter _mAdapter;
  TutorialPagination _mPagination;

  public TutorialPageTransformer(TutorialActivity mParentActivity,
                                 TutorialAdapter mAdapter,
                                 TutorialPagination mPagination) {
    super();

    _mParentActivity = mParentActivity;
    _mAdapter = mAdapter;
    _mPagination = mPagination;
  }

  @Override
  public void transformPage(View mView, float fPosition) {
    int iPage = _mAdapter.findPageByView(mView);

    if (iPage < 0) { // Likely we savedInstance and resumed
      iPage = 0;
    }

    TutorialFragment mOtherPage;
    TutorialFragment mPage = _mAdapter.getItem(iPage);

    if (fPosition == 0) {
      mOtherPage = mPage;
    } else if (fPosition < 0) {
      mOtherPage = _mAdapter.getItem(
          Math.min(iPage + 1, _mAdapter.getCount() - 1));
    } else {
      mOtherPage = _mAdapter.getItem(Math.max(iPage - 1, 0));
    }

    mPage.transformAsPage(fPosition, iPage, _mAdapter.getCount(), mOtherPage);
    _transformPage(fPosition, iPage, _mAdapter.getCount(), mPage, mOtherPage);

    _mPagination.transformPage(
        fPosition, iPage, _mAdapter.getCount(), mPage, mOtherPage);
  }

  protected void _transformPage(float fPosition, int iPage, int iPages,
                                TutorialFragment mPage,
                                TutorialFragment mOtherPage) {
    // No global display if we're out of page, we also avoid double animation
    // by only treating the negative values
    if (fPosition <= -1.0f || fPosition > 0f) return;

    float absPosition = Math.abs(fPosition);
    int iColor = Color.argb(
        Math.round(
            Color.alpha (mPage.getBackgroundColor()) * (1 - absPosition)
                + Color.alpha(mOtherPage.getBackgroundColor()) * absPosition),
        Math.round(
            Color.red  (mPage.getBackgroundColor()) * (1 - absPosition)
                + Color.red(mOtherPage.getBackgroundColor()) * absPosition),
        Math.round(
            Color.green  (mPage.getBackgroundColor()) * (1 - absPosition)
                + Color.green(mOtherPage.getBackgroundColor()) * absPosition),
        Math.round(
            Color.blue (mPage.getBackgroundColor()) * (1 - absPosition)
                + Color.blue(mOtherPage.getBackgroundColor()) * absPosition)
    );

    _setBackgroundColor(iColor);
  }

  protected void _setBackgroundColor(int iColor) {
    _mParentActivity.getViewPager().setBackgroundColor(iColor);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      _mParentActivity.getWindow().setStatusBarColor(iColor);
    }
  }
}