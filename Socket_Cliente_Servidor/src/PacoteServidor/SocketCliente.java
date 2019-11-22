package PacoteServidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author EduardoCFN
 */
public class SocketCliente
{
    /* Variaveis que compoem a mensagem customizada:
    * ID - ID do cliente
    * NOME - nome do cliente
    * DATA_N - Data de Nascimento do cliente
    * CPF - CPF do cliente
    * TELEFONE 1
    * TELEFONE 2
    * UF - Unidade Federativa onde vive o cliente
    * ENDERECO - Endereco do cliente
    * CIDADE - Cidade onde vive o cliente
    * NOME_M - Nome da Mae do cliente
    */
    public ArrayList<String> preparaMensagem()
    {
        ArrayList<String> texto = new ArrayList<>();
        
        
        
        return texto;
    }
    
    public ArrayList<String> enviarServidor(String ip, int numeroPorta, ArrayList<String> texto)
    {
        ArrayList<String> respostaServidor = new ArrayList<>();
        
        /*
        1. Estabelecer conexão com o servidor
        2. Trocar mensagens com o servidor
        */
        
        try 
        {
            //Cria conexão entre o cliente e o servidor
            Socket socket;
            socket = new Socket(ip, numeroPorta);
            
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
            m.addString("Teste");
                  
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
        
        return respostaServidor;
        
    }
    
}
