/*
 * 	Android Signer 
 * 	Eduard Millán Forn
 * 	http://codementia.blogspot.com.es
 * 	Open Source
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;


public class Coder {
/*
 * 	http://stackoverflow.com/questions/1205135/how-to-encrypt-string-in-java
 * 	http://stackoverflow.com/questions/10831801/getting-exception-java-security-invalidkeyexception-invalid-aes-key-length-29
 * 	http://www.roseindia.net/answers/viewqa/Java-Beginners/26017-Java-Encryption-using-AES-with-key-password-.html
 * 	http://stackoverflow.com/questions/5554526/comparison-of-des-triple-des-aes-blowfish-encryption-for-data
 */

	private Cipher deCipher;
	private Cipher enCipher;
	private static SecretKeySpec skeySpec;
	private static IvParameterSpec ivSpec;
	
	public Coder(String clave)
	{
	    try {
	         deCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	         enCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        	 genKey(clave);
	    } catch (NoSuchAlgorithmException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (NoSuchPaddingException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
	
	private void genKey(String clave)
	{
		byte[] key = clave.getBytes();
		
		skeySpec = new SecretKeySpec(key, "AES");
		ivSpec = new IvParameterSpec(key);
	}
	
	/*
	 * La codificación con padding requiere que la longitud de los inputs sea multiplo de 8, por ello cuando se encripta se devuelve la cadena encriptada
	 * convertida a base64 y, para desencriptar, se decodifica antes.
	 */
	
	public byte[] encrypt(Object obj) throws InvalidKeyException, InvalidAlgorithmParameterException, IOException, IllegalBlockSizeException, ShortBufferException, BadPaddingException, ClassNotFoundException

	{
	    byte[] input = convertToByteArray(obj);

	    enCipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);
	    return convertToByteArray(DatatypeConverter.printBase64Binary(enCipher.doFinal(input)));
	}
	
	public String encrypt(String obj) throws InvalidKeyException, InvalidAlgorithmParameterException, IOException, IllegalBlockSizeException, ShortBufferException, BadPaddingException, ClassNotFoundException
	{
	    byte[] input = convertToByteArray(obj);

	    enCipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);
	    byte[] b = enCipher.doFinal(input);
	    return DatatypeConverter.printBase64Binary(b);
	}
	
	public Object decrypt(byte[] encrypted) throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException, ClassNotFoundException
	{
	    deCipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);
	    byte[] b = DatatypeConverter.parseBase64Binary((String) convertFromByteArray(encrypted));
	    return convertFromByteArray(deCipher.doFinal(b));
	}
	
	public Object decrypt(String encrypted) throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException, ClassNotFoundException
	{
	    deCipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);
	    byte[] b = DatatypeConverter.parseBase64Binary(encrypted);
	    return convertFromByteArray(deCipher.doFinal(b));
	}

	private Object convertFromByteArray(byte[] byteObject) throws IOException, ClassNotFoundException
	{
		ByteArrayInputStream bais;

		ObjectInputStream in;
		bais = new ByteArrayInputStream(byteObject);
		in = new ObjectInputStream(bais);
		Object o = in.readObject();
		in.close();
		return o;
	}

	private byte[] convertToByteArray(Object complexObject) throws IOException
	{
		ByteArrayOutputStream baos;

		ObjectOutputStream out;
		baos = new ByteArrayOutputStream();
		out = new ObjectOutputStream(baos);
		out.writeObject(complexObject);
		out.close();
		return baos.toByteArray();
	}
}
