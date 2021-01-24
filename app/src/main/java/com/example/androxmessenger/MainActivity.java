package com.example.androxmessenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.androxmessenger.Adapters.FragmentAdapter;
import com.example.androxmessenger.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
       ActivityMainBinding binding1;

       FirebaseAuth mauth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding1 = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding1.getRoot());
        //getSupportActionBar().hide();

        mauth = FirebaseAuth.getInstance();
        binding1.viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        binding1.tabLayout.setupWithViewPager(binding1.viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.settings:
                Toast.makeText(this, "Coming Soon...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                mauth.signOut();
                Toast.makeText(this, "Log Out Successful", Toast.LENGTH_SHORT).show();
                Intent i1 = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i1);
                break;
        }
        return true;
    }
}