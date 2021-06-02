package businessLayer;

/**
 * @author Tarta Manuel
 * O clasa simpla ce reprezinta un BaseProduct. Nu are niciun camp pentru ca le mosteneste din MenuItems.
 * Pentru constructor, apelez constructorul din MenuItems.
 */
public class BaseProduct extends MenuItem{


    public BaseProduct(){

    }
    public BaseProduct(String title,float rating,int calories, int protein,int fat,int sodium,int price){
        super(title,rating,calories,protein,fat,sodium,price);
    }

}
