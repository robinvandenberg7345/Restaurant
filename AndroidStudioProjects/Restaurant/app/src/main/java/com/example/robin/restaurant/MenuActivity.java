package com.example.robin.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {

    ListView itemsView;
    String retrievedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // retrieve category that is clicked on
        Intent intent = getIntent();
        retrievedCategory = intent.getStringExtra("clicked_category");

        // set listeners on items in list
        itemsView = findViewById(R.id.itemsList);
        itemsView.setOnItemClickListener(new ListViewItemClickListener());

        // initiate download of items on the meny
        MenuRequest menuRequest = new MenuRequest(this);
        menuRequest.getMenu(this);


    }

    // handle the download of the menu
    @Override
    public void gotMenu(ArrayList<MenuItem> items) {
        ArrayList<MenuItem> categoryItems = new ArrayList<MenuItem>();

        // add items to view if from right category
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getCategory().equals(retrievedCategory)) {
                categoryItems.add(items.get(i));
            }
        }

        com.example.robin.restaurant.MenuAdapter adapter = new com.example.robin.restaurant.MenuAdapter(this, R.layout.menu_item, categoryItems);
        itemsView.setAdapter(adapter);
    }

    @Override
    public void gotMenuError(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    // implement listener for clicks on items
    private class ListViewItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            // retrieve item that was clicked
            MenuItem item = (MenuItem) adapterView.getItemAtPosition(position);
            Log.e("item", item + " was clicked");

            // pass on item to item activity to display a item detailed
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            intent.putExtra("clicked_item", item);
            startActivity(intent);
        }
    }
}
