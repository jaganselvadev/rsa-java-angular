package com.SpringBootApi.rsa;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAUtil {

    private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDqCV8ziSSWwzqYrfJtY12WISkutrGwLFho6B/yWlNTtrdEWuKxrlTypZ/xwUPuONqkG9RmMOtvzaVHWmRfg7ZWkc+uYaRDRwzXXNkF4WnLK4ySxmpZurBrU+2r1UVni8yD/oDoRINJFpdd+0AHJKuRuk2eR/vYuBf96XwLlMOwlQIDAQAB";
    private static String privateKey = "MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBAOoJXzOJJJbDOpit8m1jXZYhKS62sbAsWGjoH/JaU1O2t0Ra4rGuVPKln/HBQ+442qQb1GYw62/NpUdaZF+DtlaRz65hpENHDNdc2QXhacsrjJLGalm6sGtT7avVRWeLzIP+gOhEg0kWl137QAckq5G6TZ5H+9i4F/3pfAuUw7CVAgMBAAECgYEAnvkQvuwIe5RPqEh4JU/a0VDjLYUR++6Te2c51CEA+xthL8BgvnsB3vUcSVaTcy0Rb2osA+J0+rQA8g/3oF3Sq3y1Ixic//GPFk8ek7GXrebbCcDv1JsDhZUWQlOhE0lIFD6rFo22JMCCPkf+yJI3As4GWntM6+rdN6qiDK0Y7EECQQD5lForaE9TZ2vwznsezBYFEBXBxMAUWsHHD+onjIAVkrWJUmN+XiH0GC0nildalefhGBlBioXAAovkZtTFSk/RAkEA8A6oyZw+ZV1F8Dg7Uf5xCvJrHGNGd9EzA+BF39M2tAUQiKJVrvLrKeLrRxhqaf0RZ8EbaVJ3Di6+uma7phPphQJBAOggXMUmnTD7bd9ojAaW1pwa28F+1w+XYcddO/FQsghejU2NPVBZCMdYfRbbimeMKUCoA0V8Ku3s8GOcaKSeeyECQQDPq27bEr5rxDf3qz2uHha+6J4+radNif2/EaqBCeBd9a+q8/x5344O606sb3PHgvUms8i54Ww7Kbc33B3je4BdAkEAu1NMF/c0+chnq6YDSGUcoC+EMsPiu849+t4MfS5raNDNxS6wqbT+suIy7xuIa4DybKCk0g+qYXLGGwiA46A3cw==";

    public static PublicKey getPublicKey(String base64PublicKey){
        PublicKey publicKey = null;
        try{
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    public static PrivateKey getPrivateKey(String base64PrivateKey){
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    public static byte[] encrypt(String data, String publicKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
        return cipher.doFinal(data.getBytes());
    }

    public static String decrypt(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(data));
    }

    public static String decrypt(String data) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return decrypt(Base64.getDecoder().decode(data.getBytes()), getPrivateKey(privateKey));
    }

    public static void main(String[] args) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException {
        try {
            String encryptedString = Base64.getEncoder().encodeToString(encrypt("Dhiraj is the author", publicKey));
            System.out.println(encryptedString);
            String decryptedString = RSAUtil.decrypt(encryptedString);
            System.out.println(decryptedString);
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        }

    }
}
