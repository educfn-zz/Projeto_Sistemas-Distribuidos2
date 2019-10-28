package PacoteServidor;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
        
        try 
        {
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        /*
        * O 'ObjectInputStream' serve como um 'decorator' do InputStream, assim
        *como no caso do OutputStream; para poder escrever(write) e ler (read) 
        *o objeto de forma mais fácil, não necessitando assim ficar realizado 
        * sucessivas converssões de Objeto em bytes e vice-versa. O decorator 
        * ira fazer a comunicação com a Stream.
        */
        
        System.out.println("Tratando...");
        String msg = input.readUTF();
        System.out.println("Mensagem recebida...");
        output.writeUTF("Hello World!");
        output.flush();
        
        
        //4.2- Fechar streams de entrada e saída
        input.close();
        output.close();
        
        } 
        catch (IOException e) 
        {
            //Area para o tratamento de falhas na comunicação 
            //para cada cliente(Client) criado.
            System.out.println("Problema no tratamento da conexão com o cliente: " + socket.getInetAddress());
            System.out.println("Erro: " + e.getMessage());
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
            Socket socket = server.esperaConexao();
            
            //Para teste
            System.out.println("Cliente conectado");
            
            server.trataConexao(socket);
            
             //Para teste
            System.out.println("Cliente finalizado.");
        }
        catch (IOException e)
        {
            //tratar exceção
        }
    }
    
}
