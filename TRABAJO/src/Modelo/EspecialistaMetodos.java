//
package Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class EspecialistaMetodos {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarEspecialista(Especialista ep){
        String sql = "INSERT INTO especialista (dni, nombre, apellido, especialidad, telefono, correo) VALUES (?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ep.getDni());
            ps.setString(2, ep.getNombre());
            ps.setString(3, ep.getApellido());
            ps.setString(4, ep.getEspecialidad());
            ps.setString(5, ep.getTelefono());
            ps.setString(6, ep.getCorreo());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
   public List ListarEspecialista(){
       List<Especialista> ListaEp = new ArrayList();
       String sql = "SELECT * FROM especialista";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()) {               
               Especialista ep = new Especialista();
               ep.setId(rs.getInt("id"));
               ep.setDni(rs.getString("dni"));
               ep.setNombre(rs.getString("nombre"));
               ep.setApellido(rs.getString("apellido"));
               ep.setEspecialidad(rs.getString("especialidad"));
               ep.setTelefono(rs.getString("telefono"));
               ep.setCorreo(rs.getString("correo"));
               ListaEp.add(ep);
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return ListaEp;
   }
   
   public boolean EliminarEspecialista(int id){
       String sql = "DELETE FROM especialista WHERE id = ?";
       try {
           ps = con.prepareStatement(sql);
           ps.setInt(1, id);
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }finally{
           try {
               con.close();
           } catch (SQLException ex) {
               System.out.println(ex.toString());
           }
       }
   }
   
   public boolean ModificarEspecialista(Especialista ep){
       String sql = "UPDATE especialista SET dni=?, nombre=?, apellido=?, especialidad=?, telefono=?, correo=? WHERE id=?";
       try {
           ps = con.prepareStatement(sql);   
           ps.setString(1, ep.getDni());
           ps.setString(2, ep.getNombre());
           ps.setString(3, ep.getApellido());
           ps.setString(4, ep.getEspecialidad());
           ps.setString(5, ep.getTelefono());
           ps.setString(6, ep.getCorreo());
           ps.setInt(7, ep.getId());
           ps.execute();
           return true;
       } catch (SQLException e){       
           System.out.println(e.toString());
           return false;
       }finally{
           try {
               con.close();
           } catch (SQLException e) {
               System.out.println(e.toString());
           }
       }
   }
   
   public Especialista BuscarEspecialista(int dni){
       Especialista ep = new Especialista();
       String sql = "SELECT * FROM especialista WHERE dni = ?";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setInt(1, dni);
           rs = ps.executeQuery();
           if (rs.next()) {
                ep.setId(rs.getInt("id"));
                ep.setNombre(rs.getString("nombre"));
                ep.setApellido(rs.getString("apellido"));
                ep.setEspecialidad(rs.getString("especialidad"));
                ep.setTelefono(rs.getString("telefono"));
                ep.setCorreo(rs.getString("correo"));
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return ep;
   }
   
}
