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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> target;
    private SimpleCursorAdapter adapter;
    private MySQLite db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        String[] strings = new String[]{"Karol I Habsburg", " z Bożej Łaski cesarz Austrii", " apostolski król Węgier", " król Czech", " Dalmacji", " Chorwacji", " Slawonii", " Galicji", " Lodomerii i Ilyrii", " król Jerozolimy etc etc. arcyksiążę Austrii", " wielki książę Toskanii i Krakowa", " książę Lotaryngii", " Salzburga", " Styrii", " Karyntii", " Krainy i Bukowiny", " wielki książę Siedmiogrodu", " margrabia Moraw", " książę Dolnego i Górnego Śląska", " Modeny", " Parmy", " Piacenzy", " Guastalli", " Oświęcimia i Zatoru", " Cieszyna", " Friaul", " Raguzy i Zary", " uksiążęcony hrabia Habsburga i Tyrolu", " Kyburga", " Goricy i Kradiski", " książę Trydentu i Brixen", " margrabia Łużyc Dolnych i Górnych oraz Istrii", " hrabia Hohenembs", " Feldkirch", " Bregenz", " Sonnenebergu", " pan Triestu", " Cattaro i Marchii Wendyjskiej", " wielki wojewoda województwa Serbii", " etc.", " etc."};
        this.target = new ArrayList<String>();
        this.target.addAll(Arrays.asList(strings));
        db = new MySQLite(this);
        this.adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_2,
                db.lista(),
                new String[]{"_id", "gatunek"},
                new int[]{android.R.id.text1,
                        android.R.id.text2},

                SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE
        );

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(this.adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>
                                            adapter, View view, int pos, long id) {
                TextView name = (TextView) view.findViewById(android.R.id.text1);
                Animal zwierz = db.pobierz(Integer.parseInt(name.getText().toString()));
                Intent intencja = new Intent(getApplicationContext(), DodajWpis.class);
                intencja.putExtra("element", zwierz);
                startActivityForResult(intencja, 2);


            }
        });

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
            Animal nowy = (Animal) extras.getSerializable("nowy");
            this.db.dodaj(nowy);
            adapter.changeCursor(db.lista());
            adapter.notifyDataSetChanged();
        }
        if (request_code == 2 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Animal nowy = (Animal) extras.getSerializable("nowy");
            this.db.aktualizuj(nowy);
            adapter.changeCursor(db.lista());
            adapter.notifyDataSetChanged();
        }
    }

}
