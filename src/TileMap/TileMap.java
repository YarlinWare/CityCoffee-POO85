/*
 * clase encargada de nuestro mapa de tiles
 */
package TileMap;

import coffeecity.PanelJuego;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

/**
 *
 * @author 
 */
public class TileMap {
    //posicion del mapa
    private double x;
    private double y;
    
    //Limites del rectagulo
    private double xmin;
    private double xmax;
    private double ymin;
    private double ymax;
    
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
    
    //variable para los movimientos de càmara
    public double tween;

    public TileMap(int tcelda) {
        this.tamanio_celda=tcelda;
        this.num_filas_pintado=(int)PanelJuego.HEIGHT/tamanio_celda;
        this.num_columnas_pintado=(int)(PanelJuego.WIDTH/tamanio_celda)+3;
        this.tween=0.5;
    }
    
    public void cargarTiles(String nombre) throws IOException{
        BufferedImage tileset= ImageIO.read(this.getClass().getResourceAsStream(nombre));
        this.num_tile_filas=tileset.getHeight()/tamanio_celda;
        this.num_tile_columnas=tileset.getWidth()/tamanio_celda;
        
        System.out.println("num. tile filas"+this.num_tile_filas);
        System.out.println("num. tile filas"+this.num_tile_filas);
        
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
       InputStreamReader entrada=new InputStreamReader(this.getClass().getResourceAsStream(nombre));
       BufferedReader br=new BufferedReader(entrada);
       this.num_map_columnas=Integer.parseInt(br.readLine());
       
       System.out.println("num map columnas"+this.num_map_columnas);
       this.num_map_filas=Integer.parseInt(br.readLine());
       System.out.println("num map filas"+this.num_map_filas);
       
       ancho = this.num_filas_pintado*tamanio_celda;
       alto = this.num_columnas_pintado * tamanio_celda;
       
       this.map=new int[num_map_filas][num_map_columnas];
       
        xmin = 0;
        xmax = PanelJuego.WIDTH-ancho;
        ymin = 0;
        ymax = PanelJuego.HEIGHT - alto;
        
        for(int i=0;i<this.num_map_filas;i++){
            String[] split=br.readLine().split(",");
            System.out.println();
            for(int j=0;j<this.num_map_columnas;j++){                
                int celda=Integer.parseInt(split[j]); 
                System.out.print(celda);
                map[i][j]=celda;
            }
       }
    }
    
    public void setPosicion(double x,double y){
        this.x+=(x-this.x)*tween;
        this.y+=(y-this.y)*tween;
        ajustarArea();
        this.columnaOffset=(int) (-this.x/tamanio_celda);
        this.filaOffset=(int) (-this.y/tamanio_celda);
    }

        
        
    public void ajustarArea(){
        if(x<xmin){x=xmin;}
        if(x<xmax){x=xmax;}
        if(y<ymin){y=ymin;}
        if(y<ymax){x=ymax;}        
    }
    
    public void draw(Graphics2D g2d){
        
        System.out.println("filaOffset->"+this.filaOffset);
        System.out.println("num_filas_pintado"+this.num_filas_pintado);
        System.out.println("columnaOffset->"+this.columnaOffset);
        System.out.println("num_columnas_pintado"+this.num_columnas_pintado);
        
        for(int fila=this.filaOffset;fila<this.filaOffset+this.num_map_filas;fila++){
            if (fila>=num_map_filas) break;
            for(int col=this.columnaOffset;col<this.columnaOffset+this.num_map_columnas;col++){            
                if (col>=num_map_columnas) break;
                int valor=map[fila][col];
                int fila_fila=(int)(valor/this.num_tile_filas);
                int fila_col=(int)(valor%this.num_tile_columnas-1);                
                //if(fila_col>=0){ 
                    g2d.drawImage(
                        tiles[fila_fila][fila_col].getImage(),
                        (int)(this.x+col)*tamanio_celda , 
                        (int)(this.y+fila)*tamanio_celda ,
                        null 
                    );
                //}
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

    public double getTween() {
        return tween;
    }
    /*
    public void setTween(double tween) {
        this.tween = tween;
    }*/
    public void setTween(double d) { tween = d; }
    
    public int getTipo(int x,int y){
        int valor=map[x][y];
        int ffila=(int)(valor/this.num_tile_filas);
        int fcol=(int)(valor%this.num_tile_columnas-1);  
        return tiles[ffila][fcol].getType();
        
    }
    
}
