package com.example.cineplanet.ui.butaca;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CanvasButacaDetalle  extends View {
    Paint paint;
    public CanvasButacaDetalle(Context context) {
        super(context);
    }

    public CanvasButacaDetalle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();

        //drawInfo(canvas);
        linea1(canvas);
        linea2(canvas);
        canvas.restore();
    }
    void linea1(Canvas canvas){
        paint = new Paint();
        paint.setColor(Color.parseColor("#425F7B"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);
        canvas.drawCircle(this.getWidth()/2-430, 50, 20, paint);
        //
        paint.setColor(Color.parseColor("#61646A"));
        paint.setTextSize(30);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("Disponibles",this.getWidth()/2-400,62,paint);

        //2
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);
        canvas.drawCircle(this.getWidth()/2-200, 50, 20, paint);
        //
        paint.setColor(Color.parseColor("#61646A"));
        paint.setTextSize(30);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("Ocupada",this.getWidth()/2-170,62,paint);

        //3
        paint = new Paint();
        paint.setColor(Color.parseColor("#00488B"));
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);
        canvas.drawCircle(this.getWidth()/2, 50, 20, paint);
        //
        paint.setColor(Color.parseColor("#61646A"));
        paint.setTextSize(30);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("Ocupada",this.getWidth()/2+30,62,paint);

        drawSillaDeRuedas(canvas,this.getWidth()/2+200,35,"#00488B");

        paint = new Paint();
        paint.setColor(Color.parseColor("#61646A"));
        paint.setTextSize(32);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("Silla de ruedas",this.getWidth()/2+200+38,62,paint);
    }

    void drawSillaDeRuedas(Canvas canvas,int x,int y,String colorr){

        int intiX=x;
        int initY=y;
        float scale = 0.8f;

        canvas.save();
        canvas.translate(intiX, initY);
        canvas.scale(scale, scale);
        canvas.translate(-intiX, -initY);

        //drawCillaDe ruedas
        paint = new Paint();
        paint.setColor(Color.parseColor(colorr));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);
        canvas.drawCircle(intiX, initY, 7, paint);

        canvas.drawLine(intiX, initY+7,intiX , initY+7+10, paint);
        canvas.drawLine(intiX, initY+7+10,intiX+20 , initY+7+8, paint);
        canvas.drawLine(intiX+20 , initY+7+8,intiX+35 , initY+7+30, paint);
        canvas.drawLine(intiX+35  , initY+7+30,intiX+42 , initY+7+27, paint);



        paint.setColor(Color.parseColor(colorr));
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(intiX+10 - 18, (initY+20) -18, intiX+10 + 18, (initY+20)  + 18);
        canvas.drawArc(rectF, 30, 180, false, paint);
        canvas.restore();
        //


    }
    void linea2(Canvas canvas){
        drawSillaDeRuedas(canvas,this.getWidth()/2-470,100,"#00488B");

        paint = new Paint();
        paint.setColor(Color.parseColor("#61646A"));
        paint.setTextSize(28);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("Todas nuestras salas cuentan con espacios señalizados para sillas",this.getWidth()/2-470+45,120,paint);
        canvas.drawText("de ruedas. Consulta en Botelería la ubicación de las mismas",this.getWidth()/2-470+45,150,paint);
    }

}
