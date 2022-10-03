/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EmpleadoImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Empleado;



/**
 *
 * @author cupej
 */
@Named(value = "EmpleadoC")
@SessionScoped
public class EmpleadoC implements Serializable {

    private Empleado empleado;
    private EmpleadoImpl dao;
    private List<Empleado> lstEmpleado;

    public EmpleadoC() {
        empleado = new Empleado();
        dao = new EmpleadoImpl();
    }

    public void registrar() throws Exception {
        try {
            dao.guardar(empleado);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Registrado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en registar EmpleadoC/registar: " + e.getMessage());
        }

    }

    public void modificar() throws Exception {
        try {
            dao.modificar(empleado);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado", "Registrado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en modificar EmpleadoC/modificar: " + e.getMessage());
        }
    }

    public void eliminar(Empleado emp) throws Exception {
        try {
            dao.eliminar(emp);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Eliminado", "Eliminado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en eliminar ProductoC/eliminar: " + e.getMessage());
        }
    }

    public void listar() throws Exception {
        try {
            lstEmpleado = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Error en eliminar EmpleadoC/modificar: " + e.getMessage());
        }
    }

   

    // Getter y Setter

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public EmpleadoImpl getDao() {
        return dao;
    }

    public void setDao(EmpleadoImpl dao) {
        this.dao = dao;
    }

    public List<Empleado> getLstEmpleado() {
        return lstEmpleado;
    }

    public void setLstEmpleado(List<Empleado> lstEmpleado) {
        this.lstEmpleado = lstEmpleado;
    }

    private EmpleadoImpl cambiarEstado(Empleado empleado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
