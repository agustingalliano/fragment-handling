package galliano.android.tools.utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import galliano.android.tools.R;

public class ScreenLevel extends AppCompatImageView {

    int side, radio, smallerRadius, stroke;
    float[] angles;
    Bitmap bitmap;
    Bitmap bubble;

    public ScreenLevel(Context context, int side) {
        super(context);
        this.side=side;
        radio=side/2;
        smallerRadius=side/10;
        stroke=side/100;
        angles=new float[2];
        angles[0]=0;
        angles[1]=0;
        bitmap=startBackground();
        BitmapDrawable bola=(BitmapDrawable) ContextCompat.getDrawable(context, R.drawable.graphic);
        bubble=bola.getBitmap();
        bubble=Bitmap.createScaledBitmap(bubble, smallerRadius*2, smallerRadius*2, true);
    }


    private Bitmap startBackground() {
        Bitmap.Config conf=Bitmap.Config.ARGB_8888;
        Bitmap bitmap=Bitmap.createBitmap(side, side, conf);
        Canvas canvas=new Canvas(bitmap);
        Paint pencil=new Paint();
        pencil.setColor(Color.RED);
        canvas.drawCircle(radio, radio, radio, pencil);
        pencil.setColor(Color.BLACK);
        canvas.drawCircle(radio, radio, radio-stroke, pencil);
        pencil.setColor(Color.RED);
        canvas.drawCircle(radio, radio, smallerRadius+stroke, pencil);
        pencil.setStrokeWidth(stroke);
        canvas.drawLine(radio, 0, radio, side, pencil);
        canvas.drawLine(0, radio, side, radio, pencil);
        return bitmap;
    }

    public void angles(float[] angles) {
        this.angles=angles;
        invalidate();
    }


    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(side, side);
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, null);
        int posX=radio-smallerRadius+(int)(angles[0]/10*radio);
        int posY=radio-smallerRadius-(int)(angles[1]/10*radio);
        canvas.drawBitmap(bubble, posX, posY, null);
    }

}
