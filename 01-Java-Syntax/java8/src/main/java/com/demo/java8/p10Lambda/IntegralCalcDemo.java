package com.demo.java8.p10Lambda;
// �������������� ���������:
interface MyFunction1{
   // ����� � double-���������� � double-�����������:
   double f(double x);
}
// ������� �����:
class IntegralCalcDemo{
   // ����������� ����� ��� ���������� ���������:
   static double integrate(MyFunction1 obj,double a,double b){
      // ���������� ���������� ����������:
      int n=1000;
      // ����� ����������� ���������:
      double h=(b-a)/n;
      // ���������� ��� ������ ������������ �����:
      double s=(obj.f(a)+obj.f(b))*h/2;
      // ���������� ������������ �����:
      for(int k=1;k<=n-1;k++){
         s+=h*obj.f(a+k*h);
      }
      // ��������� ������ - �������� ���������:
      return s;
   }
   // ������� �����:
   public static void main(String[] args){
      // ���������� ���������:
      System.out.print(integrate((double x)->{return x*(1-x);},0,1));
      // �������� ��� ���������:
      System.out.println(" - ������ �������� "+1.0/6);
      // ���������� ���������:
      System.out.print(integrate((double x)->{return 1/x;},1,2));
      // �������� ��� ���������:
      System.out.println(" - ������ �������� "+Math.log(2));
      // ���������� ���������:
      System.out.print(integrate((double x)->{return Math.exp(-x);},0,10));
      // �������� ��� ���������:
      System.out.println(" - ������ �������� "+(1-Math.exp(-10)));
   }
}