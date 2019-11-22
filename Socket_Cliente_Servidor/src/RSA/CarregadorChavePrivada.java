/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RSA;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.PrivateKey;

 /**
 * Serve para carregar a chave privada de um arquivo. Não é o melhor jeito de
 * guardar a chave (é melhor e mais seguro usar um keystore), mas faço isso só
 * para simplificar.
 */
public class CarregadorChavePrivada {

    /**
     * Carrega a chave privada serializada de um arquivo.
     *
     * @param fPvk O arquivo com a chave privada serializada.
     * @return A chave privada.
     * @throws IOException Se não achar o arquivo, ou se houver algum problema
     * @throws ClassNotFoundException O objeto contido no arquivo é de uma
     * classe não presente neste projeto.
     */
    public PrivateKey carregaChavePrivada(File fPvk) throws IOException,
    ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fPvk));
        PrivateKey ret = (PrivateKey) ois.readObject();
        ois.close();
        return ret;
    }

}
