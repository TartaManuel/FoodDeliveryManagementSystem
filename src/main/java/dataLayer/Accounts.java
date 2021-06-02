package dataLayer;

/**
 * @author Tarta Manuel
 * O clasa generica, care contine datele pentru useri. Aceasta clasa va fi extinsa de clasele Admin, Client si Employee
 * pentru a avea aceste fielduri impreuna cu setterii si getterii.
 * Am despartit clasele Admin de AdminOperation de exemplu doar pentru a fi mai clara diferenta intre date si operatii,
 * in operatii neavand nevoie de datele specifice userilor.
 */
public class Accounts implements java.io.Serializable{

    private int Id;
    private String username;
    private String parola;
    private String email;

    Accounts(int Id,String username, String parola, String email){
        this.Id=Id;
        this.username=username;
        this.parola=parola;
        this.email=email;
    }

    public Accounts(){

    }
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
