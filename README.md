🛠️ API REST – Spring Boot + Railway + Neon Tech

Este repositorio contiene una API REST desarrollada con Java y Spring Boot, diseñada para gestionar productos y exponer endpoints que pueden ser consumidos desde aplicaciones móviles o web.
El proyecto fue desplegado en la nube utilizando Railway, mientras que la base de datos se aloja en Neon Tech (PostgreSQL serverless).

🚀 Tecnologías utilizadas

Java 17

Spring Boot

Spring Web

Spring Data JPA

PostgreSQL (Neon Tech)

Maven

Railway (hosting de la API)

Docker (opcional para despliegue)

☁️ Despliegue en Railway

La API fue desplegada en Railway debido a que ofrece:

Un proceso de despliegue rápido e intuitivo

Manejo sencillo de variables de entorno

Integración directa con repositorios de GitHub

Logs en tiempo real

Contenedores automáticos sin configuración compleja

Railway permitió llevar la API desde un entorno local a producción con mínimo esfuerzo.

🗂️ Estructura del proyecto
.
├── .mvn/wrapper        # Wrapper de Maven
├── .vscode             # Configuración del editor
├── src                 # Código fuente del proyecto
│   ├── main/java/...   # Controladores, modelos, servicios
│   └── main/resources  # Configuración, application.properties
├── wrapper             # Complementos del wrapper de Maven
├── Dockerfile          # Archivo para construir imagen Docker
├── railway.toml        # Configuración de despliegue en Railway
├── pom.xml             # Dependencias del proyecto
└── mvnw / mvnw.cmd     # Ejecución Maven Wrapper

🌐 Endpoints disponibles

🔗 Reemplaza estos links cuando me envíes los correctos.

Método	Endpoint	Descripción
GET	https://movil2-production.up.railway.app/api/productos
GET https://movil2-production.up.railway.app/api/users
GET https://movil2-production.up.railway.app/api/boletas


Cuando me pases tus links reales, los reemplazo automáticament
