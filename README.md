# 🔐 Taller de Arquitectura Empresarial: Diseño de Aplicaciones Seguras

En este taller, diseñaremos y desplegaremos una aplicación segura y escalable utilizando infraestructura de AWS, aplicando las mejores prácticas de seguridad.

---

## 📌 Componentes Principales

✅ **Servidor Web: Apache HTTP Server**

- Servirá una aplicación HTML+JavaScript sobre una conexión segura TLS.
- Garantizará la integridad y confidencialidad de los datos.

✅ **Servidor Backend: Spring Boot**

- Proveerá servicios RESTful con autenticación y autorización seguras.
- Estará protegido con TLS para una comunicación cifrada.

✅ **Medidas de Seguridad**

- 🔒 **Cifrado TLS** con certificados de Let's Encrypt.
- ⚡ **Cliente Asíncrono** para optimizar rendimiento y seguridad.
- 🛡️ **Autenticación Segura** con almacenamiento de contraseñas en hashes.
- ☁️ **Despliegue en AWS** aprovechando infraestructura segura.

---

## 🛠️ Requisitos

Antes de ejecutar el proyecto, asegúrate de contar con:

- **Java 11 o superior** - Para ejecutar el backend en Spring Boot.
- **Apache HTTP Server** - Para servir el frontend.
- **Maven** - Para compilar el backend.
- **Docker** - Para contenedorización.
- **AWS EC2** - Para el despliegue en la nube.

---

## 🚀 Instalación y Ejecución

1️⃣ **Clonar el repositorio:**

```sh
git clone https://github.com/FlypZed/Lab06-AuthService.git
```

2️⃣ **Acceder al proyecto:**

```sh
cd Lab06-AuthService
```

3️⃣ **Ejecutar Apache:**

```sh
apachectl start
```

4️⃣ **Compilar y ejecutar el backend:**

```sh
mvn clean package
java -jar target/auth-service.jar
```

5️⃣ **Acceder desde el navegador:**

🔗 [http://localhost:8080](http://localhost:8080)

---

## 📦 Uso de Docker

### 🚀 Construcción de la Imagen

```sh
docker build -t auth-service .
```

### 📡 Ejecución del Contenedor

```sh
docker run -p 8080:8080 auth-service
```

---

## 🔄 Despliegue en AWS EC2

Para desplegar en AWS EC2:

1️⃣ **Subir la imagen a Docker Hub** o transferir el código a EC2.\
2️⃣ **Configurar EC2 e instalar Docker:**

```sh
sudo yum update -y
sudo yum install docker -y
sudo systemctl start docker
```

3️⃣ **Descargar y ejecutar la imagen:**

```sh
docker run -p 8080:8080 auth-service
```

4️⃣ **Acceder desde el navegador o con `curl`**:

```sh
curl http://ec2-54-205-100-128.compute-1.amazonaws.com:8080
```

---

## 📂 Estructura del Proyecto

```
Lab06-AuthService/
│── client/  # Cliente HTML+JS servido por Apache
│── backend/
│   ├── src/main/java/com/security/
│   │   ├── SecurityConfig.java  # Configuración de TLS y autenticación
│   │   ├── AuthController.java  # Controlador de autenticación
│   │   ├── UserService.java  # Gestión segura de usuarios y contraseñas
│   ├── src/test/java/com/security/  # Pruebas unitarias y de integración
│   │   ├── AuthControllerTest.java  # Pruebas para la autenticación
│   │   ├── UserServiceTest.java  # Pruebas de lógica de negocio
```

---

## 🧪 Pruebas

Para ejecutar las pruebas unitarias y de integración, usa el siguiente comando:

```sh
mvn test
```

Esto validará la seguridad y correcto funcionamiento del sistema antes del despliegue.

---

## 📌 Autor

👨‍💻 **FlypZed**\
🔗 GitHub: [FlypZed](https://github.com/FlypZed)\
📧 Contacto: [flyp.and@gmail.com](mailto:flyp.and@gmail.com)

