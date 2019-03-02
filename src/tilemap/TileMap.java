/*
 * clase encargada de nuestro mapa de tiles
 */
package tilemap;

import coffeecity.PanelJuego;
import entity.Player;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

public class TileMap {
    //posicion del mapa
    private double x;
    private double y;
    
    //Limites del rectagulo
    private double xmin;
    private double xmax;
    private double ymin;
    private double ymax;
    
    //variable para los movimientos de càmara
    private double tween;
    //matriz Bidimensional mapa
    int[][] map;
    int tamanio_celda;//tsize
    int num_map_columnas;
    int num_map_filas;
    int ancho;
    int alto;
    
    
    //pintar mapa: desplazamiento
    int filaOffset;
    int columnaOffset;
    int num_columnas_pintado;
    int num_filas_pintado;
    
    //tiles
    Tile[][] tiles;
    int num_tile_columnas;
    int num_tile_filas;
    int id;
    BufferedImage image;
    
    

    public TileMap(int tcelda) {
        this.tamanio_celda=tcelda;
        num_filas_pintado=(int)PanelJuego.HEIGHT/tamanio_celda;
        num_columnas_pintado=(int)(PanelJuego.WIDTH/tamanio_celda)+3;
        tween=0.07;
    }
    
    public void cargarTiles(String nombre) throws IOException{
        
        BufferedImage tileset= ImageIO.read(this.getClass().getResourceAsStream(nombre));
        this.num_tile_columnas=tileset.getHeight()/tamanio_celda;
        this.num_tile_filas=tileset.getWidth()/tamanio_celda;
       
        
        tiles = new Tile[num_tile_filas][num_tile_columnas];
        BufferedImage sub_imagen;
        
        int contador=0;
        for (int i = 0; i < num_tile_filas; i++) {
            for (int j = 0; j < num_tile_columnas; j++) {
                Tile tile;
                sub_imagen= tileset.getSubimage(j*tamanio_celda, i*tamanio_celda, tamanio_celda, tamanio_celda);
                tile =new Tile(sub_imagen,0,contador);
                tile.setType(tile.asignarTipo(contador));
                tiles[i][j]=tile; 
                contador++;  
            }                  
        }
        
    }
    
    public void cargarMapa(String nombre) throws IOException{
       InputStream entrada= getClass().getResourceAsStream(nombre);
       BufferedReader br = new BufferedReader(new InputStreamReader(entrada));
       
       num_map_columnas=Integer.parseInt(br.readLine());      
       num_map_filas=Integer.parseInt(br.readLine());
       
       
        ancho = this.num_columnas_pintado*tamanio_celda;
        alto = this.num_filas_pintado * tamanio_celda;
       
        map=new int[num_map_filas][num_map_columnas];
       
        xmin = PanelJuego.WIDTH - ancho;
        xmax = 0;
        ymin = PanelJuego.HEIGHT - alto;
        ymax = 0;
        
        String limit = ",";
        for(int i=0;i<num_map_filas;i++){
            String line = br.readLine();
            String[] tokens = line.split(limit);
            for(int j=0;j<this.num_map_columnas;j++){                
                map[i][j]=Integer.parseInt(tokens[j]);
            }
       }
    }
    
    public int getTipo(int x,int y){
        int valor = map[x][y];
        int ffila = valor / this.num_tile_columnas;
        int fcol = valor % this.num_tile_columnas;  
        return tiles[ffila][fcol].getType();/*
        if(fcol>=0){
            return tiles[ffila][fcol].getType(); 
         }else{
             return 0;
         }       */ 
    }
    
    public double getTween() {
        return tween;
    }
    
    public void setTween(double d) { tween = d; }   
        
    public void setPosicion(double x,double y){
        this.x+=(x-this.x)*tween;
        this.y+=(y-this.y)*tween;
        ajustarArea();
        columnaOffset=(int)-this.x/tamanio_celda;
        filaOffset=(int)-this.y/tamanio_celda;
    }
        
    public void ajustarArea(){
        if(x < xmin){
            x = xmin;
        }
        if(y < ymin){
            y = ymin;
        }
        if(x > xmax){
            x = xmax;
        }
        if(y > ymax){
            y = ymax;
        }       
    }
    
    public void draw(Graphics2D g2d){
        
        for(int fila=this.filaOffset;fila<this.filaOffset+this.num_map_filas;fila++){
            if (fila>=num_map_filas) break;
            for(int col=this.columnaOffset;col<this.columnaOffset+this.num_map_columnas;col++){            
                if (col>=num_map_columnas) break;
                int valor=map[fila][col];
                int fila_fila=valor/num_tile_filas;
                int fila_col=valor%num_tile_columnas-1; 
                
                /*
                g2d.drawImage(
                    tiles[fila_fila][fila_col].getImage(),
                    (int)(this.x+col)*tamanio_celda , 
                    (int)(this.y+fila)*tamanio_celda ,
                    null 
                );*/
                
                if(fila_col>=0){
                    g2d.drawImage(
                        tiles[fila_fila][fila_col].getImage(),
                        (int)x + col * tamanio_celda,
                        (int)y + fila * tamanio_celda,
                        null
                    ); 
                }

            }
        }
    }   

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getXmin() {
        return xmin;
    }

    public void setXmin(double xmin) {
        this.xmin = xmin;
    }

    public double getXmax() {
        return xmax;
    }

    public void setXmax(double xmax) {
        this.xmax = xmax;
    }

    public double getYmin() {
        return ymin;
    }

    public void setYmin(double ymin) {
        this.ymin = ymin;
    }

    public double getYmax() {
        return ymax;
    }

    public void setYmax(double ymax) {
        this.ymax = ymax;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public int getTamanio_celda() {
        return tamanio_celda;
    }

    public void setTamanio_celda(int tamanio_celda) {
        this.tamanio_celda = tamanio_celda;
    }

    public int getNum_map_columnas() {
        return num_map_columnas;
    }

    public void setNum_map_columnas(int num_map_columnas) {
        this.num_map_columnas = num_map_columnas;
    }

    public int getNum_map_filas() {
        return num_map_filas;
    }

    public void setNum_map_filas(int num_map_filas) {
        this.num_map_filas = num_map_filas;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getFilaOffset() {
        return filaOffset;
    }

    public void setFilaOffset(int filaOffset) {
        this.filaOffset = filaOffset;
    }

    public int getColumnaOffset() {
        return columnaOffset;
    }

    public void setColumnaOffset(int columnaOffset) {
        this.columnaOffset = columnaOffset;
    }

    public int getNum_columnas_pintado() {
        return num_columnas_pintado;
    }

    public void setNum_columnas_pintado(int num_columnas_pintado) {
        this.num_columnas_pintado = num_columnas_pintado;
    }

    public int getNum_filas_pintado() {
        return num_filas_pintado;
    }

    public void setNum_filas_pintado(int num_filas_pintado) {
        this.num_filas_pintado = num_filas_pintado;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public int getNum_tile_columnas() {
        return num_tile_columnas;
    }

    public void setNum_tile_columnas(int num_tile_columnas) {
        this.num_tile_columnas = num_tile_columnas;
    }

    public int getNum_tile_filas() {
        return num_tile_filas;
    }

    public void setNum_tile_filas(int num_tile_filas) {
        this.num_tile_filas = num_tile_filas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    
    
    
    
}
