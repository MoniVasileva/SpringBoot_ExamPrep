package com.example.battleships.web;

import com.example.battleships.model.binding.ShipAddModel;
import com.example.battleships.services.ShipService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ships")
public class ShipController {
private ShipService shipService;
    @GetMapping("/add")
    public String getAddShip(){
        return "ship-add";
    }

    @PostMapping("/add")
    public String postAddShip(@Valid @ModelAttribute(name = "shipAddModel")ShipAddModel shipAddModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterModel", shipAddModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginModel", bindingResult);

            return "redirect:add";
        }
        this.shipService.addShip(shipAddModel);

        return "redirect:/home";
    }

    @ModelAttribute(name = "shipAddModel")
    public ShipAddModel shipAddModel(){
        return new ShipAddModel();
    }

}
