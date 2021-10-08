package galliano.android.tools.fragments;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import galliano.android.tools.utilities.ScreenLevel;
import galliano.android.tools.R;


public class Level extends Fragment implements SensorEventListener {

    private SensorManager myManager;
    private Sensor mySensor;
    private ScreenLevel screen;

    public Level() {
    }

    public final void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        myManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mySensor = myManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        int side = getResources().getDimensionPixelSize(R.dimen.maximum);
        screen = new ScreenLevel(getActivity(),side);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return screen;
    }

    public void onResume(){
        super.onResume();
        myManager.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_GAME);
    }

    public void onPause(){
        super.onPause();
        myManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        screen.angles(event.values);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}