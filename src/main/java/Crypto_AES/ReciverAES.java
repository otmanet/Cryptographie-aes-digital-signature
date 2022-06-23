package Crypto_AES;

import java.io.FileOutputStream;

import java.util.Base64;

import javax.crypto.Mac;

import javax.crypto.spec.SecretKeySpec;

public class ReciverAES {
    public static void writeBytesToFile(String fileOutput, byte[] bytes) throws Exception {
        try (FileOutputStream fos = new FileOutputStream(fileOutput)) {
            fos.write(bytes);
        }
    }

    public static void main(String[] args) throws Exception {
        SenderAES senderAES = new SenderAES();
        String ReciverMessage = senderAES.MessageSender();
        String Secret = "azerty";
        SecretKeySpec secretKeySpec = new SecretKeySpec(Secret.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        String[] splitMessage = ReciverMessage.split("_.._");
        String Message = splitMessage[0];
        String Signature = splitMessage[1];
        mac.init(secretKeySpec);
        byte[] sign = mac.doFinal(Message.getBytes());
        String Base64Sign = Base64.getEncoder().encodeToString(sign);
        if (Base64Sign.equals(Signature)) {
            System.out.println("Signature ok \n");
            System.out.println("_____That's your message ,you can show it now :______ \n");
            byte[] messageReciver = Base64.getDecoder().decode(Message);
            System.out.println(new String(messageReciver));
            writeBytesToFile("cv.pdf", messageReciver);
            System.out.println("______________________________________________________");
        } else {
            System.out.println("Signature Not Ok");
        }

    }

}
