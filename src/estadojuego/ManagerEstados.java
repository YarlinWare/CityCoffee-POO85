/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadojuego;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author 
 */
public class ManagerEstados {
    ArrayList<EstadoJuego> estadosJuego;
    int estadoactual;
    public final int MENU=0;
    public final int LEVEL1=1; 
    public final int RECORD=2;  
    public final int CREDITS=3; 

    public ManagerEstados() throws IOException {
        this.estadosJuego=new ArrayList<EstadoJuego>();
        this.estadoactual=LEVEL1;
        EstadoMenu menu=new EstadoMenu(this);
        this.estadosJuego.add(menu);
        StateLevel_1 level1=new StateLevel_1(this);
        this.estadosJuego.add(level1);
        ListRecord record=new ListRecord(this);
        this.estadosJuego.add(record);
        Credits creditos=new Credits(this);
        this.estadosJuego.add(creditos);
    }

    public int getEstadoactual() {
        return estadoactual;
    }

    public void setEstadoactual(int estadoactual) {
        this.estadoactual = estadoactual;
        this.estadosJuego.get(estadoactual).iniciar();
    }
    
    public void update(){
        this.estadosJuego.get(estadoactual).update();
    }
    
    public void draw(Graphics2D g){
        this.estadosJuego.get(estadoactual).draw(g);
    }

   public  void keyTyped(KeyEvent e){
       this.estadosJuego.get(estadoactual).keyTyped(e);
   }
   public  void keyPressed(KeyEvent e){
       this.estadosJuego.get(estadoactual).keyPressed(e);
   } 
   public  void keyReleased(KeyEvent e){
        this.estadosJuego.get(estadoactual).keyReleased(e);
   }
  
}