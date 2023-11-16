package sakke.dinnerRecipes.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//import jakarta.validation.Valid;
import sakke.dinnerRecipes.domain.CuisineRepository;
import sakke.dinnerRecipes.domain.IngredientRepository;
import sakke.dinnerRecipes.domain.Recipe;
import sakke.dinnerRecipes.domain.RecipeRepository;

@Controller
public class RecipeController {

	@Autowired
	private RecipeRepository repository;

	@Autowired
	private IngredientRepository irepository;

	@Autowired
	private CuisineRepository crepository;

	public RecipeController(RecipeRepository repository, IngredientRepository irepository,
			CuisineRepository crepository) {
		this.repository = repository;
		this.irepository = irepository;
		this.crepository = crepository;
	}

	@RequestMapping(value = "/recipelist")
	public String recipeList(Model model) {
		model.addAttribute("recipes", repository.findAll());
		return "recipelist";
	}

	@RequestMapping(value = { "login" })
	public String login(Model model) {
		return "redirect:recipelist";
	}

	@GetMapping(value = { "recipes" })
	public @ResponseBody List<Recipe> recipeListRest() {
		return (List<Recipe>) repository.findAll();
	}

	@GetMapping(value = "recipe/{id}")
	public @ResponseBody Optional<Recipe> findRecipeRest(@PathVariable("id") Long recipeId) {
		return repository.findById(recipeId);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = { "/addrecipe" })
	public String addRecipe(Model model) {
		model.addAttribute("recipe", new Recipe());
		model.addAttribute("ingredients", irepository.findAll());
		model.addAttribute("cuisines", crepository.findAll());
		return "addrecipe";
	}

	@PostMapping(value = "/saverecipe")
	public String save(Recipe recipe) {
		repository.save(recipe);
		return "redirect:recipelist";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/delete/{id}")
	public String deleteRecipe(@PathVariable("id") Long id, Model model) {
		repository.deleteById(id);
		return "redirect:../recipelist";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/editrecipe/{id}")
	public String showEditRecipe(@PathVariable("id") Long id, Model model) {
		model.addAttribute("recipe", repository.findById(id));
		model.addAttribute("ingredients", irepository.findAll());
		model.addAttribute("cuisines", crepository.findAll());
		return "editrecipe";
	}

}
