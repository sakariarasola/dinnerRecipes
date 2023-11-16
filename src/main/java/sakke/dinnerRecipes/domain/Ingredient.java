package sakke.dinnerRecipes.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;

@Entity
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredient_seq")
	@SequenceGenerator(name = "ingredient_seq", sequenceName = "ingredient_seq", allocationSize = 1)
	@NotNull
	private Long id;
	@NotNull
	private String name;
	private double unitprice;

	@JsonIgnore
	@ManyToMany(mappedBy = "ingredients")
	private List<Recipe> recipes;

	public Ingredient() {
	}

	public Ingredient(String name, double unitprice) {
		this.name = name;
		this.unitprice = unitprice;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}
}
