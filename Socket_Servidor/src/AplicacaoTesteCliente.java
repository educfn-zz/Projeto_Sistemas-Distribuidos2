/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import PacoteCliente.SocketCliente;

/**
 *
 * @author EduardoCFN
 */
public class AplicacaoTesteCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
  
        SocketCliente cliente = new SocketCliente();
        
        cliente.enviarServidor("localhost",5555);
        
    }
    
}
