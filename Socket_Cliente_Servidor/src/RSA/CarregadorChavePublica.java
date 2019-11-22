package RSA;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.PublicKey;

/**
 * Serve para carregar a chave pública de um arquivo. Não é o melhor jeito de
 * guardar a chave (é melhor e mais seguro usar um keystore), mas faço isso só
 * para simplificar.
 */
public class CarregadorChavePublica {

    /**
     * Carrega a chave pública serializada de um arquivo.
     *
     * @param fPub O arquivo com a chave pública serializada.
     * @return A chave pública
     * @throws IOException Se não achar o arquivo, ou se houver algum problema
     * ao efetuar a des-serialização.
     * @throws ClassNotFoundException O objeto contido no arquivo é de uma
     * classe não presente neste projeto.
     */
    public PublicKey carregaChavePublica(File fPub) throws IOException, 
    ClassNotFoundException 
    {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fPub));
        PublicKey ret = (PublicKey) ois.readObject();
        ois.close();
        return ret;
    }

}
