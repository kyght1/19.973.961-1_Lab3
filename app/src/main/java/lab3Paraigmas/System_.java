/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3Paraigmas;

import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Toshiba
 */
public class System_ implements FileSystem {

    int contador = 0;
    /*atributo de la clase system*/
    private String name;
    private ArrayList<Drive> Drives = new ArrayList<>();
    private ArrayList<User> Users = new ArrayList<>();
    private ArrayList<Route> Routes = new ArrayList<>();
    private String currentDate = java.time.LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
    //rodo fecha de creacion

    public String getCreationDate() {
        return currentDate;
    }

    public void setCreationDate(String creationDate) {
        this.currentDate = creationDate;
    }

    /*construtor de system*/
    public System_(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Drive> getDrives() {
        return Drives;
    }

    public void setDrives(ArrayList<Drive> Drives) {
        this.Drives = Drives;
    }

    public ArrayList<User> getUsers() {
        return Users;
    }

    public void setUsers(ArrayList<User> Users) {
        this.Users = Users;
    }

    public ArrayList<Route> getRoutes() {
        return Routes;
    }

    /**
     *
     * @param Routes
     */
    public void setRoutes(ArrayList<Route> Routes) {
        this.Routes = Routes;
    }

    @Override
    public String toString() {
        return "system{" + "name = " + name + "\nDrives=" + Drives + "\nUsers=" + Users + "\nRoutes=" + Routes + "\ncreationDate= " + currentDate + "\n}";
    }

    /*metodos auxiliares*/
    /**
     * metodo para comprobar la existencia de una unidad
     *
     * @param Drives
     * @param letter
     * @return
     */
    public boolean unityExist(ArrayList<Drive> Drives, String letter) {
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
    public boolean existeUserLogged(ArrayList<User> Users) {

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
            Drive newDrive = new Drive(letter, capacity, contador + 1, Name);
            getDrives().add(newDrive);
            /*creo la ruta*/
            Route newRoute = new Route(0, contador + 1, letter);
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
            User newUser = new User(username, 0);
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
            Drive aux = null;
            Route raux = null;
            if (existeUserLogged(getUsers())) {
                for (Drive drive : getDrives()) {
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
                for (Route route : getRoutes()) {
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
            Drive currentDrive = getDrives().get(0);
            /*el data de la unidad actual*/
            ArrayList<element> currentData = currentDrive.getData();
            /*la ruta actual*/
            Route currentRoute = getRoutes().get(0);

            /*reviso los hijos de la ruta de actual para que no haya problema de
            unicidad*/
            if (!currentRoute.getSons().contains(folderName)) {

                /*puedo crear el directorio*/
                Directory newDirectory = new Directory(currentRoute.getId(), currentDate, currentDate, new ArrayList<>(), new ArrayList<>(), "Hash", contador + 1, folderName);

                /*lo agrego a la data de la unidad*/
                currentData.add(newDirectory);
                /*lo agrego como hijo de la ruta*/
                currentRoute.getSons().add(folderName);
                /*creo una ruta para el directorio*/
                Route newRoute = new Route(currentRoute.getId(), contador + 1, folderName);
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
        /*los comandos funcionaran con ifs anidados...*/
 /*verifico que exista un usuario logueado*/
 /*para el uso del comando path1/path2 ha de estar ubicado en la ruta que 
 contenga a ambas*/
        if (existeUserLogged(getUsers())) {

            //comandos especiales
            if (path.equals("..")) { //regreso a la anterior
                Route aux = null;
                Route currentRoute = getRoutes().get(0);
                //busco la ruta con id padre de currentRoute
                for (Route route : getRoutes()) {
                    if (route.getId() == currentRoute.getId_father()) {
                        /*la agrego al inicio*/
                        aux = route;

                    }
                }
                /*realizo el cambio*/
                getRoutes().remove(aux);
                getRoutes().add(0, aux);

            }

            /*si es raiz*/
            if (path.equals("/")) {
                /*busco la ruta raiz*/
                Route aux = null;
                for (Route route : getRoutes()) {
                    if (route.getName().equals(getDrives().get(0).getLetter())) {
                        aux = route;

                    }
                }

                getRoutes().remove(aux);
                getRoutes().add(0, aux);

            } else {
                /*ahora comando general*/ //los cambios son relativos a la ruta actual visible
                String[] Saux = path.split("/");

                /*dos casos, si largo de arreglo anterior == 1 o bien 2*/
                if (Saux.length == 1) {
                    Route aux = getRoutes().get(0); //ruta actual

                    Route f1 = null;
                    for (String son : aux.getSons()) {
                        if (son.equals(Saux[0])) {

                            /*busco la ruta con id padre de aux.*/
                            for (Route route : getRoutes()) {
                                if (route.id_father == aux.id && route.getName().equals(Saux[0])) {
                                    f1 = route;

                                }
                            }

                        }
                    }
                    if (f1 != null) {
                        getRoutes().remove(f1);
                        getRoutes().add(0, f1);
                        System.out.println("Cambio realizado con exito");

                    }

                } else {
                    Route aux = getRoutes().get(0); //ruta actual

                    Route f1 = null;
                    Route f2 = null;

                    for (String son : aux.getSons()) {
                        if (son.equals(Saux[0])) {

                            /*busco la ruta con id padre de aux.*/
                            for (Route route : getRoutes()) {
                                if (route.id_father == aux.id && route.getName().equals(Saux[0])) {
                                    f1 = route;

                                }
                            }

                        }
                    }
                    /*repito el proceso para encontrar ahora el segundo hijo*/
                    for (String son : f1.getSons()) {
                        if (son.equals(Saux[1])) {

                            /*busco la ruta con id padre de aux.*/
                            for (Route route : getRoutes()) {
                                if (route.id_father == f1.id && route.getName().equals(Saux[1])) {
                                    f2 = route;

                                }
                            }

                        }
                    }

                    /*si no son nulos, encontre correctamente las rutas*/
                    if (f1 != null && f2 != null) {
                        /*realizo el movimiento de rutas*/
                        getRoutes().remove(f2);
                        getRoutes().add(0, f2);
                        System.out.println("Cambio realizado con exito");

                    } else {
                        System.out.println("No se pudo realizar el cambio de"
                                + "directorio solicitado");

                    }
                }

            }

        }

    }

    /**
     * TDA system - addFile: Método que permite añadir un archivo en la ruta
     * actual.
     *
     * @param Archive
     */
    @Override
    public void addFile(Archive Archive) {
        /*ahora un metodo para poder añadir un archivo al sistema, indexandolo
        como hijo, además, a la ruta actual*/
 /*reviso si no existe el nombre en los hijos de la ruta actual*/
        //ruta actual

        if (existeUserLogged(getUsers())) {
            Archive.setId(contador + 1);
            Archive.setFmod(currentDate);
            int existe = 0;
            /*variable archivo para saber si existe dicho nombre correspondiente a 
            un archivo*/
            element aux = null;

            Route currentRoute = getRoutes().get(0);
            Drive currentDrive = getDrives().get(0);
            String auxarchive = Archive.getName()+".";
            System.out.print(auxarchive);
            for (String son : currentRoute.getSons()) {
                if (auxarchive.equals(son)) {

                    /*reviso si es un archivo */
                    existe = 1;

                }
            }
            for (element data : currentDrive.getData()) {
                    if (data.getId_father() == currentRoute.getId() && data.getName().equals(Archive.getName())) {
                        System.out.println("voy ");
                        aux = data;

                    }
                }

            if (existe == 1 && aux != null) {
                
                
                currentDrive.getData().remove(aux);
                //quito la ruta
                Route aux2 = null;
                for (Route route : getRoutes()) {
                    if (route.getName().equals(Archive.getName()) && aux.getId() == route.getId()) {
                        aux2 = route;

                    }
                }
                getRoutes().remove(aux2);
                currentDrive.getData().add(Archive);
                /*creo la ruta*/
                Route NewRoute = new Route(currentRoute.getId(),
                        Archive.getId(), Archive.getName());
                getRoutes().add(NewRoute);
                /*lo añado como hijo*/
                currentRoute.getSons().add(Archive.getName() + ".");
                System.out.println("Archivo sobre-escrito con exito");

            } else { //lo añado al data
                currentDrive.getData().add(Archive);
                /*creo la ruta*/
                Route NewRoute = new Route(currentRoute.getId(),
                        Archive.getId(), Archive.getName());
                getRoutes().add(NewRoute);
                currentRoute.getSons().add(Archive.getName() + ".");
                System.out.println("Archivo anadido con exito");
            }
            contador++;

        }else{
        System.out.println("No hay usuario logueado");
        }

    }
    
    public void del(String FileNamePattern){
    }
}
