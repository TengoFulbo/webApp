document.addEventListener("DOMContentLoaded", function () {
  var elems = document.querySelectorAll(".sidenav");
  var instances = M.Sidenav.init(elems);
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