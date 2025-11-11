#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <windows.h> // for Windows API calls
#include <mmsystem.h> // for mciSendStringA
#pragma comment(lib, "winmm.lib") // Required for mciSendStringA

// --- Global State and Constants ---
// MCI_ALIAS is defined as a const char* to be used in sprintf
const char *MCI_ALIAS = "my_mp3_player"; 

enum PlaybackState { IDLE, PLAYING, PAUSED };
enum PlaybackState playerState = IDLE;

// --- Circular Linked List Structure (Unchanged) ---
struct Node {
    char songName[100];
    struct Node *next;
    struct Node *prev;
};

struct Node *head = NULL;
struct Node *current = NULL;

// Function to create a new node
struct Node* createNode(char *song) {
    struct Node *newNode = (struct Node*)malloc(sizeof(struct Node));
    strcpy(newNode->songName, song);
    newNode->next = newNode->prev = newNode; 
    return newNode;
}

// Function to insert a song
void insertSong(char *song) {
    struct Node *newNode = createNode(song);
    if (head == NULL) {
        head = newNode;
        current = head;
    } else {
        struct Node *last = head->prev;
        last->next = newNode;
        newNode->prev = last;
        newNode->next = head;
        head->prev = newNode;
    }
}

// --- MCI Playback Functions (FIXED) ---

// Stops the current song and closes the MCI device
void mci_stop_and_close() {
    if (playerState != IDLE) {
        char command[64]; // Local buffer for commands
        
        // FIX: Use sprintf to correctly construct the command string
        sprintf(command, "stop %s", MCI_ALIAS);
        mciSendStringA(command, NULL, 0, NULL);
        
        sprintf(command, "close %s", MCI_ALIAS);
        mciSendStringA(command, NULL, 0, NULL);
        
        playerState = IDLE;
    }
}

// Opens a new song file in the MCI device
int mci_open_song(const char *songName) {
    char command[256];
    
    // 1. Close any existing device first
    mci_stop_and_close(); 

    // 2. Open the new file
    // The format string must be built with the alias
    sprintf(command, "open \"%s\" type mpegvideo alias %s", songName, MCI_ALIAS);
    if (mciSendStringA(command, NULL, 0, NULL) != 0) {
        printf("Error: Could not open song file: %s. Check if the file exists.\n", songName);
        return 0; // Failure
    }
    return 1; // Success
}

// Plays or resumes the current song
void playSong() {
    if (current == NULL) {
        printf("No songs in the playlist.\n");
        return;
    }
    
    char command[64]; // Local buffer for play/pause commands

    if (playerState == IDLE) {
        // New song or player was stopped/closed
        if (mci_open_song(current->songName)) {
            // FIX: Use sprintf to correctly construct the command string
            sprintf(command, "play %s", MCI_ALIAS);
            mciSendStringA(command, NULL, 0, NULL);
            printf("Now Playing: %s\n", current->songName);
            playerState = PLAYING;
        }
    } else if (playerState == PAUSED) {
        // Resume from pause
        // FIX: Use sprintf to correctly construct the command string
        sprintf(command, "play %s", MCI_ALIAS);
        mciSendStringA(command, NULL, 0, NULL);
        printf("Resuming: %s\n", current->songName);
        playerState = PLAYING;
    } else if (playerState == PLAYING) {
        printf("Already playing: %s\n", current->songName);
    }
}

// Pauses the current song
void pauseSong() {
    if (playerState == PLAYING) {
        char command[64]; // Local buffer for pause command
        // FIX: Use sprintf to correctly construct the command string
        sprintf(command, "pause %s", MCI_ALIAS);
        mciSendStringA(command, NULL, 0, NULL);
        
        printf("Pausing: %s. You can resume it with option 1.\n", current->songName);
        playerState = PAUSED;
    } else if (playerState == PAUSED) {
        printf("Song is already paused.\n");
    } else {
        printf("Nothing is currently playing to pause.\n");
    }
}

// Stops the current song (equivalent to the old stop, but clean)
void stopSong() {
    if (playerState != IDLE) {
        mci_stop_and_close();
        printf("Song stopped and device closed.\n");
    } else {
        printf("Nothing is currently playing to stop.\n");
    }
}


// Move to next song
void nextSong() {
    if (current == NULL) {
        printf("No songs available.\n");
        return;
    }
    mci_stop_and_close(); // Stop and close the old song's device
    current = current->next;
    playSong();
}

// Move to previous song
void prevSong() {
    if (current == NULL) {
        printf("No songs available.\n");
        return;
    }
    mci_stop_and_close(); // Stop and close the old song's device
    current = current->prev;
    playSong();
}

int main() {
    int choice;

    // Add songs to playlist (ensure these files are in the same directory)
    insertSong("Perfect.mp3");
    insertSong("Rewrite_The_Stars.mp3");
    insertSong("I_Wanna_Be_Yours.mp3");

    while (1) {
        printf("\n=== Circular Linked List Music Player (MCI) ===\n");
        printf("1. Play/Resume Current Song\n");
        printf("2. Pause Song\n");
        printf("3. Stop and Reset Song\n");
        printf("4. Next Song\n");
        printf("5. Previous Song\n");
        printf("6. Exit\n");
        printf("Enter your choice: ");
        
        // Input validation
        if (scanf("%d", &choice) != 1) {
            printf("Invalid input! Please enter a number.\n");
            while (getchar() != '\n'); // Clear buffer
            continue;
        }
        getchar(); // consume the newline

        switch (choice) {
            case 1:
                playSong(); // Plays or Resumes
                break;
            case 2:
                pauseSong(); // Pauses, keeps position for resume
                break;
            case 3:
                stopSong(); // Stops and closes device, play will start from beginning
                break;
            case 4:
                nextSong();
                break;
            case 5:
                prevSong();
                break;
            case 6:
                printf("Exiting player.\n");
                mci_stop_and_close(); // Ensure device is closed on exit
                return 0;
            default:
                printf("Invalid choice! Try again.\n");
        }
    }

    return 0;
}

// Compile: "C:\MinGW\bin\gcc.exe" "c:\Users\Atharva\Documents\CRCE\III Sem\DS\mini_project\atharva\music.c" -o music.exe -lwinmm  