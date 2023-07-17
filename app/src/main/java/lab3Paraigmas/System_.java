/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3Paraigmas;

import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    /*añado la papelera*/
    private ArrayList<element> Papelera = new ArrayList<>();
    private ArrayList<Route> PapeleraRutas = new ArrayList<>();
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

    public ArrayList<element> getPapelera() {
        return Papelera;
    }

    public void setPapelera(ArrayList<element> Papelera) {
        this.Papelera = Papelera;
    }

    public ArrayList<Route> getPapeleraRutas() {
        return PapeleraRutas;
    }

    public void setPapeleraRutas(ArrayList<Route> PapeleraRutas) {
        this.PapeleraRutas = PapeleraRutas;
    }

    @Override
    public String toString() {
        return "system{" + name + "\nDrives=" + Drives + "\nUsers=" + Users + "\nRoutes=" + Routes + "\ncreationDate= " + currentDate + "\n}";
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
            Route newRoute = new Route(0, contador + 1, letter, letter + "/");
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
                Route newRoute = new Route(currentRoute.getId(), contador + 1, folderName, currentRoute.getStringForm() + folderName + "/");
                getRoutes().add(newRoute);

                contador++;

                System.out.println("Directorio creado con exito");
            } else {
                System.out.println("El nombre de directorio ya existe.");
            }
        } else {
            System.out.println("No hay usuario logueado");
        }

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

            Route currentRoute = getRoutes().get(0);
            Archive.setId(contador + 1);
            Archive.setId_father(currentRoute.getId());

            Archive.setFmod(currentDate);
            int existe = 0;
            /*variable archivo para saber si existe dicho nombre correspondiente a 
            un archivo*/
            element aux = null;

            Drive currentDrive = getDrives().get(0);
            String auxarchive = Archive.getName() + ".";
            System.out.print(auxarchive);
            for (String son : currentRoute.getSons()) {
                if (auxarchive.equals(son)) {

                    /*reviso si es un archivo */
                    existe = 1;

                }
            }
            for (element data : currentDrive.getData()) {
                if (data.getId_father() == currentRoute.getId() && data.getName().equals(Archive.getName())) {

                    aux = data;

                }
            }

            if (existe == 1) {

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
                        Archive.getId(), Archive.getName(), currentRoute + Archive.getName() + "." + Archive.getFormat() + "/");
                getRoutes().add(NewRoute);
                /*lo añado como hijo*/

                System.out.println("Archivo sobre-escrito con exito");

            } else { //lo añado al data
                currentDrive.getData().add(Archive);
                /*creo la ruta*/
                Route NewRoute = new Route(currentRoute.getId(),
                        Archive.getId(), Archive.getName(), currentRoute.getStringForm() + Archive.getName() + "." + Archive.getFormat() + "/");
                getRoutes().add(NewRoute);
                currentRoute.getSons().add(Archive.getName() + ".");
                System.out.println("Archivo anadido con exito");
            }
            contador++;

        } else {
            System.out.println("No hay usuario logueado");
        }

    }

    /**
     * metodo para calcular todas las rutas donde aparece dir
     *
     * @param FolderName
     * @param father
     * @return
     */
    public ArrayList<String> routesDir(String FolderName, String father) {
        String aux = father + "/" + FolderName;
        ArrayList<String> RoutesDir = new ArrayList<>();
        for (Route route : getRoutes()) {
            if (route.getStringForm().contains(aux)) {
                //reviso  los hijos
                RoutesDir.add(route.getStringForm());

            }

        }

        return RoutesDir;

    }

    /**
     * TDA system - del: Método para eliminar un archivo o varios archivos en
     * base a un patrón determinado. Esta versión también puede eliminar una
     * carpeta completa con todos sus subdirectorios. El contenido eliminado se
     * va a la papelera.
     *
     * @param FileNamePattern
     */
    @Override
    public void del(String FileNamePattern) {
        /*debo borrar los archivos dependiendo de lo que entre como argumento del
        metodo*/
        if (existeUserLogged(getUsers())) {
            Route currentRoute = getRoutes().get(0);
            //unidad actual
            Drive currentDrive = getDrives().get(0);

            /*caso de que borre un directorio, no elimina sub directorios*/
            if (FileNamePattern.split("\\.").length == 1) {
                //directorio
                Directory directoryAux = null;
                //array auxiliar para eliminar, al menos, el directorio padre
                ArrayList<element> elementsToDelete = new ArrayList<>();
                ArrayList<Route> routesToDelete = new ArrayList<>();

                for (element data : currentDrive.getData()) {
                    if ((data.getClass().getSimpleName().equals("Directory")
                            && data.getId_father() == currentRoute.getId()
                            && data.getName().equals(FileNamePattern))) {

                        directoryAux = (Directory) data;

                        elementsToDelete.add(data);

                    }
                }
                if (directoryAux != null) {

                    /*ahora, debo revisar los hijos del directorio*/
                    ArrayList<String> Aparitions = routesDir(directoryAux.getName(), currentRoute.getName());
                    /*muestro*/
                    System.out.println(Aparitions);
                    for (String Aparition : Aparitions) {
                        for (Route route : getRoutes()) {
                            if (route.getStringForm().equals(Aparition)) {
                                routesToDelete.add(route);

                            }
                        }
                    }
                    //elementos a borrar
                    if (routesToDelete != null) {
                        for (Route route : routesToDelete) {
                            for (element data : currentDrive.getData()) {
                                if (data.getId() == route.getId()) {
                                    elementsToDelete.add(data);

                                }

                            }
                        }
                        if (elementsToDelete != null) {
                            //borro
                            currentDrive.getData().removeAll(elementsToDelete);
                            getRoutes().removeAll(routesToDelete);
                            currentRoute.getSons().remove(FileNamePattern);
                            //papelera
                            getPapelera().addAll(elementsToDelete);
                            getPapeleraRutas().addAll(routesToDelete);
                            System.out.println("borrado exitoso");

                        }

                    }

                }
            }

            /*caso de que lo que borre sean archivos... comodines y comando especial*/
            if (FileNamePattern.equals("*.*") || FileNamePattern.equals("*")) {
                //borro todos los archivos del directorio actual y los envio a
                //la papelera

                ArrayList<element> aux = new ArrayList<>();
                for (element data : currentDrive.getData()) {
                    if (data.getClass().getSimpleName().equals("Archive") && data.getId_father() == currentRoute.getId()) {
                        //lo añado a aux

                        aux.add(data);

                    }
                }
                //borro todos los archivos
                currentDrive.getData().removeAll(aux);
                //los mando a la papelera
                getPapelera().addAll(aux);
                //clear
                aux.clear();
                //TODO hijos y rutas

                ArrayList<Route> aux2 = new ArrayList<>();
                /*hago lo mismo para las rutas*/
                for (Route route : getRoutes()) {
                    if (route.getName().contains(".") && route.getId_father() == currentRoute.getId()) {
                        aux2.add(route);

                    }
                }
                //borro las rutas
                getRoutes().removeAll(aux2);
                //las añado a la papelera
                getPapeleraRutas().addAll(aux2);
                //clear
                aux2.clear();

                ArrayList<String> aux3 = new ArrayList<>();
                //ahora los hijos de la ruta actual
                for (String son : currentRoute.getSons()) {
                    if (son.contains(".")) {
                        aux3.add(son);

                    }
                }
                currentRoute.getSons().removeAll(aux3);

            }
            if (FileNamePattern.split("\\.").length > 1) {
                //recibo el pattern con stringSlit
                String[] Saux = FileNamePattern.split("\\.");
                System.out.println(Saux[0] + "...." + Saux[1]);

                if (Saux[0].equals("*")) { //borro todos los archivos con extension Saux[1]
                    //drive
                    ArrayList<element> aux = new ArrayList<>();
                    for (element data : currentDrive.getData()) {
                        if (data.getClass().getSimpleName().equals("Archive")
                                && data.getId_father() == currentRoute.getId()) {
                            Archive dataAux = (Archive) data;
                            if (dataAux.getFormat().equals(Saux[1])) {

                                aux.add(data);

                            }

                        }
                    }
                    //elimino
                    currentDrive.getData().removeAll(aux);
                    getPapelera().addAll(aux);
                    aux.clear();
                    //rutas 
                    ArrayList<Route> aux2 = new ArrayList<>();
                    /*hago lo mismo para las rutas*/
                    for (Route route : getRoutes()) {
                        if (route.getName().contains(".") && route.getId_father() == currentRoute.getId()) {
                            for (element data : currentDrive.getData()) {
                                if (route.getName().contains(data.getName())) {
                                    aux2.add(route);

                                }
                            }

                        }
                    }
                    getRoutes().removeAll(aux2);
                    getPapeleraRutas().addAll(aux2);

                    //ahora los hijos
                    ArrayList<String> aux3 = new ArrayList<>();
                    //ahora los hijos de la ruta actual
                    for (String son : currentRoute.getSons()) {
                        if (son.contains(".")) {
                            for (element data : currentDrive.getData()) {
                                if (son.contains(data.getName())) {
                                    aux3.add(son);

                                }
                            }

                        }
                    }
                    currentRoute.getSons().removeAll(aux3);

                }
                if (!Saux[0].equals("*")) { //archivo unico
                    //busco archivo
                    element dataAux = null;
                    for (element data : currentDrive.getData()) {
                        if (data.getClass().getSimpleName().equals("Archive")
                                && data.getId_father() == currentRoute.getId()
                                && data.getName().equals(Saux[0] + ".")) {
                            dataAux = data;

                            getPapelera().add(data);

                        }
                    }
                    currentDrive.getData().remove(dataAux);
                    Route routeAux = null;
                    for (Route route : getRoutes()) {
                        if (route.getName().contains(dataAux.getName())) {
                            routeAux = route;

                        }
                    }
                    getRoutes().remove(routeAux);
                    getPapeleraRutas().add(routeAux);
                    //elimino hijo
                    String sonAux = null;
                    for (String son : currentRoute.getSons()) {
                        if (son.contains(dataAux.getName())) {
                            sonAux = son;

                        }
                    }
                    currentRoute.getSons().remove(sonAux);
                    getPapeleraRutas().add(routeAux);

                }

            }

        }

    }

    /**
     * TDA system - copy: Método para copiar un archivo o carpeta desde una ruta
     * origen a una ruta destino.
     *
     * @param FileOrFolder
     * @param TargetPath
     */
    @Override
    public void copy(String FileOrFolder, String TargetPath) {
        //al copiar, solo copio los elementos del data y rutas y cre nuevas rutas
        /*añadiendolos al Targetpath*/
 /*obtengo las apariciones de FileORfolder*/
        //se que ha de ser visible desde la ruta actual
        //modifico el targetpath
        String aux = TargetPath.substring(0, 1) + TargetPath.substring(2);
        System.out.printf("aux", aux);
        if (existeUserLogged(getUsers())) {

            boolean existePath = false;

            Drive currentDrive = getDrives().get(0);

            ArrayList<element> copyData = new ArrayList<>();
            /*copio la data*/
            for (element data : currentDrive.getData()) {
                if (data.getClass().getSimpleName().equals("Archive")) {
                    Archive newArch = (Archive) data;
                    try {
                        copyData.add(newArch.clone());
                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(System_.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    Directory newDir = (Directory) data;
                    try {
                        copyData.add(newDir.clone());
                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(System_.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            ArrayList<Route> copiasRoute = new ArrayList<>();
            for (Route route : getRoutes()) {
                try {
                    copiasRoute.add(route.clone());
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(System_.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Route currentRoute = copiasRoute.get(0);

            //obtengo la ryta de targetpath
            Route targetpath = null;

            //reviso si existe el path de destino
            for (Route route : copiasRoute) {

                if (route.getStringForm().equals(aux)) {
                    existePath = true;

                }
            }

            if (existePath) {
                //obtengo el targetPath
                for (Route route : copiasRoute) {
                    if (route.getStringForm().equals(aux)) {
                        targetpath = route;

                    }

                }

                /*veo si existe el File or folder*/
                ArrayList<element> elementsToCopy = new ArrayList<>();
                ArrayList<Route> routeAux = new ArrayList<>();
                String[] Saux = FileOrFolder.split("\\.");
                if (Saux.length == 1) { //es directorio
                    //busco el directorio a copiar en base al id padre actual
                    Directory directoryAux = null;
                    for (element data : copyData) {
                        if ((data.getClass().getSimpleName().equals("Directory")
                                && data.getId_father() == currentRoute.getId()
                                && data.getName().equals(FileOrFolder))) {

                            directoryAux = (Directory) data;
                            elementsToCopy.add(directoryAux);

                        }
                    }

                    /*apariciones de folder*/
                    ArrayList<String> Aparitions = routesDir(directoryAux.getName(), currentRoute.getName());
                    for (String Aparition : Aparitions) {
                        for (Route route : copiasRoute) {
                            if (route.getStringForm().equals(Aparition)) {

                                routeAux.add(route);

                            }

                        }
                    }
                    //ahora obtengo el data a copiar
                    for (Route routeAux1 : routeAux) {
                        for (element data : copyData) {
                            if (data.getId() == routeAux1.getId() && !routeAux1.getName().equals(FileOrFolder)) {
                                elementsToCopy.add(data);

                            }
                        }
                    }
                    //muestro los elementos a copiar
                    //elimino los duplicadosN

                    //como son copias, debo a cada elemento asignarle un nuevo id
                    /*con el contador*/
                    for (element elementsToCopy1 : elementsToCopy) {
                        elementsToCopy1.setId(contador + 1);
                        contador++;
                    }
                    //rutas
                    //id de rutas
                    for (Route route : routeAux) {
                        for (element elementsToCopy1 : elementsToCopy) {
                            if (route.getName().equals(elementsToCopy1.getName())) {
                                route.setId(elementsToCopy1.getId());

                            }
                            if (route.getName().equals(FileOrFolder)) {
                                route.setId_father(targetpath.getId());

                            }

                        }
                    }
                    for (Route route : routeAux) {
                        for (String son : route.getSons()) {
                            for (element elementsToCopy1 : elementsToCopy) {
                                if (son.contains(elementsToCopy1.getName())) {
                                    for (Route route1 : routeAux) {
                                        if (route1.getName().equals(elementsToCopy1.getName())) {
                                            route1.setId_father(route.getId());
                                        }
                                    }

                                }
                            }
                        }
                    }

                    System.out.println(routeAux);

                    System.out.println(elementsToCopy);

                    //ahora cambio los strings de las rutas
                    for (Route routeAux1 : routeAux) {
                        if (routeAux1.getName().equals(FileOrFolder)) {
                            routeAux1.setStringForm(aux + FileOrFolder + "/");

                        } else {
                            String[] Aux = routeAux1.getStringForm().split(FileOrFolder);
                            routeAux1.setStringForm(aux + FileOrFolder + Aux[1]);

                        }

                    }
                    //los clono

                    //agrego la data
                    currentDrive.getData().addAll(elementsToCopy);
                    System.out.println("!!!!!_!_!_!__!_!");
                    System.out.println(currentDrive.getData());

                    getRoutes().addAll(routeAux);
                    System.out.println("copiado de directorio exitoso");

                } else { //es archivo
                    //copio el archivo, lo busco en la data copiada
                    Archive archToCopy=null;
                    for (element copyData1 : copyData) {
                        if (copyData1.getClass().getSimpleName().equals("Archive")
                                && copyData1.getName().equals(Saux[0]+".")) {
                                Archive archAux = (Archive) copyData1;
                                if (archAux.getFormat().equals(Saux[1])) {
                                    archToCopy = archAux;
                                    
                                
                            }
                                
                                    
                            
                        }
                    }
                    
                    //lo añado al target path
                    archToCopy.setId_father(targetpath.getId());
                    archToCopy.setId(contador+1);
                    contador++;
                    //creo la ruta
                    //busco ruta
                    Route Raux=null;
                    for (Route copiasRoute1 : copiasRoute) {
                        if (copiasRoute1.getName().contains(archToCopy.getName())) {
                            Raux=copiasRoute1;
                            
                        }
                    }
                    
                    //modifico
                    currentDrive.getData().add(archToCopy);
                    Raux.setId(archToCopy.getId());
                    Raux.setId_father(targetpath.getId());
                    Raux.setStringForm(aux+archToCopy.getName()+archToCopy.getFormat());
                    getRoutes().add(Raux);
                    
                }

            }

        }
    }

    /**
     * TDA system - move: Método para mover un archivo o carpeta desde una ruta
     * origen a una ruta destino.La operación de mover elimina el contenido
     * desde la ruta origen.
     *
     * @param source
     * @param TargetPath
     */
    @Override
    public void move(String source, String TargetPath) {
    }
}
