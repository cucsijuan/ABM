/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persona;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author nestor
 */
public class MiCalendar extends GregorianCalendar
{

    
    public MiCalendar(int día, int mes, int año) throws FechaInvalidaException
    {
        super(año, mes-1, día);
        
        setLenient(false);
        
        try
        {
            get(DAY_OF_MONTH);
        }
        catch(IllegalArgumentException ex)
        {
            throw new FechaInvalidaException("El día es inválido.");
        }
        
        try
        {
            get(MONTH);
        }
        catch(IllegalArgumentException ex)
        {
            throw new FechaInvalidaException("El mes es inválido.");
        }
        
        try
        {
            get(YEAR);
        }
        catch(IllegalArgumentException ex)
        {
            throw new FechaInvalidaException("El año es inválido.");
        }
    }
    
    
    public MiCalendar(Calendar calendar) throws FechaInvalidaException
    { 
        try {//Juan
            setDía(calendar.get(DAY_OF_MONTH));
            setMes(calendar.get(MONTH) + 1);
            setAño(calendar.get(YEAR));
        } catch (NullPointerException e) {
            throw new FechaInvalidaException("La fecha ingresada es invalida");
        }
 
        
    }
    
    public MiCalendar(Date date) {
        setTimeInMillis(date.getTime());
    }
    
    
    public void setDía(int día) throws FechaInvalidaException
    {
        set(DAY_OF_MONTH, día);
        
        try
        {
            get(DAY_OF_MONTH);
        }
        catch(IllegalArgumentException ex)
        {
            throw new FechaInvalidaException("El día es inválido.");
        }        
    }
    
    
    public int getDía()
    {
        return get(DAY_OF_MONTH);
    }
    
    
    public void setMes(int mes) throws FechaInvalidaException
    {
        set(MONTH, mes-1);
        
        try
        {
            get(MONTH);
        }
        catch(IllegalArgumentException ex)
        {
            throw new FechaInvalidaException("El mes es inválido.");
        }
    }
    
    
    public int getMes()
    {
        return get(MONTH)+1;
    }
    
    
    public void setAño(int año) throws FechaInvalidaException
    {
        set(YEAR, año);
        
        try
        {
            get(YEAR);
        }
        catch(IllegalArgumentException ex)
        {
            throw new FechaInvalidaException("El año es inválido.");
        }        
    }
    
    
    public int getAño()
    {
        return get(YEAR);
    }
    
    
    @Override
    public String toString()
    {
        return String.format("%02d", getDía()) + "/" + String.format("%02d", getMes()) + "/" + getAño();
    }
    
    public Date toDate() {
        return new Date(getTimeInMillis());
    }
    
}
