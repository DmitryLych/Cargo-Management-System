package lych.trucks.application.security.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Entity describes authority.
 */
@Entity
@Data
@Table(name = "authorities")
@SuppressWarnings("PMD")
public class Authority implements GrantedAuthority {

    private static final long serialVersionUID = -8819640262963921681L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "authority_id")
    private Integer id;

    @Column(name = "authority")
    private String authority;

    @Column(name = "user_id")
    private Integer userId;
}
