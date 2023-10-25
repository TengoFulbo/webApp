<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="turismouy.svcentral.datatypes.dataActividad" %>
<%@ page import="turismouy.svcentral.datatypes.dataDepartamento" %>
<%@ page import="turismouy.svcentral.datatypes.dataCategoria" %>
<%@ page import="turismouy.svcentral.datatypes.dataSalida" %>
<%@ page import="turismouy.svcentral.interfaces.IActividadController" %>
<%@ page import="turismouy.svcentral.interfaces.ISalidaController" %>
<%@ page import="turismouy.svcentral.interfaces.IDepartamentoController" %>
<%@ page import="turismouy.svcentral.interfaces.ICategoriaController" %>

<%@ include file="./utils/head.jsp" %>

<head>
  <link rel="stylesheet" href="./src/css/homeMulti.css" />
</head>

<body>
  <%@ include file="./utils/navbar.jsp" %>
  <%@ include file="./utils/sidenav.jsp" %>

  <!-- MAIN -->
  <div class="main">
    <div class="mainWrap">
      <div class="mainWrap__pagination">
        <ul class="pagination">
          <li class="active"><a href="./homeSalidas.html">Salidas</a></li>
          <li class="waves-effect"><a href="./homeActividad.html">Actividades</a></li>
          <li class="waves-effect"><a href="./homePaquete.html">Paquetes</a></li>
        </ul>

        <ul id="dropdown1" class="dropdown-content">
          <li><a href="#!">Caminata</a></li>
          <li><a href="#!">Camping</a></li>
          <li><a href="#!">Ruta</a></li>
          <li><a href="#!">Trecking</a></li>
          <li><a href="#!">Historia</a></li>
        </ul>
      </div>

      <!-- page -->
      <section class="page">
        <div class="page__texto">
          <h1 class="page__title">Salidas</h1>
          <p class="page__p">
            Lorem ipsum dolor, sit amet consectetur adipisicing elit. Qui
            incidunt numquam tempora dolorem vel, facere necessitatibus ullam
            ratione laudantium in, eaque assumenda omnis. Cum vitae eaque hic
            minus corrupti tenetur?
          </p>
        </div>
        <div class="page__filter">
          <h3 class="page__filter--title">Filtros:</h3>
          <ul class="pagination page__filter--list">
            <li>
              <div class="input-field col s12">
                <select id="categorias" >
                  <option value="" disabled selected>No seleccionado</option>
                  <% List<dataCategoria> categorias = (List<dataCategoria>) request.getAttribute("categorias"); %>
                  <%
                  int value = 1;
                  if (categorias != null) {
                      if (!categorias.isEmpty()) {
                          for (dataCategoria categoria : categorias) {
                  %>
                              <option value="<%= categoria.getNombre() %>"><%= value %> - <%= categoria.getNombre() %></option>
                  <%
                              value++;
                          }
                      }
                  }
                  %>
                </select>
                <label>Categorias</label>
              </div>
            </li>
            <li>
              <div class="input-field col s12">
                <select id="departamentos">
                  <option value="" disabled selected>No seleccionado</option>
                  <% List<dataDepartamento> departamentos = (List<dataDepartamento>) request.getAttribute("departamentos"); %>
                  <%
                  int value2 = 1;
                  if (departamentos != null) {
                      if (!departamentos.isEmpty()) {
                          for (dataDepartamento departamento : departamentos) {
                  %>
                              <option value="<%= departamento.getNombre() %>"> <%= value2 %> - <%= departamento.getNombre() %></option>
                  <%
                              value2++;
                          }
                      }
                  }
                  %>
                </select>
                <label>Departamentos</label>
              </div>
            </li>

            <li>
              <div class="input-field col s12">
                <select id="actividades">
                  <option value="" disabled selected>No seleccionado</option>
                  <% List<dataActividad> listActividades = (List<dataActividad>) request.getAttribute("actividades"); %>
                  <%
                  int value3 = 1;
                  if (listActividades != null) {
                      if (!listActividades.isEmpty()) {
                          for (dataActividad act : listActividades) {
                  %>
                              <option value="<%= act.getNombre() %>"> <%= value3 %> - <%= act.getNombre() %></option>
                  <%
                              value3++;
                          }
                      }
                  }
                  %>
                </select>
                <label>Actividades</label>
              </div>
            </li>
          </ul>
        </div>

        <div class="page__wrap" id="salidas">
          <!-- JS -->
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
          <input id="nombre" type="text" class="validate" readonly value=" " />
          <label for="nombre" class="active">Nombre</label>
        </div>
        <div class="input-field">
          <input id="cant" type="number" class="validate" readonly value=" " />
          <label for="cant" class="active">Cantidad de lugares</label>
        </div>
        <div class="input-field">
          <input id="dateSalida" type="date" class="validate" disabled value=" " />
          <label for="dateSalida" class="active">Fecha de salida</label>
        </div>
        <div class="input-field">
          <input id="dateAlta" type="date" class="validate" disabled value=" " />
          <label for="dateAlta" class="active">Fecha de alta</label>
        </div>
        <div class="input-field">
          <input id="lugarSalida" type="text" class="validate" readonly value=" " />
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

  <script>
    document.addEventListener("DOMContentLoaded", function () {
        var modal = M.Modal.init(document.getElementById("consultaModal"));
    });

    // Agrega un evento de clic al botón "Consultar" (delegación de eventos)
    document.addEventListener("click", function (event) {
        if (event.target.classList.contains("abrirModalBtn")) {
            // Obtiene los datos de la actividad
            var objeto = event.target.getAttribute("dataActividad");
            var dataActividad = JSON.parse(objeto)
            // var actividadDescripcion = event.target.getAttribute("data-desc");
        
            // Llena el modal con los datos de la actividad
            document.getElementById("modalNombre").value = dataActividad.nombre;
            console.log(dataActividad);
            document.getElementById("modalFecha").value = dataActividad.fechaCrea;
            document.getElementById("modalDescripcion").value = dataActividad.desc;
            document.getElementById("modalCiudad").value = dataActividad.departamento.nombre;
            document.getElementById("modalCosto").value = dataActividad.costoUni;
            document.getElementById("modalDuracion").value = dataActividad.duracion;
            // document.getElementById("")
            // document.getElementById("modalActividadDescripcion").textContent = actividadDescripcion;
        
            // Abre el modal
            var modal = M.Modal.init(document.getElementById("consultaModal"));
            modal.open();
        }
    });

    $(document).ready(function () {
        var departamento = document.getElementById("departamentos").value;
        var categoria = document.getElementById("categorias").value;
        var actividad = document.getElementById("actividades").value;
        actualizarActividades(categoria, departamento, actividad);

        $('#actividades').on('click', '.abrir-modal-btn', function () {
            console.log("Click");
        });
    });

    $("#categorias").change(function () {
        var departamento = document.getElementById("departamentos").value;
        var categoria = document.getElementById("categorias").value;
        var actividad = document.getElementById("actividades").value;
        actualizarActividades(categoria, departamento, actividad);
    });

    $("#departamentos").change(function () {
        var departamento = document.getElementById("departamentos").value;
        var categoria = document.getElementById("categorias").value;
        var actividad = document.getElementById("actividades").value;
        actualizarActividades(categoria, departamento, actividad);
    });

    $("#actividades").change(function () {
        var departamento = document.getElementById("departamentos").value;
        var categoria = document.getElementById("categorias").value;
        var actividad = document.getElementById("actividades").value;
        actualizarActividades(categoria, departamento, actividad);
    });

    function actualizarActividades(categoria, departamento, actividad) {
        // Realiza una solicitud POST al Servlet con el valor seleccionado.
        $.ajax({
            type: "POST",
            url: "./homeSalidas",
            data: { departamento: departamento, categoria: categoria, actividad: actividad},
            dataType: "json",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success: function (salidas) {
                // Procesa la respuesta del Servlet y llena la lista.
                var lista = document.getElementById("salidas");
                
                // Limpia la lista antes de agregar nuevos elementos.
                lista.innerHTML = "";

                if (salidas.length == 0) {
                    var row = document.createElement("div");
                    row.className = "row";

                    var h2 = document.createElement("h2");
                    h2.className = "center-align"
                    h2.innerHTML = "No se encontraron salidas ☹️";

                    row.appendChild(h2)

                    lista.appendChild(row);
                    return;
                }

                salidas.forEach(salida => {
                    // Crea los elementos HTML para cada salida.
                    var row = document.createElement("div");
                    row.className = "row";

                    var col = document.createElement("div");
                    col.className = "col s12 m6 page__card";

                    var card = document.createElement("div");
                    card.className = "card";

                    var cardImage = document.createElement("div");
                    cardImage.className = "card-image";

                    var img = document.createElement("img");
                    img.src = "src/img/blurry-gradient1.svg";
                    img.className = "page__card--img";

                    var title = document.createElement("span");
                    title.className = "card-title page__card--title";
                    title.textContent = salida.nombre;

                    var cardContent = document.createElement("div");
                    cardContent.className = "card-content page__card--contenido";

                    var paragraph = document.createElement("p");
                    paragraph.textContent = salida.lugarSalida;

                    var btnDiv = document.createElement("div");
                    btnDiv.className = "page__card--btns";

                    var btn = document.createElement("button");
                    btn.className = "btn abrirModalBtn";
                    btn.setAttribute("dataSalida", JSON.stringify(salida));
                    btn.textContent = "Consultar";

                    var btnRegistro = document.createElement("button");
                    btnRegistro.className = "btn abrirRegistrar";
                    btnRegistro.setAttribute("dataSalida", JSON.stringify(salida));
                    btnRegistro.textContent = "Resgistrarse";
                    btnRegistro.style.marginLeft = "10px";

                    // Agrupa los elementos en la estructura deseada
                    cardImage.appendChild(img);
                    cardImage.appendChild(title);
                                                                
                    cardContent.appendChild(paragraph);
                    cardContent.appendChild(btnDiv);
                    btnDiv.appendChild(btn);
                    btnDiv.appendChild(btnRegistro);
                                                                
                    card.appendChild(cardImage);
                    card.appendChild(cardContent);
                                                                
                    col.appendChild(card);
                    row.appendChild(col);
                                                                
                    // Agrega la fila al contenedor de salidas
                    lista.appendChild(row);
                });
                
            }
        });
    }   
</script>

  <!-- MATERIALIZE JS -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
  <!-- MATERIALIZE LOCAL JS -->
  <script src="src/js/materialize.js"></script>
</body>

</html>