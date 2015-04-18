package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User extends Model {
    @Id
    @Constraints.Required
    @Constraints.MinLength(5)
    @Constraints.MaxLength(20)
    public String username;

    @Constraints.Required
    @Constraints.MinLength(5)
    @Constraints.MaxLength(20)
    public String password;
}//end User Model class
