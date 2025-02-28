import java.util.ArrayList;

public class Pokemon
{
    // Private constants
    private static final int MAX_HEALTH = 100;
    private static final int MAX_MOVES = 4;
    
    // Write your Pokemon class here
    private String name;
    private int health;
    private ArrayList<Move> moves;
    private String image;
    
    public Pokemon (String name, int health) {
        this.name = name;
        this.health = Math.min(health, MAX_HEALTH);
        this.moves = new ArrayList<>();
    }
    
    public Pokemon (String name) {
        this.name = name;
        this.health = MAX_HEALTH;
        this.moves = new ArrayList<>();
    }
    
    public Pokemon(String name, String image) {
        this.name = name;
        this.image = image;
        this.health = MAX_HEALTH;
        this.moves = new ArrayList<>();
    }
    
    // Returns an ArrayList of all the Moves this Pokemon knows
    public ArrayList<Move> getMoves() {
        return moves;
    }
    
    // Returns true if the Pokemon knows this move (has this Move
    // in its collection), false otherwise
    public boolean knowsMove(Move move) {
        return moves.contains(move);
    }
    
    // Returns true if the Pokemon knows a Move with
    // the name `moveName`, false otherwise
    public boolean knowsMove(String moveName) {
        for (Move move : moves) {
            if (move.getName().equals(moveName)) {
                return true;
            }
        }
        return false;
    }
    
    /* 
     * Tries to perform the Move `move` on the Pokemon `other`
     * A Pokemon can only use a Move if it knows the Move
     * An attack should subtract the damage of the Move 
     * from the health of the opponent.
     * Returns true if the Pokemon knew the Move and used it
     * Returns false otherwise.
     */
    public boolean attack(Pokemon opponent, Move move) {
        if (knowsMove(move)) {
            int newHealth = opponent.getHealth() - move.getDamage();
            opponent.setHealth(Math.max(newHealth, 0));
            return true;
        }
        return false;
    }
    
    /* 
     * Tries to perform a Move with the name `moveName` on
     * the Pokemon `other`
     * A Pokemon can only use a Move if it knows the Move
     * An attack should subtract the damage of the Move 
     * from the health of the opponent.
     * Returns true if the Pokemon knew the Move and used it
     * Returns false otherwise.
     */
    public boolean attack(Pokemon opponent, String moveName) {
        if (knowsMove(moveName)) {
            for (Move move : moves) {
                if (move.getName().equals(moveName)) {
                    int newHealth = opponent.getHealth() - move.getDamage();
                    opponent.setHealth(Math.max(newHealth, 0));
                    return true;
                }
            }
        }
        return false;
    }
    
    // Sets this Pokemon's image to be `image`
    public void setImage(String image) {
        this.image = image;
    }

    // Returns the ASCII Art image for this Pokemon
    public String getImage() {
        return image;
    }
    
    // Returns the name of the Pokemon
    public String getName() {
        return name;
    }
    
    // Returns how much health this Pokemon has
    public int getHealth() {
        return health;
    }
    
    public void setHealth(int health) {
        this.health = Math.min(health, MAX_HEALTH);
    }
    
    /*
     * Returns true if this Pokemon has fainted,
     * False otherwise. A Pokemon faints when their health is
     * less than or equal to 0
     */
    public boolean hasFainted() {
        return health <= 0;
    }
    
    /*
     * A Pokemon can only know 4 Moves. This method 
     * returns true if this Pokemon can still learn more
     * Moves, returns false otherwise.
     */
    public boolean canLearnMoreMoves() {
        return moves.size() < MAX_MOVES;
    }
    
    /*
     * Adds the Move `move` to the collection of Moves
     * that this Pokemon knows. Returns true if the Move was 
     * successfully added. Returns false if the Pokemon already
     * knew 4 Moves and couldn't learn a new one.
     */
    public boolean learnMove(Move move) {
        if (canLearnMoreMoves()) {
            moves.add(move);
            return true;
        }
        return false;
    }
    
    /*
     * Remove the Move `move` from this Pokemon's
     * collection of Moves, if it's there.
     */
    public void forgetMove(Move move) {
        moves.remove(move);
    }
    
    /*
     * Return a String containing the name and health
     * of this Pokemon
     * Example: "Pikachu (Health: 85 / 100)"
     */
    public String toString() {
        return image + "\n" + name + " (Health: " + health + " / 100)";
    }
    
}
