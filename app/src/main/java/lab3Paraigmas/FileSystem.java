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
    public void switchDrive(String letter);
    public void mkdir(String folderName);
    public void cd(String path);
    public void addFile(Archive Archive);
    public void del(String FileNamePattern);
    public void copy(String FileOrFolder, String TargetPath);
    public void move(String source, String targetPath);
}
