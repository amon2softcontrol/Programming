package com.geetaanjali.drishyam;

/**
 * Created by Amon on 20/9/2558.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;


public class Remote  extends Activity implements View.OnClickListener{


    private static final int tagCount = 3;
    private static final String tag1="guruji";
    private static final String tag2="destructive";
    private static final String tag3="sensor";
    private static final String tag4="sms";
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.remote);
        WebView remote = (WebView) findViewById(R.id.webView1);
        remote.getSettings().setJavaScriptEnabled(true);
        remote.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        remote.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                //Make the bar disappear after URL is loaded, and changes string to Loading...
                setTitle("Loading...");
                setProgress(progress * 100); //Make the bar disappear after URL is loaded

                // Return the app name after finish loading
                if (progress == 100)
                    setTitle(R.string.app_name);
            }
        });
        remote.loadUrl("http://www.geetaanjali.com");
        ImageView icon = new ImageView(this); // Create an icon
        icon.setImageResource(R.drawable.k1);

        FloatingActionButton actionButton = new FloatingActionButton.Builder(this).setContentView(icon).build();
        //getTodo();

        ImageView icon1 = new ImageView(this);
        icon1.setImageResource(R.drawable.destructive1);
        ImageView icon2 = new ImageView(this);
        icon2.setImageResource(R.drawable.sensor1);
        ImageView icon3 = new ImageView(this);
        icon3.setImageResource(R.drawable.amon);
        ImageView iconSortName13 = new ImageView(this);
        iconSortName13.setImageResource(R.drawable.mobileprogramming1);

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        SubActionButton button1 =  itemBuilder.setContentView(icon1).build();
        SubActionButton button2 =  itemBuilder.setContentView(icon2).build();
        SubActionButton button3 =  itemBuilder.setContentView(icon3).build();
        SubActionButton button4 = itemBuilder.setContentView(iconSortName13).build();

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .addSubActionView(button4)
                .attachTo(actionButton)
                .build();

        button1.setTag(tag2);
        button2.setTag(tag3);
        button3.setTag(tag1);
        button4.setTag(tag4);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.platform_botton_anim_in, R.anim.slidedown);
    }
    @Override
    public void onClick(View v) {
        if (v.getTag().equals(tag1)){
            Intent homeIntent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(homeIntent);
        }
        if (v.getTag().equals(tag2)){
            Intent homeIntent = new Intent(getApplicationContext(),Destructive.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
         }
        if (v.getTag().equals(tag3)){
            Intent homeIntent = new Intent(getApplicationContext(),bluetoothMainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        if (v.getTag().equals(tag4)){
            Intent homeIntent = new Intent(getApplicationContext(),SmsDrishyam.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
    }



}

//MaterialSearchView searchView;
//searchView = (MaterialSearchView) findViewById(R.id.search_view12);
//        searchView.setSuggestions(getResources().getStringArray(R.array.Warriors));
//        searchView.setCursorDrawable(R.drawable.custom_cursor);
//searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
//@Override
//public boolean onQueryTextSubmit(String query) {
//        //Do some magic
//        return false;
//        }
//
//@Override
//public boolean onQueryTextChange(String newText) {
//        //Do some magic
//        return false;

//        }
//        });
//
//        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
//@Override
//public void onSearchViewShown() {
//        //Do some magic
//        }
//
//@Override
//public void onSearchViewClosed() {
//        //Do some magic
//        }
//        });

//
//package com.geetaanjali.drishyam;
//
///**
// * Created by Amon on 20/9/2558.
// */
//
//        import android.app.Activity;
//        import android.content.Intent;
//        import android.os.Bundle;
//        import android.view.Gravity;
//        import android.view.View;
//        import android.widget.ImageView;
//        import android.widget.Toast;
//
//        import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
//        import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
//        import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
//        import android.content.res.Configuration;
//        import android.graphics.Color;
//        import android.graphics.drawable.BitmapDrawable;
//        import android.os.Bundle;
//        import android.support.v4.widget.DrawerLayout;
//        import android.support.v7.app.ActionBarActivity;
//        import android.support.v7.app.ActionBarDrawerToggle;
//        import android.support.v7.widget.Toolbar;
//        import android.view.Menu;
//        import android.view.MenuItem;
//        import android.view.View;
//        import android.view.animation.AccelerateInterpolator;
//        import android.widget.LinearLayout;
//
//        import java.util.ArrayList;
//        import java.util.List;
//
//        import android.content.res.Configuration;
//        import android.graphics.Color;
//        import android.graphics.drawable.BitmapDrawable;
//        import android.os.Bundle;
//        import android.support.v4.widget.DrawerLayout;
//        import android.support.v7.app.ActionBarActivity;
//        import android.support.v7.app.ActionBarDrawerToggle;
//        import android.support.v7.widget.Toolbar;
//        import android.view.Menu;
//        import android.view.MenuItem;
//        import android.view.View;
//        import android.view.animation.AccelerateInterpolator;
//        import android.widget.LinearLayout;
//
//        import java.util.ArrayList;
//        import java.util.List;
//
//        import io.codetail.animation.SupportAnimator;
//        import io.codetail.animation.ViewAnimationUtils;
//        import com.geetaanjali.drishyam.interfaces.Resourceble;
//        import com.geetaanjali.drishyam.interfaces.ScreenShotable;
//        import com.geetaanjali.drishyam.model.SlideMenuItem;
//        import com.geetaanjali.drishyam.sample.fragment.ContentFragment;
//        import com.geetaanjali.drishyam.util.ViewAnimator;
//
//
//public class Remote  extends Activity implements View.OnClickListener{
//
//
//    private static final int tagCount = 3;
//    private static final String tag1="guruji";
//    private static final String tag2="destructive";
//    private static final String tag3="sensor";
//    private DrawerLayout drawerLayout;
//    private ActionBarDrawerToggle drawerToggle;
//    private List<SlideMenuItem> list = new ArrayList<>();
//    private ContentFragment contentFragment;
//    private ViewAnimator viewAnimator;
//    private int res = R.drawable.content_music;
//    private LinearLayout linearLayout;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        contentFragment = ContentFragment.newInstance(R.drawable.content_music);
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.content_frame, contentFragment)
//                .commit();
//        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawerLayout.setScrimColor(Color.TRANSPARENT);
//        linearLayout = (LinearLayout) findViewById(R.id.left_drawer);
//        linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout.closeDrawers();
//            }
//        });
//
//
//        setActionBar();
//        createMenuList();
//        viewAnimator = new ViewAnimator<>(this, list, contentFragment, drawerLayout, this);
//
//        ImageView icon = new ImageView(this); // Create an icon
//        icon.setImageResource(R.drawable.k1);
//
//        FloatingActionButton actionButton = new FloatingActionButton.Builder(this).setContentView(icon).build();
//        //getTodo();
//
//        ImageView icon1 = new ImageView(this);
//        icon1.setImageResource(R.drawable.destructive1);
//        ImageView icon2 = new ImageView(this);
//        icon2.setImageResource(R.drawable.sensor1);
//        ImageView icon3 = new ImageView(this);
//        icon3.setImageResource(R.drawable.guruji);
//
//        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
//        SubActionButton button1 =  itemBuilder.setContentView(icon1).build();
//        SubActionButton button2 =  itemBuilder.setContentView(icon2).build();
//        SubActionButton button3 =  itemBuilder.setContentView(icon3).build();
//
//        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
//                .addSubActionView(button1)
//                .addSubActionView(button2)
//                .addSubActionView(button3)
//                .attachTo(actionButton)
//                .build();
//
//        button1.setTag(tag2);
//        button2.setTag(tag3);
//        button3.setTag(tag1);
//
//        button1.setOnClickListener(this);
//        button2.setOnClickListener(this);
//        button3.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        if (v.getTag().equals(tag1)){
//            Toast toast = Toast.makeText(getApplicationContext(), "May Radhekrishna Bless you!!\r\n  Geeta Padho :)", Toast.LENGTH_LONG);
//            toast.setGravity(Gravity.TOP | Gravity.CENTER, 120, 120);
//            toast.show();
//        }
//        if (v.getTag().equals(tag2)){
//            Intent homeIntent = new Intent(getApplicationContext(),Destructive.class);
//            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(homeIntent);
//        }
//        if (v.getTag().equals(tag3)){
//            Intent homeIntent = new Intent(getApplicationContext(),Sensor.class);
//            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(homeIntent);
//        }
//    }
//
//    private void createMenuList() {
//        SlideMenuItem menuItem0 = new SlideMenuItem(ContentFragment.CLOSE, R.drawable.icn_close);
//        list.add(menuItem0);
//        SlideMenuItem menuItem = new SlideMenuItem(ContentFragment.BUILDING, R.drawable.icn_1);
//        list.add(menuItem);
//        SlideMenuItem menuItem2 = new SlideMenuItem(ContentFragment.BOOK, R.drawable.icn_2);
//        list.add(menuItem2);
//        SlideMenuItem menuItem3 = new SlideMenuItem(ContentFragment.PAINT, R.drawable.icn_3);
//        list.add(menuItem3);
//        SlideMenuItem menuItem4 = new SlideMenuItem(ContentFragment.CASE, R.drawable.icn_4);
//        list.add(menuItem4);
//        SlideMenuItem menuItem5 = new SlideMenuItem(ContentFragment.SHOP, R.drawable.icn_5);
//        list.add(menuItem5);
//        SlideMenuItem menuItem6 = new SlideMenuItem(ContentFragment.PARTY, R.drawable.icn_6);
//        list.add(menuItem6);
//        SlideMenuItem menuItem7 = new SlideMenuItem(ContentFragment.MOVIE, R.drawable.icn_7);
//        list.add(menuItem7);
//    }
//
//
//    private void setActionBar() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        drawerToggle = new ActionBarDrawerToggle(
//                this,                  /* host Activity */
//                drawerLayout,         /* DrawerLayout object */
//                toolbar,  /* nav drawer icon to replace 'Up' caret */
//                R.string.drawer_open,  /* "open drawer" description */
//                R.string.drawer_close  /* "close drawer" description */
//        ) {
//
//            /** Called when a drawer has settled in a completely closed state. */
//            public void onDrawerClosed(View view) {
//                super.onDrawerClosed(view);
//                linearLayout.removeAllViews();
//                linearLayout.invalidate();
//            }
//
//            @Override
//            public void onDrawerSlide(View drawerView, float slideOffset) {
//                super.onDrawerSlide(drawerView, slideOffset);
//                if (slideOffset > 0.6 && linearLayout.getChildCount() == 0)
//                    viewAnimator.showMenuContent();
//            }
//
//            /** Called when a drawer has settled in a completely open state. */
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//            }
//        };
//        drawerLayout.setDrawerListener(drawerToggle);
//    }
//
//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        drawerToggle.syncState();
//    }
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        drawerToggle.onConfigurationChanged(newConfig);
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (drawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        switch (item.getItemId()) {
//            case R.id.action_settings:
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    private ScreenShotable replaceFragment(ScreenShotable screenShotable, int topPosition) {
//        this.res = this.res == R.drawable.content_music ? R.drawable.content_films : R.drawable.content_music;
//        View view = findViewById(R.id.content_frame);
//        int finalRadius = Math.max(view.getWidth(), view.getHeight());
//        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
//        animator.setInterpolator(new AccelerateInterpolator());
//        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);
//
//        findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
//        animator.start();
//        ContentFragment contentFragment = ContentFragment.newInstance(this.res);
//        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, contentFragment).commit();
//        return contentFragment;
//    }
//
//    @Override
//    public ScreenShotable onSwitch(Resourceble slideMenuItem, ScreenShotable screenShotable, int position) {
//        switch (slideMenuItem.getName()) {
//            case ContentFragment.CLOSE:
//                return screenShotable;
//            default:
//                return replaceFragment(screenShotable, position);
//        }
//    }
//
//    @Override
//    public void disableHomeButton() {
//        getSupportActionBar().setHomeButtonEnabled(false);
//
//    }
//
//    @Override
//    public void enableHomeButton() {
//        getSupportActionBar().setHomeButtonEnabled(true);
//        drawerLayout.closeDrawers();
//
//    }
//
//    @Override
//    public void addViewToContainer(View view) {
//        linearLayout.addView(view);
//    }
//
//
//}
//
////MaterialSearchView searchView;
////searchView = (MaterialSearchView) findViewById(R.id.search_view12);
////        searchView.setSuggestions(getResources().getStringArray(R.array.Warriors));
////        searchView.setCursorDrawable(R.drawable.custom_cursor);
////searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
////@Override
////public boolean onQueryTextSubmit(String query) {
////        //Do some magic
////        return false;
////        }
////
////@Override
////public boolean onQueryTextChange(String newText) {
////        //Do some magic
////        return false;
////        }
////        });
////
////        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
////@Override
////public void onSearchViewShown() {
////        //Do some magic
////        }
////
////@Override
////public void onSearchViewClosed() {
////        //Do some magic
////        }
////        });
//











