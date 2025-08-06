package Test1;

import java.util.*;
class Q1 
{
    public static void main(String []args){
        Scanner sc = new Scanner (System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int j = sc.nextInt();
        int m = sc.nextInt();
        int p = sc.nextInt();
        int banana=0 ,peanut=0;
        if( n<0 && k<0 || j<0 || m<0 || p<0)  System.out.println("Invalid Input");
        else{
            if(k>0)  banana =m/k;
            if(j>0)  peanut = p/j;
            n=n-banana-peanut;
            System.out.println(n);
        }
    }
}