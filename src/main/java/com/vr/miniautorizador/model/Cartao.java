package com.vr.miniautorizador.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "cartoes")
public class Cartao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @JsonIgnore
    private Long id;

    @Column(name = "numero", unique = true, nullable = false, length = 16)
    @Size(min=16, max = 16)
    @Pattern(regexp = "^\\d{16}$")
    private String numeroCartao;

    @Column(name = "senha", nullable = false, length = 4)
    @Size(min=4, max = 4)
    @Pattern(regexp = "^\\d{4}$")
    private String senha;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date data_criacao;

    @PrePersist
    public void prePersist() {
        saldo = new BigDecimal(500);
    }

}
