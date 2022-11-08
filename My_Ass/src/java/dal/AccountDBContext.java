/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Feature;
import model.Role;

/**
 *
 * @author HP
 */
public class AccountDBContext extends DBContext<Account> {

    @Override
    public void insert(Account model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Account model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Account model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Account get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Account get(String username, String password) {
        Account acc = new Account();
        Role role;
        Feature feature;
        try {
            String statement = "select acc.stdid, acc.lid, r.rid, r.rname, f.fid, f.fname, f.url\n"
                    + "from Account acc left join Role_Account ra on acc.username = ra.username\n"
                    + "						  left join Role r on ra.rid = r.rid\n"
                    + "						  left join Role_Feature rf on r.rid = rf.rid\n"
                    + "						  left join Feature f on rf.fid = f.fid\n"
                    + "						  where acc.username = ? and acc.[password] = ?";
            PreparedStatement pstm = connection.prepareStatement(statement);
            pstm.setString(1, username);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            acc.setStdid(rs.getInt("stdid"));
            acc.setLid(rs.getInt("lid"));
            acc.setUsername(username);
            acc.setPassword(password);

            role = new Role();
            role.setRid(rs.getInt("rid"));
            role.setRname(rs.getString("rname"));

            feature = new Feature();
            feature.setFid(rs.getInt("fid"));
            feature.setFname(rs.getString("fname"));
            feature.setUrl(rs.getString("url"));
            role.getFeatures().add(feature);

            acc.getRoles().add(role);
            while (rs.next()) {
                int rid = rs.getInt("rid");
                if (rid != role.getRid()) {
                    role = new Role();
                    role.setRid(rs.getInt("rid"));
                    role.setRname(rs.getString("rname"));

                    feature = new Feature();
                    feature.setFid(rs.getInt("fid"));
                    feature.setFname(rs.getString("fname"));
                    feature.setUrl(rs.getString("url"));
                    role.getFeatures().add(feature);

                    acc.getRoles().add(role);
                } else {
                    feature = new Feature();
                    feature.setFid(rs.getInt("fid"));
                    feature.setFname(rs.getString("fname"));
                    feature.setUrl(rs.getString("url"));
                    role.getFeatures().add(feature);
                }
            }
            return acc;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Account> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static void main(String[] args) {
        AccountDBContext adb = new AccountDBContext();
        Account acc = adb.get("khanhhd", "123");
        
        System.out.println(acc);
        
    }

}
