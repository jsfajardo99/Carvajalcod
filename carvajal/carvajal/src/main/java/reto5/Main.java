/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto5;

import Controlador.Controlador;
import Modelo.Producto;
import Modelo.modelo;
import Vista.Vista;

/**
 *
 * @author Davidas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Vista vista = new Vista ();
        modelo modelo = new modelo();
        Controlador controlador = new Controlador(vista,modelo); 
    }
}
