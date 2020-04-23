package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> target;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        String[] strings = new String[]{"Karol I Habsburg", " z Bożej Łaski cesarz Austrii", " apostolski król Węgier", " król Czech", " Dalmacji", " Chorwacji", " Slawonii", " Galicji", " Lodomerii i Ilyrii", " król Jerozolimy etc etc. arcyksiążę Austrii", " wielki książę Toskanii i Krakowa", " książę Lotaryngii", " Salzburga", " Styrii", " Karyntii", " Krainy i Bukowiny", " wielki książę Siedmiogrodu", " margrabia Moraw", " książę Dolnego i Górnego Śląska", " Modeny", " Parmy", " Piacenzy", " Guastalli", " Oświęcimia i Zatoru", " Cieszyna", " Friaul", " Raguzy i Zary", " uksiążęcony hrabia Habsburga i Tyrolu", " Kyburga", " Goricy i Kradiski", " książę Trydentu i Brixen", " margrabia Łużyc Dolnych i Górnych oraz Istrii", " hrabia Hohenembs", " Feldkirch", " Bregenz", " Sonnenebergu", " pan Triestu", " Cattaro i Marchii Wendyjskiej", " wielki wojewoda województwa Serbii", " etc.", " etc."};
        this.target = new ArrayList<String>();
        this.target.addAll(Arrays.asList(strings));
        this.adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.target);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(this.adapter);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public void nowyWpis(MenuItem mi) {
        Intent intencja = new Intent(this, DodajWpis.class);
        startActivityForResult(intencja, 1);
    }

    @Override
    public void onActivityResult(int request_code, int resultCode, Intent data) {
        super.onActivityResult(request_code, resultCode, data);
        if (request_code == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            String nowy = (String) extras.get("wpis");
            target.add(nowy);
            adapter.notifyDataSetChanged();
        }
    }

}
