/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lisa.Dao;

import lisa.Model.Pinjam;
import java.util.List;
import java.sql.Connection;
/**
 *
 * @author A416JPO
 */
public interface PinjamDao {
    public void insert(Connection con, Pinjam pinjam) throws Exception;
    public void update(Connection con, Pinjam pinjam) throws Exception;
    public void delete(Connection con, Pinjam pinjam) throws Exception;
    public Pinjam getPinjam(Connection con, String kodeanggota, String kodebuku, String tglpinjam) throws Exception;
    public List<Pinjam> getAllPinjam(Connection con) throws Exception;
}
