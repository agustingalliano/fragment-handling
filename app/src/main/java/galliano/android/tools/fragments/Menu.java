package galliano.android.tools.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import galliano.android.tools.utilities.ComunicMenu;
import galliano.android.tools.R;

public class Menu extends Fragment {

    private final int [] BUTTONS={R.id.lintern,R.id.music,R.id.level};
    private final int [] BUTTONS_ILLUMINATED = {R.drawable.flashlight2,R.drawable.music2,R.drawable.level2};
    private int button;


    public Menu() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myMenu = inflater.inflate(R.layout.fragment_menu, container, false);

        button=-1;

        if(getArguments()!=null){
            button=getArguments().getInt("PressedButton");
        }

        ImageButton btnMenu;

        for(int i=0; i < BUTTONS.length;i++){
            btnMenu = (ImageButton) myMenu.findViewById(BUTTONS[i]);

            if(button==i){
                btnMenu.setImageResource(BUTTONS_ILLUMINATED[i]);
            }

            final int queBoton=i;
            btnMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Activity thisActivity = getActivity();
                    ((ComunicMenu)thisActivity).menu(queBoton);
                }
            });

        }
        return myMenu;
    }
}