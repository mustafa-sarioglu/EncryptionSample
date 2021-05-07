package com.meawallet.encryption;

import java.util.ArrayList;
import java.util.List;

public class Encryption {

    private List<EncrpytionTable> _EncrpytionTable;

    private void _FillEncrpytionTable(){
        _EncrpytionTable = new ArrayList<EncrpytionTable>();
        _EncrpytionTable.add(new EncrpytionTable(0,0,0));
        _EncrpytionTable.add(new EncrpytionTable(0,1,1));
        _EncrpytionTable.add(new EncrpytionTable(0,2,2));
        _EncrpytionTable.add(new EncrpytionTable(0,3,3));
        _EncrpytionTable.add(new EncrpytionTable(0,4,4));
        _EncrpytionTable.add(new EncrpytionTable(0,5,5));
        _EncrpytionTable.add(new EncrpytionTable(0,6,6));
        _EncrpytionTable.add(new EncrpytionTable(0,7,7));
        _EncrpytionTable.add(new EncrpytionTable(0,8,8));
        _EncrpytionTable.add(new EncrpytionTable(0,9,9));
        _EncrpytionTable.add(new EncrpytionTable(1,1,2));
        _EncrpytionTable.add(new EncrpytionTable(1,2,3));
        _EncrpytionTable.add(new EncrpytionTable(1,3,4));
        _EncrpytionTable.add(new EncrpytionTable(1,4,5));
        _EncrpytionTable.add(new EncrpytionTable(1,5,6));
        _EncrpytionTable.add(new EncrpytionTable(1,6,7));
        _EncrpytionTable.add(new EncrpytionTable(1,7,8));
        _EncrpytionTable.add(new EncrpytionTable(1,8,9));
        _EncrpytionTable.add(new EncrpytionTable(1,9,0));
        _EncrpytionTable.add(new EncrpytionTable(2,2,4));
        _EncrpytionTable.add(new EncrpytionTable(2,3,5));
        _EncrpytionTable.add(new EncrpytionTable(2,4,6));
        _EncrpytionTable.add(new EncrpytionTable(2,5,7));
        _EncrpytionTable.add(new EncrpytionTable(2,6,8));
        _EncrpytionTable.add(new EncrpytionTable(2,7,9));
        _EncrpytionTable.add(new EncrpytionTable(2,8,0));
        _EncrpytionTable.add(new EncrpytionTable(2,9,1));
        _EncrpytionTable.add(new EncrpytionTable(3,3,6));
        _EncrpytionTable.add(new EncrpytionTable(3,4,7));
        _EncrpytionTable.add(new EncrpytionTable(3,5,8));
        _EncrpytionTable.add(new EncrpytionTable(3,6,9));
        _EncrpytionTable.add(new EncrpytionTable(3,7,0));
        _EncrpytionTable.add(new EncrpytionTable(3,8,1));
        _EncrpytionTable.add(new EncrpytionTable(3,9,2));
        _EncrpytionTable.add(new EncrpytionTable(4,4,8));
        _EncrpytionTable.add(new EncrpytionTable(4,5,9));
        _EncrpytionTable.add(new EncrpytionTable(4,6,0));
        _EncrpytionTable.add(new EncrpytionTable(4,7,1));
        _EncrpytionTable.add(new EncrpytionTable(4,8,2));
        _EncrpytionTable.add(new EncrpytionTable(4,9,3));
        _EncrpytionTable.add(new EncrpytionTable(5,5,0));
        _EncrpytionTable.add(new EncrpytionTable(5,6,1));
        _EncrpytionTable.add(new EncrpytionTable(5,7,2));
        _EncrpytionTable.add(new EncrpytionTable(5,8,3));
        _EncrpytionTable.add(new EncrpytionTable(5,9,4));
        _EncrpytionTable.add(new EncrpytionTable(6,6,2));
        _EncrpytionTable.add(new EncrpytionTable(6,7,3));
        _EncrpytionTable.add(new EncrpytionTable(6,8,4));
        _EncrpytionTable.add(new EncrpytionTable(6,9,5));
        _EncrpytionTable.add(new EncrpytionTable(7,7,4));
        _EncrpytionTable.add(new EncrpytionTable(7,8,5));
        _EncrpytionTable.add(new EncrpytionTable(7,9,6));
        _EncrpytionTable.add(new EncrpytionTable(8,8,6));
        _EncrpytionTable.add(new EncrpytionTable(8,9,7));
        _EncrpytionTable.add(new EncrpytionTable(9,9,8));
    }

    public Encryption() {
        _FillEncrpytionTable();
    }

    private int _Encrypt(int int1, int int2){
        int small = int1<int2 ? int1:int2;
        int big = int1<int2 ? int2:int1;

        for (EncrpytionTable item: _EncrpytionTable) {
            if (item.number1 == small && item.number2 == big){
                return item.result;
            }
        }
        return -1;
    }

    //white space check
    private boolean _IsWhiteSpace(String q){
        for(char c: q.toCharArray()){
            if (Character.isWhitespace(c)){
                return false;
            }
        }
        return true;
    }

    //check convert String to int
    private boolean _IsValidChar(String c){
        try {
            Integer.parseInt(c);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //null control
    private boolean _IsValidString(String d){
        return d!=null && d.length()>0;
    }

    public String Encrypt(String data, String password) throws Exception {
        String output = "";
        if (_IsValidString(data) && _IsValidString(password) && _IsWhiteSpace(data) && _IsWhiteSpace(password)){
            int dataSize = data.length();
            int passwordSize = password.length();
            int loopSize =dataSize>passwordSize ? dataSize:passwordSize;

            for (int i = 0; i<loopSize; i++){
                int iPass = i%passwordSize;
                int iData = i%dataSize;
                //String dataC = String.valueOf(data.charAt(i));
                String dataC = String.valueOf(data.charAt(iData));
                String passC = String.valueOf(password.charAt(iPass));

                if (_IsValidChar(dataC) && _IsValidChar(passC)){
                    String result = String.valueOf(_Encrypt(Integer.parseInt(dataC),Integer.parseInt(passC)));
                    output += result;
                }
                else {
                    throw new Exception("Invalid Value");
                }
            }
        }
        else {
            throw new Exception("Please delete white space and insert number");
        }
        return output;
    }
}
