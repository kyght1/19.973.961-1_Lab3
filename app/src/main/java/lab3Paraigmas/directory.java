/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3Paraigmas;

import java.util.ArrayList;
import java.time.LocalDateTime;  
   

/**
 *
 * @author Toshiba
 */
public class directory extends element {

    private int id_father;
    private LocalDateTime creationDate;
    private LocalDateTime modDate;
    private ArrayList<String> atributes;
    private ArrayList<Object> dataDirectory;
    private String hash;

    public directory(int id_father, LocalDateTime creationDate, LocalDateTime modDate, ArrayList<String> atributes, ArrayList<Object> dataDirectory, String hash, int id, String name) {
        super(id, name);
        this.id_father = id_father;
        this.creationDate = creationDate;
        this.modDate = modDate;
        this.atributes = atributes;
        this.dataDirectory = dataDirectory;
        this.hash = hash;
    }

    public int getId_father() {
        return id_father;
    }

    public void setId_father(int id_father) {
        this.id_father = id_father;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getModDate() {
        return modDate;
    }

    public void setModDate(LocalDateTime modDate) {
        this.modDate = modDate;
    }

    public ArrayList<String> getAtributes() {
        return atributes;
    }

    public void setAtributes(ArrayList<String> atributes) {
        this.atributes = atributes;
    }

    public ArrayList<Object> getDataDirectory() {
        return dataDirectory;
    }

    public void setDataDirectory(ArrayList<Object> dataDirectory) {
        this.dataDirectory = dataDirectory;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "directory{" + "id_father=" + id_father + " , " + creationDate + " , " + modDate + " , " + atributes + " , " + dataDirectory + ", hash=" + hash + '}';
    }

   
   

}
