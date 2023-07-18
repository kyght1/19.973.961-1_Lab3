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
public class Route extends element{
   
    private ArrayList<String> Sons = new ArrayList<>();
    private String StringForm;

    public Route(int id_father, int id, String name, String StringForm) {
        super(id, name);
        this.id_father = id_father;
        this.StringForm= StringForm;
    }
    
   @Override
   public Route clone() throws CloneNotSupportedException{
       Route newRoute= (Route) super.clone();
       newRoute.setSons(newRoute.getSons());
       newRoute.setStringForm(newRoute.getStringForm());
       return newRoute;
   }  

    public String getStringForm() {
        return StringForm;
    }

    public void setStringForm(String StringForm) {
        this.StringForm = StringForm;
    }

    

    public ArrayList<String> getSons() {
        return Sons;
    }

    public void setSons(ArrayList<String> Sons) {
        this.Sons = Sons;
    }

    @Override
    public String toString() {
        return "Route{" + "id_father= " + id_father +", Name= " + name + ", id =" + id + "," + Sons + " , "+StringForm +'}';
    }

    

    

   

    
}
