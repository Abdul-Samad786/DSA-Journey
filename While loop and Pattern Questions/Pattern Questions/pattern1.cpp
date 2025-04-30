#include <iostream>
using namespace std;
int main(){
  int n,i=1;
  cout<<"Enter N Numbers:";
  cin>>n;
  while(i<=n){
    int j=1;
    while(j<=n){
        cout<<n-j+1<<" ";
        j=j+1;
    }
    cout<<endl;
    i=i+1;
  }
}