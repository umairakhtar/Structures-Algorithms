import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author UmairAkhtar
 */
public class ImageSort {
    
    public static void main(String[] args) throws IOException{
        BufferedImage img = ImageIO.read(new File("F:\\Coursera\\Data Structures & Algorithms\\StructuresAlgorithms\\src\\Boston.jpeg"));
        int w = img.getWidth();
        int h = img.getHeight();
        int i=0;
        int[] pixels = new int[w*h];
        BufferedImage sorted = null;
        sorted = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        for(int x=0; x<w;x++){
            for(int y=0; y<h; y++){
                pixels[i] = img.getRGB(x, y);
                i++;            
            }
        }
                
        //SelectionSort(pixels);
        HeapSort(pixels);
        //InsertionSort(pixels);
        //msort(pixels);
        
        int j=0;
        for(int x=0; x<w;x++){
            for(int y=0; y<h; y++){
                System.out.println((j+1)+" : "+Intensity(pixels[j]));
                sorted.setRGB(x, y, pixels[j]);
                j++;
            }
        }
        File outputfile = new File("Sorted.jpeg");
        ImageIO.write(sorted, "png", outputfile);
    }
    
    public static void SelectionSort(int pixels[]){
        long startTime = System.nanoTime();
        for(int p=0; p<pixels.length;p++){
            double record = Intensity(pixels[p]);
            int selectedPixel = p;
            for(int q=p;q<pixels.length;q++){
                  double b = Intensity(pixels[q]);
                  if(b>record){
                      selectedPixel = q;
                      record=b;
                  }
            }
            int temp = pixels[p];
            pixels[p] = pixels[selectedPixel];
            pixels[selectedPixel] = temp;
        }
        long endTime = System.nanoTime();
        System.out.println(":::Selection Sort:::");
        System.out.println(String.format("Time complexity: %s\n", toString(endTime - startTime)));
    }
    
    public static void HeapSort(int arr[])
    {
        long startTime = System.nanoTime();
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
        for (int i=n-1; i>=0; i--)
        {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
        long endTime = System.nanoTime();
        System.out.println(":::Heap Sort:::");
        System.out.println(String.format("Time complexity: %s\n", toString(endTime - startTime)));
    } 
    static void heapify(int arr[], int n, int i)
    {
        int largest = i;
        int l = 2*i + 1;
        int r = 2*i + 2;
        if (l < n && Intensity(arr[l]) > Intensity(arr[largest]))
            largest = l;
        if (r < n && Intensity(arr[r]) > Intensity(arr[largest]))
            largest = r;
        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }
    
    public static void InsertionSort(int pixels[]){
        long startTime = System.nanoTime();
        int n = pixels.length;
        for (int i=1; i<n; ++i)
        {
            int key = pixels[i];
            int j = i-1;
            while (j>=0 && Intensity(pixels[j]) > Intensity(key))
            {
                pixels[j+1] = pixels[j];
                j = j-1;
            }
            pixels[j+1] = key;
        }
        long endTime = System.nanoTime();
        System.out.println(":::Insertion Sort:::");
        System.out.println(String.format("Time complexity: %s\n", toString(endTime - startTime)));
    }
    
    static void msort(int pixels[]){
        long startTime = System.nanoTime();
        MergeSort(pixels, 0, pixels.length-1);
        long endTime = System.nanoTime();
        System.out.println(":::Merge Sort:::");
        System.out.println(String.format("Time complexity: %s\n", toString(endTime - startTime)));
    }
    
    public static void MergeSort(int arr[], int l, int r){
        if (l < r)
        {
            int m = (l+r)/2;
            MergeSort(arr, l, m);
            MergeSort(arr , m+1, r);
            merge(arr, l, m, r);
        }
    }
    static void merge(int arr[], int l, int m, int r)
    {
        int n1 = m - l + 1;
        int n2 = r - m;
        int L[] = new int [n1];
        int R[] = new int [n2];
        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2)
        {
            if (Intensity(L[i]) <= Intensity(R[j]))
            {
                arr[k] = L[i];
                i++;
            }
            else
            {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1)
        {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2)
        {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    
    public static double Intensity(int col)
    {
	double I;
        //int a = (col>>24) & 0xff;
        int r = (col>>16) & 0xff;
        int g = (col>>8) & 0xff;
        int b = col & 0xff;
        I = 0.2989*r + 0.5870*g + 0.1140*b;
        return I;
    }
    
    private static String toString(long nanoSecs) {
        /*Reference: stackoverflow.com, Converting nano time to readable format*/ 
      int minutes    = (int) (nanoSecs / 60000000000.0);
      int seconds    = (int) (nanoSecs / 1000000000.0)  - (minutes * 60);
      int millisecs  = (int) ( ((nanoSecs / 1000000000.0) - (seconds + minutes * 60)) * 1000);
      if (minutes == 0 && seconds == 0)
         return millisecs + "ms";
      else if (minutes == 0 && millisecs == 0)
         return seconds + "s";
      else if (seconds == 0 && millisecs == 0)
         return minutes + "min";
      else if (minutes == 0)
         return seconds + "s " + millisecs + "ms";
      else if (seconds == 0)
         return minutes + "min " + millisecs + "ms";
      else if (millisecs == 0)
         return minutes + "min " + seconds + "s";
      return minutes + "min " + seconds + "s " + millisecs + "ms";
   }
}