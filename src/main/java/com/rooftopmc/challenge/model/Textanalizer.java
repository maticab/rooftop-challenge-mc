package com.rooftopmc.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rooftopmc.challenge.utils.CustomUtils;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Data
@Entity
@SQLDelete(sql = "UPDATE textanalizer SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Table(name = "textanalizer")
public class Textanalizer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;

    @JsonIgnore
    boolean deleted = Boolean.FALSE;

    String hash;

    @JsonIgnore
    @Transient
    transient String text;

    int chars;

    @ElementCollection
    @CollectionTable(name = "subtext_count",
            joinColumns = {@JoinColumn(name = "textanalizer_id")})
    @MapKeyColumn(name = "subtext")
    @Column(name = "count")
    Map<String, Integer> results = new HashMap<>();

    public Textanalizer(){}

    public Textanalizer(String text, int chars) {
        this.text = text.toLowerCase(Locale.ROOT);
        this.chars = (Math.max(chars, 2));
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
                String mapKey = this.text.substring(i, i + this.chars);
                Integer value = (this.results.getOrDefault(mapKey, 0));
                value++;
                this.results.put(mapKey, value);
            }
        } else {
            this.results.put(this.text, 1);
        }
    }

    private void createHash() {
        // save hashCode
        this.hash = CustomUtils.hashForTextanalizer(this.text, this.chars);
    }

}
