<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- <!DOCTYPE html> -->
<!-- <html lang="es"> -->

<!-- <head> -->
<!-- <meta charset="UTF-8" /> -->
<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0" /> -->
<!-- <title>TursistaUY</title> -->

<!-- LINKS DE CSS -->
<!-- <link rel="stylesheet" href="./src/css/homeMulti.css" /> -->

<!-- GOOGLE FONTS -->
<!-- <link rel="preconnect" href="https://fonts.googleapis.com" /> -->
<!-- <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin /> -->
<!-- <link href="https://fonts.googleapis.com/css2?family=Dancing+Script&family=Hind&family=Montserrat&display=swap" -->
<!-- rel="stylesheet" /> -->

<!-- MATERIALIZE -->
<!-- <link rel="stylesheet" href="src/css/materialize.css" /> -->
<!-- <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" /> -->
<!-- </head> -->

<%@ include file="./utils/head.jsp" %>

<head>
<link rel="stylesheet" href="./src/css/homeMulti.css" />
</head>

<%@ include file="./utils/navbar.jsp" %>

<%@ include file="./utils/sidenav.jsp" %>

<!-- MAIN -->
<div class="main">
<div class="mainWrap">
	<div class="mainWrap__pagination">
	<ul class="pagination">
		<li class="waves-effect"><a href="./homeSalidas">Salidas</a></li>
		<li class="waves-effect"><a href="./homeActividades">Actividades</a></li>
		<li class="active"><a href="./homePaquetes">Paquetes</a></li>
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
		<h1 class="page__title">Paquetes</h1>
		<p class="page__p">
		Lorem ipsum dolor, sit amet consectetur adipisicing elit. Qui
		incidunt numquam tempora dolorem vel, facere necessitatibus ullam
		ratione laudantium in, eaque assumenda omnis. Cum vitae eaque hic
		minus corrupti tenetur?
		</p>
	</div>
	<!-- <div class="page__filter">
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
	</div> -->
	<div class="page__wrap">
		<div class="row">
		<div class="col s12 m6 page__card">
			<div class="card">
			<div class="card-image">
				<img src="src/img/blurry-gradient1.svg" class="page__card--img" />
				<span class="card-title page__card--title">Paquete</span>
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
				<span class="card-title page__card--title">Paquete</span>
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
				<span class="card-title page__card--title">Paquete</span>
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
				<span class="card-title page__card--title">Paquete</span>
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

	<script>
		document.addEventListener('DOMContentLoaded', function() {
			function actualizarActividades() {
				// Realiza una solicitud POST al Servlet con el valor seleccionado.
				$.ajax({
					type: "POST",
					url: "./homePaquetes",
					// data: {  },
					dataType: "json",
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					error: function () {
						var lista = document.getElementById("paquetes");

						// Limpia la lista antes de agregar nuevos elementos.
						lista.innerHTML = "";

						var row = document.createElement("div");
						row.className = "row";

						var h2 = document.createElement("h2");
						h2.className = "center-align"
						h2.innerHTML = "No se encontraron paquetes ☹️";

						row.appendChild(h2)

						lista.appendChild(row);
						return;
					},
					success: function (paquetes) {
						// Procesa la respuesta del Servlet y llena la lista.
						var lista = document.getElementById("paquetes");
										
						// Limpia la lista antes de agregar nuevos elementos.
						lista.innerHTML = "";

						if (paquetes.length == 0) {
							var row = document.createElement("div");
							row.className = "row";

							var h2 = document.createElement("h2");
							h2.className = "center-align"
							h2.innerHTML = "No se encontraron paquetes ☹️";

							row.appendChild(h2)

							lista.appendChild(row);
							return;
						}

						paquetes.forEach(paquete => {
							var cardPaquete = crearCardPaquete(paquete);
							lista.appendChild(cardPaquete);
						});
					}
				});
			}

			function crearCardPaquete(paquete) {
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
				title.textContent = paquete.nombre;

				var cardContent = document.createElement("div");
				cardContent.className = "card-content page__card--contenido";

				var paragraph = document.createElement("p");
				paragraph.textContent = paquete.desc;

				var btnDiv = document.createElement("div");
				btnDiv.className = "page__card--btns";

				var btn = document.createElement("button");
				btn.className = "btn abrirModalBtn";
				btn.textContent = "Consultar";
				// btn.onclick = function() { consultarActividad(actividad.nombre); };

				// Agrupamos los elementos en la estructura deseada
				cardImage.appendChild(img);
				cardImage.appendChild(title);
				
				cardContent.appendChild(paragraph);
				cardContent.appendChild(btnDiv);
				btnDiv.appendChild(btn);

				card.appendChild(cardImage);
				card.appendChild(cardContent);

				col.appendChild(card);
				row.appendChild(col);

				return row;
			}
		});
	</script>
</body>

</html>