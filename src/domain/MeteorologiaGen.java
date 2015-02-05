package domain;

import java.util.HashMap;
import javax.vecmath.Vector3d;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JOSEP
 */

public class MeteorologiaGen implements Cloneable, java.io.Serializable {

    protected HashMap<String,String> desc;
    protected Double intensitat;

    public MeteorologiaGen(Double intensitat_inicial, HashMap<String,String> desc) {
        this.intensitat = intensitat_inicial;
        this.desc = desc;
    }
    @Override
    public String toString() {
        return desc.get("Nom");
    }
    public javax.vecmath.Vector3d Vector() throws NoSuchFieldException {
        //Si es fes servir el jdk 1.7 es podria fer un switch de string
        if(desc.get("Nom").equals("Pluja")) {
            return new Vector3d((intensitat/2500),(intensitat/2500),(intensitat/2500)/10);
        } else if(desc.get("Nom").equals("Sol")) {
            return new Vector3d((intensitat/2500)/20,(intensitat/2500)/20,-(intensitat/2500)/5);
        } else if(desc.get("Nom").equals("Neu")) {
            return new Vector3d((intensitat/2500),intensitat > 10 ? -(intensitat/2500) : (intensitat/2500),(intensitat/2500));
        } else if(desc.get("Nom").equals("Nuvol")) {
            return new Vector3d(-(intensitat/2500),(intensitat/2500),-(intensitat/2500));
        } else if (desc.get("Nom").equals("Vent")) {
            return new Vector3d((intensitat/2500)*4,(intensitat/2500)*5,-(intensitat/2500)*2);
        } else throw new java.lang.NoSuchFieldException(desc.get("Nom"));
    }
    public Double intensitat() {
        return this.intensitat;
    }
    public void canviaIntensitat(Double nova_intensitat) {
        this.intensitat = nova_intensitat;
    }
    public HashMap<String,String> descripcio() {
        return desc;
    }
}
