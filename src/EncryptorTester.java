import java.util.Arrays;

public class EncryptorTester
{
    public static void main(String[] args)
    {
        Encryptor encryptorTest = new Encryptor(3, 3);
        System.out.println(encryptorTest.superEncrypt("hello this is bob", 5));
        System.out.println(encryptorTest.superDecrypt("dg ocgcZjnnj  WdWA",5));
    }
}