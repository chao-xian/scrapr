package models;


import javax.persistence.*;

import play.db.jpa.*;

//@Entity
public class Colours extends Model {
    
    public String hex;
    public String rgb;
    
    public Colours(String hex, String rgb) {
        this.hex = hex;
        this.rgb = rgb;
    }
}