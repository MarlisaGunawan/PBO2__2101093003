/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lisa.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import lisa.dao.AnggotaDao;
import lisa.dao.AnggotaDaoImpl;
import lisa.dao.Koneksi;
import lisa.model.Anggota;
import lisa.view.FormAnggota;

/**
 *
 * @author A416JPO
 */
public class AnggotaController {
    private FormAnggota formAnggota;
    private Anggota anggota;
    private AnggotaDao anggotaDao;
    private Connection con;
    private Koneksi koneksi;
    
    public AnggotaController(FormAnggota formAnggota){
        try {
            this.formAnggota = formAnggota;
            anggotaDao = new AnggotaDaoImpl();
            koneksi = new Koneksi();
            con = new Koneksi().getKoneksi();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void bersihForm(){
        formAnggota.getTxtKodeanggota().setText("");
        formAnggota.getTxtNamaanggota().setText("");
        formAnggota.getTxtAlamat().setText("");
    }
    
    public void insert() throws Exception{
        try{
            anggota = new Anggota();
            anggota.setKodeanggota(
                    formAnggota.getTxtKodeanggota().getText());
            anggota.setNamaanggota(
                    formAnggota.getTxtNamaanggota().getText());
            anggota.setAlamat(
                    formAnggota.getTxtAlamat().getText());
            anggota.setJeniskelamin(
                    formAnggota.getCboJeniskelamin().getSelectedItem().toString());
            anggotaDao.insert(con, anggota);
            JOptionPane.showMessageDialog(formAnggota, "Entri OK");
        } catch (Exception ex){
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void isiCboJenisKelamin(){
        formAnggota.getCboJeniskelamin().removeAllItems();
        formAnggota.getCboJeniskelamin().addItem("L");
        formAnggota.getCboJeniskelamin().addItem("P");
    }
    
    public void update(){
        try {
            anggota = new Anggota();
            anggota.setKodeanggota(formAnggota.getTxtKodeanggota().getText());
            anggota.setNamaanggota(formAnggota.getTxtNamaanggota().getText());
            anggota.setAlamat(formAnggota.getTxtAlamat().getText());
            anggota.setJeniskelamin(
                    formAnggota.getCboJeniskelamin().getSelectedItem().toString());
            anggotaDao.update(con, anggota);
            JOptionPane.showMessageDialog(formAnggota, "Update OK");
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    public void delete(){
        try {
            anggotaDao.delete(con, anggota);
            JOptionPane.showMessageDialog(formAnggota, "Delete OK");
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    public void cari(){
        try {
            String kode = formAnggota.getTxtKodeanggota().getText(); 
            anggota = anggotaDao.getAnggota(con,kode);
            if (anggota != null){
                formAnggota.getTxtNamaanggota().setText(anggota.getNamaanggota());
                formAnggota.getTxtAlamat().setText(anggota.getAlamat());
                formAnggota.getCboJeniskelamin().setSelectedItem(anggota.getJeniskelamin());
            }
            else{
                JOptionPane.showMessageDialog(formAnggota, "Data Tidak Ada");
            }
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    public void tampil(){
        try {
            DefaultTableModel tabel = (DefaultTableModel) formAnggota.getTblAnggota().getModel();
        tabel.setRowCount(0);
        List<Anggota> list = anggotaDao.getAllAnggota(con);
        for (Anggota anggota1 : list) {
            Object[] row = {
              anggota1.getKodeanggota(),
              anggota1.getNamaanggota(),
              anggota1.getAlamat(),
              anggota1.getJeniskelamin()
            };
            tabel.addRow(row);
        }
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}

