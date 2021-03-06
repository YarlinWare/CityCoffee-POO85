package entity;

import tilemap.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Player extends MapObject {
    // animacion
    private ArrayList<BufferedImage[]> sprites;
    // numero de frames para cada accion indicada abajo
    private final int[] NUMFRAMES = {2,9,11};
	
    /*
    * Animacion del personaje segun el sprite de un tamañ de 64x64
    * seleccionando las diferentes acciones por filas
    */
    private static final int REPOSO = 3;
    private static final int ANDAR = 11;
    private static final int ATACAR = 20;
    
    
    public Player(TileMap tm)throws IOException{        
        super(tm);
        // El personaje empezara cayendo
        this.caida=true;
        
        // Tamanio de cada frame
        width = 64;
        height = 64;
        
        // Detectando colisiones
        this.colliderwidth = 20;
        colliderheight = 45;
        
        // Movimiento
        movespeed = 0.4;// Velocidad movimiento
        maxspeed = 2.6; // Velocidad movimiento
        minspeed = 0.4; // Velocidad Frenado
        
        // Caida
        this.caidaspeed = 0.2;
        this.maxcaidaspeed=4.0;
        
        // Salto
        this.maxspeedsalto = -5.8;
        this.minspeedsalto = 0.3;
		
        this.mirarderecha = true;
        
                    
        BufferedImage spritesheet = ImageIO.read(
                                    getClass().getResourceAsStream(""
                                    + "/assets/character/hero1.png")
                                    );
        
        sprites = new ArrayList<BufferedImage[]>();
        for(int i = 0; i < 21; i++) {
            int contador=0;
                if((i==REPOSO)||(i==ANDAR)||(i==ATACAR)){
                    BufferedImage[] bi =new BufferedImage[NUMFRAMES[contador]];

                    for(int j = 0; j < NUMFRAMES[contador]; j++) {
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
        // Actualizar posicion
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
            g.drawImage(
                this.animacion.getImagen(),
                (int)(x+xmap-width/2), 
                (int)(y+ymap-height/2),
                width,
                height,
                null
            );
        }else{
            g.drawImage(
                this.animacion.getImagen(),
                (int)(x+xmap-width/2)+width, 
                (int)(y+ymap-height/2),
                -width,
                height, 
                null
            );
        }
    }
   
    
}
