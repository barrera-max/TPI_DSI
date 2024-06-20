package Boundary;

import DTOs.VinoDto;
import Entidades.Bodega;
import Entidades.Vino;
import Soporte.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;

public class InterfazSistemaDeBodegas {

    private List<VinoDto> vinosActualizaciones;

    // Esto debe devolver Vinos y no Bodegas
    public static List<VinoDto> buscarActualizaciones(Bodega bodega) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        try (InputStream inputStream = InterfazSistemaDeBodegas.class.getClassLoader().getResourceAsStream("vinosActualizables.json");
             InputStreamReader fileReader = new InputStreamReader(inputStream)) {

            // Leer el array de vinos directamente desde el JSON
            Type listType = new TypeToken<List<VinoDto>>() {
            }.getType();
            List<VinoDto> vinos = gson.fromJson(fileReader, listType);

            // Filtrar los vinos por el nombre de la bodega
            return vinos.stream()
                    .filter(vino -> vino.getBodega().equals(bodega.getNombre())) // Quitar porque un vino tiene solo una bodega
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
