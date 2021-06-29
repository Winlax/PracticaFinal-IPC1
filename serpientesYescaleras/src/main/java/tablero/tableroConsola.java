
package tablero;

import fichas.*;
import juego.interfazConsola;

public class tableroConsola extends tablero{
   private String tableroConsola[][];
    private interfazConsola interfaz = new interfazConsola();

    public tableroConsola(int numJugadores, int numSerpientes, int numEscaleras, int tableroDatos[]) {
        super(numJugadores, numSerpientes, numEscaleras, tableroDatos);
        tableroConsola = new String[8][8];
    }
    
    private String mostrarSerpiente() {
        return interfaz.setColorSerpiente("$");
    }
    
    private String mostrarEscalera() {
        return interfaz.setColorEscalera("#");
    }
    
    private String mostrarJugador(int lugar) {
        return (char)27+"[31mJ"+(lugar+1)+(char)27+"[0m";
    }
    
    private String separarJugadoresPorCasilla(int x, int y) {
        Jugador jugadoresCasilla[] = new Jugador[cantidadJugadoresCasilla(x,y)];
        int contador = 0;
        String cadena = "";
        for (int i = 0; i < espacio; i++) {
            if (tablero[x][y][i] instanceof Jugador) {
                jugadoresCasilla[contador] = (Jugador) tablero[x][y][i];
                contador++;
            }
        }
        for (int i = 0; i < jugadoresCasilla.length; i++) {
            cadena += mostrarJugador(jugadoresCasilla[i].getTurno());
            if (i < contador-1) {
                cadena += ",";
            }
        }
        return cadena;
    }
    
    private int cantidadJugadoresCasilla(int x, int y) {
        int cantidad = 0;
        for (int i = 0; i < espacio; i++) {
            if(tablero[x][y][i] instanceof Jugador) {
                cantidad++;
            }
        }
        return cantidad;
    }
   
    private String colorTableroDatos(int x, int y, String casilla) {
        String cadena = "";
        if (x % 2 == 0) {
            y = 7-y;
        }
        if(x == tableroDatos[0] && y == tableroDatos[1]) {
            cadena = interfaz.setColorInicioTablero(casilla);
        } else {
            if (x == tableroDatos[2] && y == tableroDatos[3]) {
                cadena = interfaz.setColorFinalTablero(casilla);
            } else {
                cadena = casilla;
            }
        }
        return cadena;
    }
    
    private void numerosTablero() {
        int contador = 1;
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 != 0) {
                    tableroConsola[i][j] = colorTableroDatos(i,j,Integer.toString(contador));
                    contador++;
                } else {
                    tableroConsola[i][7-j] = colorTableroDatos(i,j,Integer.toString(contador));
                    contador++;
                }
            }
        }
    }
    
    private boolean existeSerpienteOEscalera(int x, int y) {
        boolean existe = false;
        for (int i = 0; i < espacio; i++) {
            if(tablero[x][y][i] instanceof Serpiente) {
                existe = true;
            } else if(tablero[x][y][i] instanceof Escalera) {
                existe = true;
            }
        }
        return existe;
    }
    
    private String agregarSerpienteOEscalera(int x, int y) {
        String cadena = null;
        for (int i = 0; i < espacio; i++) {
            if(tablero[x][y][i] instanceof Serpiente) {
                cadena = mostrarSerpiente();
            } else if(tablero[x][y][i] instanceof Escalera) {
                cadena = mostrarEscalera();
            }
        }
        return cadena;
    }
    
    public void mostrarTableroPorConsola() {
        numerosTablero();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(cantidadJugadoresCasilla(i, j) > 0) {
                    tableroConsola[i][j] += separarJugadoresPorCasilla(i,j);
                }
                if(existeSerpienteOEscalera(i,j)) {
                    tableroConsola[i][j] += agregarSerpienteOEscalera(i,j);
                }
                System.out.print(tableroConsola[i][j]+"\t");
            }
            System.out.println();
        }
    }
    
}
