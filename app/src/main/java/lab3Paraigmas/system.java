/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3Paraigmas;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private LocalDateTime currentDate = java.time.LocalDateTime.now();
    //rodo fecha de creacion

    public LocalDateTime getCreationDate() {
        return currentDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.currentDate = creationDate;
    }

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
        return "system{" + "name=" + name + ", Drives=" + Drives + ", Users=" + Users + ", Routes=" + Routes + "creationDate= " + currentDate + '}';
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
     * Metodo para comprobar si existe algun usuario logueado
     *
     * @param Users
     * @return
     */
    public boolean existeUserLogged(ArrayList<user> Users) {

        for (int i = 0; i < Users.size(); i++) {
            if (Users.get(i).getState() == 1) {
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

            } else {
                getUsers().get(i).setState(0);
            }

        }

    }

    /**
     * Metodo logout: Método que permite cerrar la sesión de un usuario en el
     * sistema.
     *
     */
    @Override
    public void logout() {
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
     * metodo switchDrive: Permite fijar la unidad en la que el usuario
     * realizará acciones.El método solo debe funcionar cuando hay un usuario
     * con sesión iniciada en el sistema a partir del método de login.
     *
     * @param letter
     */
    @Override
    public void switchDrive(String letter) {
        /*primero, debe existir un usuario logueado*/
 /*si la lista de unidades no es nula*/
        if (getDrives() != null) {
            drive aux = null;
            route raux = null;
            if (existeUserLogged(getUsers())) {
                for (drive drive : getDrives()) {
                    if (drive.getLetter().equals(letter)) { //la muevo al inicio del array
                        aux = drive;

                    }
                }
                if (aux != null) {
                    getDrives().remove(aux);
                    getDrives().add(0, aux);

                } else {
                    System.out.println("La Unidad no existe");
                }
                for (route route : getRoutes()) {
                    if (route.getName().equals(letter)) {
                        raux = route;

                    }
                    if (raux != null) {
                        getRoutes().remove(raux);
                        getRoutes().add(0, raux);
                        System.out.println("Unidad cambiada con exito");

                    } else {
                        System.out.println("La Unidad no existe");
                    }
                }

            }

        } else {
            System.out.println("La Unidad no existe");
        }

    }

    /**
     * metodo mkdir (make directory): Método que permite crear directorio dentro
     * de una unidad a partir del nombre especificado.Internamente el método
     * añade datos relativos a usuario creador, fecha de creación, fecha de
     * última modificación y atributos de seguridad como los señalados en el
     * enunciado general
     *
     * @param folderName
     */
    @Override
    public void mkdir(String folderName) {
        /*todas las operaciones se realizan si hay un usuario logueado*/
        if (existeUserLogged(getUsers())) {

            /*obtengo la unidad actual*/
            drive currentDrive = getDrives().get(0);
            /*el data de la unidad actual*/
            ArrayList<Object> currentData = currentDrive.getData();
            /*la ruta actual*/
            route currentRoute = getRoutes().get(0);

            /*reviso los hijos de la ruta de actual para que no haya problema de
            unicidad*/
            if (!currentRoute.getSons().contains(folderName)) {

                /*puedo crear el directorio*/
                directory newDirectory = new directory(currentRoute.getId(), currentDate, currentDate, new ArrayList<>(), new ArrayList<>(), "Hash", contador + 1, folderName);

                /*lo agrego a la data de la unidad*/
                currentData.add(newDirectory);
                /*lo agrego como hijo de la ruta*/
                currentRoute.getSons().add(folderName);
                /*creo una ruta para el directorio*/
                route newRoute = new route(currentRoute.getId(), contador + 1, folderName);
                getRoutes().add(newRoute);
                contador++;

                System.out.println("Directorio creado con exito");
            }
            System.out.println("El nombre de directorio ya existe.");
        }
        System.out.println("No hay usuario logueado");

    }

    /**
     * metodo cd Método que permite cambiar la ruta (path) donde se realizarán
     * las próximas operaciones.cd permite cambiarse a un directorio
     * especificado a partir de la ruta señalada en un String. Además, contará
     * con los comodines especiales: “.”, “..” y “/” (se usa slash en lugar de
     * backslash) que permitirán referirse a la carpeta actual, regresar a la
     * carpeta del nivel anterior siguiendo la ruta actual del usuario y volver
     * a la raíz de la unidad respectivamente.
     *
     * @param path
     */
    @Override
    public void cd(String path) {
        
    }

}
