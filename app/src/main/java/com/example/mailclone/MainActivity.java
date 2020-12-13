package com.example.mailclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import com.example.mailclone.adapters.Email;
import com.example.mailclone.adapters.EmailLayoutAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<Email> items, fakeitems;
    ListView listView;
    EmailLayoutAdapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_item);
        registerForContextMenu(listView);

        items = new ArrayList<Email>();
        fakeitems = new ArrayList<Email>();
        for (int i = 0; i < 10; i++) {
            items.add(new Email("Peter " + i, "Hello", "Lakad Matatag Normalin Normalin", "12:00", "P", false));
        }
        for (int i = 10; i < 20; i++) {
            items.add(new Email("Sarah " + i, "Bye", "Lakad Matatag Normalin Normalin", "12:00", "S", false));
        }
        adapter1 = new EmailLayoutAdapter(items, this);
        fakeitems = items;
        listView.setAdapter(adapter1);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            ListView listView = (ListView) v;
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
            Email obj = (Email) listView.getItemAtPosition(acmi.position);
            menu.setHeaderTitle("Select action" + obj);
            menu.add(0, 1, 0, "Delete");
            menu.add(0, 2, 0, "Reply");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == 1) {
            Log.v("TAG", "CALL action");
        } else if (id == 2) {
            Log.v("TAG", "SMS action");
        }
        return super.onContextItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter1.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            Log.v("TAG", "SEARCH action");
        } else if (id == R.id.action_share) {
            Log.v("TAG", "SHARE action");
        } else if (id == R.id.action_download) {
            Log.v("TAG", "DOWNLOAD action");
        } else if (id == R.id.action_settings) {
            Log.v("TAG", "SETTINGS action");
        } else if (id == R.id.action_about) {
            Log.v("TAG", "ABOUT action");
        } else if (id == R.id.action_favor) {
            adapter1.getFavor().filter("0");
        }

        return super.onOptionsItemSelected(item);
    }
}