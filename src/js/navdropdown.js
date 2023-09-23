document.addEventListener('DOMContentLoaded', function() {
    const toggleBtn = document.querySelector('.navbar__toggle');
    const navList = document.querySelector('.navbar__list');
    const nav = document.querySelector('.navbar');

    toggleBtn.addEventListener('click', function() {
        navList.classList.toggle('show');
        nav.classList.toggle('showNav');
        
        setTimeout(() => {
            nav.classList.toggle('show');
        }, 10);
    });
});