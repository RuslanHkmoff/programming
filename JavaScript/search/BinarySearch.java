package search;


public class BinarySearch {
    //Pred: args != null && for all i,j 0 <= i <= j < args.length - 1: arr[i] >= arr[j]
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println(0);
            return;
        }
        // Pred: for all i 0 <= i < args.length  args[i] - is Integer
        int size = args.length - 1;
        int x = Integer.parseInt(args[0]);
        int[] arr = new int[size];
        for (int i = 1; i <= size; ++i) {
            arr[i - 1] = Integer.parseInt(args[i]);
        }
        System.out.println(iterativeBinarySearch(arr, x));

    }

    // Pred:  (for all i,j 0 <= i <= j < arr.length - 1: arr[i] >= arr[j]) && (arr[-1] == +inf && arr[arr.length] == -inf)
    // Post: R  (arr[R] <= x) && (arr[R-1] > x || R == 0) && (0 <= R < arr.length)
    static int iterativeBinarySearch(int[] arr, int x) {
        // Pred
        int r = arr.length, l = -1, mid = 0;
        // Inv: (l' <= mid' <= r') && (for all i 0<=i<=l' arr[i] > x) && (for all j r'<=j<=arr.length arr[j] <= x)
        while (r - l > 1) {
            // r' - l' > 1
            mid = l + (r - l) / 2;
            // l' <= mid' <= r'
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
        // r - l == 1
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
