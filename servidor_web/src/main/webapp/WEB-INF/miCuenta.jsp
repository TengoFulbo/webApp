<%@page import="turismouy.svcentral.Fabrica"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="turismouy.svcentral.middlewares.DataActividad" %>
<%@ page import="turismouy.svcentral.middlewares.DataSalida" %>
<%@ page import="turismouy.svcentral.middlewares.DataInscripcion" %>
<%@ page import="turismouy.svcentral.middlewares.EstadoActividad" %>
<%@ page import="turismouy.svcentral.middlewares.DataUsuario" %>
<%@ page import="turismouy.svcentral.middlewares.Publicador"%>
<%@ page import="turismouy.svcentral.middlewares.PublicadorService"%>
<%@ page import="java.util.List" %>

<%@ include file="./utils/head.jsp" %>
<!-- <%
    DataUsuario user = (DataUsuario) request.getAttribute("user");
%> -->
<head>
  <link rel="stylesheet" href="./src/css/homeMulti.css" />
</head>

<body>
  <%@ include file="./utils/navbar.jsp" %>

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
              <form action="modificarUsuario" method="post" class="col s12" id="imageUploadForm" enctype="multipart/form-data">
                <div class="row">
                  <%
                  if (user == null) { %>
                    <img id="imagen" src="src/img/avatar1.png" class="imgUserAccount" alt="Imagen de usuario">
                    <% } else {
                      if (user.getImagenBase64() == "") { %>
                        <img id="imagen" src="src/img/avatar1.png" class="imgUserAccount" alt="Imagen de usuario">
                      <% } else { %>
                        <img id="imagen" src="data:image/png;base64, <%= user.getImagenBase64() %>" class="imgUserAccount" alt="Imagen de usuario">
                    <% }
                  }
                  %>
                </div>
                <div id="uploadContainer" style="display: none">
                  <div class="progress">
                    <div id="uploadProgress"class="determinate" style="width: 0%"></div>
                  </div>
                  <div id="uploadStatus"></div>
                </div>

                <% if(usuario != null ){ %>
                  <% if(user.getNombre() == usuario.getNombre()){ %>
                     <div class="row">
                      <div class="input-field col s12">
                        <div class="file-field input-field">
                          <div class="btn">
                            <span>File</span>
                            <input type="file" id="imageInput" name="file" accept="image/png, image/jpeg">
                          </div>
                          <div class="file-path-wrapper">
                            <input class="file-path validate" type="text">
                          </div>
                        </div>
                      </div>
                    </div>
                  <% } %>
                <% } %>
                
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
                    <input id="email" type="email" class="validate" value="<%= (user != null) ? user.getEmail() : "" %>" readonly>
                    <label for="email">Email</label>
                  </div>
                </div>
                <div class="row">
                  <div class="input-field col s12">
                    <input id="nacimiento" type="date" class="validate" value="<%= (user != null) ? user.getNacimiento() : "" %>">
                    <label for="nacimiento">Fecha de Nacimiento</label>
                  </div>
                </div>
                <%
                if (user != null && user.isIsProveedor()) { %>
                    <div class="row">
                      <div class="input-field col s12">
                        <textarea id="descripcion" class="materialize-textarea" readonly><%= (user != null && user.getDescripcion() != null) ? user.getDescripcion() : "" %></textarea>
                        <label for="descripcion">Descripción</label>
                      </div>
                    </div>
                    <div class="row">
                      <div class="input-field col s12">
                        <input id="url" type="text" class="validate" value="<%= (user != null && user.getUrl() != null) ? user.getUrl() : "" %>" readonly>
                        <label for="url">URL</label>
                      </div>
                    </div>
                <%
                } %>
                <%
                if (user != null && !user.isIsProveedor()) { %>
                    <div class="row">
                      <div class="input-field col s12">
                        <input id="nacionalidad" readonly type="text" class="validate" value="<%= (user != null && user.getNacionalidad() != null) ? user.getNacionalidad() : "" %>">
                        <label for="nacionalidad">Nacionalidad</label>
                      </div>
                    </div>
                <%
                }%>
                <!-- <input type="btn waves-effect waves-light" value="Subir" onclick="subirDatos()"> -->
                <!-- <a type="btn waves-effect waves-light" onclick="subirDatos()">Subir</a> -->
                <% if(usuario != null ){ %>
                  <% if(user.getNombre() == usuario.getNombre()){ %>
                    <button class="btn waves-effect waves-light" name="action" onclick="subirDatos()">Guardar
                      <i class="material-icons right">send</i>
                    </button>
                  <% } %>
                <% } %>
              </form>
            </div>
          </div>
          <% if(usuario != null ){ %>
            <% if(user.getNombre() == usuario.getNombre()){ %>
              <% if(user.isIsProveedor()){ %>
                <h4>Actividades</h4>
                <ul class="collection">
                <%
                if (user != null) {
                  if (user.getActividades() != null) {
                    for (DataActividad actividad : user.getActividades()) {
                %>
                    <!-- <p><%= actividad.getNombre() %></p> -->
                    <li class="collection-item">
                      <div><%= actividad.getNombre() %>
                <span class="new badge <%= (actividad.getEstado() == EstadoActividad.FINALIZADA) ? "pink darken-3" : "indigo darken-3" %>" data-badge-caption="">
                    <% 
                        String estadoCaption = "";
                        if (actividad.getEstado() == EstadoActividad.AGREGADA) {
                            estadoCaption = "Agregada";
                        } else if (actividad.getEstado() == EstadoActividad.FINALIZADA) {
                            estadoCaption = "Finalizada";
                        } else if (actividad.getEstado() == EstadoActividad.CONFIRMADA){
                            estadoCaption = "Confirmada";
                        } else{
                          estadoCaption = "Rechazada";
                        }
                    %>
                    <%= estadoCaption %>
                  </span>                
                </div>
                      </li>
                  <%
                      }
                    }
                  }
                  %>
                </ul>
          <% }else{%>
            <h4>Salidas</h4>
            <ul class="collection">
            <%
            if (user != null) {
              if (user.getSalidas() != null) {
                //IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
                Publicador API = new PublicadorService().getPublicadorPort();
            	DataUsuario miuserDt = API.usuarioMostrarInfo(user.getNickname());
            	List<DataInscripcion> inscripciones = miuserDt.getInscripciones(); 
                
                for (DataInscripcion ins : inscripciones) {
            %>
                <li class="collection-item" style="border-bottom: 3px dashed #ccc;">
                	<div>
                	<%= ins.getSalida().getNombre() %>               
            		</div>
            		<div class="row">
            			<ul class="collection">
            				<li class="collection-item">
            					Cantidad Boletos: <%= ins.getCant() %>
            				</li>
            				<li class="collection-item">            				
            					Costo: <%= ins.getCosto() %>
            				</li>
            				<li class="collection-item">            				
            					fecha: <%= ins.getFecha().toString() %>
            				</li>
            			</ul>
            		</div>
                </li>
              <%
                  }
                }
              }
              %>
            </ul>
        <%
          } 
        } 
        %>
          
        <% } %>
        

        </div>
      </section>
    </div>
  </div>

  <%@ include file="./utils/footer.jsp" %>

  <!-- MATERIALIZE JS -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
  <!-- MATERIALIZE LOCAL JS -->
  <script src="src/js/materialize.js"></script>


  <script>
    function subirDatos() {
      event.preventDefault();

      var formData      = new FormData();
      var fileInput     = document.getElementById("imageInput");
      var nickname      = document.getElementById("nickname");
      var nombre        = document.getElementById("nombre");
      var apellido      = document.getElementById("apellido");
      var email         = document.getElementById("email");
      var nacimiento    = document.getElementById("nacimiento");
      var descripcion   = document.getElementById("descripcion");
      var url           = document.getElementById("url");
      var nacionalidad  = document.getElementById("nacionalidad");
      // var imagen        = document.getElementById("imagen");
      // var descriptionInput = document.getElementById("description");

      formData.append("file", fileInput.files[0]);
      // formData.append("file", imageInput.files[0]);
      formData.append("nickname", nickname.value);
      formData.append("nombre", nombre.value);
      formData.append("apellido", apellido.value);
      formData.append("email", email.value);
      formData.append("nacimiento", nacimiento.value);

      if (descripcion != null) {
        formData.append("descripcion", descripcion.value);
      }
      if (nacionalidad != null) {
        formData.append("nacionalidad", nacionalidad.value);
      }
      if (url != null) {
        formData.append("url", url.value);
      }

      // var descripcionValue  = descripcion.value || '';
      // var nacionalidadValue = nacionalidad.value || '';
      // var urlValue          = url.value || '';

      // formData.append("descripcion", descripcionValue);
      // formData.append("url", urlValue);
      // formData.append("nacionalidad", nacionalidadValue);
      // formData.append("description", descriptionInput.value);

      document.getElementById("uploadContainer").style.display = "block";

      var xhr = new XMLHttpRequest();
      xhr.open("POST", "./miCuenta", true);

      // Evento que se activa cuando la solicitud se completa
      
      xhr.onload = function () {
          if (xhr.status === 200) {
              // La carga se completó con éxito, puedes mostrar un mensaje de éxito o realizar otras acciones
              document.getElementById("uploadStatus").innerHTML = "La información se envió con éxito.";
            } else {
              // Hubo un error en la carga, muestra un mensaje de error
              document.getElementById("uploadStatus").innerHTML = "Error al enviar la información.";
          }
      };

      // Evento que se activa durante la carga para mostrar el progreso
      xhr.upload.onprogress = function (e) {
          if (e.lengthComputable) {
              var percentComplete = (e.loaded / e.total) * 100;
              document.getElementById("uploadProgress").style.width = percentComplete + "%";
          }
      };

      // Enviar la solicitud con los datos del formulario y la imagen
      xhr.send(formData);
    }
    
  </script>
</body>

</html>