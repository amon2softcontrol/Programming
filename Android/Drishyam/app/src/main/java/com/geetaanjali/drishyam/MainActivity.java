package com.geetaanjali.drishyam;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.Animator;
import com.rubengees.introduction.IntroductionBuilder;
import com.rubengees.introduction.entity.Slide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle drawerListenter;
    private MyAdapter myAdapter;
    private ImageButton transitToPlatform;
    private Editable emaiText;
    private String[] warriors;
    PowerManager.WakeLock wakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (isFirstTime()) {
            new IntroductionBuilder(this).withSlides(generateSlides()).withStyle(IntroductionBuilder.STYLE_FULLSCREEN).introduceMyself();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing);

        PowerManager mgr = (PowerManager)this.getSystemService(Context.POWER_SERVICE);
        wakeLock = mgr.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"MyWakeLock12");
        wakeLock.acquire();
        Process su = null;
//
//        try {
//            su = Runtime.getRuntime().exec("su");
//            su.getOutputStream().write("input swipe 250 300 -800 300\n".getBytes());
//            su.getOutputStream().write("exit\n".getBytes());
//            su.waitFor();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (su != null) {
//                su.destroy();
//            }
//        }

        warriors = this.getResources().getStringArray(R.array.Social);
        listView = (ListView) findViewById(R.id.drawerList);
        myAdapter = new MyAdapter(this);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 selectedItem(position);
               }
        });
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerListenter = new ActionBarDrawerToggle(this,drawerLayout , R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Toast toast = Toast.makeText(getApplicationContext(), "drawer opened", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER, 120, 120);
                toast.show();

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle("Contact Designer");
                Toast toast = Toast.makeText(getApplicationContext(),"drawer closed ", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER, 120, 120);
                toast.show();

            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);

            }};
        drawerLayout.setDrawerListener(drawerListenter);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //transition button ->
        transitToPlatform = (ImageButton) findViewById(R.id.imageButton);
        Animation in = AnimationUtils.loadAnimation(this,R.anim.sequential);
        transitToPlatform.setAnimation(in);

        transitToPlatform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.SlideOutRight)
                        .duration(900)
                        .withListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                startPlatformActivity();
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        })
                        .playOn(transitToPlatform);


            }
        });


    }

    private void startPlatformActivity(){
        Intent platformIntent = new Intent(this,Landing.class);
        platformIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(platformIntent);
        overridePendingTransition(R.anim.viewtransition, R.anim.slidedown);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerListenter.syncState();
    }


    public void selectedItem(int position){
    listView.setItemChecked(position, true);
    setTitlee(warriors[position], position);
}
    public void setTitlee(String title, final int pos){
        getSupportActionBar().setTitle(title);
        Snackbar snackbar = Snackbar
                .make(listView, warriors[pos], Snackbar.LENGTH_LONG)
                .setAction("Go for it", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (pos){
                            case 0: Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/amon2softcontrol"));
                                startActivity(browserIntent);
                                break;

                            case 1: showInputDialog();
                                break;

                            case 2: String uri = "http://line.me/ti/p/tjyIlQTJ3";
                                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                startActivity(intent1);
                                break;

                            case 3: try {
                                String uri1 = "facebook://facebook.com/messages/muqtado";
                                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(uri1));
                                startActivity(intent2);
                            } catch (Exception e) {
                                String uri2 = "https://www.facebook.com/muqtado";
                                Intent intent3 = new Intent(Intent.ACTION_VIEW, Uri.parse(uri2));
                                startActivity(intent3);
                            }
                                break;

                            case 4:
                                String urlStr = "https://www.youtube.com/user/almightyamon/videos";
                                Intent intent4 = new Intent(Intent.ACTION_VIEW);
                                intent4.setData(Uri.parse(urlStr));
                                startActivity(intent4);
                                break;

                            case 5:     Intent intent5 = new Intent(Intent.ACTION_DIAL);
                                intent5.setData(Uri.parse("tel:0922828604"));
                                startActivity(intent5);
                                break;

                            default:  break;
                        }
                    }
                });

        snackbar.show();

    }

    private List<Slide> generateSlides() {
        List<Slide> result = new ArrayList<>();

        result.add(new Slide().withTitle("Destructive Message").withDescription("Send auto destructive documents to your friends. Specify the time limit\r\n\t\t and document will burn itself!").
                withColorResource(R.color.accessibility_focus_highlight).withImage(R.drawable.destructive));
        result.add(new Slide().withTitle("Monitor Your Device").withDescription("I'd never want anyone to plugin some remmovable device and take my digital innovations\r\nRemote control your pc and get updated through notification")
                .withColorResource(R.color.suggestion_highlight_text).withImage(R.drawable.detective));
        result.add(new Slide().withTitle("Controller").withDescription("Remote control your robot with the sensor of your android device. Just Fancy !!")
                .withColorResource(R.color.user_icon_7).withImage(R.drawable.sensors));
        result.add(new Slide().withTitle("Mobile Programming").withDescription("Under the guidance of our respected professor Lalita, all the apps made during the course are included too.")
                .withColorResource(R.color.material_yellow).withImage(R.drawable.mobileprogramming));

        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

//        MenuItem item = menu.findItem(R.id.action_search1);
//        searchView.setMenuItem(item);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerListenter.onConfigurationChanged(newConfig);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerListenter.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    class MyAdapter extends BaseAdapter{
    private Context context;
        String[] socialSites;
        int[] images= {R.drawable.showimage,R.drawable.mail,R.drawable.line, R.drawable.fb,R.drawable.ut,R.drawable.phone1};

        public MyAdapter(Context context){
            this.context = context;
            socialSites = context.getResources().getStringArray(R.array.Social);
        }

        @Override
        public int getCount() {
            return socialSites.length;
        }

        @Override
        public Object getItem(int position) {
            return socialSites[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = null;
            if (convertView == null) {
                LayoutInflater inflator =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflator.inflate(R.layout.custome,parent,false);  //convert to java object

            }else{
                row = convertView;
            }
            ImageView titleImageView = (ImageView)row.findViewById(R.id.imageView1);
            titleImageView.setImageResource(images[position]);
            return row;
        }
    }

    protected void showInputDialog() {
        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);
       final EditText editText = (EditText) promptView.findViewById(R.id.edittext1);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        emaiText = editText.getText();
                        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                        emailIntent.setType("message/rfc822");
                        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"amon@geetaanjali.com"});
                        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Jai Radhekrishna! We like it");
                        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, emaiText);
                        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @Override
    public void onBackPressed() {
//        if (searchView.isSearchOpen()) {
//            searchView.closeSearch();
//        } else {
//            super.onBackPressed();
//        }
        Toast toast = Toast.makeText(getApplicationContext(), "No more back", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER, 120, 120);
        toast.show();
        overridePendingTransition(R.anim.platform_botton_anim_in, R.anim.slidedown);
    }

    private boolean isFirstTime()
    {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
    }

}



//MaterialRefreshLayout materialRefreshLayout = (MaterialRefreshLayout) findViewById(R.id.home);
//        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
//            @Override
//            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
//                materialRefreshLayout.setWaveColor(0xf90fffff);
//                materialRefreshLayout.setIsOverLay(true);
//                materialRefreshLayout.setWaveShow(true);
//            }
//
//            @Override
//            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
//                //load more refreshing...
//            }
//        });
////
////        materialRefreshLayout.setWaveColor(0xf90fffff);
////        materialRefreshLayout.setIsOverLay(true);
////        materialRefreshLayout.setWaveShow(true);

//        landing_destructive1.setOnLongClickListener(new View.OnLongClickListener() {
//                                                      public boolean onLongClick(View v) {
////                                                          ToolTip toolTip = new ToolTip.Builder()
////                                                                  .withText("Simple Tool Tip!")
////                                                                  .build();
////                                                          ToolTipView toolTipView = new ToolTipView.Builder(this)
////                                                                  .withAnchor(v)
////                                                                  .withToolTip(toolTip)
////                                                                  .build();
////                                                          toolTipView.show();
//                                                            return true;
//                                                        }
//                                                    });