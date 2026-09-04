const checkAccess = () => {
    const userData = JSON.parse(localStorage.getItem('userData') || 'null');
    const actualPath = window.location.pathname;

    //Rutas de acceso publico
    const isAuthPage = actualPath.includes('login.html') || actualPath.includes('registro.html');

    //Validacion si no esta logueado y quiere entrar a un pagin privada
    if (!userData && !isAuthPage && actualPath !== '/' && !actualPath.includes('index.html')){
        window.location.href = '/src/pages/auth/login/login.html';
        return;
    }

    //Validacion si esta logueado e intentar ir al login o registro, vuelve al su home
    if (userData && isAuthPage) {
        if (userData.rol === 'admin') {
            window.location.href = '/src/pages/admin/home/home.html';
        } else {
            window.location.href = '/src/pages/client/home/home.html';
        }
        return;
    }

    //Proteccion para ADMIN
    if (actualPath.includes('/admin/') && userData?.rol !== 'admin') {
        alert("Acceso denegado: Se requieren permisos de administrador.");
        window.location.href = '/src/pages/client/home/home.html';
        return;
    }

    //Proteccion para cliente
    if (actualPath.includes('/client/') && userData?.rol !== 'client') {
        window.location.href = '/src/pages/admin/home/home.html';
        return;
    }
}