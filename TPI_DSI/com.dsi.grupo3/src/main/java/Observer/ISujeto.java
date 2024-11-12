package Observer;

import java.util.ArrayList;
import java.util.List;

public interface ISujeto {

    List<IObserverNotiActualizacion> observadores = new ArrayList<IObserverNotiActualizacion>();

    void suscribir(IObserverNotiActualizacion observer);

    void desuscribir(IObserverNotiActualizacion observer);

    void notificar();
}
