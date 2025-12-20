// package com.example.demo.security;

// import com.example.demo.entity.User;
// import com.example.demo.repository.UserRepository;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;

// import java.util.Collections;

// public class CustomUserDetailsService implements UserDetailsService {

//     private final UserRepository userRepository;

//     public CustomUserDetailsService(UserRepository userRepository) {
//         this.userRepository = userRepository;
//     }

//     @Override
//     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//         User user = userRepository.findByEmail(email)
//                 .orElseThrow(() -> new UsernameNotFoundException("User not found"));

//         String role = user.getRole() == null ? "USER" : user.getRole();
//         GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
//         return new org.springframework.security.core.userdetails.User(
//                 user.getEmail(),
//                 user.getPassword() == null ? "" : user.getPassword(),
//                 Collections.singletonList(authority)
//         );
//     }
// }
