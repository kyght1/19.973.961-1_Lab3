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
public class system implements FileSystem {

    /*atributo de la clase system*/
    private String name;
    private ArrayList<drive> Drives = new ArrayList<>();
    private ArrayList<user> Users = new ArrayList<>();
    private ArrayList<route> Routes = new ArrayList<>();

    /*construtor de system*/
    public system(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<drive> getDrives() {
        return Drives;
    }

    public void setDrives(ArrayList<drive> Drives) {
        this.Drives = Drives;
    }

    public ArrayList<user> getUsers() {
        return Users;
    }

    public void setUsers(ArrayList<user> Users) {
        this.Users = Users;
    }

    public ArrayList<route> getRoutes() {
        return Routes;
    }

    /**
     *
     * @param Routes
     */
    public void setRoutes(ArrayList<route> Routes) {
        this.Routes = Routes;
    }

    @Override
    public String toString() {
        return "system{" + "name=" + name + ", Drives=" + Drives + ", Users=" + Users + ", Routes=" + Routes + '}';
    }

    /*metodos auxiliares*/
    /**
     * metodo para comprobar la existencia de una unidad
     *
     * @param Drives
     * @param letter
     * @return
     */
    public boolean unityExist(ArrayList<drive> Drives, String letter) {
        for (int x = 0; x < Drives.size(); x++) {
            if (Drives.get(x).getLetter() == null ? letter == null : Drives.get(x).getLetter().equals(letter)) {
                return true;
            }
        }
        return false;
    }

    /**
     * metodo addDrive
     *
     * @param letter
     * @param Name
     * @param capacity
     */
    public void addDrive(String letter, String Name, int capacity) {
        /*creo la unidad*/
        if (!unityExist(getDrives(), letter)) {
            drive newDrive = new drive(letter, capacity, 1, Name);
            getDrives().add(newDrive);
            /*creo la ruta*/
            route newRoute = new route(0, 1, letter);
            getRoutes().add(newRoute);
            System.out.println("Unidad añadida con exito.");

        } else {
            System.out.println("La letra de la unidad que se intenta crear ya existe en el sistema.");
        }
    }

    @Override
    public void addDrive() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
