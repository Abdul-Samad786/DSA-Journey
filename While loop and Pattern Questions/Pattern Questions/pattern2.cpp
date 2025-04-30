#include <iostream>
using namespace std;
//1
//2 3
//3 4 5
//5 6 7 8
int main(){
    int row=1,col,n;
    cout<<"Enter n Numbers:";
    cin>>n;
    while(row<=n){
        col=1;
        while(col<=row){
            cout<<row+col-1<<" ";
            col++;
        }
        cout<<endl;
        row++;
    }
    return 0;
}