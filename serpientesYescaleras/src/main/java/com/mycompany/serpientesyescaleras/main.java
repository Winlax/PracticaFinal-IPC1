package com.mycompany.serpientesyescaleras;

import java.awt.*;
import juego.consola;
import javax.swing.*;

public class main {

    public static void main(String[] args) {
        new ventana();
        consola consola = new consola();
        consola.iniciarJuego();
    }

}

class ventana extends JFrame {

    public ventana() {
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;

        setSize(width / 2, height / 2);
        setLocationRelativeTo(null);
        setTitle("Escaleras y Serpientes"); 
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
}
