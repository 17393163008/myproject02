package com.atguigu.p2.service;

import com.atguigu.p2.bean.Customer;

public class CustomerList {
	private Customer[] customers;   //用来存放客户对象的数组
	private int total;   //存放客户数量
	
	/**
	 * 用来初始化Customer数组的构造器
	 */
	public CustomerList(int totalCustomer) {
		customers = new Customer[totalCustomer];
	}
	
	/**
	 * 将指定的客户添加到数组中
	 * @param customer
	 * @return
	 */
	public boolean addCustomer(Customer customer) {
		if(total>=customers.length) {
			return false;
		}
		customers[total++] = customer;
		return true;
	}
	
	/**
	 * 修改指定位置的客户信息
	 * @param index
	 * @param customer
	 * @return
	 */
	public boolean replaeCustomer(int index,Customer customer) {
		if(index < 0 || index >= total) {
			return false;
		}
		customers[index] = customer;
		return true;
	}
	
	public boolean deleteCustomer(int index) {
		if(index < 0 || index >= total) {
			return false;
		}
		for(int i = 0; i < total-1; i++) {
			customers[i] = customers[i+1];
		}
		//最后有数据的元素需要置空
		customers[--total] = null;
		
		return true;
	}
	
	public Customer[] getCustomers(int index) {
		Customer[] custs = new Customer[total];
		for (int i = 0; i < total; i++) {
			custs[i] = customers[i];
		}
		return custs;
	}
	
	public Customer getCustomer(int index) {
		if(index < 0 || index >=total) {
			return null;
		}
		return customers[index];
	}
	public int getTotal() {
		return total;
	}
}
