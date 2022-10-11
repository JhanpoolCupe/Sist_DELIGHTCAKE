package validator;

import dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import javax.faces.validator.Validator;
import model.Producto;

@FacesValidator(value = "productoV")
public class ProductoV extends Conexion implements Validator {
    
    public static boolean duplicadoProducto(String producto){
        try {
            PreparedStatement ps = Conexion.conectar().prepareStatement("SELECT NOMPRO FROM PRODUCTO WHERE NOMPRO = '" + producto + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                return true;
            }
            ps.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Error en duplicadoProducto " + e.getMessage());
        }
        return false;
    }
    
     @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String producto = (String) value;
        if (duplicadoProducto(producto)) {
            throw new ValidatorException(new FacesMessage("Duplicado, el Producto ya existe"));
        }
        
    }
}
