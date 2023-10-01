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
  var elems = document.querySelectorAll('.dropdown-trigger');
  var instances = M.Dropdown.init(elems);
});

document.addEventListener("DOMContentLoaded", function () {
  var elems = document.querySelectorAll(".parallax");
  var instances = M.Parallax.init(elems);
});

document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.carousel');
    var options = {
        duration: 400, 
    };
    var instances = M.Carousel.init(elems, options);

    function nextSlide() {
        var carouselInstances = M.Carousel.getInstance(elems[0]);
        carouselInstances.next();
    }
    setInterval(nextSlide, 4000);
});