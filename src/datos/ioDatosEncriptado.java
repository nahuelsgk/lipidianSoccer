/*Autor: Nahuel Velazco*/
package datos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class ioDatosEncriptado{
	private String archivoCodificado;
	private String directorioDeArchivosCodificados;
	
	public String getArchivoCodificado(){
		return this.archivoCodificado;
	}
	
	public void setArchivoCodificado(String filePath){
		this.archivoCodificado = filePath;
	}
	
	public void setDirectorioDeArchivosCodificados(String directoryPath){
		this.directorioDeArchivosCodificados = directoryPath;
	}
	
	
	public ioDatosEncriptado(){
		this.archivoCodificado = null;
		String directorioDatosLiga = System.getProperty("user.dir") + System.getProperty("file.separator") + "Data";
		File directorioDatosLigaFile = new File(directorioDatosLiga);
		if(directorioDatosLigaFile.exists() == false){
			directorioDatosLigaFile.mkdir();
		}
		this.directorioDeArchivosCodificados = directorioDatosLiga;
	}
	
	public ioDatosEncriptado(String rutaArchivoCodificado)throws Exception{
		String directorioDatosLiga = System.getProperty("user.dir") + System.getProperty("file.separator") + "Data";
		File directorioDatosLigaFile = new File(directorioDatosLiga);
		if(directorioDatosLigaFile.exists() == false){
			directorioDatosLigaFile.mkdir();
		}
		String rutaArchivoDatosCodificado = directorioDatosLiga + System.getProperty("file.separator") + rutaArchivoCodificado;
		this.archivoCodificado = rutaArchivoDatosCodificado;
		this.directorioDeArchivosCodificados = directorioDatosLiga;
	}
	
	
    /*Lee un objeto desde un archivo*/
    public Object readFromFile(String filename) throws Exception {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Object object = null;

        try {
            fis = new FileInputStream(new File(filename));
            ois = new ObjectInputStream(fis);
            object = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {	
                ois.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return object;
    }

    /*Escribe un un objeto a un archivo*/
    public void writeToFile(String filename, Object object) throws Exception {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        File file = new File(filename); // Create file if it does not exist boolean success = 
        if(file.exists() == false) file.createNewFile(); 
        try {
            fos = new FileOutputStream(new File(filename));
            oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                oos.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }

    public void setOperationForRecords(){
    	this.archivoCodificado = null;
		String directorioRecords = System.getProperty("user.dir") + System.getProperty("file.separator") + "Data" + System.getProperty("file.separator") + "Records";
		File directorioRecordsFile = new File(directorioRecords);
		if(directorioRecordsFile.exists() == false){
			directorioRecordsFile.mkdir();
		}
		this.directorioDeArchivosCodificados = directorioRecords;
	}
    
        
    
    public void escribirEnDisco(Object objeto) throws Exception{
    	IOEncrypt ioenc = new IOEncrypt("1234567890123456");
        writeToFile(archivoCodificado, ioenc.encrypt(objeto));
    }
    
    public Object descifrarObjetoCifrado(Object objetoCifrado) throws Exception{
    	IOEncrypt ioenc = new IOEncrypt("1234567890123456");
    	return ioenc.decrypt(objetoCifrado);
    }

    public Object leerDisco() throws Exception{
    	Object o =  readFromFile(archivoCodificado);
		Object od = descifrarObjetoCifrado((Object) o);
		return  od;
	}
    
    public String[] listarArchivosCifrados(){
    	File dir = new File(this.directorioDeArchivosCodificados);
    	FilenameFilter filter = new FilenameFilter() {
    	    public boolean accept(File dir, String name) {
    	        return name.contains("les");
    	    }
    	};
    	File[] filesList = dir.listFiles(filter);
    	int sizeString = filesList.length;
    	String[] retListFiles = new String[sizeString];
    	for(int i=0;i<sizeString;i++){
    		retListFiles[i] = filesList[i].getName();
    	}
    	return retListFiles;
    }

	public void eliminarEnDisco() {
		File archivoABorrar = new File(archivoCodificado);
		archivoABorrar.delete();
	}

	
 
    
}