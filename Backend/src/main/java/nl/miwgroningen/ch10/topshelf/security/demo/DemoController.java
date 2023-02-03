package nl.miwgroningen.ch10.topshelf.security.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Jacob Visser
 * <p>
 * Dit is wat het programma doet.
 */
@RestController
@RequestMapping("/topshelf")
public class DemoController {
    
    @GetMapping("/hello")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello from secured endpoint");
    }
}
