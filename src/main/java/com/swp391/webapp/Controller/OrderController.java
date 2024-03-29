package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.*;
import com.swp391.webapp.Entity.Enum.OrderStatus;
import com.swp391.webapp.Entity.Enum.TransactionStatus;
import com.swp391.webapp.ExceptionHandler.WalletIsInsufficient;
import com.swp391.webapp.Repository.OrderRepository;
import com.swp391.webapp.Repository.PackageRepository;
import com.swp391.webapp.Repository.ScheduleWorkingRepository;
import com.swp391.webapp.Service.*;
import com.swp391.webapp.dto.OrderDTO;
import com.swp391.webapp.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ScheduleWorkingRepository scheduleWorkingRepository;
    @Autowired
    private ScheduleWorkingService scheduleWorkingService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private WalletService walletService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountUtils accountUtils;
    @Autowired
    private EmailService emailService;

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAllOrders() {
        List<OrderEntity> Orders = orderService.getAllOrders();
        return ResponseEntity.ok(Orders);
    }

    @GetMapping("/{OrderId}")
    public ResponseEntity<OrderEntity> getOrderById(@PathVariable int OrderId) {
        OrderEntity Order = orderService.findOrderById(OrderId);
        return ResponseEntity.ok(Order);
    }

    @GetMapping("/guest")
    public ResponseEntity<List<OrderEntity>> getAllOrdersByGuest() {
        List<OrderEntity> Orders = orderService.getAllOrdersByGuest();
        return ResponseEntity.ok(Orders);
    }

    @GetMapping("/guest/{accountId}")
    public ResponseEntity<List<OrderEntity>> getAllOrdersByGuestId(@PathVariable int accountId) {
        List<OrderEntity> Orders = orderService.getAllOrdersByGuestId(accountId);
        return ResponseEntity.ok(Orders);
    }

    @GetMapping("/host/{hostId}")
    public ResponseEntity<List<OrderEntity>> getAllOrdersByHost(@PathVariable int hostId) {
        List<OrderEntity> Orders = orderService.getAllOrdersByHost(hostId);
        return ResponseEntity.ok(Orders);
    }

//    @PostMapping("making-order")
//    public ResponseEntity<OrderEntity> saveOrder(@RequestBody OrderDTO Order) {
//        OrderEntity savedOrder = orderService.makeOrder(Order);
//        return ResponseEntity.ok(savedOrder);
//    }

    @PostMapping("/create-payment")
    public ResponseEntity createUrl(@RequestBody OrderDTO orderDTO) throws NoSuchAlgorithmException, InvalidKeyException, Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime createDate = LocalDateTime.now();
        Date createdate = new Date();
        String formattedCreateDate = createDate.format(formatter);
        //Lấy thời gian đặt lịch
        Schedule schedule = scheduleWorkingService.findScheduleByID(orderDTO.getScheduleId());
//        schedule.setBusy(true);
        scheduleWorkingService.save(schedule);
        OrderEntity ordered = new OrderEntity();
        //Tạo thời gian đặt order
        ordered.setCreateAt(new Date());
        //Lấy tài khoản người đặt
        ordered.setAccount(accountUtils.getCurrentAccount());
        PackageEntity packageEntity = packageRepository.findPackageByPackageID(orderDTO.getPackageId());
        ordered.setPackageEntity(packageEntity);
        ordered.setSchedule(schedule);
        //Lấy quantity ra
        int quantity = orderDTO.getOrderDetailDTOList().size();
        //Lay so tien can dat coc
        long depositMoney = orderDTO.getTotalPrice() * 40 / 100;
        //Đặt trạng thái của order
        ordered.setStatus(OrderStatus.ORDERED);
        ordered.setTotalPrice(BigDecimal.valueOf(orderDTO.getTotalPrice()));
        ordered.setQuantity(quantity + 1);
        ordered.setNotes(orderDTO.getNotes());
        ordered.setPhone(orderDTO.getPhoneNumber());
        ordered.setCreateAt(createdate);
        ordered.setCustomerName(orderDTO.getUsername());
        ordered.setDate(orderDTO.getDate());
        ordered.setDepositedMoney(BigDecimal.valueOf(depositMoney));
        ordered.setRemainingMoney(BigDecimal.valueOf(orderDTO.getTotalPrice() - depositMoney));
        ordered.setVenue(orderDTO.getVenue());
        ordered.setCustomerEmail(orderDTO.getEmail());

        //Luu order voi Status = ORDERED
        OrderEntity newOrder = orderService.createOrder(ordered, orderDTO.getOrderDetailDTOList());

        //Kiem tra so du trong vi cua nguoi dung
        //Neu du tien thi thanh toan = vi, neu khong thi qua VNPay

        String tmnCode = "II9036T8";
        String secretKey = "AFWMAKAMRNPUQTQWFDCGXTXPQBQKFIRF";
        String vnpUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
        String returnUrl = "http://birthdayblitzhub.online/success";

        String currCode = "VND";
        Map<String, String> vnpParams = new TreeMap<>();
        vnpParams.put("vnp_Version", "2.1.0");
        vnpParams.put("vnp_Command", "pay");
        vnpParams.put("vnp_TmnCode", tmnCode);
        vnpParams.put("vnp_Locale", "vn");
        vnpParams.put("vnp_CurrCode", currCode);
        vnpParams.put("vnp_TxnRef", newOrder.getOrderID() + "");
        vnpParams.put("vnp_OrderInfo", "Thanh toan cho ma GD: " + newOrder.getOrderID() + "");
        vnpParams.put("vnp_OrderType", "other");
        vnpParams.put("vnp_Amount", String.valueOf(100 * depositMoney));
        vnpParams.put("vnp_ReturnUrl", returnUrl);
        vnpParams.put("vnp_CreateDate", formattedCreateDate);
        vnpParams.put("vnp_IpAddr", "http://birthdayblitzhub.online/");

        StringBuilder signDataBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : vnpParams.entrySet()) {
            signDataBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()));
            signDataBuilder.append("=");
            signDataBuilder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
            signDataBuilder.append("&");
        }
        signDataBuilder.deleteCharAt(signDataBuilder.length() - 1); // Remove last '&'

        String signData = signDataBuilder.toString();
        String signed = generateHMAC(secretKey, signData);

        vnpParams.put("vnp_SecureHash", signed);

        StringBuilder urlBuilder = new StringBuilder(vnpUrl);
        urlBuilder.append("?");
        for (Map.Entry<String, String> entry : vnpParams.entrySet()) {
            urlBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()));
            urlBuilder.append("=");
            urlBuilder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
            urlBuilder.append("&");
        }
        urlBuilder.deleteCharAt(urlBuilder.length() - 1); // Remove last '&'

        return ResponseEntity.ok(urlBuilder.toString());
    }

    //Guest thanh toan cho order
    //Them tien vao vi admin
    //Tao transaction
    //Update status = PAID cho order
    //Dat coc = 40% tong gia tri don hang
    @GetMapping("/update-order")
    public OrderEntity orderSuccess(@RequestParam int orderId) {
        OrderEntity ordered = orderService.findOrderById(orderId);
        ordered.setStatus(OrderStatus.PAID);
        //Tru tien tai khoan guest
        //Tai khoan ngan hang cua Guest truc tiep chuyen vao wallet cua admin
        //Them tien vo tai khoan admin
        WalletEntity guestWallet = walletService.getWalletByAccount(ordered.getAccount());
        walletService.guestPayForOrder(ordered, guestWallet);

        //Gui mail cho khach hang thong bao rang order da duoc dat
        AccountEntity hostAccount = ordered.getPackageEntity().getAccount();
        EmailDetail emailDetail = new EmailDetail();
        emailDetail.setRecipient(hostAccount.getEmail());
        emailDetail.setName(hostAccount.getName());
        emailDetail.setSubject("Notification!");
        emailDetail.setMsgBody("There is a new order!");
        emailService.sendHostThereIsNewOrder(emailDetail);

        //Gui mail cho host bao la da co 1 don hang moi
        AccountEntity guestAccount = ordered.getPackageEntity().getAccount();
        emailDetail = new EmailDetail();
        emailDetail.setRecipient(guestAccount.getEmail());
        emailDetail.setName(guestAccount.getName());
        emailDetail.setSubject("Congratulation!");
        emailDetail.setMsgBody("Your booking is successful!");
        emailService.sendGuestBookingInformation(emailDetail);

        return orderService.saveOrder(ordered);
    }

    @GetMapping("/ordered/cancelled")
    public List<OrderEntity> getcancelledOrder() {
        return orderService.getCancelOrder();
    }

    @PostMapping("/pay-with-wallet")
    public OrderEntity payOrderWithWallet(@RequestBody OrderDTO orderDTO) {

        //Lấy ngày đặt lịch
        Date createdate = new Date();
        //Lấy thời gian đặt lịch
        Schedule schedule = scheduleWorkingService.findScheduleByID(orderDTO.getScheduleId());
//        schedule.setBusy(true);
        scheduleWorkingService.save(schedule);
        OrderEntity ordered = new OrderEntity();
        //Tạo thời gian đặt order
        ordered.setCreateAt(new Date());
        //Lấy tài khoản người đặt
        ordered.setAccount(accountUtils.getCurrentAccount());
        PackageEntity packageEntity = packageRepository.findPackageByPackageID(orderDTO.getPackageId());
        ordered.setPackageEntity(packageEntity);
        ordered.setSchedule(schedule);
        //Lấy quantity ra
        int quantity = orderDTO.getOrderDetailDTOList().size();
        //Lay so tien can dat coc
        long depositMoney = orderDTO.getTotalPrice() * 40 / 100;

        WalletEntity guestWallet = walletService.getWalletByAccount(accountUtils.getCurrentAccount());
        if (guestWallet.getTotalMoney().longValue() < depositMoney) {
            throw new WalletIsInsufficient("Your wallet doesn't have enough money!");
        } else {
            //Đặt trạng thái của order
            ordered.setStatus(OrderStatus.ORDERED);
            ordered.setTotalPrice(BigDecimal.valueOf(orderDTO.getTotalPrice()));
            ordered.setQuantity(quantity + 1);
            ordered.setNotes(orderDTO.getNotes());
            ordered.setPhone(orderDTO.getPhoneNumber());
            ordered.setCreateAt(createdate);
            ordered.setCustomerName(orderDTO.getUsername());
            ordered.setDate(orderDTO.getDate());
            ordered.setDepositedMoney(BigDecimal.valueOf(depositMoney));
            ordered.setRemainingMoney(BigDecimal.valueOf(orderDTO.getTotalPrice() - depositMoney));
            ordered.setVenue(orderDTO.getVenue());
            ordered.setCustomerEmail(orderDTO.getEmail());

            //Luu order voi Status = ORDERED
            OrderEntity newOrder = orderService.createOrder(ordered, orderDTO.getOrderDetailDTOList());

            //Bat dau thanh toan
            ordered.setStatus(OrderStatus.PAID);
            //Tru tien tai khoan guest
            //Tai khoan ngan hang cua Guest truc tiep chuyen vao wallet cua admin
            //Them tien vo tai khoan admin
            walletService.guestPayForOrderThroughtWallet(ordered, guestWallet);

            //Gui mail cho khach hang thong bao rang order da duoc dat
            AccountEntity hostAccount = ordered.getPackageEntity().getAccount();
            EmailDetail emailDetail = new EmailDetail();
            emailDetail.setRecipient(hostAccount.getEmail());
            emailDetail.setName(hostAccount.getName());
            emailDetail.setSubject("Congratulation!");
            emailDetail.setMsgBody("There is a new order!");
            emailService.sendGuestBookingInformation(emailDetail);

            //Gui mail cho host bao la da co 1 don hang moi

            return orderService.saveOrder(ordered);
        }
    }

    @PostMapping("/pay-ordered-payment/{orderId}")
    public ResponseEntity payForOrderedOrderURL(@PathVariable int orderId) throws NoSuchAlgorithmException, InvalidKeyException, Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime createDate = LocalDateTime.now();
        String formattedCreateDate = createDate.format(formatter);

        OrderEntity newOrder = orderService.findOrderById(orderId);

        long depositMoney = newOrder.getTotalPrice().longValue() * 40 / 100;

        //Kiem tra so du trong vi cua nguoi dung
        //Neu du tien thi thanh toan = vi, neu khong thi qua VNPay

        String tmnCode = "II9036T8";
        String secretKey = "AFWMAKAMRNPUQTQWFDCGXTXPQBQKFIRF";
        String vnpUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
        String returnUrl = "http://localhost:5173/success";

        String currCode = "VND";
        Map<String, String> vnpParams = new TreeMap<>();
        vnpParams.put("vnp_Version", "2.1.0");
        vnpParams.put("vnp_Command", "pay");
        vnpParams.put("vnp_TmnCode", tmnCode);
        vnpParams.put("vnp_Locale", "vn");
        vnpParams.put("vnp_CurrCode", currCode);
        vnpParams.put("vnp_TxnRef", newOrder.getOrderID() + "");
        vnpParams.put("vnp_OrderInfo", "Thanh toan cho ma GD: " + newOrder.getOrderID() + "");
        vnpParams.put("vnp_OrderType", "other");
        vnpParams.put("vnp_Amount", String.valueOf(100 * depositMoney));
        vnpParams.put("vnp_ReturnUrl", returnUrl);
        vnpParams.put("vnp_CreateDate", formattedCreateDate);
        vnpParams.put("vnp_IpAddr", "http://birthdayblitzhub.online/");

        StringBuilder signDataBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : vnpParams.entrySet()) {
            signDataBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()));
            signDataBuilder.append("=");
            signDataBuilder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
            signDataBuilder.append("&");
        }
        signDataBuilder.deleteCharAt(signDataBuilder.length() - 1); // Remove last '&'

        String signData = signDataBuilder.toString();
        String signed = generateHMAC(secretKey, signData);

        vnpParams.put("vnp_SecureHash", signed);

        StringBuilder urlBuilder = new StringBuilder(vnpUrl);
        urlBuilder.append("?");
        for (Map.Entry<String, String> entry : vnpParams.entrySet()) {
            urlBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()));
            urlBuilder.append("=");
            urlBuilder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
            urlBuilder.append("&");
        }
        urlBuilder.deleteCharAt(urlBuilder.length() - 1); // Remove last '&'

        return ResponseEntity.ok(urlBuilder.toString());
    }

    @PostMapping("/host/refuse-order/{orderId}")
    public void refuseOrder(@PathVariable int orderId) {
        orderService.refuseOrder(orderId);
    }

    @PostMapping("/host/accept-order/{orderId}")
    public void acceptOrder(@PathVariable int orderId) {
        orderService.acceptOrder(orderId);
    }

    @PostMapping("/guest/done-order/{orderId}")
    public void doneOrder(@PathVariable int orderId) {
        orderService.doneOrder(orderId);
    }

    @PutMapping("/guest/cancel-order/{orderId}")
    public String cancelOrder(@PathVariable int orderId) {
        orderService.cancelOrder(orderId);
        return "Cancelled";
    }

    private String generateHMAC(String secretKey, String signData) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hmacSha512 = Mac.getInstance("HmacSHA512");
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
        hmacSha512.init(keySpec);
        byte[] hmacBytes = hmacSha512.doFinal(signData.getBytes(StandardCharsets.UTF_8));

        StringBuilder result = new StringBuilder();
        for (byte b : hmacBytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
