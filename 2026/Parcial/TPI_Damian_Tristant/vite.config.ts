import { defineConfig } from "vite";
import { resolve } from "path";
import { homedir } from "os";

export default defineConfig({
  build: {
    rollupOptions: {
      input: {
        //d:aplicaion/dist/
        home: resolve(__dirname, "src/pages/home/home.html"),
        login: resolve(__dirname, "src/pages/auth/login/login.html"),
        registro: resolve(__dirname, "src/pages/auth/registro/registro.html"),
        adminHome: resolve(__dirname, "src/pages/admin/home/home.html"),
        clientHome: resolve(__dirname, "src/pages/client/home/home.html"),
        cart: resolve(__dirname, "src/pages/client/cart/cart.html"),
      },
    },
  },
  base: "./",
});
