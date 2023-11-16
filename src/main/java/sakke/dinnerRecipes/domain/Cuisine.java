package sakke.dinnerRecipes.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;

@Entity
public class Cuisine {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cuisine_seq")
	@SequenceGenerator(name = "cuisine_seq", sequenceName = "cuisine_seq", allocationSize = 1)
	@NotNull
	private long id;
	@NotNull
	private String name;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cuisine")
	private List<Recipe> recipes;

	public Cuisine() {

	}

	public Cuisine(String name) {
		this.name = name;
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
}
