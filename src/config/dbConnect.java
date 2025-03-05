/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author User
 */
public class dbConnect {
    private Connection connect;

       // constructor to connect to our database
        public dbConnect(){
            try{
                connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/adlawan", "root", "");
            }catch(SQLException ex){
                    System.out.println("Can't connect to database: "+ex.getMessage());
            }
        }

    public int insertData(String sql, Object... params) {
    int result;
    try {
        PreparedStatement pst = connect.prepareStatement(sql);
        
        for (int i = 0; i < params.length; i++) {
            pst.setObject(i + 1, params[i]);
        }

        pst.executeUpdate();
        System.out.println("Inserted Successfully!");
        pst.close();
        result = 1;
    } catch (SQLException ex) {
        System.out.println("Connection Error: " + ex);
        result = 0;
    }
    return result;
}
     
        public ResultSet getData(String sql) throws SQLException{
            Statement stmt = connect.createStatement();
            ResultSet rst = stmt.executeQuery(sql);
            return rst;
        }
        public void displayData(){
        try{
            dbConnect dbc = new dbConnect();
            ResultSet rs = dbc.getData("SELECT * FROM tbl_user");
            tbl_user.setModel(DbUtils.resultSetToTableModel(rs));
             rs.close();
        }catch(SQLException ex){
            System.out.println("Errors: "+ex.getMessage());

        }

    }
      
}
