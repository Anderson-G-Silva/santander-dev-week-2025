package dio.santander_dev_week_2025.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.Random;

@Entity(name = "tb_card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = true, unique = true)
    private String number;
    @Column(name = "avaiable_limit", precision = 13, scale = 2)
    private BigDecimal limit;

    // cria o número do cartão com base no id
    @PostPersist
    private void createNumber() {
        Random random = new Random();
        this.number = "XXXX XXXX XXXX "+ String.format("%04d",this.id)+ random.nextInt(9 - 0);

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }
}
