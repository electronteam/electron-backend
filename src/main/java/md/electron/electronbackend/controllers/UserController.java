package md.electron.electronbackend.controllers;

import md.electron.electronbackend.constants.RequestMappings;
import md.electron.electronbackend.data.UserData;
import md.electron.electronbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping(value = RequestMappings.ADMIN_CREATE_USER)
    public ResponseEntity<Void> createUser(@ModelAttribute UserData userData)
    {
        userService.createUser(userData);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = RequestMappings.ADMIN_ALL_USERS)
    public List<UserData> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping(value = RequestMappings.ADMIN_USERS)
    public Page<UserData> getUsers(final Pageable page)
    {
        return userService.getUsers(page);
    }

    @GetMapping(value = RequestMappings.USER_DETAILS)
    public ResponseEntity<UserData> viewUserDetails(@PathVariable String id)
    {
        final Optional<UserData> user = userService.getUserById(id);
        return ResponseEntity.of(user);
    }
}
