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
								                    <% List<dataCategoria> categorias = (List<dataCategoria>) request.getAttribute("categorias");
								                        int value = 1;
								                        if (categorias != null) {
								                            if (!categorias.isEmpty()) {
								                                for (dataCategoria categoria : categorias) {
								                    %>
								                                    <option value="<%= value %>"><%= categoria.getNombre() %></option>
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
								                <select>
								                    <option value="" disabled selected></option>
								                    <% List<dataDepartamento> departamentos = (List<dataDepartamento>) request.getAttribute("departamentos");
								                        int valuedpto = 1;
								                        if (categorias != null) {
								                            if (!departamentos.isEmpty()) {
								                                for (dataDepartamento dpto : departamentos) {
								                    %>
								                                    <option valuedpto="<%= valuedpto %>"><%= dpto.getNombre() %></option>
								                    <%
								                    				valuedpto++;
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
                                                                    href='#<%= dataAct.getNombre() %>''>Consultar</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <!-- MODALS -->
                                            <div id='<%= dataAct.getNombre() %>'' class="modal">
                                                <div class="modal-content">
                                                    <h4>Consulta de Actividad</h4>
                                                    <form>
                                                        <div class="input-field">
                                                            <input id="nombre" type="text" class="validate" readonly value='<%= dataAct.getNombre() %>' />
                                                            <label for="nombre" class="active">Nombre</label>
                                                        </div>
                                                        <div class="input-field">
                                                            <input id="cant" type="number" class="validate" readonly value='<%= dataAct.getDuracion() %>' />
                                                            <label for="cant" class="active">Duracion (Horas)</label>
                                                        </div>
                                                        <div class="input-field">
                                                            <input id="costo" type="date" class="validate" disabled value='<%= dataAct.getCostoUni() %>' />
                                                            <label for="costo" class="active">Costo Unitario</label>
                                                        </div>
                                                        <div class="input-field">
                                                            <input id="lugar" type="text" class="validate" readonly value='<%= dataAct.getCiudad() %>' />
                                                            <label for="lugar" class="active">Ciudad</label>
                                                        </div>
                                                        <div class="input-field">
                                                            <input id="dpto" type="text" class="validate" readonly value='<%= dataAct.getDepartamento().getNombre() %>' />
                                                            <label for="dpto" class="active">Departamento</label>
                                                        </div>
                                                        <div class="input-field">
                                                            <input id="dateAlta" type="date" class="validate" disabled value='<%= dataAct.getFechaCrea() %>' />
                                                            <label for="dateAlta" class="active">Fecha de alta</label>
                                                        </div>
                                                        <div class="divider"></div>
                                                        <ul class="collection with-header">
                                                            <li class="collection-header">
                                                                <h4>Categorias</h4>
                                                            </li>
                                                            <li class="collection-item">Categoria1</li>
                                                            <li class="collection-item">Categoria2</li>
                                                            <li class="collection-item">Categoria3</li>
                                                            <li class="collection-item">Categoria4</li>
                                                        </ul>
                                                    </form>
                                                </div>
                                                <div class="modal-footer">
                                                    <a href="#!" class="modal-close waves-effect waves-green btn-flat">Cerrar</a>
                                                </div>
                                            </div>

                                            <script>
                                                document.addEventListener('DOMContentLoaded', function() {
                                                var elems = document.querySelectorAll('#<%= dataAct.getNombre() %>');
                                                var instances = M.Modal.init(elems);
                                                });
                                            </script>


                                            <% }
                                        }else { %>
                                            <h1>No hay actividades</h1>
                                        <% } %>


                                </div>
                            </section>
                        </div>
                    </div>
                    


                    <%@ include file="./utils/footer.jsp" %>


                        <!-- MATERIALIZE JS -->
                        <script
                            src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
                        <!-- MATERIALIZE LOCAL JS -->
                        <script src="src/js/materialize.js"></script>
            </body>

            </html>