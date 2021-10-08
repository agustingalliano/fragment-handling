package galliano.android.tools;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import galliano.android.tools.utilities.ComunicMenu;

public class MainActivity extends AppCompatActivity implements ComunicMenu {

    private boolean statusMusic;
    private boolean stateFlash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void menu(int btn) {
        Intent in = new Intent(this, ToolsActivity.class);
        if(getIntent().getExtras()!=null) {
            statusMusic = getIntent().getExtras().getBoolean("state");
            stateFlash = getIntent().getExtras().getBoolean("stateFlash");
        }
        in.putExtra("ButtonPressed", btn);
        in.putExtra("state",statusMusic);
        in.putExtra("stateFlash",stateFlash);
        startActivity(in);
    }
}