/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import TileMap.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Rapter
 */
public class Player extends MapObject {
    // animacion
    private ArrayList<BufferedImage[]> sprites;
    // numero de frames para cada accion indicada abajo
    private final int[] numFrames = {2,9,11};
	
    /*
    * Animacion del personaje segun el sprite de un tamañ de 64x64
    * seleccionando las diferentes acciones por filas
    */
    private static final int REPOSO = 3;
    private static final int ANDAR = 11;
    private static final int ATACAR = 20;
    
    
    // player stuff
    private int health;
    private int maxHealth;
    private int fire;
    private int maxFire;
    private boolean dead;
    private boolean flinching;
    private long flinchTimer;

    // fireball
    private boolean firing;
    private int fireCost;
    private int fireBallDamage;
    
    public Player(TileMap tm)throws IOException{        
        super(tm);
        // El personaje empezara callendo
        this.caida=true;
        
        // Tamanio de cada frame
        width = 64;
        height = 64;
        
        // Detectando colisiones
        this.colliderwidth = 45;
        colliderheight = 45;
        
        // Movimiento
        movespeed = 0.4;// Velocidad movimiento
        maxspeed = 2.6; // Velocidad movimiento
        minspeed = 0.4; // Velocidad Frenado
        
        // Caida
        this.caidaspeed = 0.2;
        this.maxcaidaspeed=4.0;
        
        // Salto
        this.maxspeedsalto = -4.8;
        this.minspeedsalto = 0.3;
		
        this.mirarderecha = true;
        
        health = maxHealth = 5;
        fire = maxFire = 2500;
        fireCost = 200;
        fireBallDamage = 5;
                
        
        BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream(""
                + "/Assets/character/hero2.png"));
        
        sprites = new ArrayList<BufferedImage[]>();
        for(int i = 0; i < 21; i++) {
            int contador=0;
                if((i==REPOSO)||(i==ANDAR)||(i==ATACAR)){
                BufferedImage[] bi =new BufferedImage[numFrames[contador]];

                for(int j = 0; j < numFrames[contador]; j++) {
                    bi[j] = spritesheet.getSubimage(j* width,i * height,64,64);
                        }	
                sprites.add(bi);
                contador++;
                }
        }
        
        this.animacion = new AnimacionPersonaje();
        this.animacionActual = REPOSO;
        this.animacion.setFrames(sprites.get(0));
        this.animacion.setDelay(200);
    }
    
		
    private void getNextPosition() {
        // movimiento
        if(left) {
            dx -= movespeed;
            if(dx < -maxspeed) {
                dx = -maxspeed;
            }
        }
        else if(right) {
            dx += movespeed;
            if(dx > maxspeed) {
                dx = maxspeed;
            }
        }
        else {
            if(dx > 0) {
                dx -= minspeed;
                if(dx < 0) {
                    dx = 0;
                }
            }
            else if(dx < 0) {
                dx += minspeed;
                if(dx > 0) {
                    dx = 0;
                }
            }
        }	
        // salto
        if(salto && !caida) {
            dy = this.maxspeedsalto;
            caida = true;
        }
        // caida
        if(caida){
            dy += this.caidaspeed;
            if(dy > 0) this.salto = false;
            if(dy < 0 && !this.salto) dy += this.minspeedsalto;
            if(dy > this.maxcaidaspeed) dy = this.maxcaidaspeed;
        }
    }

    public void update() {
        // update position
        getNextPosition();
        this.logicaColision();
        setPosition(xaux, yaux);

        if(left || right) {
            if(this.animacionActual != ANDAR) {
                this.animacionActual = ANDAR;
                this.animacion.setFrames(sprites.get(1));
                this.animacion.setDelay(60);
                //width = 30;
            }
        }
        else {
            if(this.animacionActual != REPOSO) {
                this.animacionActual = REPOSO;
                this.animacion.setFrames(sprites.get(0));
                this.animacion.setDelay(200);
                //width = 30;
            }
        }

        this.animacion.update();
        // set direction
        if(this.animacionActual != ATACAR) {
            if(right) this.mirarderecha = true;
            if(left) this.mirarderecha = false;
        }

    }

    public void draw(Graphics2D g) {		
        this.setMapPosition();		
        // Dibujar jugador
        if(this.mirarderecha){                    
            g.drawImage(this.animacion.getImagen(),(int)(x+xmap-width/2), (int)(y+ymap-height/2),width,height,null);
        }else{
            g.drawImage(this.animacion.getImagen(),(int)(x+xmap-width/2)+width, (int)(y+ymap-height/2),-width,height, null);
        }
    }
   
    
}
