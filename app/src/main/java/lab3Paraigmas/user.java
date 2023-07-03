/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3Paraigmas;

/*No necesariamente se crea una interfaz*/
public class user {
    private String name;
    private int state;

    @Override
    public String toString() {
        return "user{ "+ name + " , " + state + '}';
    }
    

    public user(String name, int state) {
        this.name = name;
        this.state = state;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the state
     */
    public int getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(int state) {
        this.state = state;
    }
    
    
    
    
}
