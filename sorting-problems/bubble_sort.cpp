#include <iostream>
using namespace std;

void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

void bubble_sort(int array[],int size){
    
    int min;
    for(int i=0;i<size-1;i++){
        min=array[i];
        for(int j=0;j<size-i;j++){
            if(array[j]>array[j+1]){
                swap(&array[j],&array[j+1]);
            }
        }
    }
}

int main(){
    int array[6]={3,5,1,98,10,120};
    int size=6;
    bubble_sort(array,size);
    cout<<"The Sorted Array is:";
    for(int i=0;i<size;i++){
        cout<<array[i]<<" ";
    }
    return 0;
}