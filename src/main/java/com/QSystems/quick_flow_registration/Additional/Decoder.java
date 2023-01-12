package com.QSystems.quick_flow_registration.Additional;


import lombok.SneakyThrows;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Decoder {

    SecretKeySpec secretKeySpec;
    IvParameterSpec ivSpec;
    String algorithm;

    final String keyEncDec = "T5+QS!st8m*445Ar3t1o-453";
    final String vecktorEncDec = "Ad*mrkRj";

//    private ParamEncrypt paramEncrypt;

    @SneakyThrows
    public Decoder() {
//        Map<String,String> tst=System.getenv();
//         secretKeySpec = new SecretKeySpec(System.getenv("DecDBPasKey").getBytes(), "TripleDES");
        secretKeySpec = new SecretKeySpec(keyEncDec.getBytes(), "TripleDES");
//         ivSpec = new IvParameterSpec(System.getenv("DecDBPasVECTOR").getBytes());
        ivSpec = new IvParameterSpec(vecktorEncDec.getBytes());
        algorithm="TripleDES/CBC/PKCS5Padding";
    }

//    @SneakyThrows
//    public Decoder(ParamEncrypt paramEncrypt) {
//        secretKeySpec = new SecretKeySpec(paramEncrypt.getKey().getBytes(), "TripleDES");
//        ivSpec = new IvParameterSpec(paramEncrypt.getVector().getBytes());
//        algorithm="TripleDES/CBC/PKCS5Padding";
//        this.paramEncrypt = paramEncrypt;
//    }

    public String encrypt(String inputString) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(this.algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, this.secretKeySpec, this.ivSpec);
        byte[] cipherText = cipher.doFinal(inputString.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);
    }

    public String decrypt(String inStr) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(this.algorithm);
        cipher.init(Cipher.DECRYPT_MODE, this.secretKeySpec, this.ivSpec);
        byte[] plainText = cipher.doFinal(Base64.getDecoder()
                .decode(inStr));
        return new String(plainText);
    }

}
