package models;

import com.fasterxml.jackson.databind.JsonNode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="Field")
public class Field {

	//Declaration vars
	@Id
	@Column(name = "name", unique = true, nullable = false)
	private String label;

	@Column(nullable = false)
	private String type;

	@Column(nullable = false)
	private boolean isActive;

	@Column(nullable = false)
	private boolean required;

	@ElementCollection(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@Column(name="value")
	@CollectionTable(name="items", joinColumns=@JoinColumn(name="item"))
	private Set<String> items;
	
	//Constructors
	public Field() {
		items = new HashSet<>();
	}
	public Field(JsonNode obj)
	{
		if( obj.get("label").isNull()	||
			obj.get("type").isNull()	||
			obj.get("isActive").isNull()||
			obj.get("required").isNull()
			){

			throw new NullPointerException();
		}
		else {
			this.label = obj.get("label").asText();
			this.type = obj.get("type").asText();
			this.isActive = obj.get("isActive").asBoolean();
			this.required = obj.get("required").asBoolean();
			this.items = new HashSet<>();

			if((this.type.equals("combobox")||this.type.equals("radio"))) {
				for(JsonNode item: obj.withArray("items"))
				{
					System.out.println(item.asText());
					this.items.add(item.asText());
				}
				if(this.items.size() < 2) {
					throw new NullPointerException();
				}
				else {
					this.items.addAll(items);
				}
			}
		}
	}

	//Methods
	public void addItem(String item)
	{
		items.add(item);
	}
	
	public Field copy()
	{	
		Field res = new Field();
		res.setLabel(this.getLabel());
		res.setType(this.getType());
		res.setItems(this.getItems());
		res.setIsActive(this.getIsActive());
		res.setRequired(this.getRequired());
		return res;
	}
	
	public void copy(Field field)
	{	
		this.setLabel(field.getLabel());
		this.setType(field.getType());
		this.setIsActive(field.getIsActive());
		this.setRequired(field.getRequired());
		this.setItems(field.getItems());
	}
	
	//Getters & Setters------------------------
	public String getLabel() {
		return label;
	}
	public void setLabel(String lable) {
		this.label = lable;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean getRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}

	public Set<String> getItems() {
		return items;
	}
	public void setItems(Set<String> items) {
		this.items = items;
	}
	//-----------------------------------------
}
