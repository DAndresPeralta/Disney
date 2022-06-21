package com.disney.demo.services;

import com.disney.demo.entities.User;
import com.disney.demo.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author d.andresperalta
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void createUser(String name, String lastName, String dni, String pass, String pass2) {

        name = name.trim();
        lastName = lastName.trim();
        dni = dni.trim();

        validate(name, lastName, dni, pass, pass2);

        User u = new User();

        u.setName(name);
        u.setLastName(lastName);
        u.setDni(dni);
        u.setAlta(true);

        String encriptada = new BCryptPasswordEncoder().encode(pass);
        u.setPass(encriptada);

        userRepository.save(u);

    }

    public void validate(String name, String lastName, String dni, String pass, String pass2) {

        if (name == null || name.isEmpty()) {
            throw new Error("El nombre no puede ser nulo.");
        }

        if (lastName == null || lastName.isEmpty()) {
            throw new Error("El apeliido no puede ser nulo.");
        }

        if (dni == null || dni.isEmpty()) {
            throw new Error("El dni no puede ser nulo.");
        }

        if (pass == null || pass.isEmpty() || pass.length() <= 6) {
            throw new Error("La clave no puede ser nula o contener menos de 7 dígitos.");
        }

        if (!pass.equals(pass2)) {
            throw new Error("Las claves no son iguales.");
        }

    }

    public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException { // Este método se llama cuando algun usuario quiera autenticarse en la página.

        User usuario = userRepository.findByDni(dni);

        if (usuario != null) { // Si el usuario se encuentra en la base de datos se crean los siguientes permisos y se les otorgan al usuario.

            System.out.println("SI EXISTE USUARIO!!!!!!!");
            System.out.println(usuario.getDni());
            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_USUARIO_REGISTRADO");
            permisos.add(p1);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes(); //Esto nos permite reutilizar los datos del usuario logeado.
            HttpSession session = attr.getRequest().getSession(true);// los cuales se pueden utilizar en la vista, por ejemplo.
            session.setAttribute("usuariosession", usuario); // En esta linea guardamos los datos recuperados anteriormente de la BD en el objeto session.

            org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(usuario.getDni(), usuario.getPass(), permisos);

            return user;

        }
        return null;

    }

}
