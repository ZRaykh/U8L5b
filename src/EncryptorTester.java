import java.util.Arrays;

public class EncryptorTester
{
    public static void main(String[] args)
    {
        Encryptor encryptorTest = new Encryptor(6, 8);
        String actualEncrypted1 = encryptorTest.decryptMessage("Nn  o enyNnyvaoenoe uvaurg e   iurldgvp eooe;gtwn udsA;gn eA o arANnantAenrd Avao yAe udoArrneuA");
        System.out.println(actualEncrypted1);


    }
}