package guru.sfg.beer.order.service.services;

import guru.sfg.beer.order.service.web.model.CustomerPageList;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    CustomerPageList listCustomers(Pageable pageable);
}
