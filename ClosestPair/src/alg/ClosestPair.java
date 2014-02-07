package alg;

public class ClosestPair {

	public static void main(String[] args) {
//		int[] A = {1,4,2,4,5};
		int[] A = {1,3,5,2,6};
		int n = A.length;
		int[] C = new int[n];
		int[] b = new int[n];
		int lenMax = 0;
		int ixMax = 0;
		
		for (int i = 0; i < n; i++)
		{
			C[i] = 0;
			b[i] = -1;
			for (int j = i-1; j >= 0; j--)
			{
				if (A[i] >= A[j]) {
					if (C[i] < C[j]) {
						C[i] = C[j];
						b[i] = j;
					}
				}
			}
			C[i]++;
			if (C[i] > lenMax) {
				lenMax = C[i];
				ixMax = i;
			}
		}

		int ix = ixMax;
		while (ix >= 0) {
			System.out.print(A[ix] + " ");
			ix = b[ix];
		}
	}

}
