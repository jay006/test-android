package com.example.joker.blogpost;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.joker.blogpost.SQLHelper.CRUDHelper;
import com.example.joker.blogpost.SQLHelper.SQLiteHelperClass;

public class PostActivity extends AppCompatActivity {

    private static final String ANONYMOUS = "anonymous" ;
    private EditText editText;
    private Button postBtn;
    private SQLiteHelperClass dbHelper;
    private SQLiteDatabase database;

    String userName = ANONYMOUS;
    String title  = "null";
    String imageUri = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Intent intent = getIntent();
        userName = intent.getStringExtra("name");

        dbHelper = new SQLiteHelperClass(this);
        database = dbHelper.getWritableDatabase();

        postBtn = findViewById(R.id.postBtn);
        editText = findViewById(R.id.titleEditText);

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = editText.getText().toString().trim();

                long i = CRUDHelper.insertData(database,userName,title,imageUri);

                if(i == -1){
                    showTost("Error inserting data ");
                }else{
                    showTost("Data insertion done");
                    finish();
                }
            }
        });


    }

    private void showTost(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}
