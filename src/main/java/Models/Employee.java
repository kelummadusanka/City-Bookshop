package Models;

public abstract class Employee {
    protected String name;
    protected int id;
    protected String type;

    public Employee(int id, String name, String type) {
        this.name = name;
        this.id = id;
        this.type = type;
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public String getAccountType(){
        return type;
    }
    public void setAccountType(String type){
        this.type = type;
    }
}


