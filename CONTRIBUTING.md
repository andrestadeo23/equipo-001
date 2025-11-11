# 🧭 Guía de Contribución – Flujo de Trabajo Git Flow

Bienvenido/a al equipo 🚀  
Este documento explica **cómo trabajamos con Git Flow** para mantener un código limpio, organizado y fácil de integrar.

---

## 🌳 Estructura Principal de Ramas

Nuestro flujo sigue el modelo **Git Flow**, el cual define ramas específicas con propósitos claros:

| Rama | Propósito | Puede fusionarse con |
|------|------------|----------------------|
| `main` | Contiene el código **estable y listo para producción**. | `release`, `hotfix` |
| `develop` | Rama base de **desarrollo**, contiene código funcional pendiente de lanzar. | `feature`, `release`, `hotfix` |
| `feature/*` | Nuevas funcionalidades o tareas asignadas. | `develop` |
| `release/*` | Preparación de versiones para producción (QA, pruebas finales). | `main`, `develop` |
| `hotfix/*` | Corrección de errores críticos en producción. | `main`, `develop` |

---

## 🧩 Flujo de Trabajo Paso a Paso

### 1. Clonar el Repositorio

```bash
git clone https://github.com/andrestadeo23/equipo-001.git
cd equipo-001
```

### 2. Crear una Rama de Trabajo (Feature)

```bash
git checkout develop
git pull origin develop
git checkout -b feature/<nombre-de-la-funcionalidad>
```

Ejemplo:

```bash
git checkout -b feature/login-auth
```

> 🔹 Usa nombres descriptivos y en **minúsculas** con guiones (-) para separar palabras.

### 3. Realizar Commits Claros y Pequeños

Sigue este formato:

```
<tipo>: <descripción breve>

Ejemplo:
feat: agrega validación JWT en el backend
fix: corrige padding en el botón de login
docs: actualiza instrucciones de instalación
```

**Tipos comunes:**

- `feat`: nueva funcionalidad  
- `fix`: corrección de error  
- `docs`: documentación  
- `refactor`: reestructuración del código sin cambiar comportamiento  
- `test`: pruebas  
- `chore`: tareas menores (config, dependencias, etc.)

---

## 📬 Integración de Cambios

### 4. Sincronizar con `develop`

Antes de abrir un pull request:

```bash
git fetch origin
git rebase origin/develop
```

### 5. Subir tu Rama

```bash
git push origin feature/<nombre-de-la-funcionalidad>
```

### 6. Crear un Pull Request (PR)

- Base: `develop`
- Comparar con: `feature/<nombre>`
- Agrega una descripción clara: **qué hace, por qué se hizo, cómo probarlo.**
- Menciona al revisor con `@usuario`.

> ✅ Todo PR debe ser aprobado por al menos 1 revisor antes de fusionarse.

---

## 🚀 Lanzamientos

### 7. Preparar una Versión (Release)

Cuando el desarrollo esté estable:

```bash
git checkout develop
git checkout -b release/<versión>
```

Ejemplo:

```bash
git checkout -b release/1.0.0
```

Durante esta fase:

- Se corrigen bugs menores.
- Se actualiza el `CHANGELOG.md`.
- Se ajusta la versión en los archivos del proyecto.

Una vez validado:

```bash
git checkout main
git merge --no-ff release/1.0.0
git tag -a v1.0.0 -m "Versión 1.0.0 estable"
git push origin main --tags
```

Luego:

```bash
git checkout develop
git merge --no-ff release/1.0.0
git push origin develop
```

---

## 🩹 Hotfixes en Producción

Si hay un bug crítico:

```bash
git checkout main
git checkout -b hotfix/<nombre>
```

Después de corregir:

```bash
git commit -m "fix(producción): corrige bug en login"
git checkout main
git merge --no-ff hotfix/<nombre>
git tag -a v1.0.1 -m "Hotfix 1.0.1"
git push origin main --tags

git checkout develop
git merge --no-ff hotfix/<nombre>
git push origin develop
```

---

## 🧠 Buenas Prácticas

- Nunca subas código directamente a `main` ni `develop`.
- Usa `git pull --rebase` para mantener un historial limpio.
- Borra tus ramas locales y remotas al terminar:

  ```bash
  git branch -d feature/<nombre>
  git push origin --delete feature/<nombre>
  ```

- Documenta siempre tus cambios en el PR.
- Mantén commits pequeños, atómicos y significativos.

---

## 🛠️ Herramientas Recomendadas

- **GitKraken / SourceTree** – visualización del flujo Git.
- **Conventional Commits** – formato estandarizado de mensajes.
- **Pull Request Template** – plantilla predefinida de PR.
- **Branch Protection Rules** – evita fusiones directas a `main` o `develop`.

---

## 💬 Dudas o Sugerencias

Si tienes alguna duda, abre un issue con el prefijo:

```
[Question] Cómo crear una rama de release
```

o pregunta directamente en el canal `#equipo-001` de nuestro Discord interno.

---

### 📘 Referencia

- [Git Flow by Vincent Driessen](https://nvie.com/posts/a-successful-git-branching-model/)
- [Conventional Commits Specification](https://www.conventionalcommits.org/)

---

👩‍💻 **Gracias por contribuir al proyecto!**  
Cada commit cuenta para seguir construyendo algo grande 💙
