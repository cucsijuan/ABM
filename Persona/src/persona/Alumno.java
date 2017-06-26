/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persona;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nestor
 */
public class Alumno extends Persona
{

    public Alumno()
    {
        cantMatAprob = 0;
        promedio = 0;
        try {
            fechaIngr = new MiCalendar(1, 1, 1900);
        } catch (FechaInvalidaException ex)
        {}
    }

    public Alumno(int cantMatAprob, double promedio, MiCalendar fechaIngr, int dni, String apyn, MiCalendar fechaNac, char sexo,char estado) throws PersonaInvalidaException
    {
        super(dni, apyn, fechaNac, sexo);
        this.cantMatAprob = cantMatAprob;
        setPromedio(promedio);
        this.fechaIngr = fechaIngr;
        this.estado = estado;
    }
 

    public int getCantMatAprob() {
        return cantMatAprob;
    }

    public void setCantMatAprob(int cantMatAprob) {
        this.cantMatAprob = cantMatAprob;
    }

    public double getPromedio() {
       
        return promedio;
    }

    public void setPromedio(double promedio) throws AlumnoInvalidoException {
        if(promedio < 0)
            throw new AlumnoInvalidoException("El promedio es erroneo.");
        
        this.promedio = promedio;
    }

    public MiCalendar getFechaIngr() {
        return fechaIngr;
    }

    public void setFechaIngr(MiCalendar fechaIngr) {
        this.fechaIngr = fechaIngr;
    }
    
    
    public char getEstado()
    {
        return estado;
    }
    
    
    public void setEstado(char estado)
    {
        this.estado = estado;
    }
    
    
    @Override
    public String toString()
    {
        return super.toString() + DELIM + promedio + DELIM + fechaIngr + DELIM + String.format("%02d", cantMatAprob) + DELIM + estado; //To change body of generated methods, choose Tools | Templates.
    }// dni 0,apyn 1, promedio 2, fecha ingr 3, cantmatAprob 4,estado 5
 
    
    private int cantMatAprob;
    private double promedio;
    private MiCalendar fechaIngr;    
    private char estado;
}
