package Boundary;

import DTOs.VinoDto;
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

public class APISistemaDeBodega implements InterfazSistemaDeBodegas{


    public ArrayList<VinoDto> buscarActualizaciones() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        try (InputStream inputStream = InterfazSistemaDeBodegas.class.getClassLoader().getResourceAsStream("vinosActualizables.json");
             InputStreamReader fileReader = new InputStreamReader(inputStream)) {
            // Leer el array de vinos directamente desde el JSON
            Type listType = new TypeToken<ArrayList<VinoDto>>() {
            }.getType();
            ArrayList<VinoDto> vinos = gson.fromJson(fileReader, listType);
            return vinos;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
