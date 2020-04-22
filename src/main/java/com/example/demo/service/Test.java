package com.example.demo.service;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by MI on 2019/6/3.
 */
public class Test {
    public static void main(String[] args) {
        try {
            //base64();
            aes();
            //sm();
//            rsa();
            //sing();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * RSA加密
     * 非对称加密,加密密钥分为公钥和私钥。可以使用公钥加密私钥解密，也可以使用私钥加密公钥解密
     * 数字签名是带有密钥（公钥、私钥）的消息摘要算法。主要作用是验证数据的完整性、认证数据来源、抗否认。在数字签名的实现中我们使用私钥签名、公钥验证。常用的数字签名算法包括RSA、DSA、ECDSA。
     */
    static void rsa() throws Exception {
        String src = "hello rsa xidian";

        //1.初始化密钥
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512);//密钥长度为64的整数倍，最大是65536
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        byte[] pk =  rsaPublicKey.getEncoded();
        byte[] sk = rsaPrivateKey.getEncoded();
        System.out.println("RSA公钥：" + parseByte2HexStr(pk));
        System.out.println("RSA私钥：" + parseByte2HexStr(sk));//可以将其保存到本地文件中

        //2.1私钥加密，公钥解密【加密】
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(src.getBytes());
        System.out.println("RSA私钥加密：" + parseByte2HexStr(result));

        //2.2私钥加密，公钥解密【解密】
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
        keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        result = cipher.doFinal(result);
        System.out.println("RSA公钥解密：" + new String(result));

        //3.1公钥加密，私钥解密【加密】
        x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
        keyFactory = KeyFactory.getInstance("RSA");
        publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        result = cipher.doFinal(src.getBytes());
        System.out.println("RSA公钥加密：" + parseByte2HexStr(result));

        //3.2公约加密，私钥解密【解密】
        pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
        keyFactory = KeyFactory.getInstance("RSA");
        privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        result = cipher.doFinal(result);
        System.out.println("RSA私钥解密：" + new String(result));

    }

    /**
     * 数字签名-rsa
     * 数字签名是带有密钥（公钥、私钥）的消息摘要算法。主要作用是验证数据的完整性、认证数据来源、抗否认。在数字签名的实现中我们使用私钥签名、公钥验证。常用的数字签名算法包括RSA、DSA、ECDSA。n
     */
    static void sing() throws Exception {
        String src = "hello rsa";

        //1.初始化密钥
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

        //2.执行数字签名【私钥签名】
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Signature signature = Signature.getInstance("MD5WithRSA");
        signature.initSign(privateKey);
        signature.update(src.getBytes());
        byte[] result = signature.sign();
        System.out.println("RSA签名：" + parseByte2HexStr(result));

        //3.验证签名【公钥验证】
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
        keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        signature = Signature.getInstance("MD5WithRSA");
        signature.initVerify(publicKey);
        signature.update(src.getBytes());
        boolean bool = signature.verify(result);
        System.out.println("数字签名是否有效？" + bool);
    }

    /**
     * Base64
     * Base64并不是一种加密/解密算法，而是一种编码方式。Base64不生成密钥，通过Base64编码后的密文就可以直接“翻译”为明文，但是可以通过向明文中添加混淆字符来达到加密的效果。
     * 注意，都是基于字节的
     */
    static void base64() throws IOException {
        String src = "www.xxx.com?title=你好";

        //加密
        BASE64Encoder encoder = new BASE64Encoder();
        String encode = encoder.encode(src.getBytes());
        System.out.println("加密后："+encode);

        //解密
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] decode = decoder.decodeBuffer(encode);
        System.out.println("解密后："+new String(decode));
    }

    /**
     * AES
     * AES是现在对称加密算法中最流行的算法之一。加密密钥和解密密钥相同，加密运算和解密运算互为逆运算。是一种初等的加密算法。主要的算法有DES（3DES）、AES、PBE、IDEA。
     */
    static void aes() throws Exception {
        String src = "name=张三";//加密内容
        String pkey = "123";//秘钥

        //配置--加密与解密公用的
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128, new SecureRandom(pkey.getBytes()));
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES");// 创建密码器

        //加密
        byte[] byteContent = src.getBytes("utf-8");
        cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化加密器
        byte[] result = cipher.doFinal(byteContent);
        String p = parseByte2HexStr(result);//不可直接转成字符串
        System.out.println("密文：" + p);

        //解密
        cipher.init(Cipher.DECRYPT_MODE, key);// 初始化解密器
        byte[] decryptFrom = parseHexStr2Byte(p);
        byte[] result1 = cipher.doFinal(decryptFrom);
        System.out.println("原文：" + new String(result1));
    }


    /**
     * SHA与MD5
     * 不可逆的加密方式,是为了防止消息在传输过程中的篡改。验证信息等。
     */
    static void sm() throws Exception {
        String src = "abcd";

        //加密
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");// 构建加密类型/SHA-512
        messageDigest.update(src.getBytes());// 传入要加密的字符串
        byte[] b = messageDigest.digest();
        String p = parseByte2HexStr(b);
        System.out.println("加密后"+p);

    }

    /**
     * 二进制转换成16进制，加密后的字节数组不能直接转换为字符串
     */
    static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 16进制转换成二进制
     */
    static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

}
