/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PacoteCliente;

import Pacote_Util.Mensagem;
import Pacote_Util.Status;
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
            
            //Para teste
            System.out.println("Enviando mensagem...");
            
            /*
            Protocolo
            HELLO
            nome : String
            
            HELLOREPLY
            Ok, ERRO, PARAMERROR
            mensagem : String
            
            */
            
            
            Mensagem m = new Mensagem("HELLO");
            m.setStatus(Status.SOLICITACAO);
            m.setParam("nome", "Eduardo");
            m.setParam("sobrenome", "Dipp");
                  
            output.writeObject(m);
            output.flush(); //liberar o buffer para envio
            
            System.out.println("Mensagem " + m + "\n enviada.");
            
            m = (Mensagem) input.readObject();
            System.out.println("Resposta: " + m);
            
            if(m.getStatus()  == Status.OK)
            {
                String resposta = (String) m.getParam("mensagem");
                System.out.println("Mensagem: \n" + resposta);
            }else
            {
                System.out.println("Erro: " + m.getStatus());
            }
            
            input.close();
            output.close();
            socket.close();
            
        } catch (IOException ex) 
        {
            Logger.getLogger(SocketCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro de cast: " + ex.getMessage());
            Logger.getLogger(SocketCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
