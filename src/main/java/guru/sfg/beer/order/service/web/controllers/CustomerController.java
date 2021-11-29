package guru.sfg.beer.order.service.web.controllers;

import guru.sfg.beer.order.service.services.CustomerService;
import guru.sfg.beer.order.service.web.model.CustomerPageList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public CustomerPageList listCustomers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                          @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        pageNumber = Optional.ofNullable(pageNumber).filter(nr -> nr > 0).orElse(0);
        pageSize = Optional.ofNullable(pageSize).filter(size -> size > 1).orElse(25);

        return customerService.listCustomers(PageRequest.of(pageNumber, pageSize));
    }
}
