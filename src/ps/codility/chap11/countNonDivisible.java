package ps.codility.chap11;

public class countNonDivisible {
    public int[] solution(int[] A) {
        int[] result = new int[A.length];


        int[] divisors = new int[(A.length * 2) + 1];
        for (int i = 0 ; i < A.length ; i++) {
            divisors[A[i]]++;
        }

        for (int i = 0 ; i < A.length ; i++) {
            int count = 0;

            for (int j = 1 ; j * j <= A[i] ; j++) {
                if (A[i] % j == 0) {
                    count += divisors[j];
                    if (A[i] / j != j) {
                        count += divisors[A[i]/j];
                    }
                }
            }

            result[i] = A.length - count;
        }
        return result;
    }

}
