package Archivos;

import java.io.*;
import estadojuego.*;

public class records {

     public static void modifica_archivo(String path) throws IOException{
            BufferedWriter buff = null;
            int score = 999999;
            try
            {
                buff = new BufferedWriter(new FileWriter(new File(path),true));
                buff.write(StateLevel_1.nombre_jugador+"\n");
                buff.write(score+"\n");
                System.out.println("Nombre Añadido y puntaje añadido");

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

}
