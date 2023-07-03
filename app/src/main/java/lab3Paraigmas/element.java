/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3Paraigmas;

/**
 *
 * @author Toshiba
 */
public class element implements OperationsByElement {
    public int id;
    public String name;

    public element(int id, String name) {
        this.id = id;
        this.name = name;
    }
    @Override
    public void createElement() {
        System.out.println("CrearElemento");

    }
    
    
}
