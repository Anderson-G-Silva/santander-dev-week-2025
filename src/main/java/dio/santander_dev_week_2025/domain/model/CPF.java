package dio.santander_dev_week_2025.domain.model;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CPFValidator.class) // Aponta para a classe de validação
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER}) // Onde a anotação pode ser aplicada
@Retention(RetentionPolicy.RUNTIME) // A anotação deve estar disponível em tempo de execução
public @interface CPF {
    String message() default "CPF inválido"; // Mensagem padrão de erro
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
