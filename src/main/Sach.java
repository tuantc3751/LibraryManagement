package main;

public class Sach {
	private int maSach;
	private String tuaSach;
	private String tacGia;
	private String theLoai;
	private int namXuatBan;
	
	public Sach(int maSach, String tuaSach, String tacGia, String theLoai, int namXuatBan) {
		this.maSach = maSach;
		this.tuaSach = tuaSach;
		this.tacGia = tacGia;
		this.theLoai = theLoai;
		this.namXuatBan = namXuatBan;
	}
	public int getMaSach() {
		return maSach;
	}
	public void setMaSach(int maSach) {
		this.maSach = maSach;
	}
	public String getTuaSach() {
		return tuaSach;
	}
	public void setTuaSach(String tuaSach) {
		this.tuaSach = tuaSach;
	}
	public String getTacGia() {
		return tacGia;
	}
	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}
	public String getTheLoai() {
		return theLoai;
	}
	public void setTheLoai(String theLoai) {
		this.theLoai = theLoai;
	}
	public int getNamXuatBan() {
		return namXuatBan;
	}
	public void setNamXuatBan(int namXuatBan) {
		this.namXuatBan = namXuatBan;
	}
	
	public String inThongTinSach() {
		return "Mã:"+maSach+";Tên sách:"+tuaSach+";Tác giả:" + tacGia+ ";Thể loại:"+theLoai+";Năm xuất bản:"+namXuatBan;
	}
	
	public String getLine() {
		return maSach+";"+tuaSach+";" + tacGia+ ";"+theLoai+";"+namXuatBan;
	}
}
