package fr.shall0wer.tntrun.state;

public enum GameState {

    WAITING, // Les joueurs attendent le nombre requis
    PRESTARTING, // Dès que le minimum de joueurs est atteint
    STARTING, // Dès le lancement de la partie dans 5 secondes
    RUNNING, // En jeu
    TERMINATED; // Partie terminée
}
