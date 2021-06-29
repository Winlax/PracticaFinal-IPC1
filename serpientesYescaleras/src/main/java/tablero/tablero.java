
package tablero;

import fichas.*;
import juego.interfazConsola;

public class tablero {

    protected Fichas tablero[][][];
    protected int tableroDatos[];
    protected Jugador jugadores[];
    protected Escalera escaleras[];
    protected Serpiente serpientes[];
    protected int espacio;
    private interfazConsola interfaz = new interfazConsola();

    public tablero(int numJugadores, int numSerpientes, int numEscaleras, int tableroDatos[]) {
        espacio = numJugadores + 1;
        tablero = new Fichas[8][8][espacio];
        escaleras = new Escalera[numEscaleras];
        serpientes = new Serpiente[numSerpientes];
        jugadores = new Jugador[numJugadores];
        this.tableroDatos = tableroDatos;
        vaciarTablero();
    }

    private void vaciarTablero() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < espacio; k++) {
                    tablero[i][j][k] = new Vacia(i, j, k);
                }
            }
        }
    }

    private int lugarVacio(int x, int y) {
        int lugar = -1;
        for (int i = 0; i < espacio; i++) {
            if (tablero[x][y][i] instanceof Vacia) {
                lugar = i;
                break;
            }
        }
        return lugar;
    }


    public void agregarJugador(String nombre, int turno) {
        int xi = tableroDatos[0];
        int yi = tableroDatos[1];
        int xf = tableroDatos[2];
        int yf = tableroDatos[3];
        int lugar = lugarVacio(xi, yi);
        this.jugadores[turno] = new Jugador(nombre, lugar, xi, yi, xf, yf);
        tablero[xi][yi][lugar] = this.jugadores[turno];
    }

    public void agregarEscalera(int xi, int yi, int xf, int yf, int turno) {
        int lugar = lugarVacio(xi, yi);
        this.escaleras[turno] = new Escalera(xi, yi, xf, yf);
        tablero[xi][yi][lugar] = this.escaleras[turno];
    }

    public void agregarSerpiente(int xi, int yi, int xf, int yf, int turno) {
        int lugar = lugarVacio(xi, yi);
        this.serpientes[turno] = new Serpiente(xi, yi, xf, yf);
        tablero[xi][yi][lugar] = this.serpientes[turno];
    }

    public void moverJugador(int turno, int movimientos) {
        int xVieja = jugadores[turno].getPosicionX();
        int yVieja = jugadores[turno].getPosicionY();
        int lVieja = jugadores[turno].getLugar();
        int lugar;
        jugadores[turno].mover(movimientos);
        if (!((jugadores[turno].getPosicionX() == xVieja) && (jugadores[turno].getPosicionY() == yVieja))) {
            if (existeEscalera(jugadores[turno].getPosicionX(), jugadores[turno].getPosicionY())) {
                jugadores[turno].setPosicion(moverJugadorEscalera(jugadores[turno].getPosicionX(), jugadores[turno].getPosicionY()));
                interfaz.mostrarAviso("El jugador(" + (turno + 1) + ") llamado " + jugadores[turno].getNombre()
                        + " ha caido en una escalera y ha subido a la posicion: "
                        + jugadores[turno].getPosicionX() + ", " + jugadores[turno].getPosicionY());
            }
            if (existeSerpiente(jugadores[turno].getPosicionX(), jugadores[turno].getPosicionY())) {
                jugadores[turno].setPosicion(moverJugadorSerpiente(jugadores[turno].getPosicionX(), jugadores[turno].getPosicionY()));
                interfaz.mostrarAviso("El jugador(" + (turno + 1) + ") llamado " + jugadores[turno].getNombre()
                        + " ha caido en una serpiente y ha bajado a la posicion: "
                        + jugadores[turno].getPosicionX() + ", " + jugadores[turno].getPosicionY());
            }
            lugar = lugarVacio(jugadores[turno].getPosicionX(), jugadores[turno].getPosicionY());
            jugadores[turno].setLugar(lugar);
            tablero[xVieja][yVieja][lVieja] = new Vacia(xVieja, yVieja, lVieja);
            tablero[jugadores[turno].getPosicionX()][jugadores[turno].getPosicionY()][lugar] = jugadores[turno];
        }
    }

    private boolean existeSerpiente(int x, int y) {
        boolean existe = false;
        for (int i = 0; i < serpientes.length; i++) {
            if (serpientes[i].getPosicionX() == x && serpientes[i].getPosicionY() == y) {
                existe = true;
            }
        }
        return existe;
    }

    private int[] moverJugadorSerpiente(int x, int y) {
        int posicion[] = new int[2];
        for (int i = 0; i < serpientes.length; i++) {
            if (serpientes[i].getPosicionX() == x && serpientes[i].getPosicionY() == y) {
                posicion[0] = serpientes[i].getIrX();
                posicion[1] = serpientes[i].getIrY();
            }
        }
        return posicion;
    }

    private boolean existeEscalera(int x, int y) {
        boolean existe = false;
        for (int i = 0; i < escaleras.length; i++) {
            if (escaleras[i].getPosicionX() == x && escaleras[i].getPosicionY() == y) {
                existe = true;
            }
        }
        return existe;
    }

    private int[] moverJugadorEscalera(int x, int y) {
        int posicion[] = new int[2];
        for (int i = 0; i < escaleras.length; i++) {
            if (escaleras[i].getPosicionX() == x && escaleras[i].getPosicionY() == y) {
                posicion[0] = escaleras[i].getIrX();
                posicion[1] = escaleras[i].getIrY();
            }
        }
        return posicion;
    }

    public int getEspacio() {
        return espacio;
    }

    public Fichas[][][] getTablero() {
        return tablero;
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }

    public Escalera[] getEscaleras() {
        return escaleras;
    }

    public Serpiente[] getSerpientes() {
        return serpientes;
    }
}
