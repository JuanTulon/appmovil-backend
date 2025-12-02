# LimpiFresh - Backend API

Este repositorio contiene el código fuente del microservicio Backend para la aplicación móvil **LimpiFresh**. Es una API REST desarrollada en Spring Boot (Java 21) que gestiona la lógica de negocio, la persistencia de datos y la seguridad de una tienda de productos de limpieza.

## 2. Integrantes

* [juan vera]
* [fabian cuevas]
* [alonso carrasco]

## 3. Funcionalidades

El sistema backend provee las siguientes capacidades:

* **Gestión de Usuarios y Autenticación:**
    * Registro de clientes.
    * Inicio de sesión (Login) con validación de credenciales.
    * Roles de usuario (ADMIN y CLIENTE).
* **Catálogo de Productos:**
    * CRUD completo de productos (Crear, Leer, Actualizar, Eliminar).
    * Filtrado de productos en oferta.
    * Gestión de stock y precios.
    * Carga de imágenes de productos (Upload).
* **Sistema de Blog:**
    * Publicación de artículos/noticias relacionadas con la limpieza.
    * Gestión de contenido por parte del administrador.
* **Gestión de Contacto:**
    * Recepción de mensajes de contacto desde la app.
    * Visualización de mensajes para administración.
* **Gestión de Ventas (Boletas):**
    * Generación de boletas de venta.
    * Cálculo de totales, IVA y subtotales.
    * Historial de compras asociado al usuario.
* **Carga de Datos Inicial (DataLoader):**
    * El sistema genera automáticamente usuarios de prueba (Admin y Cliente) y 20 productos aleatorios al iniciar si la base de datos está vacía.

## 4. Endpoints Utilizados

La documentación interactiva de la API está disponible a través de **Swagger UI** una vez iniciada la aplicación en: `http://localhost:8080/swagger-ui/index.html`

### Microservicio Backend (API Local)

A continuación, se detallan los endpoints principales expuestos por este microservicio:

#### 🔐 Autenticación (`/api/auth`)
| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `POST` | `/api/auth/registro` | Registra un nuevo cliente. |
| `POST` | `/api/auth/login` | Inicia sesión (retorna datos del usuario). |
| `POST` | `/api/auth/crear-admin` | Crea un usuario con rol ADMIN. |

#### 🛒 Productos (`/api/productos`)
| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/api/productos` | Lista todos los productos. |
| `GET` | `/api/productos/ofertas` | Lista solo los productos con oferta activa. |
| `GET` | `/api/productos/{id}` | Obtiene el detalle de un producto. |
| `POST` | `/api/productos` | Crea un nuevo producto. |
| `PUT` | `/api/productos/{id}` | Actualiza un producto existente. |
| `DELETE` | `/api/productos/{id}` | Elimina un producto. |
| `POST` | `/api/productos/upload` | Sube una imagen de producto al servidor. |

#### 📰 Blog (`/api/blogs`)
| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/api/blogs` | Lista todas las entradas del blog. |
| `GET` | `/api/blogs/{id}` | Obtiene una entrada específica. |
| `POST` | `/api/blogs` | Crea una nueva entrada (Admin). |
| `PUT` | `/api/blogs/{id}` | Actualiza una entrada. |
| `DELETE` | `/api/blogs/{id}` | Elimina una entrada. |

#### 🧾 Boletas (`/api/boletas`)
| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `POST` | `/api/boletas` | Genera una nueva boleta de compra. |
| `GET` | `/api/boletas/{id}` | Obtiene una boleta por su ID interno. |
| `GET` | `/api/boletas/numero/{numero}` | Busca una boleta por su número de folio. |

#### 📩 Contacto (`/api/contacto`)
| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `POST` | `/api/contacto` | Envía un mensaje de contacto. |
| `GET` | `/api/contacto` | Lista todos los mensajes (Admin). |

### API Externa

## 5. Pasos para Ejecutar

### Prerrequisitos
* **Java JDK 21** instalado.
* **MySQL** instalado y ejecutándose.
* **Maven** (opcional, el proyecto incluye `mvnw`).

### Configuración de la Base de Datos
1.  Abre tu gestor de base de datos (phpMyAdmin, Workbench, DBeaver).
2.  Crea una base de datos vacía llamada `LimpiFresh`.
    ```sql
    CREATE DATABASE LimpiFresh;
    ```
3.  Verifica que el archivo `src/main/resources/application.properties` tenga las credenciales correctas de tu MySQL local:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/LimpiFresh?serverTimezone=UTC&useSSL=false
    spring.datasource.username=root
    spring.datasource.password=  <-- Pon tu contraseña aquí si tienes
    ```

### Configuración de Directorio de Imágenes
El controlador de imágenes (`ProductoController`) está configurado para guardar archivos en una ruta específica. 
* **Nota:** Asegúrate de crear la carpeta `/home/ubuntu/uploads/` en tu sistema o cambia la variable `uploadDir` en `ProductoController.java` y en `WebConfig.java` a una ruta válida en tu PC (ej: `C:/LimpiFresh/uploads/`).

### Ejecución del Proyecto
1.  Abre una terminal en la raíz del proyecto.
2.  Ejecuta el siguiente comando para limpiar, compilar y ejecutar:
    * **En Windows:**
        ```vsc
        apretar en run en la app
        ```

3.  Una vez iniciado, verás en la consola que la aplicación corre en el puerto `8080`.

### Usuarios de Prueba (Generados automáticamente)
* **Admin:** `admin@limpiohogar.cl` / `admin123.`
* **Cliente:** `cliente@correo.com` / `123456`

## 6. Captura del APK firmado y .jks
<img width="840" height="506" alt="Captura de pantalla 2025-12-02 134154" src="https://github.com/user-attachments/assets/c258641a-b5d2-4f34-a79a-a875aeda889b" />
<img width="841" height="698" alt="Captura de pantalla 2025-12-02 134207" src="https://github.com/user-attachments/assets/031b498d-8a46-437a-897e-9a9dc39c1877" />
