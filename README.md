# OO2-TPFinal

## Requisitos:
- Tener instalado Docker
- Tener instalado Tomcat10
- Tener instalado algún Iddle para ejecutar Java.

### Opcional:
- Tener instalado algún visor de DB

## Recomendaciones:
- Como Ide se recomienda "Eclipse-JavaEnterpriseEdition" Probamos con otros pero este fue con el que se pudo desarrollar el software correctamente. Es de descarga gratuita desde la página de eclipse, se descarga el instalador genérico y al ejecutarlo se le ofrece entre varias versiones, entre las que está "Java EE IDE" el cual es el que interesa, el mismo tiene un icono similar a un engranaje. Los pasos a seguir en este documento se escribieron teniendo en cuenta este Ide.
- Como visor de DB se utilizó MySQL Workbench y la conexión fue sencilla por lo que se recomienda su uso.

## Puesta a punto:
1. Cree una nueva carpeta en su sistema operativo y dentro importe el contenido de la rama main de este repositorio.
2. Abrá Eclipse e importe la carpeta como un nuevo proyecto.
3. Ejecute Docker Desktop, abra una consola de comandos y el ejecute lo siguiente
    ```
    cd (el path de la carpeta que creó en el paso uno)/docker
    docker-compose.yml
    ```
    Con esto se habrá levantado un contenedor que ejecuta una BD MySQL. Si desea visualizar la misma puede conectarse desde su visor de BD de confianza. Todos los  datos de su conexión se pueden ver en el archivo docker/docker-compose.yml. Aun así, probablemente los datos importantes son los siguientes: "puerto: 3308", "db: dboo2", "username: root", "password: root".
4. Volviendo al Eclipse ahora es tiempo de configurar el servidor TomCat donde correrá la aplicación. Vaya a la solapa "Window/ShowView/Servers", se le abrirá una nueva ventana, en caso de que no tenga servidores creados entonces se le ofrecerá la opción de construir uno por medio de un link azul, clickeelo y en el menú emergente seleccione la opción "Apache/Tomcat 10 Server". Presione "Next" y en donde lo pide seleccione la carpeta donde tiene instalado TomCat10. Luego presione Finish.
Hecho esto ahora verá un servidor en la ventana "Servers" haga doble click sobre él y se le mostrará la ventana de configuración, en ella valla a la derecha en la sección "Ports" y en la Tabla que se muestra modifique el valor de la columna "PortNumber" correspondiente a la fila "TomcatAdminPort" por "1" y en la segunda fila correspondiente a "HTTP/1,1" reemplace el puerto por 9000.
5. Finalmente haya que ir a la ventana de "ProyectExplorer" (usualmente está del lado izquierdo) y haga click derechos sobre el proyecto en cuestión, allí seleccione "Maven/update Proyect" se abrirá una ventana, no toque nada de ahi y presione "Ok". Una vez el updateo termine, vuelva a hacer click derecho sobre la raíz del proyecto pero ahora seleccione "Run As/maven clean". Una vez se termina de ejecutar, haga clik derecho otra vez sobre la raíz del proyecto, pero ahora seleccionando "Run As/maven Install". Con eso el proyecto ya estará listo para su ejecución.

## Ejecución

Realizados todos los pasos anteriores, para ejecutar el software basta con ir al Eclipse, hacer click derecho sobre la carpeta raíz del proyecto y cliquear en "RunAs/RunOnServer", en la ventana emergente seleccionar el servidor antes construido y presionar siguiente hasta que el servidor se levante. Una vez se abra una ventana vacía en el navegador el servidor estará listo para su uso (puede cerrar la ventana si lo desea). 

En esta carpeta compartida de postman puede ver todos los endpoints del servicio, en la subcarpeta OO2: https://solar-spaceship-105447.postman.co/workspace/New-Team-Workspace~1e7118bf-6cc1-46ce-8b33-3f1717df3a28/collection/18209837-972e4a22-ca38-4169-bba8-9fce005e7178?action=share&creator=18209837. Tendra que solicitar permiso para poder editarlo, pero cuando lo haga le llegara una notificaccion a nuestros equipos por lo que una vez lo haga lo habilitaremos a la brevedad.


