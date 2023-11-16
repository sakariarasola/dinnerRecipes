package sakke.dinnerRecipes.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_seq")
	@SequenceGenerator(name = "recipe_seq", sequenceName = "recipe_seq", allocationSize = 1)
	@NotNull
	private Long id;
	@NotNull
	private String name;
	private String allergen;
	@NotNull
	private String difficulty;
	@NotNull
	private int totaltime;
	@NotNull
	private int servings;
	@NotNull
	@Size(max = 10000)
	private String instructions;

	@ManyToMany
	@JoinTable(name = "recipe_ingredient", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
	private List<Ingredient> ingredients;

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	@ManyToOne
	@JoinColumn(name = "cuisine_id")
	private Cuisine cuisine;

	public void setCuisine(Cuisine cuisine) {
		this.cuisine = cuisine;
	}

	public Cuisine getCuisine() {
		return cuisine;
	}

	public Recipe() {

	}

	public Recipe(String name, String allergen, String difficulty, int totaltime, int servings,
			List<Ingredient> ingredients, String instructions, Cuisine cuisine) {
		this.name = name;
		this.allergen = allergen;
		this.difficulty = difficulty;
		this.totaltime = totaltime;
		this.servings = servings;
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.cuisine = cuisine;
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

	public String getAllergen() {
		return allergen;
	}

	public void setAllergen(String allergen) {
		this.allergen = allergen;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public int getTotaltime() {
		return totaltime;
	}

	public void setTotaltime(int totaltime) {
		this.totaltime = totaltime;
	}

	public int getServings() {
		return servings;
	}

	public void setServings(int servings) {
		this.servings = servings;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
}