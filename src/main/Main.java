package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static final String FILE_PATH = "F:\\Java\\Tài liệu học\\Java82_BaiTapCuoiKhoa\\test.txt";

	public static void main(String[] args) {
		ArrayList<Sach> lstSach = new ArrayList<Sach>();
		// Đọc list các quyển sách từ file
		FileReader fileReader;
		try {
			fileReader = new FileReader(new File(FILE_PATH));
			BufferedReader buffReader = new BufferedReader(fileReader);
			String line;
			while ((line = buffReader.readLine()) != null) {
				String[] parts = line.split(";");
				Sach sach = new Sach(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3],
						Integer.parseInt(parts[4]));
				lstSach.add(sach);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Xử lý lặp lại menu
		Scanner sc = new Scanner(System.in);
		int luaChon = 0;
		do {
			System.out.println("---- Menu ----\n" 
					+ "1. Hiển thị danh sách \n" 
					+ "2. Tìm kiếm \n" 
					+ "3. Thêm sách \n"
					+ "4. Sửa thông tin sách \n" 
					+ "5. Xóa sách - cũ\n\n" 
					+ "0. Thoat");
			System.out.println("Nhap lua chon:");
			luaChon = sc.nextInt();
			if (luaChon == 1) {
				hienThiSach(lstSach);
			} else if (luaChon == 2) {
				timKiemSach(lstSach);
			} else if (luaChon == 3) {
				lstSach = themSachMoi(lstSach);
				ghiLstSachVaoFile(lstSach);
			} else if (luaChon == 4) {
				lstSach = suaThongTinSach(lstSach);
				ghiLstSachVaoFile(lstSach);
			} else if (luaChon == 5) {
				lstSach = xoaSach(lstSach);
				ghiLstSachVaoFile(lstSach);
			} else {
				System.out.println("Chuong trinh da thoat");
			}
		} while (luaChon != 0);
	}

	// Hàm đưa dữ liệu từ lstSach vào file writer
	public static void ghiLstSachVaoFile(ArrayList<Sach> lstSach) {
		BufferedWriter buffWriter = null;
		try {
			FileWriter fileWriter = new FileWriter(new File(FILE_PATH));
			buffWriter = new BufferedWriter(fileWriter);
			for (int i = 0; i < lstSach.size(); i++) {
				buffWriter.write(lstSach.get(i).getLine());
				buffWriter.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (buffWriter != null) {
					buffWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Hàm thêm mới 1 cuốn sách
	public static ArrayList<Sach> themSachMoi(ArrayList<Sach> lstSach) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhap ma sach (so nguyen):");
		int maSach = sc.nextInt();
		System.out.println("Nhap tua sach:");
		sc.nextLine();
		String tuaSach = sc.nextLine();
		System.out.println("Nhap ten tac gia:");
		String tacGia = sc.nextLine();
		System.out.println("Nhap the loai:");
		String theLoai = sc.nextLine();
		System.out.println("Nhap nam xuat ban:");
		int namXuatBan = sc.nextInt();
		lstSach.add(new Sach(maSach, tuaSach, tacGia, theLoai, namXuatBan));
		return lstSach;
	}

	// Hàm xóa 1 cuốn sách theo mã sách nhập vào
	public static ArrayList<Sach> xoaSach(ArrayList<Sach> lstSach) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhap ma sach cua sach can xoa:");
		int maSachXoa = sc.nextInt();
		for (int i = 0; i < lstSach.size(); i++) {
			if (lstSach.get(i).getMaSach() == maSachXoa)
				lstSach.remove(i);
		}
		return lstSach;
	}

	// Hàm sửa thông tin một cuốn sách theo (tìm sách cần sửa theo mã sách)
	public static ArrayList<Sach> suaThongTinSach(ArrayList<Sach> lstSach) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhap ma sach cua sach can sua thong tin:");
		int maSachSua = sc.nextInt();
		for (int i = 0; i < lstSach.size(); i++) {
			if (lstSach.get(i).getMaSach() == maSachSua) {
				Sach sachSuaThongTin = lstSach.get(i);
				System.out.println("Nhap lai thong tin moi cho sach:");
				System.out.println("Nhap ma sach (so nguyen):");
				int maSach = sc.nextInt();
				sachSuaThongTin.setMaSach(maSach);
				System.out.println("Nhap tua sach:");
				sc.nextLine();
				String tuaSach = sc.nextLine();
				sachSuaThongTin.setTuaSach(tuaSach);
				System.out.println("Nhap ten tac gia:");
				String tacGia = sc.nextLine();
				sachSuaThongTin.setTacGia(tacGia);
				System.out.println("Nhap the loai:");
				String theLoai = sc.nextLine();
				sachSuaThongTin.setTheLoai(theLoai);
				System.out.println("Nhap nam xuat ban:");
				int namXuatBan = sc.nextInt();
				sachSuaThongTin.setNamXuatBan(namXuatBan);
			}
		}
		return lstSach;
	}

	// Hàm hiển thị danh sách các cuốn sách đang có trong thư viện
	public static void hienThiSach(ArrayList<Sach> lstSach) {
		System.out.println("---- Hiển thị danh sách sách ----\n" 
						+ "1. Hiển thị theo mã \n" 
						+ "2. Hiển thị theo tên tựa A-Z \n"
						+ "3. Hiển thị theo tên tác giả A-Z \n" 
						+ "4. Hiển thị theo tên thể loại A-Z \n"
						+ "5. Hiển thị theo năm xuất bản mới - cũ\n\n" 
						+ "0. Quay lại");
		System.out.println("Nhap lua chon:");
		Scanner sc = new Scanner(System.in);
		int luaChon = sc.nextInt();
		if (luaChon == 1) {
			lstSach.sort((sach1, sach2) -> sach1.getMaSach() < sach2.getMaSach() ? -1 : 1);
		} else if (luaChon == 2) {
			lstSach.sort((sach1, sach2) -> sach1.getTuaSach().compareTo(sach2.getTuaSach()));
		} else if (luaChon == 3) {
			lstSach.sort((sach1, sach2) -> sach1.getTacGia().compareTo(sach2.getTacGia()));
		} else if (luaChon == 4) {
			lstSach.sort((sach1, sach2) -> sach1.getTacGia().compareTo(sach2.getTheLoai()));
		} else if (luaChon == 5) {
			lstSach.sort((sach1, sach2) -> sach1.getNamXuatBan() > sach2.getNamXuatBan() ? -1 : 1);
		} else {
			return;
		}
		for (int i = 0; i < lstSach.size(); i++) {
			System.out.println(lstSach.get(i).inThongTinSach());
		}
	}

	// Hàm xử lý tìm kiếm sách
	public static void timKiemSach(ArrayList<Sach> lstSach) {
		System.out.println("---- Tìm kiếm sách ----\n" 
					+ "1. Tìm theo mã sách \n" 
					+ "2. Tìm theo tựa sách A-Z \n"
					+ "3. Tim theo tên tác giả \n\n" 
					+ "0. Quay lại ");
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhap lua chon");
		int luaChon = sc.nextInt();
		ArrayList<Sach> lstTimKiem = new ArrayList<Sach>();
		if (luaChon == 1) {
			System.out.println("Nhap ma sach can tim kiem:");
			sc.nextLine();
			String maSachCanTim = sc.nextLine();
			for (int i = 0; i < lstSach.size(); i++) {
				String maSachString = Integer.toString(lstSach.get(i).getMaSach());
				if (maSachString.contains(maSachCanTim)) {
					lstTimKiem.add(lstSach.get(i));
				}
			}
		} else if (luaChon == 2) {
			System.out.println("Nhap tua sach can tim kiem:");
			sc.nextLine();
			String tuaSachCanTim = sc.nextLine();
			for (int i = 0; i < lstSach.size(); i++) {
				String tuaSachString = lstSach.get(i).getTuaSach();
				if (tuaSachString.contains(tuaSachCanTim)) {
					lstTimKiem.add(lstSach.get(i));
				}
			}
		} else if (luaChon == 3) {
			System.out.println("Nhap ten tac gia can tim kiem:");
			sc.nextLine();
			String tacGiaCanTim = sc.nextLine();
			for (int i = 0; i < lstSach.size(); i++) {
				String tacGiaString = lstSach.get(i).getTacGia();
				if (tacGiaString.contains(tacGiaCanTim)) {
					lstTimKiem.add(lstSach.get(i));
				}
			}
		} else {
			return;
		}
		if (lstTimKiem.size() == 0) {
			System.out.println("Khong tim thay cuon sach nao phu hop");
			return;
		}
		for (int i = 0; i < lstTimKiem.size(); i++) {
			System.out.println(lstTimKiem.get(i).inThongTinSach());
		}
		return;
	}

}
