<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.List" %>
    <%@ page import="turismouy.svcentral.datatypes.dataActividad" %>
    <%@ page import="turismouy.svcentral.datatypes.dataDepartamento" %>
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
                            <div class="mainWrap__pagination">
                                <ul class="pagination">
                                    <li class="waves-effect"><a href="./homeSalidas.html">Salidas</a></li>
                                    <li class="active"><a href="./homeActividad.html">Actividades</a></li>
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
                                    <h1 class="page__title">Actividades</h1>
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
                                                <select>
                                                    <option value="" disabled selected></option>
                                                    <option value="1">Escalada</option>
                                                    <option value="2">Turismo</option>
                                                    <option value="3">Caminata</option>
                                                </select>
                                                <label>Categorias</label>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="input-field col s12">
                                                <select>
                                                    <option value="" disabled selected></option>
                                                    <% List<dataCategoria> categorias = (List<dataCategoria>) request.getAttribute("categorias");
                                                    <%
                                                    int value = 1;
                                                    if (categorias != null) {
                                                        if (!categorias.isEmpty()) {
                                                            for (dataCategoria categoria : categorias) {
                                                    %>
                                                                <option value="<%= value %>"><%= categoria.getNombre() %></option>
                                                    <%
                                                                value+1;
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
                                <div class="page__wrap">
                                    <% List<dataActividad> ListaActividades = (List<dataActividad>) request.getAttribute("actividades");
                                            if (ListaActividades != null) {
                                            for (dataActividad dataAct : ListaActividades) {
                                            %>
                                            <div class="row">
                                                <div class="col s12 m6 page__card">
                                                    <div class="card">
                                                        <div class="card-image">
                                                            <img src="src/img/blurry-gradient1.svg"
                                                                class="page__card--img" />
                                                            <span class="card-title page__card--title"><%= dataAct.getNombre() %></span>
                                                        </div>
                                                        <div class="card-content page__card--contenido">
                                                            <p>
                                                                <%= dataAct.getDesc() %>
                                                            </p>
                                                            <div class="page__card--btns">
                                                                <a class="waves-effect waves-light modal-trigger btn page__card--btn"
                                                                    href="#modalConsulta">Consultar</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <% }
                                        }else { %>
                                            <h1>No hay actividades</h1>
                                        <% } %>


                                </div>
                            </section>
                        </div>
                    </div>
                    <!-- MODALS -->

                    <!-- Modal Structure -->
                    <div id="consultaModal" class="modal">
                      <div class="modal-content">
                        <h4>Consulta de Actividad</h4> 
                        <form>
                            <div class="input-field">
                                <input id="modalNombre" type="text" class="validate" readonly value=" " />
                                <label for="nombre" class="active">Nombre</label>
                            </div>
                            <div class="input-field">
                                <input id="modalDescripcion" type="text" class="validate" readonly value=" " />
                                <label for="desc" class="active">Descripción</label>
                            </div>
                            <div class="input-field">
                                <input id="modalCiudad" type="text" class="validate" readonly value=" " />
                                <label for="ciudad" class="active">Ciudad</label>
                            </div>
                            <div class="input-field">
                                <input id="modalCosto" type="text" class="validate" readonly value=" " />
                                <label for="costo" class="active">Costo Unitario</label>
                            </div>
                            <div class="input-field">
                                <input id="modalDuracion" type="text" class="validate" readonly value=" " />
                                <label for="duracion" class="active">Duración</label>
                            </div>
                            <div class="input-field">
                                <input id="modalFecha" type="date" class="validate" readonly value="" />
                                <label for="fecha" class="active">Fecha</label>
                            </div>
                            <div class="divider"></div>
                            <h4>Actividades</h4>
                            <ul class="collection">
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
                                actualizarActividades(categoria, departamento);

                                $('#actividades').on('click', '.abrir-modal-btn', function () {
                                    console.log("Click");
                                });
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
                                // Realiza una solicitud POST al Servlet con el valor seleccionado.
                                $.ajax({
                                    type: "POST",
                                    url: "./homeActividades",
                                    data: { departamento: departamento, categoria: categoria },
                                    dataType: "json",
                                    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
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
                        </script>
            </body>

            </html>