<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>
      <%
        String pageTitle = (String) request.getAttribute("pageTitle");
        if (pageTitle == null) {
            out.print("TurismoUY");
        } else {
            out.print(pageTitle);
        }
      %>
    </title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <link rel="stylesheet" href="./src/css/register.css">
  </head>
  <body>
    <div class="topButtons">
      <a href="./" class="return">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
          <path stroke-linecap="round" stroke-linejoin="round" d="M10.5 19.5L3 12m0 0l7.5-7.5M3 12h18" />
        </svg>
      </a>
      <a href="./login" class="return">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
          <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 6a3.75 3.75 0 11-7.5 0 3.75 3.75 0 017.5 0zM4.501 20.118a7.5 7.5 0 0114.998 0A17.933 17.933 0 0112 21.75c-2.676 0-5.216-.584-7.499-1.632z" />
        </svg>
        <h2 class="topText">Ingresar</h2>     
      </a>
    </div>
    <div class="main">
      <div class="container" id="container" style="min-height: 640px !important;">
        <div class="form-container sign-up-container">
			<form name="registroFormProveedor" action="./registerProveedor" method="post" onsubmit="return validarFormularioProveedor();">            <input type="hidden" name="isProveedor" value="true">
            <h1>Proveedor</h1>
            <span>Registrate como proveedor</span>
            <input id="nickIdP" name="nickname" type="text" placeholder="Nickname" />
            <span id="spanResultadoP"></span>
            <input name="nombreP" type="text" placeholder="Nombre" />
            <span id="spanResultadoNombreP"></span>        
            <input name="apellidoP" type="text" placeholder="Apellido" />
            <span id="spanResultadoApellidoP"></span>          
            <input id="emailIdP" name="email" type="email" placeholder="Email" />
            <span id="spanResultadoCorreoP"></span>
            <input name="fechaNP" type="date" placeholder="Fecha de nacimiento" />
            <span id="spanResultadoFechaP"></span>
            <input name="descP" type="text" placeholder="Descripcion" />
            <span id="spanResultadoDescP"></span>
            <input name="urlP" type="text" placeholder="Sitio Web" />
            <span id="spanResultadoUrlP"></span>
            <input name="passwordP" type="password" placeholder="Password" />
            <input name="repitPasswordP" type="password" placeholder="Repite Password" />
            <span id="spanResultadoPasswordP"></span>
            <button id="botonIdP">Registrarse</button>
          </form>
        </div>
        <div class="form-container sign-in-container">
          <form action="./registerTurista" method="post">
            <h1>Turista</h1>
            <div class="social-container">
              <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
              <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
              <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
            </div>
            <span>Quieres registrarte como turista? hazlo!</span>
            <input id="nickIdT" name="nickname" type="text" placeholder="Nickname" />
            <span id="spanResultadoT"></span>
            <input name="nombreT" type="text" placeholder="Nombre" />
            <span id="spanResultadoNombreT"></span>
            <input name="apellidoT" type="text" placeholder="Apellido" />
            <span id="spanResultadoApellidoT"></span>
            <input id="emailIdT" name="email" type="email" placeholder="Email" />
            <span id="spanResultadoCorreoT"></span>
            <input name="fechaNT" type="date" placeholder="Fecha de nacimiento" />
            <span id="spanResultadoFechaT"></span>
            <input name="nacionalidadT" type="text" placeholder="Nacionalidad" />
            <span id="spanResultadoNacionalidadT"></span>
            <input name="passwordT" type="password" placeholder="Password" /> 
            <input name="repitPasswordT" type="password" placeholder="Repite password" />
			<span id="spanResultadoPasswordT"></span>
            <button id="botonIdT">Registrarse</button>
          </form>
        </div>
        <div class="overlay-container">
          <div class="overlay">
            <div class="overlay-panel overlay-left">
              <h1>Espera! eres un turista, no? </h1>
              <p>
                Ven y registrate para poder empezar a viajar y descubrir nuevos horizontes!
              </p>
              <button class="ghost" id="signIn">Registrarse como turista</button>
            </div>
            <div class="overlay-panel overlay-right">
              <h1>Hola, No eres proveedor?</h1>
              <p>Ven y registrate como uno!</p>
              <button class="ghost" id="signUp">Registrarse como proveedor</button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <script src="./src/js/register.js"></script>

    <script>
	    var cooldownTime = 1000; // Tiempo en milisegundos (1 segundo en este ejemplo)
	    var cooldownTimerP, cooldownTimerT;
	
	    // Función para manejar la respuesta del servidor
	    function otraFuncion(respuesta) {
	        // Acceder al elemento span en tu HTML
	        var spanResultado = $("#spanResultado");
	
	        // Actualizar el contenido del span según la respuesta del servidor
	        if (respuesta.valido) {
	            spanResultado.text("El nick es válido");
	        } else {
	            spanResultado.text("El nick NO es válido");
	        }
	    }
	
	    // Función para validar el nick con un "cooldown"
	    function validarNickConCooldown(valorInput) {
	        // Realizar la solicitud AJAX
	        $.ajax({
	            type: "POST",
	            url: "validarNickname", // Ajusta la URL según tu configuración
	            data: {valor: valorInput},
	            success: function (respuesta) {
	                // Manejar la respuesta del servidor
	                console.log(respuesta);
	                // Aquí puedes ejecutar otra función con la respuesta
	                otraFuncion(respuesta);
	            }
	        });
	    }
	    
	    function otraFuncion(respuesta) {
	        var spanResultadoT = $("#spanResultadoT");
	        var spanResultadoP = $("#spanResultadoP");

	        if (respuesta.valido) {
	            spanResultadoT.text("El nick es válido");
	            spanResultadoP.text("El nick es válido");
	            console.log("El nick es válido");
	        } else {
	            spanResultadoT.text("El nick NO es válido");
	            spanResultadoP.text("El nick NO es válido");
	            console.log("El nick NO es válido");
	        }
	    }
	
	    // Asociar el evento oninput al input con cooldown para #nickIdP
	    $("#nickIdP").on("input", function () {
	        // Limpiar el temporizador si existe
	        clearTimeout(cooldownTimerP);
	
	        // Configurar un nuevo temporizador
	        cooldownTimerP = setTimeout(function () {
	            // Obtener el valor actual del input
	            var valorInputTurista = $("#nickIdT").val();
	            var valorInputProveedor = $("#nickIdP").val();
	
	            if (valorInputProveedor !== "") {
	                valorInput = valorInputProveedor;
	            } else {
	                valorInput = valorInputTurista;
	            }
	
	            // Llamar a la función ejecutarConAjax después del "cooldown"
	            validarNickConCooldown(valorInput);
	        }, cooldownTime);
	    });
	
	    // Asociar el evento oninput al input con cooldown para #nickIdT
	    $("#nickIdT").on("input", function () {
	        // Limpiar el temporizador si existe
	        clearTimeout(cooldownTimerT);
	
	        // Configurar un nuevo temporizador
	        cooldownTimerT = setTimeout(function () {
	            // Obtener el valor actual del input
	            var valorInputTurista = $("#nickIdT").val();
	            var valorInputProveedor = $("#nickIdP").val();
	
	            if (valorInputTurista !== "") {
	                valorInput = valorInputTurista;
	            } else {
	                valorInput = valorInputProveedor;
	            }
	
	            // Llamar a la función ejecutarConAjax después del "cooldown"
	            validarNickConCooldown(valorInput);
	        }, cooldownTime);
	    });
	    
	    
	    
	    
	    // Función para manejar la respuesta del servidor
	    function otraFuncionParaMail(respuesta) {
	        // Acceder al elemento span en tu HTML
	        var spanResultadoCorreo = $("#spanResultadoCorreo");
	
	        // Actualizar el contenido del span según la respuesta del servidor
	        if (respuesta.valido) {
	            spanResultado.text("El email es válido");
	        } else {
	            spanResultado.text("El email NO es válido");
	        }
	    }
	    
	 // Función para validar el email con un "cooldown"
	    function validarEmailConCooldown(valorInput) {
		 
	        // Realizar la solicitud AJAX
	        $.ajax({
	            type: "POST",
	            url: "validarCorreo", // Ajusta la URL según tu configuración
	            data: {valor: valorInput},
	            success: function (respuesta) {
	                // Manejar la respuesta del servidor
	                console.log(respuesta);
	                // Aquí puedes ejecutar otra función con la respuesta
	                otraFuncionParaEmail(respuesta);
	            }
	        });
	    }
	 
	    function otraFuncionParaEmail(respuesta) {
	        var spanResultadoCorreoT = $("#spanResultadoCorreoT");
	        var spanResultadoCorreoP = $("#spanResultadoCorreoP");
	        if (respuesta.valido) {
		    	var regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
		    	// Verificar si el nombre cumple con la expresión regular y no es vacío
		        var esEmailValido = regex.test(valorInput) && valorInput.trim() !== '';
		        if (esEmailValido) {
		            spanResultadoCorreoP.text("El email es válido");
		            spanResultadoCorreoT.text("El email es válido");
		            console.log("El email es válido");
		        } else {
		            spanResultadoCorreoP.text("El email NO es válido (debe de tener un fromato parecido a este: prueba@gmail.com, y no puede ser vacio)");
		            spanResultadoCorreoT.text("El email NO es válido (debe de tener un fromato parecido a este: prueba@gmail.com, y no puede ser vacio)");
		            console.log("El email NO es válido");
		        }
	        } else {
	            spanResultadoCorreoT.text("El email NO es válido (debe de tener un fromato parecido a este: prueba@gmail.com, y no puede ser vacio)");
	            spanResultadoCorreoP.text("El email NO es válido (debe de tener un fromato parecido a este: prueba@gmail.com, y no puede ser vacio)");
	            console.log("El email NO es válido");
	        }
	    }
	    
	    // Asociar el evento oninput al input con cooldown para #nickIdP
	    $("#emailIdP").on("input", function () {
	        // Limpiar el temporizador si existe
	        clearTimeout(cooldownTimerP);
	
	        // Configurar un nuevo temporizador
	        cooldownTimerP = setTimeout(function () {
	            // Obtener el valor actual del input
	            var valorInputCorreoTurista = $("#emailIdT").val();
	            var valorInputCorreoProveedor = $("#emailIdP").val();
	
	            if (valorInputCorreoProveedor !== "") {
	                valorInput = valorInputCorreoProveedor;
	            } else {
	                valorInput = valorInputCorreoTurista;
	            }
	
	            // Llamar a la función ejecutarConAjax después del "cooldown"
	            validarEmailConCooldown(valorInput);
	        }, cooldownTime);
	    });
	    
	    // Asociar el evento oninput al input con cooldown para #nickIdT
	    $("#emailIdT").on("input", function () {
	        // Limpiar el temporizador si existe
	        clearTimeout(cooldownTimerT);
	
	        // Configurar un nuevo temporizador
	        cooldownTimerT = setTimeout(function () {
	            // Obtener el valor actual del input
	            var valorInputCorreoTurista = $("#emailIdT").val();
	            var valorInputCorreoProveedor = $("#emailIdP").val();
	
	            if (valorInputCorreoTurista !== "") {
	                valorInput = valorInputCorreoTurista;
	            } else {
	                valorInput = valorInputCorreoProveedor;
	            }
	
	            // Llamar a la función ejecutarConAjax después del "cooldown"
	            validarEmailConCooldown(valorInput);
	        }, cooldownTime);
	    });
	    
	    // Función para validar el nombre con un "cooldown"
	    function validarNombreConCooldown(valorInput) {
	        // Expresión regular para permitir solo letras y espacios, sin comillas
	        var regex = /^[a-zA-Z\s]+$/;

	        // Verificar si el nombre cumple con la expresión regular y no es vacío
	        var esNombreValido = regex.test(valorInput) && valorInput.trim() !== '';

	        // Acceder al elemento span correspondiente en tu HTML
	        var spanResultadoNombreP = $("#spanResultadoNombreP");
	        var spanResultadoNombreT = $("#spanResultadoNombreT");

	        // Actualizar el contenido del span según la validación
	        if (esNombreValido) {
	            spanResultadoNombreP.text("El nombre es válido");
	            spanResultadoNombreT.text("El nombre es válido");
	        } else {
	            spanResultadoNombreP.text("El nombre NO es válido (solo letras y espacios, no puede estar vacío)");
	            spanResultadoNombreT.text("El nombre NO es válido (solo letras y espacios, no puede estar vacío)");
	        }
	    }

	    // Asociar el evento oninput al input con cooldown para #nombre en proveedor
	    $("input[name='nombreP']").on("input", function () {
	        // Limpiar el temporizador si existe
	        clearTimeout(cooldownTimerP);

	        // Configurar un nuevo temporizador
	        cooldownTimerP = setTimeout(function () {
	            // Obtener el valor actual del input
	            var valorInputNombre = $("input[name='nombreP']").val();

	            // Llamar a la función validarNombreConCooldown después del "cooldown"
	            validarNombreConCooldown(valorInputNombre);
	        }, cooldownTime);
	    });

	    // Asociar el evento oninput al input con cooldown para #nombre en turista
	    $("input[name='nombreT']").on("input", function () {
	        // Limpiar el temporizador si existe
	        clearTimeout(cooldownTimerT);

	        // Configurar un nuevo temporizador
	        cooldownTimerT = setTimeout(function () {
	            // Obtener el valor actual del input
	            var valorInputNombre = $("input[name='nombreT']").val();

	            // Llamar a la función validarNombreConCooldown después del "cooldown"
	            validarNombreConCooldown(valorInputNombre);
	        }, cooldownTime);
	    });
	    
	    // Función para validar el apellido con un "cooldown"
	    function validarApellidoConCooldown(valorInput) {
	        // Expresión regular para permitir solo letras y espacios
	        var regex = /^[a-zA-Z\s']+$/;

	        // Verificar si el apellido cumple con la expresión regular y no es vacío
	        var esApellidoValido = regex.test(valorInput) && valorInput.trim() !== '';

	        // Acceder al elemento span correspondiente en tu HTML
	        var spanResultadoApellidoP = $("#spanResultadoApellidoP");
	        var spanResultadoApellidoT = $("#spanResultadoApellidoT");

	        // Actualizar el contenido del span según la validación
	        if (esApellidoValido) {
	            spanResultadoApellidoP.text("El apellido es válido");
	            spanResultadoApellidoT.text("El apellido es válido");
	        } else {
	            spanResultadoApellidoP.text("El apellido NO es válido (solo letras y espacios, no puede estar vacío)");
	            spanResultadoApellidoT.text("El apellido NO es válido (solo letras y espacios, no puede estar vacío)");
	        }
	    }

	    // Asociar el evento oninput al input con cooldown para #apellido en proveedor
	    $("input[name='apellidoP']").on("input", function () {
	        // Limpiar el temporizador si existe
	        clearTimeout(cooldownTimerP);

	        // Configurar un nuevo temporizador
	        cooldownTimerP = setTimeout(function () {
	            // Obtener el valor actual del input
	            var valorInputApellido = $("input[name='apellidoP']").val();

	            // Llamar a la función validarApellidoConCooldown después del "cooldown"
	            validarApellidoConCooldown(valorInputApellido);
	        }, cooldownTime);
	    });

	    // Asociar el evento oninput al input con cooldown para #apellido en turista
	    $("input[name='apellidoT']").on("input", function () {
	        // Limpiar el temporizador si existe
	        clearTimeout(cooldownTimerT);

	        // Configurar un nuevo temporizador
	        cooldownTimerT = setTimeout(function () {
	            // Obtener el valor actual del input
	            var valorInputApellido = $("input[name='apellidoT']").val();

	            // Llamar a la función validarApellidoConCooldown después del "cooldown"
	            validarApellidoConCooldown(valorInputApellido);
	        }, cooldownTime);
	    });
	    
	    // Función para validar la descripción solo cuando está vacía
	    function validarDescripcionConCooldown(valorInput) {
	        // Verificar si la descripción está vacía
	        var esDescripcionVacia = valorInput.trim() === '';

	        // Acceder al elemento span correspondiente en tu HTML
	        var spanResultadoDescP = $("#spanResultadoDescP");

	        // Actualizar el contenido del span solo si la descripción está vacía
	        if (esDescripcionVacia) {
	            spanResultadoDescP.text("La descripción no puede estar vacía");
	        } else {
	            spanResultadoDescP.text("La descripcion es valida");
	        }
	    }

	    // Asociar el evento oninput al input con cooldown para #descP
	    $("input[name='descP']").on("input", function () {
	        // Limpiar el temporizador si existe
	        clearTimeout(cooldownTimerP);

	        // Configurar un nuevo temporizador
	        cooldownTimerP = setTimeout(function () {
	            // Obtener el valor actual del input
	            var valorInputDesc = $("input[name='descP']").val();

	            // Llamar a la función validarDescripcionConCooldown después del "cooldown"
	            validarDescripcionConCooldown(valorInputDesc);
	        }, cooldownTime);
	    });
	    
	    // Función para validar la URL solo cuando está vacía
	    function validarUrlConCooldown(valorInput) {
	        // Verificar si la URL está vacía
	        var esUrlVacia = valorInput.trim() === '';

	        // Acceder al elemento span correspondiente en tu HTML
	        var spanResultadoUrlP = $("#spanResultadoUrlP");

	        // Actualizar el contenido del span solo si la URL está vacía
	        if (esUrlVacia) {
	            spanResultadoUrlP.text("La URL no puede estar vacía");
	        } else {
	            spanResultadoUrlP.text("La URL es valida");
	        }
	    }

	    // Asociar el evento oninput al input con cooldown para #urlP
	    $("input[name='urlP']").on("input", function () {
	        // Limpiar el temporizador si existe
	        clearTimeout(cooldownTimerP);

	        // Configurar un nuevo temporizador
	        cooldownTimerP = setTimeout(function () {
	            // Obtener el valor actual del input
	            var valorInputUrl = $("input[name='urlP']").val();

	            // Llamar a la función validarUrlConCooldown después del "cooldown"
	            validarUrlConCooldown(valorInputUrl);
	        }, cooldownTime);
	    });
	    
	    // Función para validar la nacionalidad solo cuando está vacía
	    function validarNacionalidadConCooldown(valorInput) {
	        // Verificar si la nacionalidad está vacía
	        var esNacionalidadVacia = valorInput.trim() === '';

	        // Acceder al elemento span correspondiente en tu HTML
	        var spanResultadoNacionalidadT = $("#spanResultadoNacionalidadT");

	        // Actualizar el contenido del span solo si la nacionalidad está vacía
	        if (esNacionalidadVacia) {
	            spanResultadoNacionalidadT.text("La nacionalidad no puede estar vacía");
	        } else {
	            spanResultadoNacionalidadT.text("La nacionalidad es valida");
	        }
	    }

	    // Asociar el evento oninput al input con cooldown para #nacionalidadT
	    $("input[name='nacionalidadT']").on("input", function () {
	        // Limpiar el temporizador si existe
	        clearTimeout(cooldownTimerT);

	        // Configurar un nuevo temporizador
	        cooldownTimerT = setTimeout(function () {
	            // Obtener el valor actual del input
	            var valorInputNacionalidad = $("input[name='nacionalidadT']").val();

	            // Llamar a la función validarNacionalidadConCooldown después del "cooldown"
	            validarNacionalidadConCooldown(valorInputNacionalidad);
	        }, cooldownTime);
	    });
	    
	 // Function to validate if the password and repeat password fields are equal
	    function validarPasswordTurista() {
	        // Get the values of password and repeat password fields
	        var password = $("input[name='passwordT']").val();
	        var repeatPassword = $("input[name='repitPasswordT']").val();

	        // Access the span element for displaying the validation result
	        var spanResultadoPasswordT = $("#spanResultadoPasswordT");

	        // Check if the passwords match
	        if (password === repeatPassword) {
	            spanResultadoPasswordT.text("Las contraseñas coinciden");
	            document.getElementById("botonIdT").disabled = false;
	        } else {
	            spanResultadoPasswordT.text("Las contraseñas no coinciden");
	            document.getElementById("botonIdT").disabled = true;
	        }
	    }

	    // Associate the event oninput to the input fields for password and repeat password for Turista
	    $("input[name='passwordT'], input[name='repitPasswordT']").on("input", function () {
	        // Call the validation function after a brief cooldown
	        clearTimeout(cooldownTimerT);
	        cooldownTimerT = setTimeout(validarPasswordTurista, cooldownTime);
	    });
	    
		 // Function to validate if the password and repeat password fields are equal
	    function validarPasswordProveedor() {
	        // Get the values of password and repeat password fields
	        var passwordP = $("input[name='passwordP']").val();
	        var repeatPasswordP = $("input[name='repitPasswordP']").val();

	        // Access the span element for displaying the validation result
	        var spanResultadoPasswordP = $("#spanResultadoPasswordP");

	        // Check if the passwords match
	        if (passwordP === repeatPasswordP) {
	            spanResultadoPasswordP.text("Las contraseñas coinciden");
	            document.getElementById("botonIdP").disabled = false;
	        } else {
	            spanResultadoPasswordP.text("Las contraseñas no coinciden");
	            document.getElementById("botonIdP").disabled = true;
	        }
	    }

	    // Associate the event oninput to the input fields for password and repeat password for Turista
	    $("input[name='passwordP'], input[name='repitPasswordP']").on("input", function () {
	        // Call the validation function after a brief cooldown
	        clearTimeout(cooldownTimerT);
	        cooldownTimerT = setTimeout(validarPasswordProveedor, cooldownTime);
	    });
	    
	 // Función para validar el nombre con un "cooldown"
	    function validarFechaConCooldown(valorInput) {
	        // Expresión regular para permitir solo letras y espacios, sin comillas

	        // Verificar si el nombre cumple con la expresión regular y no es vacío
	        var esFechaValida = valorInput.trim() !== '';

	        // Acceder al elemento span correspondiente en tu HTML
	        var spanResultadoFechaP = $("#spanResultadoFechaP");
	        var spanResultadoFechaT = $("#spanResultadoFechaT");

	        // Actualizar el contenido del span según la validación
	        if (esFechaValida) {
	            spanResultadoFechaP.text("La fecha es válida");
	            spanResultadoFechaT.text("La fecha es válida");
	        } else {
	            spanResultadoFechaP.text("La fecha NO es válida (no puede estar vacío)");
	            spanResultadoFechaT.text("La fecha NO es válida (no puede estar vacío)");
	        }
	    }

	    // Asociar el evento oninput al input con cooldown para #nombre en proveedor
	    $("input[name='fechaNP']").on("input", function () {
	        // Limpiar el temporizador si existe
	        clearTimeout(cooldownTimerP);

	        // Configurar un nuevo temporizador
	        cooldownTimerP = setTimeout(function () {
	            // Obtener el valor actual del input
	            var valorInputFecha = $("input[name='fechaNP']").val();

	            // Llamar a la función validarNombreConCooldown después del "cooldown"
	            validarFechaConCooldown(valorInputFecha);
	        }, cooldownTime);
	    });

	    // Asociar el evento oninput al input con cooldown para #nombre en turista
	    $("input[name='fechaNT']").on("input", function () {
	        // Limpiar el temporizador si existe
	        clearTimeout(cooldownTimerT);

	        // Configurar un nuevo temporizador
	        cooldownTimerT = setTimeout(function () {
	            // Obtener el valor actual del input
	            var valorInputFecha = $("input[name='fechaNT']").val();

	            // Llamar a la función validarNombreConCooldown después del "cooldown"
	            validarFechaConCooldown(valorInputFecha);
	        }, cooldownTime);
	    });
	    
	    
	</script>
  </body>
</html>
