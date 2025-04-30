#include <iostream>
using namespace std;
int main(){
    int row=1,col,n;
    int count=1;
    cout<<"Enter N Numbers:";
    cin>>n;
    while(row<=n){
        col=1;
        while (col<=row)
        {
            cout<<count<<" ";
            count++;
            col++;
        }
        cout<<endl;
        row++;
    }
    return 0;
}