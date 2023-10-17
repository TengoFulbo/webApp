<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="es">

<head>
  <!-- <meta charset="UTF-8" /> -->
  <!-- <meta name="viewport" content="width=device-width, initial-scale=1.0" /> -->
  <!-- <title>TursistaUY</title> -->
<!--  -->
  <!-- LINKS DE CSS -->
  <link rel="stylesheet" href="./src/css/homeMulti.css" />
<!--  -->
  <!-- GOOGLE FONTS -->
  <!-- <link rel="preconnect" href="https://fonts.googleapis.com" /> -->
  <!-- <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin /> -->
  <!-- <link href="https://fonts.googleapis.com/css2?family=Dancing+Script&family=Hind&family=Montserrat&display=swap" -->
    <!-- rel="stylesheet" /> -->
<!--  -->
  <!-- MATERIALIZE -->
  <!-- <link rel="stylesheet" href="src/css/materialize.css" /> -->
  <!-- <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" /> -->
</head>

<%@ include file="./utils/head.jsp" %>

<body>
  <!-- NAVBAR -->
  <nav class="navbar">
    <div class="nav-wrapper navbar__wrap">
      <div class="navbar__leftwrap">
        <div class="brand-logo">
          <img src="src/img/kombi.png" alt="Imagen de una combi" class="navbar__logo--img" />
          <a href="index.html">TurismoUY</a>
        </div>
      </div>
      <ul class="right aside_trigger">
        <li>
          <a href="#" data-target="slide-out" class="sidenav-trigger navbar__sideopen"><i
              class="material-icons">arrow_forward</i></a>
        </li>
      </ul>
    </div>
  </nav>
  
  <%@ include file="./utils/sidenav.jsp" %>
  <!-- SIDENAV -->
  <!-- <ul id="slide-out" class="sidenav home_side sidenav-fixed trasparent__scroll">
    <li>
      <div class="user-view">
        <div class="background">
          <img src="src/img/wavesidebg.svg" />
        </div>
        <a href="./miCuenta.html"><img class="circle" src="src/img/avatar1.png" /></a>
        <a href="./miCuenta.html"><span class="white-text name">John Doe</span></a>
        <a href="./miCuenta.html"><span class="white-text email">jdandturk@gmail.com</span></a>
      </div>
    </li> -->

    <!-- <li><a href="./home.html">Inicio</a></li>
    <li><a href="./homeSalidas.html">Salidas</a></li>
    <li><a href="./homeActividad.html">Actividades</a></li>
    <li><a href="./homePaquete.html">Paquetes</a></li>
    <li><a href="./homeUsuarios.html">Usuarios</a></li>
    <div class="divider"></div>
    <li><a href="./misSalidas.html">Mis Salidas</a></li>
    <li><a href="./misActividades.html">Mis Actividades</a></li>
    <li><a href="./misPaquetes.html">Mis Paquetes</a></li>
    <li>
      <div class="divider"></div>
    </li>
    <li><a class="subheader">Cuenta</a></li>
    <li>
      <a href="./miCuenta.html" class="waves-effect"><i class="material-icons">person</i>Mi Cuenta</a>
    </li>
    <li>
      <a class="waves-effect" href="./index.html"><i class="material-icons">exit_to_app</i>Cerrar Sesion</a>
    </li>
  </ul> -->

  <!-- MAIN -->
  <div class="main">
    <div class="mainWrap">

      <!-- page -->
      <section class="page">
        <div class="page__texto">
          <h1 class="page__title">Usuarios</h1>
          <p class="page__p">
            Lorem ipsum dolor, sit amet consectetur adipisicing elit. Qui
            incidunt numquam tempora dolorem vel, facere necessitatibus ullam
            ratione laudantium in, eaque assumenda omnis. Cum vitae eaque hic
            minus corrupti tenetur?
          </p>
        </div>
        <div class="page__wrap">

          <ul class="collection usuariosCollection">
            <%
                List<dataUsuario> usuarios = (List<dataUsuario>) request.getAttribute("usuarios");
                if (usuarios != null) {
                    for (dataUsuario user : usuarios) {
            %>
            <li class="collection-item avatar">
                <img src="src/img/avatar1.png" alt="" class="circle">
                <span class="title"><%= user.getNombre() %> <%= user.getApellido() %></span>
                <p>@<%= user.getNickname() %> <br>
                </p>
                <a class="secondary-content dropdown-trigger" data-target='dropConsultaUser'><i class="material-icons">more_horiz</i></a>
                <!-- <a href="./miCuenta?nickname=<%= user.getNickname() %>" class="secondary-content dropdown-trigger" data-target='dropConsultaUser'><i class="material-icons">more_horiz</i></a>                 -->
                <ul id='dropConsultaUser' class='dropdown-content userConsultaDrop'>
                    <li class="userConsultaDrop--li"><a href="./miCuenta?nickname=<%= user.getNickname() %>" class="secondary-content">Ver Perfil</a></li>
                </ul>
            </li>
            <%
                    }
                }
            %>
          
        </div>
      </section>
    </div>
  </div>

  <!-- FOOTER -->
  <footer class="footer">
    <div class="footer__container">
      <img src="./src/img/kombi.png" alt="" class="footer_img" />
      <div class="footer__text">
        <h3 class="footer__text--title">TurismoUY</h3>
        <p class="footer__text--p">
          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque
          nisi ex, tristique et suscipit et, posuere sit amet erat.
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