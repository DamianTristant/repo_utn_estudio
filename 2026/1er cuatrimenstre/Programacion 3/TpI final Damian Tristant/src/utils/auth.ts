import { Iuser } from '../types/IUser';

export const getSession = (): Iuser | null => {
    const data = localStorage.getItem('userData');
    return data ? JSON.parse(data) : null;
};

export const logout = () => {
    localStorage.removeItem('userData');
    window.location.href = '/pages/auth/login/login.html';
};