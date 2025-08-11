# Challenge-ConversorMonedas

# Descripción
Desafío Java  Conversor de Monedas, es un programa desarrollado en Java que tiene por finalidad realizar conversiones del valor de la moneda de un país en el valor de moneda de otro país.
Para ello, el programa utiliza la ExchangeRateAPI la cual es una herramienta que permite consultar las monedas que tiene habilitadas para poder aplicar el tipo de cambio entre una y otra.

Los métodos invocados de la API son:
1) GET https://v6.exchangerate-api.com/v6/YOUR-API-KEY/codes --> Retorna los códigos de moneda disponibles para realizar operaciones con la API.
2) GET https://v6.exchangerate-api.com/v6/YOUR-API-KEY/pair/cod1/cod2 --> Retorna el tipo de cambio entre la moneda base "cod1" y la moneda destino "cod2".

A nivel de estructura, está basado en:
a) Una Clase principal (Main) que maneja las opciones de menú para que el usuario pueda interactuar con el programa.
b) Una Clase que realiza las invocaciones a la API.
c) Tres Clases record:
c.1) Registro de monedas habilitadas por la API
c.2) Registro lista de monedas permitidas 
c.3) Cálculo de la conversión final 


# Status:  Finalizado

# Funciones y Aplicaciones
1) Muestra al usuario una lista con códigos de monedas disponibles y el nombre de cada una de ellas. La lista se muestra dividida en 3 columnas.
2) Solicita al usuario ingresar un código de moneda, que será el base para aplicar el tipo de cambio. Se verifica que el valor ingresado no sea vacío y que el código corresponda con uno de la lista.
3) Solicita al usuario ingresar un código de moneda, que será la moneda destino a la cual se convertirá el tipo de cambio. Se verifica que el valor ingresado no sea vacío y que el código corresponda con uno de la lista.
4) Solicita al usuario ingresar una cantidad para realizar la conversión total. Se verifica que el valor ingresado no sea vacío y sea un valor mayor que cero.
5) Muestra el tipo de cambio y la conversión final de la cantidad solicitado.
6) Consulta al usuario si desea continuar con otra conversión o se le invita a salir del programa.

# Acceso al Proyecto
1) Descargar los fuentes desde https://github.com/etoledomeyer/Challenge-ConversorMonedas.
2) Abrir con un programa que soporte java (Ejemplo: Intellij IDEA).
3) Ir al archivo Main y ejecutar el programa.

# Tecnologías Utilizadas
1) Java versión 21.0.6
2) Gson 2.13.1

# Desarrolladores
1) Engelberth Toledo - Analista Funcional

