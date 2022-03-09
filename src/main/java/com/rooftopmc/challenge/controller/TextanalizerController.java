package com.rooftopmc.challenge.controller;

import com.rooftopmc.challenge.model.Textanalizer;
import com.rooftopmc.challenge.request.TextanalizerRequest;
import com.rooftopmc.challenge.service.TextanalizerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/text")
@Api(tags = "text", value = "Text API")
public class TextanalizerController {

    private final TextanalizerService textanalizerService;

    public TextanalizerController(TextanalizerService textanalizerService) {
        this.textanalizerService = textanalizerService;
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Find analyzed text",notes = "Find analyzed text by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Analyzed text found"),
            @ApiResponse(code = 404,message = "Analyzed text not found"),
    })
    public ResponseEntity<Textanalizer> findOne(@PathVariable("id") String id){
        return ResponseEntity.ok(this.textanalizerService.findOne(id));
    }
    @GetMapping
    @ApiOperation(value = "List of analyzed text",notes = "List all analyzed text")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "All texts found"),
            @ApiResponse(code = 404,message = "All texts not found")
    })
    public ResponseEntity<List<Textanalizer>> findAll(@RequestParam("chars") String chars, @RequestParam("page") String page, @RequestParam("rpp") String rpp){
        return ResponseEntity.ok(this.textanalizerService.findAll());
    }
    @PostMapping
    @ApiOperation(value = "Create analyzed text", notes = "It allows to create and store an analyzed text")
    @ApiResponses(value = {
            @ApiResponse(code = 201,message = "Analyzed text created successfully"),
            @ApiResponse(code = 400,message = "Invalid request")
    })
    public ResponseEntity<Textanalizer> newTextanalizer(TextanalizerRequest textanalizerRequest){
        return new ResponseEntity<>(this.textanalizerService.create(textanalizerRequest), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Remove category",notes = "It permits to remove a category")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Analyzed text removed successfully"),
            @ApiResponse(code = 404,message = "Analyzed text not found")
    })
    public ResponseEntity<String> removeTextanalizer(@PathVariable("id") String id){
        return ResponseEntity.ok(this.textanalizerService.delete(id));
    }

}
