/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persona;

/**
 *
 * @author nestor
 */
public class PersonaInvalidaException extends Exception
{
    public PersonaInvalidaException(String mensaje)
    {
        super(mensaje);
    }
}
