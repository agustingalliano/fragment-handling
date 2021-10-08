package galliano.android.tools.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import galliano.android.tools.R;
import galliano.android.tools.utilities.ServiceMusic;
import galliano.android.tools.utilities.ServiceMusicNow;

public class Music extends Fragment {

    private boolean switchedOn;
    private ImageView buttonMusic;


    public Music() {

    }


    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switchedOn=((ServiceMusicNow)getActivity()).getMusicStatus();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_music, container, false);

        buttonMusic = (ImageView) fragment.findViewById(R.id.music);
        switchedOn=((ServiceMusicNow)getActivity()).getMusicStatus();
        if(switchedOn) buttonMusic.setImageResource(R.drawable.music2);
        buttonMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchedOn) turnOffMusic();
                else turnOnMusic();
            }
        });

        return fragment;
    }

    public void turnOnMusic (){
        buttonMusic.setImageResource(R.drawable.music2);
        Intent intent = new Intent(getActivity(), ServiceMusic.class);
        getActivity().startService(intent);
        switchedOn=!switchedOn;
        ((ServiceMusicNow)getActivity()).setMusicStatus(switchedOn);
    }

    public void turnOffMusic(){
        buttonMusic.setImageResource(R.drawable.music);
        Intent intent = new Intent(getActivity(),ServiceMusic.class);
        getActivity().stopService(intent);
        switchedOn=!switchedOn;
        ((ServiceMusicNow)getActivity()).setMusicStatus(switchedOn);
    }
}