
package dev.trapalhoes.ChapaQuente.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CsrfController {

    @GetMapping("/csrf")
    public Map<String, String> csrf(CsrfToken csrf) {
        return Map.of(
                "headerName", csrf.getHeaderName(),
                "token", csrf.getToken()
        );
    }
}
