package geopixel.utils; 

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cryptography {
	MessageDigest md;

	public Cryptography() {

	}

	/**
	 * 
	 * @param value
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public String encrypt(String value) throws NoSuchAlgorithmException {
		this.md = MessageDigest.getInstance("SHA1");
		this.md.update(value.getBytes());
		BigInteger hash = new BigInteger(1, this.md.digest());
		String pass = hash.toString(16);
		return pass;
	}


	/**
	 * Generate MD5 hash of a string.
	 * 
	 * @param String
	 *            text
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
}
