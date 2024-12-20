package com.assignment.CarManagement.controller;

import com.assignment.CarManagement.CarDto;
import com.assignment.CarManagement.UserDto;
import com.assignment.CarManagement.dao.CarRepository;
import com.assignment.CarManagement.entities.Car;
import com.assignment.CarManagement.service.CarService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CarController {

    @Autowired
    CarService carService;

    @Autowired
    CarRepository carRepository;

//    @GetMapping("/view")
//    public String view()
//    {
//        return "view";
//    }

    @GetMapping("/viewUser")
    public String viewUser(Model model)
    {
        model.addAttribute("cars", carService.getAllCars());
        return "viewUser";
    }

    @GetMapping("/add")
    public String add(Model model)
    {
        model.addAttribute("car",new Car());
        return "addCar";
    }

    @GetMapping("/view")
    public String view(Model model)
    {
        model.addAttribute("cars", carService.getAllCars());
        return "view";
    }
    @PostMapping("/saveCar")
    public String saveCar(@Valid @ModelAttribute("car") CarDto carDto)
    {
        this.carService.saveCar(carDto);
        System.out.println(carDto.getName());
        System.out.println(carDto.getColor());
        System.out.println(carDto.getFuelType());
        System.out.println(carDto.getYear());
        System.out.println(carDto.getPrice());

        return "redirect:/view";
    }

    @PostMapping("/updatedCar")
    public String updateCar(@Valid @ModelAttribute("car") CarDto carDto, HttpSession session)
    {
        Car car = (Car)session.getAttribute("ucar");
        long id = car.getId();
        session.removeAttribute("ucar");
        this.carService.updateCar(carDto, id);
        System.out.println(car.getId());
        System.out.println(carDto.getColor());
        System.out.println(carDto.getFuelType());
        System.out.println(carDto.getYear());
        System.out.println(carDto.getPrice());

        return "redirect:/view";
    }
    @GetMapping("/update")
    public String update(Model model)
    {
        model.addAttribute("cars", carService.getAllCars());
        return "update";
    }

    @GetMapping("/updateCar/{id}")
    public String updateform(@PathVariable (value="id") long id, Model model, HttpSession session)
    {
        Car car = carService.getCarById(id);
        session.setAttribute("ucar",car);
        model.addAttribute("ucar",carService.getCarById(id));
        System.out.println(car.getId());
        return "updateform";
    }

    @GetMapping("/delete")
    public String delete(Model model)
    {
        model.addAttribute("cars", carService.getAllCars());
        return "delete";
    }

    @GetMapping("/admin")
    public String admin()
    {
        return "admin";
    }

    @GetMapping("/deleteCar/{id}")
    public String deleteCar(@PathVariable (value = "id") long id)
    {
        this.carService.deleteCarById(id);
        return "redirect:/delete";
    }

//    @GetMapping("/filter")
//    public List<Car> filterCars(
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String model,
//            @RequestParam(required = false) Integer year) {
//        return carRepository.findByNameContainingOrModelContainingOrYear(
//                name != null ? name : "",
//                model != null ? model : "",
//                year != null ? year : 0
//        );
//    }

    @ResponseBody
    @RequestMapping("/view/name/{name}")
    public List<Car> filterNamev(@PathVariable("name") String name, Model mod){
        System.out.println(name);
        List<Car> car = this.carRepository.findByNameContaining(name);
        mod.addAttribute("filter", car);
        return car;
    }

    @ResponseBody
    @RequestMapping("/view/model/{model}")
    public List<Car> filterModelv(@PathVariable("model") String model, Model mod){
        System.out.println(model);
        List<Car> car = this.carRepository.findByModelContaining(model);
        mod.addAttribute("filter", car);
        return car;
    }

    @ResponseBody
    @RequestMapping("/view/year/{year}")
    public List<Car> filterYearv(@PathVariable("year") int year, Model mod){
        System.out.println(year);
        List<Car> car = this.carRepository.findByYear(year);
        mod.addAttribute("filter", car);
        return car;
    }

    @ResponseBody
    @RequestMapping("/update/name/{name}")
    public List<Car> filterNameu(@PathVariable("name") String name, Model mod){
        System.out.println(name);
        List<Car> car = this.carRepository.findByNameContaining(name);
        mod.addAttribute("filter", car);
        return car;
    }

    @ResponseBody
    @RequestMapping("/update/model/{model}")
    public List<Car> filterModelu(@PathVariable("model") String model, Model mod){
        System.out.println(model);
        List<Car> car = this.carRepository.findByModelContaining(model);
        mod.addAttribute("filter", car);
        return car;
    }

    @ResponseBody
    @RequestMapping("/update/year/{year}")
    public List<Car> filterYearu(@PathVariable("year") int year, Model mod){
        System.out.println(year);
        List<Car> car = this.carRepository.findByYear(year);
        mod.addAttribute("filter", car);
        return car;
    }

    @ResponseBody
    @RequestMapping("/delete/name/{name}")
    public List<Car> filterNamed(@PathVariable("name") String name, Model mod){
        System.out.println(name);
        List<Car> car = this.carRepository.findByNameContaining(name);
        mod.addAttribute("filter", car);
        System.out.println(car.get(0).getName()+"I am name");
        return car;
    }

    @ResponseBody
    @RequestMapping("/delete/model/{model}")
    public List<Car> filterModeld(@PathVariable("model") String model, Model mod){
        System.out.println(model);
        List<Car> car = this.carRepository.findByModelContaining(model);
        mod.addAttribute("filter", car);
        return car;
    }

    @ResponseBody
    @RequestMapping("/delete/year/{year}")
    public List<Car> filterYeard(@PathVariable("year") int year, Model mod){
        System.out.println(year);
        List<Car> car = this.carRepository.findByYear(year);
        mod.addAttribute("filter", car);
        return car;
    }

}
