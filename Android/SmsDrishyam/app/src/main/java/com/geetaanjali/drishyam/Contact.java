package com.geetaanjali.drishyam;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Contact extends AppCompatActivity {
    private ListView listView;
    private MyAdapter myAdapter;
    private String[] Social;
    private Editable emaiText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Contact Designer");

        Social = this.getResources().getStringArray(R.array.Social);

        listView = (ListView) findViewById(R.id.listView2);
        myAdapter = new MyAdapter(this, Social,new int[] {R.drawable.showimage,R.drawable.line, R.drawable.fb,R.drawable.ut,R.drawable.phone1,R.drawable.gmail});
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem(position);
            }
        });

    }

    private void selectedItem(final int pos){
        getSupportActionBar().setTitle(Social[pos]);
        Snackbar snackbar = Snackbar
                .make(listView, Social[pos], Snackbar.LENGTH_LONG)
                .setAction("Go for it", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (pos){
                            case 0: Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/amon2softcontrol"));
                                startActivity(browserIntent);
                                break;

                            case 5: showInputDialog();
                                break;

                            case 1: String uri = "http://line.me/ti/p/tjyIlQTJ3";
                                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                startActivity(intent1);
                                break;

                            case 2: try {
                                String uri1 = "facebook://facebook.com/messages/muqtado";
                                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(uri1));
                                startActivity(intent2);
                            } catch (Exception e) {
                                String uri2 = "https://www.facebook.com/muqtado";
                                Intent intent3 = new Intent(Intent.ACTION_VIEW, Uri.parse(uri2));
                                startActivity(intent3);
                            }
                                break;

                            case 3:
                                String urlStr = "https://www.youtube.com/user/almightyamon/videos";
                                Intent intent4 = new Intent(Intent.ACTION_VIEW);
                                intent4.setData(Uri.parse(urlStr));
                                startActivity(intent4);
                                break;

                            case 4:     Intent intent5 = new Intent(Intent.ACTION_DIAL);
                                intent5.setData(Uri.parse("tel:0922828604"));
                                startActivity(intent5);
                                break;

                            default:  break;
                        }
                    }
                });

        snackbar.show();
    }

    protected void showInputDialog() {
        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(Contact.this);
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Contact.this);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Toast toast = Toast.makeText(getApplicationContext(), "No going back", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER, 120, 120);
        toast.show();
        overridePendingTransition(R.anim.platform_botton_anim_in, R.anim.slidedown);
    }

}
