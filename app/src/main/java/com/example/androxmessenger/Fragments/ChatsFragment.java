package com.example.androxmessenger.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androxmessenger.Adapters.UsersAdapter;
import com.example.androxmessenger.Models.Users;
import com.example.androxmessenger.databinding.FragmentChatsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration;

import java.util.ArrayList;


public class ChatsFragment extends Fragment {


    public ChatsFragment() {
        // Required empty public constructor
    }

    FragmentChatsBinding binding;
    ArrayList<Users> list = new ArrayList<>();
    FirebaseDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChatsBinding.inflate(inflater, container, false);
        db = FirebaseDatabase.getInstance();
        UsersAdapter adapter = new UsersAdapter(list, getContext());        // We create the User's adapter here
        binding.chatRecyclerView.setAdapter(adapter);               // and set our chatRecyclerView using this adapter

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());      // It will manage all the Linear Layouts used in our system

        binding.chatRecyclerView.setLayoutManager(layoutManager);
//        binding.chatRecyclerView.addItemDecoration( new LayoutMarginDecoration( 1, 8 ) );

        db.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){       // It takes the data from the database
                    Users users = dataSnapshot.getValue(Users.class);           // And creates a User Entity
                    users.setUserId(dataSnapshot.getKey());             //   Set its ID which is given by firebase by default
                    if(!users.getUserId().equals(FirebaseAuth.getInstance().getUid()))
                    list.add(users);                            // Add it in a list of type Users
                }                                               // NOw go to UsersAdapter
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return binding.getRoot();
    }
}
