package io.github.anton_petrunov.telros_test.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class Contact extends BaseEntity {

    //    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "value", nullable = false)
    @NotNull
    @NotBlank
    @Size(max = 128)
    private String value;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private User user;
}
