import { defineConfig } from 'vite';

export default defineConfig({
  root: './', // El punto de entrada es la raíz donde está tu index.html
  build: {
    outDir: 'dist',
  },
  server: {
    port: 5173
  }
});