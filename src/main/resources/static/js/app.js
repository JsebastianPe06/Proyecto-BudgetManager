document.addEventListener('DOMContentLoaded', () => {
    
    // --- 1. Marcar el menú activo basado en la URL ---
    const currentPath = window.location.pathname;
    const navItems = document.querySelectorAll('.nav-item');
    
    navItems.forEach(item => {
        const path = item.getAttribute('data-path');
        if (path && currentPath.startsWith(path)) {
            // Evitar que /admin marque también /admin/parameters
            if (path === '/admin' && currentPath !== '/admin') return;
            item.classList.add('active');
        }
    });

    // --- 2. Sidebar Toggle (Móvil) ---
    const menuToggle = document.getElementById('menuToggle');
    const sidebar = document.getElementById('sidebar');
    const backdrop = document.getElementById('sidebarBackdrop');

    if (menuToggle && sidebar && backdrop) {
        menuToggle.addEventListener('click', () => {
            sidebar.classList.add('open');
            backdrop.classList.add('open');
        });
        backdrop.addEventListener('click', () => {
            sidebar.classList.remove('open');
            backdrop.classList.remove('open');
        });
    }

    const alerts = document.querySelectorAll('.alert-dismissible');
    alerts.forEach(alert => {
        setTimeout(() => {
            alert.style.opacity = '0';
            alert.style.transition = 'opacity 0.5s';
            setTimeout(() => alert.remove(), 500);
        }, 5000);
    });
});