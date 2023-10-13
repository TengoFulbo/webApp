<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% String usuario = (String) session.getAttribute("username"); %>

<!DOCTYPE html>
<html lang="es">
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

    <!-- LINKS DE CSS -->
    <link rel="stylesheet" href="./src/css/style.css" />

    <!-- GOOGLE FONTS -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Dancing+Script&family=Hind&family=Montserrat&display=swap"
      rel="stylesheet"
    />

    <!-- MATERIALIZE -->
    <link rel="stylesheet" href="src/css/materialize.css" />
    <link
      href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet"
    />
  </head>
  <body>
    <!-- NAVBAR -->
    <nav class="navbar">
      <div class="nav-wrapper navbar__wrap">
        <div class="brand-logo">
          <img
            src="src/img/kombi.png"
            alt="Imagen de una combi"
            class="navbar__logo--img"
          />
          <a href="#!">TurismoUY</a>
        </div>
        <a href="#" data-target="mobile-demo" class="sidenav-trigger"
          ><i class="material-icons">menu</i></a
        >
        <ul class="right hide-on-med-and-down">
          <li><a class="waves-effect waves-light" href="home">Inicio</a></li>
          <% if (usuario == null) { %>
            <li><a class="waves-effect waves-light" href="login">Ingresar</a></li>
            <li><a class="waves-effect waves-light" href="register">Registrarse</a></li>
          <% } else { %>
            <li><a class="waves-effect waves-light" href="#">Cerrar sesión</a></li>
          <% } %>
        </ul>
      </div>
    </nav>

    <ul class="sidenav" id="mobile-demo">
      <li><a class="waves-effect waves-light" href="home">Inicio</a></li>

      <% if (usuario == null) { %>
        <li><a class="waves-effect waves-light" href="login">Ingresar</a></li>
        <li><a class="waves-effect waves-light" href="register">Registrarse</a></li>
      <% } else { %>
        <li><a class="waves-effect waves-light" href="#">Cerrar sesión</a></li>
      <% } %>
      
    </ul>

    <!-- HEADER -->
    <section>
        <div class="parallax-container z-depth-3">
            <div class="parallax header">
              <img src="src/img/uru4.jpg" />
              <div class="header__wrap">
                  <h1 class="header__frase">“Ve el mundo. Es más fantástico que cualquier sueño.”</h1>
                  <h1 class="header__frase--owner">– Ray Bradbury</h1>
                  <a class="header__btn" href="./home">Viaja!</a>
              </div>
          </div>
    </section>

    <!-- TARJETAS -->
    <section class="cards">
        <div class="card cards__card z-depth-4">
            <div class="card-image waves-effect waves-block waves-light">
              <img class="activator cards__img" src="src/img/actividades.jpg">
            </div>
            <div class="card-content">
              <span class="card-title activator grey-text text-darken-4 cards__title">Actividades<i class="material-icons right">more_vert</i></span>
              <p><a href="#">Ver Actividades</a></p>
            </div>
            <div class="card-reveal">
              <span class="card-title grey-text text-darken-4 cards__title">Actividades<i class="material-icons right">close</i></span>
              <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Adipisci quam, magni porro velit voluptate sed obcaecati consequuntur, eius amet ducimus magnam reprehenderit at soluta autem maiores veritatis doloribus! Ipsa, similique?</p>
            </div>
          </div>
        </div>

        <div class="card cards__card z-depth-4">
            <div class="card-image waves-effect waves-block waves-light">
              <img class="activator cards__img" src="src/img/disfruta.jpg">
            </div>
            <div class="card-content">
              <span class="card-title activator grey-text text-darken-4 cards__title">Usuarios<i class="material-icons right">more_vert</i></span>
              <p><a href="#">Ver Usuarios</a></p>
            </div>
            <div class="card-reveal">
              <span class="card-title grey-text text-darken-4 cards__title">Usuarios<i class="material-icons right">close</i></span>
              <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Velit temporibus quos natus vel, impedit itaque corrupti officiis. Saepe exercitationem sit, eveniet, ipsum cumque officiis totam sint ducimus, nostrum ab vero!</p>
            </div>
          </div>
        </div>

        <div class="card cards__card z-depth-4">
            <div class="card-image waves-effect waves-block waves-light">
              <img class="activator cards__img" src="src/img/descubre.jpg">
            </div>
            <div class="card-content">
              <span class="card-title activator grey-text text-darken-4 cards__title">Salidas<i class="material-icons right">more_vert</i></span>
              <p><a href="#">Ver Salidas</a></p>
            </div>
            <div class="card-reveal">
              <span class="card-title grey-text text-darken-4 cards__title">Salidas<i class="material-icons right">close</i></span>
              <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellat, perspiciatis consectetur! Temporibus commodi nihil deleniti iure libero maxime earum necessitatibus inventore totam rerum voluptatum ipsa quas voluptatibus corporis, optio aliquid.</p>
            </div>
          </div>
        </div>   
    </section>

    <!-- PARALLAX SECUNDARIO / DIVIDER -->
    <section class="secondParallax">
        <div class="parallax-container ">
            <div class="parallax z-depth-3"><img src="src/img/explora.jpg"></div>
          </div>
    </section>

    <!-- page -->
    <section class="page">
        <div class="page__texto">
            <h1 class="page__title">Salidas mas Recientes</h1>
            <p class="page__p">Lorem ipsum dolor, sit amet consectetur adipisicing elit. Qui incidunt numquam tempora dolorem vel, facere necessitatibus ullam ratione laudantium in, eaque assumenda omnis. Cum vitae eaque hic minus corrupti tenetur?</p>
        </div>
        <div class="page__wrap">
    
            <div class="row">
                <div class="col s12 m6  page__card">
                    <div class="card">
                        <div class="card-image">
                            <img src="src/img/uru1.jpg" class="page__card--img">
                            <span class="card-title page__card--title">Salida a Colonia</span>
                            <a class="btn-floating halfway-fab waves-effect waves-light page__card--icon"><i class="material-icons">more_horiz</i></a>
                        </div>
                        <div class="card-content page__card--contenido">
                            <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Reprehenderit nisi impedit eveniet inventore nemo, laboriosam ex ut quas ad totam? Expedita quidem animi iste possimus ullam dolores temporibus natus nam!</p>
                        </div>
                    </div>
                </div>
            </div>
    
            <div class="row">
                <div class="col s12 m6  page__card">
                    <div class="card">
                        <div class="card-image">
                            <img src="src/img/uru2.jpg" class="page__card--img">
                            <span class="card-title page__card--title">Salida a Cabo Polonio</span>
                            <a class="btn-floating halfway-fab waves-effect waves-light page__card--icon"><i class="material-icons">more_horiz</i></a>
                        </div>
                        <div class="card-content page__card--contenido">
                            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolorem, delectus! Explicabo dolorum facere hic tenetur! Fugit quidem porro est illum debitis unde consectetur ut amet, nulla consequuntur, repudiandae magnam nisi!</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col s12 m6  page__card">
                    <div class="card">
                        <div class="card-image">
                            <img src="src/img/uru3.jpg" class="page__card--img">
                            <span class="card-title page__card--title">Salida a Montevideo</span>
                            <a class="btn-floating halfway-fab waves-effect waves-light page__card--icon"><i class="material-icons">more_horiz</i></a>
                        </div>
                        <div class="card-content page__card--contenido">
                            <p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Atque officiis vero dolor commodi fugiat, quaerat optio mollitia omnis aliquid nemo est tenetur nesciunt libero sunt sit dolore iusto! Reprehenderit, consectetur.</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col s12 m6  page__card">
                    <div class="card">
                        <div class="card-image">
                            <img src="src/img/uru5.jpg" class="page__card--img">
                            <span class="card-title page__card--title">Salida a La Paloma</span>
                            <a class="btn-floating halfway-fab waves-effect waves-light page__card--icon"><i class="material-icons">more_horiz</i></a>
                        </div>
                        <div class="card-content page__card--contenido">
                            <p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Doloribus soluta est repellat iusto, magni dolor necessitatibus voluptates vitae, voluptatibus dolores minima quod dolorem. Sit doloribus vitae laboriosam voluptatum. Maiores, unde.</p>
                        </div>
                    </div>
                </div>
            </div>
    
        </div>
    </section>
    
    <!-- TERCER PARALLAX/DIVIDER -->
    <section class="thirdParallax">
        <div class="parallax-container ">
            <div class="parallax z-depth-3"><img src="src/img/globos.jpg"></div>
          </div>
    </section>

    <!-- CARRUSEL -->
    <section class="carrusel">
        <div class="carrusel__text">
            <h1 class="carrusel__title">
                Testimonios
            </h1>
            <p class="carrusel__text">Lorem ipsum dolor sit amet consectetur adipisicing elit. Facilis possimus, dolore, commodi sed earum iusto soluta dolorem sunt placeat optio blanditiis aut aspernatur quo? Veniam nulla maxime quas porro soluta.</p>
        </div>
        <div class="carousel carrusel__main">
            <div class="carousel-item carrusel__item">
                <div class="carrusel__item--wrap">
                    <img src="src/img/avatar1.png" alt="" class="carrusel__item--img">
                    <div class="carrusel__item--text">
                        <h2 class="carrusel__item--uname">User Name</h2>
                        <p class="carrusel__item--umsj"> Lorem ipsum dolor sit amet consectetur, adipisicing elit. Magnam ipsa quam explicabo. Maxime accusamus, et expedita, assumenda tempore unde deleniti aliquam molestiae itaque hic similique, nulla cumque quidem aliquid consequatur?</p>
                    </div>
                </div>
            </div>

            <div class="carousel-item carrusel__item">
                <div class="carrusel__item--wrap">
                    <img src="src/img/avatar2.png" alt="" class="carrusel__item--img">
                    <div class="carrusel__item--text">
                        <h2 class="carrusel__item--uname">User Name</h2>
                        <p class="carrusel__item--umsj"> Lorem ipsum dolor sit amet consectetur, adipisicing elit. Magnam ipsa quam explicabo. Maxime accusamus, et expedita, assumenda tempore unde deleniti aliquam molestiae itaque hic similique, nulla cumque quidem aliquid consequatur?</p>
                    </div>
                </div>
            </div>

            <div class="carousel-item carrusel__item">
                <div class="carrusel__item--wrap">
                    <img src="src/img/avatar3.png" alt="" class="carrusel__item--img">
                    <div class="carrusel__item--text">
                        <h2 class="carrusel__item--uname">User Name</h2>
                        <p class="carrusel__item--umsj"> Lorem ipsum dolor sit amet consectetur, adipisicing elit. Magnam ipsa quam explicabo. Maxime accusamus, et expedita, assumenda tempore unde deleniti aliquam molestiae itaque hic similique, nulla cumque quidem aliquid consequatur?</p>
                    </div>
                </div>
            </div>

            <div class="carousel-item carrusel__item">
                <div class="carrusel__item--wrap">
                    <img src="src/img/avatar4.png" alt="" class="carrusel__item--img">
                    <div class="carrusel__item--text">
                        <h2 class="carrusel__item--uname">User Name</h2>
                        <p class="carrusel__item--umsj"> Lorem ipsum dolor sit amet consectetur, adipisicing elit. Magnam ipsa quam explicabo. Maxime accusamus, et expedita, assumenda tempore unde deleniti aliquam molestiae itaque hic similique, nulla cumque quidem aliquid consequatur?</p>
                    </div>
                </div>
            </div>
          </div>
    </section>


    <!-- FOOTER -->
    <footer class="footer">
        <div class="footer__container">
            <img src="./src/img/kombi.png" alt="" class="footer_img">
            <div class="footer__text">
                <h3 class="footer__text--title">
                    TurismoUY
                </h3>
                <p class="footer__text--p">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque nisi ex, tristique et suscipit et, posuere sit amet erat.
                </p>
            </div>
        </div>
        <div class="footer__p">TengoFulbo &copy; | 2023 - 2023</div>
    </footer>

    <!-- MATERIALIZE JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <!-- MATERIALIZE LOCAL JS -->
    <script src="src/js/materialize.js"></script>
  </body>
</html>
