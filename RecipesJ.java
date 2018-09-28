package ru.cookbook;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RecipesJ {
	@XmlElement public String name, recipe, products, energy_value, time, ingestion ;
}
