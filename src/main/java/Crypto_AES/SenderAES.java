package Crypto_AES;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class SenderAES {

    public byte[] getBytes(File f) throws Exception {
        // Creating an object of FileInputStream to
        // read from a file
        FileInputStream fl = new FileInputStream(f);

        // Now creating byte array of same length as file
        byte[] arr = new byte[(int) f.length()];

        // Reading file content to byte array
        // using standard read() method
        fl.read(arr);

        // lastly closing an instance of file input stream
        // to avoid memory leakage
        fl.close();

        // Returning above byte array
        return arr;
    }

    public String MessageSender() throws Exception {
        // File file = new File(
        // "C:\\Users\\otman\\Downloads\\Digital-Signature-AES-RSA-In-java-master\\Digital-Signature-AES-RSA-In-java-master\\uploads\\otmane.txt");
        byte[] strToEncrypt = Files.readAllBytes(Paths.get(
                "C:\\Users\\otman\\Desktop\\Cryptographie_aes\\uploads\\cvOtmane.pdf"));

        System.out.println(strToEncrypt);

        String Secret = "azerty";
        String MessageEncrypted = Base64.getEncoder().encodeToString(strToEncrypt);
        SecretKeySpec SecretKeySpec = new SecretKeySpec(Secret.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(SecretKeySpec);
        byte[] signature = mac.doFinal(MessageEncrypted.getBytes());
        String signatureTest = Base64.getEncoder().encodeToString(signature);
        String SignMessage = MessageEncrypted + "_.._" + signatureTest;
        return SignMessage;

    }

    public static void main(String[] args) throws Exception {
        SenderAES senderAES = new SenderAES();
        System.out.println(senderAES.MessageSender());
    }
}
