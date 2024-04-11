package com.example.notesapp.view.activity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.model.Note;
import com.example.notesapp.adapter.NoteAdapter;
import com.example.notesapp.R;
import com.example.notesapp.Utils.Utility;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.Query;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton addNoteBtn;
    RecyclerView recyclerView;
    ImageButton menuBtn, sortMenuBtn;
    NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNoteBtn = findViewById(R.id.add_note_btn);
        recyclerView = findViewById(R.id.recyler_view);
        menuBtn = findViewById(R.id.menu_btn);
        sortMenuBtn = findViewById(R.id.sort_btn);

        addNoteBtn.setOnClickListener((v) -> startActivity(new Intent(MainActivity.this, NoteDetailsActivity.class)));
        menuBtn.setOnClickListener((v) -> showMenu());
        sortMenuBtn.setOnClickListener((v) -> showSortMenu());
        setupRecyclerView();
        notificationApp();
    }

    void showMenu() {
        //TODO Display menu
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, menuBtn);
        popupMenu.getMenu().add("Note List");
        popupMenu.getMenu().add("Schedule");
        popupMenu.getMenu().add("ToDo List");
        popupMenu.getMenu().add("Profile");
        popupMenu.getMenu().add("Logout");
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getTitle() == "Logout") {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                    return true;
                }
                if (item.getTitle() == "Note List") {
                    startActivity(new Intent(MainActivity.this, SplashActivity.class));
                    finish();
                    return true;
                }
                if (item.getTitle() == "ToDo List") {
                    startActivity(new Intent(MainActivity.this, SplashToDoListActivity.class));
                    finish();
                    return true;
                }
                if (item.getTitle() == "Profile") {
                    startActivity(new Intent(MainActivity.this, SplashProfileActivity.class));
                    return true;
                }
                return false;
            }
        });
    }

    void setupRecyclerView() {
        Query query = Utility.getCollectionReferenceForNotes().orderBy("timestamp", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>()
                .setQuery(query, Note.class).build();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(options, this);
        recyclerView.setAdapter(noteAdapter);
    }

    void showSortMenu() {
        //TODO Display menu
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, sortMenuBtn);
        popupMenu.getMenu().add("Sort A to Z");
        popupMenu.getMenu().add("Sort Z to A");
        popupMenu.getMenu().add("Sort date ascending");
        popupMenu.getMenu().add("Sort date descending");
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getTitle() == "Sort A to Z") {
//                    Query query = Utility.getCollectionReferenceForNotes().orderBy("timestamp", Query.Direction.DESCENDING);
//                    FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>()
//                            .setQuery(query, Note.class).build();
//                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                    noteAdapter = new NoteAdapter(options, MainActivity.this);
//                    recyclerView.setAdapter(noteAdapter);
                }
                if (item.getTitle() == "Sort Z to A") {
//                    Query query = Utility.getCollectionReferenceForNotes().orderBy("title", Query.Direction.DESCENDING);
//                    FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>()
//                            .setQuery(query, Note.class).build();
//                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                    noteAdapter = new NoteAdapter(options, MainActivity.this);
//                    recyclerView.setAdapter(noteAdapter);
//                    return true;
                }
                if (item.getTitle() == "Sort date ascending") {
//                    Query query = Utility.getCollectionReferenceForNotes().orderBy("timestamp", Query.Direction.ASCENDING);
//                    FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>()
//                            .setQuery(query, Note.class).build();
//                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                    noteAdapter = new NoteAdapter(options, MainActivity.this);
//                    recyclerView.setAdapter(noteAdapter);
//                    return true;
                }
                if (item.getTitle() == "Sort date descending") {
//                    Query query = Utility.getCollectionReferenceForNotes().orderBy("timestamp", Query.Direction.DESCENDING);
//                    FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>()
//                            .setQuery(query, Note.class).build();
//                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                    noteAdapter = new NoteAdapter(options, MainActivity.this);
//                    recyclerView.setAdapter(noteAdapter);
//                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        noteAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        noteAdapter.stopListening();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onRestart() {
        super.onRestart();
        noteAdapter.notifyDataSetChanged();
    }
    public void notificationApp(){
        Context context = getApplicationContext();

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channel_id")
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_android_black_24dp))
                .setContentTitle("Let save note !")
                .setContentText("Please save some notes!")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Please save some notes!! Schedule your day!"))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        Notification notification = builder.build();

        // Hiển thị thông báo
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Let save note ";
            String description = "Channel for today notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channel_id", name, importance);
            channel.setDescription(description);

            // Đăng ký channel với hệ thống
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}