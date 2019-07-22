package ua.testing.demo_jpa.entity;

import javax.persistence.*;

@Entity
@Table( name="requests",
        uniqueConstraints={@UniqueConstraint(columnNames={"request_id"})})
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "request_id", nullable = false)
    private long requestId;
    @ManyToOne(fetch = FetchType.LAZY)
    private User applicant;

    private String message;


}
