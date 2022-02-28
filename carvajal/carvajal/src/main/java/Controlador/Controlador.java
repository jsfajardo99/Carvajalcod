/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Producto;
import Vista.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import Modelo.modelo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Davidas
 */
public class Controlador implements ActionListener {

    Vista vista;
    modelo modelo;
    ArrayList<Producto> lista;

    public Controlador(Vista vista, modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        iniciarvista();
    }

    public void iniciarvista() {
        vista.setVisible(true);
        vista.jButtongGuardar.addActionListener(this);
        vista.jButtonConsultar.addActionListener(this);
        vista.jButtonActualizar.addActionListener(this);
        vista.jButtonEliminar.addActionListener(this);
        vista.jTabladeProductos.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int posicion = vista.jTabladeProductos.getSelectedRow();
                Producto p = lista.get(posicion);
                vista.jTextFieldIdC.setText(String.valueOf(p.getId()));
                vista.jTextFieldNombreC.setText(p.getNombre());
                vista.jTextFieldCantidadC.setText(String.valueOf(p.getCantidad()));
                vista.jComboBoxCategoriaC.setSelectedItem(p.getCategoria());
                vista.jTextFieldPrecioC.setText(String.valueOf(p.getPrecio()));
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        LlenarTabla();
    }

    public void LlenarTabla() {
        this.lista = modelo.ListarProductos();
        String[] columnas = new String[]{"Id", "Nombre", "Cantidad", "Categoria", "Precio"};
        Object[][] filas = new Object[][]{};
        DefaultTableModel Tabla = new DefaultTableModel(filas, columnas) {
            @Override
            public boolean isCellEditable(int rowIndex, int ColumIndex) {
                return false;
            }
        };
        for (int i = 0; i < lista.size(); i++) {
            Producto p = lista.get(i);
            Object[] fila1 = new Object[]{p.getId(), p.getNombre(), p.getCantidad(), p.getPrecio(), p.getPrecio()};
            Tabla.addRow(fila1);
        }
        vista.jTabladeProductos.setModel(Tabla);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.jButtongGuardar) {
            String nombre = vista.jTextFieldnombre1.getText();
            int cantidad = Integer.parseInt(vista.jTextFieldCantidad.getText());
            double precio = Double.parseDouble(vista.jTextFieldPrecio.getText());
            String categoria = (String) vista.jComboBoxCategoria.getSelectedItem();
            Producto p = new Producto(nombre, cantidad, categoria, precio);
            modelo.insert(p);
            JOptionPane.showMessageDialog(vista, "Producto Agregado");
            LlenarTabla();
        }
        if(e.getSource() == vista.jButtonEliminar){
            int boton = JOptionPane.showConfirmDialog(vista, "Desea eliminar el producto?");
            if(boton == 0){
            int indice = vista.jTabladeProductos.getSelectedRow();
            Producto p = lista.get(indice);
            modelo.EliminarProducto(p);
            LlenarTabla();
            }  
        }
        if(e.getSource() == vista.jButtonActualizar){
            int indice = vista.jTabladeProductos.getSelectedRow();
            Producto p = lista.get(indice);
            p.setNombre(vista.jTextFieldNombreC.getText());
            p.setCantidad(Integer.parseInt(vista.jTextFieldCantidadC.getText()));
            p.setCategoria((String)vista.jComboBoxCategoriaC.getSelectedItem());
            p.setPrecio(Double.parseDouble(vista.jTextFieldPrecioC.getText()));
            modelo.update(p);
            JOptionPane.showMessageDialog(vista,"Producto Actualizado correctamente ");
            LlenarTabla();  
        }
        if(e.getSource() == vista.jButtonConsultar){
            LlenarTabla();
        }
        
    }
    }
