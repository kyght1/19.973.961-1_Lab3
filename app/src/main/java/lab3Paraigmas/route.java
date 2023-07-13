/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3Paraigmas;
import java.util.ArrayList;
/**
 *
 * @author Toshiba
 */
public class Route extends element {
   
    private ArrayList<String> Sons = new ArrayList<>();

    public Route(int id_father, int id, String name) {
        super(id, name);
        this.id_father = id_father;
    }

    @Override
    public int getId_father() {
        return id_father;
    }

    @Override
    public void setId_father(int id_father) {
        this.id_father = id_father;
    }

    public ArrayList<String> getSons() {
        return Sons;
    }

    public void setSons(ArrayList<String> Sons) {
        this.Sons = Sons;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "route{" + "id_father= " + id_father +", Name= " + name + ", id =" + id + "," + Sons + '}';
    }

    

    

   

    
}
