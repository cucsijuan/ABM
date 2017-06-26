/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author nestor
 */
public abstract class DAO<T, U>
{
    public abstract void insertar(T obj) throws DAOException;
    public abstract void actualizar(T obj) throws DAOException;
    public abstract void eliminar(T obj) throws DAOException;
    public abstract T buscar(U id) throws DAOException;
    public abstract boolean existe(U id) throws DAOException;
    public abstract List<T> getTodos() throws DAOException;
    public abstract List<T> getALtas() throws DAOException;
    public abstract List<T> getBajas() throws DAOException;
}
