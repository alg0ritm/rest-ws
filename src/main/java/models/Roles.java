package models;
// Generated Oct 6, 2012 2:33:08 AM by Hibernate Tools 3.4.0.CR1



/**
 * Roles generated by hbm2java
 */
public class Roles  implements java.io.Serializable {


     private Integer id;
     private String name;
     private String description;

    public Roles() {
    }

    public Roles(String name, String description) {
       this.name = name;
       this.description = description;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }




}


