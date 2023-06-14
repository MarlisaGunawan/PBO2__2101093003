/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lisa.Controller;

import lisa.Dao.BukuDao;
import lisa.Dao.BukuDaoImpl;
import lisa.Dao.Koneksi;
import lisa.Model.Buku;
import lisa.View.FormBuku;
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
public class BukuController {
    private FormBuku formBuku;
    private Buku buku;
    private BukuDao bukuDao;
    private Connection con;
    private Koneksi koneksi;
    
    public BukuController(FormBuku formBuku){
        try {
            this.formBuku = formBuku;
            bukuDao = new BukuDaoImpl();
            con = new Koneksi().getKoneksi();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BukuController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BukuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void bersihForm(){
        formBuku.getTxtKode().setText("");
        formBuku.getTxtJudul().setText("");   
        formBuku.getTxtPengarang().setText("");
        formBuku.getTxtPenerbit().setText("");
    }
   
    
    public void insert(){
        try {
            buku = new Buku();
            buku.setKodebuku(formBuku.getTxtKode().getText());
            buku.setJudul(formBuku.getTxtJudul().getText());
            buku.setPengarang(formBuku.getTxtPengarang().getText());
            buku.setPenerbit(formBuku.getTxtPenerbit().getText());
            bukuDao.insert(con, buku);
            JOptionPane.showMessageDialog(formBuku, "Entri OK");
        } catch (Exception ex) {
            Logger.getLogger(BukuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(){
        try {
            buku = new Buku();
            buku.setKodebuku(formBuku.getTxtKode().getText());
            buku.setJudul(formBuku.getTxtJudul().getText());
            buku.setPengarang(formBuku.getTxtPengarang().getText());
            buku.setPenerbit(formBuku.getTxtPenerbit().getText());
            bukuDao.update(con, buku);
            JOptionPane.showMessageDialog(formBuku, "Update Ok");
        } catch (Exception ex) {
           Logger.getLogger(BukuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(){
        try {
            bukuDao.delete(con, buku);
            JOptionPane.showMessageDialog(formBuku, "Delete Ok");
        } catch (Exception ex) {
            Logger.getLogger(BukuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cari(){
        try {
            String kode = formBuku.getTxtKode().getText();
            buku = bukuDao.getBuku(con, kode);
            if(buku != null){
                formBuku.getTxtJudul().setText(buku.getJudul());
                formBuku.getTxtPengarang().setText(buku.getPengarang());
                formBuku.getTxtPenerbit().setText(buku.getPenerbit());
            }else {
                JOptionPane.showMessageDialog(formBuku, "Data tidak ada");
            }
        } catch (Exception ex) {
            Logger.getLogger(BukuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tampil(){
        try {
            DefaultTableModel tabel = (DefaultTableModel) formBuku.getTblBuku().getModel();
            tabel.setRowCount(0);
            List<Buku> list = bukuDao.getAllBuku(con);
            for (Buku buku1 : list) {
                Object[] row = {
                    buku1.getKodebuku(),
                    buku1.getJudul(),
                    buku1.getPengarang(),
                    buku1.getPenerbit()
                };
                tabel.addRow(row);
            }
        } catch (Exception ex) {
            Logger.getLogger(BukuController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}
