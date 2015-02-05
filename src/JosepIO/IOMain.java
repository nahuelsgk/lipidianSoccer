/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package JosepIO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

/**
 *
 * @author JOSEP
 */
public class IOMain {

    private static void printArray() {
        System.out.println("Objectes: ");
        for(int i = 0; i < objects.size(); ++i) {
            System.out.println("ID: " + i + ", Descripció: " + objects.get(i).toString());
        }
    }
    static ArrayList<Object> objects = new ArrayList<Object>() { {
        add("Hola");
        add(new Long(65655));
        add(new Integer(89989));
    }};
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /* Cada cop que s'afegeixi una classe nova a objects s'ha d'afegir a aquí
         * Els savegames no són retro compatibles, així que si es canvien les classes s'han
         * de tornar a fer de nou
         */
    	
    	Document XML = crearXML();
        ArrayList<Class<?>> array = new ArrayList<Class<?>>() {
            {
                add(Document.class);
            }
        };
        try {
            IO.init(array);
        } catch (IOException ex) {
            System.out.println("Init IO error: " + ex.getLocalizedMessage());
            System.exit(-1566);
        }
        DataWriter.init(IO.obteDataDir());
        //Domain.StaticRandom.init(new java.util.Date().getTime());
        ArrayList<Integer> arraytmp = new ArrayList<Integer>();
        ArrayList<Document> arraytmp2 = new ArrayList<Document>();
        objects.add(XML);
        try {
            //for(int i = 20; i <100; i+=2) arraytmp.add(i);
            //DataWriter.write(Integer.class, arraytmp);
            //for(int i = 0; i < 100; ++i) 
            arraytmp2.add(XML);
            DataWriter.write(Document.class, arraytmp2);
        } catch(Exception ex) {
            System.out.println("Data writer peta: " + ex.getLocalizedMessage());
        }
        arraytmp = null;
        arraytmp2 = null; //Per a demostrar que no llegim els objectes d'aquí
        Runtime.getRuntime().gc();
        System.out.println("ATENCIÓ: Degut a limitacions de java no és possible assegurar el correcte tractament de"
                + "\n carpetes amb caràcters que no siguin ascii, així que en el pitjor dels casos és recomenable"
                + "\n executar el programa des de l'arrel del disc");
        System.out.println("1: Gestionar Perfils");
        System.out.println("2: Gestionar Carpetes");
        System.out.println("3: Configuracions vàries");
        System.out.println("4: Gestionar partides guardades");
        System.out.println("9: Veure aquest menú");
        System.out.println("0: Sortir");
        System.out.println();
        java.io.BufferedReader reader = null;
        try {
            reader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in, "UTF8"));
        } catch (UnsupportedEncodingException ex) {
            System.out.println("Error mentre s'inicialitzava el lector de dades de consola, el joc de proves finalitarà la seva execució: " + ex.getLocalizedMessage());
            System.exit(-1567);
        }
        while(true) {
            boolean b = false;
            String readLine = null;
            Integer number = 0;
            System.out.println();
            System.out.print("Introdueixi un valor (1-9): ");
            try {
                readLine = reader.readLine();
            } catch (IOException ex) {
                System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                continue;
            }
            try {
                number = Integer.parseInt(readLine);
            } catch (NumberFormatException ex) {
                System.out.println(readLine + " no és un valor correcte (1-9)");
                continue;
            }
            switch(number) {
                case 1:
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println("Gestionar perfils, OPCIONS: ");
                    System.out.println("1: Crear Perfil");
                    System.out.println("2: Esborrar perfil");
                    System.out.println("3: Sel·leccionar Perfil");
                    System.out.println("4: Informació sobre un Perfil");
                    System.out.println("5: Actualitzar la informació sobre el perfil actual");
                    System.out.println("6: Veure perfils");
                    System.out.println("9: Veure aquest menú");
                    System.out.println("0: Enrere");
                    while(!b) {
                        System.out.println();
                        System.out.print("Introdueixi un valor (1-9): ");
                        try {
                            readLine = reader.readLine();
                        } catch (IOException ex) {
                            System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                            continue;
                        }
                        try {
                            number = Integer.parseInt(readLine);
                        } catch (NumberFormatException ex) {
                            System.out.println(readLine + " no és un valor correcte (1-9)");
                            continue;
                        }
                        switch(number) {
                            case 1:
                                System.out.println("Introdueixi el nom del perfil, atenció ha de ser ÚNIC!");
                                try {
                                    readLine = reader.readLine();
                                } catch (IOException ex) {
                                    System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                try {
                                    IO.createProfile(readLine);
                                } catch (IOException ex) {
                                    System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                } catch (ClassNotFoundException ex) {
                                    System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                break;
                            case 2:
                                System.out.println("Introdueixi el nom del perfil, atenció ha d'existir en la carpeta de perfils sel·leccionada!");
                                try {
                                    readLine = reader.readLine();
                                } catch (IOException ex) {
                                    System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                try {
                                    IO.removeProfile(readLine);
                                } catch (IOException ex) {
                                    System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                break;
                            case 3:
                                System.out.println("Introdueixi el nom del perfil, atenció ha d'existir en la carpeta de perfils sel·leccionada!");
                                try {
                                    readLine = reader.readLine();
                                } catch (IOException ex) {
                                    System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                try {
                                    IO.selectProfile(readLine);
                                } catch (IOException ex) {
                                    System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                } catch (ClassNotFoundException ex) {
                                    System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                break;
                            case 4:
                                System.out.println("Introdueixi el nom del perfil, atenció ha d'existir en la carpeta de perfils sel·leccionada!");
                                try {
                                    readLine = reader.readLine();
                                } catch (IOException ex) {
                                    System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                try {
                                    System.out.println(IO.infoProfile(readLine).toString());
                                } catch (FileNotFoundException ex) {
                                    System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                } catch (IOException ex) {
                                    System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                break;
                            case 5:
                                System.out.println("Introdueixi els valors a guardar (clau=valor, les claus no es poden repetir), introdueixi una línia en blanc per a parar. Un atribut necessari és el de nom, es sobreescriuràn els anteriors valors!");
                                HashMap<String,String> valors = new HashMap<String,String>();
                                while(!readLine.equals("")) {
                                    try {
                                        if(!readLine.contains("=")) throw new IOException(readLine + " no conté =");
                                        String[] split = readLine.split("=");
                                        valors.put(split[0], split[1]);
                                        System.out.println("S'ha afegit: " + split[0] + "=" + split[1]);
                                        readLine = reader.readLine();
                                    } catch (IOException ex) {
                                        System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                        try {
                                            readLine = reader.readLine();
                                        } catch(IOException ex1) {
                                            readLine = "";
                                        }
                                        continue;
                                    }
                                }
                                try {
                                    IO.updateCurrentProfile(valors);
                                } catch (FileNotFoundException ex) {
                                    System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                } catch (IOException ex) {
                                    System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                break;
                            case 6:
                                ArrayList<HashMap<String, String>> profiles = null;
                                try {
                                    profiles = IO.getProfiles();
                                } catch (IOException ex) {
                                    System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                for(HashMap<String, String> profile : profiles) {
                                    System.out.println("Perfil: " + profile.get("Nom") + System.getProperty("line.separator") + profile);
                                }
                                break;
                            default:
                            case 9:
                                System.out.println();
                                System.out.println();
                                System.out.println();
                                System.out.println("1: Crear Perfil");
                                System.out.println("2: Esborrar perfil");
                                System.out.println("3: Sel·leccionar Perfil");
                                System.out.println("4: Informació sobre un Perfil");
                                System.out.println("5: Actualitzar la informació sobre el perfil actual");
                                System.out.println("6: Veure perfils");
                                System.out.println("9: Veure aquest menú");
                                System.out.println("0: Enrere");
                                break;
                            case 0:
                                System.out.println("Enrere...");
                                b = true;
                                break;
                        }
                    }
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println("1: Gestionar Perfils");
                System.out.println("2: Gestionar Carpetes");
                System.out.println("3: Configuracions vàries");
                System.out.println("4: Gestionar partides guardades");
                System.out.println("9: Veure aquest menú");
                System.out.println("0: Sortir");
                break;
                case 2:
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println("Configuracions vàries, OPCIONS: ");
                    System.out.println("1: Canviar carpeta de dades");
                    System.out.println("2: Canviar carpeta d'idiomes");
                    System.out.println("3: Canviar carpeta de perfils");
                    System.out.println("4: Llegir text localitzat");
                    System.out.println("5: Canviar idioma");
                    System.out.println("6: Veure carpetes");
                    System.out.println("7: Veure idiomes");
                    System.out.println("9: Veure aquest menú");
                    System.out.println("0: Enrere");
                    while(!b) {
                        System.out.println();
                        System.out.print("Introdueixi un valor (1-9): ");
                        try {
                            readLine = reader.readLine();
                        } catch (IOException ex) {
                            System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                            continue;
                        }
                        try {
                            number = Integer.parseInt(readLine);
                        } catch (NumberFormatException ex) {
                            System.out.println(readLine + " no és un valor correcte (1-9)");
                            continue;
                        }
                        switch(number) {
                            case 1:
                                System.out.println("Introdueixi el camí de la nova carpeta de dades (sense / al final), atenció ha d'existir i ser un directori!");
                                try {
                                    readLine = reader.readLine();
                                } catch (IOException ex) {
                                    System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                try {
                                    IO.canviaDataDir(readLine);
                                } catch (IOException ex) {
                                    System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                break;
                            case 2:
                                System.out.println("Introdueixi el camí de la nova carpeta d'idiomes (sense / al final), atenció ha d'existir i ser un directori!");
                                try {
                                    readLine = reader.readLine();
                                } catch (IOException ex) {
                                    System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                System.out.println(readLine);
                                try {
                                    IO.canviaLanguageDir(readLine);
                                } catch (IOException ex) {
                                    System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                break;
                            case 3:
                                System.out.println("Introdueixi el camí de la nova carpeta de perfils (sense / al final), atenció ha d'existir i ser un directori!");
                                try {
                                    readLine = reader.readLine();
                                } catch (IOException ex) {
                                    System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                try {
                                    IO.canviaProfilesDir(readLine);
                                } catch (IOException ex) {
                                    System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                break;
                            case 4:
                                System.out.println("Introdueixi el nom complet de la classe (de l'estil java.lang.String), atenció ha d'existir!");
                                try {
                                    readLine = reader.readLine();
                                    if(readLine != null && readLine.equals("")) readLine = null;
                                } catch (IOException ex) {
                                    System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                System.out.println("Introdueixi el nom de l'objecte, atenció ha d'existir!");
                                String readLine2 = null;
                                try {
                                    readLine2 = reader.readLine();
                                } catch (IOException ex) {
                                    System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                try {
                                    System.out.println(IO.readObjLocalizedData(Class.forName(readLine), readLine2));
                                } catch (Exception ex) {
                                    System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                break;
                            case 5:
                                System.out.println("Introdueixi el nom del idioma (nomès el nom de l'idioma, sense el .txt) a sel·leccionar, atenció ha d'existir i ser un fitxer .txt diferent de Plantilla.txt!");
                                try {
                                    readLine = reader.readLine();
                                } catch (IOException ex) {
                                    System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                try {
                                    IO.seleccionaLanguage(readLine);
                                } catch (IOException ex) {
                                    System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                } catch (ClassNotFoundException ex) {
                                    System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                break;
                            case 6:
                                System.out.println();
                                System.out.println(IO.obteDataDir());
                                System.out.println(IO.obteLanguagesDir());
                                System.out.println(IO.obteProfilesDir());
                                break;
                            case 7:
                                try {
                                    System.out.println(IO.obteLanguages());
                                } catch (Exception ex) {
                                    System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                break;
                            default:
                            case 9:
                                System.out.println();
                                System.out.println();
                                System.out.println();
                                System.out.println("1: Canviar carpeta de dades");
                                System.out.println("2: Canviar carpeta d'idiomes");
                                System.out.println("3: Canviar carpeta de perfils");
                                System.out.println("4: Llegir text localitzat");
                                System.out.println("5: Canviar idioma");
                                System.out.println("6: Veure carpetes");
                                System.out.println("7: Veure idiomes");
                                System.out.println("9: Veure aquest menú");
                                System.out.println("0: Enrere");
                                break;
                            case 0:
                                System.out.println("Enrere...");
                                b = true;
                                break;
                        }
                    }
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println("1: Gestionar Perfils");
                System.out.println("2: Gestionar Carpetes");
                System.out.println("3: Configuracions vàries");
                System.out.println("4: Gestionar partides guardades");
                System.out.println("9: Veure aquest menú");
                System.out.println("0: Sortir");
                break;
                case 3:
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println("Configuracions vàries, OPCIONS: ");
                    System.out.println("1: Canviar camí de l'arxiu de configuració");
                    System.out.println("2: Canviar la configuració actual del programa");
                    System.out.println("3: Veure la configuració actual del programa");
                    System.out.println("4: Veure la configuració d'un altre arxiu (ha d'estar a la carpeta per defecte)");
                    System.out.println("5: Veure camí dels fitxers de log i configuració");
                    System.out.println("9: Veure aquest menú");
                    System.out.println("0: Enrere");
                    while(!b) {
                        System.out.println();
                        System.out.print("Introdueixi un valor (1-9): ");
                        try {
                            readLine = reader.readLine();
                        } catch (IOException ex) {
                            System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                            continue;
                        }
                        try {
                            number = Integer.parseInt(readLine);
                        } catch (NumberFormatException ex) {
                            System.out.println(readLine + " no és un valor correcte (1-9)");
                            continue;
                        }
                        switch(number) {
                            case 1:
                                System.out.println("Introdueixi el camí de la nova carpeta (sense / al final) on s'ubicarà el fitxer (no introduïr res equival al directori per defecte), atenció ha d'existir i ser un directori!");
                                try {
                                    readLine = reader.readLine();
                                    if(readLine != null && readLine.equals("")) readLine = null;
                                } catch (IOException ex) {
                                    System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                System.out.println("Introdueixi el nom del nou fitxer de configuració del programa, atenció ha d'existir!");
                                String readLine2 = null;
                                try {
                                    readLine2 = reader.readLine();
                                } catch (IOException ex) {
                                    System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                try {
                                    IO.changeSettings(readLine,readLine2);
                                } catch (IOException ex) {
                                    System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                break;
                            case 2:
                                System.out.println("Introdueixi els valors a guardar (clau=valor, les claus no es poden repetir), introdueixi una línia en blanc per a parar. Un atribut necessari és el de nom, es sobreescriuràn els anteriors valors!");
                                readLine = "";
                                try {
                                    readLine = reader.readLine();
                                } catch (IOException ex) {
                                    System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                HashMap<String,String> valors = new HashMap<String,String>();
                                while(!readLine.equals("")) {
                                    try {
                                        if(!readLine.contains("=")) throw new IOException(readLine + " no conté =");
                                        String[] split = readLine.split("=");
                                        valors.put(split[0], split[1]);
                                        System.out.println("S'ha afegit: " + split[0] + "=" + split[1]);
                                        readLine = reader.readLine();
                                    } catch (IOException ex) {
                                        System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                        try {
                                            readLine = reader.readLine();
                                        } catch(IOException ex1) {
                                            readLine = "";
                                        }
                                        continue;
                                    }
                                }
                                try {
                                    IO.updateSettings(valors);
                                } catch (FileNotFoundException ex) {
                                    System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                } catch (IOException ex) {
                                    System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                            case 3:
                                try {
                                    System.out.println(IO.getSettings(null).toString());
                                } catch (IOException ex) {
                                    System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                break;
                            case 4:
                                System.out.println("Introdueixi el nom del fitxer, atenció ha d'existir i estar a la carpeta per defecte!");
                                try {
                                    readLine = reader.readLine();
                                } catch (IOException ex) {
                                    System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                try {
                                    System.out.println(IO.getSettings(readLine).toString());
                                } catch (IOException ex) {
                                    System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                break;
                            case 5:
                                System.out.println();
                                System.out.println(IO.obteLog());
                                System.out.println(IO.settingsFile());
                                break;
                            default:
                            case 9:
                                System.out.println();
                                System.out.println();
                                System.out.println();
                                System.out.println("1: Canviar camí de l'arxiu de configuració");
                                System.out.println("2: Canviar la configuració actual del programa");
                                System.out.println("3: Veure la configuració actual del programa");
                                System.out.println("4: Veure la configuració d'un altre arxiu (ha d'estar a la carpeta per defecte)");
                                System.out.println("5: Veure camí dels fitxers de log i configuració");
                                System.out.println("9: Veure aquest menú");
                                System.out.println("0: Enrere");
                                break;
                            case 0:
                                System.out.println("Enrere...");
                                b = true;
                                break;
                        }
                    }
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println("1: Gestionar Perfils");
                System.out.println("2: Gestionar Carpetes");
                System.out.println("3: Configuracions vàries");
                System.out.println("4: Gestionar partides guardades");
                System.out.println("9: Veure aquest menú");
                System.out.println("0: Sortir");
                break;
                case 4:
                    try {
                        IO.getSaveGames();
                    } catch (NullPointerException ex) {
                        System.out.println("S'ha d'haver sel·leccionat un perfil abans");
                        continue;
                    }
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println("Gestionar partides guardades, OPCIONS: ");
                    System.out.println("1: Crear una nova partida guardada buida");
                    System.out.println("2: Crear una nova partida guardada amb les dades actuals (S'ha d'haver creat una partida buida abans o haver-ne carregat una!)");
                    System.out.println("3: Esborrar una partida guardada");
                    System.out.println("4: Carregar una partida guardada");
                    System.out.println("5: Veure partides guardades");
                    System.out.println("6: Llegir/Escriure objectes");
                    System.out.println("9: Veure aquest menú");
                    System.out.println("0: Enrere");
                    while(!b) {
                        System.out.println();
                        System.out.print("Introdueixi un valor (1-9): ");
                        try {
                            readLine = reader.readLine();
                        } catch (IOException ex) {
                            System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                            continue;
                        }
                        try {
                            number = Integer.parseInt(readLine);
                        } catch (NumberFormatException ex) {
                            System.out.println(readLine + " no és un valor correcte (1-9)");
                            continue;
                        }
                        switch(number) {
                            case 1:
                                System.out.println("Introdueixi el nom de la nova partida guardada, atenció no ha d'existir!");
                                try {
                                    readLine = reader.readLine();
                                    if(readLine != null && readLine.equals("")) readLine = null;
                                } catch (IOException ex) {
                                    System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                System.out.println("Introdueixi els valors a guardar (clau=valor, les claus no es poden repetir), introdueixi una línia en blanc per a parar. Un atribut necessari és el de nom, es sobreescriuràn els anteriors valors!");
                                String readLine2 = "";
                                try {
                                    readLine2 = reader.readLine();
                                } catch (IOException ex) {
                                    System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                HashMap<String,String> valors = new HashMap<String,String>();
                                while(!readLine2.equals("")) {
                                    try {
                                        if(!readLine2.contains("=")) throw new IOException(readLine2 + " no conté =");
                                        String[] split = readLine2.split("=");
                                        valors.put(split[0], split[1]);
                                        System.out.println("S'ha afegit: " + split[0] + "=" + split[1]);
                                        readLine2 = reader.readLine();
                                    } catch (IOException ex) {
                                        System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                        try {
                                            readLine2 = reader.readLine();
                                        } catch(IOException ex1) {
                                            readLine2 = "";
                                        }
                                        continue;
                                    }
                                }
                                try {
                                    IO.newEmptySaveGame(readLine, valors);
                                } catch (IOException ex) {
                                    System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                } catch (ClassNotFoundException ex) {
                                    System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                break;
                            case 2:
                                System.out.println("Introdueixi el nom de la nova partida guardada, atenció no ha d'existir!");
                                try {
                                    readLine = reader.readLine();
                                    if(readLine != null && readLine.equals("")) readLine = null;
                                } catch (IOException ex) {
                                    System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                System.out.println("Introdueixi els valors a guardar (clau=valor, les claus no es poden repetir), introdueixi una línia en blanc per a parar. Un atribut necessari és el de nom, es sobreescriuràn els anteriors valors!");
                                String readLine3 = "";
                                try {
                                    readLine3 = reader.readLine();
                                } catch (IOException ex) {
                                    System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                HashMap<String,String> valors2 = new HashMap<String,String>();
                                while(!readLine3.equals("")) {
                                    try {
                                        if(!readLine3.contains("=")) throw new IOException(readLine3 + " no conté =");
                                        String[] split = readLine3.split("=");
                                        valors2.put(split[0], split[1]);
                                        System.out.println("S'ha afegit: " + split[0] + "=" + split[1]);
                                        readLine3 = reader.readLine();
                                    } catch (IOException ex) {
                                        System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                        try {
                                            readLine3 = reader.readLine();
                                        } catch(IOException ex1) {
                                            readLine3 = "";
                                        }
                                        continue;
                                    }
                                }
                                try {
                                    IO.newSaveGame(readLine, valors2);
                                } catch (IOException ex) {
                                    System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                break;
                            case 3:
                                System.out.println("Introdueixi el nom de la partida guardada a esborrar, atenció ha d'existir!");
                                try {
                                    readLine = reader.readLine();
                                } catch (IOException ex) {
                                    System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                try {
                                    IO.removeSaveGame(readLine);
                                } catch (Exception ex) {
                                    System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                break;
                            case 4:
                                System.out.println("Introdueixi el nom de la partida guardada a carregar, atenció ha d'existir!");
                                try {
                                    readLine = reader.readLine();
                                } catch (IOException ex) {
                                    System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                try {
                                    IO.loadSaveGame(readLine);
                                } catch (Exception ex) {
                                    System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                break;
                            case 5:
                                try {
                                    HashMap<String, HashMap<String, String>> saveGames = IO.getSaveGames();
                                    for(Entry<String,HashMap<String,String>> entry : saveGames.entrySet()) {
                                        System.out.println(entry.getKey() + ":\n" + entry.getValue().toString());
                                    }
                                } catch (Exception ex) {
                                    System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                    continue;
                                }
                                break;
                            case 6:
                                System.out.println();
                                System.out.println();
                                System.out.println();
                                System.out.println("Llegir/Escriure objectes (s'ha d'haver sel·leccionat una partida guardada), OPCIONS: ");
                                System.out.println("1: Escriure un objecte");
                                System.out.println("2: Llegir un objecte");
                                System.out.println("3: Borrar un objecte");
                                System.out.println("4: Escriure vàris objectes");
                                System.out.println("5: Llegir vàris objectes de la mateixa classe");
                                System.out.println("6: Borrar vàris objectes");
                                System.out.println("7: Crear Instància");
                                System.out.println("8: Afegir un objecte a la cua d'escriptura");
                                System.out.println("9: Obligar l'escriptura de tots els objectes de l'instància");
                                System.out.println("10: Destruir instància");
                                System.out.println("19: Veure aquest menú");
                                System.out.println("0: Enrere");
                                Thread t = null;
                                IO inst = null;
                                boolean b1 = false;
                                while(!b1) {
                                    System.out.println();
                                    System.out.print("Introdueixi un valor (1-19): ");
                                    try {
                                        readLine = reader.readLine();
                                    } catch (IOException ex) {
                                        System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                        continue;
                                    }
                                    try {
                                        number = Integer.parseInt(readLine);
                                    } catch (NumberFormatException ex) {
                                        System.out.println(readLine + " no és un valor correcte (1-19)");
                                        continue;
                                    }
                                    switch(number) {
                                        case 1:
                                            printArray();
                                            System.out.println("Introdueixi l'identificador de l'objecte a escriure, atenció, la classe de l'objecte ha d'estar registrada i nomès s'accepten objectes únics (si està escrit un objecte amb la mateixa clau única (.toString()), aquest es sobreescriurà)!");
                                            try {
                                                readLine = reader.readLine();
                                                if(readLine != null && readLine.equals("")) readLine = null;
                                            } catch (IOException ex) {
                                                System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                                continue;
                                            }
                                            Integer number2 = 0;
                                            try {
                                                number2 = Integer.parseInt(readLine);
                                                if(number2 > objects.size()) throw new NumberFormatException();
                                            } catch (NumberFormatException ex) {
                                                System.out.println(readLine + " no és un valor correcte (1-" + objects.size() + ")");
                                                continue;
                                            }
                                            try {
                                                IO.write(objects.get(number2));
                                            }
                                            catch(Exception ex) {
                                                System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                                continue;
                                            }
                                            /* Pot arribar a ser molt llarg depenent de la classe, s'ha d'anar creant objectes
                                             * fins a arribar a tipus primitius (o objectes amb constructors buits)
                                             Class<?> newClass = null;
                                            try {
                                                newClass = Class.forName(readLine);
                                            } catch(Exception ex) {
                                                System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                                continue;
                                            }
                                            Constructor[] constr = newClass.getConstructors();
                                            System.out.println("Constructors disponibles: " + constr.toString());
                                            System.out.println("Introdueixi un número del 0 al " + constr.length);
                                            Integer number2 = 0;
                                            try {
                                                readLine = reader.readLine();
                                            } catch (IOException ex) {
                                                System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                                continue;
                                            }
                                            try {
                                                number2 = Integer.parseInt(readLine);
                                                if(number2 >= constr.length) throw new NumberFormatException();
                                            } catch (NumberFormatException ex) {
                                                System.out.println(readLine + " no és un valor correcte (1-19)");
                                                continue;
                                            }
                                            Class[] parameterTypes = constr[number2].getParameterTypes();
                                            Object[] objects = new Object[parameterTypes.length];
                                            boolean b2 = false;
                                            while(!b2) {

                                            }*/
                                            break;
                                        case 2:
                                            printArray();
                                            System.out.println("Introdueixi l'identificador de l'objecte a llegir, atenció, la classe"
                                                    + "\n de l'objecte ha d'estar registrada i l'objecte ha d'estar escrit (en una partida guardada "
                                                    + "\no a la carpeta de dades)!");
                                            try {
                                                readLine = reader.readLine();
                                                if(readLine != null && readLine.equals("")) readLine = null;
                                            } catch (IOException ex) {
                                                System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                                continue;
                                            }
                                            Integer number3 = 0;
                                            try {
                                                number3 = Integer.parseInt(readLine);
                                                if(number3 > objects.size()) throw new NumberFormatException();
                                            } catch (NumberFormatException ex) {
                                                System.out.println(readLine + " no és un valor correcte (1-" + objects.size() + ")");
                                                continue;
                                            }
                                            try {
                                                System.out.println(IO.read(objects.get(number3).getClass(),objects.get(number3).toString()));
                                            }
                                            catch(Exception ex) {
                                                System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                                continue;
                                            }
                                            break;
                                        case 3:
                                            printArray();
                                            System.out.println("Introdueixi l'identificador de l'objecte a esborrar, atenció, l'objecte"
                                                    + "\n ha d'estar escrit!");
                                            try {
                                                readLine = reader.readLine();
                                                if(readLine != null && readLine.equals("")) readLine = null;
                                            } catch (IOException ex) {
                                                System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                                continue;
                                            }
                                            Integer number4 = 0;
                                            try {
                                                number4 = Integer.parseInt(readLine);
                                                if(number4 > objects.size()) throw new NumberFormatException();
                                            } catch (NumberFormatException ex) {
                                                System.out.println(readLine + " no és un valor correcte (1-" + objects.size() + ")");
                                                continue;
                                            }
                                            try {
                                                IO.remove(objects.get(number4));
                                            }
                                            catch(Exception ex) {
                                                System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                                continue;
                                            }
                                            break;
                                        case 4:
                                            Integer number5 = 0;
                                            HashMap<Class<?>,ArrayList<?>> totsobj = new HashMap<Class<?>,ArrayList<?>>();
                                            printArray();
                                            System.out.println("-1 per a sortir");
                                            while(number5 != -1) {
                                                System.out.println("Introdueixi l'identificador de l'objecte a escriure, atenció, la classe de"
                                                        + "\n l'objecte ha d'estar registrada i nomès s'accepten objectes únics (si està escrit un"
                                                        + "\n objecte amb la mateixa clau única (.toString()), aquest es sobreescriurà)!");
                                                try {
                                                    readLine = reader.readLine();
                                                    if(readLine != null && readLine.equals("")) readLine = null;
                                                } catch (IOException ex) {
                                                    System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                                    continue;
                                                }
                                                try {
                                                    number5 = Integer.parseInt(readLine);
                                                    if(number5 > objects.size()) throw new NumberFormatException();
                                                } catch (NumberFormatException ex) {
                                                    System.out.println(readLine + " no és un valor correcte (1-" + objects.size() + ")");
                                                    continue;
                                                }
                                                if(number5 >= 0) {
                                                    if(totsobj.containsKey(objects.get(number5).getClass())) {
                                                        ((ArrayList<Object>)totsobj.get(objects.get(number5).getClass())).add(objects.get(number5));
                                                    } else {
                                                        ArrayList<Object> obj = new ArrayList<Object>();
                                                        obj.add(objects.get(number5));
                                                        totsobj.put(objects.get(number5).getClass(), obj);
                                                    }
                                                }
                                            }
                                            try {
                                                IO.writeAll(totsobj);
                                            }
                                            catch(Exception ex) {
                                                System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                                continue;
                                            }
                                            break;
                                        case 5:
                                            printArray();
                                            System.out.println("Introdueixi l'identificador de l'objecte a que pertany a la classe a llegir,"
                                                    + "\n atenció, la classe de l'objecte ha d'estar registrada!");
                                            try {
                                                readLine = reader.readLine();
                                                if(readLine != null && readLine.equals("")) readLine = null;
                                            } catch (IOException ex) {
                                                System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                                continue;
                                            }
                                            Integer number7 = 0;
                                            try {
                                                number7 = Integer.parseInt(readLine);
                                                if(number7 > objects.size()) throw new NumberFormatException();
                                            } catch (NumberFormatException ex) {
                                                System.out.println(readLine + " no és un valor correcte (1-" + objects.size() + ")");
                                                continue;
                                            }
                                            try {
                                                ArrayList<? extends Object> readAll = IO.readAll(objects.get(number7).getClass());
                                                for(Object obj : readAll) {
                                                    boolean b2 = false;
                                                    for(Object x : objects) {
                                                        if(x.toString().equals(obj.toString())) {
                                                            b2 = true;
                                                            break;
                                                        }
                                                    }
                                                    if(!b2) objects.add(obj);
                                                }
                                                System.out.println(readAll.toString());
                                            }
                                            catch(Exception ex) {
                                                System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                                continue;
                                            }
                                            break;
                                        case 6:
                                            Integer number6 = 0;
                                            HashMap<Class<?>,ArrayList<?>> totsobj2 = new HashMap<Class<?>,ArrayList<?>>();
                                            printArray();
                                            System.out.println("-1 per a sortir");
                                            while(number6 != -1) {
                                                System.out.println("Introdueixi l'identificador de l'objecte a esborrar, atenció, l'objecte"
                                                        + "\n ha d'estar escrit!");
                                                try {
                                                    readLine = reader.readLine();
                                                    if(readLine != null && readLine.equals("")) readLine = null;
                                                } catch (IOException ex) {
                                                    System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                                    continue;
                                                }
                                                try {
                                                    number6 = Integer.parseInt(readLine);
                                                    if(number6 > objects.size()) throw new NumberFormatException();
                                                } catch (NumberFormatException ex) {
                                                    System.out.println(readLine + " no és un valor correcte (1-" + objects.size() + ")");
                                                    continue;
                                                }
                                                if(number6 >= 0) {
                                                    if(totsobj2.containsKey(objects.get(number6).getClass())) {
                                                        ((ArrayList<Object>)totsobj2.get(objects.get(number6).getClass())).add(objects.get(number6));
                                                    } else {
                                                        ArrayList<Object> obj = new ArrayList<Object>();
                                                        obj.add(objects.get(number6));
                                                        totsobj2.put(objects.get(number6).getClass(), obj);
                                                    }
                                                }
                                            }
                                            try {
                                                IO.removeAll(totsobj2);
                                            }
                                            catch(Exception ex) {
                                                System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                                continue;
                                            }
                                            break;
                                        case 7:
                                            System.out.println("Està segur? Això destruirà la instància anterior (si aquesta estava creada)"
                                                    + "\n sense interrompre-la ni fer flush per tant, hi pot haver dades inconsistents i"
                                                    + "\n es quedarà un thread executant-se sense cap referència en el pitjor dels casos) (s/n o S/N)!");
                                            try {
                                                readLine = reader.readLine();
                                                if(readLine.toLowerCase().equals("s")) {
                                                    inst = new IO();
                                                    t = new Thread(inst);
                                                    t.start();
                                                } else if(readLine.toLowerCase().equals("n")) {
                                                    continue;
                                                } else throw new IOException("La resposta ha de ser s/n o S/N.");
                                            } catch (IOException ex) {
                                                System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                                continue;
                                            }
                                            break;
                                        case 8:
                                            printArray();
                                            System.out.println("Introdueixi l'identificador de l'objecte a escriure, atenció, la classe de"
                                                    + "\n l'objecte ha d'estar registrada i nomès s'accepten objectes únics (si està escrit"
                                                    + "\n un objecte amb la mateixa clau única (.toString()), aquest es sobreescriurà), a"
                                                    + "\n més l'instància ha d'estar inicialtizada i funcionant!");
                                            try {
                                                readLine = reader.readLine();
                                                if(readLine != null && readLine.equals("")) readLine = null;
                                            } catch (IOException ex) {
                                                System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                                continue;
                                            }
                                            Integer number8 = 0;
                                            try {
                                                number8 = Integer.parseInt(readLine);
                                                if(number8 > objects.size()) throw new NumberFormatException();
                                            } catch (NumberFormatException ex) {
                                                System.out.println(readLine + " no és un valor correcte (1-" + objects.size() + ")");
                                                continue;
                                            }
                                            try {
                                                inst.beginWrite(objects.get(number8));
                                            } catch(NullPointerException ex) {
                                                System.out.println("Error s'ha d'haver creat la instància: " + ex.getLocalizedMessage());
                                                continue;
                                            } catch(Exception ex) {
                                                System.out.println("Error, provi un altre cop: " + ex.getLocalizedMessage());
                                                continue;
                                            }
                                            break;
                                        case 9:
                                            System.out.println("Està segur? Això provocarà que el thread actual es quedi esperant fins que"
                                                    + "\n s'acabin d'escriure tots els objectes, l'instància ha d'estar creada i en funcionament"
                                                    + "\n (s/n o S/N)!");
                                            try {
                                                readLine = reader.readLine();
                                                if(readLine.toLowerCase().equals("s")) {
                                                    try {
                                                        inst.flush();
                                                    } catch(InterruptedException ex) {
                                                        System.out.println("Error mentre s'estava fent flush, el flux ha estat interromput: " + ex.getLocalizedMessage());
                                                    }
                                                } else if(readLine.toLowerCase().equals("n")) {
                                                    continue;
                                                } else throw new IOException("La resposta ha de ser s/n o S/N.");
                                            } catch (IOException ex) {
                                                System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                                continue;
                                            }
                                            break;
                                        case 10:
                                            System.out.println("Està segur? Això interromprà l'instància (si aquesta estava creada) sense"
                                                    + "\n fer flush (per tant, hi pot haver dades inconsistents) (s/n o S/N)!");
                                            try {
                                                readLine = reader.readLine();
                                                if(readLine.toLowerCase().equals("s")) {
                                                    t.interrupt();
                                                } else if(readLine.toLowerCase().equals("n")) {
                                                    continue;
                                                } else throw new IOException("La resposta ha de ser s/n o S/N.");
                                            } catch (IOException ex) {
                                                System.out.println("Error mentre es llegia la línia introduïda, provi un altre cop: " + ex.getLocalizedMessage());
                                                continue;
                                            }
                                            break;
                                        default:
                                        case 19:
                                            System.out.println();
                                            System.out.println();
                                            System.out.println();
                                            System.out.println("1: Escriure un objecte");
                                            System.out.println("2: Llegir un objecte");
                                            System.out.println("3: Borrar un objecte");
                                            System.out.println("4: Escriure vàris objectes");
                                            System.out.println("5: Llegir vàris objectes de la mateixa classe");
                                            System.out.println("6: Borrar vàris objectes");
                                            System.out.println("7: Crear Instància");
                                            System.out.println("8: Afegir un objecte a la cua d'escriptura");
                                            System.out.println("9: Obligar l'escriptura de tots els objectes de l'instància");
                                            System.out.println("10: Destruir instància");
                                            System.out.println("19: Veure aquest menú");
                                            System.out.println("0: Enrere");
                                            break;
                                        case 0:
                                            System.out.println("Enrere...");
                                            b1 = true;
                                            number = 9;
                                            break;
                                    }
                                }
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println("Gestionar partides guardades, OPCIONS: ");
                            System.out.println("1: Crear una nova partida guardada buida");
                            System.out.println("2: Crear una nova partida guardada amb les dades actuals (S'ha d'haver creat una partida buida abans o haver-ne carregat una!)");
                            System.out.println("3: Esborrar una partida guardada");
                            System.out.println("4: Carregar una partida guardada");
                            System.out.println("5: Veure partides guardades");
                            System.out.println("6: Llegir/Escriure objectes");
                            System.out.println("9: Veure aquest menú");
                            System.out.println("0: Enrere");
                            break;
                            default:
                            case 9:
                                System.out.println();
                                System.out.println();
                                System.out.println();
                                System.out.println("1: Crear una nova partida guardada buida");
                                System.out.println("2: Crear una nova partida guardada amb les dades actuals (S'ha d'haver creat una partida buida abans o haver-ne carregat una!)");
                                System.out.println("3: Esborrar una partida guardada");
                                System.out.println("4: Carregar una partida guardada");
                                System.out.println("5: Veure partides guardades");
                                System.out.println("6: Llegir/Escriure objectes");
                                System.out.println("9: Veure aquest menú");
                                System.out.println("0: Enrere");
                                break;
                            case 0:
                                System.out.println("Enrere...");
                                b = true;
                                break;
                        }
                    }
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println("1: Gestionar Perfils");
                System.out.println("2: Gestionar Carpetes");
                System.out.println("3: Configuracions vàries");
                System.out.println("4: Gestionar partides guardades");
                System.out.println("9: Veure aquest menú");
                System.out.println("0: Sortir");
                break;
                default:
                case 9:
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println("1: Gestionar Perfils");
                    System.out.println("2: Gestionar Carpetes");
                    System.out.println("3: Configuracions vàries");
                    System.out.println("4: Gestionar partides guardades");
                    System.out.println("9: Veure aquest menú");
                    System.out.println("0: Sortir");
                break;
                case 0:
                    return;
            }
        }
        /*
        IO.canviaDataDir(System.getProperty("user.dir") + System.getProperty("file.separator") + "Data" + System.getProperty("file.separator"));
        IO.canviaProfilesDir(System.getProperty("user.dir") + System.getProperty("file.separator") + "Profiles2" + System.getProperty("file.separator"));
        IO.canviaLanguageDir(System.getProperty("user.dir") + System.getProperty("file.separator") + "Language2" + System.getProperty("file.separator"));
        IO.changeSettings(null, "john.cfg");
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.OFF, "Hola");
        System.out.println(IO.obteLanguages());
        System.out.println(IO.settingsFile());
        System.out.println(IO.getSettings(null));
        System.out.println(IO.obteDataDir());
        System.out.println(IO.obteLanguagesDir());
        System.out.println(IO.obteLog());
        System.out.println(IO.obteProfilesDir());
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.putAll(IO.getSettings(null));
        hashMap.put("Adreça", "Matrix");
        IO.updateSettings(hashMap);
        System.out.println(IO.getSettings(null));
        IO.createProfile("Johna");
        IO.createProfile("John");
        ArrayList<HashMap<String, String>> profiles = IO.getProfiles();
        for(HashMap<String, String> profile : profiles) {
            System.out.println(profile);
        }
        
        IO.selectProfile("Johna");
        IO.removeProfile("Johna");
        IO.removeProfile("John");*/
    }
    
    public static Document crearXML(){
		try{
			//Crear un documento XML: incializas la api, construyes el documento y lo parseas
			DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
			DocumentBuilder parser = fact.newDocumentBuilder();
			Document document = parser.newDocument();
			return document;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}

}
