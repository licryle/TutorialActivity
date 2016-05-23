package com.licryle.tutorialactivity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.licryle.IntroActivity.R;

abstract public class TutorialActivity extends AppCompatActivity {
  protected ViewPager _mViewPager;
  protected TutorialAdapter _mAdapter;
  protected RelativeLayout _mContainer;
  protected TutorialPagination _mPagination;
  protected TutorialPageTransformer _mPageTransformer;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.tutorial_layout);

    _mContainer = (RelativeLayout) findViewById(R.id.container);
    _mViewPager = (ViewPager) findViewById(R.id.viewpager);
    _mAdapter = new TutorialAdapter(getSupportFragmentManager());

    _createFragments();

    // Set an Adapter on the ViewPager
    _mViewPager.setAdapter(_mAdapter);
    _mPagination = _createPagination();

    // Set a PageTransformer
    _mPageTransformer = new TutorialPageTransformer(
        this, _mAdapter, _mPagination);
    _mViewPager.setPageTransformer(false, _mPageTransformer);
  }

  public ViewPager getViewPager() {
    return _mViewPager;
  }
  public RelativeLayout getContainerView() { return _mContainer; }

  public void setPage(int iPage) {
    if (iPage < 0 || iPage >= _mAdapter.getCount()) return;

    _mViewPager.setCurrentItem(iPage);
  }

  public int getPageCount() { return _mAdapter.getCount(); }

  protected void _addFragment(TutorialFragment mFragment) {
    _mAdapter.addFragment(mFragment);
  }

  protected TutorialPagination _createPagination() {
    return new TutorialPagination(this);
  }

  abstract protected void _createFragments();

  public void finishActivity(View view) {
    finish();
  }
}