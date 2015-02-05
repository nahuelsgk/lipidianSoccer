/*Autor: Nahuel Velazco
 * Definicion: Clase para encriptar y desencriptar objetos mediante AES
 * */

package datos;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class IOEncrypt {
	private SecretKey clave;
	
	/*Constructoras mediante clave*/
	public IOEncrypt(String clave){
		this.clave = new SecretKeySpec(clave.getBytes(), "AES");
	}
	
	/*Constructora mediante clave por defecto*/
	public IOEncrypt(){
		this.clave = new SecretKeySpec(new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06,
    	        0x07, 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06,
    	        0x07,0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06,
    	        0x07,0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06,
    	        0x07,}, "AES");
	}
	
	/*Metodos publicos*/
	public String encrypt(Object objeto) throws Exception{
        ByteArrayOutputStream bis = new java.io.ByteArrayOutputStream();
        ObjectOutputStream obj = new java.io.ObjectOutputStream(bis);
        obj.writeObject(objeto);
        byte[] toByteArray = bis.toByteArray();
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, this.clave);
        byte[] doFinal = cipher.doFinal(toByteArray);
        return deArrayBytesAHex(doFinal);
	}
	
    public Object decrypt(Object o) throws IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, ClassNotFoundException, NoSuchAlgorithmException, NoSuchPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, this.clave);
        byte[] doFinal = cipher.doFinal(deHexadecimalABytes((String) o));
        ByteArrayInputStream bai = new java.io.ByteArrayInputStream(doFinal);
        ObjectInputStream ois = new java.io.ObjectInputStream(bai);
        return ois.readObject();
    }

	/*Privadas*/
	private byte[] deHexadecimalABytes(String hex) {
        byte rc[] = new byte[hex.length() / 2];
        for (int i = 0; i < rc.length; i++) {
            String h = hex.substring(i * 2, i * 2 + 2);
            int x = Integer.parseInt(h, 16);
            rc[i] = (byte) x;
        }
        return rc;
    }

	private String deArrayBytesAHex (byte buf[]) {
	      StringBuffer strbuf = new StringBuffer(buf.length * 2);
	      int i;

	      for (i = 0; i < buf.length; i++) {
	       if (((int) buf[i] & 0xff) < 0x10)
		    strbuf.append("0");

	       strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
	      }

	      return strbuf.toString();
	}
}
