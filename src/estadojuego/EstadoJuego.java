/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadojuego;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author 
 */
public abstract class EstadoJuego {
    
    protected ManagerEstados ms;
    public abstract void iniciar();
    public abstract void draw(Graphics2D g2d);
    public abstract void update();
    //cargando eventos teclado
    public abstract void keyTyped(KeyEvent ke);
    public abstract void keyPressed(KeyEvent ke);
    public abstract void keyReleased(KeyEvent ke);
    
    
    
}
