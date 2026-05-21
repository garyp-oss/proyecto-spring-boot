9. Farmacia Laboratorio → Medicamento
Laboratorio	Medicamento
nombre, pais, web	nombre, principioActivo, formato, precioEuros, conReceta
Listar medicamentos de un laboratorio
Filtrar por principio activo o si requiere receta
Buscar medicamentos por rango de precio
Borrar todos los medicamentos de un laboratorio

INSTRUCCIONES PARA EJECUTARLO 

1. Una vez tengamos todo el repo en nuestro equipo lo unico que tenemos que hacer es poner este comando en la terminal de Visual:

    - ./mvnw spring-boot:run

3. Despues de esto podremos comprobar que funciona poniendo en nuetro buscador de preferencia lo siguiente:

    - Web: http://localhost:8080/web/laboratorios
    - API: http://localhost:8080/api/laboratorios
    - Base de datos: http://localhost:8080/h2-console
  
En estas podremos comprobar que todo funciona correctamente.


