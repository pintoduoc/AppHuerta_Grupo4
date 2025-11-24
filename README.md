# Asignatura:
  Desarrollo Apps Moviles (002D)
# Integrantes:
 - Benjamín Pinto
 - Oscar Sepulveda
 - Nicolas Alvarado

# Introduccion al proyecto
En esta app de android, podremos ver los productos en venta de la empresa 'Huerto Hogar', para hacer esto antes tendremos que registrarnos.

# Guia para implementacion de API para visualizar los productos
1. Primero debemos correr la api del siguiente repositorio: https://github.com/pintoduoc/API_AppHuerta_Grupp4.git
2. Una vez corriendo, en el navegador abrir el siguiente enlace: http://localhost:8080/doc/swagger-ui/index.html
3. En la sección "Productos", hacer click en el dropdown de "Añadir Producto", luego clickear el botón "Try it out"
4. En el campo JSON de "Request Body", pegar el siguiente texto:

{
  "id": 0,
  "nombre": "Lechuga",
  "descripcion": "Lechuga fresca y orgánica",
  "precio": "$1.200 / unidad",
  "imagenId": 1
}

5. Clickear en botón "Execute"
6. Repetir lo mismo pero con este texto:

{
  "id": 0,
  "nombre": "Zanahoria",
  "descripcion": "Zanahorias recién cosechadas",
  "precio": "$1.000 / kilo",
  "imagenId": 2
}

7. Y nuevamente repetir con este texto:

{
  "id": 0,
  "nombre": "Tomate",
  "descripcion": "Tomates maduros del huerto",
  "precio": "$1.800 / kilo",
  "imagenId": 3
}


# Explicación de tests
En este proyecto, constamos de 6 tests, todos para el registro de usuarios:

1. registrarUsuario_exitosamente() : Este test comprueba si al registrar un usuario correctamente, se obtiene el resultado esperado.
2. registrarUsuario_correoInvalido_muestraError() : Este test comprueba si al registrar un usuario con el **correo invalido**, se obtiene el error esperado.
3. registrarUsuario_contrasenaCorta_muestraError() : Este test comprueba si al registrar un usuario con una **contraseña invalida**, se obtiene el error esperado.
4. registrarUsuario_nombresVacio_muestraError() : Este test, comprueba si al registrar un usuario con el campo de **nombres vacio**, se obtiene el error esperado.
5. registrarUsuario_apellidosVacio_muestraError() : Este test, comprueba si al registrar un usuario con el campo de **apellidos vacio**, se obtiene el error esperado.
6. registrarUsuario_direccionVacia_muestraError() : Este test, comprueba si al registrar un usuario con el campo de **direccion vacio**, se obtiene el error esperado.
