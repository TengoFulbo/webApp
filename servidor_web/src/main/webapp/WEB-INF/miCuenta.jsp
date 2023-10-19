<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="./utils/head.jsp" %>
<!-- <%
    dataUsuario user = (dataUsuario) request.getAttribute("user");
%> -->
<head>
  <link rel="stylesheet" href="./src/css/homeMulti.css" />
</head>

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

    <div class="mainWrap userWrap">
      <section class="page">
        <div class="page__texto">
          <h1 class="page__title">Mi Cuenta</h1>
        </div>
        <div class="container center-align">
          <div class="container center-align">
            <div class="row center-align">
              <form class="col s12">
                <div class="row">
                  <img src="src/img/avatar1.png" class="imgUserAccount" alt="Imagen de usuario">
                </div>
                <div class="row">
                  <div class="input-field col s12">
                    <div class="file-field input-field">
                      <div class="btn">
                        <span>File</span>
                        <input type="file">
                      </div>
                      <div class="file-path-wrapper">
                        <input class="file-path validate" type="text">
                      </div>
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="input-field col s6">
                    <input id="nickname" type="text" class="validate" value="<%= (user != null) ? user.getNickname() : "" %>" readonly>
                    <label for="nickname">Nickname</label>
                  </div>
                  <div class="input-field col s6">
                    <input id="nombre" type="text" class="validate" value="<%= (user != null) ? user.getNombre() : "" %>">
                    <label for="nombre">Nombre</label>
                  </div>
                </div>
                <div class="row">
                  <div class="input-field col s6">
                    <input id="apellido" type="text" class="validate" value="<%= (user != null) ? user.getApellido() : "" %>">
                    <label for="apellido">Apellido</label>
                  </div>
                  <div class="input-field col s6">
                    <input id="email" type="email" class="validate" value="<%= (user != null) ? user.getEmail() : "" %>">
                    <label for="email">Email</label>
                  </div>
                </div>
                <div class="row">
                  <div class="input-field col s12">
                    <input id="nacimiento" type="date" class="validate" value="<%= (user != null) ? user.getNacimiento() : "" %>" readonly>
                    <label for="nacimiento">Fecha de Nacimiento</label>
                  </div>
                </div>
                <%
                if (user != null && user.getisProveedor()) { %>
                    <div class="row">
                      <div class="input-field col s12">
                        <textarea id="descripcion" class="materialize-textarea"><%= (user != null && user.getDescripcion() != null) ? user.getDescripcion() : "" %></textarea>
                        <label for="descripcion">Descripción</label>
                      </div>
                    </div>
                    <div class="row">
                      <div class="input-field col s12">
                        <input id="url" type="text" class="validate" value="<%= (user != null && user.getUrl() != null) ? user.getUrl() : "" %>">
                        <label for="url">URL</label>
                      </div>
                    </div>
                <%
                } %>
                <%
                if (user != null && !user.getisProveedor()) { %>
                    <div class="row">
                      <div class="input-field col s12">
                        <input id="nacionalidad" type="text" class="validate" value="<%= (user != null && user.getNacionalidad() != null) ? user.getNacionalidad() : "" %>">
                        <label for="nacionalidad">Nacionalidad</label>
                      </div>
                    </div>
                <%
                }%>
                <button class="btn waves-effect waves-light" type="submit" name="action">Guardar
                  <i class="material-icons right">send</i>
                </button>
              </form>
            </div>
          </div>

        </div>
      </section>
    </div>
  </div>
  <!-- MODALS -->
  <div id="modalInscribirse" class="modal">
    <div class="modal-content">
      <h4>Inscripcion</h4>
      <div class="divider"></div>
      <form action="#" class="modal__inscribirse">
        <p>Ingresa la cantidad de personas a inscribirse:</p>
        <div class="input-field col s6 modal__inscribirse--ninput">
          <input id="numero" type="number" class="validate" />
          <label for="numero">Ingresa un número</label>
        </div>
        <p>Forma de pago:</p>
        <p>
          <label>
            <input id="formaPago" name="formaPago" value="general" type="radio" checked />
            <span>General</span>
          </label>
        </p>
        <p>
          <label>
            <input id="formaPago" name="formaPago" value="paquete" type="radio" />
            <span>Paquete</span>
          </label>
        </p>
        <button class="btn waves-effect waves-light modal__inscribirse--submit" type="submit" name="action">
          Enviar
          <i class="material-icons right">send</i>
        </button>
      </form>
    </div>
    <div class="modal-footer">
      <a href="#!" class="modal-close waves-effect waves-green btn-flat">Cerrar</a>
    </div>
  </div>

  <div id="modalConsulta" class="modal">
    <div class="modal-content">
      <h4>Consulta de Salida</h4>
      <form>
        <div class="input-field">
          <input id="nombre" type="text" class="validate" readonly value="Salida 1" />
          <label for="nombre" class="active">Nombre</label>
        </div>
        <div class="input-field">
          <input id="cant" type="number" class="validate" readonly value="99" />
          <label for="cant" class="active">Cantidad de lugares</label>
        </div>
        <div class="input-field">
          <input id="dateSalida" type="date" class="validate" disabled value="2023-01-01" />
          <label for="dateSalida" class="active">Fecha de salida</label>
        </div>
        <div class="input-field">
          <input id="dateAlta" type="date" class="validate" disabled value="2023-01-01" />
          <label for="dateAlta" class="active">Fecha de alta</label>
        </div>
        <div class="input-field">
          <input id="lugarSalida" type="text" class="validate" readonly value="San Jose" />
          <label for="lugarSalida" class="active">Lugar de Salida</label>
        </div>
        <div class="divider"></div>
        <ul class="collection with-header">
          <li class="collection-header">
            <h4>Actividades</h4>
          </li>
          <li class="collection-item">Actividad1</li>
          <li class="collection-item">Actividad2</li>
          <li class="collection-item">Actividad3</li>
          <li class="collection-item">Actividad4</li>
        </ul>
      </form>
    </div>
    <div class="modal-footer">
      <a href="#!" class="modal-close waves-effect waves-green btn-flat">Cerrar</a>
    </div>
  </div>

  
  <%@ include file="./utils/footer.jsp" %>

  <!-- MATERIALIZE JS -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
  <!-- MATERIALIZE LOCAL JS -->
  <script src="src/js/materialize.js"></script>
</body>

</html>