package md.electron.electronbackend.controllers;

import md.electron.electronbackend.constants.RequestMappings;
import md.electron.electronbackend.data.UserData;
import md.electron.electronbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
