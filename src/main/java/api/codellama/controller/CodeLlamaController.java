package api.codellama.controller;

import api.codellama.dto.CodeRequestDTO;
import api.codellama.service.CodeLlamaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
public class CodeLlamaController {

    @Autowired
    private CodeLlamaService codeLlamaService;

    @PostMapping
    public ResponseEntity<String> reviewCode(@RequestBody CodeRequestDTO request) {
        return ResponseEntity.ok(codeLlamaService.generateCode(request.getCode()));
    }
}