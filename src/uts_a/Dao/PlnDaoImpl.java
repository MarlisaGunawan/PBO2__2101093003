/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts_a.Dao;

import uts_a.Model.Pln;
import java.sql.Connection;
import java.sql.PreparedStatement; 
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author A416JPO
 */
public class PlnDaoImpl implements PlnDao{

    @Override
    public void insert(Connection con, Pln pln) throws Exception {
        String sql = "insert into pln values(?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pln.getKodepelanggan());
        ps.setString(2, pln.getNamapelanggan());
        ps.setString(3, pln.getMeterbulanini());
        ps.setString(4, pln.getMeterbulanlalu());
        ps.setString(5, pln.getTarif());
        ps.setString(6, pln.getPotongan());
        ps.executeUpdate();
    }

    @Override
    public void update(Connection con, Pln pln) throws Exception {
        String sql = 
                "update pln set namapelanggan=?, meterbulanini=?, meterbulanlalu=?, tarif=?, potongan=? where kodepelanggan=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pln.getNamapelanggan());
        ps.setString(2, pln.getMeterbulanini());
        ps.setString(3, pln.getMeterbulanlalu());
        ps.setString(4, pln.getTarif());
        ps.setString(5, pln.getPotongan());
        ps.setString(6, pln.getKodepelanggan());
        ps.executeUpdate();
    }

    @Override
    public void delete(Connection con, Pln pln) throws Exception {
        String sql = "delete from pln " + "where kodepelanggan = ? ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pln.getKodepelanggan());
        ps.executeUpdate();
    }

    @Override
    public Pln getPln(Connection con, String kode) throws Exception {
        String sql = "select * from pln where kodepelanggan = ? ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kode);
        ResultSet rs = ps.executeQuery();
        Pln pln = null;
        if (rs.next()){
            pln = new Pln();
            pln.setKodepelanggan(rs.getString(1));
            pln.setNamapelanggan(rs.getString(2));
            pln.setMeterbulanini(rs.getString(3));
            pln.setMeterbulanlalu(rs.getString(4));
            pln.setTarif(rs.getString(5));
            pln.setPotongan(rs.getString(6));
        }
        return pln;
    }

    @Override
    public List<Pln> getAllPln(Connection con) throws Exception {
        String sql = "select * from pln";
        PreparedStatement ps = con.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        List<Pln> list = new ArrayList<>();
        Pln pln = null;
        while (rs.next()) {
            pln = new Pln();
            pln.setKodepelanggan(rs.getString(1));
            pln.setNamapelanggan(rs.getString(2));
            pln.setMeterbulanini(rs.getString(3));
            pln.setMeterbulanlalu(rs.getString(4));
            pln.setTarif(rs.getString(5));
            pln.setPotongan(rs.getString(6));
            list.add(pln);
        }
        return list;
    }
    
    
}
