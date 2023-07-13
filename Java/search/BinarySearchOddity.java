package search;

public class BinarySearchOddity {
    // Pred: args != null && for all i,j 0 <= i <= j < args.length - 1: arr[i] >= arr[j]
    // Post: R  (int(args[R]) <= x) && (int(arr[R-1]) > x || R == 0) && (1 <= R < args.length-1)
    public static void main(String[] args) {
        int size = args.length - 1;
        if (size < 1) {
            System.out.println(0);
            return;
        }
        // Pred: for all i 0 <= i < args.length  args[i] - is Integer
        int x = Integer.parseInt(args[0]);
        int[] arr = new int[size];
        int sum = x;
        // sum = x
        for (int i = 0; i < size; ++i) {
            arr[i] = Integer.parseInt(args[i + 1]);
            sum += arr[i];
            // sum'' = sum' + arr[i]
        }
        if (sum % 2 == 0) {
            // sum is odd
            System.out.println(iterativeBinarySearch(arr, x));
        } else {
            // sum is even
            System.out.println(recursiveBinarySearch(arr, x, 0, size));
        }
    }

    // Pred:  (for all i,j 0 <= i <= j < arr.length - 1: arr[i] >= arr[j]) && (arr[-1] == +inf && arr[arr.length] == -inf)
    // Post: R  (arr[R] <= x) && (arr[R-1] > x || R == 0) && (0 <= R < arr.length)
    private static int iterativeBinarySearch(int[] arr, int x) {
        // Pred
        int r = arr.length, l = -1, mid = 0;
        // Inv: (l' <= mid' <= r') && (for all i 0<=i<=l' arr[i] > x) && (for all j r'<=j<=arr.length arr[j] <= x)
        while (r - l > 1) {
            // r' - l' > 1
            mid = l + (r - l) / 2;
            // l' < mid' < r'
            // for all i,j 0 <= i <= j < arr.length - 1: arr[i] >= arr[j]
            if (arr[mid] <= x) {
                // arr[mid'] <= x && l' <= mid' <= r'
                r = mid;
                // r' = mid' && for all i r'<=i<arr.length arr[i] <= x
            } else {
                // arr[mid'] > x && l' <= mid' <= r'
                l = mid;
                // l' == mid' && for all i 0<=i<=l' arr[i] > x
            }
            // (l' <= mid' <= r') && (for all i 0<=i<=l' arr[i] > x) && (for all j r'<=j<=arr.length arr[j] >= x)
        }
        // r - l <= 1
        return r;
        // r:  (arr[r] <= x) && (arr[r-1] > x || r == 0) && (0 <= r < arr.length) =>
        // Post
    }

    // Pred:  (for all i,j 0 <= i <= j < arr.length - 1: arr[i] >= arr[j]) && (l < r)
    // Post: R  (arr[R] <= x) && (arr[R-1] > x || R == 0) && (0 <= R < arr.length)
    public static int recursiveBinarySearch(int[] arr, int x, int l, int r) {
        // Pred
        if (r - l > 1) {
            // r' - l' > 1
            int mid = l + (r - l) / 2;
            // mid' = l' + (r'-l')/2
            if (arr[mid] <= x) {
                // arr[mid'] <= x
                return recursiveBinarySearch(arr, x, l, mid);
                // r' = mid' && l' < r' &&
                //  for all i: r' <= i < arr.length arr[i] <= x
            } else {
                // arr[mid'] > x
                return recursiveBinarySearch(arr, x, mid, r);
                // l' = mid' && l' < r' &&
                // for all i: 0 <= i < l arr[i] > x
            }
        }
        // r - l == 1
        return r;
        // r:  (arr[r] <= x) && (arr[r-1] > x || r == 0) && (0 <= r < arr.length) =>
        // Post
    }
}
