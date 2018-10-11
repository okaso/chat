package app.ice.logingmail;

public class Notes {

    public String id;
    public String name;
    public String count;
    public Notes(){
        super();
    }
    public Notes(String id,String name, String count){
        this.count=count;
        this.id=id;
        this.name=name;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

}
