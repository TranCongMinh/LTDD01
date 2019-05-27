package com.example.admin.skype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        StoryDatabase db = new StoryDatabase(Main4Activity.this);
        Bundle receiveBundle = this.getIntent().getExtras();
        final String s = receiveBundle.getString("name");
        final TextView name = (TextView) findViewById(R.id.viewName);
        final TextView author = (TextView) findViewById(R.id.viewAuthor);
        final TextView content = (TextView) findViewById(R.id.viewContent);

        final Story story = db.getStory(s);
        name.setText(String.valueOf(story.getName()));
        author.setText(String.valueOf(story.getAuthor()));
        content.setText(String.valueOf(story.getContent()));

        Button btn = (Button)findViewById(R.id.back);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Main4Activity.this, Main3Activity.class);
                startActivity(i);
                finish();
            }
        });

        Button del = (Button)findViewById(R.id.delete);
        del.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                StoryDatabase db = new StoryDatabase(Main4Activity.this);
                db.deleteStory(db.getStory(story.getName()));
                Intent i = new Intent(Main4Activity.this, Main3Activity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
