import { getSession } from './utils/auth';

const checkAccess = () => {
    const userData = JSON.parse(localStorage.getItem('userData') || 'null');
    const actualPath = window.location.pathname;

  // 1. Si no hay nadie logueado y no está en el login, sale.
    if (!userData && !actualPath.includes('login.html') && !actualPath.includes('registro.html')) {
    window.location.href = '/src/pages/auth/login/login.html';
    return;
}  

    //Si intenta entrar a admin pero no tiene el rol
    if (actualPath.includes('/admin/') && userData?.rol !== 'admin') {
    alert("¡No tenés permiso para estar acá!");
    window.location.href = '/index.html';  
}
};

// Ejecutamos la validación apenas carga el script
checkAccess();