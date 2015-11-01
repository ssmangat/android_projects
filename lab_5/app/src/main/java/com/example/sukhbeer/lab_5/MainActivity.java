package com.example.sukhbeer.lab_5;

import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static android.text.TextUtils.concat;
import static java.lang.Integer.parseInt;


public class MainActivity extends ActionBarActivity {
    private String[] colorNames;
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        colorNames = getResources().getStringArray(R.array.listArray);
        ListView lv = (ListView) findViewById(R.id.listView);
        ArrayAdapter aa = new ArrayAdapter(this, R.layout.layout, colorNames);
        lv.setAdapter(aa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                String[] colorvalues = getResources().getStringArray(R.array.listValues);
                value = "#".concat(colorvalues[position].substring(2));
                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rl);
                relativeLayout.setBackgroundColor(Color.parseColor(value));
                Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }
        });
        registerForContextMenu(lv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select The Action");
        menu.add(0, v.getId(), 0, "Write to SDCard");
        menu.add(0, v.getId(), 0, "Read from SDCard");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Write to SDCard") {
            Toast.makeText(getApplicationContext(), "writing ....", Toast.LENGTH_LONG).show();
            writeToFile();
        } else if (item.getTitle() == "Read from SDCard") {
            Toast.makeText(getApplicationContext(), "reading ....", Toast.LENGTH_LONG).show();
            readFromFile();
        } else {
            return false;
        }
        return true;
    }

    private void readFromFile() {
        if (isExternalStorageReadable()) {
            try {
                File myFile = new File("/sdcard/abc.txt");
                FileInputStream fIn = new FileInputStream(myFile);
                BufferedReader myReader = new BufferedReader(
                        new InputStreamReader(fIn));
                String aDataRow = "";
                String value = "";
                while ((aDataRow = myReader.readLine()) != null) {
                    value += aDataRow;
                }
                //value = "#".concat(value);
                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rl);
                relativeLayout.setBackgroundColor(Color.parseColor(value));
                myReader.close();
                Toast.makeText(getBaseContext(),
                        "Done reading SD 'abc.txt'",
                        Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(getBaseContext(), e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void writeToFile() { //http://stackoverflow.com/questions/20369782/write-file-to-sdcard-in-android
        if (isExternalStorageWritable()) {
            try {
                String filename = "abc.txt";
                File myFile = new File(Environment
                        .getExternalStorageDirectory(), filename);
                if (!myFile.exists())
                    myFile.createNewFile();
                FileOutputStream fos;
                byte[] data = value.getBytes();
                try {
                    fos = new FileOutputStream(myFile);
                    fos.write(data);
                    fos.flush();
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() { //http://developer.android.com/intl/ja/guide/topics/data/data-storage.html#filesExternal
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
