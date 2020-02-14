package org.n52.spare.kicker.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.n52.spare.kicker.model.Views.Basic;
import org.n52.spare.kicker.model.Views.Details;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
        name = "players"
)

public final class Player {
    @JsonView({Basic.class})
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )

    private Long id;
    @JsonView({Basic.class})
    @Column(
            unique = true
    )

    private String nickName;
    @JsonView({Details.class})

    private String firstName;
    @JsonView({Details.class})

    private String lastName;

    private String email;
    @Column(
            nullable = false
    )

    private String password;


    public void setId(Long id) {
        this.id = id;
    }

    public final Long getId() {
        return this.id;
    }


    public final String getNickName() {
        return this.nickName;
    }

    public final void setNickName(String var1) {
        this.nickName = var1;
    }


    public final String getFirstName() {
        return this.firstName;
    }

    public final void setFirstName(String var1) {
        this.firstName = var1;
    }


    public final String getLastName() {
        return this.lastName;
    }

    public final void setLastName(String var1) {
        this.lastName = var1;
    }


    public final String getEmail() {
        return this.email;
    }

    public final void setEmail(String var1) {
        this.email = var1;
    }


    public final String getPassword() {
        return this.password;
    }

    public final void setPassword(String var1) {
        this.password = var1;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
