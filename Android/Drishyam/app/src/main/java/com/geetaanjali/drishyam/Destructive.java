package com.geetaanjali.drishyam;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.nineoldandroids.animation.Animator;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.pnikosis.materialishprogress.ProgressWheel;

import org.apache.http.Header;


public class Destructive extends Activity implements View.OnClickListener{
    //Variable declaration - buttons, webview , etc..
    Landing client = new Landing();
    private static final int tagCount = 3;
    private static final String tag1="guruji";
    private static final String tag2="remote";
    private static final String tag3="sensor";
    private static final String tag4="sms";

    final String URLofScript = "https://script.google.com/macros/s/AKfycbzeSMemNvMFz_IKIaimhM4O_1zV0DUNvnlop_PuEBmBmAWMrAI/exec";
    TextView header;
    TextView inputLabel;
    EditText inputText;
    WebView info;
    int item;
    float progress;
    ProgressWheel progressBar;
    Button submitButton;
    String ajaxtext, ajaxemail, ajaxname, ajaxtime, ajaxMessage;
    Button e, mahadev_name, mahadev_email, mahadev_text, mahadev_time;
    ClipboardManager clipboard;
    ClipData clip;
    // initialization completed!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.destructiveswitch);
        //setting progress to be 0
        progress = (float) 0;

        //setting params to blank
        ajaxtext=" ";
        ajaxname=" ";
        ajaxtime=" ";
        ajaxemail=" ";


        //using clipboard for the ease of presenting in the class
         clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        clip = ClipData.newPlainText("label", "Jai Radhekrishna");
        clipboard.setPrimaryClip(clip);
        ImageView icon = new ImageView(this); // Create an icon
        icon.setImageResource(R.drawable.k1);
        FloatingActionButton actionButton = new FloatingActionButton.Builder(this).setContentView(icon).build();
        //getTodo();
        ImageView iconSortName = new ImageView(this);
        iconSortName.setImageResource(R.drawable.computer1);
        ImageView iconSortName1 = new ImageView(this);
        iconSortName1.setImageResource(R.drawable.sensor1);
        ImageView iconSortName12 = new ImageView(this);
        iconSortName12.setImageResource(R.drawable.amon);
        ImageView iconSortName13 = new ImageView(this);
        iconSortName13.setImageResource(R.drawable.mobileprogramming1);

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        SubActionButton button1 = itemBuilder.setContentView(iconSortName).build();
        SubActionButton button2 = itemBuilder.setContentView(iconSortName1).build();
        SubActionButton button3 = itemBuilder.setContentView(iconSortName12).build();
        SubActionButton button4 = itemBuilder.setContentView(iconSortName13).build();


        button1.setTag(tag2);
        button2.setTag(tag3);
        button3.setTag(tag1);
        button4.setTag(tag4);


        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .addSubActionView(button4)
                .attachTo(actionButton)
                .build();

        new Thread(new Task()).start();
// new DownloadImageTask().execute("http://example.com/image.png");

        info = (WebView) findViewById(R.id.webView2);
        info.getSettings().setJavaScriptEnabled(true);
        info.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        info.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                //Make the bar disappear after URL is loaded, and changes string to Loading...
                setTitle("Loading...");
                setProgress(progress * 100); //Make the bar disappear after URL is loaded

                // Return the app name after finish loading
                if (progress == 100)
                    setTitle(R.string.app_name);
            }
        });
        info.loadUrl("file:///android_asset/www/info1.html");

       header = (TextView) findViewById(R.id.textView2);
       header.startAnimation(AnimationUtils.loadAnimation(this, R.anim.move));

        submitButton = (Button) findViewById(R.id.btnMorph);

        submitButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                YoYo.with(Techniques.SlideOutRight)
                        .duration(1200)
                        .withListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                WebRequest.callService(new AsyncHttpResponseHandler() {

                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                        Toast toast = Toast.makeText(getApplicationContext(), "Jai Radhekrihna!!\r\n  Submitted :)", Toast.LENGTH_LONG);
                                        toast.setGravity(Gravity.TOP | Gravity.CENTER, 120, 120);
                                        toast.show();
                                    }

                                    @Override
                                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                    }
                                }, ajaxname, ajaxMessage,URLofScript);

                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        })
                        .playOn(submitButton);


            }
        });
        submitButton.setVisibility(View.INVISIBLE);
    }




    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && info.canGoBack()) {
            info.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    class Task implements Runnable {

        @Override

        public void run() {

            progressBar = (ProgressWheel) findViewById(R.id.linear);
//            progressBar.setCallback();
            try {

                   progressBar.setProgress((float) 1.0);
//

            } catch (Exception e) {
            }

        }

    }

    public void editName(View v) {
        e = (Button) findViewById(R.id.btnMorph);
        mahadev_name = (Button) findViewById(R.id.mahadev_name);
        mahadev_name.setText("Set Name");
        mahadev_name.setBackgroundColor(Color.parseColor("#e69310"));

        YoYo.with(Techniques.Flash)
                .duration(700)
                .playOn(e);
        YoYo.with(Techniques.Pulse)
                .duration(700)
                .playOn(mahadev_name);

        item = 0;
        String name = "MACAVITY";
        showInputDialog(name);
    }
    public void editText(View v) {
        e = (Button) findViewById(R.id.btnMorph);
        mahadev_text = (Button) findViewById(R.id.mahadev_text);
        mahadev_text.setText("Text");
        mahadev_text.setBackgroundColor(Color.parseColor("#80bebebe"));

        YoYo.with(Techniques.RubberBand)
                .duration(700)
                .playOn(e);
        YoYo.with(Techniques.Shake)
                .duration(700)
                .playOn(mahadev_text);

        item = 1;

        String text = "Macavity's a Mystery Cat : she's called the Hidden Paw—\n" +
                "For she's the master criminal who can defy the Law.\n" +
                "She's the bafflement of Scotland Yard, the Flying Squad's despair:\n" +
                "For when they reach the scene of crime—Macavity's not there!\n" +
                "\n" +
                "Macavity, Macavity, there's no one like Macavity,\n" +
                "She's broken every human law, she breaks the law of gravity.\n" +
                "Her powers of levitation would make a fakir stare,\n" +
                "And when you reach the scene of crime—Macavity's not there!\n" +
                "You may seek her in the basement, you may look up in the air—\n" +
                "But I tell you once and once again, Macavity's not there!";

        text += "Macavity's a ginger cat, she's very tall and thin;\n" +
                "You would know her if you saw her, for her eyes are sunken in.\n" +
                "Her brow is deeply lined with thought, her head is highly domed;\n" +
                "Her coat is dusty from neglect, her whiskers are uncombed.\n" +
                "She sways her head from side to side, with movements like a snake;\n" +
                "And when you think she's half asleep, she's always wide awake.\n" +
                "\n" +
                "Macavity, Macavity, there's no one like Macavity,\n" +
                "For she's a fiend in feline shape, a monster of depravity.\n" +
                "You may meet her in a by-street, you may see her in the square—\n" +
                "But when a crime's discovered, then Macavity's not there!\n" +
                "\n" +
                "She's outwardly respectable. (They say she cheats at cards.)\n" +
                "And her footprints are not found in any file of Scotland Yard's\n" +
                "And when the larder's looted, or the jewel-case is rifled,\n" +
                "Or when the milk is missing, or another Peke's been stifled,\n" +
                "Or the greenhouse glass is broken, and the trellis past repair\n" +
                "Ay, there's the wonder of the thing! Macavity's not there!\n" +
                "\n" +
                "And when the Foreign Office find a Treaty's gone astray,\n" +
                "Or the Admiralty lose some plans and drawings by the way,\n" +
                "There may be a scrap of paper in the hall or on the stair—\n" +
                "But it's useless to investigate—Macavity's not there!\n" +
                "And when the loss has been disclosed, the Secret Service say:\n" +
                "It must have been Macavity!'—but she's a mile away.\n" +
                "You'll be sure to find her resting, or a-licking of her thumb;\n" +
                "Or engaged in doing complicated long division sums.\n" +
                "\n" +
                "Macavity, Macavity, there's no one like Macavity,\n" +
                "There never was a Cat of such deceitfulness and suavity.\n" +
                "She always has an alibi, and one or two to spare:\n" +
                "At whatever time the deed took place—MACAVITY WASN'T THERE !\n" +
                "And they say that all the Cats whose wicked deeds are widely known\n" +
                "(I might mention Mungojerrie, I might mention Griddlebone)\n" +
                "Are nothing more than agents for the Cat who all the time\n" +
                "Just controls their operations: the Napoleon of Crime!";

        showInputDialog(text);
    }
    public void editEmails(View v) {
        e = (Button) findViewById(R.id.btnMorph);
        mahadev_email = (Button) findViewById(R.id.mahadev_email);

        mahadev_email.setText("Email(s)");
        mahadev_email.setBackgroundColor(Color.parseColor("#e69310"));

        YoYo.with(Techniques.Swing)
                .duration(700)
                .playOn(e);
        YoYo.with(Techniques.Wobble)
                .duration(700)
                .playOn(mahadev_email);

        item = 2;

        String email = "muqtado3g@gmail.com,amon@geetaanjali.com";
        showInputDialog(email);

//        Toast toast = Toast.makeText(getApplicationContext(), input[2], Toast.LENGTH_LONG);
//        toast.setGravity(Gravity.TOP | Gravity.CENTER, 120, 120);
//        toast.show();
    }
    public void editTime(View v) {
       e = (Button) findViewById(R.id.btnMorph);
       mahadev_time = (Button) findViewById(R.id.mahadev_time);

        mahadev_time.setText("Time Limit");
        mahadev_time.setBackgroundColor(Color.parseColor("#80bebebe"));

        YoYo.with(Techniques.Bounce)
                .duration(700)
                .playOn(e);
        YoYo.with(Techniques.Tada)
                .duration(700)
                .playOn(mahadev_time);

        item = 3;

        String time = "12";
        showInputDialog(time);

//        Toast toast = Toast.makeText(getApplicationContext(), input[3], Toast.LENGTH_LONG);
//        toast.setGravity(Gravity.TOP | Gravity.CENTER, 120, 120);
//        toast.show();
    }

    @Override
    public void onClick(View v) {
    if (v.getTag().equals(tag1)){
        Intent homeIntent = new Intent(getApplicationContext(),MainActivity.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
        }
            if (v.getTag().equals(tag2)){
                Intent homeIntent = new Intent(getApplicationContext(),Remote.class);
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

    protected void showInputDialog(String text) {

        progressBar.setBarColor(Color.BLUE);
        progressBar.spin();
        LayoutInflater layoutInflater = LayoutInflater.from(Destructive.this);
        View inputView = layoutInflater.inflate(R.layout.destructive_inputs, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Destructive.this,R.style.DialogSlideAnim);
        alertDialogBuilder.setView(inputView);
        inputLabel = (TextView) inputView.findViewById(R.id.textView3);
        inputText = (EditText) inputView.findViewById(R.id.destructive_input);


        switch (item){
            case 0: inputLabel.setText("NAME");
                clip = ClipData.newPlainText("label", text);
                clipboard.setPrimaryClip(clip);
                inputText.setHint("Enter Your Name ");
                if(ajaxname.equals(" ")) progress += (float) 0.25;
                    else  inputText.setText(ajaxname);
                 break;
            case 1: inputLabel.setText("TEXT");
                clip = ClipData.newPlainText("label", text);
                clipboard.setPrimaryClip(clip);
                 inputText.setHint("Enter Your Text Messgae");
                if(ajaxtext.equals(" ")) progress += (float) 0.27;
                      else inputText.setText(ajaxtext);
                break;
            case 2: inputLabel.setText("EMAIL");
                clip = ClipData.newPlainText("label", text);
                clipboard.setPrimaryClip(clip);
                inputText.setHint("Enter recipients email");
                if(ajaxemail.equals(" ")) progress += (float) 0.27;
                    else  inputText.setText(ajaxemail);
                break;
            case 3: inputLabel.setText("TIME");
                clip = ClipData.newPlainText("label", text);
                clipboard.setPrimaryClip(clip);
                inputText.setHint("Enter Your Granted Time");
                if(ajaxtime.equals(" ")) progress += (float) 0.30;
                    else inputText.setText(ajaxtime);
                break;
            default: break;
        }

        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String inputRecieved = inputText.getText().toString();

                        boolean v1;
                        boolean v2;

                        switch (item) {
                            case 0:
                                v1 = Utility.isNotNull(inputRecieved);
                                if (v1) {
                                    ajaxname = inputRecieved;
                                } else {
                                   mahadev_name.setBackgroundResource(R.color.cpb_red);
                                   mahadev_name.setText("Can't be Empty!");

                                    YoYo.with(Techniques.Wobble)
                                            .duration(700)
                                            .playOn(mahadev_name);
                                    progress -= (float) 0.27;
                                }
                                break;
                            case 1:
                                v1 = Utility.isNotNull(inputRecieved);
                                if (v1) {
                                       ajaxtext = inputRecieved;
                                } else {

                                    mahadev_text.setBackgroundResource(R.color.cpb_red);
                                    mahadev_text.setText("Can't be Empty!");

                                    YoYo.with(Techniques.Wobble)
                                            .duration(700)
                                            .playOn(mahadev_text);
                                    progress -= (float) 0.27;
                                }
                                break;
                            case 2:
                                v1 = Utility.isNotNull(inputRecieved);
                                v2 = Utility.validate(inputRecieved);
                                if (v1 && v2) {
                                    ajaxemail = inputRecieved;
                                } else {
                                    mahadev_email.setBackgroundResource(R.color.cpb_red);
                                    mahadev_email.setText("Enter valid Email!");

                                    YoYo.with(Techniques.Wobble)
                                            .duration(700)
                                            .playOn(mahadev_email);
                                    progress -= (float) 0.27;
                                }
                                break;
                            case 3:
                                v1 = Utility.isNotNull(inputRecieved);
                                if (v1) {
                                    ajaxtime = inputRecieved;
                                } else {
                                    mahadev_time.setBackgroundResource(R.color.cpb_red);
                                    mahadev_time.setText("Can't be Empty!");

                                    YoYo.with(Techniques.Wobble)
                                            .duration(700)
                                            .playOn(mahadev_time);

                                    progress -= (float) 0.27;
                                }
                                break;
                            default:   break;

                        }
                        progressBar.stopSpinning();
                        progressBar.setBarColor(Color.BLACK);

                        progressBar.setProgress(progress);

                        if(progress>(float)1){
                            String[] s = ajaxtext.split(" ");
                            String ss = s[0]+" "+s[1]+" "+s[2]+" "+s[3];
                            String submitText;
                            submitText = "\t\t\tProceed by clicking\r\n\r\n\r\n\r\nName (from): "+ajaxname+"\r\n\r\nMessage: "+ss+" ...\r\n";
                            submitText += "\r\nEmail (TO): "+ajaxemail+"\r\n\r\nTime : "+ajaxtime;
                            submitButton.setText(submitText);

                            submitButton.setVisibility(View.VISIBLE);
                            YoYo.with(Techniques.StandUp)
                                    .duration(1200)
                                    .playOn(submitButton);
                            ajaxMessage = ajaxname+" "+ajaxemail+" "+ajaxtext+" "+ajaxtime;

                        }
                    }
                })
            .

    setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
        public void onClick (DialogInterface dialog,int id){
            progress -= (float) 0.27;
            dialog.cancel();
            progressBar.stopSpinning();
            progressBar.setBarColor(Color.BLACK);
            progressBar.setProgress(progress);
        }
    });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();

    }

//
    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.platform_botton_anim_in, R.anim.slidedown);
    }
}


//tionUtils.loadAnimation(MainActivity.this,android.R.anim.slide_out_right));
//        TextView txt = (TextView) findViewById(R.id.aa);
//        txt.setText("");
//        txt.startAnimation(Anima