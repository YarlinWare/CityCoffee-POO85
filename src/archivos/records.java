package archivos;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import estadojuego.*;

public class records {

     public static BufferedWriter buff = null;
     public static String nombre_jugador= StateLevel_1.nombre_jugador;
     public static int score = 999999;
     public static ArrayList<String> nombres = new ArrayList<String>();
     
     public static void crea_archivo(String path) throws IOException
     {
        try {
        File file = new File(path);
        // Si el archivo no existe lo crea
        if (!file.exists()) {
            file.createNewFile();
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
     }

     public static void modifica_archivo(String path) throws IOException{
            try
            {
                buff = new BufferedWriter(new FileWriter(new File(path),true));
                if(nombres.contains(nombre_jugador))
                    System.out.println("El nombre ya existe");
                else
                {
                    buff.write(nombre_jugador+"\n");
                    buff.write(score+"\n");
                    System.out.println("Nombre Añadido y puntaje añadido");
                }

            }
            catch(FileNotFoundException e)
            {
                System.out.println("Archivo No Encontrado");
            }
            catch(IOException e)
            {
                System.out.println("IO ERROR");
            }
            finally
            {
                try{
                    if(buff != null)
                    {
                        buff.close();
                    }
                }
                catch(IOException e)
                {
                    System.out.println("I/O ERROR");
                }
            }
     }
     public static void almacena_nombres(String path)
     {
         String cadena;
         FileReader f = null;
         try {
             f = new FileReader("records.txt");
         } catch (FileNotFoundException ex) {
             Logger.getLogger(ListRecord.class.getName()).log(Level.SEVERE, null, ex);
         }
         BufferedReader b = new BufferedReader(f);
         try {
             while((cadena = b.readLine())!=null) {
                 String[] partes = cadena.split("\n");
                    for(int i=0; i<partes.length;i++)
                    {
                        nombres.add(partes[i]);

                    }
             }
         } catch (IOException ex) {
             Logger.getLogger(ListRecord.class.getName()).log(Level.SEVERE, null, ex);
         }
         try {
             b.close();
         } catch (IOException ex) {
             Logger.getLogger(ListRecord.class.getName()).log(Level.SEVERE, null, ex);
         }
     }

}
