/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package JosepIO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Josep
 */
public class DataWriter {

    public static String dataDir;
    public static void init(String dataDir) {
        DataWriter.dataDir = dataDir;
    }
    public static <T> void write(Class<T> classe, ArrayList<T> array) throws IOException {
        File file = new File((dataDir.endsWith(System.getProperty("file.separator")) ? dataDir : (System.getProperty("line.separator") + dataDir)) + classe.getSimpleName() + ".dat");
        if(file.exists()) file.delete();
        file.createNewFile();
        RandomAccessFile raf = new RandomAccessFile(file, "rws");
        raf.writeLong(0);
        HashMap<String,Long> hash = new HashMap<String,Long>();
        long obj_in = file.length();
        for(T obj : array) {
            obj_in = file.length();
            raf.write(comprimir(obj));
            hash.put(obj.toString(), Long.rotateLeft(obj_in, Integer.SIZE) | file.length());
        }
        obj_in = file.length();
        raf.write(comprimir(hash));
        raf.seek(0);
        raf.writeLong(Long.rotateLeft(obj_in, Integer.SIZE) | file.length());
        raf.close();
    }
    private static byte[] comprimir(Object obj) throws FileNotFoundException, IOException {
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        java.util.zip.GZIPOutputStream gos = new java.util.zip.GZIPOutputStream(bStream, java.util.zip.Deflater.BEST_COMPRESSION);
        ObjectOutputStream oStream = new ObjectOutputStream( gos );
        oStream.writeObject ( obj );
        oStream.flush();
        oStream.close();
        return bStream.toByteArray();
    }
}
