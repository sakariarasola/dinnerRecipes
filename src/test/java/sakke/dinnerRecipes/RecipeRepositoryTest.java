package sakke.dinnerRecipes;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import sakke.dinnerRecipes.domain.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RecipeRepositoryTest {

	@Autowired
	private RecipeRepository repository;

	@Autowired
	private IngredientRepository irepository;

	@Autowired
	private CuisineRepository crepository;

	@Test
	public void findByNameShouldReturnServings() {
		List<Recipe> recipes = repository.findByName("Jauhelihakastike");
		assertThat(recipes).hasSize(1);
		assertThat(recipes.get(0).getServings()).isEqualTo(4);
	}

	@Test
	public void findByNameShouldReturnDifficulty() {
		List<Recipe> recipes = repository.findByName("Jauhelihakastike");
		assertThat(recipes).hasSize(1);
		assertThat(recipes.get(0).getDifficulty()).isEqualTo("helppo");
	}

	@Test
	public void createNewRecipeIngredientAndCuisine() {
		Ingredient ingredient = new Ingredient("kirjolohi", 15.0);
		List<Ingredient> ingredients = Arrays.asList(ingredient);
		Cuisine cuisine = new Cuisine("amerikkalainen");

		Recipe recipe = new Recipe("a", "b", "c", 10, 1, ingredients, "testi", cuisine);
		repository.save(recipe);
		assertThat(recipe.getId()).isNotNull();

	}

	@Test
	public void deleteRecipe() {
		List<Recipe> recipes = repository.findByName("Jauhelihakastike");
		assertThat(recipes).hasSize(1);
		Recipe deleteThisRecipe = (recipes.get(0));
		repository.delete(deleteThisRecipe);
		List<Recipe> recipes2 = repository.findByName("Jauhelihakastike");
		assertThat(recipes2).hasSize(0);
	}

	@Test
	void findAllRecipesShouldReturnTwoRows() {
		Iterable<Recipe> recipes = repository.findAll();
		assertThat(recipes).hasSize(2);
	}

	@Test
	public void findByNameShouldReturnId() {
		List<Ingredient> ingredients = irepository.findByName("paistijauheliha");
		assertThat(ingredients).hasSize(1);
		assertThat(ingredients.get(0).getId()).isEqualTo(1);
	}

	@Test
	public void editCuisine() {
		List<Recipe> recipes = repository.findByName("Jauhelihakastike");
		assertThat(recipes).hasSize(1);
		Recipe editThisRecipesCuisine = recipes.get(0);
		List<Cuisine> cuisine = crepository.findByName("thai");
		assertThat(cuisine).hasSize(1);
		editThisRecipesCuisine.setCuisine(cuisine.get(0));
		repository.save(editThisRecipesCuisine);
		List<Recipe> recipes2 = repository.findByName("Jauhelihakastike");
		assertThat(recipes2).hasSize(1);
		assertThat(recipes2.get(0).getCuisine().getName()).isEqualTo("thai");
	}
}