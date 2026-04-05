package presenter;

import java.util.List;

public interface MainView {
    void showBakeries(List<String> bakeries);
    void showCakes(List<String> cakes);
    void showStocks(List<String> stocks);
    void showError(String message);
    void showMessage(String message);
    void fillCakeFields(String id, String name, String price, String imagePath);
    void showCakeImage(String imagePath);
}