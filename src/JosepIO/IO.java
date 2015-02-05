/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package JosepIO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;

/**
 *
 * @author JOSEP
 */
public class IO implements Runnable {
    private static String fileSeparator = System.getProperty("file.separator");
    private static String dataFolder = System.getProperty("user.dir") + System.getProperty("file.separator") + "Data" + System.getProperty("file.separator");
    private static String localizedDataFolder = System.getProperty("user.dir") + System.getProperty("file.separator") + "Language" + System.getProperty("file.separator");
    private static String profileSFolder = System.getProperty("user.dir") + System.getProperty("file.separator") + "Profiles" + System.getProperty("file.separator");
    private static String profileDir = null;
    private static String configFile = System.getProperty("user.dir") + System.getProperty("file.separator") + "Settings.cfg";
    private static String logFile = System.getProperty("user.dir") + System.getProperty("file.separator") + "Log.txt";
    //Guardar un hashmap i una llista sobre els objectes a escriure (amb operació de flush i thread - la prioritat hauria de ser baixa - apart)
    private static java.util.logging.FileHandler log = null;
    /*
     * El que es carrega per defecte ve donat pel path de l'executable, carrega
     * des de la carpeta english dins de la carpeta data.
     * Posar timer per a fer save games automàtics cada certs minuts i també
     * guardar un nombre màxim de savegames (borrar els més antics amb un
     * list d'un directori i definint al FileFilter que els ordeni de més antic
     * a més nou)
     * Presentació ha d'estar registrada al log global
     */

    private static void copyFile(File source, File newFile) throws IOException {
        FileChannel fileCh = new FileInputStream(source).getChannel();
        if(newFile.exists()) newFile.delete();
        newFile.createNewFile();
        FileChannel newCh = new FileOutputStream(newFile).getChannel();
        newCh.lock();
        while(newCh.position() < source.length()) fileCh.transferTo(fileCh.position(), 64*1024*1024, newCh);
        //A windows, el java no suporta copiar arxius superiors a 64 MB
        newCh.close();
        fileCh.close();
    }
    public static ArrayList<HashMap<String,String>> getProfiles() throws IOException {
        //Llegir del XML dades vàries i tornar-les amb un HashMap<String,HashMap<String,String>>
        File profileFolder = new File(profileSFolder);
        if(profileFolder.exists()) {
            if(!profileFolder.isDirectory()) throw new IOException("El path " + profileSFolder + " és invàlid ja que no és un directori.");
        } else throw new IOException("El directori " + profileSFolder + " no existeix.");
        java.io.FilenameFilter filenameFilter = new java.io.FilenameFilter() {
            public boolean accept(File dir, String name) {
                File file = new File(dir.getAbsolutePath() + fileSeparator + name);
                return !file.isHidden() && file.isDirectory();
            }
        };
        ArrayList<HashMap<String,String>> arrayList = new ArrayList<HashMap<String,String>>();
        for(File file : profileFolder.listFiles(filenameFilter)) {
            arrayList.add(infoProfile(file.getName()));
        }
        return arrayList;
    }
    
    /* PRE: El perfil amb nom name existeix
       POST: S'ha sel·leccionat el perfil amb nom name */
    public static void selectProfile(String name) throws IOException, ClassNotFoundException {
        //Load Profile Data from XML
        //Could chain with getSavegames
        //Look in profileSFolder
        File file = new File(profileSFolder + name);
        if(file.exists()) {
            if(file.isDirectory()) {
                profileDir = file.getAbsolutePath() + fileSeparator;
            } else throw new IOException("Profile: " + name + " isn't a directory.");
        } else throw new IOException("Profile: " + name + " doesn't exist.");
    }
    /* Realment la implementació en si mateix pot variar des d'escriure-ho com a
     * objecte a escriure-ho com a hashmap, el propi hashmap ja dòna un .toString()
     * correcte
     * Se li pot consultar si es pot fer servir JSON per a que l'usuari ho entengui millor
     */
    private static HashMap<String,String> readStringString(FileReader FileReader) throws IOException {
        java.io.BufferedReader br = new java.io.BufferedReader(FileReader);
        HashMap<String,String> reader = new HashMap<String,String>();
        String readLine = br.readLine();
        while(readLine != null) {
            String[] split = readLine.split("=");
            //S'ha de borrar ﻿ perquè el sistema ho posa automàticament
            reader.put(split[0].replace("﻿",""), split[1]);
            readLine = br.readLine();
        }
        FileReader.close();
        return reader;
    }
    private static void writeStringString(FileWriter writer, HashMap<String,String> write) throws IOException {
        java.io.BufferedWriter bw = new java.io.BufferedWriter(writer);
        for(Entry<String,String> entry :  write.entrySet()) {
            bw.write(entry.toString());
            bw.newLine();
        }
        bw.close();
    }
    public static HashMap<String,String> infoProfile(String name) throws FileNotFoundException, IOException {
        FileReader FileReader = new FileReader(profileSFolder + name + fileSeparator + "Config.cfg");
        return readStringString(FileReader);
    }
    /* PRE: El perfil amb nom name NO existeix
       POST: El perfil amb nom name existeix i té la configuració bàsica
       POST: El perfil sel·leccionat actualment és name */
    public static void createProfile(String name) throws IOException, ClassNotFoundException {
        //Create folder, copy default profile data to dir
        //Create it in profileSFolder
        File newProfile = new File(profileSFolder + name);
        newProfile.mkdirs();
        File defaultProfile = new File(dataFolder + "Profile" + fileSeparator + "Default");
        for(File file : defaultProfile.listFiles()) {
            copyFile(file,new File(newProfile.getPath() + fileSeparator + file.getName()));
        }
        HashMap<String, String> infoProfile = infoProfile(name);
        infoProfile.put("Nom", name);
        selectProfile(name);
        updateCurrentProfile(infoProfile);
    }
    public static void removeProfile(String name) throws IOException {
        File f = new File(profileSFolder + name);
        if(!f.exists()) throw new IOException("Profile " + name + " doesn't exist.");
        if(!f.isDirectory()) f.delete();
        else removeDir(f);
        f.delete();
        if((profileSFolder + name + fileSeparator).equals(profileDir)) profileDir = null;
    }
    private static void removeDir(File dir) {
        for(File file : dir.listFiles()) {
            if(file.isDirectory()) removeDir(file);
            file.delete();
        }
    }
    /* Operació per a llegir de DATA (s'ha de fer sempre que es llegeix, per a
     comprovar si l'objecte que es vol llegir està a Data, està a memòria o bé
     és invàlid (i, per tant, no està enlloc) */
    private static String getDataPath(Class<?> clas) throws IOException {
        return dataFolder + clas.getSimpleName() + ".dat";
    }
    public static HashMap<String,HashMap<String,String>> getSaveGames() {
        final HashMap<String,HashMap<String,String>> hash = new HashMap<String,HashMap<String,String>> ();
        new File(profileDir).listFiles(new java.io.FilenameFilter() {
            public boolean accept(File dir, String name) {
                File file = new File(dir.getAbsolutePath() + fileSeparator + name);
                if(!file.isDirectory() && !file.isHidden() && name.endsWith(".sav")) {
                    try {
                        hash.put(name, infoSaveGame(name));
                        return true;
                    }
                    catch(Exception ex) {
                        System.out.println(ex.getLocalizedMessage());
                    }
                }
                return false;
            }
        });
        return hash;
    }
    public static void removeSaveGame(String name) {
        File SaveG = new File(profileDir + name);
        if(SaveG.exists()) SaveG.delete();
    }
    public static void updateCurrentProfile(HashMap<String,String> config) throws IOException {
        File file = new File(profileDir + "Config.cfg");
        file.delete();
        file.createNewFile();
        FileWriter FileWriter = new FileWriter(profileDir + "Config.cfg");
        writeStringString(FileWriter,config);
    }

    public static void updateSettings(HashMap<String,String> config) throws IOException {
        File file = new File(configFile);
        file.delete();
        file.createNewFile();
        FileWriter FileWriter = new FileWriter(configFile);
        writeStringString(FileWriter,config);
    }
    public static HashMap<String,String> getSettings(String name) throws FileNotFoundException, IOException {
        FileReader FileReader;
        if(name != null) {
            FileReader = new FileReader(System.getProperty("user.dir") + fileSeparator + name);
        } else FileReader = new FileReader(configFile);
        return readStringString(FileReader);
    }
    public static String settingsFile() {
        return configFile;
    }
    public static void changeSettings(String path, String name) throws FileNotFoundException {
        File file = null;
        if(path == null) {
            //File is in the same dir
            file = new File(System.getProperty("user.dir") + fileSeparator + name);
        } else {
            file = new File(path + fileSeparator + name);
        }
        if(!file.exists()) throw new FileNotFoundException(path + fileSeparator + name);
        configFile = file.getAbsolutePath();
    }



    private static File profile;
    private static FileChannel profileIn;
    private static RandomAccessFile profileRaf;
    private static HashMap<Class<?>,Long> classMap;
    /* Necessitem un hashmap que es pugui accedir per la clau de string però que alhora
     * estigui ordenat internament segons la seva posició (el long)
     * La implementació usada per al mapa ordenat bé donada a que el mapa tindrà SEMPRE valors
     * únics, amb la qual cosa no hi haurà mai el cas en que dos valors siguin iguals
     * (i per tant funcionarà)
     */
    private static HashMap<Long,TreeMap<String,Long>> objMap;
    private static Long classHashSize;
    private static Long classHashPos;

    /* Per a major eficència passar per nivell de modificació prevista
     de les classes (- modificació ... + modificació) */
    public static void init(ArrayList<Class<?>> classes) throws IOException {
        File log1 = new File(logFile);
        if(log1.exists()) {
            File logtmp = new File(logFile + ".old");
            if(logtmp.exists())logtmp.delete();
            log1.renameTo(logtmp);
        }
        try {
            IO.log = new java.util.logging.FileHandler(logFile);
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).addHandler(new Handler() {

                @Override
                public void publish(LogRecord record) {
                    IO.log.publish(record);
                }

                @Override
                public void flush() {
                }

                @Override
                public void close() throws SecurityException {
                    IO.log.close();
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(IO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(IO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Init hashmaps
        classMap = new HashMap<Class<?>,Long>();
        objMap = new HashMap<Long,TreeMap<String,Long>>();
        File tmp = File.createTempFile("tmp", null, new File(System.getProperty("user.dir")));
        RandomAccessFile raf = new RandomAccessFile(tmp, "rws");
        raf.write(comprimir(new TreeMap<String,Long>()));
        Long HashSize = tmp.length();
        classHashPos = tmp.length();
        raf.writeLong(0);
        Long longlenght = tmp.length() - classHashPos;
        raf.write(comprimir(classMap));
        classHashSize = tmp.length();
        raf.close();
        tmp.delete();
        for(Class<?> classe : classes) {
            classMap.put(classe, Long.rotateLeft(classHashPos + longlenght, Integer.SIZE) | (longlenght + classHashPos + classHashSize));
            Comp comp = new Comp();
            TreeMap<String, Long> treeMap = new TreeMap<String, Long>(comp);
            comp.setMap(treeMap);
            objMap.put(Long.rotateLeft(longlenght + classHashPos, Integer.SIZE) | (longlenght + classHashPos + classHashSize), treeMap);
            classHashPos += HashSize;
        }
        
        /* No tots els fitxers temporals s'esborren al finalitzar el programa
         * ja que poden quedar canals oberts
         */
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                if(tempLangFile != null) {
                    try {
                        lfr.close();
                    } catch (IOException ex) {
                    }
                    tempLangFile.delete();
                }
                if(profile != null) {
                    try {
                        profileIn.close();
                    } catch (IOException ex) {
                    } try {
                        profileRaf.close();
                    } catch (IOException ex) {
                    }
                    profile.delete();
                }
            }
        });
    }
    /* El que estem fent és que enlloc d'ordenar per claus, ordeni per valor
     * amb la qual cosa podem utilitzar les operacions del TreeMap per a accedir
     * a rangs de valors molt eficientment
     */
    private static class Comp implements Comparator {

        private TreeMap<String,Long> treeMap;
        public int compare(Object o1, Object o2) {
            Long v1 = treeMap.get((String)o1);
            Long v2 = treeMap.get((String)o2);
            if(v1.longValue() > v2.longValue()) {
              return 1;
            } else if(v1.longValue() == v2.longValue()) {
              return 0;
            } else {
              return -1; 
            }
        }

        private void setMap(TreeMap<String, Long> treeMap) {
            this.treeMap = treeMap;
        }

    }

    public static <T> void remove(final T obj) throws IOException {
        HashMap<Class<?>, ArrayList<?>> hash = new HashMap<Class<?>, ArrayList<?>>() {
            {
                put((Class<T>) obj.getClass(), new ArrayList<T>(){
                    {
                        add(obj);
                    }
                });
            }
        };
        removeAll(hash);
    }

    public static <T> Object read(Class<T> obj_class, String name) throws IOException, DataFormatException, ClassNotFoundException {
        if(!classMap.containsKey(obj_class)) throw new ClassNotFoundException(obj_class.toString() + " isn't registered.");
        Long hash_pos = classMap.get(obj_class);
        TreeMap<String, Long> class_obj = objMap.get(hash_pos);
        synchronized(writeQueue) {
            if(writeQueue.containsKey(name)) return writeQueue.get(name);
        }
        if(!class_obj.containsKey(name)) {
            //Buscar a data, si no es troba llançar excepció
            String path = getDataPath(obj_class);
            RandomAccessFile raf = new RandomAccessFile(path, "r");
            Long obj_pos = raf.readLong();
            Integer obj_in = new Long(Long.rotateRight(obj_pos, Integer.SIZE)).intValue();
            Integer obj_end = obj_pos.intValue();
            byte[] buf = new byte[obj_end - obj_in];
            raf.seek(obj_in);
            raf.readFully(buf);
            HashMap<String,Long> hash = (HashMap<String, Long>) descomprimir(buf);
            if(!hash.containsKey(name)) throw new DataFormatException("Object " + name + " doesn't exist.");
            obj_pos = hash.get(name);
            obj_in = new Long(Long.rotateRight(obj_pos, Integer.SIZE)).intValue();
            obj_end = obj_pos.intValue();
            raf.seek(obj_in);
            buf = new byte[obj_end - obj_in];
            raf.readFully(buf);
            return descomprimir(buf);
        }
        Long obj_pos = class_obj.get(name);
        Integer obj_in = new Long(Long.rotateRight(obj_pos, Integer.SIZE)).intValue();
        Integer obj_end = new Long(obj_pos).intValue();
        profileRaf.seek(obj_in);
        byte[] buf = new byte[obj_end - obj_in];
        profileRaf.readFully(buf);
        return descomprimir(buf);
    }

    /* read i readAll no poden ser el mateix? No ja que a read s'ha de buscar
     l'element i a readall es llegeixen tots */
    public static <T> ArrayList<T> readAll(Class<T> obj_class) throws IOException, DataFormatException, ClassNotFoundException {
        ArrayList<T> array = new ArrayList<T>();
        Long hash_pos = classMap.get(obj_class);
        TreeMap<String, Long> class_obj = objMap.get(hash_pos);
        String path = getDataPath(obj_class);
        RandomAccessFile raf = new RandomAccessFile(path, "r");
        Long obj_pos = raf.readLong();
        Integer obj_in = new Long(Long.rotateRight(obj_pos, Integer.SIZE)).intValue();
        Integer obj_end = new Long(obj_pos).intValue();
        byte[] buf = new byte[obj_end - obj_in];
        raf.seek(obj_in);
        raf.readFully(buf);
        HashMap<String,Long> hash = (HashMap<String, Long>) descomprimir(buf);
        for(java.util.Map.Entry<String,Long> obj : class_obj.entrySet()) {
            synchronized(writeQueue) {
                if(writeQueue.containsKey(obj.getKey())) {
                    array.add((T) writeQueue.get(obj.getKey()));
                    hash.remove(obj.getKey());
                    continue;
                }
            }
            obj_pos = obj.getValue();
            obj_in = new Long(Long.rotateRight(obj_pos, Integer.SIZE)).intValue();
            obj_end = new Long(obj_pos).intValue();
            profileRaf.seek(obj_in);
            buf = new byte[obj_end - obj_in];
            profileRaf.readFully(buf);
            array.add((T) descomprimir(buf));
            hash.remove(obj.getKey());
        }
        //Agafar tots els de data
        for(Long obj_pos1 : hash.values()) {
            obj_in = new Long(Long.rotateRight(obj_pos1, Integer.SIZE)).intValue();
            obj_end = new Long(obj_pos1).intValue();
            raf.seek(obj_in);
            buf = new byte[obj_end - obj_in];
            raf.readFully(buf);
            array.add((T) descomprimir(buf));
        }
        return array;
    }

    public static <T> void write(final Object obj) throws IOException {
        HashMap<Class<?>, ArrayList<?>> hash = new HashMap<Class<?>, ArrayList<?>>() {
            {
                put(obj.getClass(), new ArrayList<Object>(){
                    {
                        add(obj);
                    }
                });
            }
        };
        writeAll(hash);
    }

    /* Object ha de ser de la classe a la qual està associada l'array */
    public static <T> void writeAll(HashMap<Class<?>, ArrayList<?>> obj_map) throws IOException {
        File removeAll = removeall(obj_map);
        /* Copiar el fitxer de nou */
        if(removeAll == null) {
            removeAll = java.io.File.createTempFile(profile.getName().substring(0,10), null, new File(profileDir));
            FileChannel dest = new FileOutputStream(removeAll).getChannel();
            dest.lock();
            profileIn.position(0);
            dest.transferFrom(profileIn, 0, classHashPos);
            dest.force(true);
            dest.close();
        }
        RandomAccessFile raf = new RandomAccessFile(removeAll, "rws");
        raf.seek(removeAll.length());
        Long obj_in = removeAll.length();
        for(Class<?> obj_class : obj_map.keySet()) {
            for(Object obj : obj_map.get(obj_class)) {
                TreeMap<String, Long> class_obj = objMap.get(classMap.get(obj_class));
                raf.write(comprimir(obj));
                Long obj_end = removeAll.length();
                class_obj.put(obj.toString(), (Long.rotateLeft(obj_in, Integer.SIZE) | obj_end.intValue()));
                obj_in = obj_end;
            }
        }
        raf.close();
        writeHash(removeAll);
    }

    public static <T> void removeAll(HashMap<Class<?>, ArrayList<?>> obj_map) throws IOException {
        File removeall = removeall(obj_map);
        if(removeall != null) writeHash(removeall);
    }

    /* Aquesta operació escriu el hash al fitxer tmp i sobreescriu el savegame amb aquest fitxer */
    private static void writeHash(File tmp) throws IOException {
        /* El tamany del hash de classes es calcula quan es carrega un savegame i
         * quan s'inicialitza la classe
         * S'ha de fer dos fors, un per a posar bé les posicions dels hash (utilitzant tmp.size())
         * i l'altre per a escriure'ls
         */
        RandomAccessFile raf = new java.io.RandomAccessFile(tmp, "rws");
        raf.seek(tmp.length());
        for(Entry<Class<?>,Long> entry : classMap.entrySet()) {
            Long size = tmp.length();
            raf.write(comprimir(objMap.get(entry.getValue())));
            TreeMap<String, Long> remove = objMap.remove(entry.getValue());
            objMap.put(Long.rotateLeft(size, Integer.SIZE) | new Long(tmp.length()).intValue(), remove);
            classMap.put(entry.getKey(), Long.rotateLeft(size, Integer.SIZE) | new Long(tmp.length()).intValue());
        }
        Long size = tmp.length();
        classHashPos = tmp.length();
        raf.write(comprimir(classMap));
        raf.seek(0);
        raf.writeLong(Long.rotateLeft(size, Integer.SIZE) | tmp.length());
        raf.close();
        try {
            profileIn.close();
        } catch (IOException ex) {

        } try {
            profileRaf.close();
        } catch (IOException ex) {

        }
        profile.delete();
        tmp.renameTo(profile);
        profileIn = new FileInputStream(profile).getChannel();
        profileRaf = new java.io.RandomAccessFile(profile, "rws");
        //Arreglar hash de les classes i escriure-ho tot
        //Aquesta operació també ESCRIU el PUNTER a la taula de hash
        //byte[] != objecte escrit! S'ha d'escriure la part del protocol!
        /* Comprovar sempre que els valors de la posició
         * dels hash estiguin ben posats o bé són absoluts al fitxer o bé són realatius
         * respecte la posició de la taula de hash de classes
         */
    }

    //TODO: Revisar això que no acaba d'anar bé
    private static <T> File removeall(HashMap<Class<?>, ArrayList<?>> obj_map) throws IOException {
        java.util.TreeMap<Long,EntryImpl> queue = new java.util.TreeMap<Long,EntryImpl>();
        for(Class<?> obj_class : obj_map.keySet()) {
            TreeMap<String, Long> class_obj = IO.objMap.get(IO.classMap.get(obj_class));
            if(class_obj == null) continue;
            for(Object obj : obj_map.get(obj_class)) {
                //Suposem que tots els objectes que ens passen han estat modificats
                //Mirem si està a la llista per a borrar-lo
                synchronized(writeQueue) {
                    //Els objectes poden canviar, les claus no
                    //L'objecte que anavem a escriure està obsolet
                    if(writeQueue.containsKey(obj.toString())) writeQueue.remove(obj.toString());
                }
                if(class_obj.containsKey(obj.toString())) {
                    queue.put(class_obj.get(obj.toString()),new EntryImpl(obj.toString(), classMap.get(obj_class)));
                }
            }
        }
        if(queue.isEmpty()) return null;
        File tmp = java.io.File.createTempFile(profile.getName().substring(0,10), null, new File(profileDir));
        FileChannel dest = new FileOutputStream(tmp).getChannel();
        dest.lock();
        Integer obj_in = 0;
        Integer obj_end = 0;
        Integer bytes_removed = 0;
        //Pot donar problemes amb les posicions
        //Llista per pesos ==> el més petit és el primer i el més gran l'últim
        /* Es té una llista de tots els elements ordenats de petit a gran
         * Com que va per pesos, el primer element seria el que està primer
         * i l'últim el que està al final
         * Ha de ser un linked hashmap que em digui donat una posició quina és
         * la seva classe (bàsicament classes pertanyent a la llista de classes)
         * de tal forma que pugui accedir a la llista de la classe i modificar l'element
         * No fa falta tenir una altre llista de còpia ja que donem per suposat que
         * l'element que estem modificant és > a tots els anteriors
         * Les llistes han de ser per pesos, es crea una llista de tots els elements i la seva classe
         * i, al estar organitzada per pesos, realment nomès haurem de calcular el rang dels elements
         * per a cada posició de la queue (és a dir, avançant el iterador fins que no trobi l'element)
         * Realment no fa falta tenir dos iteradors, el que s'haurà de fer en primer lloc és fer que
         * l'iterador arribi al primer element de la cua (cosa que es podria estalviar si es disposés
         * d'una operació que proporcionés l'iterador a la posició determinada
         */
        /* En aquest cas SI que fem servir l'ordre natural de les seves claus
         * Guardarem directament la @ de la classe enlloc de la classe i així ens evitarem haver
         * d'accedir al mapa de classes
         */
        //Ja tenim en comtpe el cas en que no hi hagi cap objecte a la cua
        profileIn.position(0);
        TreeMap<Long, Entry<String, Long>> allValues = new TreeMap<Long,Entry<String,Long>>();
        for(Long classpos : classMap.values()) {
            for(Entry<String,Long> objpos : objMap.get(classpos).entrySet()) allValues.put(objpos.getValue(), new EntryImpl(objpos.getKey(),classpos));
        }
        Entry<Long, EntryImpl> obj_pos1 = queue.firstEntry();
        objMap.get(obj_pos1.getValue().getValue()).remove(obj_pos1.getValue().getKey());
        Long obj_pos = obj_pos1.getKey();
        obj_in = new Long(Long.rotateRight(obj_pos, Integer.SIZE)).intValue();
        Entry<Long, Entry<String, Long>> firstEntry = allValues.firstEntry();
        while(firstEntry.getKey() != obj_pos) {
            //S'ha de saber a qui pertany la posició
            Integer objIN = new Long(Long.rotateRight(firstEntry.getKey(), Integer.SIZE)).intValue();
            Integer objEND = new Long(firstEntry.getKey()).intValue();
            objIN -= bytes_removed;
            objEND -= bytes_removed;
            objMap.get(firstEntry.getValue().getValue()).put(firstEntry.getValue().getKey(), Long.rotateLeft(objIN, Integer.SIZE) | (objEND));
            firstEntry = allValues.higherEntry(firstEntry.getKey());
        }
        dest.transferFrom(profileIn, 0, obj_in);
        while(queue.higherEntry(obj_pos) != null) {
            obj_in = new Long(Long.rotateRight(obj_pos, Integer.SIZE)).intValue();
            obj_end = obj_pos.intValue();
            bytes_removed += obj_end - obj_in;
            firstEntry = allValues.higherEntry(obj_pos);
            Entry<Long, EntryImpl> obj_tmp1 = queue.higherEntry(obj_pos);
            objMap.get(obj_tmp1.getValue().getValue()).remove(obj_tmp1.getValue().getKey());
            Long objtmp = obj_tmp1.getKey();
            while(firstEntry.getKey() != objtmp) {
                //S'ha de saber a qui pertany la posició
                Integer objIN = new Long(Long.rotateRight(firstEntry.getKey(), Integer.SIZE)).intValue();
                Integer objEND = new Long(firstEntry.getKey()).intValue();
                objIN -= bytes_removed;
                objEND -= bytes_removed;
                objMap.get(firstEntry.getValue().getValue()).put(firstEntry.getValue().getKey(), Long.rotateLeft(objIN, Integer.SIZE) | (objEND));
                firstEntry = allValues.higherEntry(firstEntry.getKey());
            }
            profileIn.position(profileIn.position() + obj_end - obj_in);
            dest.transferFrom(profileIn, tmp.length(), new Long(Long.rotateRight(objtmp, Integer.SIZE)).intValue() - obj_end);
            obj_pos = objtmp;
        }
        obj_in = new Long(Long.rotateRight(obj_pos, Integer.SIZE)).intValue();
        obj_end = obj_pos.intValue();
        bytes_removed += obj_end - obj_in;
        for(Entry<Long, Entry<String, Long>> firstEntry1 : allValues.tailMap(obj_pos, false).entrySet()) {
            //S'ha de saber a qui pertany la posició
            Integer objIN = new Long(Long.rotateRight(firstEntry1.getKey(), Integer.SIZE)).intValue();
            Integer objEND = new Long(firstEntry1.getKey()).intValue();
            objIN -= bytes_removed;
            objEND -= bytes_removed;
            objMap.get(firstEntry1.getValue().getValue()).put(firstEntry1.getValue().getKey(), Long.rotateLeft(objIN, Integer.SIZE) | (objEND));
            firstEntry1 = allValues.higherEntry(firstEntry1.getKey());
        }
        profileIn.position(profileIn.position() + obj_end - obj_in);
        dest.transferFrom(profileIn, tmp.length(), classHashPos - obj_end);
//            /* Com que és un arbre vermell-negre és log(n) obtenir el mapa començant a cert valor
//             * Per a tots els elements que estiguin entre aquest i el següent s'hauràn de modificar
//             * totes les seves posicions utilitzant bytes_removed
//             * Això implica que s'hauràn de tenir dos iteradors, un que vagi una posició més avançada
//             * que l'altre per a tal de poder saber els rangs de modificació
//             * S'ha d'anar amb compte amb els casos en que no hi ha cap element o bé en que nomès
//             * hi ha un sol element (si queue.size() == llista_total.size() borra tots els elements)
//             */
        dest.force(true);
        dest.close();
        return tmp;
    }

    public static void newEmptySaveGame(String name, HashMap<String,String> hash) throws IOException, ClassNotFoundException {
        File SaveG = new File(profileDir + (name == null ? ("auto" + java.util.Calendar.getInstance().getTime().toString() + ".sav") : (name + ".sav")));
        SaveG.delete();
        if(!SaveG.createNewFile()) throw new IOException("Couldn't create new savegame: " + SaveG.getName() + " reason: Unknown");
        //Escriure Hash Buit
        HashMap<Class<?>,Long> empty_hash = new HashMap<Class<?>,Long>();
        RandomAccessFile raf = new RandomAccessFile(SaveG, "rws");
        raf.writeLong(0);
        Long inici_obj = new Long(0);
        Long final_obj = new Long(0);
        for(Class<?> classe : classMap.keySet()) {
            inici_obj = SaveG.length();
            raf.write(comprimir(new TreeMap<String,Long>()));
            final_obj = SaveG.length();
            empty_hash.put(classe, Long.rotateLeft(inici_obj, Integer.SIZE) | final_obj);
            
        }
        //Hash_pos
        inici_obj = SaveG.length();
        raf.write(comprimir(empty_hash));
        final_obj = SaveG.length();
        raf.writeLong(Long.rotateLeft(inici_obj, Integer.SIZE) | final_obj);
        raf.seek(0);
        raf.writeLong(Long.rotateLeft(final_obj, Integer.SIZE) | (int)SaveG.length());
        raf.seek(SaveG.length());
        raf.write(comprimir(hash));
        raf.close();
    }
    /* Si el fitxer amb nom name existeix, es sobreescriu */
    public static void newSaveGame(String name, HashMap<String,String> hash) throws IOException {
        File SaveG = new File(profileDir + (name == null ? ("auto" + java.util.Calendar.getInstance().getTime().toString() + ".sav") : (name + ".sav")));
        SaveG.delete();
        if(!SaveG.createNewFile()) throw new IOException("Couldn't create new savegame: " + SaveG.getName() + " reason: Unknown");
        FileChannel fChannel = new FileOutputStream(SaveG).getChannel();
        profileIn.position(0);
        fChannel.lock();
        fChannel.transferFrom(profileIn, 0, profile.length());
        fChannel.force(true);
        fChannel.close();
        RandomAccessFile raf = new RandomAccessFile(SaveG, "rws");
        long obj_pos = raf.readLong();
        raf.seek(SaveG.length());
        raf.writeLong(obj_pos);
        raf.seek(0);
        raf.writeLong(Long.rotateLeft(profile.length(), Integer.SIZE) | (int)SaveG.length());
        raf.seek(SaveG.length());
        raf.write(comprimir(hash));
        raf.close();
        //Modificar el punter del principi per a que apunti a la informació extra del savegame
        //Escriure un punter al final del fitxer que apunti a on està el hashmap
        //Escriure informació extra com per exemple data de finalització del joc
        
    }

    private static HashMap<String,String> infoSaveGame(String name) throws IOException, DataFormatException, ClassNotFoundException {
        File SaveG = new File(profileDir + name);
        RandomAccessFile raf = new RandomAccessFile(SaveG, "r");
        int obj_pos = (int)raf.readLong();
        raf.seek(obj_pos);
        byte[] buf = new byte[(int)SaveG.length() - obj_pos];
        raf.readFully(buf);
        raf.close();
        return (HashMap<String, String>) descomprimir(buf);
    }

    /* PRE: El savegame existeix (és de la llista de savegames)
       POST: Carrega el savegame a memòria */
    public static void loadSaveGame(String name) throws IOException, ClassNotFoundException, DataFormatException {
        //Llegir el punter per a buscar a on està el punter al hashmap
        /* Copiar el savegame reconstruint el punter del hashmap i tota la resta
         excepte la informació extra */
        synchronized(writeQueue) {
            writeQueue.entrySet().removeAll(writeQueue.entrySet());
        }
        File SaveG = new File(profileDir + name);
        profile = File.createTempFile(name, null, new File(profileDir));
        profile.deleteOnExit();
        RandomAccessFile raf = new RandomAccessFile(SaveG, "r");
        Long obj_pos = raf.readLong();
        Integer obj_in = new Long(Long.rotateRight(obj_pos, Integer.SIZE)).intValue();
        raf.seek(obj_in);
        obj_pos = raf.readLong();
        raf.close();
        profileRaf = new java.io.RandomAccessFile(profile, "rws");
        profileIn = new FileOutputStream(profile).getChannel();
        FileChannel SaveCh = new FileInputStream(SaveG).getChannel();
        //Copiem tot el fitxer fins a on comença el long i sobreescrivim el punter
        profileIn.transferFrom(SaveCh, 0, obj_in);
        profileIn.force(true);
        profileIn.close();
        SaveCh.close();
        profileRaf.writeLong(obj_pos);
        obj_in = new Long(Long.rotateRight(obj_pos, Integer.SIZE)).intValue();
        Integer obj_end = new Long(obj_pos).intValue();
        profileRaf.seek(obj_in);
        byte[] buf = new byte[obj_end - obj_in];
        profileRaf.readFully(buf);
        classMap = (HashMap<Class<?>, Long>) descomprimir(buf);
        classHashSize = new Long(obj_end - obj_in);
        classHashPos = new Long(obj_in);
        objMap = new HashMap<Long, TreeMap<String, Long>>();
        for(Long obj_clas : classMap.values()) {
            obj_in = new Long(Long.rotateRight(obj_clas, Integer.SIZE)).intValue();
            obj_end = new Long(obj_clas).intValue();
            profileRaf.seek(obj_in);
            buf = new byte[obj_end - obj_in];
            profileRaf.readFully(buf);
            objMap.put(obj_clas, (TreeMap<String, Long>) descomprimir(buf));
        }
        profileIn = new FileInputStream(profile).getChannel();
    }

    //TODO: A veure si es pot trobar una altre forma de fer això
    private static byte[] comprimir(Object obj) throws FileNotFoundException, IOException {
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        java.util.zip.GZIPOutputStream gos = new java.util.zip.GZIPOutputStream(bStream, java.util.zip.Deflater.BEST_COMPRESSION);
        ObjectOutputStream oStream = new ObjectOutputStream( gos );
        oStream.writeObject ( obj );
        oStream.flush();
        oStream.close();
        return bStream.toByteArray();
    }

    private static Object descomprimir(Object obj) throws IOException, DataFormatException, ClassNotFoundException {
        ByteArrayInputStream bStream = new java.io.ByteArrayInputStream((byte[]) obj);
        java.util.zip.GZIPInputStream iis = new java.util.zip.GZIPInputStream(bStream);
        ObjectInputStream oStream = new ObjectInputStream(iis);
        return oStream.readObject();
    }

    public void beginWrite(Object o) {
        writeQueue.put(o.toString(),o);
        synchronized(writeQueue) {
            writeQueue.notify();
        }
    }
    public void flush() throws InterruptedException {
        synchronized(writeQueue) {
            if(!writeQueue.isEmpty())writeQueue.wait();
        }
    }

    private final static java.util.LinkedHashMap<String,Object> writeQueue = new java.util.LinkedHashMap<String,Object>();

    //Abans de destruir el thread s'hauria de fer un flush
    public synchronized void run() {
        try {
            while(true) {
                synchronized(writeQueue){
                    while(writeQueue.isEmpty()) {
                        writeQueue.wait();
                    }
                    try {
                        java.util.Map.Entry<String,Object> entry = writeQueue.entrySet().iterator().next();
                        write(entry.getValue());
                        writeQueue.remove(entry.getKey());
                        if(writeQueue.isEmpty()) writeQueue.notify();
                    } catch (IOException ex) {
                        //Estarem intentant escrivint de per vida
                        Logger.getLogger(IO.class.getName()).log(Level.WARNING, null, ex);
                    }
                }
            }
        } catch (InterruptedException ex) {
        }
    }
    public static void canviaDataDir(String DataDir) throws IOException {
        File file = new File(DataDir);
        if(file.exists()) {
            if(file.isDirectory()) {
                dataFolder = DataDir + fileSeparator;
            } else throw new IOException("Data Folder: " + DataDir + " isn't a directory.");
        } else throw new IOException("Data Folder: " + DataDir + " doesn't exist.");
    }
    public static void canviaLanguageDir(String LocalizedDataDir) throws IOException {
        File file = new File(LocalizedDataDir);
        if(file.exists()) {
            if(file.isDirectory()) {
                localizedDataFolder = LocalizedDataDir + fileSeparator;
            } else throw new IOException("Localized Data Folder: " + LocalizedDataDir + " isn't a directory.");
        } else throw new IOException("Localized Data Folder: " + LocalizedDataDir + " doesn't exist.");
    }

    //Van per line number
    private static HashMap<Class<?>,Long> locClassMap;
    private static HashMap<Long,HashMap<String,Long>> locObjMap;
    private static RandomAccessFile lfr;
    private static File tempLangFile;
    public static <T> HashMap<String,String> readObjLocalizedData(Class<T> obj_class, String name) throws IOException, NoSuchFieldException, ClassNotFoundException {
        Long class_lines = locClassMap.get(obj_class);
        if(class_lines == null) throw new java.lang.ClassNotFoundException(obj_class.toString());
        Long obj_lines = locObjMap.get(class_lines).get(name);
        if(obj_lines == null) throw new java.lang.NoSuchFieldException(name);
        Integer obj_start = new Long(Long.rotateRight(obj_lines, Integer.SIZE)).intValue();
        Integer obj_end = new Long(obj_lines).intValue();
        HashMap<String,String> atr = new HashMap<String,String>();
        while(obj_start < obj_end) {
            lfr.seek(obj_start);
            String[] readLine = lfr.readLine().split("=");
            atr.put(readLine[0].trim(), readLine[1].trim());
            obj_start = (int) lfr.getFilePointer();
        }
        return atr;
    }
    public static void seleccionaLanguage(String lang) throws IOException, ClassNotFoundException {
        File file = new File(localizedDataFolder + lang + ".txt");
        if(file.exists()) {
            if(!file.isDirectory()) {
                tempLangFile = File.createTempFile(lang, null, new File(localizedDataFolder));
                tempLangFile.deleteOnExit();
                copyFile(file,tempLangFile);
                lfr = new java.io.RandomAccessFile(tempLangFile, "r");
                String line = lfr.readLine();
                Integer classe = 0;
                Class<?> curr_class = null;
                Integer class_start = 0;
                Integer obj_start = 0;
                Integer class_end = 0;
                Integer obj_end = 0;
                String curr_obj = "";
                HashMap<String,Long> class_hash = null;
                locClassMap = new HashMap<Class<?>,Long>();
                locObjMap = new HashMap<Long,HashMap<String,Long>>();
                while(line != null) {
                    if(line.endsWith("{")) {
                        //0 == dins de res, 1 == dins de class, 2 == dins de class i obj
                        if(classe == 0) {
                            class_hash = new HashMap<String,Long>();
                            classe = 1;
                            //Trim elimina espais del principi i del final de la string, però no d'entremig
                            curr_class = Class.forName(line.substring(0, line.lastIndexOf("{")).trim());
                            class_start = (int)lfr.getFilePointer();
                        } else { //classe == 1
                            obj_start = (int)lfr.getFilePointer();
                            classe = 2;
                            curr_obj = line.substring(0, line.lastIndexOf("{")).trim();
                        }
                    } else if(line.endsWith("}")) {
                        if(classe == 1) {
                            classe = 0;
                            class_end = (int)lfr.getFilePointer();
                            locClassMap.put(curr_class, (Long.rotateLeft(class_start, Integer.SIZE) | class_end));
                            locObjMap.put((Long.rotateLeft(class_start, Integer.SIZE) | class_end), class_hash);
                        } else {
                            classe = 1;
                            class_hash.put(curr_obj, (Long.rotateLeft(obj_start, Integer.SIZE) | obj_end));
                        }
                    }
                    if(classe == 2) obj_end = (int)lfr.getFilePointer();
                    line = lfr.readLine();
                }
                //Per a més seguretat els objectes haurien de tenir un idioma per defecte ja carregat
                /*
                 * Format: nomclasse{
                 *          Obj1{
                 *              Atr1=Val1
                 *              Atr2=Val2
                 *          }
                 *          Obj2{
                 *              Atr1=Val1
                 *              Atr2=Val2
                 *          }
                 *         }
                 */
            } else throw new IOException("Language: " + lang + " isn't a directory.");
        } else throw new IOException("Language: " + lang + " doesn't exist.");
    }
    public static ArrayList<String> obteLanguages() {
        return new ArrayList<String>(java.util.Arrays.asList(
            new File(localizedDataFolder).list(new java.io.FilenameFilter() {
                public boolean accept(File dir, String name) {
                    File file = new File(dir+fileSeparator+name);
                    return !name.equals("Plantilla.txt") && !file.isDirectory() && !file.isHidden() && name.endsWith(".txt");
                }
            })
        ));
    }
    public static void canviaProfilesDir(String ProfilesDir) throws IOException {
        File file = new File(ProfilesDir);
        if(file.exists()) {
            if(file.isDirectory()) {
                profileSFolder = ProfilesDir + fileSeparator;
            } else throw new IOException("Profile: " + ProfilesDir + " isn't a directory.");
        } else {
            file.mkdir();
            if(file.exists()) profileSFolder = ProfilesDir + fileSeparator;
            else throw new IOException("Couldn't create profile folder: " + ProfilesDir);
        }
    }
    public static String obteDataDir() {
        return dataFolder;
    }
    public static String obteProfilesDir() {
        return profileSFolder;
    }
    public static String obteLanguagesDir() {
        return localizedDataFolder;
    }
    public static String obteLog() {
        return logFile;
    }

    private static class EntryImpl implements Entry<String, Long> {

        public EntryImpl(String key, Long value) {
            this.key = key;
            this.value = value;
        }
        String key;
        Long value;

        public String getKey() {
            return key;
        }

        public Long getValue() {
            return value;
        }

        public Long setValue(Long value) {
            Long tempvalue = this.value;
            this.value = value;
            return tempvalue;
        }
    }
}
