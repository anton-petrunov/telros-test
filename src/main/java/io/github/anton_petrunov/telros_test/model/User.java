package io.github.anton_petrunov.telros_test.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class User extends BaseEntity {

    @Column(name = "last_name", nullable = false)
    @NotEmpty
    @NotBlank
    @Size(max = 128)
    private String lastName;

    @Column(name = "first_name", nullable = false)
    @NotEmpty
    @NotBlank
    @Size(max = 128)
    private String firstName;

    @Column(name = "patronymic", nullable = false)
    @Size(max = 128)
    private String patronymic;

    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    List<Contact> contacts;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    Set<Photo> photos;
}
