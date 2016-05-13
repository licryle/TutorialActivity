package com.licryle.tutorialactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.licryle.IntroActivity.R;

public class TutorialFragment extends Fragment {
  protected static final String BACKGROUND_COLOR = "backgroundColor";
  protected int _mBackgroundColor;

  public static TutorialFragment newInstance(int iBackgroundColor) {
    TutorialFragment mFragment = new TutorialFragment();

    Bundle mBundle = new Bundle();
    mBundle.putInt(BACKGROUND_COLOR, iBackgroundColor);
    mFragment.setArguments(mBundle);

    return mFragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (!getArguments().containsKey(BACKGROUND_COLOR))
      throw new RuntimeException("Fragment must contain a \"" + BACKGROUND_COLOR + "\" argument!");
    _mBackgroundColor = getArguments().getInt(BACKGROUND_COLOR);
  }

  public int getBackgroundColor() { return _mBackgroundColor; }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    int layoutResId = _getLayoutResourceId();

    // Inflate the layout resource file
    View view = getActivity().getLayoutInflater().inflate(layoutResId, container, false);

    return view;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  public void transformAsPage(float dPosition, int iPage, int iNbPages,
                              TutorialFragment mOtherPage) {
  }

  protected int _getLayoutResourceId() {
    return R.layout.tutorial_fragment_layout_example;
  }
}