package data_p.user_p;

import data_p.PacketData;

public class UserData extends PacketData {
	
	public String name; // �̸�
	public String id; // ���̵�
	public String pw; // ���
	public String phone; // ��
	public String birth; // ����
	public String cType; // �α��� Ÿ�� (�Ϲ�,������(�����ͺ��̽��� �ִ� Ű���� ������ ��))

	public UserData(String name, String id, String pw, String phone, String birth, String cType) {
		super();
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.phone = phone;
		this.birth = birth;
		this.cType = cType;
	}
}
