
package fichas;


public class Fichas {
    protected int posicionX;
    protected int posicionY;
    protected int lugar;

    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }
    
    public void setPosicion(int posicion[]) {
        posicionX = posicion[0];
        posicionY = posicion[1];
    }

    public int getLugar() {
        return lugar;
    }

    public void setLugar(int lugar) {
        this.lugar = lugar;
    }
}
