package com.rooftopmc.challenge.service;

import com.rooftopmc.challenge.exceptions.TextanalizerNotFoundException;
import com.rooftopmc.challenge.model.Textanalizer;
import com.rooftopmc.challenge.repository.TextanalizerRepository;
import com.rooftopmc.challenge.request.TextanalizerRequest;
import com.rooftopmc.challenge.response.TextanalizerResponse;
import com.rooftopmc.challenge.utils.CustomUtils;
import org.springframework.data.domain.Pageable;
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
    public TextanalizerResponse create(TextanalizerRequest request) {
        String hash = CustomUtils.hashForTextanalizer(request.getText(), request.getChars());
        Optional<Textanalizer> textanalizer = this.textanalizerRepository.findByHash(hash);
        if(textanalizer.isPresent())
            return new TextanalizerResponse(textanalizer.get().getId());
        else
            return new TextanalizerResponse(this.textanalizerRepository.save( new Textanalizer(request.getText(), request.getChars())).getId());
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

    public List<Textanalizer> findPage(int chars, int page, int rpp) {
        page = getPageFixed(page);

        rpp = getRppFixed(rpp);

        Pageable pageable = Pageable.ofSize(rpp).withPage(page);
        return this.textanalizerRepository.findAllPageable(chars, pageable).toList();
    }

    private int getPageFixed(int page) {
        /* Page arrangements */
        page--;
        if(page < 0) page = 0;
        return page;
    }

    private int getRppFixed(int rpp) {
        /* rpp arrangements */
        if(rpp < 10) rpp = 10;
        if(rpp > 100) rpp = 100;
        return rpp;
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
