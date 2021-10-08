package galliano.android.tools.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import galliano.android.tools.utilities.FlashManagerCamera;
import galliano.android.tools.R;
import galliano.android.tools.utilities.ServiceFlashLight;


public class FlashLight extends Fragment {
    private ImageView btnCamera;
    private boolean switchedOn;

    public FlashLight() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_flashlisght, container, false);
        btnCamera = (ImageView) fragment.findViewById(R.id.lintern);

        switchedOn = ((ServiceFlashLight)getActivity()).getFlashStatus();

        if(switchedOn) btnCamera.setImageResource(R.drawable.flashlight2);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchedOn){
                    btnOffFlash();
                }
                else{
                    btnOnFlash();
                }
            }
        });

        return fragment;
    }

    private void btnOnFlash (){
        btnCamera.setImageResource(R.drawable.flashlight2);
        switchedOn=true;
        Activity activity = getActivity();
        ((FlashManagerCamera)activity).turnOnOff(switchedOn);
    }

    private void btnOffFlash(){
        btnCamera.setImageResource(R.drawable.flashlight);
        switchedOn=false;
        Activity activity = getActivity();
        ((FlashManagerCamera)activity).turnOnOff(switchedOn);
    }


}