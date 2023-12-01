# Watch Next
[![en](https://img.shields.io/badge/lang-en-red.svg)](https://github.com/Shadowsvl/Watch-Next/blob/develop/README.en.md)

Proyecto muestra en desarrollo usando [Jetpack Compose](https://developer.android.com/jetpack/compose). Con el objetivo de mostrar un proyecto modular con las capacidades actuales para la creación de UI, y su uso en conjunto con el resto de librerías para navegación, inyección de dependencias, consumo de servicios REST y persistencia de datos local. Siguiendo las prácticas actuales recomendadas por la [Guía de Arquitectura](https://developer.android.com/topic/architecture).

Conoce lo más reciente en películas y series, tendencias y otros.
Revisa el listado sin fin para películas y series.
Agrega contenido a tu lista para no perderte de nada.

## Características

La aplicación consta de cinco pantallas:
1. Vista principal dividida por secciones de contenido reciente para películas y series, así como los más recientes agregados a tu lista.
2. Detalle con la información y resumen completo, que cuenta con una sección con más contenido.
3. Listado que muestra películas o series, que va cargando más contenido conforme te vas desplazando hacia el fondo.
4. Listado de películas y series guardadas.
5. Búsqueda de contenido por título.

Cada pantalla muestra una vista previa con la información del contenido seleccionado, que permite agregar fácilmente a tu listado, así como navegar a la pantalla de detalle.

#### Dependencias principales
* **Compose** - UI
* **Material 3** - Sistema de Diseño
* **Hilt** - Inyección de Dependencias
* **Room** - Persistencia de datos local (SQLite)
* **Retrofit** - Cliente HTTP
* **Coil** - Carga de imágenes

## Requisitos

El proyecto utiliza [The Movie DB API](https://www.themoviedb.org/documentation/api). Puedes registrarte para obtener una llave de API.

Es necesario crear un archivo **secret.properties** en el directorio principal y agregar la llave:

`API_KEY=<TuLlaveDeAPI>`

## Capturas

<img src="https://drive.google.com/uc?id=1P0x4d0H7oy7gxZJ14kJLDo3sBlOGt0wD" alt="Screenshot">
<img src="https://drive.google.com/uc?id=1lCtLq7Il52dde460HJ_-gJbrIBc4DDSl" alt="Screenshot">
<img src="https://drive.google.com/uc?id=1PPvJL9-cxYQ9GL-nOvZrXpaIxT2GapX3" alt="Screenshot">
<img src="https://drive.google.com/uc?id=1a2HYysYruph4XJ4DzdRyJhMy2Z4PWZSw" alt="Screenshot">
<img src="https://drive.google.com/uc?id=1C4B1mHWnGgUNCiAi2C_ojEhsTXR71gaV" alt="Screenshot">
<img src="https://drive.google.com/uc?id=1r3PHiMG7jKghlzxG2_8sH2pixniq72Bi" alt="Screenshot">
<img src="https://drive.google.com/uc?id=1AF2wLoITkIsQgWJn2KdxbQZ5KYGJYhDg" alt="Screenshot">
<img src="https://drive.google.com/uc?id=1ThuPaKN6xOl8c-rP9y7SvpGTJNDSetUj" alt="Screenshot">
<img src="https://drive.google.com/uc?id=1u82nS_7dmKFcwIx_8hFDO1RKhYVo_3Sg" alt="Screenshot">
<img src="https://drive.google.com/uc?id=1P1bAmMRrtZsHtM4e3LoJIsb8FLevBfzk" alt="Screenshot">
<img src="https://drive.google.com/uc?id=1G_xbx7Wuo611G5VDIqIM9zdT3589LMVQ" alt="Screenshot">
<img src="https://drive.google.com/uc?id=1wmcI3r-VOb7J3ei_DqzQxMeNWRzCW407" alt="Screenshot">