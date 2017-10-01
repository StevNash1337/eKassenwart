package de.naju.ahlen.persistence.model.project;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import de.naju.ahlen.util.ProjectUtil;

@DatabaseTable(tableName = "projects")
public class Project {

    @DatabaseField(id = true)
    private String name;
    @DatabaseField(canBeNull = false)
    private String fullName;
    //@DatabaseField(canBeNull = false)
    //private String url;
    //@DatabaseField(canBeNull = false)
    //private String username;
    //@DatabaseField(canBeNull = false)
    //private String password;
    //@DatabaseField(canBeNull = false)
    //private String path;
    //@DatabaseField
    //private String iconFile;

    Project() {

    }

    /*
    public Project(String name, String fullName, String url, String username, String password, String path, String iconFile) {
        this.name = name;
        this.fullName = fullName;
        this.url = url;
        this.username = username;
        this.password = password;
        this.path = path;
        this.iconFile = iconFile;
    }
    */

    public Project(String name, String fullName) {
        this.name = name;
        this.fullName = fullName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUrl() {
        if (name == null) {
            return null;
        }
        return ProjectUtil.getUrl(this);
    }

    public Project copy() {
        return new Project(name, fullName);
    }

    /*
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIconFile() {
        return iconFile;
    }

    public void setIconFile(String iconFile) {
        this.iconFile = iconFile;
    }
    */
}
