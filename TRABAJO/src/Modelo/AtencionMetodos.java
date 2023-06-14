//Herbozo
package Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AtencionMetodos {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarAtencion(Atencion at){
        String sql = "INSERT INTO atencion (dni, nombre, apellido, telefono, correo) VALUES (?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, at.getDni());
            ps.setString(2, at.getNombre());
            ps.setString(3, at.getApellido());
            ps.setString(4, at.getTelefono());
            ps.setString(5, at.getCorreo());
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
    
   public List ListarAtencion(){
       List<Atencion> ListarAt = new ArrayList();
       String sql = "SELECT * FROM atencion";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()) {               
               Atencion at = new Atencion();
               at.setId(rs.getInt("id"));
               at.setDni(rs.getString("dni"));
               at.setNombre(rs.getString("nombre"));
               at.setApellido(rs.getString("apellido"));
               at.setTelefono(rs.getString("telefono"));
               at.setCorreo(rs.getString("correo"));
               ListarAt.add(at);
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return ListarAt;
   }
   
   public boolean EliminarAtencion(int id){
       String sql = "DELETE FROM atencion WHERE id = ?";
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
   
   public boolean ModificarAtencion(Atencion at){
       String sql = "UPDATE atencion SET dni=?, nombre=?, apellido=?, telefono=?, correo=? WHERE id=?";
       try {
           ps = con.prepareStatement(sql);   
           ps.setString(1, at.getDni());
           ps.setString(2, at.getNombre());
           ps.setString(3, at.getApellido());
           ps.setString(4, at.getTelefono());
           ps.setString(5, at.getCorreo());
           ps.setInt(6, at.getId());
           ps.execute();
           return true;
       } catch (SQLException e) {
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
   
   public Atencion Buscaratencion(int dni){
       Atencion at = new Atencion();
       String sql = "SELECT * FROM atencion WHERE dni = ?";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setInt(1, dni);
           rs = ps.executeQuery();
           if (rs.next()) {
               at.setId(rs.getInt("id"));
               at.setNombre(rs.getString("nombre"));
               at.setApellido(rs.getString("apellido"));
               at.setTelefono(rs.getString("telefono"));
               at.setCorreo(rs.getString("correo"));
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return at;
   }
   
}
