import java.util.List;
//Registra la lista de monedas habilitadas
public record SupportedCodesResponse(String result, List<List<String>> supported_codes) {}
