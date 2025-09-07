#include <stdio.h>
#include <string.h>

#define MAX 100

struct Knapsack {
    int p;           // profit
    int w;           // weight
    char name[20];   // item name
};

struct Knapsack knapsack[MAX];

int cap = 0, noe = 0;

void sort(char element[][20], int p[], int w[], float ptwr[]) {
    int i, j, temp;
    char name[20];
    float tempRatio;

    for (i = 0; i < noe - 1; i++) {
        for (j = 0; j < noe - i - 1; j++) {
            if (ptwr[j] < ptwr[j + 1]) {
                // Swap names
                strcpy(name, element[j]);
                strcpy(element[j], element[j + 1]);
                strcpy(element[j + 1], name);

                // Swap profits
                temp = p[j];
                p[j] = p[j + 1];
                p[j + 1] = temp;

                // Swap weights
                temp = w[j];
                w[j] = w[j + 1];
                w[j + 1] = temp;

                // Swap ratios
                tempRatio = ptwr[j];
                ptwr[j] = ptwr[j + 1];
                ptwr[j + 1] = tempRatio;
            }
        }
    }
}

int main() {
    int weight = 0, profit = 0, i;

    printf("Enter knapsack capacity: ");
    scanf("%d", &cap);

    printf("Enter number of elements: ");
    scanf("%d", &noe);

    char elements[noe][20];
    int p[noe];
    int w[noe];
    float ptwr[noe];

    for (i = 0; i < noe; i++) {
        printf("Enter profit, weight and name of element %d: ", i + 1);
        scanf("%d %d %s", &p[i], &w[i], elements[i]);
        ptwr[i] = (float)p[i] / w[i];
    }

    sort(elements, p, w, ptwr);

    printf("\nItems in Knapsack:\n");
    printf("Name\tProfit\tWeight\n");

    for (i = 0; i < noe; i++) {
        
        if(weight + w[i] <= cap)
        {
        knapsack[i].p = p[i];
        knapsack[i].w = w[i];
        strcpy(knapsack[i].name, elements[i]);

        weight += w[i];
        profit += p[i];

        printf("%s\t%d\t%d\n", elements[i], p[i], w[i]);
        }
    }

    printf("\nTotal Profit: %d\n", profit);
    printf("Total Weight: %d\n", weight);

    return 0;
}

/*
### FRACTIONAL KNAPSCAK


#include <stdio.h>
#include <string.h>

#define MAX 100

struct Knapsack {
    float p;         // profit
    float w;         // weight
    char name[20];   // item name
};

struct Knapsack knapsack[MAX];

int cap = 0, noe = 0;

void sort(char element[][20], int p[], int w[], float ptwr[]) {
    int i, j, temp;
    char name[20];
    float tempRatio;

    for (i = 0; i < noe - 1; i++) {
        for (j = 0; j < noe - i - 1; j++) {
            if (ptwr[j] < ptwr[j + 1]) {
                // Swap names
                strcpy(name, element[j]);
                strcpy(element[j], element[j + 1]);
                strcpy(element[j + 1], name);

                // Swap profits
                temp = p[j];
                p[j] = p[j + 1];
                p[j + 1] = temp;

                // Swap weights
                temp = w[j];
                w[j] = w[j + 1];
                w[j + 1] = temp;

                // Swap ratios
                tempRatio = ptwr[j];
                ptwr[j] = ptwr[j + 1];
                ptwr[j + 1] = tempRatio;
            }
        }
    }
}

int main() {
    float weight = 0, profit = 0;
    int i;

    printf("Enter knapsack capacity: ");
    scanf("%d", &cap);

    printf("Enter number of elements: ");
    scanf("%d", &noe);

    char elements[noe][20];
    int p[noe];
    int w[noe];
    float ptwr[noe];

    for (i = 0; i < noe; i++) {
        printf("Enter profit, weight and name of element %d: ", i + 1);
        scanf("%d %d %s", &p[i], &w[i], elements[i]);
        ptwr[i] = (float)p[i] / w[i];
    }

    sort(elements, p, w, ptwr);

    printf("\nItems in Knapsack:\n");
    printf("Name\tProfit\tWeight (Taken)\n");

    for (i = 0; i < noe; i++) {
        if (weight + w[i] <= cap) {
            // Take full item
            knapsack[i].p = p[i];
            knapsack[i].w = w[i];
            strcpy(knapsack[i].name, elements[i]);

            weight += w[i];
            profit += p[i];

            printf("%s\t%d\t%d (full)\n", elements[i], p[i], w[i]);
        } else {
            // Take fractional part
            float remain = cap - weight;
            if (remain > 0) {
                float fraction = remain / w[i];
                knapsack[i].p = p[i] * fraction;
                knapsack[i].w = remain;
                strcpy(knapsack[i].name, elements[i]);

                weight += remain;
                profit += p[i] * fraction;

                printf("%s\t%.2f\t%.2f (fractional)\n", elements[i], p[i] * fraction, remain);
            }
            break; // Knapsack is full
        }
    }

    printf("\nTotal Profit: %.2f\n", profit);
    printf("Total Weight: %.2f\n", weight);

    return 0;
}

*/