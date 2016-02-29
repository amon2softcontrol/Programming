package com.egci392.id0201;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class main extends Activity{

    ImageButton add;
    ListView listView;
    MyAdapter myAdapter;
    ArrayList<String> routes;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        routes = new ArrayList<>();

        add = (ImageButton) findViewById(R.id.addRoute);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),newRoute.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        try{
             db = SQLiteDatabase.openDatabase("//data/data/com.egci392.id0201/databases/Quiz.db",null,SQLiteDatabase.OPEN_READWRITE);
                //this path differs according to the package name
                getAllRoutes();
        }catch (Exception e){
            makeToast("Error opening database for readWrite");
            Utility.isDatabaseCreated = false;
        }

        listView = (ListView) findViewById(R.id.listView);
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView.setItemChecked(position, true);
                Intent mapactivity = new Intent(getApplicationContext(), route_view.class);
                mapactivity.putExtra("sas_route", routes.get(position));
                mapactivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mapactivity);
            }
        });

   }

    public void onBackPressed() {
        makeToast("There's no going back");
    }

    private void makeToast(String txt){
        Toast.makeText(this,txt,Toast.LENGTH_SHORT).show();
    }


 class MyAdapter extends BaseAdapter {

        private Context context;
        public MyAdapter(){
            this.context = getApplicationContext();
        }

        @Override
        public int getCount() {
            return routes.size();
        }

        @Override
        public Object getItem(int position) {
            return routes.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = null;
            try{
                if (convertView == null){
                    LayoutInflater inflator =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    row = inflator.inflate(R.layout.list,parent,false);  //convert to java object
                }else{
                    row = convertView;
                }
                TextView usernameName = (TextView)row.findViewById(R.id.routeList);
                usernameName.setText(routes.get(position));
            }catch (Exception e){
                makeToast("Error1");
            }
            return row;
        }
    }

 private void getAllRoutes(){
        try {
            Cursor cur = db.rawQuery("SELECT * FROM Router",null);
            if(cur.moveToFirst()){
                while (!cur.isAfterLast()) {
                    String name = cur.getString(cur.getColumnIndex("route"));
                    routes.add(name);
                    cur.moveToNext();
                } cur.close();
            }else makeToast("query result in null12");
        }catch (Exception e){
            makeToast("query result in null");
        }
    }
}

