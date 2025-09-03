# 🎯 IronWars - Presentación del Proyecto

## 📋 Índice
1. [Introducción](#introducción)
2. [Objetivos Principales](#objetivos-principales)
3. [Alcance del Proyecto](#alcance-del-proyecto)
4. [Funcionalidades Principales](#funcionalidades-principales)
5. [Arquitectura Técnica](#arquitectura-técnica)
6. [Stack Tecnológico](#stack-tecnológico)
7. [API y Endpoints](#api-y-endpoints)
8. [Modelo de Datos](#modelo-de-datos)
9. [Casos de Uso](#casos-de-uso)
10. [Futuras Mejoras](#futuras-mejoras)

---

## 🌟 Introducción

**IronWars** es una plataforma backend de aprendizaje de lenguajes de programación desarrollada con **Java** y **Spring Boot**. La aplicación gestiona un entorno educativo completo donde estudiantes pueden aprender diferentes lenguajes de programación a través de ejercicios prácticos y exámenes evaluativos.

### 🎯 Concepto Central
Una plataforma educativa que conecta **profesores expertos** con **estudiantes** en un ambiente estructurado de aprendizaje progresivo de programación.

---

## 🎯 Objetivos Principales

### Objetivo General
Crear una plataforma robusta y escalable para la enseñanza y aprendizaje de lenguajes de programación mediante un sistema estructurado de ejercicios, exámenes y progresión por niveles.

### Objetivos Específicos

1. **🎓 Gestión Educativa**
   - Facilitar la creación y administración de contenido educativo
   - Proporcionar seguimiento del progreso estudiantil
   - Implementar evaluaciones automatizadas

2. **👨‍💻 Experiencia de Usuario**
   - Ofrecer una progresión clara por niveles (Básico, Intermedio, Avanzado)
   - Proporcionar feedback inmediato en ejercicios
   - Crear un sistema de evaluación justo y transparente

3. **🔧 Arquitectura Técnica**
   - Desarrollar una API REST robusta y bien documentada
   - Implementar un modelo de datos eficiente y escalable
   - Garantizar la seguridad y validación de datos

---

## 📊 Alcance del Proyecto

### 👥 Usuarios Objetivo

#### 👩‍🏫 Profesores
- **Rol**: Creadores y gestores de contenido
- **Responsabilidades**:
  - Crear y gestionar lenguajes de programación
  - Diseñar ejercicios prácticos
  - Crear exámenes evaluativos
  - Revisar el progreso de estudiantes

#### 👨‍🎓 Estudiantes
- **Rol**: Aprendices activos
- **Actividades**:
  - Inscribirse en lenguajes de programación
  - Completar ejercicios prácticos
  - Realizar exámenes
  - Progresar a través de niveles de dificultad

### 🌐 Ámbito Funcional

**Incluye:**
- ✅ Gestión completa de usuarios (CRUD)
- ✅ Sistema de lenguajes de programación
- ✅ Creación y gestión de ejercicios
- ✅ Sistema de exámenes y evaluaciones
- ✅ Progresión por niveles
- ✅ API REST completa con documentación

**Excluye (Versión Actual):**
- ❌ Interfaz de usuario frontend
- ❌ Sistema de autenticación avanzado
- ❌ Ejecución de código en tiempo real
- ❌ Sistema de notificaciones

---

## ⚙️ Funcionalidades Principales

### 1. 👤 Gestión de Usuarios

#### Estudiantes
- **Registro y perfil personal**
- **Inscripción en lenguajes**
- **Seguimiento de progreso**
- **Historial de ejercicios y exámenes**

#### Profesores
- **Gestión de perfil experto**
- **Asignación a lenguajes específicos**
- **Creación de contenido educativo**
- **Monitoreo de estudiantes**

### 2. 💻 Gestión de Lenguajes
- **CRUD completo de lenguajes de programación**
- **Asignación de profesores expertos**
- **Categorización por niveles de dificultad**
- **Listado de expertos por lenguaje**

### 3. 📝 Sistema de Ejercicios
- **Creación de ejercicios vinculados a lenguajes**
- **Autoría por profesores**
- **Clasificación por dificultad**
- **Gestión completa (CRUD)**

### 4. 📘 Sistema de Exámenes
- **Creación de exámenes personalizados**
- **Asignación de ejercicios a exámenes**
- **Evaluación de estudiantes**
- **Sistema de calificaciones**

### 5. 📈 Sistema de Progresión
- **Niveles**: Básico → Intermedio → Avanzado
- **Seguimiento de rendimiento**
- **Evaluación continua**
- **Certificación de competencias**

---

## 🏗️ Arquitectura Técnica

### Arquitectura de Capas

```
┌─────────────────────────────────────┐
│            Controllers              │ ← REST API Layer
├─────────────────────────────────────┤
│               DTOs                  │ ← Data Transfer Objects
├─────────────────────────────────────┤
│           Services Layer            │ ← Business Logic
├─────────────────────────────────────┤
│         Repository Layer            │ ← Data Access
├─────────────────────────────────────┤
│          Model/Entities             │ ← Domain Model
├─────────────────────────────────────┤
│            Database                 │ ← MySQL Database
└─────────────────────────────────────┘
```

### Componentes Principales

#### 🎮 Controllers
- **TeacherController**: Gestión de profesores
- **StudentController**: Gestión de estudiantes
- **LanguageController**: Gestión de lenguajes
- **ExerciseController**: Gestión de ejercicios
- **ExamController**: Gestión de exámenes

#### 🔄 Services
- **Interfaces**: Contratos de servicio
- **Implementaciones**: Lógica de negocio
- **Validaciones**: Reglas de negocio
- **Transformaciones**: Mapeo de datos

#### 💾 Repositories
- **JPA Repositories**: Acceso a datos
- **Consultas personalizadas**: Búsquedas específicas
- **Relaciones**: Gestión de asociaciones

#### 🏛️ Models
- **User** (Clase abstracta base)
- **Student** y **Teacher** (Herencia)
- **Language**, **Exercise**, **Exam**
- **PersonalData** (Datos embebidos)

---

## 🛠️ Stack Tecnológico

### Backend Core
- **☕ Java 17+**: Lenguaje de programación principal
- **🍃 Spring Boot 3.x**: Framework de aplicación
- **🗄️ Spring Data JPA**: Acceso a datos
- **🔍 Hibernate**: ORM (Object-Relational Mapping)

### Base de Datos
- **🐬 MySQL**: Base de datos relacional
- **🔧 DBeaver**: Herramienta de gestión de BD

### Herramientas de Desarrollo
- **🔨 Maven**: Gestión de dependencias y construcción
- **🎯 Lombok**: Reducción de código boilerplate
- **📚 Swagger/OpenAPI**: Documentación automática de API
- **📬 Postman**: Testing de endpoints

### Validación y Configuración
- **✅ Bean Validation**: Validación de datos
- **⚙️ Spring Boot DevTools**: Desarrollo ágil
- **📄 YAML**: Configuración de aplicación

---

## 🌐 API y Endpoints

### 👩‍🏫 Endpoints de Profesores (`/teachers`)

```http
GET    /teachers                     → Listar todos los profesores
GET    /teachers/{id}               → Obtener profesor por ID
POST   /teachers                    → Crear nuevo profesor
PUT    /teachers/{id}               → Actualizar profesor
DELETE /teachers/{id}               → Eliminar profesor
POST   /teachers/{id}/languages/{langId} → Asignar lenguaje
DELETE /teachers/{id}/languages/{langId} → Desasignar lenguaje
GET    /teachers/by-language/{langId}     → Profesores por lenguaje
```

### 👨‍🎓 Endpoints de Estudiantes (`/students`)

```http
GET    /students                    → Listar todos los estudiantes
GET    /students/{id}              → Obtener estudiante por ID
POST   /students                   → Crear nuevo estudiante
PUT    /students/{id}              → Actualizar estudiante
DELETE /students/{id}              → Eliminar estudiante
```

### 💻 Endpoints de Lenguajes (`/languages`)

```http
GET    /languages                  → Listar todos los lenguajes
GET    /languages/{id}            → Obtener lenguaje por ID
POST   /languages                 → Crear nuevo lenguaje
PUT    /languages/{id}            → Actualizar lenguaje
DELETE /languages/{id}            → Eliminar lenguaje
GET    /languages/{id}/teachers   → Profesores expertos del lenguaje
```

### 📝 Endpoints de Ejercicios (`/exercises`)

```http
GET    /exercises                  → Listar todos los ejercicios
GET    /exercises/{id}            → Obtener ejercicio por ID
POST   /exercises                 → Crear nuevo ejercicio
PUT    /exercises/{id}            → Actualizar ejercicio
DELETE /exercises/{id}            → Eliminar ejercicio
```

### 📘 Endpoints de Exámenes (`/exams`)

```http
GET    /exams                     → Listar todos los exámenes
GET    /exams/{id}               → Obtener examen por ID
POST   /exams                    → Crear nuevo examen
PUT    /exams/{id}               → Actualizar examen
DELETE /exams/{id}               → Eliminar examen
```

---

## 📊 Modelo de Datos

### Entidades Principales

#### 👤 User (Clase Abstracta)
```java
- id: Long
- nickName: String
- password: String
- personalData: PersonalData
```

#### 👩‍🏫 Teacher
```java
- experienceLevel: ExperienceLevel
- languages: Set<Language>
```

#### 👨‍🎓 Student
```java
- currentLevel: ExperienceLevel
- enrolledLanguages: Set<Language>
- completedExercises: Set<Exercise>
- examResults: Set<ExamResult>
```

#### 💻 Language
```java
- id: Long
- name: String
- description: String
- difficulty: Difficulty
- teachers: Set<Teacher>
- exercises: Set<Exercise>
```

#### 📝 Exercise
```java
- id: Long
- title: String
- description: String
- difficulty: Difficulty
- language: Language
- author: Teacher
```

#### 📘 Exam
```java
- id: Long
- title: String
- description: String
- exercises: Set<Exercise>
- duration: Integer
- language: Language
```

### Enumeraciones

#### 🎯 Difficulty
```java
BASIC, INTERMEDIATE, ADVANCED
```

#### 📊 Score
```java
EXCELLENT, GOOD, FAIR, POOR
```

#### 🎓 ExperienceLevel
```java
BEGINNER, INTERMEDIATE, ADVANCED, EXPERT
```

---

## 🎬 Casos de Uso

### Caso de Uso 1: Profesor Crea Ejercicio
1. **Actor**: Profesor
2. **Flujo**:
   - Profesor se autentica en el sistema
   - Selecciona el lenguaje de programación
   - Crea un nuevo ejercicio con título y descripción
   - Asigna nivel de dificultad
   - Guarda el ejercicio en el sistema

### Caso de Uso 2: Estudiante Realiza Examen
1. **Actor**: Estudiante
2. **Flujo**:
   - Estudiante accede a la lista de exámenes disponibles
   - Selecciona un examen de su nivel
   - Completa los ejercicios del examen
   - Envía las respuestas
   - Recibe calificación y feedback

### Caso de Uso 3: Gestión de Progresión
1. **Actor**: Sistema
2. **Flujo**:
   - Evalúa el rendimiento del estudiante
   - Calcula la progresión basada en exámenes completados
   - Actualiza el nivel del estudiante si cumple requisitos
   - Notifica al estudiante sobre su progreso

---

## 🚀 Futuras Mejoras

### Fase 2: Expansión de Funcionalidades
- **🖥️ Frontend React/Angular**: Interfaz de usuario completa
- **🔐 Autenticación JWT**: Sistema de seguridad robusto
- **📧 Sistema de Notificaciones**: Emails y notificaciones push
- **📊 Dashboard Analytics**: Métricas y reportes avanzados

### Fase 3: Características Avanzadas
- **⚡ Ejecución de Código**: Compilador y ejecutor en línea
- **🤖 IA Integration**: Asistente virtual para apoyo
- **📱 Mobile App**: Aplicación móvil nativa
- **🌍 Internacionalización**: Soporte multi-idioma

### Fase 4: Escalabilidad
- **☁️ Cloud Deployment**: AWS/Azure deployment
- **🐳 Containerización**: Docker y Kubernetes
- **📈 Microservicios**: Arquitectura distribuida
- **🔄 CI/CD Pipeline**: Integración y despliegue continuo

---

## 📈 Conclusiones

**IronWars** representa una solución integral para la educación en programación, combinando:

- ✅ **Arquitectura sólida** con Spring Boot
- ✅ **Modelo de datos bien estructurado**
- ✅ **API REST completa y documentada**
- ✅ **Sistema de progresión educativa**
- ✅ **Escalabilidad para futuras mejoras**

### Impacto Esperado
- **📚 Educación estructurada** en programación
- **🎯 Seguimiento personalizado** del progreso
- **🏆 Motivación** a través de niveles y logros
- **🤝 Colaboración** entre profesores y estudiantes

---

## 📞 Información del Proyecto

- **📂 Repositorio**: [YronJack/IronWars](https://github.com/YronJack/IronWars)
- **🛠️ Tecnología**: Java 17 + Spring Boot 3.x
- **📋 Estado**: Versión MVP completa
- **🎯 Objetivo**: Plataforma de aprendizaje de programación

---

*Desarrollado como parte del módulo Backend (Java, JPA, Spring, MySQL, Lombok)*