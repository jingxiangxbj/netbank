package com.netbank.biz;

import java.util.List;

import com.netbank.entity.*;
//������Ϣ��ɾ�Ĳ�

public interface PersoninfoBiz {
	//�޸ĸ�����Ϣ
	public boolean modifyPersoninfo(Personinfo personinfo);
	
	//��Ӹ�����Ϣ
	public boolean add(Personinfo personinfo);

	//��ȡȫ��������Ϣ
	public List getAllPersoninfo();
	
	//���ݿ���ҳ������д�����֤�ż���ѯ������Ϣ
	public List searchPersoninfo(Personinfo personinfo);
	
	//�����˻�״̬��ȡ������Ϣ
	public List searchPersoninfo(Status status);
}
