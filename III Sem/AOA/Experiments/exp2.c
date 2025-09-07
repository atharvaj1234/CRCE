#include <stdio.h>

struct Employee {
    char name[50];
    int experience;
};

void quickSort(struct Employee arr[], int low, int high) {
    if (low < high) {
        int pivot = arr[high].experience, i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].experience >= pivot) {
                i++;
                struct Employee temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
            }
        }
        struct Employee temp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = temp;
        int pi = i + 1;
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}

void merge(struct Employee arr[], int l, int m, int r) {
    int n1 = m - l + 1, n2 = r - m;
    struct Employee L[n1], R[n2];
    for (int i = 0; i < n1; i++) L[i] = arr[l + i];
    for (int j = 0; j < n2; j++) R[j] = arr[m + 1 + j];
    int i = 0, j = 0, k = l;
    while (i < n1 && j < n2) {
        if (L[i].experience >= R[j].experience) arr[k++] = L[i++];
        else arr[k++] = R[j++];
    }
    while (i < n1) arr[k++] = L[i++];
    while (j < n2) arr[k++] = R[j++];
}

void mergeSort(struct Employee arr[], int l, int r) {
    if (l < r) {
        int m = l + (r - l) / 2;
        mergeSort(arr, l, m);
        mergeSort(arr, m + 1, r);
        merge(arr, l, m, r);
    }
}

int main() {
    int n, choice;

    printf("Enter number of employees: ");
    scanf("%d", &n);
    if (n <= 0) return 1;
    
    struct Employee employees[n], temp[n];

    for (int i = 0; i < n; i++) {
        printf("Enter name of employee %d: ", i + 1);
        scanf("%49s", employees[i].name);
        printf("Enter experience of employee %d: ", i + 1);
        scanf("%d", &employees[i].experience);
    }

    for (int i = 0; i < n; i++) temp[i] = employees[i];

    printf("\nChoose sorting method:\n");
    printf("1. Quick Sort\n");
    printf("2. Merge Sort\n");
    printf("Enter choice: ");
    scanf("%1d", &choice);

    if (choice == 1) quickSort(temp, 0, n - 1);
    else if (choice == 2) mergeSort(temp, 0, n - 1);
    else {
        printf("Invalid choice.\n");
        return 1;
    }

    printf("\nSorted Employee Records (Descending by Experience):\n");
    printf("-----------------------------------------------------------\n");
    printf("|\tRank\t|\tName\t\t|\tExperience\t|\n");
    printf("-----------------------------------------------------------\n");
    for (int i = 0; i < n; i++) {
        printf("|\t%2d\t\t|\t%-10s\t|\t%3d years\t|\n", i + 1, temp[i].name, temp[i].experience);
    }
    printf("-----------------------------------------------------------\n");

    return 0;
}
