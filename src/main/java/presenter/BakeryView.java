package presenter;

import model.entity.Bakery;
import java.util.List;

public interface BakeryView {
    void showBakeries(List<Bakery> bakeries);
    void showError(String message);
}
//trebuie sa nu se foloseasca de nimic din model asa ca nu pot folosii lista de Bakery, etc