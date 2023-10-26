<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.List" %>
    <%@ page import="turismouy.svcentral.datatypes.dataActividad" %>
    <%@ page import="turismouy.svcentral.datatypes.dataDepartamento" %>
    <%@ page import="turismouy.svcentral.datatypes.dataCategoria" %>

    <%@ include file="./utils/head.jsp" %>

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
                    <a href="./index.html">TurismoUY</a>
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
            <div class="main__createBtn">
                <div class="main__createBtn--wrap">
                    <a class="waves-effect waves-light modal-trigger btn main__createBtn--btn"
                        href="#modalNuevaActividad">Crear
                        Actividad</a>
                </div>
            </div>
            <!-- page -->
            <section class="page">
                <div class="page__texto">
                    <h1 class="page__title">Mis Actividades</h1>
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
                                <select id="categorias">
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
                    </ul>
                </div>
                <div class="page__wrap" id="actividades">
                    <!-- Acá se llena con JS. -->
                </div>
            </section>
        </div>
    </div>
    <!-- MODALS -->
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

    <div id="modalNuevaActividad" class="modal">
        <div class="modal-content">
            <h4>Crear Salida</h4>
            <form>
                <div class="input-field col s12">
                    <select>
                        <option value="" disabled selected>No seleccionado</option>
                        <%
                        int value3 = 1;
                        if (departamentos != null) {
                            if (!departamentos.isEmpty()) {
                                for (dataDepartamento departamento : departamentos) {
                        %>
                                    <option value="<%= departamento.getNombre() %>"> <%= value3 %> - <%= departamento.getNombre() %></option>
                        <%
                                    value3++;
                                }
                            }
                        }
                        %>
                    </select>
                    <label>Departamento</label>
                </div>
                <div class="input-field">
                    <input id="nombre" type="text" class="validate" value="">
                    <label for="nombre" class="active">Nombre</label>
                </div>
                <div class="input-field">
                    <input id="dura" type="number" class="validate" value="" placeholder="En Horas">
                    <label for="dura" class="active">Duracion</label>
                </div>
                <div class="input-field">
                    <input id="precio" type="number" class="validate" value="" placeholder="En Pesos Uruguayos">
                    <label for="precio" class="active">Precio</label>
                </div>
                <div class="input-field">
                    <input id="ciudad" type="text" class="validate" value="">
                    <label for="ciudad" class="active">Ciudad</label>
                </div>
                <div class="divider"></div>
                <button class="btn waves-effect waves-light modal__inscribirse--submit" type="submit" name="action" onclick="crearActividad()">
                    Enviar
                    <i class="material-icons right">send</i>
                </button>
            </form>
        </div>
        <div class="modal-footer">
            <a href="#!" class="modal-close btn-flat">Cerrar</a>
        </div>
    </div>

    <%@ include file="./utils/footer.jsp" %>

    <!-- MATERIALIZE JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <!-- MATERIALIZE LOCAL JS -->
    <script src="src/js/materialize.js"></script>

    <script>
        document.addEventListener("DOMContentLoaded", function () {
            var modal = M.Modal.init(document.getElementById("consultaModal"));
        });
        $(document).ready(function () {
            var departamento = document.getElementById("departamentos").value;
            var categoria = document.getElementById("categorias").value;
            actualizarActividades(categoria, departamento);
        });

        $("#categorias").change(function () {
            var departamento = document.getElementById("departamentos").value;
            var categoria = document.getElementById("categorias").value;
            actualizarActividades(categoria, departamento);
        });

        $("#departamentos").change(function () {
            var departamento = document.getElementById("departamentos").value;
            var categoria = document.getElementById("categorias").value;
            actualizarActividades(categoria, departamento);
        });

        function actualizarActividades(categoria, departamento) {
            // console.log("Se ejecuta actualizarActividades");
            // Realiza una solicitud POST al Servlet con el valor seleccionado.
            $.ajax({
                type: "POST",
                url: "./misActividades",
                data: { departamento: departamento, categoria: categoria },
                dataType: "json",
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                error: function () {
                    var lista = document.getElementById("actividades");

                    // Limpia la lista antes de agregar nuevos elementos.
                    lista.innerHTML = "";

                    var row = document.createElement("div");
                    row.className = "row";

                    var h2 = document.createElement("h2");
                    h2.className = "center-align"
                    h2.innerHTML = "No se encontraron actividades ☹️";

                    row.appendChild(h2)

                    lista.appendChild(row);
                    return;
                },
                success: function (actividades) {
                    // Procesa la respuesta del Servlet y llena la lista.
                    var lista = document.getElementById("actividades");
                                        
                    // Limpia la lista antes de agregar nuevos elementos.
                    lista.innerHTML = "";

                    if (actividades.length == 0) {
                        var row = document.createElement("div");
                        row.className = "row";

                        var h2 = document.createElement("h2");
                        h2.className = "center-align"
                        h2.innerHTML = "No se encontraron actividades ☹️";

                        row.appendChild(h2)

                        lista.appendChild(row);
                        return;
                    }

                    actividades.forEach(actividad => {

                        // Creamos los elementos HTML.
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
                        title.textContent = actividad.nombre;

                        var cardContent = document.createElement("div");
                        cardContent.className = "card-content page__card--contenido";

                        var paragraph = document.createElement("p");
                        paragraph.textContent = actividad.desc;

                        var btnDiv = document.createElement("div");
                        btnDiv.className = "page__card--btns";

                        var btn = document.createElement("button");
                        btn.className = "btn abrirModalBtn";
                        // btn.href = "#modalConsulta";
                        // btn.id = "abrirModalBtn"
                        btn.setAttribute("dataActividad", JSON.stringify(actividad));
                        btn.textContent = "Consultar";
                        // <!-- Modal Trigger -->
                            // <button data-target="modal1" class="btn modal-trigger">Modal</button>

                        // Agrupa los elementos en la estructura deseada
                        cardImage.appendChild(img);
                        cardImage.appendChild(title);

                        cardContent.appendChild(paragraph);
                        cardContent.appendChild(btnDiv);
                        btnDiv.appendChild(btn);

                        card.appendChild(cardImage);
                        card.appendChild(cardContent);

                        col.appendChild(card);
                        row.appendChild(col);
                                                                                
                        // Agrega la fila al contenedor de actividades
                        console.log("Llenando..");
                        lista.appendChild(row);
                    });
                }
            });
        }

        function crearActividad() {
            event.preventDefault()
            console.log("holasldlasmdklas");
        }
    </script>
</body>

</html>