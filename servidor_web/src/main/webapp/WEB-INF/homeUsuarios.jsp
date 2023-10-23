<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<% List<dataUsuario> usuarios = (List<dataUsuario>) request.getAttribute("usuarios"); %>
<head>
  <link rel="stylesheet" href="./src/css/homeMulti.css" />
</head>

<%@ include file="./utils/head.jsp" %>

<body>
  <%@ include file="./utils/navbar.jsp" %>
  
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
                <span class="title"><%= user.getNombre() %> <%= user.getApellido() %>
                  <span class="new badge <%= (user.getisProveedor()) ? "pink darken-3" : "indigo darken-3" %>" data-badge-caption=""><%= (user.getisProveedor()) ? "Proveedor" : "Turista" %></span>
                </span>
                <p>@<%= user.getNickname() %><br>
                </p>
                <div class="right-align">
                  <a class="btn right-align" href="./miCuenta?nickname=<%= user.getNickname() %>">Ver perfil</a>
                </div>
              <%
                    }
                } else {
              %>
                  <h2>Parece que no hay nada por aquí</h2>
            <%
                }
            %>
        </li>
          
        </div>
      </section>
    </div>
  </div>

  <%@ include file="./utils/footer.jsp" %>

  <!-- MATERIALIZE JS -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
  <!-- MATERIALIZE LOCAL JS -->
  <script src="src/js/materialize.js"></script>
</body>

</html>