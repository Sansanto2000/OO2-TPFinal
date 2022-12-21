# OO2-TPFinal

## Requisitos:
- Tener instalado Docker
- Tener instalado Tomcat10
- Tener instalado algun Iddle para ejecutar Java.

### Opcional:
- Tener instalado algun visor de DB

## Recomendaciones:
- Como Ide se recomienda "Eclipse-JavaEnterpriseEdition" Probamos con otros pero este fue con el que se pudo desarrollar el software correctamente. Es de descarga gratuita desde la pagina de eclipse, se descarga el instalador generico y al ejecutarlo se le ofrece entre varias verciones, entre las que esta "Java EE IDE" el cual es el que interesa, el mismo tienen un icono similar a un engranaje. Los pasos a seguir en este documento se escribieron teniendo en cuenta este Ide.
- Como visor de DB se utilizo MySQL Workbench y la conexion fue sencilla por lo que se recomienda su uso.

## Puesta a punto:
1. Cree una nueva carpeta en su sistema operativo y dentor importe el contenido de la rama main de este repositorio.
2. Abra Eclipse e importe la carpeta como un nuevo proyecto.
3. Ejecute Docker Descktop, abra una consola de comandos y el ejecute lo siguiente
    ```
    cd (el path de la carpeta que creo en el paso uno)/docker
    docker-compose.yml
    ```
    Con esto se habra levantado un contenedor que ejecuta una BD MySQL. Si desea visualizar la misma puede conectarse desde su visor de BD de confianza. Todos los  datos de su conexion se pueden ver en el archivo docker/docker-compose.yml. Aun asi, provablemente los datos importantes son los siguientes: "puerto: 3308", "db: dboo2", "username: root", "password: root".
4. Volviendo al Eclipse ahora es tiempo de configurar el servidor TomCat donde correra la aplicacion. Vaya a la solapa "Window/ShowView/Servers", se le abrira una nueva ventana, en caso de que no tenga servidores creados entonces se le ofrecera la opcion de construir uno por medio de un link azul, clickeelo y en el menu emergente seleccione la opcion "Apache/Tomcat 10 Server". Precione "Next" y en donde lo pide seleccione la carpeta donde tiene instalado TomCat10. Luego precione Finish.
Hecho esto ahora vera un servidor en la ventana "Servers" haga doble click sobre el y se le mostrara la ventana de configuracion, en ella valla a la deracha en la seccion "Ports" y en la Tabla que se muestra modifique el valor de la columna "PortNumber" correspondiente a la fila "TomcatAdminPort" por "1" y en la segunda fila correspondiente a "HTTP/1,1" remplaze el puerto por 9000.
5. Finalmente haya que ir a la ventana de "ProyectExplorer" (usualmente esta del lado izquierdo) y haga click derechos sobre el proyecto en cuestion, alli seleccione "Maven/update Proyect" se abrira una ventana, no toque nada de ahi y precione "Ok". Una vez el updateo termine, vuelva a hacer click derecho sobre la raiz del proyecto pero ahora seleccione "Run As/maven clean". Una vez se termina de ejecutar, haga clik derecho otra vez sobre la raiz del proyecto, pero ahora seleccionando "Run As/maven Install". Con eso el proyecto ya estara listo para su ejecucion.

## Ejecucion

Realizados todos los pasos anteriores, para ejecutar el software basta con ir al Eclipse, hacer click derecho sobre la carpeta raiz del proyecto y cliquear en "RunAs/RunOnServer", en la ventana emergente seleccionar el servidor antes construido y precionar siguiente hasta que el servidor se levante. Una vez se habra una ventana vacia en el navegador el servidor estara listo para su uso (puede cerrar la ventana si lo desea). 

En esta carpeta compartida de postman puede ver todos los endpoints del servicio, en la subcarpeta OO2: .
1. 

