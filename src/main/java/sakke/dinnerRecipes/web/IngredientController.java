package sakke.dinnerRecipes.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sakke.dinnerRecipes.domain.Ingredient;
import sakke.dinnerRecipes.domain.IngredientRepository;
//import sakke.dinnerRecipes.domain.Recipe;

@Controller
public class IngredientController {
	
	@Autowired
	private IngredientRepository repository;

	@RequestMapping(value = "/ingredientlist")
	public String ingredientList(Model model) {
		model.addAttribute("ingredients", repository.findAll());
		return "ingredientlist";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = { "/addingredient" })
	public String addRecipe(Model model) {
		model.addAttribute("ingredient", new Ingredient());
		return "addingredient";
	}
	
	@PostMapping(value = "/saveingredient")
	public String save(Ingredient ingredient) {
		repository.save(ingredient);
		return "redirect:ingredientlist";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/editingredient/{id}")
	public String showEditIngredient(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ingredient", repository.findById(id));
		return "editingredient";
	}
}