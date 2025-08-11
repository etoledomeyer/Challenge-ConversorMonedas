//Realiza la conversión de una tasa y muestra el resultado
public record ConversionResponse(

        String result,
        String base_code,
        String target_code,
        double conversion_rate,
        double conversion_result
) {}
