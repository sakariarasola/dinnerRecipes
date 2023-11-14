package sakke.dinnerRecipes.domain;

import org.springframework.data.repository.CrudRepository;

public interface CredentialsRepository extends CrudRepository<Credentials, Long> {
	Credentials findByUsername(String username);
}