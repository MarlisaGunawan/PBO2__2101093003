/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lisa.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private Koneksi konekssi;
    
    public AnggotaController(FormAnggota formAnggota){
        try {
            this.formAnggota = new FormAnggota();
            anggotaDao = new AnggotaDaoImpl();
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
    }
    
    public void isiCboJenisKelamin(){
        formAnggota.getCboJeniskelamin().removeAllItems();
        formAnggota.getCboJeniskelamin().addItem("L");
        formAnggota.getCboJeniskelamin().addItem("P");
    }
}
