package com.example.admin.skype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        StoryDatabase db = new StoryDatabase(Main3Activity.this);
        db.createDefaultStorysIfNeed();
        List<Story> list = db.getAllStorys();
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new CustomListAdapter(this, list));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Story story = (Story) o;
                Bundle sendBundle = new Bundle();
                sendBundle.putString("name", story.getName());
                Intent i = new Intent(Main3Activity.this, Main4Activity.class);
                i.putExtras(sendBundle);
                startActivity(i);
                finish();
            }
        });
    }
}
