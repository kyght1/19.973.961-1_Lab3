/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3Paraigmas;

/**
 *
 * @author Toshiba
 */
public class element implements Cloneable{

    public int id;
    public String name;
    public int id_father;

    public element(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getId_father() {
        return id_father;
    }

    public void setId_father(int id_father) {
        this.id_father = id_father;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
