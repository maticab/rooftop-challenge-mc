package com.rooftopmc.challenge.service;

import com.rooftopmc.challenge.exceptions.TextanalizerNotFoundException;
import com.rooftopmc.challenge.model.Textanalizer;
import com.rooftopmc.challenge.repository.TextanalizerRepository;
import com.rooftopmc.challenge.request.TextanalizerRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TextanalizerService {
    private final TextanalizerRepository textanalizerRepository;

    public TextanalizerService(TextanalizerRepository textanalizerRepository) {
        this.textanalizerRepository = textanalizerRepository;
    }

    @Transactional
    public Textanalizer create(TextanalizerRequest request) {
        Textanalizer textanalizer = new Textanalizer();
        textanalizer.setText(request.getText());
        textanalizer.setChars(request.getChars());
        return this.textanalizerRepository.save(textanalizer);
    }

    @Transactional
    public String delete(String id) {
        final Optional<Textanalizer> textanalizer = this.textanalizerRepository.findById(id);
        if (textanalizer.isPresent()) {
            this.textanalizerRepository.delete(textanalizer.get());
            return "";
        } else {
            throw new TextanalizerNotFoundException(HttpStatus.NOT_FOUND);
        }
    }

    public List<Textanalizer> findAll() {
        return this.textanalizerRepository.findAll();
    }

    public Textanalizer findOne(String id) {
        final Optional<Textanalizer> textanalizer = this.textanalizerRepository.findById(id);
        if (textanalizer.isPresent()) {
            return textanalizer.get();
        } else {
            throw new TextanalizerNotFoundException(HttpStatus.NOT_FOUND);
        }
    }

}
