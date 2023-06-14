/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts_a.Controller;

import uts_a.Dao.PlnDao;
import uts_a.Dao.PlnDaoImpl;
import uts_a.Dao.Koneksi;
import uts_a.Model.Pln;
import uts_a.View.FormPln;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author A416JPO
 */
public class PlnController {
    private FormPln formPln;
    private Pln pln;
    private PlnDao plnDao;
    private Connection con;
    private Koneksi koneksi;
    
    public PlnController(FormPln formPln){
        try {
            this.formPln = formPln;
            plnDao = new PlnDaoImpl();
            con = new Koneksi().getKoneksi();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PlnController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlnController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void bersihForm(){
        formPln.getTxtKodepelanggan().setText("");
        formPln.getTxtNamapelanggan().setText("");   
        formPln.getTxtMeterbulanini().setText("");
        formPln.getTxtMeterbulanlalu().setText("");
        formPln.getTxtTarif().setText("");
        formPln.getTxtPotongan().setText("");
    }
    
    public void insert(){
        try {
            pln = new Pln();
            pln.setKodepelanggan(formPln.getTxtKodepelanggan().getText());
            pln.setNamapelanggan(formPln.getTxtNamapelanggan().getText());
            pln.setMeterbulanini(formPln.getTxtMeterbulanini().getText());
            pln.setMeterbulanlalu(formPln.getTxtMeterbulanlalu().getText());
            pln.setTarif(formPln.getTxtTarif().getText());
            pln.setPotongan(formPln.getTxtPotongan().getText());
            plnDao.insert(con, pln);
            JOptionPane.showMessageDialog(formPln, "Entri OK");
        } catch (Exception ex) {
            Logger.getLogger(PlnController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(){
        try {
            pln = new Pln();
            pln.setKodepelanggan(formPln.getTxtKodepelanggan().getText());
            pln.setNamapelanggan(formPln.getTxtNamapelanggan().getText());
            pln.setMeterbulanini(formPln.getTxtMeterbulanini().getText());
            pln.setMeterbulanlalu(formPln.getTxtMeterbulanlalu().getText());
            pln.setTarif(formPln.getTxtTarif().getText());
            pln.setPotongan(formPln.getTxtPotongan().getText());
            plnDao.update(con, pln);
            JOptionPane.showMessageDialog(formPln, "Update Ok");
        } catch (Exception ex) {
           Logger.getLogger(PlnController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(){
        try {
            plnDao.delete(con, pln);
            JOptionPane.showMessageDialog(formPln, "Delete Ok");
        } catch (Exception ex) {
            Logger.getLogger(PlnController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cari(){
        try {
            String kode = formPln.getTxtKodepelanggan().getText();
            pln = plnDao.getPln(con, kode);
            if(pln != null){
                formPln.getTxtNamapelanggan().setText(pln.getNamapelanggan());
                formPln.getTxtMeterbulanini().setText(pln.getMeterbulanini());
                formPln.getTxtMeterbulanlalu().setText(pln.getMeterbulanlalu());
                formPln.getTxtTarif().setText(pln.getTarif());
                formPln.getTxtPotongan().setText(pln.getPotongan());
            }else {
                JOptionPane.showMessageDialog(formPln, "Data tidak ada");
            }
        } catch (Exception ex) {
            Logger.getLogger(PlnController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tampil(){
        try {
            DefaultTableModel tabel = (DefaultTableModel) formPln.getTblPln().getModel();
            tabel.setRowCount(0);
            List<Pln> list = plnDao.getAllPln(con);
            for (Pln pelanggan1 : list) {
                Object[] row = {
                    pelanggan1.getKodepelanggan(),
                    pelanggan1.getNamapelanggan(),
                    pelanggan1.getMeterbulanini(),
                    pelanggan1.getMeterbulanlalu(),
                    pelanggan1.getTarif(),
                    pelanggan1.getPotongan()
                    
                };
                tabel.addRow(row);
            }
        } catch (Exception ex) {
            Logger.getLogger(PlnController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}
