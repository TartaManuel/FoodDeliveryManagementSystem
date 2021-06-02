package businessLayer;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem{

    ArrayList<BaseProduct> compositeProducts=new ArrayList<>();//lista de produse

    public CompositeProduct(){

    }

    public void addProducts(ArrayList<BaseProduct> p,String title){
        setTitle(title);
        for(BaseProduct pr:p){
            pr.printMenuItems();
            compositeProducts.add(pr);
            setRating(getRating()+pr.getRating());
            setCalories(getCalories()+pr.getCalories());
            setProtein(getProtein()+pr.getProtein());
            setFat(getFat()+pr.getFat());
            setSodium(getSodium()+pr.getSodium());
            setPrice(getPrice()+pr.getPrice());
        }
        setRating(getRating()/p.size());
    }

    public ArrayList<BaseProduct> getCompositeProducts() {
        return compositeProducts;
    }
}
