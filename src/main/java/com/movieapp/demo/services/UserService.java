package com.movieapp.demo.services;



import com.movieapp.demo.UserDetailService;
import com.movieapp.demo.models.UserEntity;
import com.movieapp.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
// it makes use of userdetailservice and user repository to fetch user by name
// from database
// implementing fetching database by user name
// the user service would be use by jwt filter to check username from jwt token against existing users in the database
// this service would be of use towards the AuthencationFilter
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    private UserEntity userEntity;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // fetch user by name in the database findbyname as argument
        userEntity = this.userRepository.findByUsername(s); // s is a name
           // to check user and there token
        return new UserDetailService(userEntity);
    }
}
