/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Davidas
 */
public class modelo extends ConexionBD {

    public Producto consultaProducto(int id) {
        ConexionBD conexion = new ConexionBD();
        String sql = "SELECT * FROM productos WHERE id = " + id + ";";
        ResultSet resultadoConsulta = conexion.consultarBD(sql);
        Producto prod = new Producto(); //crear producto
        try {
            ArrayList<Producto> Lista = new ArrayList<Producto>();
            while (resultadoConsulta.next()) {
                prod.setId(resultadoConsulta.getInt("id"));
                prod.setNombre(resultadoConsulta.getString("nombre"));
                prod.setCantidad(resultadoConsulta.getInt("cantidad"));
                prod.setCategoria(resultadoConsulta.getString("categoria"));
                prod.setPrecio(resultadoConsulta.getDouble("precio"));
                Lista.add(prod);
            }
            //imprimo el string del metodo toostring de producto
            System.out.println(Lista.get(0));
        } catch (SQLException ex) {
            System.out.println("Fallo el recorrido, intente solucionar el BD");
        }
        return prod;
    }

    public ArrayList<Producto> ListarProductos() {
        ConexionBD conexion = new ConexionBD();
        ArrayList<Producto> Lista = new ArrayList<Producto>();
        String sql = "SELECT * FROM productos ";
        ResultSet resultadoConsulta = conexion.consultarBD(sql);
        try {
            while (resultadoConsulta.next()) {
                Producto prod = new Producto();
                prod.setId(resultadoConsulta.getInt("id"));
                prod.setNombre(resultadoConsulta.getString("nombre"));
                prod.setCantidad(resultadoConsulta.getInt("cantidad"));
                prod.setCategoria(resultadoConsulta.getString("categoria"));
                prod.setPrecio(resultadoConsulta.getDouble("precio"));
                Lista.add(prod);

            }
            //imprimo el string del metodo toostring de producto
            System.out.println(Lista);
        } catch (SQLException ex) {
            System.out.println("Fallo el recorrido, intente solucionar el BD");
        }
        return Lista;
    }
    //Eliminar producto son casi el mismo metodo con boolean

//    public boolean EliminarProducto(int id) {
//        ConexionBD conexion = new ConexionBD();
//        String sql = "DELETE FROM productos WHERE id = " + id + ";";
//        boolean resultadoEliminar = conexion.borrarBD(sql);
//        if (resultadoEliminar == true) {
//            System.out.println("ELIMINADO CON EXITO");
//            return true;
//        } else {
//            System.out.println("NO SE PUDO ELIMINAR CON EXITO");
//            return false;
//        }
//    }
    public boolean AgregarProducto(String nombre, int cantidad, String categoria, double precio) {
        ConexionBD conexion = new ConexionBD();
        String sql = String.format("INSERT INTO productos(nombre,cantidad,categoria,precio)" + " VALUES('%s',%s,'%s',%s)", nombre, cantidad, categoria, precio);
        boolean resultadoInsertar = conexion.insertarBD(sql);
        if (resultadoInsertar == true) {
            System.out.println("AGREGADO CON EXITO");
            return true;
        } else {
            System.out.println("NO SE PUDO AGREGAR CON EXITO");
            return false;
        }
    }

    public void EliminarProducto(Producto p) {
        ConexionBD conexion = new ConexionBD();
        String sql = "DELETE FROM productos WHERE id = ?";
        conexion.getConnection();
        try {
            PreparedStatement pstm = conexion.getConnection().prepareStatement(sql);
            pstm.setInt(1, p.getId());
            pstm.executeUpdate();
        } catch (SQLException excepcion) {
            System.out.println("Ocurrio un error al eliminar" + excepcion.getMessage());
        }
    }

    public void update(Producto p) {
        ConexionBD conexion = new ConexionBD();
        String sql = "UPDATE productos SET nombre = ?,cantidad = ?,categoria = ?,precio = ? WHERE id =?";
        conexion.getConnection();
        try {
            PreparedStatement pstm = conexion.getConnection().prepareStatement(sql);
            pstm.setString(1, p.getNombre());
            pstm.setInt(2, p.getCantidad());
            pstm.setString(3, p.getCategoria());
            pstm.setDouble(4, p.getPrecio());
            pstm.setInt(5, p.getId());
            pstm.executeUpdate();
        } catch (SQLException excepcion) {
            System.out.println("Ocurrio un error al actualizar" + excepcion.getMessage());
        }
        conexion.cerrarConexion();
    }

    public void insert(Producto p) {
        ConexionBD conexion = new ConexionBD();
        conexion.getConnection();
        String sql = "INSERT INTO productos (nombre,cantidad,categoria,precio) VALUES(?,?,?,?)";
        try{
        PreparedStatement pstm = conexion.getConnection().prepareStatement(sql);
        pstm.setString(1, p.getNombre());
        pstm.setInt(2, p.getCantidad());
        pstm.setString(3, p.getCategoria());
        pstm.setDouble(4, p.getPrecio());
        pstm.executeUpdate();
    }catch(SQLException excepcion){
            System.out.println("Ocurrio un error Insertando " + excepcion.getMessage());
    }conexion.cerrarConexion();}

}
