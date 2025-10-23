package dio.santander_dev_week_2025.domain.model;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.InputMismatchException;

public class CPFValidator implements ConstraintValidator<CPF, String> {

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return true; // Considera campos nulos ou vazios como válidos, a menos que se use @NotNull
        }

        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("\\D", "");

        // Validação de formato (11 dígitos)
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais (ex: 111.111.111-11)
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            // Algoritmo para verificar o primeiro dígito verificador
            int soma = 0;
            int peso = 10;
            for (int i = 0; i < 9; i++) {
                soma += Integer.parseInt(cpf.substring(i, i + 1)) * peso--;
            }
            int digito1 = 11 - (soma % 11);
            if (digito1 > 9) {
                digito1 = 0;
            }

            // Algoritmo para verificar o segundo dígito verificador
            soma = 0;
            peso = 11;
            for (int i = 0; i < 10; i++) {
                soma += Integer.parseInt(cpf.substring(i, i + 1)) * peso--;
            }
            int digito2 = 11 - (soma % 11);
            if (digito2 > 9) {
                digito2 = 0;
            }

            // Compara os dígitos verificadores calculados com os do CPF
            return (Integer.parseInt(cpf.substring(9, 10)) == digito1) &&
                    (Integer.parseInt(cpf.substring(10, 11)) == digito2);
        } catch (InputMismatchException | NumberFormatException e) {
            return false;
        }
    }
}
