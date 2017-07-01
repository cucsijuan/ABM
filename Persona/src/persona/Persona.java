/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persona;

import java.util.Calendar;

/**
 *
 * @author nestor
 */
public class Persona
{
    public Persona()
    {
        dni = 0;
        apyn = "";
        try
        {
            fechaNac = new MiCalendar(1, 1, 1900);
        }
        catch (FechaInvalidaException ex)
        {}
        
        sexo = 'M';
    }
    
    
    public Persona(int dni) throws PersonaInvalidaException
    {
        setDni(dni);
        
        apyn = "";
        try
        {
            fechaNac = new MiCalendar(1, 1, 1900);
        }
        catch (FechaInvalidaException ex)
        {}
        
        sexo = 'M';        
    }
    
    
    public Persona(int dni, String apyn, MiCalendar fechaNac, char sexo) throws PersonaInvalidaException
    {
        setDni(dni);
        this.apyn = apyn;
        this.fechaNac = fechaNac;
        this.sexo = sexo;
    }
    
    
    public int getDni() {
        return dni;
    }
    
    
    public void setDni(int dni) throws PersonaInvalidaException
    {
        if(dni <= 0)
            throw new PersonaInvalidaException("El DNI es inválido.");
            
        this.dni = dni;
    }
    
    
    public String getApyn() {
        return apyn;
    }

    public void setApyn(String apyn) throws PersonaInvalidaException
    {
        if(apyn == null)
            throw new PersonaInvalidaException("El Apellido y Nombres no puede ser nulo.");
        
        this.apyn = apyn.trim();
    }
    
    public MiCalendar getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(MiCalendar fechaNac) {
        this.fechaNac = fechaNac;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo)
    {
        this.sexo = sexo;
    }
    
    
    @Override
    public String toString()
    {
        return String.format("%08d", dni) + DELIM + String.format("%-30s", apyn.length() > 30? apyn.substring(0, 30) : apyn) + DELIM + fechaNac + DELIM + sexo;
    }
    
    
    
    public static final String DELIM = "\t";
    
    private int dni;
    private String apyn;
    private MiCalendar fechaNac;
    private char sexo;
}
