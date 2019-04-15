package com.csci5115.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;
import android.widget.Toast;

import com.csci5115.activities.dummy.SongList;
import com.csci5115.activities.dummy.Song;

import java.util.ArrayList;
import java.util.List;

public class Pop_PlayListActivity extends AppCompatActivity
        implements SongListFragment.OnListFragmentInteractionListener,
        SongFragment.OnListFragmentInteractionListener,
        BlankFragment.OnClickListener,
        BlankFragment3.OnClickListener{

    private Fragment fragment = null;
    private Fragment fragment_button = null;
    private FragmentManager fm = getSupportFragmentManager();
    private FragmentTransaction tf = fm.beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_playlist);

        Intent intent = getIntent();

        TextView title = findViewById(R.id.playlistname);
        title.setText(intent.getStringExtra("playListName"));

        //Get the fragment manager for this activity (MainActivity)
        //FragmentTransaction tf = getSupportFragmentManager().beginTransaction();

        ArrayList<Song> song_list = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            song_list.add(new Song("" + i,"song" + i, "artist"+i, "4:00"));
        }

        fragment = new SongFragment();
        fragment_button = new BlankFragment3();


        Bundle args = new Bundle();
        args.putParcelableArrayList("songs", song_list);
        fragment.setArguments(args);

        tf.add(R.id.main_frag, fragment);
        tf.add(R.id.button_frag, fragment_button);

        tf.commit();
        /*ft.add(R.id.f_layout,f,"");
        ft.commit();*/

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof BlankFragment) {
            BlankFragment f = (BlankFragment) fragment;
            f.setOnClickListenerListener(this);
        }
        else if (fragment instanceof BlankFragment3) {
            BlankFragment3 f = (BlankFragment3) fragment;
            f.setOnClickListenerListener(this);
        }
    }

    public void onSongListSelected( SongList item) {
        Toast.makeText(getApplicationContext(), item.name, Toast.LENGTH_SHORT).show();
        SongFragment fragment2 = new SongFragment();
        BlankFragment2 fragment_button = new BlankFragment2();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Bundle args = new Bundle();
        args.putParcelableArrayList("songs", item.song_list);
        fragment2.setArguments(args);

        ft.replace(R.id.main_frag, fragment2);
        ft.replace(R.id.button_frag, fragment_button);

        ft.commit();
    }

    public void onSongSelected( Song item) {
        Toast.makeText(getApplicationContext(), item.name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClicked(int id){
        if (id == 2) {
            Toast.makeText(getApplicationContext(), ""+id, Toast.LENGTH_SHORT).show();
//                SongFragment fragment2 = new SongFragment();
//                BlankFragment2 fragment_button = new BlankFragment2();
//                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//
//                ft.replace(R.id.main_frag, fragment2);
//                ft.replace(R.id.button_frag, fragment_button);
//
//                ft.commit();
            Intent intent = new Intent(this, New_Playlist.class);

            startActivity(intent);

        }
        if (id == 4) {
            Toast.makeText(getApplicationContext(), ""+id, Toast.LENGTH_SHORT).show();
//                SongFragment fragment2 = new SongFragment();
//                BlankFragment2 fragment_button = new BlankFragment2();
//                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//
//                ft.replace(R.id.main_frag, fragment2);
//                ft.replace(R.id.button_frag, fragment_button);
//
//                ft.commit();
            Intent intent = new Intent(this, PlayListActivity.class);
            //intent.putExtra();
            startActivity(intent);

        }
    }

}

/*    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof SongListFragment) {
            SongListFragment songlistFragment = (SongListFragment) fragment;

            songlistFragment.setOnSongListSelectedListener(this);
            SongListRecyclerViewAdapter adapter = new SongListRecyclerViewAdapter(items, this);
        }
    }
}*/
