package com.iwood.web.util;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class DES {
    private static final String DES_ALGORITHM = "DES";

    private static Logger logger = LoggerFactory.getLogger(DES.class);

    private static Cipher getCipher(String key, int mode) throws Exception {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(DES_ALGORITHM);
            cipher.init(mode, generateKey(key));
        } catch (NoSuchAlgorithmException e) {
            throw e;
        } catch (NoSuchPaddingException e) {
            throw e;
        } catch (InvalidKeyException e) {
            throw e;
        }
        return cipher;
    }

    public static String encrypt(String input, String key) throws Exception {
        return encrypt(input, key, true);
    }

    public static String encrypt(String input, String key, boolean isUrlSafe) throws Exception {

        Cipher cipher = getCipher(key, Cipher.ENCRYPT_MODE);
        try {
            byte[] buf = cipher.doFinal(input.getBytes());
            if(isUrlSafe) {
                return new String(Base64.encodeBase64URLSafe(buf));
            } else {
                return new String(Base64.encodeBase64(buf));
            }
        } catch (IllegalBlockSizeException e) {
            logger.error("encrypt error, input={} key={} err={}", new Object[] { input, key, e.getMessage() });
            throw e;
        } catch (BadPaddingException e) {
            logger.error("encrypt error, input={} key={} err={}", new Object[] { input, key, e.getMessage() });
            throw e;
        }
    }

    public static String decrypt(String input, String key) throws Exception {
        Cipher cipher = getCipher(key, Cipher.DECRYPT_MODE);
        try {
            byte[] buf = cipher.doFinal(Base64.decodeBase64(input.getBytes()));
            return new String(buf);
        } catch (IllegalBlockSizeException e) {
            logger.error("encrypt error, input={} key={} err={}", new Object[] { input, key, e.getMessage() });
            throw e;
        } catch (BadPaddingException e) {
            logger.error("encrypt error, input={} key={} err={}", new Object[] { input, key, e.getMessage() });
            throw e;
        }
    }

    private static SecretKey generateKey(String secretKey) {
        KeyGenerator kg = null;
        SecureRandom secureRandom = null;
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(secretKey.getBytes());
            kg = KeyGenerator.getInstance(DES_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
        }
        kg.init(secureRandom);
        return kg.generateKey();
    }
}
