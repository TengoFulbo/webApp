<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>
    <% String pageTitle=(String) request.getAttribute("pageTitle"); 
       if (pageTitle==null) { out.print("TurismoUY"); }
       else { out.print(pageTitle); } %>
  </title>
  <link rel="stylesheet" href="./src/css/register.css">
  <style>
    @import url('https://fonts.googleapis.com/css?family=Montserrat:400,800');
    @media only screen and (max-width: 600px) {
      .overlay-container {
        transform: translateY(100%);
        padding-bottom: 25px;
      }

      /* Agregamos estilos para la alerta en pantalla pequeña */
      
    }
    .toast {
        position: fixed;
        top: 16px;
        right: 0%;
        transform: translateX(-50%);
        background-color: #333;
        color: #fff;
        padding: 10px 20px;
        border-radius: 5px;
        opacity: 0;
        transition: opacity 0.3s;
        z-index: 10;
        font-family: 'Montserrat', sans-serif;
      }

      .toast.show {
        opacity: 1;
      }
  </style>
</head>

<body>
  <div class="topButtons">
    <a href="./" class="return">
      <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"
        class="w-6 h-6">
        <path stroke-linecap="round" stroke-linejoin="round" d="M10.5 19.5L3 12m0 0l7.5-7.5M3 12h18" />
      </svg>
    </a>
    <a href="./register" class="return">
      <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"
        class="w-6 h-6">
        <path stroke-linecap="round" stroke-linejoin="round"
          d="M19 7.5v3m0 0v3m0-3h3m-3 0h-3m-2.25-4.125a3.375 3.375 0 11-6.75 0 3.375 3.375 0 016.75 0zM4 19.235v-.11a6.375 6.375 0 0112.75 0v.109A12.318 12.318 0 0110.374 21c-2.331 0-4.512-.645-6.374-1.766z" />
      </svg>
      <h2 class="topText">Registrarse</h2>
    </a>
  </div>
  <div class="main">
    <div class="container" id="container">
      <div class="form-container sign-in-container">
        <form action="./login" method="post">
          <h1>Ingresar</h1>
          <div class="social-container">
            <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
            <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
            <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
          </div>
          <span>Ingresa los campos y busca tus horizontes!</span>
          <input name="username" type="text" placeholder="Nickname" />
          <input name="password" type="password" placeholder="Contraseña" />
          <button>LogIn</button>
        </form>
      </div>
      <div class="overlay-container">
        <div class="overlay">
          <div class="overlay-panel overlay-left">
          </div>
          <div class="overlay-panel overlay-right">
            <h1>Todavía no te has registrado?</h1>
            <p>Ven y regístrate!</p>
            <a href="./register"><button class="ghost" id="signUp">Registrarse</button></a>
          </div>
        </div>
      </div>
    </div>
  </div>

  <script>
    function showToast(message) {
      var toast = document.createElement("div");
      toast.className = "toast";
      toast.textContent = message;
      document.body.appendChild(toast);
  
      setTimeout(function () {
        toast.classList.add("show");
        setTimeout(function () {
          toast.classList.remove("show");
          document.body.removeChild(toast);
        }, 3000); 
      }, 100);
    }
  
    var notificacion = '<%= request.getSession().getAttribute("errorLogin") %>';
  
    if (notificacion && notificacion !== 'null' && notificacion !== 'undefined') {
      showToast(notificacion);
      request.getSession().removeAttribute("errorLogin");
    }
  </script>
  
</body>

</html>
