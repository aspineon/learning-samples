package de.rieckpil.learning.highperformancejpa.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name="topic_post")
public class TopicPost extends Topic {

    private String content;
}
