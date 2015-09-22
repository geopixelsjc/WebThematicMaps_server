package geopixel.utils; 

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64DecoderStream;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64EncoderStream;

public class Cryptography {
	MessageDigest md;
	private static Cipher ecipher;
	private static Cipher dcipher;
	
	

	public Cryptography() throws NoSuchAlgorithmException {
		
	}

	/**
	 * 
	 * @param value
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public String encryptSHA1(String value) throws NoSuchAlgorithmException {
		this.md = MessageDigest.getInstance("SHA1");
		this.md.update(value.getBytes());
		BigInteger hash = new BigInteger(1, this.md.digest());
		String pass = hash.toString(16);
		return pass;
	}


	/**
	 * Generate MD5 hash of a string.
	 * 
	 * @param String text
	 * @return String result
	 * @throws NoSuchAlgorithmException
	 */
	public String generateHashMD5(String text) throws NoSuchAlgorithmException {
		String result = "";

		this.md = MessageDigest.getInstance("MD5");
		this.md.update(text.getBytes());
		byte[] hashMd5 = this.md.digest();
		result = stringHexa(hashMd5);

		return result;
	}

	private static String stringHexa(byte[] bytes) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
			int parteBaixa = bytes[i] & 0xf;
			if (parteAlta == 0) {
				s.append('0');
			}
			s.append(Integer.toHexString(parteAlta | parteBaixa));
		}
		return s.toString();
	}

	public static String encrypt(String str,SecretKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		ecipher = Cipher.getInstance("DES");
		ecipher.init(Cipher.ENCRYPT_MODE, key);

		try {

			// encode the string into a sequence of bytes using the named
			// charset

			// storing the result into a new byte array.

			byte[] utf8 = str.getBytes("UTF8");

			byte[] enc = ecipher.doFinal(utf8);

			// encode to base64

			enc = BASE64EncoderStream.encode(enc);

			return new String(enc);

		}

		catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	public static String decrypt(String str,SecretKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		
		dcipher = Cipher.getInstance("DES");
		dcipher.init(Cipher.DECRYPT_MODE, key);

		try {

			// decode with base64 to get bytes

			byte[] dec = BASE64DecoderStream.decode(str.getBytes());

			byte[] utf8 = dcipher.doFinal(dec);

			// create new string based on the specified charset

			return new String(utf8, "UTF8");

		}

		catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}
	
}
	




