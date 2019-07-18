package ua.testing.demo_jpa.entity;

import javax.persistence.*;

@Entity
@Table( name="transaction",
        uniqueConstraints={@UniqueConstraint(columnNames={"id"})})
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private long id;
}
