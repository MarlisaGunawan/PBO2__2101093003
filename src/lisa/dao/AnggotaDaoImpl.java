/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lisa.dao;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import lisa.model.Anggota;

/**
 *
 * @author A416JPO
 */
public class AnggotaDaoImpl implements AnggotaDao{

    @Override
    public void insert(java.sql.Connection con, Anggota anggota) throws Exception{
       String sql = "Insert into anggota values(?,?,?,?)";
       PreparedStatement ps = con.prepareStatement(sql);
       ps.setString(1, anggota.getKodeanggota());
       ps.setString(2,anggota.getNamaanggota());
       ps.setString(3,anggota.getAlamat());
       ps.setString(4,anggota.getJeniskelamin());
       ps.executeUpdate();
    }
    
}
