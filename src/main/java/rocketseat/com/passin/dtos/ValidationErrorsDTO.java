package rocketseat.com.passin.dtos;

import java.util.List;

public record ValidationErrorsDTO(
        List<String> errors
) {
}
