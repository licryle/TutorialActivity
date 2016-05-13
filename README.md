# TutorialActivity
A little library to easily build a Tutorial Activity in Android.
My original learnings come from this quick [tutorial](https://medium.com/android-news/creating-an-intro-screen-for-your-app-using-viewpager-pagetransformer-9950517ea04f#.cedk02txr).

This tiny library is composed of 4 classes:
- [TutorialActivity](https://github.com/licryle/TutorialActivity/blob/master/library/src/main/java/com/licryle/tutorialactivity/TutorialActivity.java) - This is the main class as it is the actual Activity you want to instanciate. It must be derived into its own class, in particular to implement the _createFragments() which should create instances of the following class:
- [TutorialFragment](https://github.com/licryle/TutorialActivity/blob/master/library/src/main/java/com/licryle/tutorialactivity/TutorialFragment.java) - This class is the second one you want to derive as it represents each Fragment you'll be swiping in. A least, you'll want to override _getLayoutResourceId(), transformAsPage() and onViewCreated().
- [TutorialPageTransformer](https://github.com/licryle/TutorialActivity/blob/master/library/src/main/java/com/licryle/tutorialactivity/TutorialPageTransformer.java) - This class handles the transition work between the different Fragments - "Pages". By default, it calls back into each Fragment for rendering, and does update the background color to an aliasing between the 2 background colors. It also calls the rendering of the following Pagination object:
- [TutorialPagination](https://github.com/licryle/TutorialActivity/blob/master/library/src/main/java/com/licryle/tutorialactivity/TutorialPagination.java) - Finally, this pagination class handles the basic Paginator at the bottom of the fragment. You'll want to override it for its protected methods related to styles & advanced animation. The items are clickable and by default simply change color as you change pages. Nothing fancy but nice not to have to handle manually.

# How to use?
## Android Studio Setup
In your Android Studio project, create a folder called libs, for example: ./AndroidStudioProject/MyProject/libs

Download the repository files and unpack either:
* At the root of ./AndroidStudioProjects/ then create a symbolic link from ./AndroidStudioProject/MyProject/libs/TutorialActivity to ./AndroidStudioProject/TutorialActivity/library
* The directory "library" into the ./AndroidStudioProject/MyProject/libs/ directory and rename the "library" folder to "TutorialActivity"

In your build.gradle Module file, add the dependency

```
dependencies {
   ...
    compile project(':libs:TutorialActivity')
}
```

## Quick usage
```
public class IntroActivity extends TutorialActivity {
  @Override
  protected void _createFragments() {
    _addFragment(TutorialFragment.newInstance(Color.parseColor("#03A9F4")));
    _addFragment(TutorialFragment.newInstance(Color.parseColor("#4CAF50")));
    _addFragment(TutorialFragment.newInstance(Color.parseColor("#FF0000")));
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }
}

// Don't forget to declare your new IntroActivity in your manifest!
        <activity
            android:name=".IntroActivity"
            android:label="@string/introactivity_title"
            android:screenOrientation="portrait" >
        </activity>

// And then send an Intent to start it
Intent mIntent = new Intent(this, IntroActivity.class);
startActivity(mIntent);

```