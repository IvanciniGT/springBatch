package com.curso.job.mappers;

import com.curso.models.Persona;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

// TODO: PASO 3
// Montar un mapeador desde lo que leo en el fichero CSV al Objeto Persona que genero
@Component                                                                    /// (1)
public class PersonaReaderMapper extends BeanWrapperFieldSetMapper<Persona> {

        public PersonaReaderMapper() {
                         // (2)
            setTargetType(Persona.class);
        }

        // Si algun dia los nombres que uso para leer el csv no encajan con los de mi clase, puedo reimplementar esta función
        /* @Override
        public Persona mapFieldSet(FieldSet fieldSet) {
            Persona p = new Persona();
            p.setNombre(fieldSet.readString("nombre"));
            p.setApellidos(fieldSet.readString("apellidos"));
            p.setDni(fieldSet.readString("dni"));
            p.setEmail(fieldSet.readString("email"));
            return p;
        }*/
}

// Le llega un FiledSet: Es algo así com oun Map
/** Esto es la implementación de una función que yan nos da BeanWrapperFieldSetMapper:
 *
* // FieldSet { nombre: "Juan", apellido: "Perez", dni: "12345678Z", email: "federico@gmail.com"}
* Persona(1) p = new Persona() (2);
* p.setNombre(fieldSet.get("nombre"));
* p.setApellido(fieldSet.get("apellido"));
* p.setDni(fieldSet.get("dni"));
* p.setEmail(fieldSet.get("email"));
* return p;
 *
* */