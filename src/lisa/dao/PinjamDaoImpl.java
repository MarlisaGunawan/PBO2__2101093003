/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lisa.Dao;

import lisa.Model.Pinjam;
import java.sql.Connection;
import java.sql.PreparedStatement; 
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author A416JPO
 */
public class PinjamDaoImpl implements PinjamDao{
    public void insert(Connection con, Pinjam pinjam) throws Exception{
        String sql = "insert into peminjaman values(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pinjam.getKodeanggota());
        ps.setString(2, pinjam.getKodebuku());
        ps.setString(3, pinjam.getTglpinjam());
        ps.setString(4, pinjam.getTglkembali());
        ps.executeUpdate();
    }

    @Override
    public void update(Connection con, Pinjam pinjam) throws Exception {
        String sql = 
                "update peminjaman set tglkembali = ? where kodeanggota = ? and kodebuku = ? and tglpinjam = ? ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pinjam.getTglkembali());
        ps.setString(2, pinjam.getKodeanggota());
        ps.setString(3, pinjam.getKodebuku());
        ps.setString(4, pinjam.getTglpinjam());
        ps.executeUpdate();
    }

    @Override
    public void delete(Connection con, Pinjam pinjam) throws Exception {
        String sql = "delete from peminjaman " + "where kodeanggota = ? and kodebuku = ? and tglpinjam = ? ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pinjam.getKodeanggota());
        ps.setString(2, pinjam.getKodebuku());
        ps.setString(3, pinjam.getTglpinjam());
        ps.executeUpdate();
    }

    @Override
    public Pinjam getPinjam(Connection con, String kodeanggota, String kodebuku, String tglpinjam) throws Exception {
        String sql = "select * from peminjaman where kodeanggota = ? and kodebuku = ? and tglpinjam = ? ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kodeanggota);
        ps.setString(2, kodebuku);
        ps.setString(3, tglpinjam);
        ResultSet rs = ps.executeQuery();
        Pinjam pinjam = null;
        if (rs.next()){
            pinjam = new Pinjam();
            pinjam.setKodeanggota(rs.getString(1));
            pinjam.setKodebuku(rs.getString(2));
            pinjam.setTglpinjam(rs.getString(3));
            pinjam.setTglkembali(rs.getString(4));
        }
        return pinjam;
    }

    @Override
    public List<Pinjam> getAllPinjam(Connection con) throws Exception {
        String sql = "select * from peminjaman";
        PreparedStatement ps = con.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        List<Pinjam> list = new ArrayList<>();
        Pinjam pinjam = null;
        while (rs.next()) {
            pinjam = new Pinjam();
            pinjam.setKodeanggota(rs.getString(1));
            pinjam.setKodebuku(rs.getString(2));
            pinjam.setTglpinjam(rs.getString(3));
            pinjam.setTglkembali(rs.getString(4));
            list.add(pinjam);
        }
        return list;
    }
}
