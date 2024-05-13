package ru.iteco.itecospringcource.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name= "group", schema = "ad")
@NoArgsConstructor
@AllArgsConstructor
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "groups")
    @ToString.Exclude
    private Set<UserEntity> users = new HashSet<>();

}
