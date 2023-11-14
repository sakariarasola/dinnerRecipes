package sakke.dinnerRecipes.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sakke.dinnerRecipes.domain.Credentials;
import sakke.dinnerRecipes.domain.CredentialsRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private final CredentialsRepository repository;

	@Autowired
	public UserDetailServiceImpl(CredentialsRepository credRepository) {
		this.repository = credRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws
	UsernameNotFoundException {
	Credentials curruser = repository.findByUsername(username);
	UserDetails user = new org.springframework.security.core.userdetails.User(username,
	curruser.getPasswordHash(),
	AuthorityUtils.createAuthorityList(curruser.getRole()));
	return user;
	}
	
}