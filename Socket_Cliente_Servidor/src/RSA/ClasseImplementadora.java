/*
 *A autoria do codigo no pacote RSA esta descrito no arquivo README.txt deste 
mesmo pacote.
 */
package RSA;

import static RSA.CodePrinter.printHex;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
     * O programa principal
     *
     * @param args Os argumentos são ignorados.
     * @throws Exception Lanço uma Exception para não poluir muito o código de
     * demonstração com tratamento de exceções. Em um programa "de verdade" você
     * deveria tratar corretamente as exceções.
*/
public class ClasseImplementadora {
        
    public byte[][] cifrarMensagem(Object obj) throws Exception
    {
        // -- Gera o par de chaves, em dois arquivos (chave.publica e
        // chave.privada)
        GeradorParChaves gpc = new GeradorParChaves();
        gpc.geraParChaves(new File("chave.publica"), new File("chave.privada"));

        // -- Cifrando a mensagem
        
        //ObjectOutput out = null;
        //out.writeObject(yourObject);
        
        byte[] mensagem = "Hello, world!".getBytes("ISO-8859-1");
        CarregadorChavePublica ccp = new CarregadorChavePublica();
        PublicKey pub = ccp.carregaChavePublica(new File("chave.publica"));
        Cifrador cf = new Cifrador();
        byte[][] cifrado = cf.cifra(pub, mensagem);
        printHex(cifrado[0]);
        printHex(cifrado[1]);
        
        return cifrado;
    }
    
    
    public Object decifrarMensagem(byte[][] cifrado) throws Exception
    { 
        Object obj_decifrado = null;
        
        // -- Decifrando a mensagem
        CarregadorChavePrivada ccpv = new CarregadorChavePrivada();
        PrivateKey pvk = ccpv.carregaChavePrivada(new File("chave.privada"));
        Decifrador dcf = new Decifrador();
        byte[] decifrado = dcf.decifra(pvk, cifrado[0], cifrado[1]);
        printHex(decifrado);
        
        //Object o = in.readObject(); 
        
        return obj_decifrado;
    }
    
}
