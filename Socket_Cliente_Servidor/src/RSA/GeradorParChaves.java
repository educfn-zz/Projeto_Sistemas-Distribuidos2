package RSA;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAKeyGenParameterSpec;
import javax.security.cert.CertificateException;

/**
 * Gera um par de chaves e as guarda em formato serializado. (não é o formato
 * mais seguro - seria melhor usar um keystore, que pode ser protegido por
 * senha), mas faço isto para simplificar a compreensão.
 */
public class GeradorParChaves {

    private static final int RSAKEYSIZE = 1024;

    /**
     * Gera um par de chaves e as guarda em formato serializado em arquivos.
     *
     * @param fPub O arquivo que irá conter a chave pública.
     * @param fPvk O arquivo que irá conter a chave privada.
     * @throws IOException Problemas de acesso/gravação do arquivo.
     * @throws NoSuchAlgorithmException RSA não disponível nesta versão do JDK.
     * @throws InvalidAlgorithmParameterException Não deve ocorrer.
     * @throws CertificateException Não deve ocorrer.
     * @throws KeyStoreException Não deve ocorrer.
     */
    public void geraParChaves(File fPub, File fPvk) throws IOException, NoSuchAlgorithmException,
    InvalidAlgorithmParameterException, CertificateException, KeyStoreException 
    {

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(new RSAKeyGenParameterSpec(RSAKEYSIZE,
                RSAKeyGenParameterSpec.F4));
        KeyPair kpr = kpg.generateKeyPair();
        PrivateKey priv = kpr.getPrivate();
        PublicKey pub = kpr.getPublic();

        // -- Gravando a chave pública em formato serializado
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
                fPub));
        oos.writeObject(pub);
        oos.close();

        // -- Gravando a chave privada em formato serializado
        // -- Não é a melhor forma (deveria ser guardada em um keystore, e
        // protegida por senha),
        // -- mas isto é só um exemplo
        oos = new ObjectOutputStream(new FileOutputStream(fPvk));
        oos.writeObject(priv);
        oos.close();

    }

}
