package businessLayer;

import java.util.Objects;

/**
 * @author Tarta Manuel
 * Clasa MenuItem care e clasa generala pentru produse. Un MenuItem poate fi fie BaseProduct, fie Composite. Le tratez
 * la fel. Contine cele 7 campuri specifice pentru un produs, plus numarul de comenzi in care apare acest produs, un camp
 * care ma ajuta la generarea rapoartelor.
 * Am dat override la metoda equals si hashCode pentru a putea folosi distinct la streams.
 */
public class MenuItem implements java.io.Serializable{
    private String title;
    private float rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;

    private int nrComenzi;

    public MenuItem(String title,float rating,int calories, int protein,int fat,int sodium,int price){
        this.title=title;
        this.rating=rating;
        this.calories=calories;
        this.protein=protein;
        this.fat=fat;
        this.sodium=sodium;
        this.price=price;
    }

    public MenuItem(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return Objects.equals(title, menuItem.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    public String printMenuItems(){
        return new String(getTitle()+", Rating:"+getRating()+", Calories:"+getCalories()+", Protein:"+getProtein()
                +", Fat:"+getFat()+", Sodium:"+getSodium()+", Price:"+getPrice());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNrComenzi() {
        return nrComenzi;
    }

    public void setNrComenzi(int n) {
        this.nrComenzi = n;
    }
}
