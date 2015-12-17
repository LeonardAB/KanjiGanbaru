package com.diptamahardhika.kanjiganbaru;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DictSearch extends AppCompatActivity {
DatabaseAdapter dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dict_search);
        TextView lexeme = (TextView) findViewById(R.id.lexeme);
        String searchTerm = getIntent().getExtras().getString("searchTerm");
        lexeme.setText(searchTerm);
        dbHelper = new DatabaseAdapter(this) ;
//        long id = dbHelper.insertData(searchTerm);
//        if (id<0){
//Message.message (this,"unsuccesful");
//        }
//        else {
//            Message.message (this,"succesfully insert "+searchTerm);
//        }
        //String data = dbHelper.getAllData();
        String data = dbHelper.getData(searchTerm);
        Message.message(this,data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dict_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
