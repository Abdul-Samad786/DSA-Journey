#include <iostream>
using namespace std;
int main(){
    int row=1,col,n;
    cout<<"Enter n Numbers:";
    cin>>n;
    while(row<=n){
        col=1;
        while(col<=n){
            char ch='A'+col-1;
            cout<<ch<<" ";
            col++;
        }
        cout<<endl;
        row=row+1;
    }
    return 0;
}