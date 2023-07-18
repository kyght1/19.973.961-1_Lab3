/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3Paraigmas;

/**
 *
 * @author Toshiba
 */
public class Archive extends element{
    private String format;
    private String content;
    private String fmod;

    @Override
    public String toString() {
        return "Archive{" + "format=" + format + ", content=" + content + ", name=" + getName() + getId_father()+ '}';
    }

    public Archive(String format, String content, String fmod, int id, String name) {
        super(id, name);
        this.format = format;
        this.content = content;
        this.fmod = fmod;
    }
    
    @Override
    public Archive clone() throws CloneNotSupportedException{
        Archive newArch = (Archive) super.clone();
        newArch.setFormat(newArch.getFormat());
        newArch.setContent(newArch.getContent());
        newArch.setFmod(newArch.getFmod());
        return newArch;
    }
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFmod() {
        return fmod;
    }

    public void setFmod(String fmod) {
        this.fmod = fmod;
    }

    
    
    
    
    
    
}
