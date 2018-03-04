package com.example.joker.blogpost;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.joker.blogpost.Adapters.RecyclerAdapter;
import com.example.joker.blogpost.SQLHelper.CRUDHelper;
import com.example.joker.blogpost.SQLHelper.SQLiteHelperClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String ANONYMOUS = "ANONYMOUS";
    private SQLiteHelperClass dbHelper;
    private SQLiteDatabase database;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private FloatingActionButton addBtn;

    private String userName = ANONYMOUS;;

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        if(user == null ){
            userName = ANONYMOUS;
            startActivity(new Intent(MainActivity.this, LogInActivity.class));
            finish();
        }else{
            userName = user.getDisplayName();
        }


        recyclerView = findViewById(R.id.postRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        addBtn = findViewById(R.id.addBtn);

        //get instance to database.
        dbHelper = new SQLiteHelperClass(this);
        database = dbHelper.getWritableDatabase();

        adapter = new RecyclerAdapter(null, getApplicationContext());
        setDataToAdapter();
        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT){

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                long id = (long) viewHolder.itemView.getTag();

                deleteFriend(id);

                adapter.swipeCursor(getAllPost());
            }
        }).attachToRecyclerView(recyclerView);

        
    }

    private void deleteFriend(long id) {

        CRUDHelper.deletePost(id,database);

    }

    private void setDataToAdapter() {
        Cursor cursor = getAllPost();
        adapter.swipeCursor(cursor);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setDataToAdapter();
    }

    public void addNewPost(View view) {

        Intent intent = new Intent(this,PostActivity.class);
        intent.putExtra("name",userName);
        startActivity(intent);

    }

    public Cursor getAllPost() {
        return CRUDHelper.getAllPost(database);
    }
}
