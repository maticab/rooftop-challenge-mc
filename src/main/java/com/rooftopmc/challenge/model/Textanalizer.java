package com.rooftopmc.challenge.model;

import com.google.common.hash.HashCode;
import lombok.Data;
import org.springframework.util.DigestUtils;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(name = "textanalizer")
public class Textanalizer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;

    String hash;

    String text;

    int chars;

    @ElementCollection
    @CollectionTable(name = "subtext_count",
            joinColumns = { @JoinColumn(name = "textanalizer_id") })
    @MapKeyColumn(name = "subtext")
    @Column(name = "count")
    Map<String, Integer> results = new HashMap<>();

    public Textanalizer(){}

    public Textanalizer(String text, int chars) {
        this.text = text;
        this.chars = chars;
        initialize();
    }

    private void initialize() {
        createHash();

        createResultMap();
    }

    private void createResultMap() {
        // Create result Map
        if(this.text.length() > 0 && this.text.length() > this.chars) {
            for(int i = 0; i < this.text.length() - this.chars + 1; i++) {
                String mapKey = text.substring(i, i + this.chars);
                Integer value = (this.results.getOrDefault(mapKey, 0));
                value++;
                this.results.put(mapKey, value);
            }
        }
    }

    private void createHash() {
        // save hashCode
        this.hash = DigestUtils.md5DigestAsHex((this.text + this.chars).getBytes(StandardCharsets.UTF_8));
    }

}
