package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Lookup extends Model{

    @Id
    @Constraints.Required
    public String dateStamp;

    @Constraints.Required
    public String username;

    @Constraints.Required
    public String stockTicker;

    @Constraints.Required
    public float stockPrice;
}