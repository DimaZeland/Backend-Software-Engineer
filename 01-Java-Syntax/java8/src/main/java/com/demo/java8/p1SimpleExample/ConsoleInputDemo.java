package com.demo.java8.p1SimpleExample;

// ������������� ����� Scanner:
import java.util.Scanner;
// �������� ������:
class ConsoleInputDemo{
   // ������� �����:
   public static void main(String[] args){
      // �������� ������� input ������ Scanner:
      Scanner input=new Scanner(System.in);
      // ���������� ��� ���������� ��������
      // ��� ������ � ������:
      String day,month;
      // ������������ ���������:
      System.out.print("����� ������� ����? ");
      // ����������� ��������� ������:
      day=input.nextLine();
      // ������������ ���������:
      System.out.print("����� �����? ");
      // ����������� ��������� ������:
      month=input.nextLine();
      // ��������� ����������:
      String text;
      // ��������� �������� ��� ����������� � �������:
      text="������� "+day+", ����� - "+month;
      // ������������ ���������:
      System.out.println(text);
   }
}