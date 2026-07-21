#include <iostream>
using namespace std;

void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}
int* selection_sort(int array[],int size){
    //[3,5,2,1,7,6]
    int min;
    int j;
    for(int i=0;i<size-1;i++){
        min=i;
        for(j=i+1;j<6;j++){
            if(array[j]<array[min]){
                min=j;
            }
        }
        swap(&array[min],&array[i]);
    }
    return array;
}
int main(){
    int array[6]={88,15,20,125,10,250};
    int size=6;
    selection_sort(array,size);
    cout<<"The sorted array is:";
    for(int i=0;i<size;i++){
        cout<<array[i]<<" ";
    }
    return 0;
}