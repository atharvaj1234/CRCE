#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>

#define INF 99999

// ---------- Bellman-Ford Algorithm ----------
struct Edge {
    int u, v, w;
};

void bellmanFord(int V, int E, struct Edge edges[], int src) {
    int distance[V];
    
    // Initialize distances
    for (int i = 0; i < V; i++)
        distance[i] = INF;
    distance[src] = 0;

    // Relax edges V-1 times
    for (int i = 1; i <= V - 1; i++) {
        for (int j = 0; j < E; j++) {
            int u = edges[j].u;
            int v = edges[j].v;
            int w = edges[j].w;

            if (distance[u] != INF && distance[u] + w < distance[v])
                distance[v] = distance[u] + w;
        }
    }

    // Check for negative cycles
    for (int j = 0; j < E; j++) {
        int u = edges[j].u;
        int v = edges[j].v;
        int w = edges[j].w;

        if (distance[u] != INF && distance[u] + w < distance[v]) {
            printf("Graph contains a negative weight cycle!\n");
            return;
        }
    }

    // Output shortest distances
    printf("\nShortest distances from source %d:\n", src);
    for (int i = 0; i < V; i++)
        printf("Vertex %d: %d\n", i, distance[i]);
}

// ---------- Longest Common Subsequence (LCS) ----------
int max(int a, int b) {
    return (a > b) ? a : b;
}

void LCS(char seq1[], char seq2[]) {
    int m = strlen(seq1);
    int n = strlen(seq2);
    int dp[m + 1][n + 1];

    // Initialize dp table
    for (int i = 0; i <= m; i++)
        for (int j = 0; j <= n; j++)
            dp[i][j] = 0;

    // Fill dp matrix
    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (seq1[i - 1] == seq2[j - 1])
                dp[i][j] = dp[i - 1][j - 1] + 1;
            else
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
        }
    }

    // Output LCS length
    printf("\nLength of Longest Common Subsequence: %d\n", dp[m][n]);

    // Traceback to reconstruct LCS
    int index = dp[m][n];
    char lcs[index + 1];
    lcs[index] = '\0';

    int i = m, j = n;
    while (i > 0 && j > 0) {
        if (seq1[i - 1] == seq2[j - 1]) {
            lcs[index - 1] = seq1[i - 1];
            i--;
            j--;
            index--;
        } else if (dp[i - 1][j] > dp[i][j - 1])
            i--;
        else
            j--;
    }

    printf("LCS: %s\n", lcs);
}

// ---------- Main Function ----------
int main() {
    int V, E, src;
    printf("Enter number of vertices and edges: ");
    scanf("%d %d", &V, &E);

    struct Edge edges[E];
    printf("Enter edges (source destination weight):\n");
    for (int i = 0; i < E; i++)
        scanf("%d %d %d", &edges[i].u, &edges[i].v, &edges[i].w);

    printf("Enter source vertex: ");
    scanf("%d", &src);

    bellmanFord(V, E, edges, src);

    char seq1[100], seq2[100];
    printf("\nEnter first DNA/RNA sequence: ");
    scanf("%s", seq1);
    printf("Enter second DNA/RNA sequence: ");
    scanf("%s", seq2);

    LCS(seq1, seq2);
    return 0;
}
