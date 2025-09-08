document.addEventListener("DOMContentLoaded", function () {

    var sidebarToggle = document.querySelector('.navbar-toggler');
    var sidebar = document.getElementById('sidebar');
    var content = document.getElementById('content');

    if (sidebarToggle) {
        sidebarToggle.addEventListener('click', function () {

            if (sidebar) sidebar.classList.toggle('active');
            if (content) content.classList.toggle('active');
        });
    }
});
