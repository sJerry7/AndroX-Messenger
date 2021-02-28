package com.example.androxmessenger.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.androxmessenger.Fragments.CallsFragments;
import com.example.androxmessenger.Fragments.ChatsFragment;
import com.example.androxmessenger.Fragments.StatusFragments;

public class FragmentAdapter extends FragmentPagerAdapter {

    public FragmentAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                return new ChatsFragment();
            case 1:
                return new StatusFragments();
            case 2:
                return new CallsFragments();
            default:
                return new ChatsFragment();

        }
    }

    @Override
    public int getCount() {

        return 3;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        if(position==0){
            title = "CHATS";
        }
        if(position==1){
            title = "STATUS";
        }
        if(position==2){
            title = "Calls";
        }
        return title;
    }
}
