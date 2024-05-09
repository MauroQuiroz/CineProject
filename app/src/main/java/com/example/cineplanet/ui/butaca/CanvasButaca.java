package com.example.cineplanet.ui.butaca;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CanvasButaca extends View {
    Paint paint;
    List<Circle> circles = new ArrayList<>();
    public CanvasButaca(Context context) {
        super(context);
        init();
    }

    public CanvasButaca(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    void init(){
        paint = new Paint();
        setBackgroundColor(Color.parseColor("#D5D8DF"));

    }


    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();


        drawPantalla(canvas);
        cargarDatos(canvas);
        drawCircles(canvas);

        canvas.restore();
    }

    void drawPantalla(Canvas canvas){
        paint = new Paint();
        paint.setColor(Color.parseColor("#ABB4BC"));
        paint.setTextSize(65);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText("PANTALLA",(this.getWidth()/2)-165,150,paint);


        //Rect 1
        paint.setColor(Color.parseColor("#F5F5F7"));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        canvas.drawRect(this.getWidth()/2-390,180,this.getWidth()/2+390,220, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.parseColor("#DEDEDE"));
        canvas.drawRect(this.getWidth()/2-390,180,this.getWidth()/2+390,220, paint);
        //Rect 2
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);
        canvas.drawRect(this.getWidth()/2-390,220,this.getWidth()/2+390,235, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.parseColor("#DEDEDE"));
        canvas.drawRect(this.getWidth()/2-390,220,this.getWidth()/2+390,235, paint);


        //LUz
        int[] puntosX = {this.getWidth()/2-480, this.getWidth()/2-390,this.getWidth()/2+390, this.getWidth()/2+480};
        int[] puntosY = {400, 235, 235, 400};
        Path path = new Path();
        path.moveTo(puntosX[0], puntosY[0]);
        for (int i = 1; i < puntosX.length; i++) {
            path.lineTo(puntosX[i], puntosY[i]);
        }
        path.close();
        Shader shader = new LinearGradient(0,235, 0, 360,
                Color.parseColor("#FAFAFA"), Color.parseColor("#D5D8DF"), Shader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, paint);


    }

    void drawCircles(Canvas canvas){

        for(Circle circle : circles){
            Paint paint = new Paint();

            if(circle.pintado){
                paint.setColor(Color.parseColor("#00488B"));
                paint.setStyle(Paint.Style.FILL);
                paint.setStrokeWidth(4);
                paint.setAntiAlias(true);
                canvas.drawCircle(circle.X, circle.Y, circle.radius, paint);

                //texto
                paint.setTextSize(20);
                paint.setColor(Color.WHITE);
                String texto = ""+circle.letra+circle.number;
                Rect bounds = new Rect();
                paint.getTextBounds(texto, 0, texto.length(), bounds);
                float x = circle.X - bounds.width() / 2f;
                float y = circle.Y + bounds.height() / 2f;
                canvas.drawText(texto, x, y, paint);


            }else{
                paint.setColor(Color.parseColor("#425F7B"));
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(4);
                paint.setAntiAlias(true);
                canvas.drawCircle(circle.X, circle.Y, circle.radius, paint);
            }

        }

    }
    void cargarDatos(Canvas canvas){
        Paint paint = new Paint();
        int xValue = 0;
        int yValue = 0;
        for (int i = 0;i < 11;i++){
            paint.setColor(Color.parseColor("#6B6E75"));
            paint.setTextSize(45);
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText( String.valueOf((char)(65+i)) ,this.getWidth()/2-490,480+yValue,paint);

            //
            for (int j = 0; j < 4; j++){
                circles.add(new Circle((this.getWidth()/2-400)+xValue,
                        (465)+yValue,25,false,12-j,String.valueOf((char)(65+i))));
                xValue+=65;
            }
            xValue=0;
            for (int j = 0; j < 8   ; j++){
                circles.add(new Circle((this.getWidth()/2-50)+xValue,
                        (465)+yValue,25,false,8-j,String.valueOf((char)(65+i))));
                xValue+=65;
            }
            xValue=0;
            yValue += 85;
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            for (Circle circle : circles) {
                if (Math.sqrt(Math.pow(event.getX() - circle.X, 2) +
                        Math.pow(event.getY() - circle.Y, 2)) <= circle.radius) {


                    circle.pintado = circle.pintado?false:true;
                    invalidate();
                    return true;
                }
            }
        }
        return super.onTouchEvent(event);
    }

    private class Circle {
        int X, Y, radius, number;
        boolean pintado;
        String letra;
        Circle(int centerX, int centerY, int radius,boolean pintado,int number,String letra) {
            this.X = centerX;
            this.Y = centerY;
            this.radius = radius;
            this.pintado = pintado;
            this.number = number;
            this.letra = letra;
        }
    }
}
