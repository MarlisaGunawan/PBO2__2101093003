/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lisa.dao;

import java.sql.Connection;
import lisa.model.Anggota;

/**
 *
 * @author A416JPO
 */
public interface AnggotaDao {
    void insert(Connection con,Anggota anggota)throws Exception;
    
}
