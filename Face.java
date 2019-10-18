import java.util.Random;

public enum Face {
    FISH("Fish"), 
    PRAWN("Prawn"), 
    CRAB("Crab"), 
    ROOSTER("Rooster"), 
    GOURD("Gourd"), 
    STAG("Stag");

    
    private static Random random = new Random();
    
    private final String name;

    
    
    private Face(String name) {
        this.name = name;
    }
 
    
    
    public static Face getByIndex(int index) {
        Face[] faces = Face.values();
        Face face = faces[index];
        return face;
    }

    
    
    public static Face getRandom() {
        Face[] faces = Face.values();
        int len = faces.length ;
        int rand = random.nextInt(len);
        int index = rand % len ;
        Face face = faces[index];
        return face;
    }

    
    
    String getName() {
        return name;
    }

    
    
    public String toString() {
        return name;
    }
}
