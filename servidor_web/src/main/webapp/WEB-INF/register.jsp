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
      <div class="container" id="container">
        <div class="form-container sign-up-container">
          <form action="#">
            <h1>Proveedor</h1>
            <span>Registrate como proveedor</span>
            <input type="text" placeholder="Nickname" />
            <input type="text" placeholder="Nombre y Apellido" />
            <input type="email" placeholder="Email" />
            <input type="date" placeholder="Fecha de nacimiento" />
            <input type="text" placeholder="Descripcion" />
            <input type="text" placeholder="Sitio Web" />
            <input type="password" placeholder="Password" />
            <button>Registrarse</button>
          </form>
        </div>
        <div class="form-container sign-in-container">
          <form action="#">
            <h1>Turista</h1>
            <div class="social-container">
              <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
              <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
              <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
            </div>
            <span>Quieres registrarte como turista? hazlo!</span>
            <input type="text" placeholder="Nickname" />
            <input type="text" placeholder="Nombre y Apellido" />
            <input type="email" placeholder="Email" />
            <input type="date" placeholder="Fecha de nacimiento" />
            <input type="text" placeholder="Nacionalidad" />
            <input type="password" placeholder="Password" /> 
            <button>Registrarse</button>
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
  </body>
</html>
