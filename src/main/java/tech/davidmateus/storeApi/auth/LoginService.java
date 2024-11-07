//package tech.davidmateus.storeApi.auth;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.stereotype.Service;
//import tech.davidmateus.storeApi.configSecurity.JwtServiceGenerator;
//
//
//@Service
//public class LoginService {
//
//    @Autowired
//    private LoginRepository repository;
//    @Autowired
//    private JwtServiceGenerator jwtService;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//
//    public String logar(Login login) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        login.getUsername(),
//                        login.getPassword()
//                )
//        );
//        Usuario user = repository.findByUsername(login.getUsername()).get();
//        String jwtToken = jwtService.generateToken(user);
//
//        return jwtToken;
//    }
//
//}