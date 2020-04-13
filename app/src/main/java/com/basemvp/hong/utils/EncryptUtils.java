package com.basemvp.hong.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Create by Hong on 2020/4/13 13:47.
 */
public class EncryptUtils {
    /**
     * md5 加密
     * @param key
     * @return
     */
    public static String MD5(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(0xFF & aByte);
            if (hex.length() == 1) {
                sb.append('0');
            }
        }
        return sb.toString();
    }


}
