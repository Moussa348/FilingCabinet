import { STORAGE_KEY } from './constant';
import decode from 'jwt-decode';

export const getId = () =>{
  const token = sessionStorage.getItem(STORAGE_KEY);
  return token != null ?decode<any>(token).subject : null;

};

export const getRole = () => {
  const token = sessionStorage.getItem(STORAGE_KEY);
  return token != null ? decode<any>(token).role : null;
};

export const getEmail = () => {
  const token = sessionStorage.getItem(STORAGE_KEY);
  return token != null ? decode<any>(token).userEmail : null;
};

export const isTokenExpired = () => {
  const token = sessionStorage.getItem(STORAGE_KEY);
  return decode<any>(token).exp * 1000 < new Date().getTime();
};

export const isTokenValid = () => {
  const token = sessionStorage.getItem(STORAGE_KEY);
};
