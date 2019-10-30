package PacoteServidor;


import Pacote_Util.Mensagem;
import Pacote_Util.Status;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketServidor {

    /**
     * 1- Criar o servidor de conexões
     * 2- Esperar o um pedido de conexão - Outro processo
     * 2.1- Criar uma nova conexão;
     * 3- Criar streams de entrada e saída;
     * 4- Tratar a conversação entre cliente e servidor (tratar protoclo);
     * 4.1- Fechar socket de comunicação entre servidor/cliente
     * 4.2- Fechar streams de entrada e saída
     * 
     * 5- Voltar para o passo 2, até que finalize o programa;
     * 6- Fechar serverSocket
     */
    
    private ServerSocket serverSocket;
    
    private void criarServerSocket(int porta) throws IOException 
    {
        serverSocket = new ServerSocket(porta);
    }
    
    private Socket esperaConexao() throws IOException
    {
       Socket socket =  serverSocket.accept();
       return socket;
    }
    
    private void fechaSocket(Socket s) throws IOException
    {
        s.close();
    }
    
    private void trataConexao(Socket socket) throws IOException
    {
        //Local onde será aplicado o protocolo da aplicação
        /*
        * 3- Criar streams de entrada e saída;
        * 4- Tratar a conversação entre cliente e servidor (tratar protoclo);
        *
        * 1(Um) Socket para cada cliente de Servidor.
        */
        
        /*
        * O 'ObjectInputStream' serve como um 'decorator' do InputStream, assim
        *como no caso do OutputStream; para poder escrever(write) e ler (read) 
        *o objeto de forma mais fácil, não necessitando assim ficar realizado 
        * sucessivas converssões de Objeto em bytes e vice-versa. O decorator 
        * ira fazer a comunicação com a Stream.
        */
        try 
        {
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        
        /*
          Protocolo
            HELLO
            nome : String
            
            HELLOREPLY
            Ok, ERRO, PARAMERROR
            mensagem : String
            
        */
        /*4 - Tratar a conversação entre cliente e servidor (tratar protocolo)
        */
        System.out.println("Tratando...");
        Mensagem m = (Mensagem) input.readObject();
        String operacao = m.getOperacao();
        Mensagem reply = null;
        
        if (operacao.equals("HELLO"))
        {
            String nome = (String) m.getParam("nome");
            String sobrenome = (String) m.getParam("sobrenome");
            
            reply = new Mensagem("HELLOREPLY");
            
            if( nome == null || sobrenome == null) 
                reply.setStatus( Status.PARAMERROR);
            else
            {
                reply.setStatus( Status.OK );
                reply.setParam( "mensagem", "Hello World, "+ nome + " " + sobrenome );
            }
            
        }
        
        if( operacao.equals("DIV"))
        {
            try
            {
                Integer op1 = (Integer) m.getParam("op1");
                Integer op2 = (Integer) m.getParam("op2");

                //testar os dados
                reply = new Mensagem("DIVREPLY");

                if( op2 == 0)
                {
                    reply.setStatus(Status.DIVZERO);
                }else
                {
                    reply.setStatus(Status.OK);
                    
                    float div = (float) op1/op2;
                    reply.setParam("res", div);
                }
            }catch(Exception e)
            {
                reply = new Mensagem("DIVREPLY");
                reply.setStatus(Status.PARAMERROR);
            }
            
        }
        
        output.writeObject(reply);
        output.flush();
        
        
        //4.2- Fechar streams de entrada e saída
        input.close();
        output.close();
        
        } 
        catch (IOException ex) 
        {
            //Area para o tratamento de falhas na comunicação 
            //para cada cliente(Client) criado.
            System.out.println("Problema no tratamento da conexão com o cliente: " + socket.getInetAddress());
            System.out.println("Erro: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro de cast: " + ex.getMessage());
            Logger.getLogger(SocketServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            //4.1- Fechar socket de comunicação entre servidor/cliente
            fechaSocket(socket);
        }
        
     
        
    }
    
    public static void main(String[] args) 
    {
        
        try
        {                                         
                SocketServidor server = new SocketServidor();

                //Para teste
                System.out.println("Aguando conexão...");

                server.criarServerSocket(5555);     
            while (true) 
            {  
                Socket socket = server.esperaConexao();

                //Para teste
                System.out.println("Cliente conectado");

                //Espaço reservado para criar codigo para novo processo(thread)
                
                server.trataConexao(socket);

                 //Para teste
                System.out.println("Cliente finalizado.");
            }
        }
        catch (IOException e)
        {
            //tratar exceção
            System.out.println("Erro no servidor: " + e.getMessage());
        }
    }
    
}
