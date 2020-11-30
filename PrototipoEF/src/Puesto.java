/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*import static Finanzas_Clases.Departamento.Base_de_Datos;
import static Finanzas_Clases.Departamento.Clave;
import static Finanzas_Clases.Departamento.Usuario;
*/import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Langas
 */
public class Puesto 
{
    //Atributos
    JTextField id_Puesto;
    JTextField nombre_Puesto;
    JTextArea descripcion_Puesto;
    JTextField estatus_Puesto;
    JTextField buscar_Puesto;
    JTable tabla_Puesto;

    public Puesto(JTextField id_Puesto, JTextField nombre_Puesto, JTextArea descripcion_Puesto, JTextField estatus_Puesto, JTextField buscar_Puesto, JTable tabla_Puesto) {
        this.id_Puesto = id_Puesto;
        this.nombre_Puesto = nombre_Puesto;
        this.descripcion_Puesto = descripcion_Puesto;
        this.estatus_Puesto = estatus_Puesto;
        this.buscar_Puesto = buscar_Puesto;
        this.tabla_Puesto = tabla_Puesto;
    }
    
    public void Actualizar_Tabla()
    {
        int cantidad = Cantidad_Registros();
        
        
        String[] Departamento_Datos = new String [4];
        try
        {
            Connection cn = DriverManager.getConnection(Base_de_Datos,Usuario,Clave);
            PreparedStatement pst = cn.prepareStatement("select * from puesto");
            
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID Puesto");
            model.addColumn("Nombre Puesto");
            model.addColumn("Descripcion Puesto");
            model.addColumn("Estatus Puesto");
            
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                
                Departamento_Datos[0] = rs.getString("id_puesto");
                Departamento_Datos[1] = rs.getString("nombre_puesto");
                Departamento_Datos[2] = rs.getString("descripcion");
                Departamento_Datos[3] = rs.getString("estatus_puesto");
                
                model.addRow(Departamento_Datos);
                
                tabla_Puesto.setModel(model);
                
            }
            //JOptionPane.showMessageDialog(null, "La cantidad es " + cantidad);
            tabla_Puesto.setModel(model);
        }catch(Exception e)
        {
            System.out.println(e);
        }
                
    }
    
    public int Cantidad_Registros()
    {
        int cant = 0;
        
        try
        {
            Connection cn = DriverManager.getConnection(Base_de_Datos,Usuario,Clave);
            PreparedStatement pst = cn.prepareStatement("select * from puesto");
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next())
            {
                cant++;
            }
         //JOptionPane.showMessageDialog(null, "La cantidad es " + cant);
        }catch(Exception e)
        {
            System.out.println(e);
        }

        
        return cant;
    }
        
    public void Insertar_Puesto()
    {
        try
        {
            Connection  cnIP = DriverManager.getConnection(Base_de_Datos,Usuario,Clave);
            PreparedStatement pstIP = cnIP.prepareStatement("insert into puesto values(?,?,?,?)");
            
            pstIP.setString(1, id_Puesto.getText().trim());
            pstIP.setString(2, nombre_Puesto.getText().trim());
            pstIP.setString(3, descripcion_Puesto.getText().trim());
            pstIP.setString(4, estatus_Puesto.getText().trim());
            
            pstIP.executeUpdate();
            
            id_Puesto.setText("");
            nombre_Puesto.setText("");
            descripcion_Puesto.setText("");
            estatus_Puesto.setText("");
            
            JOptionPane.showMessageDialog(null,"Registro Exitoso");
            
            
            Actualizar_Tabla();

            
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void Modificar_Puesto()
    {
        try
        {
            String ID = buscar_Puesto.getText().trim();

            Connection cn = DriverManager.getConnection(Base_de_Datos,Usuario,Clave);
            PreparedStatement pst = cn.prepareStatement("update puesto set id_puesto = ?, nombre_puesto=?,descripcion = ?, estatus_puesto=? where id_puesto = " + ID);

            pst.setString(1, id_Puesto.getText().trim());
            pst.setString(2, nombre_Puesto.getText().trim());
            pst.setString(3, descripcion_Puesto.getText().trim());
            pst.setString(4, estatus_Puesto.getText().trim());

            pst.executeUpdate();

            buscar_Puesto.setText("");
            JOptionPane.showMessageDialog(null,"Modificacion Exitosa");
            
            Actualizar_Tabla();
            
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void Eliminar_Puesto()
    {
        try
        {
            Connection cn = DriverManager.getConnection(Base_de_Datos,Usuario,Clave);
            PreparedStatement pst = cn.prepareStatement("delete from puesto where id_puesto = ?");

            pst.setString(1, buscar_Puesto.getText().trim());
            pst.executeUpdate();

            id_Puesto.setText("");
            nombre_Puesto.setText("");
            descripcion_Puesto.setText("");
            estatus_Puesto.setText("");

            JOptionPane.showMessageDialog(null,"Eliminacion Exitosa");
            Actualizar_Tabla();
            
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public DefaultTableModel Buscar_Puesto(String Buscar)
    {
        String[] nombre_ColumnasD = {"ID Puesto", "Nombre Puesto","Descripcion Puesto","Estatus Puesto"};
        String[] RegistrosD = new String[4];
        
        DefaultTableModel model = new DefaultTableModel(null,nombre_ColumnasD);
        
        try
        {
            Connection cn = DriverManager.getConnection(Base_de_Datos,Usuario,Clave);
            PreparedStatement pst = cn.prepareStatement("select * from puesto WHERE id_puesto LIKE '%"+Buscar+"%' OR nombre_puesto LIKE '%"+Buscar+"%'");
            ResultSet rs = pst.executeQuery();
            
            while(rs.next())
            {
                RegistrosD[0] = rs.getString("id_puesto");
                RegistrosD[1] = rs.getString("nombre_puesto");
                RegistrosD[2] = rs.getString("descripcion");
                RegistrosD[3] = rs.getString("estatus_puesto");
                
                model.addRow(RegistrosD);
            }
            
        }catch(Exception e)
        {
            System.out.println(e);
        }
        
        return model;
    }
    
    public void Buscar_PuestoF(String Buscar)
    {
        DefaultTableModel model = Buscar_Puesto(Buscar);
        tabla_Puesto.setModel(model);
    }
    
}
