package Boundary;

import DTOs.VinoDto;
import Entidades.Bodega;
import Soporte.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InterfazSistemaDeBodegas {

    public static ArrayList<VinoDto> buscarActualizaciones(Bodega bodega) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        try (InputStream inputStream = InterfazSistemaDeBodegas.class.getClassLoader().getResourceAsStream("vinosActualizables.json");
             InputStreamReader fileReader = new InputStreamReader(inputStream)) {

            // Leer el array de vinos directamente desde el JSON
            Type listType = new TypeToken<ArrayList<VinoDto>>() {
            }.getType();
            ArrayList<VinoDto> vinos = gson.fromJson(fileReader, listType);

            return (ArrayList<VinoDto>) vinos.stream()
                    .filter(vino -> vino.getBodega().equals(bodega.getNombre())) // Quitar porque un vino tiene solo una bodega
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
