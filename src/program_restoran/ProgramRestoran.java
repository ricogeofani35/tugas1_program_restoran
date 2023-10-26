package program_restoran;
import java.util.Scanner;

//class utama
public class ProgramRestoran {
	public static void main(String args[]) {
		//set array menu
		MenuItem[] menu = new MenuItem[5];
		
		//get method dataMenuMakananMinuman
		dataMenuMakananMinuman(menu);
		
		//get method tampilan menu
		tampilMenu(menu);
		
		//set scanner pemesanan
		Scanner scanner = new Scanner(System.in);
		
		//set array pemesanan
		SimpanPesanan[] dataPesanan = new SimpanPesanan[6];
		
		//get method pilihMenu
		pilihMenu(dataPesanan, menu, scanner);
		
        
		//get method tampilTransaksiPesanan
        tampilTransaksiPesanan(dataPesanan, scanner);
		
		//close scanner
		scanner.close();
	}
	
//	set method dataMenuMakananMinuman
	public static void dataMenuMakananMinuman(MenuItem[] menu) {
		//isi array menu dengan data menu makanan dan minuman
		menu[0] = new MenuItem(1, "nasi goreng", 12000, "makanan");
		menu[1] = new MenuItem(2, "Ayam Bakar + nasi", 23000, "makanan");
		menu[2] = new MenuItem(3, "Ayam Goreng + nasi", 18000, "makanan");
		menu[3] = new MenuItem(4, "Es Teh", 8000, "minuman");
		menu[4] = new MenuItem(5, "Es Kelapa Muda", 10000, "minuman");
		
	}
	
	//method set tampilMenu
	public static void tampilMenu(MenuItem[] menu) {
		//tampilan awal
		System.out.println("------------------------------");
		System.out.println("---Menu Makanan dan Minuman---");
		System.out.println("------------------------------");
		for(MenuItem item : menu) {
			//jika data sudah ada
			if(item != null) {
				System.out.println("-------- Menu No : " + item.getId() + " ---------");
				System.out.println("------------------------------");
				System.out.println("Nama : " + item.getNama());
				System.out.println("Harga : " + item.getHarga());
				System.out.println("kategori : " + item.getKategori());
				System.out.println("------------------------------");
			}
		}
	}
	
	//set method pilihMenu
	public static void pilihMenu(SimpanPesanan[] dataPesanan, MenuItem[] menu,Scanner scanner) {
		//variabel boolean
		boolean pesanLagi = true;
		int maks = 0;
		//looping menu dan jumlah pesanan
				outerLoop: //label outerLoop
				while(pesanLagi) {
					
						
					System.out.println("------------------------------");
					System.out.println("Jumlah Pesanan Menu : " + maks); //jumlah pesanan menu user
					System.out.println("------------------------------");
					
					System.out.print("Masukan Pilihan Menu(1-5): ");
					String pilMenu = scanner.nextLine();
					//validasi input menu
					if (!pilMenu.matches("[1-5]*")) {
						System.out.println("------------------------------");
					    System.out.println(">>>pilihan menu tidak ada!<<<");
					    continue;
					} 
					
					System.out.print("Masukan Jumlah Pesanan: ");
					String jmlPesanan = scanner.nextLine();
					//validasi input jumlah pesanan
					if (!jmlPesanan.matches("[0-9]*")) {
						System.out.println("------------------------------");
					    System.out.println(">>>Input Jumlah Pesanan Salah!<<<");
					    continue;
					} 
					
					//jika pesanan sudah ada
					for(SimpanPesanan data : dataPesanan) {
						if(data != null) {
							String id = Integer.toString(data.getId());
							if (pilMenu.matches(id)) {
								System.out.println("------------------------------");
							    System.out.println(">>>Menu Pilihan Sudah Ada!<<<");
							    continue outerLoop; //akan continue ke label outerLoop
							} 
						}
					}
					
					//input boolean pilihan
					System.out.print("Mau Pesan Lagi, maks-4 (ya/tidak)?: ");
					String isLagi = scanner.nextLine();
					//rubah menjadi huruf kecil
					isLagi = isLagi.toLowerCase();
					
					//validasi input jumlah pesanan
					if (!isLagi.matches("ya|tidak")) {
						System.out.println("------------------------------");
					    System.out.println(">>>Pilihan Tidak Ada!<<<");
					    continue;
					} 
					
			        //convert string menjadi boolean
			        isLagi = (isLagi.matches("ya")) ? "true" : "false";
			        boolean pilihan = Boolean.parseBoolean(isLagi);
			        
			      //isi dengan input boolean (true / false)
					pesanLagi = pilihan;
					
				
					//jika pesanan sudah 4 kali
					maks++;
					if(maks == 4) {
						System.out.println("------------------------------");
						System.out.println(">>> Pesanan Maksimal 4 <<<");
						pesanLagi = false;
					}
					
					//konvert string jmlPesanan ke int jumlah
					int jumlah = Integer.parseInt(jmlPesanan);
					
					//case pilihan menu
					switch(pilMenu) {
						case "1":
							dataPesanan[0] = new SimpanPesanan(menu[0].getId(), menu[0].getNama(), menu[0].getHarga(), menu[0].getKategori(), jumlah);
							break;
						case "2":
							dataPesanan[1] = new SimpanPesanan(menu[1].getId(), menu[1].getNama(), menu[1].getHarga(), menu[1].getKategori(), jumlah); 
							break;
						case "3":
							dataPesanan[2] = new SimpanPesanan(menu[2].getId(), menu[2].getNama(), menu[2].getHarga(), menu[2].getKategori(), jumlah); 
							break;
						case "4":
							dataPesanan[3] = new SimpanPesanan(menu[3].getId(), menu[3].getNama(), menu[3].getHarga(), menu[3].getKategori(), jumlah); 
							break;
						case "5":
							dataPesanan[4] = new SimpanPesanan(menu[4].getId(), menu[4].getNama(), menu[4].getHarga(), menu[4].getKategori(), jumlah); 
							break;
						default:
							System.out.println("data menu tidak ada!");
							
					}
					
				}
				
				// ANSI escape code untuk membersihkan layar
		        System.out.print("\033[H\033[2J"); // Windows Command Prompt

				//output pesanan
		        tampilPesanan(maks, dataPesanan, menu, scanner);
	}
	
	//set method tampilPesanan
	public static void tampilPesanan(int maks, SimpanPesanan[] dataPesanan, MenuItem[] menu, Scanner scanner) {
		System.out.println("------------------------------");
		System.out.println("---------Data Pesanan---------");
		System.out.println("------------------------------");
		System.out.println("Jumlah Pesanan Menu : " + maks); //jumlah pesanan menu user
		System.out.println("------------------------------");
		
	    double subTotal = 0;
	    //looping data pesanan
		for(SimpanPesanan data : dataPesanan) {
			if(data != null) {
				System.out.println("-------- Menu No : " + data.getId() + " ---------");
				System.out.println("------------------------------");
				System.out.println("Nama : " + data.getNama());
				System.out.println("Harga : " + data.getHarga());
				System.out.println("kategori : " + data.getKategori());
				System.out.println("Jumlah : " + data.getJumlah());
				System.out.println("------------------------------");
				
				//kali harga dan jumlah dan masukan ke variabel subTotal
				subTotal += data.hitungSubTotal();
			}
		}
		
		//get method pilihPromoMinuman
		pilihPromoMinuman(subTotal, dataPesanan, menu, scanner);
	}
	
//set method pilihPromoMinuman	
	public static void pilihPromoMinuman(double subTotal, SimpanPesanan[] dataPesanan, MenuItem[] menu, Scanner scanner) {
		//jika sub total lebih dari 50000
		if(subTotal > 50000) {
			//tampilkan sub total
        	System.out.println("------------------------------");
    		System.out.println("Sub Total : " + subTotal);
			//input boolean pilihan ambil promo atau tidak
    		boolean isPilihan = true;
    		
    		//looping pilihan jika true
    		while(isPilihan) {
    			System.out.println("------------------------------");
    			System.out.print("Mau Ambil Promo, beli 1 gratis 1,\nuntuk kategori minuman (ya/tidak)?: ");
    			String isPromo = scanner.nextLine();
    			//validasi input menu
				if (!isPromo.matches("ya|tidak")) {
					System.out.println("------------------------------");
				    System.out.println(">>>pilihan tidak ada!<<<");
				    continue;
				} 
    			
    	        //convert string menjadi boolean
    	        isPromo = (isPromo.matches("ya")) ? "true" : "false";
    	        boolean pilihan = Boolean.parseBoolean(isPromo);
    	        
    	        if(pilihan == true) {
    	        	
    	        	//get method tampilMenuPromoMinuman
    	        	tampilMenuPromoMinuman(menu);
    	        	
    	        	System.out.print("Masukan Pilihan Menu(4 - 5) : ");
    				String pilMenu = scanner.nextLine();
    				//validasi input menu
					if (!pilMenu.matches("[4-5]*")) {
						System.out.println("------------------------------");
					    System.out.println(">>>pilihan menu promo tidak ada!<<<");
					    continue;
					} 
					
    				System.out.print("Masukan Jumlah Pesanan: ");
    				String jmlPesanan = scanner.nextLine();
    				//validasi input menu
					if (!jmlPesanan.matches("[0-9]*")) {
						System.out.println("------------------------------");
					    System.out.println(">>>Input Jumlah Pesanan Salah!<<<");
					    continue;
					} 
    				
    				//konvert string jmlPesanan ke int jumlah
    				int jumlah = Integer.parseInt(jmlPesanan);
    				
    				//case pilihan menu
    				switch(pilMenu) {
    					case "4":
    						dataPesanan[5] = new SimpanPesanan(menu[3].getId(), menu[3].getNama(), menu[3].getHarga(), menu[3].getKategori(), jumlah); 
    						break;
    					case "5":
    						dataPesanan[5] = new SimpanPesanan(menu[4].getId(), menu[4].getNama(), menu[4].getHarga(), menu[4].getKategori(), jumlah); 
    						break;
    					default:
    						System.out.println("data menu tidak ada!");
    						
    				}
    				//set menjadi false
        	        isPilihan = false;
    	        } else {
    	        	//set menjadi false
        	        isPilihan = false;
    	        }
    		}
		}
	}
	
//	set method tampilMenuPromoMinuman
	public static void tampilMenuPromoMinuman(MenuItem[] menu) {
		System.out.println("------------------------------");
		System.out.println("------Menu Promo Minuman------");
		System.out.println("------------------------------");
    	for(MenuItem item : menu) {
			//jika data sudah ada
			if(item != null && item.getKategori() == "minuman") {
				System.out.println("-------- Menu No : " + item.getId() + " ---------");
				System.out.println("------------------------------");
				System.out.println("Nama : " + item.getNama());
				System.out.println("Harga : " + item.getHarga());
				System.out.println("kategori : " + item.getKategori());
				System.out.println("------------------------------");
				
			}
		}
	}
	
//	set method hitungTransaksi dengan return grandTotal
	public static double hitungTransaksi(SimpanPesanan[] dataPesanan) {
//		set variabel
		double subTotal = 0;
		double grandTotal = 0;
		double pajak = 0;
		double diskon = 0;
		double pelayanan = 20000;
//		simpan data subTotal dari harga * jumlah
		for(SimpanPesanan data : dataPesanan) {
			if(data != null) {
				subTotal += data.hitungSubTotal();
			}
		}
		
//		set variabel pajak 10% dan diskon 10%
		pajak = subTotal * 10 / 100;
		diskon = subTotal * 10 / 100;
		
//		set grand total
		grandTotal = (subTotal + pajak) + pelayanan;
		
//		jika sub total lebih dari 100000
		if(subTotal > 100000) {
//			mendapatkan diskon 10%
			grandTotal = grandTotal - diskon;
			System.out.println("Diskon : 10%");
			return grandTotal;
		}
//		return grand total
		return grandTotal;
	}
	
//	set method tampilTransaksiPesanan
	public static void tampilTransaksiPesanan(SimpanPesanan[] dataPesanan, Scanner scanner) {
		double subTotal = 0;
		System.out.println("------------------------------");
		System.out.println("------Transaksi Pemesanan------");
		System.out.println("------------------------------");
		//looping data pesanan
		for(SimpanPesanan data : dataPesanan) {
			if(data != null) {
				System.out.println("-------- Menu No : " + data.getId() + " ---------");
				System.out.println("------------------------------");
				System.out.println("Nama : " + data.getNama());
				System.out.println("Harga : " + data.getHarga());
				System.out.println("kategori : " + data.getKategori());
				System.out.println("Jumlah : " + data.getJumlah());
				System.out.println("------------------------------");
				
				//kali harga dan jumlah dan masukan ke variabel subTotal
				subTotal += data.hitungSubTotal();
			}
			
		}
		
		//jika data pesanan di index-5 ada maka tampilkan 
		if(dataPesanan[5] != null) {
			System.out.println("------------------------------");
			System.out.println("---------Gratis Minuman-------");
			System.out.println(">>Untuk Promo beli 1 gratis 1<<");
			System.out.println("------------------------------");
			System.out.println("Nama : " + dataPesanan[5].getNama());
			System.out.println("kategori : " + dataPesanan[5].getKategori());
			System.out.println("Jumlah : " + dataPesanan[5].getJumlah());
		}
		
		//tampil sub total
		System.out.println("------------------------------");
		System.out.println("Sub Total : " + subTotal);
		System.out.println("------------------------------");
		
		//get method cetakStrukTransaksi
		cetakStrukTransaksi(scanner, dataPesanan, subTotal);
	}
	
//	set method cetakStrukTransaksi
	public static void cetakStrukTransaksi(Scanner scanner, SimpanPesanan[] dataPesanan,double subTotal) {
		boolean isPilihan = true;
		//jika isPilihan true
		while(isPilihan) {
			System.out.print("Cetak Struk Pembayaran (ya/tidak)?: ");
			String isCetak = scanner.nextLine();
			//validasi input menu
			if (!isCetak.matches("ya|tidak")) {
				System.out.println("------------------------------");
			    System.out.println(">>>pilihan tidak ada!<<<");
			    continue;
			} 
			
			//convert string ke boolean
			isCetak = (isCetak.matches("ya")) ? "true" : "false";
			boolean pilihan = Boolean.parseBoolean(isCetak);
			isPilihan = pilihan;
			System.out.println("------------------------------");
			System.out.println("----------Program End---------");
			System.out.println("------------------------------");
			//akhir dari program
			
			//jika pilihan true maka cetak struk transaksi
			if(pilihan == true) {
				System.out.println("\n\n");
				System.out.println("-------Struk Pembayaran-------");
				System.out.println("------------------------------");
				//looping data pesanan
				for(SimpanPesanan data : dataPesanan) {
					if(data != null) {
						System.out.println("-------- Menu No : " + data.getId() + " ---------");
						System.out.println("------------------------------");
						System.out.println("Nama : " + data.getNama());
						System.out.println("Harga : " + data.getHarga());
						System.out.println("kategori : " + data.getKategori());
						System.out.println("Jumlah : " + data.getJumlah());
						System.out.println("Total : " + (data.getHarga() * data.getJumlah()));
						System.out.println("------------------------------");
					}
				}
				//jika ada promo maka tampilkan
				if(dataPesanan[5] != null) {
					System.out.println("---------Gratis Minuman-------");
					System.out.println(">>Untuk Promo beli 1 gratis 1<<");
					System.out.println("------------------------------");
					System.out.println("Nama : " + dataPesanan[5].getNama());
					System.out.println("kategori : " + dataPesanan[5].getKategori());
					System.out.println("Jumlah : " + dataPesanan[5].getJumlah());
					System.out.println("------------------------------");
				}
				//tampilkan total transaksi
				System.out.println("------------------------------");
				System.out.println("Sub Total: " + subTotal);
				System.out.println("Biaya Pajak : 10%");
				System.out.println("Biaya Pelayanan : Rp.20.000");
				System.out.println("Grand Total : " + hitungTransaksi(dataPesanan));
				System.out.println("------------------------------");
				System.out.println("---------Terimah Kasih--------");
				System.out.println("------------------------------");
				
				//set isPilihan menjadi false
				isPilihan = false;
			}
		}
	}
	
}

//class bersarang
//save data menu item child method dari main method
class MenuItem {
	//propert data
	private int id;
	private String nama;
	private double harga;
	private String kategori;
	
	//method save data item
	MenuItem(int id, String nama, double harga, String kategori) {
		this.id = id;
		this.nama = nama;
		this.harga = harga;
		this.kategori = kategori;
	}
	
	//method get data item
	public int getId() {
		return id;
	}
	
	public String getNama() {
		return nama;
	}
	
	public double getHarga() {
		return harga;
	}
	
	public String getKategori() {
		return kategori;
	}
		
}

//save data pesanan  child method dari main method
class SimpanPesanan {
	//prpoerty data
	private int id;
	private String nama;
	private double harga;
	private String kategori;
	private int jumlah;
	
	//method save data
	SimpanPesanan(int id, String nama, double harga, String kategori, int jumlah) {
		this.id = id;
		this.nama = nama;
		this.harga = harga;
		this.kategori = kategori;
		this.jumlah = jumlah;
	}
	
	//method get data
	public int getId() {
		return id;
	}
	
	public String getNama() {
		return nama;
	}
	
	public double getHarga() {
		return harga;
	}
	
	public String getKategori() {
		return kategori;
	}
	
	public int getJumlah() {
		return jumlah;
	}
	
	public double hitungSubTotal() {
		return harga * jumlah;
	}
}










