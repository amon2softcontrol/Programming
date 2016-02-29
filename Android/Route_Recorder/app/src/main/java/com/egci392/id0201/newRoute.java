package com.egci392.id0201;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class newRoute extends Activity{
    EditText routeName;
    Button start;
    ImageButton back;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newroute);
        //initialize layout inputs
        routeName = (EditText) findViewById(R.id.editText);
        start = (Button) findViewById(R.id.button2);
        back = (ImageButton) findViewById(R.id.back1);

        try{
            db = SQLiteDatabase.openDatabase("//data/data/com.egci392.id0201/databases/Quiz.db",null,SQLiteDatabase.OPEN_READWRITE);
        }catch (Exception e){
            makeToast("Error opening database for readWrite");
            Utility.isDatabaseCreated = false;
        }
        start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!Utility.isDatabaseCreated){
                    startNewRoute();
                }
                if(checkDatabase(routeName.getText().toString())){
                    startNewRoute();
                }else makeToast("Route Name is duplicate");
            }
        });

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                   Intent intent = new Intent(getApplicationContext(), main.class);
                    intent.putExtra("routeName",routeName.getText().toString());
                    startActivity(intent);
            }
        });
    }

    private void startNewRoute(){
        Intent intent = new Intent(getApplicationContext(), route_map.class);
        intent.putExtra("routeName",routeName.getText().toString());
        startActivity(intent);
    }

    private boolean checkDatabase(String route){
        try {
            String sql = "SELECT * FROM Router WHERE route=?";
            Cursor cur = db.rawQuery(sql, new String[] {route});
            cur.moveToFirst();
            Toast.makeText(getApplicationContext(), cur.getColumnName(1), Toast.LENGTH_SHORT);
            if(cur.getCount()== 0){
                cur.close();
                return true;
            }else   return false;
        } catch (Exception e) {
            makeToast("Error occured during sql insert"+e.toString());
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
   }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void makeToast(String text){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
            makeToast("Press back button at the topleft");
    }

}