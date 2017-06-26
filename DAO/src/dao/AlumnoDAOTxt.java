/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persona.Alumno;
import persona.FechaInvalidaException;
import persona.MiCalendar;
import persona.Persona;
import persona.PersonaInvalidaException;

/**
 *
 * @author nestor
 */
public class AlumnoDAOTxt extends DAO<Alumno, Integer> {

    public AlumnoDAOTxt(File archivo) throws FileNotFoundException {
        raf = new RandomAccessFile(archivo, "rws");
    }

    @Override
    public void insertar(Alumno alu) throws DAOException {
        if (existe(alu.getDni())) {
            throw new DAOException("El alumno con DNI " + alu.getDni() + " ya existe.");
        }

        String linea = alu.toString() + System.lineSeparator();

        try {
            raf.seek(raf.length());
            raf.writeBytes(linea);
        } catch (IOException ex) {
            Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("No se pudo insertar: " + ex.getMessage());
        }
    }

    @Override
    public void actualizar(Alumno obj) throws DAOException {
        try {

            Long posAnt = 0L;

            raf.seek(0);

            String[] campos;
            String linea;

            while ((linea = raf.readLine()) != null) {

                campos = linea.split(Persona.DELIM);

                if (Integer.parseInt(campos[0]) == obj.getDni()) {

                    raf.seek(posAnt);
                    raf.writeBytes(obj.toString() + System.lineSeparator());
                    return;
                    // dni 0,apyn 1,fechanac 2,sexo 3, promedio 4, fecha ingr 5, cantmatAprob 6,estado 7
                }

                posAnt = raf.getFilePointer();
            }

        } catch (IOException ex) {
            Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminar(Alumno obj) throws DAOException {
        obj.setEstado('B');
        actualizar(obj);

    }

    @Override
    public Alumno buscar(Integer id) throws DAOException {

        try {
            raf.seek(0);
        } catch (IOException ex) {
            Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
        }

        String[] campos;
        String linea;

        try {
            while ((linea = raf.readLine()) != null) {
                campos = linea.split(Persona.DELIM);

                if (Integer.parseInt(campos[0]) == id) {

                    DateFormat df;
                    df = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar cal = Calendar.getInstance();
                    Calendar cal2 = Calendar.getInstance();
                    cal.setTime(df.parse(campos[5]));
                    cal2.setTime(df.parse(campos[2]));

                    Alumno alumno;
                    

                    try {

                        alumno = new Alumno(Integer.valueOf(campos[6]), Double.valueOf(campos[4]), new MiCalendar(cal), Integer.valueOf(campos[0]), campos[1], new MiCalendar(cal2), campos[3].charAt(0), campos[7].charAt(0));
                        return alumno;
                    } catch (FechaInvalidaException | PersonaInvalidaException ex) {
                        Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
                        return null;
                    }
                    // dni 0,apyn 1,fechanac 2,sexo 3, promedio 4, fecha ingr 5, cantmatAprob 6,estado 7
                }
            }
        } catch (IOException | ParseException ex) {
            Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    @Override
    public boolean existe(Integer id) throws DAOException {
        try {
            raf.seek(0);

            String[] campos;
            String linea;
            while ((linea = raf.readLine()) != null) {
                campos = linea.split(Persona.DELIM);

                if (Integer.valueOf(campos[0]).intValue() == id.intValue()) {
                    return true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException(ex.getMessage());
        }

        return false;
    }

    @Override
    public List<Alumno> getTodos() {
        try {
            List<Alumno> alus = new ArrayList<>();

            String[] campos;
            String linea;

            raf.seek(0);

            while ((linea = raf.readLine()) != null) {

                campos = linea.split(Persona.DELIM);

                DateFormat df;
                df = new SimpleDateFormat("dd/MM/yyyy");
                Calendar cal = Calendar.getInstance();

                try {
                    cal.setTime(df.parse(campos[5]));
                } catch (ParseException ex) {
                    Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
                }
                Calendar cal2 = Calendar.getInstance();
                try {
                    cal2.setTime(df.parse(campos[2]));
                } catch (ParseException ex) {
                    Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    alus.add(new Alumno(Integer.valueOf(campos[6]), Double.valueOf(campos[4]), new MiCalendar(cal), Integer.valueOf(campos[0]), campos[1], new MiCalendar(cal2), campos[3].charAt(0), campos[7].charAt(0)));
                    // dni 0,apyn 1,fechanac 2,sexo 3, promedio 4, fecha ingr 5, cantmatAprob 6,estado 7
                } catch (PersonaInvalidaException | FechaInvalidaException ex) {
                    Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return alus;
        } catch (IOException ex) {
            Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Alumno> getBajas() {
        try {
            List<Alumno> alus = new ArrayList<>();

            String[] campos;
            String linea;

            raf.seek(0);

            while ((linea = raf.readLine()) != null) {

                campos = linea.split(Persona.DELIM);

                DateFormat df;
                df = new SimpleDateFormat("dd/MM/yyyy");
                Calendar cal = Calendar.getInstance();

                if (campos[7].equals("B")) {
                    try {
                        cal.setTime(df.parse(campos[5]));
                    } catch (ParseException ex) {
                        Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Calendar cal2 = Calendar.getInstance();
                    try {
                        cal2.setTime(df.parse(campos[2]));
                    } catch (ParseException ex) {
                        Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        alus.add(new Alumno(Integer.valueOf(campos[6]), Double.valueOf(campos[4]), new MiCalendar(cal), Integer.valueOf(campos[0]), campos[1], new MiCalendar(cal2), campos[3].charAt(0), campos[7].charAt(0)));
                        // dni 0,apyn 1,fechanac 2,sexo 3, promedio 4, fecha ingr 5, cantmatAprob 6,estado 7
                    } catch (PersonaInvalidaException | FechaInvalidaException ex) {
                        Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            return alus;
        } catch (IOException ex) {
            Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
     @Override
    public List<Alumno> getALtas() {
        try {
            List<Alumno> alus = new ArrayList<>();

            String[] campos;
            String linea;

            raf.seek(0);

            while ((linea = raf.readLine()) != null) {

                campos = linea.split(Persona.DELIM);

                DateFormat df;
                df = new SimpleDateFormat("dd/MM/yyyy");
                Calendar cal = Calendar.getInstance();

                if (campos[7].equals("A")) {
                    try {
                        cal.setTime(df.parse(campos[5]));
                    } catch (ParseException ex) {
                        Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Calendar cal2 = Calendar.getInstance();
                    try {
                        cal2.setTime(df.parse(campos[2]));
                    } catch (ParseException ex) {
                        Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        alus.add(new Alumno(Integer.valueOf(campos[6]), Double.valueOf(campos[4]), new MiCalendar(cal), Integer.valueOf(campos[0]), campos[1], new MiCalendar(cal2), campos[3].charAt(0), campos[7].charAt(0)));
                        // dni 0,apyn 1,fechanac 2,sexo 3, promedio 4, fecha ingr 5, cantmatAprob 6,estado 7
                    } catch (PersonaInvalidaException | FechaInvalidaException ex) {
                        Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            return alus;
        } catch (IOException ex) {
            Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    private RandomAccessFile raf;

    public boolean estaActivo(Integer dni) throws DAOException {
        try {
            raf.seek(0);

            String[] campos;
            String linea;
            while ((linea = raf.readLine()) != null) {
                campos = linea.split(Persona.DELIM);

                if (Integer.valueOf(campos[0]).intValue() == dni.intValue()) {
                    return campos[7].charAt(0) == 'A';
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(AlumnoDAOTxt.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException(ex.getMessage());
        }

        return false;
    }

}
