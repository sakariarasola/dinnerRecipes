package sakke.dinnerRecipes.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
//import jakarta.validation.Valid;
import sakke.dinnerRecipes.domain.Ingredient;
import sakke.dinnerRecipes.domain.IngredientRepository;

@Controller
public class IngredientController {

	@Autowired
	private IngredientRepository repository;

	@PreAuthorize("hasAuthority('ADMIN')")
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

	@PostMapping(value = "/saveaddingredient")
	public String saveAdd(@Valid Ingredient ingredient, BindingResult bindingresult) {
		if (bindingresult.hasErrors()) {
			return "addingredient";
		}
		repository.save(ingredient);
		return "redirect:ingredientlist";
	}

	@PostMapping(value = "/saveeditingredient")
	public String saveEdit(@Valid Ingredient ingredient, BindingResult bindingresult) {
		if (bindingresult.hasErrors()) {
			return "editingredient";
		}
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