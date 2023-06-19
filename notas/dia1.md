# SpringBatch

Es una librería dentro de un framework GIGANTE (el framework) de JAVA.
De qué framework? Spring

Spring es un framework para el desarrollo de apps JAVA... de todo tipo.
SpringBatch es una librería para procesos BATCH.

## Framework vs librería

Un framework suele tener 1 o más librerías... algunos como Spring, cientos de ellas.
Pero además, un framework, en contraposición con una librería me impone una determinada metodología (o forma de uso) de esas librerías

# Spring

Un framework que ofrece INVERSION DE CONTROL.

## Inversión de control

Un patrón de desarrollo, donde delegamos el control del flujo de una aplicación a un tercero (el framework: Spring)
Para qué? Para conseguir INYECCION DE DEPENDENCIAS

## Inyección de dependencias

Un patrón de desarrollo, mediante el que hacemos que un objeto de una clase no cree objetos de otras clases directamente, sino que le sean suministrados.
Para qué? Para conseguir INVERSION DE DEPENDENCIAS

## Inversión de dependencias:

Otro patrón de desarrollo de software (Uno de los 5 grandes) que permite DESACOPLAR módulos de un sistema.
Nos dice que los módulos de alto nivel no deberían depender de implementaciones sino de abstraciones (contratos/interfaces).

---

Quiero desarrollar una Librería que me permita trabajar con diccionarios, en los que buscar palabras... y ver si existen en un determinado idioma y sacar sus definiciones.

Esa librería tendrá un API:

    interface Diccionario {

        String getIdioma();
        boolean existe (String palabra);
        Optional<List<String>> getSignificados(String palabra);

                        Esas 33 formas de implementarlo son una MIERDA GIGANTE en java... ¡Claro... desde java 1.8!
                            Lista vacia...                  Ambiguo. No se viendo la definición si devuelve eso o un null.
                            null
                            lanzar NoSuchWordException      No es ambigua... pero fatal práctica de desarrollo usar exceciones para controlar lógica
    }

    interface SuministradorDeDiccionarios{
        boolean tienesDiccionarioDe(String idioma);
        Optional<Diccionario> getDiccionarioDe(String idioma);
    }

Implemento esa interfaz:
- SuministradorDeDiccionariosDesdeFicheros
- SuministradorDeDiccionariosDesdeBBDD

Ahora quiero montar una app que use el diccionario
Monto una app... y en algún sitio recibe una palabra... y la quiere validar a ver si existe en el diccionario de un determinado idioma.

App.java

import com.milibreria.Diccionario;
import com.milibreria.SuministradorDeDiccionarios;
//import com.milibreria.impl.SuministradorDeDiccionariosDesdeFicheros; // ESTE ES EL PROBLEMA
// ESTA LINEA ES LA QUE MATA LOS PROYECTOS DE SOFTWARE
// A partir de este momento el proyecto está condenado a TENER UNA MANTENIBILIDAD de -10
// RUINA GIGANTE !!!
public void procesarTexto(..., SuministradorDeDiccionarios suministrador){ // Inyección de dependencias, que me permite RESPETAR EL PPO DE INVERSION DE DEPENDENCIAS
...
boolean existe=false;
String idioma = "ES";
String palabra = "Federico";
// SuministradorDeDiccionarios suministrador = new SuministradorDeDiccionariosDesdeFicheros(); // ROMPE EL PPO DE INVERSION DE DEPENDENCIAS
Optional<Diccionario> posibleDiccionario = suministrador.getDiccionarioDe(idioma);
if(posibleDiccionario.isPresent()){
Diccionario diccionarioDelIdioma = posibleDiccionario.get(); // Abre la caja
if(diccionarioDelIdioma.existe(palabra)){
existe=true;
}
}
// Hago lo que necesite con "existe"
...
...
}
App  --------->>  API Librería  Diccionario  <<---------  Impl Diccionario desde Ficheros
|                                                                     ^
  -----------------------------------------------------------------------


# Inversión de control

Es un patrón de desarrollo de software que nos facilita la inyección de dependencias.

Cómo cambia mi código con esto...

class Federico {

    public static void main(String[] args){
        Spring.ejecutaMiAplicacion(); // .PELOTA !
        // Qué aplicación tio??
        // Le explico a Spring cómo es mi app.
        // Esto lo voy a hacer de una forma sencilla
        // Quiero que mi aplicación sea un proceso BATCH
        // Quiero que ejecute TAL TRABAJO
        // Ese trabajo tiene una serie de pasos
        // Cada paso... se lo detallo
        // El paso1:
        //  Quiero que lea un fichero de datos (CARGA DE DATOS)
        //  Quiero que transformes esos datos (TRANSFORMACION, que los proceses)
        //  Quiero que vuelques los datos transformados a algún sitio (VOLCADO DE DATOS)
        // Oye! Ah! que me acabo de acordar!!! Cuando acabes, me avisas (por email)
        // Oye! Ah ! que me acabo de acordar!!! Si algo falla, me avisas (por email)
        // Oye Spring!! Ah ! que me acabo de acordar!!! Cuando vayas a empezar a ejecutar el trabajo, me avisas (por email)
        // Oye que antes de empezar: Necesito que mires si la palabra existe en el diccionario
        //              procesarTexto
    }

}

# Cocnepto clave en Spring BEAN

Qué es un Bean?

Un objeto que se crea y se gestiona mediante Spring.

Cuando trabajamos con Spring usamos mucho el concepto de ANOTACION. Se introduce en JAVA 1.5

Qué es una anotación?
Una forma de inyectar código mediante el compilador de JAVA anuestras clases.
O de hacer chequeos adicionales en tiempo de Compilación.

@deprecated
@override   <<< Le decimos al compilador que revise que nuestra clase extiende a otra
clase que YA DEFINE esa función que le hemos puesto el override

                Usamos mucho hoy en día... y en el curso lo vamos a usar: Lombok

    Antes os he hablado de las numeroras cagadas que tiene JAVA en su sintaxis (gramática)
    De las peores ... y que a más gente le echa para atrás de usar JAVA son los setter y los getters
    Pero... es lo que hay.
    Bastante que tenga que usarlos... como para encima tener que escribirlos.
    Lombok es una libreria de anotaciones, que me da anotaciones superchulas

    public class Usuario {

        @Getter 
        private String name;

        @Getter @Setter
        private int age;

        @Getter @Setter
        private String email;

    }

    @Data
    public class Usuario {
        private String name;
        private int age;
        private String email;
    }

    Spring me da muchas anocaciones: 
    @Bean
    // Oye Springboot... encargate tu de esto!
    @Autowired
    // Oye Spring rellenar tu los datos de una función... o una variable


    public class ImpresorDeUsuarios{

        @Autowired
        private ServicioDeImpresion servicioDeImpresion; // Que se que es algo al que le puedo pedir .imprime(String )

        public imprimirUsuario(Usuario usuario){
            servicioDeImpresion.imprime(usuario.getName());
            servicioDeImpresion.imprime(usuario.getAge());
        }
    }

    public interface ServicioDeImpresion{
        void imprime(String);
    }

    @Service //@Repository @Component 
    public class ServicioDeImpresionGenialDeMiEmpresa implements ServicioDeImpresion {
        public void imprime(String){
            // MI IMPLEMTACION GENIAL !!!!
        }
    }

# SpringBatch

Es una librería dentro del framework Spring que:
- Me ofrece algunas anotaciones especiales para trabajar con procesos BATCH
- Me ofrece algunas clases, interfaces de uso común cuando trabaajo con procesos BATCH: Job, Step, Reader, Writer, Processor
- Me ofrece una estructura de BBDD predefinida, que yo poder desplegar en AUTO en cualquier Motor de BBDD Relacional
  para controlasr los procesos que hay en marcha, los acabados, los errores....
- Me ofrece el código para gestionar toda esa BBDD

Spring, SpringBatch, SpringBoot

## SpringBoot

Es una librería desarrollada encima de Spring que me facilita el desarrollo de una app con Spring.

## SpringBoot Starters

Básicamente son CONJUNTOS DE DEPENDENCIAS que poder usar en un proyecto.

hibernate -> Librería para darnos persistencia JPA

## Versión 1.8 -> 11 ........ -> 20

La nueva versión de Spring 3 sólo funciona a partir de Java 17
En paralelo se mantiene la versión 2.7.12, que permite trabajar con Java 8

SpringBatch 4 -> 5

## Spring


@Component      Anota una clase como proveedores de la implementación de una interfaz
De forma que cuando se solicite la INYECCION de una instancia del interfaz
Spring entregue una instancia de la clase anotada con @Component

                Tipos especiales de Component. Nos ayudana entender la NATURALEZA de una clase
                @Repository     Para implementaciones de Repositorios (objetos que se encargan de persistir datos)
                @Service        PAra implementaciones de Servicios    (objetos que aportan lógica a nivel de negocio)

Estas tres anotaciones, son las preferidas parea indicar a Spring que una clase debe
usarse para ofrecer instancias cuando sean solicitadas de una interfaz

No siempre nos son suficientes.
Eso está guay si soy YO Quien define esas clases.... PEro y si son clases de una librería que no es metodología
Entonces, definimos una función que devuelva la instancia que debe inyectarse cuando sea solicitada.
Esa función la anoto como @Bean
Y la clase que contiene funciones definidas como @Bean la anoto con @Configuration


@Autowired      Sirve para indicar a Spring que necesitamos que inyecte un dato o datos (objetos) en una clase, cuando la instancie

@SpringBootApplication
@ComponentScan
@Configuration
@EnableAutoConfiguration

## SpringBatch

Nos especializamos en Aplicaciones BATCH.

JOB: Una definición de un proceso batch
De ese Job, se irán creando instancias, para cada vez que quiera ejecutar ese proceso Batch
Los Jobs pueden tener 1 o más Steps (PASOS)

JOBLISTENER: Permite aportar lógica en distons momentos de la ejecución de un JOB: Antes del comienzo,. después de acabar...
STEPLISTENER

STEP: Un paso, una etapa, dentro de un proceso batch.
Un step está pensado para ejecutar UNA TAREA sobre un conjunto de ITEMs

        Los steps tipicamente tienen:
            - ITEMREADER            Se encarga de conseguir los datos para el Step
                    JDBC    Base de datos relacional
                    JPA     Base de datos de cualquier tipo
                    File    Fichero
            - ITEMPROCESSOR         Se encarga de procesar los datos 
            - ITEMWRITER            Se encarga de Guardar los datos en algún sitio
                    JDBC    Base de datos relacional
                    JPA     Base de datos de cualquier tipo
                    File    Fichero

JOBLAUNCHER:    Ese es el qur inicia un JOB...
- Podremos configurarlo para que sea bajo petición
- De forma programada en el tiempo

JOBREPOSITORY:  Es quién gestiona las tablas de la BBDD dedicadas a controlar los Jobs
que se han ejecutado/están ejecutándose... o están programaados para ser ejecutados




ITEM: Un dato con el quiero trabajar


----

Persona
Nombre
Apellidos
Fecha de Nacimiento
DNI
Email

    ETL ---> FICHERO CSV ------> Extracción > Transformación > Carga de datos ------> BBDD
                                 \__________________________________________/
    ETL Se ejecuta por las noches

Que tipo de proceso estamos montando en definitiva? Un proceso BATCH

Los datos de las personas los voy a sacar de un archivo CSV (Excel)
Los procesar:
- Validar DNI
- Validar el email
- Calcular la edad actual

Y una vez tenga los datos procesados, os vamos a guardar en una BBDD

Listado de cosas a configurar:
- JobLauncher, para especificar con que frecuencia queremos ejeutar los Jobs
- Job,         donde configuraremos los Steps... en nuestro caso: 1
- Ese Step:
    - Reader:         Fichero
    - Procesamiento: Las 3 cositas de ahí arriba(validaciones y calculos)
    - Writer:         Escritura
- JobRepository     Donde se va persistiendo la infromación  de los jobs que se van ejecutando/se ejecutarán
- JobListener...    Cuando acabes, manda un mensaje de alguna forma
- Item.....         Que quiero procesar
- Mappers           Cómo pasar datos de una etapa a otra        ACTUA DE BARRERA DE CONTENCION ANTE CAMBIOS

Una "Persona"... nuestro Item

Persona
Nombre
Apellidos
Fecha de Nacimiento
DNI
Email

POJO: PlainOldJavaObject
El objeto de transporte de datos... ese objeto no lleva lógica...
Y a priori no le ponemos constructor.

@Data
public class Persona {
private String nombre;
private String apellidos;
private Date fechaDeNacimiento;
private String dni;
private String email;

    private int age;
}


Una cosa son los datos que me llegan            LECTURA         *****
Otra, los datos que proceso                     PROCESAMIENTO
Y otra, los datos que guardo                    ESCRITURA       *****

Una de las cosas que queremos automatizar: LECTURA y la ESCRITURA
Vamos a tener un montón de ayudas y utilidades para estos menesteres
Ahora... una cosa es que haya utilidades... y otra cosa es el cómo esas utilidades me van a dar/necesitan los datos

CSV
nombre,apellidos,fecha_nacimiento,dni,email
----> De cada linea me van a generar un Objeto JAVA, instancia de una clase
Persona, pero tendré que decir, cómo encajo los datos
CSV         Objeto JAVA
nombre ----> nombre
apellidos -> apellidos
dni -------> dni

Cuando vaya a guardar a una BBDD... le podré decir a Java... este Objeto (instancia de una clase)
quiero que lo guardes en una TABLA: People

        Objeto JAVA (Persona)                       Tabla BBDD Relacional: People

        private String nombre; ------------------>  name
        private String apellidos;                   apellidos
        private Date fechaDeNacimiento;             birthdate
        private String dni;                         id
        private String email;                       email
    
        private int age;

        De alguna forma necesitamos indicar a JAVA la corresppondencia entre campos de:
            Lectura <> Procesamiento <> Escritura
        
        Ese trabajo se lo encargamos a Mapeador MAPPER


---
# MAVEN

Es una herramienta de automatizacion para desarrollos en JAVA

Nos ayuda con tareas comunes del proyecto:
- compilación
- empaquetado
- ejecución de pruebas automatizadas
- mandar mi código a un sonarqube
- descargar las dependencias de mi proyecto
- ...

Todo el trabajo lo hace a través de plugins que configuramos para cada proyecto
