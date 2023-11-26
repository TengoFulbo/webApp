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
                    <a class="modal-trigger btn main__createBtn--btn"
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
    <div id="consultaModal" class="modal">
        <div class="modal-content">
          <h4>Consulta de Actividad</h4> 
          <form >
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
              <h4>Categorias</h4>
              <ul class="collection" id="listaCategorias">
                  
              </ul>
              <div class="divider"></div>
              <h4>Salidas</h4>
              <ul class="collection" id="listaSalidas">
                  
              </ul>
              <button class="btn waves-effect waves-light modal__inscribirse--submit" type="submit" name="action" onclick="bajaActividad()">
                Dar por finalizada
                </button>
              <div class="modal-footer">
                <a href="#!" class="modal-close waves-effect waves-green btn-flat">Cerrar</a>
              </div>
          </form>
      </div>
          
      </div>

    <div id="modalNuevaActividad" class="modal">
        <div class="modal-content">
            <h4>Crear Actividad</h4>
            <form>
                <div class="input-field col s12">
                    <select id="departamentosAlta">
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
                    <input id="nombreAlta" type="text" class="validate" value="" placeholder="Nombre">
                    <label for="nombre" class="active">Nombre</label>
                </div>
                <div class="input-field">
                    <input id="descAlta" type="text" class="validate" value="" placeholder="Descripcion">
                    <label for="descripcion" class="active">Descripcion</label>
                </div>
                <div class="input-field">
                    <input id="duraAlta" type="number" class="validate" value="" placeholder="En Horas">
                    <label for="dura" class="active">Duracion</label>
                </div>
                <div class="input-field">
                    <input id="precioAlta" type="number" class="validate" value="" placeholder="En Pesos Uruguayos">
                    <label for="precio" class="active">Precio</label>
                </div>
                <div class="input-field">
                    <input id="ciudadAlta" type="text" class="validate" value="">
                    <label for="ciudad" class="active">Ciudad</label>
                </div>
                <div class="input-field">
                    <input id="urlAlta" type="text" class="validate" value="">
                    <label for="url" class="active">URL del Video</label>
                </div>
                <div class="input-field col s12" id="contenedorCheckbox">
                    <!-- Las casillas de verificación de Materialize se agregarán aquí de forma dinámica -->
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
            var modal = M.Modal.init(document.getElementById("modalNuevaActividad"));
        });

        document.addEventListener("click", function (event) {
            if (event.target.classList.contains("abrirModalBtn")) {
                // Obtiene los datos de la actividad
                var objeto = event.target.getAttribute("dataActividad");
                var dataActividad = JSON.parse(objeto)
                // var actividadDescripcion = event.target.getAttribute("data-desc");
            
                // Llena el modal con los datos de la actividad
                document.getElementById("modalNombre").value = dataActividad.nombre;
                // console.log(dataActividad);
                document.getElementById("modalFecha").value = dataActividad.fechaCrea;
                document.getElementById("modalDescripcion").value = dataActividad.desc;
                document.getElementById("modalCiudad").value = dataActividad.departamento.nombre;
                document.getElementById("modalCosto").value = dataActividad.costoUni;
                document.getElementById("modalDuracion").value = dataActividad.duracion;
                // document.getElementById("")
                // document.getElementById("modalActividadDescripcion").textContent = actividadDescripcion;

                var categoriasList = dataActividad.dtCategorias;
                var salidasList = dataActividad.DtSalidas;
                var contCat = document.getElementById("listaCategorias");
                var contSal = document.getElementById("listaSalidas");

                contCat.innerHTML = "";
                contSal.innerHTML = "";

                categoriasList.forEach(categoria => {
                    let cat = document.createElement("li");
                    cat.className = "collection-item";
                    cat.textContent = categoria;
                    contCat.appendChild(cat);
                });

                salidasList.forEach(salida => {
                    let sal = document.createElement("li");
                    sal.className = "collection-item";
                    sal.textContent = salida.nombre + " - " + salida.fechaSalida;
                    contSal.appendChild(sal);
                });
            
                // Abre el modal
                var modal = M.Modal.init(document.getElementById("consultaModal"));
                modal.open();
            }
        });

        $(document).ready(function () {
            var departamento = document.getElementById("departamentos").value;
            var categoria = document.getElementById("categorias").value;
            actualizarActividades(categoria, departamento);

            function crearCasillasDeVerificacion() {
                var categorias;

                $.ajax({
                    url: "./crearActividad", // Reemplaza con la URL de tu servlet
                    type: "GET",
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                    success: function (data) {
                        // Accede a las actividades y categorías por separado
                        var actividades = data.actividades;
                        categorias = data.categorias;

                        // Realiza acciones con las listas de actividades y categorías
                        console.log("Actividades:", actividades);
                        console.log("Categorias:", categorias);

                        var contenedor = $("#contenedorCheckbox");
                        contenedor.empty(); // Limpia el contenedor

                        console.log(categorias);

                        $.each(categorias, function (index, categoria) {
                            var checkboxId = "checkbox" + index;
                            var checkbox = $('<p class="p-checkbox">');
                            var label = $('<label>').attr('for', checkboxId);
                            var input = $('<input>')
                                .attr("type", "checkbox")
                                .attr("id", checkboxId)
                                .attr("name", "categorias")
                                .attr("class", "filled-in")
                                .val(categoria.nombre);
                            var span = $('<span>').text(categoria.nombre);

                            label.append(input);
                            label.append(span);
                            checkbox.append(label);

                            contenedor.append(checkbox);
                        });
                    },
                    error: function (error) {
                        console.error("Error en la solicitud AJAX:", error);
                    },
                });
            }

            // Inicializa Materialize CSS para las casillas de verificación
            M.AutoInit();

            crearCasillasDeVerificacion(categorias);
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

            // Obtener los valores de los campos del formulario
            var departamento = document.getElementById('departamentosAlta').value;
            var nombre = document.getElementById('nombreAlta').value;
            var desc = document.getElementById('descAlta').value;
            var duracion = document.getElementById('duraAlta').value;
            var precio = document.getElementById('precioAlta').value;
            var ciudad = document.getElementById('ciudadAlta').value;
            var urlVid = document.getElementById('urlAlta').value;
            var categorias = $("input[name='categorias']:checked")
                .map(function () {
                    return $(this).val();
                }).get();
            console.log(categorias);

            console.log("================ Video =================");
            console.log(urlVid);
            console.log("========================================");

            // Realizar comprobaciones de validación
            if (
                departamento === "" ||
                nombre === "" ||
                duracion === "" ||
                precio === "" ||
                ciudad === ""
            ) {
                alert('Por favor, complete todos los campos obligatorios antes de enviar.');
                return;
            }

            // Crear un objeto con los datos
            var actividadData = {
                departamento: departamento,
                nombre: nombre,
                desc: desc,
                duracion: duracion,
                precio: precio,
                ciudad: ciudad,
                urlVid: urlVid,
                categorias: categorias
            };

            console.log(actividadData);

            $.ajax({
                url: './crearActividad',
                type: 'POST',
                contentType: 'application/json; charset=UTF-8',
                data: JSON.stringify(actividadData),
                success: function (data, status, xhr) {
                    if (xhr.status === 200) {
                    // La solicitud se completó con éxito, puedes realizar acciones adicionales si es necesario.
                    console.log('Actividad creada con éxito.');
                    alert("La actividad se ha creado correctamente.")
                    }
                },
                error: function (xhr, status, error) {
                    // Manejar errores aquí
                    console.error('Error al crear la actividad: ' + error);
                    alert("Hubo un error al crear la actividad.")
                }
                });
        }

        function bajaActividad() {
            event.preventDefault()

            // Obtener los valores de los campos del formulario
            var nombre = document.getElementById('modalNombre').value;

            console.log(nombre);

            // Crear un objeto con los datos
            var actividadData = {
                nombre: nombre
            };

            console.log(actividadData);

            $.ajax({
                url: './bajaActividad',
                type: 'POST',
                contentType: 'application/json; charset=UTF-8',
                data: JSON.stringify(actividadData),
                success: function (data, status, xhr) {
                    if (xhr.status === 200) {
                    // La solicitud se completó con éxito, puedes realizar acciones adicionales si es necesario.
                    console.log('Actividad finalizada con éxito.');
                    alert("La actividad se ha finalizado correctamente.")
                    }
                },
                error: function (xhr, status, error) {
                    // Manejar errores aquí
                    console.error('Error al finalizar la actividad: ' + error);
                    alert("Hubo un error al finalizar la actividad.")
                }
                });
        }
    </script>
</body>

</html>