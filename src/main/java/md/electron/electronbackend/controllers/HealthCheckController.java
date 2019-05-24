package md.electron.electronbackend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController
{
    @GetMapping("/healthCheck")
    public String testHealth() {
        return "I am OK";
    }
}
