package presenter;

import model.entity.Bakery;
import model.repository.BakeryRepository;
import model.repository.BakeryRepositoryImpl;

import java.util.List;

public class BakeryPresenter {

    private final BakeryRepository bakeryRepository;
    private final BakeryView view;

    public BakeryPresenter( BakeryView view) {
        this.bakeryRepository = new BakeryRepositoryImpl();
        this.view = view;
    }

    public void loadBakeries() {
        List<Bakery> bakeries = bakeryRepository.findAll();
        view.showBakeries(bakeries);
    }

    public void handleAddBakery(String name, String address) {

        if (name == null || name.isEmpty()) {
            view.showError("Name cannot be empty!");
            return;
        }

        Bakery bakery = new Bakery();
        bakery.setName(name);
        bakery.setAddress(address);

        bakeryRepository.add(bakery);
        loadBakeries();
    }

    public void handleUpdateBakery(int id, String name, String address) {

        Bakery bakery = new Bakery();
        bakery.setId(id);
        bakery.setName(name);
        bakery.setAddress(address);


        bakeryRepository.update(bakery);
        loadBakeries();
    }

    public void handleDeleteBakery(int id) {
        bakeryRepository.delete(id);
        loadBakeries();
    }
}
