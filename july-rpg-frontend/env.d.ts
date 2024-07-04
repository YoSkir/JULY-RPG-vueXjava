/// <reference types="vite/client" />

interface ImportMetaEnv {
  readonly VITE_API_URL: string;
  // 其他环境变量的类型声明
}

interface ImportMeta {
  readonly env: ImportMetaEnv;
}