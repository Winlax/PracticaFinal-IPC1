
package datos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import juego.interfazConsola;

public class rankingDatos extends leerDatos{
    
    private String jugadores[][];
    private String archivo = "ranking.txt";
    private FileWriter fWDatos;
    private PrintWriter escribirDatos;
    private interfazConsola interfaz = new interfazConsola();
    
    public rankingDatos() {
        super("ranking.txt");
        if(!existeArchivo()) {
            try {
                File file = new File(archivo);
                file.createNewFile();
            } catch (IOException ex) {
                interfaz.mostrarError("Error al leer o escribir el ranking.txt");
            }
        }
    }
    
    public void agregarGanador(String nombre) {
        int sumar;
        try {
            if(existeArchivo()) {
                if(existeJugador(nombre)) { 
                    for (int i = 0; i < jugadores.length; i++) {
                        if (jugadores[i][0].equals(nombre)) {
                        sumar = Integer.parseInt(jugadores[i][1]);
                        sumar++;
                        jugadores[i][1] = String.valueOf(sumar);
                        }
                    }
                    crearDatos();
                    close();
                } else {
                    crearDatos();
                    setDatosJugador(nombre,"1");
                    close();
                }
            } else {
                jugadores = new String[1][2];
                jugadores[0][0] = nombre;
                jugadores[0][1] = "1";
                crearDatos();
                close();
            }
        } catch(Exception e) {
            interfaz.mostrarError("Datos corruptos de ranking. Se ha borrado todo y creado denuevo");
            jugadores = new String[1][2];
            jugadores[0][0] = nombre;
            jugadores[0][1] = "1";
            crearDatos();
            close();
        }
    }

    public String[][] tresMejores() {
        int mayor = 0;
        int limite = 0;
        String mejores[][] = new String[3][2];
        String guardar[];
        separarDatos();
        for (int i = 0; i < jugadores.length; i++) {
            mayor = i;
            for (int j = i; j < jugadores.length; j++) {
                if(Integer.parseInt(jugadores[mayor][1]) < Integer.parseInt(jugadores[j][1])) {
                    mayor = j;
                }
            }
            guardar = jugadores[i];
            jugadores[i] = jugadores[mayor];
            jugadores[mayor] = guardar;
        }
        if(jugadores.length < 3) {
            limite = jugadores.length;
        } else {
            limite = 3;
        }
        for (int i = 0; i < limite; i++) {
            mejores[i] = jugadores[i];
        }
        return mejores;
    }

    private void escribirDatos() {
        try {
            fWDatos = new FileWriter(archivo);
        } catch (IOException ex) {
            System.out.println("-ERROR: al escribir archivo: "+ex.getMessage());
        }
        escribirDatos = new PrintWriter(fWDatos);
    }
    
    private void crearDatos() {
        escribirDatos();
        for (int i = 0; i < jugadores.length; i++) {
            setDatosJugador(jugadores[i][0], jugadores[i][1]);
            setLinea();
        }
    }

    private boolean existeJugador(String nombre) {
        boolean existe = false;
        separarDatos();
        for (int i = 0; i < jugadores.length; i++) {
            if(jugadores[i][0].equals(nombre)) {
                existe = true;
            }
        }
        return existe;
    }
    
    private void separarDatos() {
        String linea = obtenerLinea(1);
        jugadores = new String[datosLineas.length][];
        for (int i = 0; i < jugadores.length; i++) {
            linea = obtenerLinea(i+1);
            this.jugadores[i] = linea.split(":");
        }
    }

    private void close() {
        escribirDatos.close();
    }
    
    private void setDatosJugador(String nombre, String ganadas) {
        escribirDatos.append(nombre+":"+ganadas);
    }
    
    private void setLinea() {
       escribirDatos.append("\r\n");
    }
}
