/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.awt.image.BufferedImage;

/**
 *
 * @author Rapter
 */
public class AnimacionPersonaje {
    BufferedImage[] frames;
    long delay;//tiempo de espera
    int AnimacionActual;    
    boolean completada;
    long tiempoStart;
    
    public AnimacionPersonaje(){
        completada=false;
    }
    
    public void cargarFrames(BufferedImage[] animacion){
        frames=animacion;
        AnimacionActual=0;
        tiempoStart=System.nanoTime();
        completada=false;
    }
    
     public void update(){
       if(delay==-1) return;
            long elapsed=System.nanoTime()-tiempoStart;
            if(delay<elapsed){
                AnimacionActual++;
                tiempoStart=System.nanoTime();
                
            }
            if(AnimacionActual==frames.length){
                AnimacionActual=0;
                completada=true;

            }
    }

    public BufferedImage[] getFrames() {
        return frames;
    }

    public void setFrames(BufferedImage[] frames) {
        this.frames = frames;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay*1000000;
    }

    public int getAnimacionActual() {
        return AnimacionActual;
    }

    public void setAnimacionActual(int AnimacionActual) {
        this.AnimacionActual = AnimacionActual;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public long getTiempoStart() {
        return tiempoStart;
    }

    public void setTiempoStart(long tiempoStart) {
        this.tiempoStart = tiempoStart;
    }
     
     
}
