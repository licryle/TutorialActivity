package com.licryle.tutorialactivity;

import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.licryle.IntroActivity.R;

import java.util.ArrayList;

/**
 * Created by licryle on 5/12/16.
 */
public class TutorialPagination {
  protected TutorialActivity _mActivity;
  protected LinearLayout _mPaginationView;

  protected ArrayList<ImageView> _aPages;
  protected int _iSelectedItem;

  public TutorialPagination(TutorialActivity mActivity) {
    super();

    _aPages = new ArrayList<ImageView>();
    _mActivity = mActivity;

    _createContainer();
    _createPages();
    _setSelectedPage(0);
  }

  public LinearLayout getPaginationView() {
    return _mPaginationView;
  }

  public void setVisible(boolean bVisible) {
    _mPaginationView.setVisibility(bVisible ? View.VISIBLE : View.INVISIBLE);
  }

  public void transformPage(
      float fPosition, int iPage, int count,
      TutorialFragment mPage, TutorialFragment mOtherPage) {
    if (fPosition == 0 && iPage != _iSelectedItem) {
      _setSelectedPage(iPage);
    }
  }

  protected void _setSelectedPage(int iPage) {
    for(int i = 0; i < _aPages.size(); i++) {
      ImageView mView = (ImageView) _aPages.get(i);

      ViewGroup.LayoutParams mParams = mView.getLayoutParams();

      if (i == iPage) {
        mView.setColorFilter(_getSelectedIconColor());
        mParams.height = _getSelectedIconSize();
        mParams.width = _getSelectedIconSize();
        _iSelectedItem = iPage;
      } else {
        mView.setColorFilter(_getUnSelectedIconColor());
        mParams.height = _getUnSelectedIconSize();
        mParams.width = _getUnSelectedIconSize();
      }

      mView.setLayoutParams(mParams);
    }
  }

  protected void _createContainer() {
    _mPaginationView = new LinearLayout(_mActivity.getApplicationContext());
    _mPaginationView.setOrientation(LinearLayout.HORIZONTAL);
    _mPaginationView.setClickable(false);


    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      _mPaginationView.setId(_mPaginationView.generateViewId());
    } else {
      //noinspection ResourceType
      _mPaginationView.setId(515484846);
    }

    _mActivity.getContainerView().addView(_mPaginationView);

    RelativeLayout.LayoutParams mParams =
        (RelativeLayout.LayoutParams) _mPaginationView.getLayoutParams();
    mParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
    mParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
    mParams.alignWithParent = true;

    Integer[] aMargins = _getMargins();
    mParams.setMargins(aMargins[0], aMargins[1], aMargins[2], aMargins[3]);
    _mPaginationView.setGravity(Gravity.CENTER);
    mParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, _mPaginationView.getId());

    _mPaginationView.setLayoutParams(mParams);
    _mPaginationView.setVisibility(View.VISIBLE);
  }

  protected void _createPages() {
    for(int i = 0; i < _mActivity.getPageCount(); i++) {
      ImageView mPage = new ImageView(_mActivity.getApplicationContext());

      mPage.setImageResource(_getPageIconResource());

      mPage.setTag(i);
      mPage.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          _mActivity.setPage((int) v.getTag());
        }
      });

      _aPages.add(i, mPage);
      _mPaginationView.addView(mPage);

      LinearLayout.LayoutParams mParams =
          (LinearLayout.LayoutParams) mPage.getLayoutParams();
      mParams.setMargins(
          _getIconLeftRightMargin(), 0, _getIconLeftRightMargin(), 0);
      mPage.setLayoutParams(mParams);
    }
  }

  protected int _getPageIconResource() {
    return R.drawable.ic_brightness_1_black_24dp;
  }

  protected int _getUnSelectedIconColor() {
    return Color.BLACK;
  }

  protected int _getSelectedIconColor() {
    return Color.GRAY;
  }

  protected int _getIconLeftRightMargin() {
    return 10;
  }

  protected Integer[] _getMargins() {
    return new Integer[]{25, 25, 25, 100};
  }

  protected int _getSelectedIconSize() { return 40; }
  protected int _getUnSelectedIconSize() {
    return 30;
  }
}
