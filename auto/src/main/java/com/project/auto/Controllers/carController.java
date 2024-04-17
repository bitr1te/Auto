package com.project.auto.Controllers;

import com.project.auto.Models.Car;
import com.project.auto.Models.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class carController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping("/cars")
    public String mainCars(Model model) {
        Iterable<Car> cars = carRepository.findAll();
        model.addAttribute("cars", cars);
        return "cars";
    }

    @GetMapping("/cars/add")
    public String addCars(Model model) {
        return "cars-add";
    }

    @PostMapping("/cars/add")
    public String postCars(@RequestParam String target, @RequestParam String owner, @RequestParam String color,@RequestParam String carModel, Model model) {
        Car car = new Car(carModel, color, target, owner);
        carRepository.save(car);
        return "redirect:/cars";
    }

    @GetMapping("/cars/{id}")
    public String carDetails(@PathVariable(value = "id") long id, Model model) {
        if(!carRepository.existsById(id)) {
            return "redirect:/cars";
        }
        Optional<Car> car = carRepository.findById(id);
        ArrayList<Car> cars = new ArrayList<>();
        car.ifPresent(cars::add);
        model.addAttribute("cars", cars);
        return "car-details";
    }

    @GetMapping("/cars/{id}/edit")
    public String getCarDelete(@PathVariable(value = "id") long id, Model model) {
        Optional<Car> car = carRepository.findById(id);
        ArrayList<Car> cars = new ArrayList<>();
        car.ifPresent(cars::add);
        model.addAttribute("cars", cars);
        return "car-edit";
    }

    @PostMapping("/cars/{id}/edit")
    public String doCarEdit(@PathVariable(value = "id") long id, @RequestParam String target, @RequestParam String owner, @RequestParam String color,@RequestParam String carModel, Model model) {
        Car car = carRepository.findById(id).orElseThrow();
        car.setTarget(target);
        car.setOwner_name(owner);
        car.setColor(color);
        car.setModel(carModel);
        carRepository.save(car);
        return "redirect:/cars";
    }

    @PostMapping("/cars/{id}/delete")
    public String doCarDelete(@PathVariable(value = "id") long id, Model model) {
        Car car = carRepository.findById(id).orElseThrow();
        carRepository.delete(car);
        return "redirect:/cars";
    }
}