/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3Paraigmas;

/**
 *
 * @author Toshiba
 */
public class Texto extends Archive {

    public Texto(String format, String content, String fmod, int id, String name) {
        super(format, content, fmod, id, name);
    }

    @Override
    public String getName() {
        return "txt" ;// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    
    
}
