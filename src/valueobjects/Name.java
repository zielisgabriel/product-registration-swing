package valueobjects;

public class Name {
    private String name;
    
    public Name(String name) {
        this.name = name;
    }

    public void validate() throws Exception {
        if (this.name.trim().length() == 0) {
            throw new Exception("Name not valided");
        }
    }

    public String getName() {
        return name;
    }
}
