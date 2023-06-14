/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts_a.Dao;

import uts_a.Model.Pln;
import java.util.List;
import java.sql.Connection;

/**
 *
 * @author USER
 */
public interface PlnDao {
    public void insert(Connection con, Pln pln) throws Exception;
    public void update(Connection con, Pln pln) throws Exception;
    public void delete(Connection con, Pln pln) throws Exception;
    public Pln getPln(Connection con, String kode) throws Exception;
    public List<Pln> getAllPln(Connection con) throws Exception;
}