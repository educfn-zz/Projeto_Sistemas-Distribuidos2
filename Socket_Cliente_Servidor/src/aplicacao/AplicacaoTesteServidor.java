package aplicacao;

import PacoteServidor.SocketServidor;

/**
 *
 * @author EduardoCFN
 */
public class AplicacaoTesteServidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
           
        SocketServidor servidor = new SocketServidor();
        
        
        servidor.iniciarServidor(5555);
        
    }
    
}
