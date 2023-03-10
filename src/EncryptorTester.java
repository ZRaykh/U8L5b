import java.util.Arrays;

public class EncryptorTester
{
    public static void main(String[] args)
    {
        Encryptor encryptorTest = new Encryptor(3, 5);
        System.out.println(encryptorTest.charShift("hello", 4));
    }
}