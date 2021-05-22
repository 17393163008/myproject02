package com.atguigu.p2.ui;

import com.atguigu.p2.util.CMUtility;
import com.atguigu.p2.bean.Customer;
import com.atguigu.p2.service.*;
public class CustomerView {
	private CustomerList customerList = new CustomerList(10);
	
	public CustomerView() {
		Customer customer = new Customer("Tom",'m',23,"13477668824","1648542@qq.com");
		customerList.addCustomer(customer);
	}
	
	
	public void enterMainMenu() {
		
		boolean isFlag = true;
		do {
			System.out.println("\n----------客户信息管理软件----------\n");
			System.out.println("               1、添加客户");
			System.out.println("               2、修改客户");
			System.out.println("               3、删除客户");
			System.out.println("               4、客户列表");
			System.out.println("               5、退    出");
			System.out.print("               请选择（1-5）：");
			
			char menu = CMUtility.readMenuSelection();
			switch (menu) {
			case '1':
				addNewCustomer();
				break;
			case '2':
				modifyCustomer();
				break;
			case '3':
				deleteCustomer();
				break;
			case '4':
				listAllCustomer();
				break;
			case '5':
				System.out.print("确认是否退出（Y/N）：");
				char isExit = CMUtility.readConfirmSelection();
				if(isExit=='Y') {
					isFlag = false;
				}
				break;

			default:
				break;
			}
		}while(isFlag == true);
	}
	
	/**
	 * 添加客户的操作
	 */
	private void addNewCustomer() {
		System.out.println("---------------添加客户---------------");
		System.out.print("姓名：");
		String name = CMUtility.readString(10);
		System.out.print("性别：");
		char gender = CMUtility.readChar();
		System.out.print("年龄：");
		int age = CMUtility.readInt();
		System.out.print("电话：");
		String phone = CMUtility.readString(13);
		System.out.print("邮箱：");
		String email = CMUtility.readString(30);
		
		//将上述方法封装到对象中
		Customer customer = new Customer(name, gender, age, phone, email);
		boolean isSuccess = customerList.addCustomer(customer);
		
		if(isSuccess) {
			System.out.println("------添加完成------");
		}else {
			System.out.println("------添加失败，目录已满---");
		}
	}
	
	/**
	 * 修改客户的操作
	 */
	private void modifyCustomer() {
		System.out.println("------修改客户------");
		int number;
		Customer customer;
		for(;;) {
			System.out.print("请选择修改的客户编号（-1退出）：");
			number = CMUtility.readInt();
			if(number == -1) {
				return;
			}
			customer = customerList.getCustomer(number-1);
			if(customer == null) {
				System.out.println("无法找到指定客户！");
			}else {
				break;
			}
		}
		//修改客户信息
		System.out.print("姓名（" +customer.getName()+"):");
		String name = CMUtility.readString(10,customer.getName());
		System.out.print("性别（" +customer.getGender()+"):");
		char gender = CMUtility.readChar(customer.getGender());
		System.out.print("年龄（" +customer.getAge()+"):");
		int age = CMUtility.readInt(customer.getAge());
		System.out.print("电话（" +customer.getPhone()+"):");
		String phone = CMUtility.readString(13,customer.getPhone());
		System.out.print("邮箱（" +customer.getEmail()+"):");
		String email = CMUtility.readString(30,customer.getEmail());
		
		Customer newCust = new Customer(name, gender, age, phone, email);
		boolean isRe = customerList.replaeCustomer(number-1, newCust);
		if(isRe) {
			System.out.println("修改完成！");
			
		}else {
			System.out.println("修改失败！");
		}
	}
	
	/**
	 * 删除客户的操作
	 */
	private void deleteCustomer() {
		System.out.println("------删除客户------");
		int number;
		for(;;) {
			System.out.print("请选择需要删除的客户编号(-1退出)：");
			number = CMUtility.readInt();
			
			if(number==-1) {
				return;
			}
			
			Customer customer = customerList.getCustomer(number-1);
			if(customer == null) {
				System.out.println("无法找到客户！");
				
			}else {
				break;
			}
		}
		System.out.print("确认是否删除（Y/N）：");
		char isDelete = CMUtility.readConfirmSelection();
		if(isDelete == 'Y') {
			customerList.deleteCustomer(number-1);
		}else {
			return;
		}
		System.out.println("删除成功！");
	}
	
	/**
	 * 查询全部客户的操作
	 */
	private void listAllCustomer() {
		System.out.println("---------------客户列表---------------");
		
		int total = customerList.getTotal();
		if(total == 0) {
			System.out.println("没有客户记录！");
		}else {
			System.out.println("编号\t姓名\t性别\t年龄\t电话\t\t邮箱");
			Customer[] customers = customerList.getCustomers(total);
			for (int i = 0; i < customers.length; i++) {
				Customer customer = customers[i];
				System.out.println((i+1)+"\t"+customer.getName() + "\t"
						+customer.getGender()+"\t"+customer.getAge()+"\t"
						+customer.getPhone()+"\t"+customer.getEmail());
			}
		}
		System.out.println("-----------客户列表加载完毕-----------");
	}
	
	public static void main(String[] args) {
		CustomerView customerView = new CustomerView();
		customerView.enterMainMenu();
	}
}
