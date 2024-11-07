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

public interface InterfazSistemaDeBodegas {

    // Instancia interna de la implementación
    APISistemaDeBodega apiSistemaDeBodega = new APISistemaDeBodega();

    // Método estático que delega a la instancia de la implementación
    static ArrayList<VinoDto> buscarActualizaciones() {
        return apiSistemaDeBodega.buscarActualizaciones();
    }
}