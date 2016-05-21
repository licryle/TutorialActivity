package com.licryle.tutorialactivity;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TutorialFragment extends Fragment {
  protected static final String LAYOUT_RESOURCE = "layout_resource";
  protected int _iLayoutResId;
  protected int _iBackgroundColor;

  protected View _mView = null;

  public static TutorialFragment newInstance(int iLayoutResId) {
    TutorialFragment mFragment = new TutorialFragment();

    Bundle mBundle = new Bundle();
    mBundle.putInt(LAYOUT_RESOURCE, iLayoutResId);
    mFragment.setArguments(mBundle);

    return mFragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (!getArguments().containsKey(LAYOUT_RESOURCE))
      throw new RuntimeException("TutorialFragment must contain a \"" +
          LAYOUT_RESOURCE + "\" argument!");

    _iLayoutResId = getArguments().getInt(LAYOUT_RESOURCE);

    try {
      getActivity().getResources().getLayout(_iLayoutResId);
    } catch (Resources.NotFoundException mException) {
      throw new RuntimeException("TutorialFragment must be provided a valid" +
          " Layout Resource. This one couldn't be found.");
    }
  }

  public int getBackgroundColor() {
    return _iBackgroundColor;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    int layoutResId = _getLayoutResourceId();

    // Inflate the layout resource file
    _mView = getActivity().getLayoutInflater().inflate(layoutResId,
        container, false);

    _iBackgroundColor = ((ColorDrawable) _mView.getBackground()).getColor();
    _mView.setBackgroundColor(Color.TRANSPARENT);

    return _mView;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  public void transformAsPage(float dPosition, int iPage, int iNbPages,
                              TutorialFragment mOtherPage) {
  }

  protected int _getLayoutResourceId() {
    return _iLayoutResId;
  }
}