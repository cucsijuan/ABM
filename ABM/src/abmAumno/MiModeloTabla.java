/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abmAumno;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import persona.Alumno;

/**
 *
 * @author nestor
 */
public class MiModeloTabla extends AbstractTableModel
{
    public MiModeloTabla()
    {
        alumnos = new ArrayList<>();
    }
    
    
    void setLista(List<Alumno> alumnos)
    {
        this.alumnos = alumnos;
        fireTableDataChanged();
    }
    
    
    @Override
    public int getRowCount()
    {
        return alumnos.size();
    }
    
    
    @Override
    public int getColumnCount()
    {
        return nomCols.length;
    }
    
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Alumno alu = alumnos.get(rowIndex);
		
        switch(columnIndex)
        {
            case 0: return alu.getDni();
            case 1: return alu.getApyn();
            case 2: return alu.getFechaNac();
            case 3: return alu.getSexo();
            case 4: return alu.getCantMatAprob();
            case 5: return alu.getFechaIngr();
            case 6: return alu.getPromedio();
            case 7: return alu.getEstado();
            default: return "";
        }
    }
    
    
    @Override
    public String getColumnName(int column)
    {
        return nomCols[column];
    }
    
    
    
    private List<Alumno> alumnos;
    private String[] nomCols = {"DNI", "ApYN", "Fecha Nac", "Sexo", "Cant Mat Aprob", "Fecha Ingr", "Promedio", "Estado"};
}
