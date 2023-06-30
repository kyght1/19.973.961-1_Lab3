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
public class route extends element {
    private int id_father;
    private ArrayList<Object> Sons = new ArrayList<>();

    public route(int id_father, int id, String name) {
        super(id, name);
        this.id_father = id_father;
    }

    public int getId_father() {
        return id_father;
    }

    public void setId_father(int id_father) {
        this.id_father = id_father;
    }

    public ArrayList<Object> getSons() {
        return Sons;
    }

    public void setSons(ArrayList<Object> Sons) {
        this.Sons = Sons;
    }

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "route{" + "id_father=" + id_father +", Name =" + name + ", id =" + id + ", Sons=" + Sons + '}';
    }

    

    

   

    
}
