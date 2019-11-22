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




public class ExemploCriptografia
{

    public static void main(String[] args) throws Exception 
    {
        ClasseImplementadora ci = new ClasseImplementadora();
        
        ci.implementar();
    }    
    
}
