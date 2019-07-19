package com.example.ghautham.fblaquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.model.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeaderboardActivity extends AppCompatActivity {

    ListView listView;
    Button btn;
    List<Leader> list;
    CustomAdapter customAdapter;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        db = FirebaseFirestore.getInstance();
        db.collection("users")
                .whereEqualTo("name", true)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

//        final DocumentReference docRef = db.collection("users").document("SF");
//        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot snapshot,
//                                @Nullable FirebaseFirestoreException e) {
//                if (e != null) {
//                    Log.w(TAG, "Listen failed.", e);
//                    return;
//                }
//
//                if (snapshot != null && snapshot.exists()) {
//                    Log.d(TAG, "Current data: " + snapshot.getData());
//                } else {
//                    Log.d(TAG, "Current data: null");
//                }
//            }
//        });

        db.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if (e != null) {
                   Log.d("TAG", "Listen failed: " + e);
                    return;
                }
                assert queryDocumentSnapshots != null;

                List<DocumentSnapshot> newList = queryDocumentSnapshots.getDocuments();
                list.clear();
                int count = 0;
                for(DocumentSnapshot ds: newList)
                {
                    Map<String, Object> d = ds.getData();
                    assert d != null;
                    list.add(new Leader((String) d.get("name"), (int) (long) d.get("score")));
                }
                Collections.sort(list);
                customAdapter.notifyDataSetChanged();
            }
        });

        listView = findViewById(R.id.id_leaders);
        btn = findViewById(R.id.id_btn);
        list = new ArrayList<>();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LeaderboardActivity.this, LoginActivity.class));
            }
        });

        customAdapter = new CustomAdapter(this, R.layout.custom_layout, list);
        listView.setAdapter(customAdapter);
    }
    public class CustomAdapter extends ArrayAdapter<Leader> {

        Context context;
        List<Leader> leaderList;
        int resource;

        public CustomAdapter(Context context, int resource, List<Leader> leaderList){
            super(context, resource, leaderList);

            this.context = context;
            this.resource = resource;
            this.leaderList = leaderList;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View adapterLayout = layoutInflater.inflate(resource,null);

            TextView leadername = adapterLayout.findViewById(R.id.id_leadername);
            TextView leaderscore = adapterLayout.findViewById(R.id.id_leaderscore);

            leadername.setText(leaderList.get(position).getName());
            leaderscore.setText(""+leaderList.get(position).getScore());

            return adapterLayout;
        }


    }
}
