#include <stdio.h>

struct Student {
    char name[50];
    int marks;
};

int main() {
    int n, choice;

    printf("Enter the number of students: ");
    scanf("%d", &n);

    struct Student students[n], tempArr[n];

    for (int i = 0; i < n; i++) {
        printf("Enter name of student %d: ", i + 1);
        scanf("%49s", students[i].name);
        printf("Enter marks of student %d: ", i + 1);
        scanf("%d", &students[i].marks);
    }

    for (int i = 0; i < n; i++) {
        tempArr[i] = students[i];
    }

    printf("\nChoose sorting method:\n");
    printf("1. Modified Bubble Sort\n");
    printf("2. Insertion Sort\n");
    printf("3. Selection Sort\n");
    printf("Enter your choice: ");
    scanf("%d", &choice);

    switch (choice) {
        case 1:
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (tempArr[j].marks < tempArr[j + 1].marks) {
                        struct Student temp = tempArr[j];
                        tempArr[j] = tempArr[j + 1];
                        tempArr[j + 1] = temp;
                    }
                }
            }
            break;

        case 2:
            for (int i = 1; i < n; i++) {
                struct Student key = tempArr[i];
                int j = i - 1;
                while (j >= 0 && tempArr[j].marks < key.marks) {
                    tempArr[j + 1] = tempArr[j];
                    j--;
                }
                tempArr[j + 1] = key;
            }
            break;

        case 3:
            for (int i = 0; i < n - 1; i++) {
                int maxIndex = i;
                for (int j = i + 1; j < n; j++) {
                    if (tempArr[j].marks > tempArr[maxIndex].marks) {
                        maxIndex = j;
                    }
                }
                if (maxIndex != i) {
                    struct Student temp = tempArr[i];
                    tempArr[i] = tempArr[maxIndex];
                    tempArr[maxIndex] = temp;
                }
            }
            break;

        default:
            printf("Invalid choice. Exiting.\n");
            return 1;
    }

    printf("\nSorted Student Results (Descending Order):\n");
    printf("-----------------------------------------------------------\n");
    printf("|\tRank\t|\t  Name  \t|\tMarks\t|\n");
    printf("-----------------------------------------------------------\n");
    for (int i = 0; i < n; i++) {
        printf("|\t  %d  \t|\t %s   \t|\t%d  \t|\n", i + 1, tempArr[i].name, tempArr[i].marks);
    }
    printf("-----------------------------------------------------------\n");

    return 0;
}
