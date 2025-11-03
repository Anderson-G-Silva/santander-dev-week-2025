package dio.santander_dev_week_2025.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostPersist;

import java.math.BigDecimal;
import java.util.Random;

@Entity(name = "tb_account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = true, unique = true)
    private String number;
    @Column(updatable = false)
    private String agency;
    @Column(precision = 13, scale = 2)
    private BigDecimal balance;
    @Column(name = "additional_limit", precision = 13, scale = 2)
    private BigDecimal limit;

    // cria o número da conta com base no id
    @PostPersist
    private void createNumber() {
        Random random = new Random();
        this.number = String.format("%08d",this.id)+"-"+ random.nextInt(9 - 0);

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

    public String getAgency() {
        return agency;
    }

    // numero da agência padrão e não permite alteração
    public void setAgency(String agency) {
        this.agency = "0109";
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }
}
