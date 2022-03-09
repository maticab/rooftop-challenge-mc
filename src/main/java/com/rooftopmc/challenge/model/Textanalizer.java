package com.rooftopmc.challenge.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Data
@Entity
@Table(name = "textanalizer")
public class Textanalizer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;

    int hash;

    String text;

    int chars;

    @ElementCollection
    @CollectionTable(name = "subtext_count",
            joinColumns = { @JoinColumn(name = "textanalizer_id") })
    @MapKeyColumn(name = "subtext")
    @Column(name = "count")
    Map<String, Integer> results = new HashMap<>();


}
