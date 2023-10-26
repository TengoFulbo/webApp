document.addEventListener('DOMContentLoaded', function() {
  var elemsRight = document.querySelectorAll('#mobile-demo');
  var instancesRight = M.Sidenav.init(elemsRight, {
    edge: 'right'
  });

  var elemsLeft = document.querySelectorAll('#slide-out');
  var instancesLeft = M.Sidenav.init(elemsLeft, {
    edge: 'left'
  });
});

document.addEventListener('DOMContentLoaded', function() {
  var elems = document.querySelectorAll('#modalInscribirse');
  var instances = M.Modal.init(elems);
});

document.addEventListener('DOMContentLoaded', function() {
  var elems = document.querySelectorAll('#modalConsulta');
  var instances = M.Modal.init(elems);
});

document.addEventListener('DOMContentLoaded', function() {
  var elems = document.querySelectorAll('#modalNuevaSalida');
  var instances = M.Modal.init(elems);
});

document.addEventListener('DOMContentLoaded', function() {
  var elems = document.querySelectorAll('#modalNuevaActividad');
  var instances = M.Modal.init(elems);
});

document.addEventListener('DOMContentLoaded', function() {
  var elems = document.querySelectorAll('.datepicker');
  var options = {
      format: 'dd mmmm, yyyy', 
      i18n: {
          cancel: 'Cancelar',
          clear: 'Limpiar',
          done: 'Listo',
          months: [
              'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
              'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'
          ],
          monthsShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
          weekdays: [
              'Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'
          ],
          weekdaysShort: ['Dom', 'Lun', 'Mar', 'Mié', 'Jue', 'Vie', 'Sáb'],
          weekdaysAbbrev: ['D', 'L', 'M', 'M', 'J', 'V', 'S']
      }
  };
  var instances = M.Datepicker.init(elems, options);
});
document.addEventListener('DOMContentLoaded', function() {
  var elems = document.querySelectorAll('select');
  var instances = M.FormSelect.init(elems);
});

document.addEventListener('DOMContentLoaded', function() {
  var elems = document.querySelectorAll('.dropdown-trigger');
  var instances = M.Dropdown.init(elems);
});

document.addEventListener("DOMContentLoaded", function () {
  var elems = document.querySelectorAll(".parallax");
  var instances = M.Parallax.init(elems);
});

