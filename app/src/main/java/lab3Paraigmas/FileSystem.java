/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lab3Paraigmas;

/**
 *
 * @author Toshiba
 */
/*interfaz de system*/
public interface FileSystem {
    public void addDrive(String letter, String name, int capacity);
    public void register(String username);
    public void login(String username);
    public void logout();
}
