package il.ac.bgu.finalproject.server.Domain.DomainObjects;

import javax.crypto.Cipher;
import java.security.*;

public class Encryption {
    // generate public and private keys
    KeyPair keyPair;
    PublicKey pubKey;
    PrivateKey privateKey;

    public Encryption() throws NoSuchAlgorithmException {
        keyPair = buildKeyPair();
        pubKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();
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

    public static byte[] encrypt(PrivateKey privateKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(message.getBytes());
    }

    public static byte[] decrypt(PublicKey publicKey, byte [] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return cipher.doFinal(encrypted);
    }
}
