<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
                                    <option value="1">Artigas</option>
                                    <option value="2">Canelones</option>
                                    <option value="3">Cerro Largo</option>
                                    <option value="4">Colonia</option>
                                    <option value="5">Durazno</option>
                                    <option value="6">Flores</option>
                                    <option value="7">Florida</option>
                                    <option value="8">Lavalleja</option>
                                    <option value="9">Maldonado</option>
                                    <option value="10">Montevideo</option>
                                    <option value="11">Paysandú</option>
                                    <option value="12">Río Negro</option>
                                    <option value="13">Rivera</option>
                                    <option value="14">Rocha</option>
                                    <option value="15">Salto</option>
                                    <option value="16">San José</option>
                                    <option value="17">Soriano</option>
                                    <option value="18">Tacuarembó</option>
                                    <option value="19">Treinta y Tres</option>
                                </select>
                                <label>Departamentos</label>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="page__wrap">
                    <div class="row">
                        <div class="col s12 m6 page__card">
                            <div class="card">
                                <div class="card-image">
                                    <img src="src/img/blurry-gradient1.svg" class="page__card--img" />
                                    <span class="card-title page__card--title">Actividad</span>
                                </div>
                                <div class="card-content page__card--contenido">
                                    <p>
                                        Lorem ipsum, dolor sit amet consectetur adipisicing elit.
                                        Reprehenderit nisi impedit eveniet inventore nemo,
                                        laboriosam ex ut quas ad totam? Expedita quidem animi iste
                                        possimus ullam dolores temporibus natus nam!
                                    </p>
                                    <div class="page__card--btns">
                                        <a class="waves-effect waves-light modal-trigger btn page__card--btn"
                                            href="#modalConsulta">Consultar</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col s12 m6 page__card">
                            <div class="card">
                                <div class="card-image">
                                    <img src="src/img/blurry-gradient1.svg" class="page__card--img" />
                                    <span class="card-title page__card--title">Actividad</span>
                                </div>
                                <div class="card-content page__card--contenido">
                                    <p>
                                        Lorem ipsum dolor sit amet consectetur adipisicing elit.
                                        Dolorem, delectus! Explicabo dolorum facere hic tenetur!
                                        Fugit quidem porro est illum debitis unde consectetur ut
                                        amet, nulla consequuntur, repudiandae magnam nisi!
                                    </p>
                                    <div class="page__card--btns">
                                        <a class="waves-effect waves-light modal-trigger btn page__card--btn"
                                            href="#modalConsulta">Consultar</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col s12 m6 page__card">
                            <div class="card">
                                <div class="card-image">
                                    <img src="src/img/blurry-gradient1.svg" class="page__card--img" />
                                    <span class="card-title page__card--title">Actividad</span>
                                </div>
                                <div class="card-content page__card--contenido">
                                    <p>
                                        Lorem, ipsum dolor sit amet consectetur adipisicing elit.
                                        Atque officiis vero dolor commodi fugiat, quaerat optio
                                        mollitia omnis aliquid nemo est tenetur nesciunt libero
                                        sunt sit dolore iusto! Reprehenderit, consectetur.
                                    </p>
                                    <div class="page__card--btns">
                                        <a class="waves-effect waves-light modal-trigger btn page__card--btn"
                                            href="#modalConsulta">Consultar</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col s12 m6 page__card">
                            <div class="card">
                                <div class="card-image">
                                    <img src="src/img/blurry-gradient1.svg" class="page__card--img" />
                                    <span class="card-title page__card--title">Actividad</span>
                                </div>
                                <div class="card-content page__card--contenido">
                                    <p>
                                        Lorem, ipsum dolor sit amet consectetur adipisicing elit.
                                        Doloribus soluta est repellat iusto, magni dolor
                                        necessitatibus voluptates vitae, voluptatibus dolores
                                        minima quod dolorem. Sit doloribus vitae laboriosam
                                        voluptatum. Maiores, unde.
                                    </p>
                                    <div class="page__card--btns">
                                        <a class="waves-effect waves-light modal-trigger btn page__card--btn"
                                            href="#modalConsulta">Consultar</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
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


    <%@ include file="./utils/footer.jsp" %>


    <!-- MATERIALIZE JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <!-- MATERIALIZE LOCAL JS -->
    <script src="src/js/materialize.js"></script>
</body>

</html>