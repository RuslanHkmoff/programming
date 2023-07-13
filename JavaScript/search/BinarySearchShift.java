package search;

public class BinarySearchShift {
    public static void main(String[] args) {
        int size = args.length;
        if (size < 1) {
            System.out.println(0);
            return;
        }
        // Pred: for all i 0 <= i < args.length  args[i] - is Integer
        int[] arr = new int[size];
        for (int i = 0; i < size; ++i) {
            arr[i] = Integer.parseInt(args[i]);
        }
        System.out.println(iterativeBinarySearch(arr));

    }

    // Pred: exist only one k: 0<=k<arr.length for all i < k arr[i] > arr[i+1]
    //                  && arr[k] < arr[k+1] && for all k<=j<arr.length arr[j] > arr[j+1]
    // Post: R  (arr[R] > arr[R-1] && 1 <= R < arr.length) || (R == 0)
    private static int iterativeBinarySearch(int[] arr) {
        // Pred
        int l = 0, r = arr.length - 1, mid = 0;
        if (arr[r] < arr[l]) {
            // arr[r] < arr[l]  => arr is sorted shift is 0
            // return 0
            return 0;
        }
        // Inv: l < r && arr[l] < arr[r]
        while (r - l > 1) {
            // r' - l' > 1 && Inv
            mid = l + (r - l) / 2;
            // mid' = l' + (r' - l')/2 && Inv
            //
            if (arr[mid] < arr[r]) {
                // arr[mid'] < arr[r']
                l = mid;
                // l' = mid' => arr[mid'] < arr[r'] && l' < r'
                // Inv
            } else {
                // arr[mid'] >= arr[r']
                r = mid;
                // r' = mid' && l' < r'
                // Inv
            }
            // r'' - l'' < r' - l'
        }
        // r - l <= 1
        return r;
        // r: arr[r] < arr[r-1] && (1 <= r < arr.length)
    }

    // Pred: exist only one k: 0<=k<arr.length for all i < k arr[i] < arr[i+1]
    //                  && arr[k] > arr[k+1] && for all k<=j<arr.length arr[j] < arr[j+1]
    // Post: R  (arr[R] < arr[R-1] && 1 <= R < arr.length) || (R == 0)
    private static int recursiveBinarySearch(int[] arr, int l, int r) {
        if (arr[arr.length - 1] < arr[0]) {
            // for all i: arr[i+1] < arr[i]  => arr is sorted shift is 0
            // return 0
            return 0;
        }
        if (r - l > 1) {
            // r' - l' > 1
            int mid = l + (r - l) / 2;
            // mid' = l' + (r' - l')/2
            if (arr[mid] < arr[r]) {
                // arr[mid'] < arr[r']
                return recursiveBinarySearch(arr, mid, r);
                // mid' = l' && Inv
            } else {
                // arr[mid'] >= arr[r']
                return recursiveBinarySearch(arr, l, mid);
                // r' = mid' && Inv
            }
        }
        // r - l == 1
        return r;
        // r: arr[r] < arr[r-1] && (1 <= r < arr.length)
    }
}
