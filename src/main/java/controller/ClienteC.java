/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ClienteImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Data;
import model.Cliente;
import model.Producto;

@Data
@Named(value = "ClienteC")
@SessionScoped
public class ClienteC implements Serializable {

    private Cliente cliente;
    ClienteImpl dao;
    private List<Cliente> lstCliente;
    private int tipo = 1;
    
    public ClienteC() {
        cliente = new Cliente();
        dao = new ClienteImpl();
    }

    public void registrar() throws Exception {
        try {
            dao.guardar(cliente);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Registrado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en registar ClienteC/registar: " + e.getMessage());
        }

    }

    public void modificar() throws Exception {
        try {
            dao.modificar(cliente);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado", "Registrado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en modificar ClienteC/modificar: " + e.getMessage());
        }
    }

    public void eliminar(Cliente cli) throws Exception {
        try {
            dao.eliminar(cli);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Eliminado", "Eliminado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en eliminar ClienteC/eliminar: " + e.getMessage());
        }
    }

    public void listar() throws Exception {
        try {
            lstCliente = dao.listarTodos(tipo);
        } catch (Exception e) {
            System.out.println("Error en eliminar ClienteC/modificar: " + e.getMessage());
        }
    }

   public void limpiar() {
        cliente = new Cliente();
    }

}

