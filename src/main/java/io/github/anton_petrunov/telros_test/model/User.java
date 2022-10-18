package io.github.anton_petrunov.telros_test.model;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class User extends AbstractPersistable<Integer> {

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

    @Column(name = "email", nullable = false)
    @Email
    @Size(min = 3, max = 128)
    private String email;

    @Column(name = "phone", nullable = false)
    @Size(min = 5, max = 15)
    private String phone;
}
