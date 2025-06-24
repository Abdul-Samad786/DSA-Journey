#include <iostream>
using namespace std;
void Insertion_sort(int array[],int size){
    int temp,j;
    for(int i=1;i<size-1;i++){
        temp=array[i];
        for(j=i-1;j>=0 && array[j]>temp;j--){
            array[j+1]=array[j];
        }
        array[j]=temp;
    }
    cout<<"The sorted array using Insertion sorting technique is:";
    for(int i=0;i<size;i++){
        cout<<array[i]<<" ";
    }
}
int main(){
    int array[6]={3,5,2,15,10,6};
    int size=6;
    Insertion_sort(array,size);
    return 0;
}