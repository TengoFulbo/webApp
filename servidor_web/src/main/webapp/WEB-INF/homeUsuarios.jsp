<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<% List<dataUsuario> usuarios = (List<dataUsuario>) request.getAttribute("usuarios"); %>
<head>
  <link rel="stylesheet" href="./src/css/homeMulti.css" />
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

  <!-- MAIN -->
  <div class="main">
    <div class="mainWrap">

      <!-- page -->
      <section class="page">
        <div class="page__texto">
            <h1 class="page__title">Usuarios</h1>
            <p class="page__p">
                Lorem, ipsum dolor sit amet consectetur adipisicing elit. Quidem ut iusto ipsam optio tempore nobis a eum minima, repellat maxime autem. Corporis neque consectetur ex possimus sed voluptatum. Iure, porro?
            </p>
            <p class="page__p">
              Cantidad de usuarios: <%= (usuarios != null && usuarios.size() != 0) ? usuarios.size() : "0" %>
            </p>
            <!-- <%= (usuario != null && usuario.getNombre() != null) ? usuario.getNombre() : "Visitante" %> -->
        </div>
        <div class="page__wrap">

          <ul class="collection usuariosCollection">
            <%
                if (usuarios != null) {
                    for (dataUsuario user : usuarios) {
            %>
            <li class="collection-item avatar">
                <img src="src/img/avatar1.png" alt="" class="circle">
                <span class="title"><%= user.getNombre() %> <%= user.getApellido() %></span>
                <p>@<%= user.getNickname() %> <br>
                </p>
                <a href="./miCuenta?nickname=<%= user.getNickname() %>">Ver perfil</a>
                <!-- <a class="secondary-content dropdown-trigger" data-target='dropConsultaUser'><i class="material-icons">more_horiz</i></a> -->
                <!-- <a href="./miCuenta?nickname=<%= user.getNickname() %>" class="secondary-content dropdown-trigger" data-target='dropConsultaUser'><i class="material-icons">more_horiz</i></a>                 -->
                <!-- <ul id='dropConsultaUser' class='dropdown-content userConsultaDrop'> -->
                  <!-- <li class="userConsultaDrop--li"><a href="./miCuenta?nickname=<%= user.getNickname() %>" class="secondary-content">Ver Perfil</a></li> -->
                <!-- </ul> -->
                <%
                    }
                } else {
            %>
                    <p>Parece que no hay nada por aquí</p>
            <%
                }
            %>
        </li>
          
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