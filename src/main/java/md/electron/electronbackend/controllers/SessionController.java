package md.electron.electronbackend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class SessionController
{
    @GetMapping("/sessionToken")
    public String getSessionId(final HttpSession session)
    {
        return session.getId();
    }
}
