document.addEventListener('DOMContentLoaded', function() {
    const toggleBtn = document.querySelector('.navbar__toggle');
    const navList = document.querySelector('.navbar__list');
    const nav = document.querySelector('.navbar');

    // Agregar un evento de clic al botón de menú
    toggleBtn.addEventListener('click', function() {
        navList.classList.toggle('show');
        nav.classList.toggle('showNav');

        // Añadimos una clase después de un pequeño retraso para que la animación tenga tiempo para activarse
        setTimeout(() => {
            nav.classList.toggle('show');
        }, 10);
    });
});