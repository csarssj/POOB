package Shapes;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.event.*;  
import javax.swing.*; 

/**
 * Clase line para poder dibujar las lonas
 * 
 * @author C�sar Eduardo Gonz�lez y Brayan Santiango Buitrago 
 * @version 15/09/2019
 */
public class Line
{
    // instance variables - replace the example below with your own
    private int height;
    private int width;
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int x11;
    private int y11;
    private int x21;
    private int y21;
    private String color;
    private boolean isVisible;

    /**
     * Constructor de la clase line
     */
    public Line()
    {
        height = 30;
        width = 40;
        x1 = 70;
        x11= 75;
        x2 = 100;
        x21= 105;
        y1 = 15;
        y11= 25;
        y2 = 30;
        y21= 40;
        color = "Magenta";
        isVisible = false;
    }
    /**
     * Constructor de la clase line
     * @param x1 posicion en x del primer punto
     * @param y1 posicion en y del primer punto
     * @param x2 posicion en x del segundo punto
     * @param y2 posicion en y del segundo punto
     */
    public Line(int x1, int x2, int y1, int y2)
    {
        height = 30;
        width = 40;
        this.x1 = x1;
        this.x11 = x1+5;
        this.x2 = x2;
        this.x21 = x2+5;
        this.y1 = y1;
        this.y11 = y1+10;
        this.y2 = y2;
        this.y21 = y2+10;
        color = "Magenta";
        isVisible = false;
    }
    /**
     * Make this line visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    /**
     * Return position X of shape.
     */
    public double getX(){
        return x1;
    }
    /**
     * Return position y of shape.
     */
    public double getY(){
        return y1;
    }
    /**
     * Make this rectangle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /**
     * Move the rectangle a few pixels to the right.
     */
    public void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Move the rectangle a few pixels to the left.
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }

    /**
     * Move the rectangle a few pixels up.
     */
    public void moveUp(){
        moveVertical(-20);
    }

    /**
     * Move the rectangle a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }

    /**
     * Move the rectangle horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        x1 += distance;
        draw();
    }

    /**
     * Move the rectangle vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        erase();
        y1 += distance;
        draw();
    }

    /**
     * Slowly move the rectangle horizontally.
     * @param distance the desired distance in pixels
     */
    public void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            x1 += delta;
            draw();
        }
    }

    /**
     * Slowly move the rectangle vertically.
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            y1 += delta;
            draw();
        }
    }

    /**
     * Change the size to the new size
     * @param newHeight the new height in pixels. newHeight must be >=0.
     * @param newWidht the new width in pixels. newWidth must be >=0.
     */
    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        draw();
    }
    /**
     * Change the size to the new size
     * @param newHeight the new height in pixels. newHeight must be >=0.
     * @param newWidht the new width in pixels. newWidth must be >=0.
     */
    public void changeSize2(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        this.x1 = x1-5;
        this.y1 = y1-5;
        this.x2 = x2+5;
        this.y2 = y2-5;
        this.x11 = x11-5;
        this.x21 = x21+5;
        this.y11 = y11+5;
        this.y21 = y21+5;
        draw();
    }
    /**
     * Change the size to the new size
     * @param newHeight the new height in pixels. newHeight must be >=0.
     * @param newWidht the new width in pixels. newWidth must be >=0.
     */
    public void changeSize3(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        this.x1 = x1+5;
        this.y1 = y1+5;
        this.x2 = x2-5;
        this.y2 = y2+5;
        this.x11 = x11+5;
        this.x21 = x21-5;
        this.y11 = y11-5;
        this.y21 = y21-5;
        draw();
    }
    
    /**
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }

    /*
     * Draw the rectangle with current specifications on screen.
     */

    private void draw() {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = {x1,x2,x21,x11};
            int[] ypoints = {y1,y2,y21,y11};
            canvas.draw(this, color, new Polygon(xpoints, ypoints, 4));
            canvas.wait(10);
        }
    }

    /*
     * Erase the rectangle on screen.
     */
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    /**
     * Se obtiene el ancho de la linea
     * @returns int width ancho de la linea
     */
    public int getW(){
        return width;
    }
    /**
     * Se obtiene el alto de la linea
     * @returns int height alto de la linea
     */
    public int getH(){
        return height;
    }
}
