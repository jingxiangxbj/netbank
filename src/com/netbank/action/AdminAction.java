package com.netbank.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.netbank.biz.*;
import com.netbank.entity.*;
import com.opensymphony.xwork2.ActionSupport;

public  class AdminAction extends ActionSupport implements RequestAware,SessionAware {
	
	////////////////////////////////////
	//����ͨ��@Resourceע��ע�������userBiz����ʡ��set����
	@Resource private UserBiz userBiz;//����һ��UserBiz�ӿڵ�����,��action�в���ȥ����dao�������
	Map<String, Object> request;
	Map<String, Object> session;
	//����Admin���Ͷ������ڷ�װ����Ա��¼��ҳ��ı�����
	private Admin admin;	
	
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
	public void setRequest(Map<String, Object> request) {
		this.request=request;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	
	//����ͨ��@Resourceע��ע�������personinfoBiz����ʡ��set����	
	@Resource private PersoninfoBiz personinfoBiz;
	private Status status;	
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	/** �Ե�¼ҳ�������֤������û����������Ƿ���ȷ	 */
	public void validateLogin(){
		//����ҵ�񷽷�������username��ȡ����Ա
		Admin a=userBiz.getAdmin(admin.getUsername());
		if(a==null){
			this.addFieldError("username", "�û���������");
		}else{
			if(!admin.getPassword().equals(a.getPassword())){
				this.addFieldError("password", "���벻��ȷ");
			}
			admin=a;		}
	}	
	
	/**	 * ��¼	 * @return	 */
	public String login(){	
		System.out.println("9999");
		if(admin!=null){
			session.put("admin",admin);
			return SUCCESS;		}
		return "login";
	}
	
	//������װ��"����"��"����"��"ɾ��"��ť�������Ĳ���
		private Account account;	
		public Account getAccount() {
			return account;
		}
		public void setAccount(Account account) {
			this.account = account;
		}
      private int id;
	   public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * �����˻�	 * @return	 */
	public String locking(){
		System.out.println("dongjie");
		userBiz.locking(id);
		return "list";
	}	
	/**	 * �����˻�	 * @return	 */
	public String enabled(){
		System.out.println("qiyong");
		userBiz.enabled(id);
		return "list";
	}
	/**
	 * ɾ���˻�
	 */	
	public String del(){
		//����ҵ�񷽷���ɾ���˻���ͬʱ���м���ɾ��
		userBiz.delAccount(id);
		return "list";
	}
	
	/*----------------------------------------------------------------------------------------------------------*/	
	/**	 * ��ѯ�˻�	 * @return	 */
	public String listUsersAdminActionfangfa(){
		//����ҵ�񷽷��������˻�״̬��ȡ�ͻ�������Ϣ��״̬Ϊ0��ʾ��ȡ���пͻ�
		List users=personinfoBiz.searchPersoninfo(status);
		request.put("users",users);
		return "users";
	}
   public Personinfo personinfo;
   
	
	public Personinfo getPersoninfo() {
	return personinfo;
}
public void setPersoninfo(Personinfo personinfo) {
	this.personinfo = personinfo;
}
	public void validateKaihu(){
		if(userBiz.getAccount(account.getUsername())!=null){
			request.put("message", "�û����Ѵ���");
		}
		//��ȡ���������ĸ�����Ϣ�����������Ϊ����ҳ������д�����֤��
		List list = personinfoBiz.searchPersoninfo(personinfo);
		//�������д�����֤���ڸ�����Ϣ���Ѵ��ڣ�����ʾ������Ϣ
		if(list.size()>0){
			this.addFieldError("personinfo.cardid", "һ�����ֻ֤��ӵ��һ���˻�");
		}		
	}	
	
	
	
	//����
		public String kaihu(){
		    //����ҵ�񷽷������˻���Account������˻�
			userBiz.addAccount(account);
			//����ҵ�񷽷����������Ϣ��personinfo��Ӹ�����Ϣ
			account = userBiz.getAccount(account.getUsername());
			personinfo.setAccount(account);
			personinfoBiz.add(personinfo);
			request.put("message", "��ӳɹ�");
			return "message";		
		}
		
		
		
		private Password pwd;
		public Password getPwd() {
			return pwd;
		}
		public void setPwd(Password pwd) {
			this.pwd = pwd;
		}
		public void validateChangepwd(){
			System.err.println(pwd.getOldpwd()+"---"+pwd.getNewpwd());
			admin=(Admin)session.get("admin");
			if(!pwd.getOldpwd().equals(admin.getPassword())){
				this.addFieldError("pwd.oldpwd", "���벻��ȷ");
			}
			if(!pwd.getNewpwd().equals(pwd.getConfirmpwd())){
				this.addFieldError("pwd.confirmpwd", "�������벻һ��");
			}
		}	
		//�޸�����
		public String changepwd(){
			admin.setPassword(pwd.getNewpwd());
			if(userBiz.modifyAdmin(admin)){
				session.put("admin",admin);
				request.put("message", "�����޸ĳɹ���");
				return "message";
			}
			request.put("message", "�����޸�ʧ�ܣ�");
			return "message";
		}
		
	
	//ע��
	public String logout(){
		session.remove("admin");
		return "login";
	}
	
	
		 /*��ѯ�˻�*/
	public String search(){
		List users=personinfoBiz.searchPersoninfo(personinfo);
		request.put("users",users);
		return "users";
	}
	
	
	

}
