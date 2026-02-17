package it.giovanna.taskmanager.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="User",uniqueConstraints=@UniqueConstraint(name="uk_users_username", columnNames="username"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false,unique=true,length=30)
    private String username;

    @Column(nullable=false,length=100)
    private String passwordHash;
}
