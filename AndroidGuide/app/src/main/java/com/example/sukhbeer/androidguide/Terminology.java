package com.example.sukhbeer.androidguide;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;


public class Terminology extends ActionBarActivity {
    private static final String[] commands = new String[] {"pull","push","status","add","commit","init","clone"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminology);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,commands);
        final AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        textView.setAdapter(adapter);
        textView.setThreshold(1);
        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String entry = textView.getText().toString().trim();
                if (entry.equals("pull")) {
                    Toast.makeText(getApplicationContext(), R.string.pull, Toast.LENGTH_LONG).show();
                }
                if (entry.equals("push")) {
                    Toast.makeText(getApplicationContext(), R.string.push, Toast.LENGTH_LONG).show();
                }
                if (entry.equals("status")) {
                    Toast.makeText(getApplicationContext(), R.string.status, Toast.LENGTH_LONG).show();
                }
                if (entry.equals("add")) {
                    Toast.makeText(getApplicationContext(), R.string.add, Toast.LENGTH_LONG).show();
                }
                if (entry.equals("init")) {
                    Toast.makeText(getApplicationContext(), R.string.init, Toast.LENGTH_LONG).show();
                }
                if (entry.equals("clone")) {
                    Toast.makeText(getApplicationContext(), R.string.clone, Toast.LENGTH_LONG).show();
                }
                if (entry.equals("commit")) {
                    Toast.makeText(getApplicationContext(), R.string.commit, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_terminology, menu);
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
    public void about(MenuItem item) {
        Intent i = new Intent(this,about.class);
        startActivity(i);
    }

    public void helpMenu(MenuItem item) {
        Intent i = new Intent(this, help.class);
        startActivity(i);
    }
}
