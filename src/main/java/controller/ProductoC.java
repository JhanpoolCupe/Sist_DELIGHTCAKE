/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProductoImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Data;
import model.Producto;

@Data
@Named(value = "ProductoC")
@SessionScoped
public class ProductoC implements Serializable {

    private Producto producto;
    ProductoImpl dao;
    private List<Producto> lstProducto;
    private int tipo = 1;

    public ProductoC() {
        producto = new Producto();
        dao = new ProductoImpl();
    }

    public void registrar() throws Exception {
        try {
            dao.guardar(producto);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Registrado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en registar ProductoC/registar: " + e.getMessage());
        }

    }

    public void modificar() throws Exception {
        try {
            dao.modificar(producto);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado", "Registrado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en modificar ProductoC/modificar: " + e.getMessage());
        }
    }

    public void Eliminar(Producto pro) throws Exception {
        try {
            dao.eliminar(pro);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Cambio de estado", "Cambio de estado con exito"));
        } catch (Exception e) {
            System.out.println("Error en cambiar estado ProductoC/estado: " + e.getMessage());
        }
    }

    public void listar() throws Exception {
        try {
            lstProducto = dao.listarTodos(tipo);
        } catch (Exception e) {
            System.out.println("Error en eliminar ProductoC/modificar: " + e.getMessage());
        }
    }
    
    public void limpiar() {
        producto = new Producto();
    }
}
