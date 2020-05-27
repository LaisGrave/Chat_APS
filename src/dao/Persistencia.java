/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author Jéssica
 */
public interface Persistencia<T> {
    public int create(T r);
    public List<T> read();
    public boolean update(T r);
    public boolean delet(T r);
}
