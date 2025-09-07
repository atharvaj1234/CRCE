#include <stdio.h>
struct Pair
{
    int min;
    int max;
};

struct Pair find_min_max(int arr[], int low, int high)
{
    struct Pair result;
    if (low == high || low + 1 == high)
    {
        if (arr[low] <= arr[high])
        {
            result.min = arr[low];
            result.max = arr[high];
        }
        else
        {
            result.min = arr[high];
            result.max = arr[low];
        }
    }
    else
    {
        int mid;
        struct Pair beg, end;
        mid = low + (high - low) / 2;
        beg = find_min_max(arr, low, mid);
        end = find_min_max(arr, mid + 1, high);
        if (beg.min <= end.min)
            result.min = beg.min;
        else
            result.min = end.min;
        if (beg.max >= end.max)
            result.max = beg.max;
        else
            result.max = end.max;
    }
    return result;
}

int main()
{
    int i, n;
    printf("Enter number of elements: ");
    scanf("%d", &n);

    int arr[n];
    printf("Enter integer array elements:");
    for (i = 0; i < n; i++)
    {
        scanf("%d", &arr[i]);
    }
    struct Pair result = find_min_max(arr, 0, n - 1);
    printf("Minimum is %d\nMaximum is %d", result.min, result.max);
    return 0;
}