import java.util.Scanner;

public class PokemonSimulation extends ConsoleProgram {
    private PokemonImages images = new PokemonImages();

    public void run() {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Pokemon Simulator!\n");
        System.out.println("Set up your Pokemon Trainer");
        PokemonTrainer trainer1 = setupTrainer(input);
        ComputerTrainer computer = new ComputerTrainer("Computer");

        // Start the battle
        battle(trainer1, computer, input);
    }

    private PokemonTrainer setupTrainer(Scanner input) {
        System.out.print("Trainer, what is your name? ");
        String name = input.next();
        PokemonTrainer trainer = new PokemonTrainer(name);
        System.out.println("Hello " + trainer + "!\n");
        System.out.println("Choose your first Pokemon");
        setupPokemon(input, trainer);
        System.out.println("Choose your second Pokemon");
        setupPokemon(input, trainer);
        return trainer;
    }

    private void setupPokemon(Scanner input, PokemonTrainer trainer) {
        System.out.print("Enter the name of your Pokemon: ");
        String pokemonName = input.next();
        Pokemon pokemon = new Pokemon(pokemonName, images.getPokemonImage(pokemonName));
        trainer.addPokemon(pokemon);
        System.out.println("You chose:\n" + pokemon + "\n");

        while (pokemon.canLearnMoreMoves()) {
            System.out.print("Would you like to teach " + pokemon.getName() + " a new move? (yes/no): ");
            String choice = input.next();
            if (choice.equalsIgnoreCase("no")) {
                break;
            }
            System.out.print("Enter the name of the move: ");
            String moveName = input.next();
            System.out.print("How much damage does this move do? ");
            int moveDmg = input.nextInt();
            pokemon.learnMove(new Move(moveName, moveDmg));
            System.out.println(pokemon.getName() + " learned " + moveName + " (" + Math.min(moveDmg, 25) + " Damage)!");
        }
        System.out.println(pokemon.getName() + " has learned all of their moves\n");
    }

    private void battle(PokemonTrainer trainer1, PokemonTrainer computer, Scanner input) {
        System.out.println("Let the battle begin!\n");
    
        PokemonTrainer currentTrainer = trainer1;
        PokemonTrainer opponentTrainer = computer;
    
        while (!trainer1.hasLost() && !computer.hasLost()) {
            // Get the current active Pokemon for both trainers
            Pokemon currentPokemon = currentTrainer.getNextPokemon();
            Pokemon opponentPokemon = opponentTrainer.getNextPokemon();
    
            if (currentPokemon == null || opponentPokemon == null) {
                break; // One of the trainers has no more Pokemon
            }
    
            System.out.println(currentTrainer + "'s " + currentPokemon.getName() + " vs " + opponentTrainer + "'s " + opponentPokemon.getName() + "\n");
    
            // Display available moves for the current Pokemon
            System.out.println(currentPokemon.getName() + "'s Moves:");
            for (Move move : currentPokemon.getMoves()) {
                System.out.println("- " + move);
            }
    
            // Handle move selection based on the current trainer
            String moveName;
            if (currentTrainer instanceof ComputerTrainer) {
                // Computer chooses a random move
                Move chosenMove = ((ComputerTrainer) currentTrainer).chooseRandomMove();
                if (chosenMove == null) {
                    System.out.println(currentPokemon.getName() + " has no moves left!");
                    break;
                }
                moveName = chosenMove.getName();
                System.out.println(currentTrainer + " chose " + moveName + "!");
            } else {
                // Player chooses a move
                System.out.print(currentTrainer + ", choose a move for " + currentPokemon.getName() + ": ");
                moveName = input.next();
            }
    
            // Perform the attack
            if (currentPokemon.attack(opponentPokemon, moveName)) {
                System.out.println(currentPokemon.getName() + " used " + moveName + "!");
                System.out.println(opponentPokemon.getName() + " now has " + opponentPokemon.getHealth() + " health.\n");
            } else {
                System.out.println(currentPokemon.getName() + " doesn't know that move!\n");
            }
    
            // Check if the opponent's Pokemon has fainted
            if (opponentPokemon.hasFainted()) {
                System.out.println(opponentPokemon.getName() + " has fainted!\n");
                opponentTrainer.getNextPokemon(); // Switch to the next Pokemon
            }
    
            // Swap turns
            PokemonTrainer temp = currentTrainer;
            currentTrainer = opponentTrainer;
            opponentTrainer = temp;
        }
    
        // Determine the winner
        if (trainer1.hasLost()) {
            System.out.println(computer + " wins the battle!");
        } else if (computer.hasLost()) {
            System.out.println(trainer1 + " wins the battle!");
        } else {
            System.out.println("The battle ended in a draw!");
        }
    }
}
