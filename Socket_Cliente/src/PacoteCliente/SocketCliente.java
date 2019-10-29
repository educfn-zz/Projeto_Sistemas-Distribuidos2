/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PacoteCliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author EduardoCFN
 */
public class SocketCliente {
    
    public static void main(String[] args)
    {
        /*
        1. Estabelecer conexão com o servidor
        2. Trocar mensagens com o servidor
        */
        
        try 
        {
            //Cria conexão entre o cliente e o servidor
            Socket socket;
            socket = new Socket("localhost", 5555);
            
            //criação dos treams de entrada e saída
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());           
            
            String msg = "HELLO";
            output.writeUTF(msg);
            output.flush();
            
            msg = input.readUTF();
            output.flush(); //liberar o buffer para envio
            System.out.println("Resposta: " + msg);
            
            input.close();
            output.close();
            socket.close();
            
        } catch (IOException ex) 
        {
            Logger.getLogger(SocketCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
