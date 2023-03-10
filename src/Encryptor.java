public class Encryptor
{
    /** A two-dimensional array of single-character strings, instantiated in the constructor */
    private String[][] letterBlock;

    /** The number of rows of letterBlock, set by the constructor */
    private int numRows;

    /** The number of columns of letterBlock, set by the constructor */
    private int numCols;

    /** Constructor*/
    public Encryptor(int r, int c)
    {
        letterBlock = new String[r][c];
        numRows = r;
        numCols = c;
    }

    public String[][] getLetterBlock()
    {
        return letterBlock;
    }

    /** Places a string into letterBlock in row-major order.
     *
     *   @param str  the string to be processed
     *
     *   Postcondition:
     *     if str.length() < numRows * numCols, "A" in each unfilled cell
     *     if str.length() > numRows * numCols, trailing characters are ignored
     */
    public void fillBlock(String str)
    {
        int k = 0;
        for (int r = 0; r < letterBlock.length;r++)
        {
            for (int c = 0; c < letterBlock[0].length;c++)
            {
                if (k < str.length())
                {
                    letterBlock[r][c] = str.substring(k, k + 1);
                }
                else
                {
                    letterBlock[r][c] = "A";
                }
                k++;
            }
        }
    }

    /** Extracts encrypted string from letterBlock in column-major order.
     *
     *   Precondition: letterBlock has been filled
     *
     *   @return the encrypted string from letterBlock
     */
    public String encryptBlock()
    {
        String encrypted = "";
        for (int c = 0; c < letterBlock[0].length; c++)
        {
            for (int r = 0; r < letterBlock.length; r++)
            {
                encrypted+= letterBlock[r][c];
            }
        }
        return encrypted;
    }

    /** Encrypts a message.
     *
     *  @param message the string to be encrypted
     *
     *  @return the encrypted message; if message is the empty string, returns the empty string
     */
    public String encryptMessage(String message)
    {
        String encrypted = "";
        int keySize = numRows * numCols;
        int i = 0;
        while (i + keySize < message.length())
        {
            fillBlock(message.substring(i, i + keySize));
            encrypted += encryptBlock();
            i += keySize;
        }
        fillBlock(message.substring(i));
        encrypted += encryptBlock();
        return encrypted;
    }

    /**  Decrypts an encrypted message. All filler 'A's that may have been
     *   added during encryption will be removed, so this assumes that the
     *   original message (BEFORE it was encrypted) did NOT end in a capital A!
     *
     *   NOTE! When you are decrypting an encrypted message,
     *         be sure that you have initialized your Encryptor object
     *         with the same row/column used to encrypted the message! (i.e.
     *         the “encryption key” that is necessary for successful decryption)
     *         This is outlined in the precondition below.
     *
     *   Precondition: the Encryptor object being used for decryption has been
     *                 initialized with the same number of rows and columns
     *                 as was used for the Encryptor object used for encryption.
     *
     *   @param encryptedMessage  the encrypted message to decrypt
     *
     *   @return  the decrypted, original message (which had been encrypted)
     *
     *   TIP: You are encouraged to create other helper methods as you see fit
     *        (e.g. a method to decrypt each section of the decrypted message,
     *         similar to how encryptBlock was used)
     */
    public String decryptMessage(String encryptedMessage)
    {
        String encrypted = "";
        int keySize = numRows * numCols;
        int i = 0;
        while (i < encryptedMessage.length())
        {
            reverseFillBlock(encryptedMessage.substring(i, i + keySize));
            for (int r = 0; r < letterBlock.length; r++)
            {
                for (int c = 0; c < letterBlock[0].length; c++)
                {
                    encrypted+= letterBlock[r][c];
                }
            }
            i += keySize;
        }
        i = encrypted.length() - 1;
        while (encrypted.substring(i).equals("A"))
        {
            encrypted = encrypted.substring(0, i);
            i--;
        }
        return encrypted;
    }
    public void reverseFillBlock(String str)
    {
        int k = 0;
        for (int c = 0; c < letterBlock[0].length;c++)
        {
            for (int r = 0; r < letterBlock.length;r++)
            {
                letterBlock[r][c] = str.substring(k, k + 1);
                k++;
            }
        }
    }

    public String superEncrypt(String message, int shift)
    {
        int keySize = numRows * numCols;
        int i = 0;
        while (i + keySize < message.length())
        {
            fillBlock(charShift(message.substring(i, i + keySize), shift));
            rowShift(shift);
            colShift(shift);

            message += encryptBlock();
            i += keySize;
        }
        fillBlock(charShift(message.substring(i), shift));
        message += encryptBlock();
        return message;
    }
    public String charShift(String message, int ofSet)
    {
        String values = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        for (int o = 0; o < ofSet; o++)
        {
            for (int i = 0; i < message.length(); i++)
            {
                int index = values.indexOf(message.substring(i, i + 1));
                if(index == 0)
                {
                    message = message.substring(0, i) + values.substring(values.length() - 1) + message.substring(i + 1);
                }
                else if(index != -1)
                {
                    message = message.substring(0, i) + values.substring(index - 1, index) + message.substring(i + 1);
                }
            }
        }
        return message;
    }
    public void rowShift(int oftSet)
    {
        for(int o = 0; o < oftSet; o++)
        {
            String[] first = letterBlock[0];
            for (int i = 1; i < letterBlock.length - 1; i++)
            {
                letterBlock[i - 1] = letterBlock[i];
                letterBlock[i] = letterBlock[i + 1];

            }
            letterBlock[letterBlock.length - 1] = first;
        }
    }

    public void colShift(int oftSet)
    {
        for(int r = 0; r < letterBlock.length; r++)
        {
            for (int o = 0; o < oftSet; o++)
            {
                String first = letterBlock[r][0];
                for (int c = 1; c < letterBlock[0].length - 1; c++)
                {
                    letterBlock[r][c - 1] = letterBlock[r][c];
                    letterBlock[r][c] = letterBlock[r][c + 1];
                }
                letterBlock[r][letterBlock[0].length - 1] = first;
            }
        }
    }
}