package galliano.android.tools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;

import galliano.android.tools.fragments.FlashLight;
import galliano.android.tools.fragments.Level;
import galliano.android.tools.fragments.Menu;
import galliano.android.tools.fragments.Music;
import galliano.android.tools.utilities.ComunicMenu;
import galliano.android.tools.utilities.FlashManagerCamera;
import galliano.android.tools.utilities.ServiceFlashLight;
import galliano.android.tools.utilities.ServiceMusicNow;

public class ToolsActivity extends AppCompatActivity implements ComunicMenu, FlashManagerCamera, ServiceMusicNow, ServiceFlashLight {

    private Fragment[] fragments;
    private CameraManager cameraManager;
    private String idCamera;
    private boolean statusMusic;
    private boolean stateFlash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);

        fragments = new Fragment[3];
        fragments[0]=new FlashLight();
        fragments[1]=new Music();
        fragments[2]=new Level();

        Bundle extras = getIntent().getExtras();
        menu(extras.getInt("ButtonPressed"));
        statusMusic = extras.getBoolean("state");
        stateFlash = extras.getBoolean("stateFlash");

        cameraManager = (CameraManager)getSystemService(Context.CAMERA_SERVICE);

        try {
            idCamera = cameraManager.getCameraIdList()[0];
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void menu(int btn) {
        FragmentManager myManager = getSupportFragmentManager();
        FragmentTransaction myTransaction = myManager.beginTransaction();
        Fragment menu_iluminated = new Menu();
        Bundle data = new Bundle();
        data.putInt("PressedButton",btn);
        menu_iluminated.setArguments(data);
        myTransaction.replace(R.id.fragment,menu_iluminated);
        myTransaction.replace(R.id.tools, fragments[btn]);
        myTransaction.commit();
    }


    @Override
    public void turnOnOff(boolean stateFlash) {
        try{
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                this.stateFlash = stateFlash;
                cameraManager.setTorchMode(idCamera, stateFlash);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        Intent i = new Intent(this,MainActivity.class);
        i.putExtra("state",statusMusic);
        i.putExtra("stateFlash",stateFlash);
        startActivity(i);
    }

    @Override
    public void setMusicStatus(boolean status) {
        this.statusMusic=status;
    }

    @Override
    public boolean getMusicStatus() {
        return statusMusic;
    }

    @Override
    public boolean getFlashStatus() {
        return stateFlash;
    }
}