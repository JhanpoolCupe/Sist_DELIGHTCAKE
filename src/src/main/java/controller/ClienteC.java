package controller;

import dao.ClienteImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Cliente;
import lombok.Data;
import org.primefaces.component.export.ExcelOptions;
import org.primefaces.component.export.PDFOptions;
import org.primefaces.component.export.PDFOrientationType;
import services.EmailS;

@Data

@Named(value = "ClienteC")
@SessionScoped
public class ClienteC implements Serializable {

    private Cliente cliente;
    private ClienteImpl dao;
    private List<Cliente> lstCliente;
    private int tipo = 1;
    private PDFOptions pdf;
    private ExcelOptions xls;

    public ClienteC() {
        cliente = new Cliente();
        dao = new ClienteImpl();
        lstCliente = new ArrayList<>();
        opcionesPersonalizacion();
    }

    public void registrar() throws Exception {
        try {
            dao.guardar(cliente);
            EmailS.enviarEmail(cliente);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Registrado con éxito"));
        } catch (Exception e) {
            Logger.getLogger(ClienteC.class.getName()).log(Level.SEVERE, "Error en registar ClienteC/registar: ", e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(cliente);
//            EmailS.enviarPwdCliente(cliente);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado", "Registrado con éxito"));
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error en modificar ClienteC/modificar: ", e.getMessage());
        } finally {

        }
    }

    public void eliminar() throws Exception {
        try {
            dao.eliminar(cliente);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Eliminado", "Eliminado con éxito"));
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error en eliminar ClienteC/eliminar: ", e.getMessage());
        } finally {

        }
    }

    public void restaurar() throws Exception {
        try {
            dao.restaurar(cliente);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Restaurado", "Restaurado con éxito"));
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error en eliminar ClienteC/restaurar: ", e.getMessage());
        } finally {

        }
    }

    public void listar() {
        try {
            lstCliente = dao.listarTodos(tipo);
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error en restaurar ClienteC/listar: ", e.getMessage());
        }
    }

    public void limpiar() {
        cliente = new Cliente();
    }

    public void opcionesPersonalizacion() {
        xls = new ExcelOptions();
        xls.setFacetBgColor("#19C7FF");
        xls.setFacetFontSize("10");
        xls.setFacetFontColor("#FFFFFF");
        xls.setFacetFontStyle("BOLD");
        xls.setCellFontColor("#000000");
        xls.setCellFontSize("8");
        xls.setFontName("Verdana");

        pdf = new PDFOptions();
        pdf.setFacetBgColor("#19C7FF");
        pdf.setFacetFontColor("#000000");
        pdf.setFacetFontStyle("BOLD");
        pdf.setCellFontSize("12");
        pdf.setFontName("Reporte");
        pdf.setOrientation(PDFOrientationType.LANDSCAPE);
    }

    @PostConstruct
    public void construir() {
        listar();
    }
}
