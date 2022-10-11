/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

public class Empleado {

    private int IDEMP;
    private String NOMEMP;
    private String APEEMP;

    private int DNIEMP;
    private int TELEMP;
    private String ESTEMP;
    private String ROLEMP;
    private List<Empleado> empleado;

    public int getIDEMP() {
        return IDEMP;
    }

    public void setIDEMP(int IDEMP) {
        this.IDEMP = IDEMP;
    }

    public String getNOMEMP() {
        return NOMEMP;
    }

    public void setNOMEMP(String NOMEMP) {
        this.NOMEMP = NOMEMP;
    }

    public String getAPEEMP() {
        return APEEMP;
    }

    public void setAPEEMP(String APEEMP) {
        this.APEEMP = APEEMP;
    }

    public int getDNIEMP() {
        return DNIEMP;
    }

    public void setDNIEMP(int DNIEMP) {
        this.DNIEMP = DNIEMP;
    }

    public int getTELEMP() {
        return TELEMP;
    }

    public void setTELEMP(int TELEMP) {
        this.TELEMP = TELEMP;
    }

    public String getESTEMP() {
        return ESTEMP;
    }

    public void setESTEMP(String ESTEMP) {
        this.ESTEMP = ESTEMP;
    }

    public String getROLEMP() {
        return ROLEMP;
    }

    public void setROLEMP(String ROLEMP) {
        this.ROLEMP = ROLEMP;
    }

    public List<Empleado> getEmpleado() {
        return empleado;
    }

    public void setEmpleado(List<Empleado> empleado) {
        this.empleado = empleado;
    }

}
