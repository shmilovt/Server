package il.ac.bgu.finalproject.server.Domain.DomainObjects;

import il.ac.bgu.finalproject.server.Domain.Controllers.MyLogger;

import javax.crypto.Cipher;
import java.security.*;
import java.util.logging.Level;

public class Encryption {
    // generate public and private keys
    private KeyPair keyPair;
    private PublicKey pubKey;
    private PrivateKey privateKey;

    private static Encryption instance = null;
    public static Encryption getInstance() { //retuns null in case of NoSuchAlgorithmException
        if(instance == null) {
            try {
                instance = new Encryption();
            } catch (NoSuchAlgorithmException e) {
                MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            }
        }
        return instance;
    }

    public Encryption() throws NoSuchAlgorithmException {
        setKeyPair(buildKeyPair());
        setPubKey(getKeyPair().getPublic());
        setPrivateKey(getKeyPair().getPrivate());
    }

//    // encrypt the message
//    byte [] encrypted = encrypt(privateKey, "This is a secret message");
//        System.out.println(new String(encrypted));  // <<encrypted message>>
//
//    // decrypt the message
//    byte[] secret = decrypt(pubKey, encrypted);
//        System.out.println(new String(secret));     // This is a secret message


    public static KeyPair buildKeyPair() throws NoSuchAlgorithmException {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);
        return keyPairGenerator.genKeyPair();
    }

    public String encrypt(String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, getPrivateKey());

        return new String(cipher.doFinal(message.getBytes()));
    }

    public String decrypt(String encryptedString) throws Exception {
        byte[] encrypted = encryptedString.getBytes();
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, getPubKey() );

        return new String(cipher.doFinal(encrypted));
    }

    public KeyPair getKeyPair() {
        return keyPair;
    }

    public void setKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public PublicKey getPubKey() {
        return pubKey;
    }

    public void setPubKey(PublicKey pubKey) {
        this.pubKey = pubKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }
}
