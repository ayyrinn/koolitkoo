/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package koolitkoo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author April
 */
public class Koo {
    
    /**
     * @param args the command line arguments
     */
    
    static final String DB_URL = "jdbc:mysql://localhost/koolitkoo";
    static final String USER = "root";
    static final String PASS = "";
    static final String QUERY = "SELECT * FROM users";
    
    public static void main(String[] args) {
        // TODO code application logic here
        try(
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(QUERY);
                ){
            while(rs.next()){
                System.out.println("ID : " + rs.getInt("id"));
                System.out.println("Nama : " + rs.getString("nama"));
                System.out.println("Email : " + rs.getString("email"));
                System.out.println("NIM : " + rs.getString("nim"));
                System.out.println("===========================");
                System.out.println("");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
}
