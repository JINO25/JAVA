/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testexam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class DB {
    public Connection con=null;
    private String uid="root";
    private String pwd ="0834023573Dat@@";
    
    private String url="jdbc:mysql://localhost:3306/test";
    
    public DB(){
        this.Open();
    }
    
     public void Open(){
        try {
            con = DriverManager.getConnection(url, uid, pwd);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public int addData(String name,String date, String email, String type, String detail) throws SQLException, ParseException{
         String sql="insert into itrequest(ReqName,ReqDate,ReqEmail,ReqType,ReqDetails) values(?,?,?,?,?)";
         PreparedStatement ptm = con.prepareStatement(sql);
         
         SimpleDateFormat Dateformat = new SimpleDateFormat("yyyy/MM/dd");
         java.util.Date Date =Dateformat.parse(date);
         java.sql.Date sqlDate = new java.sql.Date(Date.getTime());
         
         ptm.setString(1, name);
         ptm.setDate(2, sqlDate);
         ptm.setString(3,email);
         ptm.setString(4, type);
         ptm.setString(5,detail);
         int rs = ptm.executeUpdate();
         return rs;
     }
     
     public List<String> search(int id) throws SQLException{
         String sql ="select * from itrequest where ReqID=?";
         List<String> list = new ArrayList<>();
         PreparedStatement pr = con.prepareCall(sql);
         pr.setInt(1, id);
         ResultSet rs = pr.executeQuery();
         while(rs.next()){
             list.add(rs.getString("ReqName"));            
             list.add(rs.getDate("ReqDate").toString());   
             list.add(rs.getString("ReqEmail")); 
             list.add(rs.getString("ReqType"));  
             list.add(rs.getString("ReqDetails"));
         }
         return list;
     }
}
