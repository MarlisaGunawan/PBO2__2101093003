/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lisa.Controller;

import lisa.Dao.PinjamDao;
import lisa.Dao.PinjamDaoImpl;
import lisa.Dao.Koneksi;
import lisa.Model.Pinjam;
import lisa.View.FormPinjam;
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
public class PinjamController {
    private FormPinjam formPinjam;
    private Pinjam pinjam;
    private PinjamDao pinjamDao;
    private Connection con;
    private Koneksi koneksi;
    
    public PinjamController(FormPinjam formPinjam){
        try {
            this.formPinjam = formPinjam;
            pinjamDao = new PinjamDaoImpl();
            con = new Koneksi().getKoneksi();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PinjamController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PinjamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void bersihForm(){
        formPinjam.getTxtKodeanggota().setText("");
        formPinjam.getTxtKodebuku().setText("");   
        formPinjam.getTxtTglpinjam().setText("");
        formPinjam.getTxtTglkembali().setText("");
    }
   
    
    public void insert(){
        try {
            pinjam = new Pinjam();
            pinjam.setKodeanggota(formPinjam.getTxtKodeanggota().getText());
            pinjam.setKodebuku(formPinjam.getTxtKodebuku().getText());
            pinjam.setTglpinjam(formPinjam.getTxtTglpinjam().getText());
            pinjam.setTglkembali(formPinjam.getTxtTglkembali().getText());
            pinjamDao.insert(con, pinjam);
            JOptionPane.showMessageDialog(formPinjam, "Entri OK");
        } catch (Exception ex) {
            Logger.getLogger(PinjamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(){
        try {
            pinjam = new Pinjam();
            pinjam.setKodeanggota(formPinjam.getTxtKodeanggota().getText());
            pinjam.setKodebuku(formPinjam.getTxtKodebuku().getText());
            pinjam.setTglpinjam(formPinjam.getTxtTglpinjam().getText());
            pinjam.setTglkembali(formPinjam.getTxtTglkembali().getText());
            pinjamDao.update(con, pinjam);
            JOptionPane.showMessageDialog(formPinjam, "Update Ok");
        } catch (Exception ex) {
           Logger.getLogger(PinjamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(){
        try {
            pinjamDao.delete(con, pinjam);
            JOptionPane.showMessageDialog(formPinjam, "Delete Ok");
        } catch (Exception ex) {
            Logger.getLogger(PinjamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cari(){
        try {
            String kodeanggota = formPinjam.getTxtKodeanggota().getText();
            String kodebuku = formPinjam.getTxtKodebuku().getText();
            String tglpinjam = formPinjam.getTxtTglpinjam().getText();
            pinjam = pinjamDao.getPinjam(con, kodeanggota, kodebuku, tglpinjam);
            if(pinjam != null){
                formPinjam.getTxtTglkembali().setText(pinjam.getTglkembali());
            }else {
                JOptionPane.showMessageDialog(formPinjam, "Data tidak ada");
            }
        } catch (Exception ex) {
            Logger.getLogger(PinjamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tampil(){
        try {
            DefaultTableModel tabel = (DefaultTableModel) formPinjam.getTblPinjam().getModel();
            tabel.setRowCount(0);
            List<Pinjam> list = pinjamDao.getAllPinjam(con);
            for (Pinjam pinjam : list) {
                Object[] row = {
                    pinjam.getKodeanggota(),
                    pinjam.getKodebuku(),
                    pinjam.getTglpinjam(),
                    pinjam.getTglkembali()
                };
                tabel.addRow(row);
            }
        } catch (Exception ex) {
            Logger.getLogger(PinjamController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}
