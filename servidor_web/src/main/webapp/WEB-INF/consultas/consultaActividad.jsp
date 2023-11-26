<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="turismouy.svcentral.middlewares.DataActividad" %>
<% DataActividad actividad = (DataActividad) request.getAttribute("actividad"); %>

<%@ include file="../utils/head.jsp" %>
<head>
    <link rel="stylesheet" href="./src/css/homeMulti.css" />
</head>

<body>
    <%@ include file="../utils/navbar.jsp" %>
  
    <%@ include file="../utils/sidenav.jsp" %>

    <div class="container center-align">
        <div class="container center-align">
            <div class="row center-align">
                <div class="row">
                    <h4>Consulta de Actividad</h4>
                    <% String videoUrl = ""; %> 
                    <% if (actividad != null) {
                        if (actividad.getUrl() != null) {
                            if (actividad.getUrl() != "") {
                                videoUrl = actividad.getUrl();
                            }
                        }
                    }%>

                    <% if(videoUrl == "") { videoUrl = "https://www.youtube.com/watch?v=uZZMZ4PyOfw&ab_channel=EngagementHub"; } %>
                    <% String embedURL = "https://www.youtube.com/embed/" + obtenerIDdeVideo(videoUrl) + "?autoplay=1"; %>
                    <%-- Inserta el iframe con la URL del reproductor de YouTube --%>
                    <iframe width="100%" height="315" src="<%= embedURL %>" frameborder="0" allowfullscreen></iframe>
                    
                    <%-- Función para extraer el ID del video desde la URL completa --%>
                    <%!
                        public String obtenerIDdeVideo(String url) {
                          String videoID = "";
                          if (url != null && url.contains("youtube.com/watch?v=")) {
                              int index = url.indexOf("youtube.com/watch?v=");
                              videoID = url.substring(index + 20);

                              // Elimina el parámetro "ab_channel" si está presente
                              int abChannelIndex = videoID.indexOf("&ab_channel=");
                              if (abChannelIndex != -1) {
                                  videoID = videoID.substring(0, abChannelIndex);
                              }
                          }
                          return videoID;
                      }
                    %>
                    <form>
                        <div class="input-field">
                            <input id="modalNombre" type="text" class="validate" readonly value="<%= (actividad != null) ? actividad.getNombre() : "" %>" />
                            <label for="nombre" class="active">Nombre</label>
                        </div>
                        <div class="input-field">
                            <input id="modalDescripcion" type="text" class="validate" readonly value="<%= (actividad != null) ? actividad.getDesc() : "" %>" />
                            <label for="desc" class="active">Descripción</label>
                        </div>
                        <div class="input-field">
                            <input id="modalCiudad" type="text" class="validate" readonly value="<%= (actividad != null) ? actividad.getCiudad() : "" %>" />
                            <label for="ciudad" class="active">Ciudad</label>
                        </div>
                        <div class="input-field">
                            <input id="modalCosto" type="text" class="validate" readonly value="<%= (actividad != null) ? actividad.getCostoUni() : "" %>" />
                            <label for="costo" class="active">Costo Unitario</label>
                        </div>
                        <div class="input-field">
                            <input id="modalDuracion" type="text" class="validate" readonly value="<%= (actividad != null) ? actividad.getDuracion() : "" %>" />
                            <label for="duracion" class="active">Duración</label>
                        </div>
                        <div class="input-field">
                            <input id="modalFecha" type="date" class="validate" value="<%= (actividad != null) ? actividad.getFechaCrea() : "" %>" />
                            <label for="fecha" class="active">Fecha</label>
                        </div>
                        <div class="divider"></div>

                        <h5>Categorias</h5>
                        <ul class="collection" id="listaCategorias"></ul>

                        <div class="divider"></div>

                        <h5>Salidas</h5>
                        <ul class="collection" id="listaSalidas"></ul>
                    </form>
                </div>
            </div>
        </div>
    </div>
        

    <%@ include file="../utils/footer.jsp" %>

    <!-- MATERIALIZE JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <!-- MATERIALIZE LOCAL JS -->
    <script src="src/js/materialize.js"></script>
</body>

</html>