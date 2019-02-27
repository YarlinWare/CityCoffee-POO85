package estadojuego;

import Archivos.records;
import TileMap.BackGround;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.layout.Background;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;



public class EstadoMenu extends EstadoJuego{
    BackGround bg;
    
    String[] lopciones={"Nuevo Juego","Records","Creditos","Salir"};
    int opcionactual=0;

    public EstadoMenu(ManagerEstados ms) throws IOException{
       this.ms=ms;
       bg=new BackGround("/Assets/Background/Fondo.gif",1.0);
       bg.setPosition(0, 0);
       bg.setVector(0, 0.08);
       this.iniciar();
    }

    @Override
    public void iniciar() {
        try {
            records.modifica_archivo("records.txt");
        } catch (IOException ex) {
            Logger.getLogger(ListRecord.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void draw(Graphics2D g2d) {
        bg.draw(g2d);//pinta el fondo
        
        ImageIcon Img = new ImageIcon(getClass().getResource("/images/Logo2.png"));
        g2d.drawImage(Img.getImage(), 45, 5, 230, 130, null);   
        ImageIcon Img2 = new ImageIcon(getClass().getResource("/images/edificios2.png"));
        g2d.drawImage(Img2.getImage(), 0, 0, 320, 240, null);
        
        //pintamos el titulo
        /*Font titlefont=new Font("Arial",Font.PLAIN,20);
        g2d.setFont(titlefont);
        g2d.setColor(Color.red);
        g2d.drawString("City Coffee", 90, 50);  */      
        
        Font font=new Font("Arial",Font.PLAIN,10);
        g2d.setFont(font);
         
        for(int i=0;i<this.lopciones.length;i++){
            if(i==this.opcionactual){
                g2d.setColor(Color.white);
            }else{
                g2d.setColor(Color.GREEN);
            }
             g2d.drawString(this.lopciones[i], 120, 155+(i*20));
        }
    }

    @Override
    public void update() {
        bg.update();
        
    }
    
    public void seleccionar(){
        if(this.opcionactual==0){
            this.ms.setEstadoactual(ms.LEVEL1);
            System.out.println("Level");
        }
        if(this.opcionactual==1){
            this.ms.setEstadoactual(ms.RECORD);
            System.out.println("Records");
        }
        if(this.opcionactual==2){
            this.ms.setEstadoactual(ms.CREDITS);
            System.out.println("Creditos");
        }
        if(this.opcionactual==3){
            System.exit(0);
        }
    }
    
    public void Records(){
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
       int tecla=e.getKeyCode();
       switch(tecla){
           case KeyEvent.VK_UP: {this.opcionactual=this.opcionactual-1;
                                  if(opcionactual<0){
                                      opcionactual=this.lopciones.length-1;
                                  }} break;
           case KeyEvent.VK_DOWN: {this.opcionactual=this.opcionactual+1;
                                  if(opcionactual>this.lopciones.length-1){
                                      opcionactual=0;
                                  }} break;
           case KeyEvent.VK_ENTER: {this.seleccionar();} break;
       }
    }

    @Override
    public void keyReleased(KeyEvent e) {
      
    }
}
