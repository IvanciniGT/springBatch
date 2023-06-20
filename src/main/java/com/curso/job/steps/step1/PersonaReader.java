package com.curso.job.steps.step1;

import com.curso.job.mappers.PersonaReaderMapper;
import com.curso.models.Persona;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
// ^ Spring!!!
// Que esta clase contiene funciones que devuelven beans
// Empápate!
public class PersonaReader {

    @Value("${fichero.personas}")
    // Spring rellename esta propiedad con el dato que lees del fichero application.properties llamado fichero.personas
    private String ficheroCCSVConLasPersonas;

    // TODO: PASO 2
    // Montamos una función que devuelve el lector del fichero de personas
    @Bean
    // ^^ Spring!!!! Empápate!
    // Necesito que cuando alguien pida un ItemReader<Persona> le enchufes
    // La instancia que devuelve ésta función de aquí abajo..
    // A la que he puesto la anotación @Bean
    public ItemReader<Persona> generarElReaderDePersonas(PersonaReaderMapper mapeadorCSV2Persona){
                                                        // Es una variante del @Autowired
                                                        // Le decimos a Spring que cuando él llame a esta función
                                                        // Nos dé una instancia de PersonaReaderMapper
        return new FlatFileItemReaderBuilder<Persona>()
                .name("personasItemReader")
                .resource(new ClassPathResource(ficheroCCSVConLasPersonas))
                .delimited() // Es un fichero csv
                .names("nombre", "apellidos", "dni", "email") // Campos que encuentras en el fichero
                .fieldSetMapper( mapeadorCSV2Persona )
                .build();
    }

}
