
package datos;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class leerDatos extends datos{
    
     private int[] tablero = new int[4];
    private int Serpiente[][];
    protected String[] datosLineas;
    
    public leerDatos() {
        super("datos.txt");
    }
    
    public leerDatos(String archivo) {
        super(archivo);
    }
    
    protected int cantidadDeCaracteresDelArchivo() {
        int valor;
        int cantidadChar = 0;
        try {
            FileReader buscar = new FileReader(archivo);
            valor = buscar.read();
            while(valor != -1) {
                cantidadChar++;
                valor = buscar.read();
            }
        buscar.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(leerDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(leerDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantidadChar;
    }
    
    protected String obtenerLinea(int numLinea) {
        int cantidadChar = cantidadDeCaracteresDelArchivo();
        char datos[] = new char[cantidadChar];
        int valor;
        try {
            FileReader buscar = new FileReader(archivo);
            valor = buscar.read();
            int posicion = 0;
            while(valor != -1) {
                datos[posicion] = (char)valor;
                posicion++;
                valor = buscar.read();
            }
            buscar.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(leerDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(leerDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        StringBuffer resultado = new StringBuffer();
        for (int i = 0; i < cantidadChar; i++) {
           resultado.append( datos[i] );
        }
        String datosString = resultado.toString();
        datosLineas = datosString.split("\r\n");
        return datosLineas[numLinea-1];
    }
    
    private int[][] separarDatos(int numLinea) {
        String linea = obtenerLinea(numLinea);
        String datosPosicion[] = linea.split("\\|");
        int posicion = 0;
        String datosI[][] = new String[datosPosicion.length][];
        int datos[][] = new int[datosPosicion.length][10];
        
        for (int i = 0; i < datosPosicion.length; i++) {
            datosI[i] = datosPosicion[i].split("<|>|,");
        }
        for (int i = 0; i < datosI.length; i++) {
            for (int j = 0; j < datosI[i].length; j++) {
                if (!datosI[i][j].isEmpty()) {
                   datos[i][posicion] = Integer.parseInt(datosI[i][j]);
                   posicion++;
                }
            }
            posicion = 0;
        }
        return datos;
    }
    
    public boolean existeArchivo() {
        File archivo = new File(this.archivo);
        return archivo.exists();
    }
    
    public int[] getDatosTablero() {
        return separarDatos(1)[0];
    }
    
    public int[][] getDatosSerpientes() {
        return separarDatos(2);
    }
    
    public int[][] getDatosEscalera() {
        return separarDatos(3);
    }
}
