#include <iostream>
using namespace std;
int main(){
    char ch;
    cout<<"Enter any character:";
    cin>>ch;
    if(ch>=48&&ch<=57){
        cout<<"Given character is Integer";
    }
    else if(ch>=65&&ch<=90){
        cout<<"Given character is Upper Case Alphabet";
    }
    else{
        cout<<"Given character is Lower case Alphabel";
    }
    return 0;
}