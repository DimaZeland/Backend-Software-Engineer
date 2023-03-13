package com.demo.java8.p8UsingObjects;

// ����������� ������:
import static javax.swing.JOptionPane.*;
// ������� �����:
class BankAccount{
   // �������� ���� (���������� ������):
   double rate;
   // ���� - ������ ����������� ������:
   Person fellow;
   // �����������:
   BankAccount(String name,double money,int time,double r){
      // ���������� ������:
      rate=r;
      // �������� ������� ����������� ������:
      fellow=new Person(name,money,time);
   }
   // ����� ��� ����������� ����������� ���� � ����������:
   void show(){
      showMessageDialog(null,
         // ����� ��������� (������������� ����������
         // ����� toString() ��� ������ Person):
         fellow,
         // �������� ����:
         "�������",
         // ��� ����:
         INFORMATION_MESSAGE
      );
   }
   // ���������� �����:
   class Person{
      // ��������� ���� (��� ���������):
      String name;
      // ��������� �����:
      double money;
      // �����, �� ������� ����������� �����:
      int time;
      // ����� ��� ���������� �������� �����:
      double getMoney(){
         // ���������� ��� ������ ����������:
         double s=money;
         // ���������� ����������:
         for(int k=1;k<=time;k++){
            s*=(1+rate/100);
         }
         // ��������� ���������� (���������� ��
         // ���� ���� ����� ���������� �����):
         s=Math.round(s*100)/100.0;
         // ��������� ������:
         return s;
      }
      // ��������������� ������ toString():
      public String toString(){
         String txt="���: "+name+"\n";
         txt+="��������� �����: "+money+"\n";
         txt+="���������� ������: "+rate+"\n";
         txt+="����� (���): "+time+"\n";
         txt+="�������� �����: "+getMoney();
         return txt;
      }
      // ����������� ����������� ������:
      Person(String txt,double m,int t){
         name=txt; // ��� ���������
         money=m;  // ��������� ����� ������
         time=t;   // ����� ���������� ������
      }
   }
}
// ������� �����:
class UsingInnerClassDemo{
   public static void main(String[] args){
      // �������� ������� �������� ������:
      BankAccount ivanov=new BankAccount(
         "���� ������", // ��� ���������
         1000.0,        // ��������� �����
         5,             // ����� ���������� ������
         8.0            // ���������� ������
      );
      // ����������� ���������� �� ������:
      ivanov.show();
   }
}