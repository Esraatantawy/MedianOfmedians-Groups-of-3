import java.util.Arrays;
import static java.util.Arrays.sort;

public class MedianOfMedians {

	static int SELECT(int[] array, int start, int end)
	{
		sort(array);
		if (end%2!=0)
		{
			return array[start+(end-start)/2];
		}
			return (array[start+(end-start-1)/2]+array[start+(end-start)/ 2])/2;
	}

	static int medianOfMedians(int[] array, int left, int right, int k)
	{
		int elements = array.length;
		int i = 0, j = 0;
		int medOfMed;
		int size=(int)Math.ceil((double)elements/5);
		boolean found = false;

		int[] chunk =new int[Math.min(5,elements)];
		int[] median = new int[size];
		int begin=left;
			while (j <size) {
				for (i = 0; i < (chunk.length) && begin < right; i++) {
					chunk[i]=array[begin];
					begin++;
				}
				median[j] = SELECT(chunk, 0, chunk.length-1);
				System.out.println(j);
				System.out.println("median[j]=" + median[j]);
				j++;
				//median[i] = SELECT(chunk, i, (i) + 5);
				//i++;
			}

			if (i == 1)
			{	medOfMed = median[0];
				found = true;
			}
			else {
				System.out.println("i="+i);
				System.out.println("k="+k);
				return medianOfMedians(median, 0, median.length, median.length/2);
			}

			System.out.println("k="+k);
			// get index of median of median to partition around it,similar to randomized select
			if( found )
				return medOfMed;
			int index;
			index = partition(array, left, right, medOfMed);
			System.out.println("index="+index);
			if (index - left == k - 1)
				return array[index];
			if (index - left > k - 1)
				return medianOfMedians(array, left, index - 1, k);
         	 else
				return medianOfMedians(array, index + 1, right, k - (index + left) - 1);

	}


	static int partition(int[] arr, int left, int right,int pivot)
	{
		//arr[right]= pivot ;
		int i = left, j =left;
		while (j <= right - 1)
		{
			if (arr[j] <= pivot)
			{
				swap(arr, i, j);
				i++;
			}
			j++;
		}
		swap(arr, i , right);
		return (i);
	}
	static void swap(int[] arr, int i, int j)
	{
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}



