package models;

import com.fasterxml.jackson.databind.JsonNode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "UserData")
public class UserData {

    //Declaration vars
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @javax.persistence.Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @MapKeyColumn(name="key")
    @Column(name="value")
    @CollectionTable(name="USERDATA_DATA", joinColumns=@JoinColumn(name="id"))
    private Map<String,String> data;

    //Constructors---------------------------
    public UserData() {
        data = new HashMap<String,String>();
    }

    public UserData(JsonNode obj) {
        data = new HashMap<String,String>();

        String name;
        for (Iterator<String> it = obj.fieldNames(); it.hasNext(); ) {
            name = it.next();
            data.put(name,obj.findValue(name).asText());
        }
    }

    //Methods----------------------------------

    //Getters & Setters------------------------
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Map<String, String> getData() {
        return data;
    }
    public void setData(Map<String, String> data) {
        this.data = data;
    }
    //-----------------------------------------
}

