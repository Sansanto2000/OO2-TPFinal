# OO2-TPFinal

## Requisitos:
- Tener instalado Docker
- 
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
4. Por ultimo, en Eclipse debe ir a la ventana de "ProyectExplorer" (usualmente está del lado izquierdo) y haga click derechos sobre el proyecto en cuestión, allí seleccione "Maven/update Proyect" se abrirá una ventana, no toque nada de ahi y presione "Ok". Una vez el updateo termine, vuelva a hacer click derecho sobre la raíz del proyecto pero ahora seleccione "Run As/maven clean". Una vez se termina de ejecutar, haga clik derecho otra vez sobre la raíz del proyecto, pero ahora seleccionando "Run As/maven Install". Con eso el proyecto ya estará listo para su ejecución.

## Ejecución

Realizados todos los pasos anteriores, para ejecutar el software basta con ir al Eclipse, ir a la carpeta correspondiente al proyecto, desde ahi avanzar a la seccion "Java Resources", entrar a la carpeta "/src/main/java", seleccionar el paquete "com.demo.finaloo2", hacer clik derecho en el archivo "Finaloo2Aplication" y clickear la opcion "Run As > Java Aplication".
![image](https://user-images.githubusercontent.com/64858429/209239787-617e5966-23d5-4937-befe-75ac5b53b52e.png)

Hecho esto los endpoins de la aplicacion estaran disponibles para su acceso en "http://localhost:8080/api/(el endpoint al que quiera acceder)". A continacion se deja el link a una carpeta compartida de postman, donde en la subcarpeta OO2 se pueden ver todos los endpoints del servicio: https://solar-spaceship-105447.postman.co/workspace/New-Team-Workspace~1e7118bf-6cc1-46ce-8b33-3f1717df3a28/collection/18209837-972e4a22-ca38-4169-bba8-9fce005e7178?action=share&creator=18209837. Tendra que solicitar permiso para poder editarlo, pero cuando lo haga le llegara una notificaccion a nuestros equipos por lo que cuando ocurra lo habilitaremos a la brevedad.

## Tests

En caso de que desee ejecutar los tests del proyecto (una vez terminada la puesta a punto) basta con que haga click derecho sobre la carpeta raiz del proyecto y seleccione la opcion "Run As > Maven test". Se abrira una consola emergente donde podra ver el resultado de los tests.
![image](https://user-images.githubusercontent.com/64858429/209240482-6bd7cdbc-0201-49cc-909b-09dd12cac02a.png)



