<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="./utils/head.jsp" %>

<body>
    <!-- NAVBAR -->
    <nav class="navbar">
        <div class="nav-wrapper navbar__wrap">
            <div class="navbar__leftwrap">
                <div class="brand-logo">
                    <img src="src/img/kombi.png" alt="Imagen de una combi" class="navbar__logo--img" />
                    <a href="./">TurismoUY</a>
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
        <div class="main__wrap">
            <div class="main__text">
                <div>
                    <h1 class="main__title">
                        Inicio
                    </h1>
                    <p class="main__p">
                        Lorem, ipsum dolor sit amet consectetur adipisicing elit. Doloribus aliquid praesentium in
                        exercitationem quidem alias nam excepturi repudiandae corrupti dolorum sed porro quas, quod
                        numquam explicabo blanditiis mollitia officia amet.
                    </p>
                </div>
                <img src="./src/img/kombi.png" alt="" class="main__image">
            </div>
            <%
            if (usuario != null) { %>
            <div class="main__content">
                <div class="main__content--container">
                    <h2 class="main__content--title">Salidas Prog.</h2>
                    <p class="main__content--number"><%= request.getAttribute("salidas") %></p>
                </div>
                <div class="main__content--container">
                    <h2 class="main__content--title">Compras</h2>
                    <p class="main__content--number"><%= request.getAttribute("compras") %></p>
                </div>
                <div class="main__content--container">
                    <h2 class="main__content--title">Actividades</h2>
                    <p class="main__content--number"><%= request.getAttribute("actividades") %></p>
                </div>
            </div>
            <%
            } %>
        </div>
    </div>

    <%@ include file="./utils/footer.jsp" %>

    <!-- MATERIALIZE JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <!-- MATERIALIZE LOCAL JS -->
    <script src="src/js/materialize.js"></script>
</body>

</html>