package com.example.aluno.running;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import com.example.aluno.running.R;

public class SensoAngulos extends AppCompatImageView{
int l1, l2, l3, l4;
float[] angulos;
Bitmap fundo;
Paint tra;
Bitmap bur;

public SensoAngulos (Context contexto, int l1){
    super(contexto);
    this.l1=l1;

    l2 = l1/2;
    l3 = l1/10;
    l4 = l1/100;
    angulos = new float[2];
    angulos[0]= 0;
    angulos[1]=0;
    fundo = iniciaFundo();
    tra = new Paint();
    tra.setColor(Color.BLACK);
    tra.setTextSize(20);
    BitmapDrawable bol = (BitmapDrawable) ContextCompat.getDrawable(contexto, R.drawable.bur);
    bur =  bol.getBitmap();
    bur = Bitmap.createScaledBitmap(bur, l3*2, l3*2, true);

}
private Bitmap iniciaFundo(){
    Bitmap.Config conf = Bitmap.Config.ARGB_4444;
    Bitmap fundo = Bitmap.createBitmap(l1, l1, conf);
    Canvas lin = new Canvas(fundo);
    Paint lapiz = new Paint();
    lapiz.setColor(Color.RED);
    lin.drawCircle(l2,l2, l3,lapiz);
    lapiz.setColor(Color.BLACK);
    lin.drawCircle(l2,l2, l2-l4,lapiz);
    lapiz.setColor(Color.RED);
    lin.drawCircle(l2,l2, l3+l4,lapiz);
    lapiz.setStrokeWidth(l4);
    lin.drawLine(l2,0,l2,l1, lapiz);
    lin.drawLine(0,l2,l1,l2,lapiz);
    return fundo;

}
public void angulos(float[] angulos){
    this.angulos = angulos;
    invalidate();
    }
protected  void onMeasure(int widthMeasureSpec, int heighMeasureSpec){
    super.onMeasure(widthMeasureSpec, heighMeasureSpec);
    setMeasuredDimension(l1,l1);
}
    protected void onDraw (Canvas lin){
    super.onDraw(lin);
    lin.drawBitmap(fundo, 0, 0, null);
    int posX = l2 - l3+(int)(angulos[0]/10*l2);
    int posY = l2 - l3-(int)(angulos[1]/10*l2);
    lin.drawBitmap(bur, posX, posY, null);
    }
}
