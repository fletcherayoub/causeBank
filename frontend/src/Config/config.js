// src/config/config.js

const config = {
  env: import.meta.env.NODE_ENV || "development",
  get isProduction() {
    return this.env === "production";
  },
  get isDevelopment() {
    return this.env === "development";
  },
  get apiUrl() {
    return this.isProduction
      ? import.meta.env.VITE_APP_API_PROD_URL
      : import.meta.env.VITE_APP_API_DEV_URL;
  },
  get frontendUrl() {
    return this.isProduction
      ? import.meta.env.VITE_APP_PROD_URL
      : import.meta.env.VITE_APP_DEV_URL;
  },
};

export default config;
