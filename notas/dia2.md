En algún sitio habrá una variable a la que quiero que Spring le asigne el valor.
Esa variable la podré tener definida como propiedad de una clase

    // import MICLASE; ESTO ES LO QUE ME QUIERO AHORRAR... La dependencia.
    // Y al hacerlo, genero componentes DESACOPLADOS: TACHAN !!!!!

    class FEDERICO {
        @Autowired
        private LOQUESEA variable //  En la opción 2, éste es el código que ejecuta Spring:             = new MICLASE().miFuncion();
                                  // En la opción 1, el código que ejecuta Spring es:                   = new LOQUESEA();      

    }

                                                                        ^^^^ Eso realmente no es cierto
                                                                        Spring instancia UNA UNICA VEZ las clases, no cada vez que se piden
                                                                        PAra mi, es como si hubiera definido esas clases como un SINGLETON

        // TRUCOS, y más:
        1º: AUTOWIRED ESTA DEPRECATED... no oficialmente ... pero está considerado una mala práctica hoy en día
            Alternativa:

                class FEDERICO {
                    private LOQUESEA variable;
                    public FEDERICO(LOQUESEA variable){ // Esta función (El constructor) Es a la que llama Spring)... y ahí me inyecta la variable sin necesidad de AUTOWIRES
                        this.variable = variable;
                    }   
                }
            
        
o bien la podré solicitar en alguna función:

    public void miFuncion(LOQUESEA variable){
        ...
    }
        OJO: Aquí solo se inyectará el valor si la función es invocada por Spring.
             Pregunta es: Qué funciones invoca Spring???
                - Los constructores
                - Las que defina com o @Bean en una clase anotada con @Configuration

La pregunta que me tengo que hacer es:

OPCION 1: QUIERO que Spring suministre una instancia de una clase que yo he definido para asignarle el valor a la variable.

    @Component   // Opcionalmente, para aportar valor SEMANTICO (a mi como humano) podré optar por usar @Service, @Repository, @Controller
    class LOQUESEA{
        ...
    }

OPCION 2: QUIERO que Spring llame a una función que yo he definido... y lo que devuelva esa función será el valor que se asignará a la variable.

    @Configuration
    class MICLASE {
        
                            Ese nombre es anecdótico... 
        @Bean               vvv
        public LOQUESEA miFuncion(){
            ...
            return new LOQUESEA();
        }

    }

---

class MiSingleton{
    private static MiSingleton instancia = null;
    public static MiSingleton getInstancia(){
        if(instancia == null){
            synchronized(MiSingleton.class){
                if(instancia == null){
                    instancia = new MiSingleton();
                }
            }
        }
        return instancia;
    }

    private MiSingleton(){
        ...
    }

}