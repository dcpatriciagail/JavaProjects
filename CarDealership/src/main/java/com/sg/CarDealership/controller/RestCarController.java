/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.controller;

import com.sg.CarDealership.dto.Model;
import com.sg.CarDealership.dto.Contact;
import com.sg.CarDealership.dto.Make;
import com.sg.CarDealership.dto.Purchase;
import com.sg.CarDealership.dto.SalesReport;
import com.sg.CarDealership.dto.SalesSearchModel;
import com.sg.CarDealership.dto.User;
import com.sg.CarDealership.dto.Vehicle;
import com.sg.CarDealership.service.CarModelService;
import com.sg.CarDealership.service.ContactService;
import com.sg.CarDealership.service.DuplicateMakeException;
import com.sg.CarDealership.service.DuplicateVINException;
import com.sg.CarDealership.service.InvalidContactException;
import com.sg.CarDealership.service.InvalidMakeException;
import com.sg.CarDealership.service.InvalidModelException;
import com.sg.CarDealership.service.InvalidPurchaseException;
import com.sg.CarDealership.service.InvalidUserException;
import com.sg.CarDealership.service.InvalidVehicleException;
import com.sg.CarDealership.service.MakeService;
import com.sg.CarDealership.service.PurchaseService;
import com.sg.CarDealership.service.SalesReportService;
import com.sg.CarDealership.service.UserService;
import com.sg.CarDealership.service.VehicleReportService;
import com.sg.CarDealership.service.VehicleService;
import com.sg.CarDealership.viewmodel.VehicleReport;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Patricia
 */
@RestController
@RequestMapping("/cardealership")
public class RestCarController {
 
    
    
@Autowired
    MakeService makeService;
    
    @Autowired
    CarModelService modelService;
    
    @Autowired
    VehicleService vehicleService;
    
    @Autowired
    ContactService contactService;
    
    @Autowired
    PurchaseService purchaseService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    VehicleReportService vehicleReportService;    
    
    @Autowired
    SalesReportService salesService;
    

    @CrossOrigin(origins = "*")
    @GetMapping("/home/index")
    public List<Vehicle> seeFeatured() {
        return vehicleService.searchForFeatured();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("inventory/new")
    public List<Vehicle> seeNewSearchResults(@RequestParam("makeOrModel") String makeOrModel,
            @RequestParam("minYear") Integer minYear,
            @RequestParam("maxYear") Integer maxYear,
            @RequestParam("minPrice") BigDecimal minPrice,
            @RequestParam("maxPrice") BigDecimal maxPrice) {
        return vehicleService.searchForNewVehicle(makeOrModel, minYear, maxYear, minPrice, maxPrice);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("inventory/used")
    public List<Vehicle> seeUsedSearchResults(@RequestParam String makeOrModel,
            @RequestParam Integer minYear,
            @RequestParam Integer maxYear,
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) {
        return vehicleService.searchForUsedVehicle(makeOrModel, minYear, maxYear, minPrice, maxPrice);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("inventory/details/{id}")
    public Vehicle seeDetailsForCarById(@PathVariable Integer id) {
        return vehicleService.readVehicle(id);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("home/contact")
    @ResponseStatus(HttpStatus.CREATED)
    public Contact createContact(@RequestBody Contact contact) throws InvalidContactException {
        return contactService.createContact(contact);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("sales/index")
    public List<Vehicle> seeInStockVehicles(@RequestParam String makeOrModel,
            @RequestParam Integer minYear,
            @RequestParam Integer maxYear,
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) {
        return vehicleService.seeInStockVehicles(makeOrModel, minYear, maxYear, minPrice, maxPrice);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("sales/purchase/{vehicleId}")
    public Vehicle getVehicleForPurchase(@PathVariable Integer vehicleId) {

        return vehicleService.readVehicle(vehicleId);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("sales/purchase/{vehicleId}")
    public ResponseEntity<Purchase> submitPurchase(@RequestBody Purchase purchase) throws InvalidPurchaseException, DuplicateVINException {

        try {
            return ResponseEntity.ok(purchaseService.createPurchase(purchase));
        } catch (InvalidPurchaseException  ex) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("admin/vehicles")
    public List<Vehicle> seeVehiclesForEdit(@RequestParam("makeOrModel") String makeOrModel,
            @RequestParam("minYear") Integer minYear,
            @RequestParam("maxYear") Integer maxYear,
            @RequestParam("minPrice") BigDecimal minPrice,
            @RequestParam("maxPrice") BigDecimal maxPrice) {
        return vehicleService.seeInStockVehicles(makeOrModel, minYear, maxYear, minPrice, maxPrice);

    }

    @CrossOrigin(origins = "*")
    @PostMapping("admin/addvehicle")
    public ResponseEntity<Vehicle> adminAddVehicle(@RequestBody Vehicle vehicle) throws DuplicateVINException {

        try {
            return ResponseEntity.ok(vehicleService.createVehicle(vehicle));
        } catch (InvalidVehicleException ex) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("admin/editvehicle/{vehicleId}")
    public Vehicle adminEditVehicle(@PathVariable Integer vehicleId) {
        return vehicleService.readVehicle(vehicleId);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("admin/editvehicle/{vehicleId}")
    public ResponseEntity<Vehicle> adminSubmitEdit(@RequestBody Vehicle vehicle)  {
        try {
            vehicleService.updateVehicle(vehicle);
        } catch (InvalidVehicleException ex) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(vehicle);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("admin/adduser")
    public ResponseEntity<User> adminAddUser(@RequestBody User user) {
        try {
            userService.createUser(user);
        } catch (InvalidUserException ex) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(user);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("admin/editvehicle{vehicleId}")
    public void adminDeleteVehicle(@PathVariable Integer vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("admin/models")
    public List<Model> seeAllModels() {
        return modelService.readAllModels();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("admin/addmodel")
    public ResponseEntity<Model> adminAddModel(@RequestBody Model model) throws InvalidModelException {
        try {
            return ResponseEntity.ok(modelService.createModel(model));
        } catch (InvalidModelException ex) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    @CrossOrigin(origins = "*")
    
    @GetMapping("admin/edituser/{userId}")
    public User adminEditUser(@PathVariable Integer userId) throws InvalidUserException {
        return userService.readUser(userId);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("admin/edituser/{userId}")
    public ResponseEntity<User> adminSubmitEditUser(@RequestBody User user) throws InvalidUserException {
        try {
            userService.updateUser(user);
        } catch (InvalidUserException ex) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(user);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.readAllUsers();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("admin/makes")
    public List<Make> seeAllMakes() {
        return makeService.readAllMakes();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("admin/addmake")
    public ResponseEntity<Make> adminAddMake(@RequestBody Make make) throws DuplicateMakeException {
        try {
            return ResponseEntity.ok(makeService.createMake(make));
        } catch (InvalidMakeException | DuplicateMakeException ex) {
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("reports/inventory")
    public ResponseEntity<List<List<VehicleReport>>> seeAllInventoryReports() {

        List<List<VehicleReport>> reports = new ArrayList<>();

        try {
            List<VehicleReport> newReport = vehicleReportService.getNewInventoryReport();
            List<VehicleReport> usedReport = vehicleReportService.getUsedInventoryReport();

            reports.add(newReport);
            reports.add(usedReport);

            return ResponseEntity.ok(reports);
        } catch (EmptyResultDataAccessException ex) {
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("reports/sales")
    public ResponseEntity<List<SalesReport>> seeAllSalesReportsByDate(@RequestBody SalesSearchModel search) {
        List<SalesReport> salesReports = new ArrayList<>();
        try {
            salesReports = salesService.seeSalesReportForDateRange(search.getUserName(), search.getFromDate(), search.getToDate());
            return ResponseEntity.ok(salesReports);
        } catch (EmptyResultDataAccessException ex) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("getuser/{userId}")
    public User getUser(@PathVariable Integer userId) throws InvalidUserException {
        return userService.readUser(userId);
    }
 
    @GetMapping("sales/index/models/{makeId}")
   public List<Model> seeModelsByMakes(@PathVariable("makeId") Integer makeId) {
       return modelService.getAllModelsByMake(makeId);
   }
}