//package com.example.controller;
//
///*
// * Lop cha: Model, Vehicle, Booking
// */
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.example.iservice.IBookingService;
//import com.example.iservice.IBookingStatusService;
//import com.example.iservice.ICustomerService;
//import com.example.iservice.IManufactureService;
//import com.example.iservice.IModelService;
//import com.example.iservice.IVehicleCatogoryService;
//import com.example.iservice.IVehicleServcie;
//import com.example.model.Booking;
//import com.example.model.BookingStatus;
//import com.example.model.Custromer;
//import com.example.model.Manufacturer;
//import com.example.model.Model;
//import com.example.model.Vehicle;
//import com.example.model.VehicleCategory;
//import com.example.service.BookingService;
//import com.example.service.BookingStatusService;
//import com.example.service.CustomerService;
//import com.example.service.ManufacturerService;
//import com.example.service.ModelService;
//import com.example.service.VehicleCategoryService;
//import com.example.service.VehicleService;
//
//// hàm return phải log.info đẻ in
//// hàm ko trả về in trc tiếp
//@Component
//public class MyController implements CommandLineRunner {
//	private static final Logger log = LogManager.getLogger(MyController.class);
//
//	@Autowired
//	private IManufactureService manufactureService;
//
//	@Autowired
//	private IModelService modelService;
//
//	@Autowired
//	private IVehicleCatogoryService vehiclecategoryService;
//	@Autowired
//	private IVehicleServcie vehicleService;
//
//	@Autowired
//	private ICustomerService customerService;
//
//	@Autowired
//	private IBookingService bookingService;
//
//	@Autowired
//	private IBookingStatusService bookingStatusService;
//
//	@Transactional
//	@Override
//	public void run(String... args) throws Exception {
//
//		// SimpleDateFormat sdf = new SimpleDateFormat("dd//mm//yyyy");
//		log.info("_______________________________1. Lớp Manufacture__________________________________");
//		Manufacturer mf1 = new Manufacturer("Ma ma", "San xuat tai Thai lan");
//		Manufacturer mf2 = new Manufacturer("Hong Kong", "San xuat Hong Kong");
//		manufactureService.addManufacture(mf1);
//		manufactureService.addManufacture(mf2);
//		manufactureService.findAllManufacturer();
//		manufactureService.updateManufacture(new Manufacturer( "Nga", "San xuat tai Thai lan"));
//		manufactureService.deleteManufacture(1);
//		manufactureService.findManufactureById(2);
//		if (manufactureService.existManufacturer(1)) {
//			log.info("existManufacturer: yes");
//		} else {
//			log.info("existManufacturer: no");
//		}
//
//		log.info("_______________________________2. Lớp Model_lop cha_____________________________________");
//		Model m1 = modelService.addModel("20c/day", "N1", mf2);
//		Model m2 = modelService.addModel("20c/day", "N2", mf2);
//		modelService.findAllModel();
//		modelService.updateModel(1, "30c/ngay", "N1", mf2); ////
//		modelService.deleteModel(0);
//		modelService.findModelById(0);
//		log.info("_______________________________3. Lớp VehicleCategory__________________________________");
//		VehicleCategory vc1 = new VehicleCategory("Xe gia dinh");
//		vehiclecategoryService.addVehicleCategory(vc1);
//		vehiclecategoryService.findAllVehicleCategory();
//		vehiclecategoryService.updateVehicleCategory(6, "Xe mui tran dang cap 1");
//		vehiclecategoryService.deleteModel(0);
//		vehiclecategoryService.findByIdVehicleCategory(1);
//		log.info("_______________________________4. Lớp Vehicle_Lop cha__________________________________");
//		Vehicle v1 = vehicleService.addVehicle("60km/h", new Date("10/10/2017"), "50Mhz", m1, vc1);
//		v1.toString();
//		vehicleService.findAllVehicle();
//		vehicleService.updateVehicle(2, "80km/h", new Date("30/01/2018"), "80Mhz", m1, vc1);
//		vehicleService.deleteVehicle(0);
//		vehicleService.findVehicleById(1);
//		log.info("_______________________________5. Lớp Customer__________________________________");
//		Custromer c1 = new Custromer("Da Nang", "Yen Bai", "Lao Cai", "Viet Nam", "vn", "Khach hang tiem nang", "Truc",
//				"truc@gmail.com", "Nam", "029377232", "Dong Da");
//		customerService.addCustomer(c1);
//		customerService.findAllCustome();
//		customerService.updateCustomer(1, "Ha noi", "a", "aa", "sa", "asa", "Viet Nam", "Huong", "nguyenhuong@gmail.com", "Nu",
//				"013143743", "Cau Giay");
//		customerService.deleteCustomer(0);
//		customerService.findCustomerById(1);
//		log.info("___________________________6. Lớp Booking Status__________________________");
//		BookingStatus bs1 = new BookingStatus("Khach hang dat lich som nhat trong thang: 03/01/1018");
//		bookingStatusService.addBookingStatus(bs1);
//		BookingStatus bs2 = new BookingStatus("Khach hang vip trong thang: Bach Ma");
//		bookingStatusService.addBookingStatus(bs2);
//		bookingStatusService.findAllBookingStatus();
//		bookingStatusService.updateBookingStatus(1, "Dat lich ngay 02/03/2018");
//		bookingStatusService.deleteBookingStatus(0);
//		bookingStatusService.findBookingStatusById(1);
//		log.info("___________________________7. Lớp Booking_Lop cha__________________________");
//		bookingService.addBooking( (new Booking("Yes", new Date("20/11/2017"), new Date("29/11/2017"), "2016")), bs2, c1, v1);
//		bookingService.findAllBooking();
//		bookingService.updateBooking(1, new Booking("NOooo", new Date("20/02/2018"), new Date("29/02/2018"), "2019"), bs1, c1, v1);
//		bookingService.delete(1);
//		bookingService.findBookingById(1);
//
//		// log.info("_______________________________Lớp
//		// Model__________________________________");
//		// Manufacturer m0 = new Manufacturer("Japan", "Sản xuất tại Nhật Bản @.@");
//		// manufactureRepository.save(m0);
//		// Model model = new Model(2, "90chiec/tuan", "Toyota vận tải", m0);
//		//
//		// if(modelRepository.findOne(1)!= null) {
//		// modelRepository.delete(1);
//		// }else {
//		// log.debug("Error not exists!!!!");
//		// }
//		//
//		// Manufacturer m2 = new Manufacturer("Japan","Sản xuất tại Nhật Bản @.@");
//		// if(modelRepository.findOne(6)!=null) {
//		// Model model1 = new Model(2,"30chiec/tuan","Toyota 4 chỗ",m2);
//		// modelRepository.save(model1);
//		// modelRepository.flush();
//		// log.info("Update thanh cong");
//		// model.toString();
//		// }
//		//
//		// log.info("_______________________________Lớp
//		// Vehicle__________________________________");
//		// VehicleCategory vc1 = new VehicleCategory("Xe Toyota 4 chỗ laf loại xe hang
//		// sang");
//		//
//		// SimpleDateFormat date = new SimpleDateFormat("dd/mm/yyyy");
//		// Vehicle v1 = new Vehicle("200km",date.parse("20/10/2017")," 20
//		// xe/ngày",model,vc1);
//		// vehiclecategoryRepository.save(vc1);
//		// vehicleRepository.save(v1);
//		//
//		// log.info("_______________________________Lớp
//		// Booking__________________________________");
//		// Custromer c1 = new Custromer("104 Kim Mã, Hà Nội","12 ba Vì, hà nội","34 hưng
//		// yên","Việt Nam","Hưng Yên","Khach hàng tiềm năng","Nguyễn Như
//		// Lương","luong@gmail.com","male","0663434","Yên Lãng");
//		// BookingStatus bs1 = new BookingStatus("Lịch đặt đầu tiên");
//		//
//		// Booking b1 = new
//		// Booking("Yes",date.parse("03/01/2018"),date.parse("11/01/2018"),"phòng 402,
//		// Kim Mã, Hà Nội",v1,c1,bs1);
//		// bookingStatusRepository.save(bs1);
//		// customerRepository.save(c1);
//		// bookingRepository.save(b1);
//
//		// log.info("_______________________________Lớp
//		// manufacturer__________________________________");
//		// Thêm 1 manufactures
//		// Manufacturer manu1 = new Manufacturer("Japan","Sản xuất tại Nhật Bản @.@");
//		// manufactureRepository.save(manu1);
//		// manu1.toString();
//		// Manufacturer manu2 = new Manufacturer("Korea", "Sản xuất tại Hàn Quốc ^.^");
//		// manufactureRepository.save(manu2);
//		// manu2.toString();
//		// Manufacturer manu3 = new Manufacturer("American", "Sản xuất tại Mĩ ^.^");
//		// manufactureRepository.save(manu3);
//		// manu3.toString();
//		// log.info("Thêm thành công");
//		//
//		// Model m9 = new Model("30chiec/tuan","Toyota 4 chỗ",manu1);
//		// modelRepository.save(m9);
//		// log.info("In ra thành công! ");
//
//		// Update 1 manufacture
//		// Manufacturer manu4 = manufactureRepository.findOne(1);
//		// if( manu4 != null ) {
//		// manu4.setManufacturerName("French");
//		// manu4.setManufacurerDetails("Sản xuất tại Pháp");
//		// manufactureRepository.saveAndFlush(manu4);
//		// manu4.toString();
//		// }
//
//		// Delete 1 manufacture;
//		// manufactureRepository.delete(1);
//
//		// Hiển thi tất cả manufacturer
//		// for(Manufacturer manufacture: manufactureRepository.findAll()) {
//		// log.info(" "+ manufacture.getManufacturerCode()+ " , "+
//		// manufacture.getManufacturerName()+" , "+
//		// manufacture.getManufacurerDetails());
//		// }
//
//		// Tìm kiếm theo tên
//		// String name = null;
//		// Set<Manufacturer> manu = (Set<Manufacturer>)
//		// manufactureRepository.getManufacturerByName(name);
//		// for(Manufacturer me: manu) {
//		// if((me.getManufacturerName()).equals(name)) {
//		//
//		// }
//	}
//
//}
