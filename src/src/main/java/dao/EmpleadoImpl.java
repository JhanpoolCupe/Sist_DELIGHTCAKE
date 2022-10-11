package dao;

import java.sql.PreparedStatement;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Empleado; 

public class EmpleadoImpl extends Conexion implements ICRUD<Empleado> {

    @Override
    public void guardar(Empleado empleado) throws Exception {
        try {
            String sql = "insert into EMPLEADO" + " (NOMEMP,APEEMP,DNIEMP,TELEMP, ESTEMP, ROLEMP)" + 
                    " values (?,?,?,?,?,?) ";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, empleado.getNOMEMP());
            ps.setString(2, empleado.getAPEEMP());
            ps.setInt(3, empleado.getDNIEMP());
            ps.setInt(4, empleado.getTELEMP());
            ps.setString(5, empleado.getESTEMP());
            ps.setString(6, empleado.getROLEMP());
            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            System.out.println("Error en EmpleadoImpl/registrar: " + e.getMessage());
        }
    }

    @Override
    public void modificar(Empleado empleado) throws Exception {
        try {
            String sql = "update EMPLEADO set NOMEMP=?,APEEMP=?,DNIEMP=?,TELEMP=?, ESTEMP=?,ROLEMP=? where IDEMP=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, empleado.getNOMEMP());
            ps.setString(2, empleado.getAPEEMP());
            ps.setInt(3, empleado.getDNIEMP());
            ps.setInt(4, empleado.getTELEMP());
            ps.setString(5, empleado.getESTEMP());
            ps.setString(6, empleado.getROLEMP());
            ps.setInt(7, empleado.getIDEMP());
            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            System.out.println("Error en EmpleadoImpl/modificar: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Empleado empleado) throws Exception {
        try {
            String sql = "delete from EMPLEADO where IDEMP=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, empleado.getIDEMP());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error en EmpleadoImpl/Cambiar Estado: " + e.getMessage());
        }
    }

//    
//        public void cambiarEstado(Empleado empleado) throws Exception {
//        try {
//            String sql = "update EMPLEADO set ESTEMP=? where IDEMP=?";
//            PreparedStatement ps = this.conectar().prepareStatement(sql);
//            ps.setString(1, empleado.getESTEMP());
//            ps.setInt(2, empleado.getIDEMP());
//            ps.executeUpdate();
//            ps.close();
//        } catch (Exception e) {
//            System.out.println("Error en PacienteImpl/cambiarEstado: " + e.getMessage());
//        }
//    }


   
    public List<Empleado> listarTodos() throws Exception {
        List<Empleado> lista = new ArrayList<>();
        ResultSet rs;
        String sql = "Select * from EMPLEADO where ESTEMP='A' order by IDEMP desc";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Empleado emp = new Empleado();
                emp.setIDEMP(rs.getInt("IDEMP"));
                emp.setNOMEMP(rs.getString("NOMEMP"));
                emp.setAPEEMP(rs.getString("APEEMP"));
                emp.setDNIEMP(rs.getInt("DNIEMP"));
                emp.setTELEMP(rs.getInt("TELEMP"));
                emp.setESTEMP(rs.getString("ESTEMP"));
                emp.setROLEMP(rs.getString("ROLEMP"));
                lista.add(emp);

            }
        } catch (Exception e) {
            System.out.println("Error al listar todos)");
        } finally {
            this.cerrar();
        }
        return lista;
    }

    @Override
    public void restaurar(Empleado arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Empleado> listarTodos(int arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}