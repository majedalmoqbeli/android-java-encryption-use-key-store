package com.example.encryption.encryption;

import android.util.Base64;
import android.util.Log;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class EncHelper {
    private static EncHelper obj;

    private final EnCryptor encryptor;
    private final DeCryptor decryptor;

    private EncHelper() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        encryptor = new EnCryptor();
        decryptor = new DeCryptor();
    }

    public static EncHelper getInstance() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        if (obj == null) {
            obj = new EncHelper();
        }
        return obj;
    }


    public String decryptText(String alisName, byte[] encryption) {
        try {


            return decryptor.decryptData(alisName, encryption, encryptor.getIv());
        } catch (UnrecoverableEntryException | NoSuchAlgorithmException |
                KeyStoreException | NoSuchPaddingException | NoSuchProviderException |
                IOException | InvalidKeyException e) {
            Log.e("decryptText", "decryptData() called with: " + e.getMessage(), e);
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String encryptText(String alisName, String targetText) {

        try {
            final byte[] encryptedText = encryptor.encryptText(alisName, targetText);

            return Base64.encodeToString(encryptedText, Base64.DEFAULT);

        } catch (UnrecoverableEntryException | NoSuchAlgorithmException | NoSuchProviderException |
                KeyStoreException | IOException | NoSuchPaddingException | InvalidKeyException e) {
            Log.e("encryptText", "onClick() called with: " + e.getMessage(), e);
        } catch (InvalidAlgorithmParameterException | SignatureException |
                IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
