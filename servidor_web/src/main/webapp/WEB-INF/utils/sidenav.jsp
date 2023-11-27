<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Importamos el datatype dataUsuario -->
<%@ page import="turismouy.svcentral.datatypes.dataUsuario" %>

<% dataUsuario usuario = (dataUsuario) session.getAttribute("dataUsuario"); %>
<!-- <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script> -->
    <!-- SIDENAV -->
    <ul id="slide-out" class="sidenav home_side sidenav-fixed">
        <li>
            <div class="user-view">
                <div class="background">
                    <img src="src/img/wavesidebg.svg" />
                </div>
                <a href="./miCuenta"><img class="circle" src="src/img/avatar1.png" /></a>
                <a href="./miCuenta"><span class="white-text name">
                    <%= (usuario != null && usuario.getNombre() != null) ? usuario.getNombre() : "Visitante" %>
                </span></a>
                <a href="./miCuenta"><span class="white-text email">
                    <%= (usuario != null && usuario.getEmail() != null) ? usuario.getEmail() : "" %>
                </span></a>
            </div>
        </li>
        
        <li>
            <div class="row">
                <div class="col s12 pl4 pr4">
                    <div class="row">
                        <div class="input-field col s12">
                            <i class="material-icons prefix">search</i>
                            <input type="text" id="autocomplete-input" class="autocomplete">
                            <label for="autocomplete-input">Buscar</label>
                        </div>
                    </div>
                </div>
          </div>
        </li>
        <li><a href="./home">Inicio</a></li>
        <li><a href="./homeSalidas">Salidas</a></li>
        <li><a href="./homeActividades">Actividades</a></li>
        <li><a href="./homePaquete">Paquetes</a></li>
        <li><a href="./homeUsuarios">Usuarios</a></li>

        <%
        if (usuario != null) {
            if (usuario.getisProveedor() == true) { %>
                <div class="divider"></div>
                <li><a href="./misActividades">Mis Actividades</a></li>
                <li><a href="./misSalidas">Mis Salidas</a></li>
            <% } else { %>
                <div class="divider"></div>
                <li><a href="./misSalidas">Mis Salidas</a></li>
                <li><a href="./misActividades">Mis Actividades</a></li>
                <li><a href="./misPaquetes">Mis Paquetes</a></li>
            <% }
        }
        %>

        <%
            if (usuario != null) { %>
                <li>
                    <div class="divider"></div>
                </li>
                <li><a class="subheader">Cuenta</a></li>
                <li>
                    <a href="./miCuenta" class="waves-effect"><i class="material-icons">person</i>Mi Cuenta</a>
                </li>
                <li>
                    <a class="waves-effect" href="./cerrarSesion"><i class="material-icons">exit_to_app</i>Cerrar Sesion</a>
                </li>
        <%  } else { %>
            <li>
                <div class="divider"></div>
            </li>
            <li><a class="subheader">Cuenta</a></li>
            <li>
                <a href="./login" class="waves-effect"><i class="material-icons">account_circle</i>Iniciar Sesión</a>
            </li>
            <li>
                <a href="./register" class="waves-effect"><i class="material-icons">input</i>Regístrate</a>
            </li>
        <%  } %>

        <script>
            $(document).ready(function(){
                $.ajax({
                    url: "autoCompleteServ",
                    dataType: "json",
                    success: function(data) {
                        $('input.autocomplete').autocomplete({
                            data: obtenerDatosAutocomplete(data),
                        });
                    }
                });
        
                function obtenerDatosAutocomplete(data) {
                    var autocompleteData = {};
                    for (var i = 0; i < data.length; i++) {
                        autocompleteData[data[i]] = null;
                    }
                    return autocompleteData;
                }
            });
        </script>
    </ul>