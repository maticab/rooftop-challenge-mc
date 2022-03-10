package com.rooftopmc.challenge.controller;

import com.rooftopmc.challenge.model.Textanalizer;
import com.rooftopmc.challenge.request.TextanalizerRequest;
import com.rooftopmc.challenge.response.TextanalizerResponse;
import com.rooftopmc.challenge.service.TextanalizerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/text")
public class TextanalizerController {

    private final TextanalizerService textanalizerService;

    public TextanalizerController(TextanalizerService textanalizerService) {
        this.textanalizerService = textanalizerService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Textanalizer> findOne(@PathVariable("id") String id){
        return ResponseEntity.ok(this.textanalizerService.findOne(id));
    }
    @GetMapping
    public ResponseEntity<List<Textanalizer>> findPage(
            @RequestParam(value = "chars", required = false, defaultValue = "2") int chars,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "rpp", required = false, defaultValue = "10") int rpp){
        return ResponseEntity.ok(this.textanalizerService.findPage(chars, page, rpp));
    }
    @PostMapping
    public ResponseEntity<TextanalizerResponse> newTextanalizer(@RequestBody TextanalizerRequest textanalizerRequest){
        return new ResponseEntity<>(this.textanalizerService.create(textanalizerRequest), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeTextanalizer(@PathVariable("id") String id){
        return ResponseEntity.ok(this.textanalizerService.delete(id));
    }

}
