
package datos;

public abstract class datos {
    protected String archivo;
    
    public datos(String archivo) {
        this.archivo = archivo;
    }
    
    abstract public boolean existeArchivo();

    public String getArchivo() {
        return archivo;
    }
}
