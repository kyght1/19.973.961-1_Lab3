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

    int contador = 0;
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
    @Override
    public void addDrive(String letter, String Name, int capacity) {
        /*creo la unidad*/
        if (!unityExist(getDrives(), letter)) {
            drive newDrive = new drive(letter, capacity, contador + 1, Name);
            getDrives().add(newDrive);
            /*creo la ruta*/
            route newRoute = new route(0, contador + 1, letter);
            contador++;
            getRoutes().add(newRoute);
            System.out.println("Unidad anadida con exito.");

        } else {
            System.out.println("La letra de la unidad que se intenta crear ya existe en el sistema.");
        }
    }

    /**
     * Método que permite registrar un nuevo usuario al sistema.El nombre de
     * usuario es único y no puede ser duplicado.
     *
     * @param username
     */
    @Override
    public void register(String username) {
        /*compruebo la existencia del username en la lista de usuarios*/
        boolean state = false;
        for (int i = 0; i < getUsers().size(); i++) {
            if (getUsers().get(i).getName().equals(username)) {
                state = true;

            }
        }

        if (state == false) {  //no se encuentra el usuario
            user newUser = new user(username, 0);
            getUsers().add(newUser);
            System.out.println("Usuario ingresado con exito.");

        } else {
            System.out.println("El usuario ya existe, no se realizan cambios en el sistema");
        }

    }

    /**
     * Metodo login Método que permite iniciar sesión con un usuario del
     * sistema, solo si éste existe.
     *
     * @param username
     */
    @Override
    public void login(String username) {
        /*usando getter y setters correctamente*/
        for (int i = 0; i < getUsers().size(); i++) {
            if (getUsers().get(i).getName().equals(username) && getUsers().get(i).getState() == 0) {
                getUsers().get(i).setState(1);
                System.out.println("Usuario " + username + " logueado correctamente");

            }else{
                 getUsers().get(i).setState(0);
                }

        }
        System.out.println("El usuario no existe o bien ya esta logueado");

    }
    
    /**
     *Metodo logout: Método que permite cerrar la sesión de un usuario en el sistema.

     */
    @Override
    public void logout(){
        /*elimino al usuario logueado*/
        for (int i = 0; i < getUsers().size(); i++) {
            if (getUsers().get(i).getState() == 1) {
                /*modifico con setter*/
                getUsers().get(i).setState(0);
                System.out.println("El usuario se ha deslogueado correctamente");

            }
        }
       
        
    }
    
    /**
     * metodo switchDrive: Permite fijar la unidad en la que el usuario realizará acciones.El método solo debe funcionar cuando hay un usuario con sesión iniciada en el sistema a partir del método de login.
     * @param letter
     */
    public void switchDrive(String letter){
        
    }

}
