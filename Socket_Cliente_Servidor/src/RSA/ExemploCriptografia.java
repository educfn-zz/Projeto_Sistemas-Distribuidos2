// Exemplo de criptografia RSA.
//
// Para criptografar um bloco de dados com RSA, gera-se uma chave aleatória para
// usar com um algoritmo simétrico (como o AES), e então criptografa-se essa chave
// com o algoritmo RSA usando-se a chave pública do destinatário.
//
// Para o destinatário decifrar os dados, ele deve decifrar a chave recebida com sua
// chave privada, e então usar a chave decifrada para decifrar os dados.
//
// Note que o modo correto de fazer isso é usar um formato padronizado, como o PKCS#7
// (ou CMS), e usar o BouncyCastle. Só faço isto para exemplificar as APIs do Java.
//
package RSA;

import static RSA.CodePrinter.printHex;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * A classe a seguir cifra a decifra a string "Hello, world!".
 */
public class ExemploCriptografia
{
    /**
     * O programa principal
     *
     * @param args Os argumentos são ignorados.
     * @throws Exception Lanço uma Exception para não poluir muito o código de
     * demonstração com tratamento de exceções. Em um programa "de verdade" você
     * deveria tratar corretamente as exceções.
     */
    public static void main(String[] args) throws Exception {
        // -- Gera o par de chaves, em dois arquivos (chave.publica e
        // chave.privada)
        GeradorParChaves gpc = new GeradorParChaves();
        gpc.geraParChaves(new File("chave.publica"), new File("chave.privada"));

        // -- Cifrando a mensagem "Hello, world!"
        byte[] textoClaro = "Hello, world!".getBytes("ISO-8859-1");
        CarregadorChavePublica ccp = new CarregadorChavePublica();
        PublicKey pub = ccp.carregaChavePublica(new File("chave.publica"));
        Cifrador cf = new Cifrador();
        byte[][] cifrado = cf.cifra(pub, textoClaro);
        printHex(cifrado[0]);
        printHex(cifrado[1]);

        // -- Decifrando a mensagem
        CarregadorChavePrivada ccpv = new CarregadorChavePrivada();
        PrivateKey pvk = ccpv.carregaChavePrivada(new File("chave.privada"));
        Decifrador dcf = new Decifrador();
        byte[] decifrado = dcf.decifra(pvk, cifrado[0], cifrado[1]);
        // System.out.println (new String (textoClaro, "ISO-8859-1"));
        printHex(decifrado);
    }    
    
}
