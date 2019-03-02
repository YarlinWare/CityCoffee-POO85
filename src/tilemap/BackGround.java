package tilemap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import coffeecity.PanelJuego;

/**
 *
 * @author rapterpakfa
 */
public class BackGround {
    double x;
    double  y;    
    double desplazamiento_x;//dx
    double desplazamiento_y;//dy    
    double esaclaMovimiento;    
    BufferedImage image;

    public BackGround(String nombre,double esaclaMovimiento) throws IOException{
        image= ImageIO.read(this.getClass().getResourceAsStream(nombre));
        this.esaclaMovimiento = esaclaMovimiento;
    }
    
    public void setPosition(double x, double y){
        this.x=(x*this.esaclaMovimiento)%PanelJuego.WIDTH;
        this.y=(y*esaclaMovimiento)%PanelJuego.WIDTH;
        
    }
    //posicion vectorial
    public void setVector(double dx, double dy){
        this.desplazamiento_x=dx;
        this.desplazamiento_y=dy;
        
    }
    
    public void update(){
        x+=desplazamiento_x;
        y+=desplazamiento_y;       
    }
    
    public void draw(Graphics2D g2d){
        g2d.drawImage(image, (int)x, (int)y,null); 
    }
    
}
