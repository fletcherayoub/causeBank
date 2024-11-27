import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

// Vite configuration
export default defineConfig({
  plugins: [react()],
  esbuild: {
    loader: "jsx", // Treat .js files as containing JSX
  },
});
