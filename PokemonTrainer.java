import java.util.ArrayList;

public class PokemonTrainer
{
    // private constants
    protected static final int MAX_POKEMON = 2;
    private String name;
    private ArrayList<Pokemon> pokemon;
    // Write your PokemonTrainer class here
    public PokemonTrainer(String name) {
        this.name = name;
        this.pokemon = new ArrayList<>(); // Initialize an empty list of Pokemon
    }
    
    public ArrayList<Pokemon> getPokemon() {
        return pokemon;
    }
    
    /*
     * Adds Pokemon p to the PokemonTrainer's collection of Pokemon.
     * A Player is only allowed MAX_POKEMON Pokemon, so this method
     * will return true if there was room for the new Pokemon and
     * it was successfully added, false if there was no room for the
     * new Pokemon.
     */
    public boolean addPokemon(Pokemon p) {
        if (pokemon.size() < MAX_POKEMON) {
            pokemon.add(p);
            return true;
        }
        return false;
        
    }
    
    /*
     * Returns true if all of the PokemonTrainer's Pokemon
     * have fainted, false otherwise.
     */
    public boolean hasLost() {
        for (Pokemon p : pokemon) {
            if (!p.hasFainted()) {
                return false; // At least one Pokemon has not fainted
            }
        }
        return true; // All Pokemon have fainted
    }
    
    /*
     * Returns the first Pokemon that has not yet fainted
     * from this PokemonTrainer's collection of Pokemon.
     * If every Pokemon has fainted, this method returns null.
     */
    public Pokemon getNextPokemon() {
        for (Pokemon p : pokemon) {
            if (!p.hasFainted()) {
                return p; // Return the first non-fainted Pokemon
            }
        }
        return null; // All Pokemon have fainted
    }
    
    // Returns this PokemonTrainer's name
    public String toString() {
        return name;
    }
    
}
