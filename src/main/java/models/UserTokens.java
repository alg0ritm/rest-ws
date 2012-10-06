package models;
// Generated Oct 6, 2012 2:33:08 AM by Hibernate Tools 3.4.0.CR1



/**
 * UserTokens generated by hbm2java
 */
public class UserTokens  implements java.io.Serializable {


     private Integer id;
     private Users users;
     private String userAgent;
     private String token;
     private String type;
     private int created;
     private int expires;

    public UserTokens() {
    }

    public UserTokens(Users users, String userAgent, String token, String type, int created, int expires) {
       this.users = users;
       this.userAgent = userAgent;
       this.token = token;
       this.type = type;
       this.created = created;
       this.expires = expires;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Users getUsers() {
        return this.users;
    }
    
    public void setUsers(Users users) {
        this.users = users;
    }
    public String getUserAgent() {
        return this.userAgent;
    }
    
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
    public String getToken() {
        return this.token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public int getCreated() {
        return this.created;
    }
    
    public void setCreated(int created) {
        this.created = created;
    }
    public int getExpires() {
        return this.expires;
    }
    
    public void setExpires(int expires) {
        this.expires = expires;
    }




}


