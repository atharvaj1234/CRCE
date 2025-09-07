#include <stdio.h>

int n;

void sort(int coins[])
{
    int i, j, max_idx, temp;
    for(i = 0; i < n - 1; i++)
    {
        max_idx = i;
        for(j = i + 1; j < n; j++)
        {
            if(coins[j] > coins[max_idx])
                max_idx = j;
        }
        temp = coins[i];
        coins[i] = coins[max_idx];
        coins[max_idx] = temp;
    }
}

int main() {
    int i, amount, remaining_amount;
    
    printf("Enter number of coins: ");
    scanf("%d", &n);
    
    int coins[n];
    
    printf("Enter coins:");
    for(i=0;i<n;i++)
    {
        scanf("%d", &coins[i]);
    }
    
    printf("Enter amount: ");
    scanf("%d", &amount);
    remaining_amount = amount;
    sort(coins);
    
    for(i=0; i<n; i++)
    {
        printf("You will need %d of coins %d\n", remaining_amount/coins[i], coins[i]);
        remaining_amount = remaining_amount%coins[i];
    }

    return 0;
}