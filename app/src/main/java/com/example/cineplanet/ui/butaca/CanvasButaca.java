package com.example.cineplanet.ui.butaca;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CanvasButaca extends View {
    Paint paint;
    List<Circle> circles = new ArrayList<>();
    int numW = 1;
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

        drawPantalla(canvas);
        cargarDatos(canvas);
        drawCircles(canvas);
        if(numW==1){
            initDatos();
            numW = 15;
            invalidate();
        }


    }

    void drawPantalla(Canvas canvas){
        canvas.save();
        paint = new Paint();
        paint.setColor(Color.parseColor("#ABB4BC"));
        paint.setTextSize(65);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText("PANTALLA ",(this.getWidth()/2)-165,150,paint);


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
        canvas.restore();


    }

    void drawCircles(Canvas canvas){
        canvas.save();
        paint = new Paint();
        for(Circle circle : circles){
            Paint paint = new Paint();
            if(!circle.dispnible){
                paint.setColor(Color.RED);
                paint.setStyle(Paint.Style.FILL);
                paint.setStrokeWidth(4);
                paint.setAntiAlias(true);
                canvas.drawCircle(circle.X, circle.Y, circle.radius, paint);
            }
            else if(circle.pintado){
                if(!circle.type){
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
                    canvas.save();
                    paint.setColor(Color.parseColor("#00488B"));
                    paint.setStyle(Paint.Style.FILL);
                    paint.setStrokeWidth(4);
                    paint.setAntiAlias(true);
                    canvas.drawCircle(circle.X, circle.Y, circle.radius, paint);

                    drawSillaDeRuedas(canvas,circle.X-(circle.radius/2), circle.Y-(circle.radius/2),"#FFFFFF");
                    canvas.restore();
                }
            }else{
                if(circle.type){
                    canvas.save();
                    drawSillaDeRuedas(canvas,circle.X-(circle.radius/2), circle.Y-(circle.radius/2),"#00488B");
                    canvas.restore();

                }else{
                    canvas.save();
                    paint.setColor(Color.parseColor("#425F7B"));
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeWidth(4);
                    paint.setAntiAlias(true);
                    canvas.drawCircle(circle.X, circle.Y, circle.radius, paint);
                    canvas.restore();
                }
            }

        }
        canvas.restore();

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
    }
    void initDatos(){
        int xValue = 0;
        int yValue = 0;
        for (int i = 0;i < 11;i++){
            for (int j = 0; j < 4; j++){

                if(i==10&&j==2){
                    circles.add(new Circle((this.getWidth()/2-400)+xValue,
                            (465)+yValue,25,false,12-j,String.valueOf((char)(65+i)),true,true));
                    xValue+=65;
                    continue;
                }
                //
                Random random = new Random();
                int randomNumber = random.nextInt(20) + 1;
                if(randomNumber==2){
                    circles.add(new Circle((this.getWidth()/2-400)+xValue,
                            (465)+yValue,25,false,12-j,String.valueOf((char)(65+i)),false,false));
                }else{
                    circles.add(new Circle((this.getWidth()/2-400)+xValue,
                            (465)+yValue,25,false,12-j,String.valueOf((char)(65+i)),false,true));
                }
                //
                xValue+=65;
            }
            xValue=0;
            for (int j = 0; j < 8   ; j++){

                if((i==10&&j==4)||(i==10&&j==6)){
                    circles.add(new Circle((this.getWidth()/2-50)+xValue,
                            (465)+yValue,25,false,8-j,String.valueOf((char)(65+i)),true,true));
                    xValue+=65;
                    continue;
                }

                //

                Random random = new Random();
                int randomNumber = random.nextInt(20) + 1;
                if(randomNumber==2){
                    circles.add(new Circle((this.getWidth()/2-50)+xValue,
                            (465)+yValue,25,false,8-j,String.valueOf((char)(65+i)),false,false));
                }else{
                    circles.add(new Circle((this.getWidth()/2-50)+xValue,
                            (465)+yValue,25,false,8-j,String.valueOf((char)(65+i)),false,true));
                }
                xValue+=65;
            }
            xValue=0;
            yValue += 85;
        }
    }
    void cargarDatos(Canvas canvas){
        canvas.save();
        ///
        Paint paint = new Paint();
        int yValue = 0;
        for (int i = 0;i < 11;i++){
            paint.setColor(Color.parseColor("#6B6E75"));
            paint.setTextSize(45);
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText( String.valueOf((char)(65+i)) ,this.getWidth()/2-490,480+yValue,paint);

            yValue += 85;
        }
        canvas.restore();

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            for (Circle circle : circles) {
                if ((Math.sqrt(Math.pow(event.getX() - circle.X, 2) +
                        Math.pow(event.getY() - circle.Y, 2)) <= circle.radius)&&circle.dispnible) {

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
        boolean type;
        boolean dispnible;
        Circle(int centerX, int centerY, int radius,boolean pintado,int number,String letra,boolean type,boolean dispnible) {
            this.X = centerX;
            this.Y = centerY;
            this.radius = radius;
            this.pintado = pintado;
            this.number = number;
            this.letra = letra;
            this.type = type;
            this.dispnible  =dispnible;
        }

        public int getX() {
            return X;
        }

        public void setX(int x) {
            X = x;
        }

        public int getY() {
            return Y;
        }

        public void setY(int y) {
            Y = y;
        }

        public int getRadius() {
            return radius;
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public boolean isPintado() {
            return pintado;
        }

        public void setPintado(boolean pintado) {
            this.pintado = pintado;
        }

        public String getLetra() {
            return letra;
        }

        public void setLetra(String letra) {
            this.letra = letra;
        }

        public boolean isType() {
            return type;
        }

        public void setType(boolean type) {
            this.type = type;
        }

        public boolean isDispnible() {
            return dispnible;
        }

        public void setDispnible(boolean dispnible) {
            this.dispnible = dispnible;
        }
    }
}
